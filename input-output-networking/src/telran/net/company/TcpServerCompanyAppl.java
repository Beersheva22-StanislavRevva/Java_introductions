package telran.net.company;

import java.net.ServerSocket;
import java.net.Socket;

import telran.employees.net.CompanyProtocol;
import telran.net.*;
import telran.net.application.*;

public class TcpServerCompanyAppl {
	public static void main(String[] args) throws Exception {
		TcpServerCompanyImpl server = new TcpServerCompanyImpl(new ServerSocket (4000),new CompanyProtocol());
		server.run();
	}
}
