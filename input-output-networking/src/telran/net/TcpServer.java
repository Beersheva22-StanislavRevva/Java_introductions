package telran.net;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServer implements Runnable {
	private Protocol protocol;
	private int port;
	ExecutorService executor;
	private ServerSocket serverSocket;
	public static final int TIMEOUT = 10000;
	public static boolean shutdownflag = false;

	public TcpServer(Protocol protocol, int port) throws Exception {
		this.protocol = protocol;
		this.port = port;
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(TIMEOUT);
		int nThrads = Runtime.getRuntime().availableProcessors();
		System.out.printf("Number of threads %d \n", nThrads);
		executor = Executors.newFixedThreadPool(nThrads);
	}

	@Override
	public void run() {
		System.out.println("Server listening on port " + this.port);
		while (true && !shutdownflag) {
			try {
				Socket socket = serverSocket.accept();
				serverSocket.setSoTimeout(TIMEOUT);
				TcpServerClient serverClient = new TcpServerClient(socket, protocol);
				executor.execute(serverClient);
			} catch (SocketTimeoutException se) {
				if (shutdownflag) {
					System.out.println("Serever is shuttimg down");
					break;
				}
			} catch (Exception e) {
				System.out.println(e.toString());
				break;
			}
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void shutdown() {
		shutdownflag = true;
		executor.shutdownNow();
	}
}