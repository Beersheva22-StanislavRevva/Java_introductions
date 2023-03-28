package telran.net.company;

import java.io.*;
import java.net.*;
import telran.net.*;

public class TcpServerCompanyImpl implements Runnable{
private Socket socket;
private ObjectInputStream input;
private ObjectOutputStream output;
private CompanyProtocol protocol;

public TcpServerCompanyImpl(ServerSocket serverSocket, CompanyProtocol protocol) throws IOException {
	this.protocol = protocol;
	this.socket = serverSocket.accept();
	input = new ObjectInputStream(socket.getInputStream());
	output = new ObjectOutputStream(socket.getOutputStream());
	
}
	@Override
	public void run() {
		boolean running = true;
		System.out.println("TCP server running on port " + socket.getLocalPort());
		while(running) {
			try {
				Request request = (Request) input.readObject();
				Response response = protocol.getResponse(request);
				output.writeObject(response);
			} catch (EOFException e) {
				System.out.println("client closed connection");
				running = false;
			} catch (Exception e)  {
				throw new RuntimeException(e.toString());
				
			}
		}
		
	}

}
