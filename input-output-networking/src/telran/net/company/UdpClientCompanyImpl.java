package telran.net.company;

import static telran.net.UdpUtills.MAX_BUFFER_LENGTH;
import static telran.net.UdpUtills.toBytesArray;
import static telran.net.UdpUtills.toSerializable;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;

import telran.employees.*;
import telran.net.*;

public class UdpClientCompanyImpl implements Company, NetworkClient {
	
	private static final long serialVersionUID = 1L;
	private String host;
	private DatagramSocket socket;
	private int port;
	
	public UdpClientCompanyImpl(String host, int port) {
		this.host = host;
		this.port = port;
		try {
			socket = new DatagramSocket();
		}catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
		
	}
	
	@Override
	public Iterator<Employee> iterator() {
		return this.send("iterator", null);
	}

	@Override
	public boolean addEmployee(Employee empl) {
		
		return this.send("addEmployee", empl);
	}

	@Override
	public Employee removeEmployee(long id) {
		return this.send("removeEmployee", id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.send("getAllEmployees", null);
	}

	@Override
	public List<Employee> getEmployeesByMonthBirth(int month) {
		return this.send("getEmployeesByMonthBirth", month);
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		return this.send("getEmployeesBySalary", new Integer[] {salaryFrom, salaryTo});
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		return this.send("getEmployeesByDepartment", department);
	}

	@Override
	public Employee getEmployee(long id) {
		return this.send("getEmployee", id);
	}

	@Override
	public void save(String pathName) {
		this.send("save", pathName);

	}

	@Override
	public void restore(String pathName) {
		this.send("restore", pathName);

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T send(String type, Serializable requestData) {
		Request request = new Request(type, requestData);
		try {
			byte [] bufferSend = toBytesArray(request);
			byte[] bufferReceive = new byte[MAX_BUFFER_LENGTH];
			DatagramPacket packetSend = new DatagramPacket(bufferSend, bufferSend.length,
					InetAddress.getByName(host), port);
			DatagramPacket packetReceive = new DatagramPacket(bufferReceive, MAX_BUFFER_LENGTH);
			socket.send(packetSend);
			socket.receive(packetReceive);
			Response response = (Response) toSerializable(packetReceive.getData(), packetReceive.getLength());
			if (response.code != ResponseCode.OK) {
				throw new Exception(response.data.toString());
			}
			return (T) response.data;
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
