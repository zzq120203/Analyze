package cn.ac.iie.Method;

import cn.ac.iie.Entity.*;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PreAllocate implements Runnable {
	static Logger log = Logger.getLogger(PreAllocate.class.getName());
	private ArrayList<LinkedBlockingQueue<URLDetailEntity>> outputDXOBlockingQList;
	private static GlobalConfig gConfig;
	private AtomicLong obtain_url_nr = new AtomicLong(0);
	private AtomicLong filter_url_nr = new AtomicLong(0);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public PreAllocate(GlobalConfig config){
		gConfig = config;
		outputDXOBlockingQList = new ArrayList<LinkedBlockingQueue<URLDetailEntity>>();
	    for (int i = 0; i < gConfig.maxBlockQueue; i++) {
	    	LinkedBlockingQueue<URLDetailEntity> dxoBQueue = new LinkedBlockingQueue<URLDetailEntity>(gConfig.maxBlockingQueueCapacity);
	    	outputDXOBlockingQList.add(dxoBQueue);
	    }
	}
	
	public long getObtainNr() {
		return obtain_url_nr.get();
	}
	public long getFilterNr() {
		return filter_url_nr.get();
	}
	
	public ArrayList<LinkedBlockingQueue<URLDetailEntity>> getOutputDXOBlockingQList() {
		return outputDXOBlockingQList;
	}

	@Override
	public void run() {
		System.out.println("process:" + gConfig.getFilePath+ "\t" + gConfig.crawledFilePath); 
		File todofile = new File(gConfig.getFilePath);
		while( true ){
			int count = 0;
			try {
				// 获取网闸内传入的json文件
				String[] filelist = todofile.list();
				
				for( String tt : filelist){
					if( tt.endsWith(".json") ){
						String fname = gConfig.getFilePath + File.separator +  tt;
						File file = new File(fname);
						if (file.length() == 0)
							continue;
						URLDetailEntity uentity = mapper.readValue(file, URLDetailEntity.class);
						if(uentity != null){
							while (outputDXOBlockingQList.get(count % gConfig.maxBlockQueue).remainingCapacity() <= 0) {
								Thread.sleep(10);
	                        }
							outputDXOBlockingQList.get(count % gConfig.maxBlockQueue).put(uentity);
							obtain_url_nr.incrementAndGet();
							Util.deleteFile(fname);
						}else{
							log.info("unformal input file\n");
							filter_url_nr.incrementAndGet();
							//mv
							Util.writeToJsonFile(uentity,gConfig.errorFilePath);
						}
					}
					count++;
				}
			} catch (InterruptedException e) {
				log.error(e.getMessage(), e);
			} catch (JsonParseException e) {
				log.error(e.getMessage(), e);
			} catch (JsonMappingException e) {
				log.error(e.getMessage(), e);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}//while
	}
	
	
//	
//	private static void MoveFile(String filepath, String tofilepath){
//	 
//		File afile = new File(filepath);
//		String fileto = null;
//		if( tofilepath.endsWith("\\") || tofilepath.endsWith(File.separator))
//			fileto = tofilepath + afile.getName();
//		else
//			fileto = tofilepath + File.separator + afile.getName();
//		if( afile.renameTo( new File(fileto)  ) ){
//			log.info("File move to :" + fileto);
//		} else{
//			log.error("File move failed.Original file:" + filepath);
//			Processing2.DeleteFile(filepath);
//		}
//	}
//	
//	
	
}
