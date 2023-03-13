package telran.util;

import java.io.PrintStream;

public class SimpleStreamHandler implements Handler {
	
	PrintStream stream;
	
	public SimpleStreamHandler (PrintStream stream, boolean isConsole) {
		super();
		if (!isConsole) {
			this.stream = stream;
		} else this.stream = System.out;
	}

	@Override
	public void publish(LoggerRecord loggerRecord) {
		stream.printf("%s	%s	%s 	%s 	%s \n",loggerRecord.timestamp, loggerRecord.zoneid, loggerRecord.level, loggerRecord.loggerName, loggerRecord.message);
				
	}
}
