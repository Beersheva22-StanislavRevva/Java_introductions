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
	this.message = handler.toString();
	}
	
	public void setLevel (Level level) {
		this.level = level;
	}
	
	public void error (String message) {
		setLevel(Level.ERROR);
		LoggerRecord loggerRecord = new LoggerRecord (level, name, message);
		publish(loggerRecord);
	}
	
	public void warn (String message) {
		setLevel(Level.WARNING);
		LoggerRecord loggerRecord = new LoggerRecord (level, name, message);
		publish(loggerRecord);	
	}
	
	public void info (String message) {
		setLevel(Level.INFO);
		LoggerRecord loggerRecord = new LoggerRecord (level, name, message);
		publish(loggerRecord);	
	}
	
	public void debug (String message) {
		setLevel(Level.DEBUG);
		LoggerRecord loggerRecord = new LoggerRecord (level, name, message);
		publish(loggerRecord);	
	}
	
	public void trace (String message) {
		setLevel(Level.TRACE);
		LoggerRecord loggerRecord = new LoggerRecord (level, name, message);
		publish(loggerRecord);	
	}
	
	@Override
	public void publish(LoggerRecord loggerRecord) {
		// TODO Auto-generated method stub
		
	}
	
	

}
