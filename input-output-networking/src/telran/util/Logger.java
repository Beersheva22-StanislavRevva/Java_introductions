package telran.util;

import java.lang.System.Logger.*;

public class Logger implements Handler {
	
	
	Level level;
	Handler handler;
	String name;
	String message;
	
	public Logger (Handler handler, String name)	{
	this.handler = handler;
	this.name = name;
	}
	
	public void setLevel (Level level) {
		this.level = level;
	}
	
	public void error (String message) {
		if (this.level.compareTo(Level.ERROR) <= 0) {
			LoggerRecord loggerRecord = new LoggerRecord (level, name, message);
			publish(loggerRecord);
		}
	}
	
	public void warn (String message) {
		if (this.level.compareTo(Level.WARNING) <= 0) {
		LoggerRecord loggerRecord = new LoggerRecord (level, name, message);
		publish(loggerRecord);
		}
	}
	
	public void info (String message) {
		if (this.level.compareTo(Level.INFO) <= 0) {
		LoggerRecord loggerRecord = new LoggerRecord (level, name, message);
		publish(loggerRecord);
		}
	}
	
	public void debug (String message) {
		if (this.level.compareTo(Level.DEBUG) <= 0) {
		LoggerRecord loggerRecord = new LoggerRecord (level, name, message);
		publish(loggerRecord);
		}
	}
	
	public void trace (String message) {
		if (this.level.compareTo(Level.TRACE) <= 0) {
		LoggerRecord loggerRecord = new LoggerRecord (level, name, message);
		publish(loggerRecord);
		}
	}
	
	@Override
	public void publish(LoggerRecord loggerRecord) {
		handler.publish(loggerRecord);
		
	}
	
	

}
