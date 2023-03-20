package telran.util;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TcpClientHandler implements Handler {
String HOSTNAME;
int PORT;
Socket socket;
PrintStream output;
BufferedReader input;

public TcpClientHandler(Socket socket, PrintStream output, BufferedReader input) throws Exception {
	super();
	this.socket = socket;
	this.output = output; // new PrintStream(socket.getOutputStream());
	this.input = input;// new BufferedReader(new InputStreamReader(socket.getInputStream()));
}

	@Override
	public void publish(LoggerRecord loggerRecord)  {
		LocalDateTime ldt = LocalDateTime.ofInstant(loggerRecord.timestamp,
		ZoneId.of(loggerRecord.zoneId));
		output.printf("log#%s %s %s %s", ldt, loggerRecord.level,
				loggerRecord.loggerName, loggerRecord.message);
	}
	
	@Override
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void requestCounter(String level) {
		output.printf("counter#%s\n",level);
		
	}

}
