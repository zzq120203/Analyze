package cn.ac.iie.Entity;

import java.util.HashMap;
import java.util.HashSet;

public class GlobalConfig {
	
	public int maxBlockQueue;
	public int maxBlockingQueueCapacity;
	public int maxCrawlThreadCount; 
	public String configFilePath;
	public String getFilePath;
	public String crawledFilePath;
	public String errorFilePath;
	public boolean stopCrawlProcessThread;
	public int timeout;
	
//	public boolean stopInfoObtainThread;
//	public boolean stopDetailObtainThread;
//	public boolean stopLogicProcessThread;
//	public boolean stopTransThread;
//	public boolean stopPushResultThread;
//	public boolean stopTransputClientThread;
//	public boolean stopTransputHistoryHotsThread;
	
	
//	public String RDBTableNameOfWl;
//	public int maxTransmitThreadCount;
//	public int maxBlockQueue;
//	public int maxBlockingQueueCapacity;
//	public int maxLogicProcThreadCount;
//	public String consumerNameSrv;
//	public String consumerInstanceName;
//	public String MQTopicName;
//	public String redisConfigStr;
//	public long persistInterval;
//	public HashSet<String> whiteWebSiteSet;
//	public HashSet<String> blackWebSiteSet;
//	public HashMap<String, HashMap<String, String>> url_bw_list;

//	public String rdbUrl;
//	public String rdbDriver;
//	public String rdbPwd;
//	public String rdbUser;
//	public String mppDataCenter;
//	public String mppKeySpace;
//	public String mppConnAddress;
//	public int mppPort;
//	public String mppUserName;
//	public String mppPassword;
//
//	public String oriMessMQAddress;
//	public int oriMessMQPort;
//	public String oriMessConsumerGroup;
//	public String oriMessTopic;
//	
//	public String URLconsumerNameSrv;
//	public String putURLTopic;
//	public String getURLTopic;
//	public String URLconsumerInstanceName;
//	
//	public boolean isRocketmq;
//	
//	public int BFexpectedInsertions;
//	public boolean stopUrlData;
	

	public GlobalConfig() {
//		this.stopInfoObtainThread = false;
//		this.stopDetailObtainThread = false;
//		this.stopLogicProcessThread = false;
		this.stopCrawlProcessThread = false;
	}

	@Override
	public String toString() {
		return "GlobalConfig [maxBlockQueue=" + maxBlockQueue + ", maxBlockingQueueCapacity=" + maxBlockingQueueCapacity
				+ ", maxCrawlThreadCount=" + maxCrawlThreadCount + ", configFilePath=" + configFilePath
				+ ", getFilePath=" + getFilePath + ", crawledFilePath=" + crawledFilePath + ", stopCrawlProcessThread="
				+ stopCrawlProcessThread + "]";
	}


//	@Override
//	public String toString() {
//		return "GlobalConfig ["																+ "\n"
//				+ "stopInfoObtainThread=" 				+ stopInfoObtainThread              + "\n"
//				+ "stopDetailObtainThread=" 			+ stopDetailObtainThread            + "\n"
//				+ "stopLogicProcessThread=" 			+ stopLogicProcessThread            + "\n"
//				+ "stopTransThread=" 					+ stopTransThread                   + "\n"
//				+ "stopPushResultThread=" 				+ stopPushResultThread              + "\n"
//				+ "stopTransputClientThread=" 			+ stopTransputClientThread          + "\n"
//				+ "stopTransputHistoryHotsThread=" 		+ stopTransputHistoryHotsThread     + "\n"
//				+ "stopCheckStatesThread=" 				+ stopCheckStatesThread             + "\n"
//				+ "configFilePath=" 					+ configFilePath                    + "\n"
//				+ "RDBTableNameOfWl=" 					+ RDBTableNameOfWl                  + "\n"
//				+ "maxTransmitThreadCount=" 			+ maxTransmitThreadCount            + "\n"
//				+ "maxBlockQueue=" 						+ maxBlockQueue                     + "\n"
//				+ "maxBlockingQueueCapacity=" 			+ maxBlockingQueueCapacity          + "\n"
//				+ "maxLogicProcThreadCount=" 			+ maxLogicProcThreadCount           + "\n"
//				+ "consumerNameSrv=" 					+ consumerNameSrv                   + "\n"
//				+ "consumerInstanceName=" 				+ consumerInstanceName              + "\n"
//				+ "MQTopicName=" 						+ MQTopicName                       + "\n"
//				+ "redisConfigStr=" 					+ redisConfigStr                    + "\n"
//				+ "mmconf=" 							+ mmconf                            + "\n"
//				+ "persistInterval=" 					+ persistInterval                   + "\n"
//				+ "whiteWebSiteSet=" 					+ whiteWebSiteSet                   + "\n"
//				+ "blackWebSiteSet=" 					+ blackWebSiteSet                   + "\n"
//				+ "url_bw_list=" 						+ url_bw_list                       + "\n"
//				+ "InfoFilePath=" 						+ InfoFilePath                      + "\n"
//				+ "DetailFilePath=" 					+ DetailFilePath                    + "\n"
//				+ "rdbUrl=" 							+ rdbUrl                            + "\n"
//				+ "rdbDriver=" 							+ rdbDriver                         + "\n"
//				+ "rdbPwd=" 							+ rdbPwd                            + "\n"
//				+ "rdbUser=" 							+ rdbUser                           + "\n"
//				+ "mppDataCenter=" 						+ mppDataCenter                     + "\n"
//				+ "mppKeySpace=" 						+ mppKeySpace                       + "\n"
//				+ "mppConnAddress=" 					+ mppConnAddress                    + "\n"
//				+ "mppPort=" 							+ mppPort                           + "\n"
//				+ "mppUserName="  						+ mppUserName                       + "\n"
//				+ "mppPassword=" 						+ mppPassword                       + "\n"
//				+ "oriMessMQAddress=" 					+ oriMessMQAddress                  + "\n"
//				+ "oriMessMQPort=" 						+ oriMessMQPort                     + "\n"
//				+ "oriMessConsumerGroup=" 				+ oriMessConsumerGroup              + "\n"
//				+ "oriMessTopic=" 						+ oriMessTopic                      + "\n"
//				+ "URLconsumerNameSrv=" 				+ URLconsumerNameSrv                + "\n"
//				+ "putURLTopic=" 						+ putURLTopic                       + "\n"
//				+ "getURLTopic=" 						+ getURLTopic                       + "\n"
//				+ "URLconsumerInstanceName=" 			+ URLconsumerInstanceName           + "\n"
//				+ "isRocketmq=" 						+ isRocketmq                        + "\n"
//				+ "BFexpectedInsertions=" 				+ BFexpectedInsertions              + "\n"
//				+ "stopUrlData=" 						+ stopUrlData                       + "\n"
//				+ "]";
//	}
	
	
}
