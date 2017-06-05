package cn.ac.iie.check;

import cn.ac.iie.Method.CrawlProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckStates implements Runnable {
	private static Logger logger;

	public CheckStates() {
		logger = LoggerFactory.getLogger(CheckStates.class);
	}

	public void run() {
		//long lastTs = System.currentTimeMillis();
		//Jedis jedis = GlobalConfig.getRpL1().getResource();
		while (true) {
			try {
				logger.info("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"
						   +"+	total_nr				process		total			url	nr	: " + CrawlProcess.total_nr + "\n"
						   +"+	httpStatus_nr				process		total			url	nr	: " + CrawlProcess.httpStatusException_nr + "\n"
						   +"+	timeout_nr				process		total			url	nr	: " + CrawlProcess.timeoutException_nr + "\n"
						   +"+	unknownHost_nr				process		total			url	nr	: " + CrawlProcess.unknownHostException_nr + "\n"
						   +"+	iOException_nr				process		total			url	nr	: " + CrawlProcess.iOException_nr + "\n"
						   +"+	connect_nr				process		total			url	nr	: " + CrawlProcess.connectException_nr + "\n"
						   +"+	exception_nr				process		total			url	nr	: " + CrawlProcess.exception_nr + "\n"
						   +"+	crawled_nr				process		total			url	nr	: " + CrawlProcess.crawled_nr + "\n"
						   +"+	title_null_nr				process		total			url	nr	: " + CrawlProcess.title_null_nr + "\n"
						   +"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				Thread.sleep(30000L);
			} catch (InterruptedException exception) {
				logger.error(exception.getMessage(), exception);
			}
		}
	}
}
