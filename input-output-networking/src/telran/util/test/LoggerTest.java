package telran.util.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.System.Logger.Level;
import org.junit.jupiter.api.Test;

import telran.util.Logger;
import telran.util.SimpleStreamHandler;

class LoggerTest {

	
	
	@Test
	void loggerFileTest() {
		try {
			File logFile = new File("C:\\Users\\revva\\git\\input-output-networking\\input-output-networking\\src\\telran\\util\\test\\log.txt");
			PrintStream stream = new PrintStream (logFile);
			boolean isConsole = false;
			SimpleStreamHandler handler = new SimpleStreamHandler(stream, isConsole);
			Logger logger = new Logger (handler, "LOGGER");
			logger.setLevel(Level.ERROR);
			logger.error("Error message");
			logger.trace("Trace message");
			logger.setLevel(Level.TRACE);
			logger.error("Error message");
			logger.trace("Trace message");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	void loggerConsoleTest() {
		PrintStream stream = null;
		boolean isConsole = true;
		SimpleStreamHandler handler = new SimpleStreamHandler(stream, isConsole);
		Logger logger = new Logger (handler, "LOGGER");
		logger.setLevel(Level.INFO);
		logger.error("Error message");
		logger.debug("Debug message");
		logger.setLevel(Level.TRACE);
		logger.error("Error message");
		logger.debug("Debug message");
	}

}
