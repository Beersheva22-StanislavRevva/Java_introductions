package telran.employees.net.application;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;
import telran.employees.*;
import telran.employees.cotroller.CompanyControllerItems;
import telran.employees.net.*;
import telran.net.*;
import telran.view.*;

public class CompanyClientAppl {
	private static final String PACKAGE = "telran.net.";
	
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new RuntimeException("Must be at least one argument");
		} else {
			File file = new File(args[0]);
			if (file.exists() == false) {
				throw new RuntimeException("File's not found"); 
			} else {
				Properties prop = new Properties();
				prop.load(new FileReader(file));
				
			NetworkClient client = getClient(prop.getProperty("hostname"), prop.getProperty("port"), prop.getProperty("transport"));
			Company company = new CompanyNetProxy(client);
			CompanyControllerItems companyController = new CompanyControllerItems(company, getDepName(prop.getProperty("departments")));
			menu(companyController, client).perform(new StandardInputOutput());
			}
			
		}
	}

	private static Set<String> getDepName(String prop) {
		return new HashSet<>(Arrays.asList(prop.split(",")));
	}

	private static NetworkClient getClient(String host, String port, String transport) throws Exception {
		Class<NetworkClient> clazz = (Class<NetworkClient>) Class.forName(PACKAGE + transport + "Client");
		Constructor<NetworkClient> constructor = clazz.getConstructor(String.class, int.class);
		return constructor.newInstance(host, Integer.parseInt(port));
	}
	
	private static Menu menu(CompanyControllerItems companyController, NetworkClient client) {
		return new Menu("Company", companyController.getAdminMenu(), companyController.getUserMenu(),
				Item.of("Exit", io -> {
					try {
						client.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					io.writeLine("Connection closed");
				}, true));
	}
	
	
	
	

}
