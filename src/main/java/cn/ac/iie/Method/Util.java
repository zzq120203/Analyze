package cn.ac.iie.Method;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ac.iie.Entity.GlobalConfig;
import cn.ac.iie.Entity.URLDetailEntity;
import net.sf.json.JSONObject;

public class Util {
	public static Logger logger = LoggerFactory.getLogger(Util.class);
	public static GlobalConfig gConfig;
	
	public Util(GlobalConfig gc) {
		  gConfig = gc;
	}
	//爬取成功后写入Json文件，放入返回网闸内的路径
	public static boolean writeToJsonFile(URLDetailEntity uentity,String fileDirPath ){
		
		BufferedWriter output = null;
		try {		
			output = new BufferedWriter(new FileWriter(fileDirPath + File.separator + uentity.getG_id()));
			JSONObject outjobj = JSONObject.fromObject(uentity);
			// 删除爬虫部分增加字段
			outjobj.remove("crawlercount");
			outjobj.remove("crawler_fail_reason");
			outjobj.remove("crawler_last_process_time");
			output.write(outjobj + "\n");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}finally{
			if( output!=null){
				try {
					output.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return true;		
	}
//	
	public  static void deleteFile(String filepath){ 
		File file = new File(filepath);  
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if(file.exists() && file.isFile()){
			if(file.delete()) {  
				//**********log****true*******
				System.out.println("delete:" + filepath + " success."); 
				logger.info("Delete file success. File:" + filepath);
			}
			else{
				//**********log*****false******
				System.out.println("delete:" + filepath + " failed.");  
				logger.error("Delete file failed. File:" + filepath);
			}  
		}
		else{  
			//**********log*****false******
			System.out.println("file:" + filepath + " not existed.");  
			logger.error("File not existed, delete failed. File:" + filepath);
		}  
	}  
}
