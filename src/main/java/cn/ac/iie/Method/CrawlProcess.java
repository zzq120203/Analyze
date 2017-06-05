package cn.ac.iie.Method;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ac.iie.Entity.GlobalConfig;
import cn.ac.iie.Entity.URLDetailEntity;
import cn.ac.iie.Entity.WEBContentEntity;

public class CrawlProcess {

	private static Logger logger;
	private GlobalConfig gConfig;
	private ArrayList<LinkedBlockingQueue<URLDetailEntity>> inputURLDBlockingQList;
	private ArrayList<Thread> crawlThreads;
	
	
	public static AtomicLong false_status_code_nr = new AtomicLong(0L);
	public static AtomicLong httpStatusException_nr = new AtomicLong(0L);
	public static AtomicLong timeoutException_nr = new AtomicLong(0L);
	public static AtomicLong unknownHostException_nr = new AtomicLong(0L);
	public static AtomicLong connectException_nr = new AtomicLong(0L);
	public static AtomicLong iOException_nr = new AtomicLong(0L);
	public static AtomicLong exception_nr = new AtomicLong(0L);
	public static AtomicLong title_null_nr = new AtomicLong(0L);

	public static AtomicLong crawled_nr = new AtomicLong(0L);
	public static AtomicLong total_nr = new AtomicLong(0L);
	
	
	public CrawlProcess(GlobalConfig gc, ArrayList<LinkedBlockingQueue<URLDetailEntity>> obtainSrcBQList) {
		logger = LoggerFactory.getLogger(CrawlProcess.class);
		gConfig = gc;
		inputURLDBlockingQList = obtainSrcBQList;
		crawlThreads = new ArrayList<Thread>();
	}

	public boolean initalize() {
		try {
			// run logic tree threads
			for (int i = 0; i < gConfig.maxCrawlThreadCount; i++) {
				CrawlThread sThread = new CrawlThread(inputURLDBlockingQList.get(i % gConfig.maxBlockQueue));
				Thread proThread = new Thread(sThread);
				crawlThreads.add(proThread);
				proThread.start();
			}
			logger.debug("initalize crawling threads success!");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return true;
	}

	// Multiple thread
	class CrawlThread implements Runnable {

		private BlockingQueue<URLDetailEntity> srcBlockQ;

		public CrawlThread(BlockingQueue<URLDetailEntity> bq) {
			this.srcBlockQ = bq;
		}

		// @Override
		public void run() {
			while (!gConfig.stopCrawlProcessThread) {
				URLDetailEntity detailEntity = new URLDetailEntity();
				try {
					detailEntity = srcBlockQ.take();
					total_nr.incrementAndGet();
					WEBContentEntity webContentEntity = getText(detailEntity.getUrl(), gConfig.timeout);
					if ((webContentEntity.getTitle() != null && webContentEntity.getTitle().length() > 0)
							||(webContentEntity.getContent() != null && webContentEntity.getContent().length() > 0))  {
						detailEntity.setUrl_title(webContentEntity.getTitle());
						detailEntity.setUrl_content(webContentEntity.getContent());
						Util.writeToJsonFile(detailEntity,gConfig.crawledFilePath);
						crawled_nr.incrementAndGet();
						logger.info("6666666666666666666666666: " + detailEntity.getG_id());
					} else {
						title_null_nr.incrementAndGet();
						logger.info("5555555555555555555555555" + detailEntity.toString());
						Util.writeToJsonFile(detailEntity,gConfig.errorFilePath);
					}
				} catch (Exception e) {
					Util.writeToJsonFile(detailEntity, gConfig.errorFilePath);
					exception_nr.incrementAndGet();
					logger.error(e.getMessage());
				}
			}
			logger.info("logic process Thread exiting...");
		}
		
	}
	public static WEBContentEntity getText(String url, int timeout){
		WEBContentEntity title_content = new WEBContentEntity();
		int count =0;
//		if(url.length() >5 && url.substring(0,4).equals("http") == false)
//			url= "http://" + url;
		int url_status_code = 0 ;
		Connection connection = null;
		Document document = null;
		try {
//			 connection  =  Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648; CIBA) ").timeout(30000);
//			 connection  =  Jsoup.connect(url).userAgent("Mozilla/5.0 (Linux; U; Android 4.1.2; zh-cn; GT-I9300 Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MicroMessenger/5.2.380").timeout(30000);
			 connection  =  Jsoup.connect(url).userAgent("Mozilla/5.0 (Linux; Android 5.0.1; M040 Build/LRX22C) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.8 TBS/036872 Safari/537.36 MicroMessenger/6.3.31.940 NetType/WIFI Language/zh_CN")
					 .ignoreContentType(true).timeout(timeout);
			 url_status_code = connection.execute().statusCode();
			if(url_status_code == 200 || url_status_code == 302)
			{
				document = connection.get();
				for( count=0; count<2 ; count++){
					if( (Pattern.compile("wxn.qq.com").matcher(url).find() ) == true)
						title_content = GetWebInfo.GetWeiXinNews(document);
					else if( ( Pattern.compile("weixin.qq.com").matcher(url).find() ) == true)
						title_content = GetWebInfo.GetWeiXinGZHH(document);
					else if( ( Pattern.compile(".qq.com").matcher(url).find() ) == true)
						title_content = GetWebInfo.GetTecentNews(document);
					else if( ( Pattern.compile(".ifeng.com").matcher(url).find() ) == true)
						title_content = GetWebInfo.GetIFeng(document);
					else if( ( Pattern.compile("tieba.baidu.com").matcher(url).find() ) == true)
						title_content = GetWebInfo.GetBaiduTB(document);
					else if( ( Pattern.compile(".people.com.cn").matcher(url).find() ) == true)
						title_content = GetWebInfo.GetPeopleNews(document);
					else if( ( Pattern.compile(".zhihu.com").matcher(url).find() ) == true)
						title_content = GetWebInfo.GetZhiHu(document);
					else {
						title_content = GetWebInfo.GetTotal(document);
					}
					return title_content;
				}
			}else{
				false_status_code_nr.incrementAndGet();
				logger.info("----------------totally url_filter_nr: " + false_status_code_nr.get());
				logger.info("filter url :" + url + "[statuscode:" + url_status_code+"]");
			}
		} catch (HttpStatusException e) {
			httpStatusException_nr.incrementAndGet();
			logger.error("-exception--------" + url + "--------"+ "[statuscode:" + e.getStatusCode()+"]");
		} catch (SocketTimeoutException e) {
			timeoutException_nr.incrementAndGet();
			logger.error("SocketTimeoutException : url=" + url);
		} catch (ConnectException e) {
			connectException_nr.incrementAndGet();
			logger.error("ConnectException : url=" + url);
		} catch (UnknownHostException e) {
			unknownHostException_nr.incrementAndGet();
			logger.error("UnknownHostException : url=" + url);
		} catch (IOException e) {
			iOException_nr.incrementAndGet();
			logger.error(e.getMessage(), e);
		}
		return title_content;
	}
		
	public static void main(String[] args) {
		String url = "http://picosong.com/cdn/7535bafbec81e9ff83a1023441ce63d0.mp3";
		WEBContentEntity webContentEntity = getText(url, 10000);
		System.out.println(webContentEntity.toString());
	}
}
