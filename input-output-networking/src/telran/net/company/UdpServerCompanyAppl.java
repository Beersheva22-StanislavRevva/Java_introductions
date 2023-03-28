package telran.net.company;

public class UdpServerCompanyAppl {
	public static void main(String[] args) throws Exception {
		UdpServerCompanyImpl server = new UdpServerCompanyImpl(4000, new CompanyProtocol());
		server.run();
	}

}
