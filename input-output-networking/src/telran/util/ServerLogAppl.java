package telran.util;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerLogAppl {
	
	private static final int PORT = 4000;
	static List <String> logs = new ArrayList<String>();

	public static void main(String[] args) throws Exception{
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("server listening on port " + PORT );
		while(true) {
			Socket socket = serverSocket.accept();
			try {
				runServerClient(socket);
			} catch (IOException e) {
				System.out.println("abnormal closing connection");
			}
		}

	}

	private static void runServerClient(Socket socket) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream output = new PrintStream(socket.getOutputStream());
		while(true) {
			String request = input.readLine();
			if (request == null) {
				break;
			}
			String response = getResponse(request);
			output.println(response);
		}
		System.out.println("client closed connection");
		
	}

	private static String getResponse(String request) {
		String res = "Wrong Request";
		String tokens[] = request.split("#");
		if (tokens.length == 2) {
			res = switch(tokens[0]) {
			case "log" -> res = addLog(tokens[1]);
			case "counter" -> res = Integer.toString(getCounter(tokens));
			default -> "Wrong type " + tokens[0];
			};
	}
		return res;

		
	}

	private static String addLog(String string) {
		logs.add(string);
		return "Log added";
	}

	private static int getCounter(String[] tokens) {
		int res = 0;
		switch(tokens[1]) {
			case "error" -> {
				for (String s : logs) {
					if (s.contains("error")) {
						res+=1;
					}	
				}
			}
			case "warn" -> {
				for (String s : logs) {
					if (s.contains("warn")) {
						res+=1;
					}	
				}
			}
			case "info" -> {
				for (String s : logs) {
					if (s.contains("info")) {
						res+=1;
					}	
				}
			}
			case "debug" -> {
				for (String s : logs) {
					if (s.contains("debug")) {
						res+=1;
					}	
				}
			}
			case "trace" -> {
				for (String s : logs) {
					if (s.contains("trace")) {
						res+=1;
					}	
				}
			}
			default -> res = 0;
			};
		
		return res;
	}

	
}
