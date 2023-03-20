package telran.util;

import java.io.*;
import java.net.*;

import org.junit.jupiter.api.*;

class TcpClientHandlerTest {
	Handler handler;
	Logger logger;
	BufferedReader reader;
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 4000;

	

	@BeforeEach
	void setUp() throws Exception {
		Socket socket = new Socket(HOSTNAME, PORT); 
		PrintStream output = new PrintStream(socket.getOutputStream());
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		handler = new TcpClientHandler(socket,output,input);
		logger = new Logger(handler, "test-logger");
		
	}
	
	private void logging() {
		logger.error("error message");
		logger.warn("warn message");
		logger.info("info message");
		logger.debug("debug message");
		logger.trace("trace message");
	}
	
	@Test
	void addTest() {
		logger.setLevel(Level.ERROR);
		logging();
		logger.requestCounter("error");
		logger.requestCounter("warn");
		logger.requestCounter("info");
		logger.requestCounter("debug");
		logger.requestCounter("trace");
	}
	
	
}
