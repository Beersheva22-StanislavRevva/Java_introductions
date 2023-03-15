package telran.employees;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;


public class CompanyImpl implements Company {
	
	List<Employee> employeeList = new ArrayList<Employee>();
	private static final long serialVersionUID = 1L;
	
	/*public CompanyImpl (List<Employee> employeeList) {
	 this.employeeList = employeeList;
	}*/
		

	@Override
	public Iterator<Employee> iterator() {
	
		return employeeList.listIterator();
	}

	@Override
	public boolean addEmployee(Employee empl) {
		return employeeList.add(empl);
	}

	@Override
	public Employee removeEmployee(long id) {
		Employee empl = getEmployee(id);
		employeeList.remove(empl);
		
		return empl;
	}

	@Override
	public List<Employee> getAllEmployees() {
	 
		return employeeList.stream().toList();
	}

	@Override
	public List<Employee> getEmployeesByMonthBirth(int month) {
		
		return (employeeList.stream().filter(n -> n.getBirthDate().getMonthValue() == month).toList());
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		return (employeeList.stream().filter(n -> n.salary >= salaryFrom).filter(n -> n.salary <= salaryTo).toList());
		
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		return (employeeList.stream().filter(n -> n.getDepartment().equalsIgnoreCase(department)).toList());
	}

	@Override
	public Employee getEmployee(long id) {
		List<Employee> tmp = employeeList.stream().filter(n -> n.getId() == id).toList();
				
		return tmp != null?	tmp.get(0) : null;
		 
	}

	@Override
	public void save(String pathName) {
		try {
			writeObject(pathName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeObject(String pathName) throws Exception {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(pathName))) {
			output.writeObject(this);
		} 
	}
	
	@Override
	public void restore(String pathName) {
		CompanyImpl companyImpl = null;
		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(pathName))) {
			companyImpl = (CompanyImpl) input.readObject();
		} catch (Exception e) {
		}
		for (Employee e : companyImpl) {
			addEmployee(e);
		}
		
	}
	

}