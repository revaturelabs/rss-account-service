package com.revature.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.springframework.web.bind.annotation.RestController;


public class Logging {

	static Logger logger = LogManager.getLogger();
	
	public static void Log4(String level, String message) {
		switch (level) {
		case "debug":
			logger.debug(message);
			break;
		case "warn":
			logger.warn(message);
			break;
		case "error":
			logger.error(message);
			break;
		case "fatal":
			logger.fatal(message);
			break;
		case "info":
			logger.info(message);
			break;
		case "trace":
			logger.trace(message);
			break;
		default:
			System.out.println("Logger Broken");
		}
	}
}