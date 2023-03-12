package telran.util;

import java.lang.System.Logger.Level;
import java.time.*;

public class LoggerRecord {
	
	Level level = null;
	String loggerName = "";
	String message = "";

	public LoggerRecord (Level level, String loggerName, String message) {
		Instant timestamp = Instant.now(); 
		String zoneid = ZoneId.systemDefault().toString();
		this.level = level;
		this.loggerName = loggerName;
		this.message = message;
	}
	
}
