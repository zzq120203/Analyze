package run;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ac.iie.Entity.GlobalConfig;
import cn.ac.iie.Method.CrawlProcess;
import cn.ac.iie.Method.LoadConfig;
import cn.ac.iie.Method.PreAllocate;
import cn.ac.iie.check.CheckStates;


public class Run {
	private static Logger logger;
	private static GlobalConfig gConfig = new GlobalConfig();
	
	
	public static void main(String[] args){
		
		logger = LoggerFactory.getLogger(Run.class);
		
		new LoadConfig("./Configure.xml", gConfig);
		logger.info(gConfig.toString());
		
		PreAllocate obtainUrlInfo = new PreAllocate(gConfig);
		Thread obtainInfoThread = new Thread(obtainUrlInfo);
		obtainInfoThread.start();

		
		CrawlProcess process = new CrawlProcess(gConfig, obtainUrlInfo.getOutputDXOBlockingQList());
		process.initalize();
		
		CheckStates checkStates = new CheckStates();
		Thread checkThread = new Thread(checkStates);
		checkThread.start();
	}
	
}
