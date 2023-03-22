package telran.net;

import java.io.*;
import java.net.*;


public class TcpClient implements NetworkClient {
private Socket socket;
private ObjectOutputStream output;
private ObjectInputStream input;
public TcpClient(String hostname, int port) throws Exception {
		socket = new Socket(hostname, port);
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	}
	@Override
	public void close() throws IOException {
		socket.close();
	
	}
	@Override
	public <T> T send(String type, Serializable requestData) {
		Request request = new Request(type, requestData);
		T res = null;
		Response response = null;
		try {
			output.writeObject(request);
			
			try {
				response = (Response) input.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (response.code.toString() == "OK") {
				res = (T) response.data;	
				}
				else { System.out.println("response.code");
					}
			
		} catch (IOException e) {
			System.out.println("server closed connection");
		}
		return res;
	}
	
}
