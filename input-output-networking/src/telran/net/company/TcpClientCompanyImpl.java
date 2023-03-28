package telran.net.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import telran.employees.Company;
import telran.employees.Employee;
import telran.net.NetworkClient;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class TcpClientCompanyImpl implements Company, NetworkClient {
	private static final long serialVersionUID = 1L;
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	public TcpClientCompanyImpl(Socket socket) throws Exception {
			this.socket = socket;
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
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
				socket.close();

			}
			
	@SuppressWarnings("unchecked")
	@Override
	public <T> T send(String type, Serializable requestData) {
			Request request = new Request(type, requestData);
			T res = null;
			try {
				output.writeObject(request);
				Response response = (Response) input.readObject();
				if(response.code != ResponseCode.OK) {
					throw new Exception(response.data.toString());
				}
				res = (T) response.data;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			return res;
		}
}
