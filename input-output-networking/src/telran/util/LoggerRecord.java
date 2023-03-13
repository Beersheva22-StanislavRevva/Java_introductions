package telran.util;

import java.lang.System.Logger.Level;
import java.time.*;

public class LoggerRecord {
	
	Level level;
	String loggerName;
	String message;
	Instant timestamp;
	String zoneid;

	public LoggerRecord (Level level, String loggerName, String message) {
		this.timestamp = Instant.now(); 
		this.zoneid = ZoneId.systemDefault().toString();
		this.level = level;
		this.loggerName = loggerName;
		this.message = message;
	}
	
}
