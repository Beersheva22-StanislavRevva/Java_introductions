package telran.employees;

import java.util.*;
import java.util.concurrent.locks.*;
import java.io.*;

public class CompanyImpl implements Company {

	
	private static final long serialVersionUID = 1L;
	private HashMap<Long, Employee> employees = new HashMap<>();
	private HashMap<Integer, Set<Employee>> employeesMonth = new HashMap<>();
	private HashMap<String, Set<Employee>> employeesDepartment = new HashMap<>();
	private TreeMap<Integer, Set<Employee>> employeesSalary = new TreeMap<>();
	
	private final ReadWriteLock employeesLock = new ReentrantReadWriteLock();
	Lock writeEmployeesLock = employeesLock.writeLock();
	Lock readEmployeesLock = employeesLock.readLock();

	private final ReadWriteLock employeesMonthLock = new ReentrantReadWriteLock();
	Lock writeEmployeesMonthLock = employeesMonthLock.writeLock();
	Lock readEmployeesMonthLock = employeesMonthLock.readLock();

	private final ReadWriteLock employeesDepartmentLock = new ReentrantReadWriteLock();
	Lock writeEmployeesDepartmentLock = employeesDepartmentLock.writeLock();
	Lock readEmployeesDepartmentLock = employeesDepartmentLock.readLock();

	private final ReadWriteLock employeesSalaryLock = new ReentrantReadWriteLock();
	Lock writeEmployeesSalaryLock = employeesSalaryLock.writeLock();
	Lock readEmployeesSalaryLock = employeesSalaryLock.readLock();
	
	@Override
	public Iterator<Employee> iterator() {
		
		return getAllEmployees().iterator();
	}

	@Override
	public boolean addEmployee(Employee empl) {
		lock(writeEmployeesLock, writeEmployeesMonthLock, writeEmployeesDepartmentLock, writeEmployeesSalaryLock);
		try {
			boolean res = false;
			if (employees.putIfAbsent(empl.id, empl) == null) {
				res = true;
				addIndexMap(employeesMonth, empl.getBirthDate().getMonthValue(), empl);
				addIndexMap(employeesDepartment, empl.getDepartment(), empl);
				addIndexMap(employeesSalary, empl.getSalary(), empl);
			}

			return res;
		} finally {
			unlock(writeEmployeesLock, writeEmployeesMonthLock, writeEmployeesDepartmentLock, writeEmployeesSalaryLock);
		}

	}

	private <T> void addIndexMap(Map<T, Set<Employee>> map, T key, Employee empl) {
		map.computeIfAbsent(key, k->new HashSet<>()).add(empl);
		
	}

	@Override
	public Employee removeEmployee(long id) {
		try {
			lock(writeEmployeesLock, writeEmployeesMonthLock, writeEmployeesDepartmentLock, writeEmployeesSalaryLock);
			Employee empl = employees.remove(id);
			if (empl != null) {
				removeIndexMap(employeesMonth, empl.getBirthDate().getMonthValue(), empl);
				removeIndexMap(employeesDepartment, empl.getDepartment(), empl);
				removeIndexMap(employeesSalary, empl.getSalary(), empl);
			}
			return empl;
		} finally {
			unlock(writeEmployeesLock, writeEmployeesMonthLock, writeEmployeesDepartmentLock, writeEmployeesSalaryLock);
		}
	}

	private <T>void removeIndexMap(Map<T, Set<Employee>> map, T key, Employee empl) {
		Set<Employee> set = map.get(key);
		set.remove(empl);
		if (set.isEmpty()) {
			map.remove(key);
		}
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		try {
			lock(readEmployeesLock);
			return new ArrayList<>(employees.values());
		} finally {
			unlock(readEmployeesLock);
		}
	}

	@Override
	public List<Employee> getEmployeesByMonthBirth(int month) {
		try {
			lock(readEmployeesMonthLock);
			return new ArrayList<>(employeesMonth.getOrDefault(month, Collections.emptySet()));
		} finally {
			unlock(readEmployeesMonthLock);
		}
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		try {
			lock(readEmployeesSalaryLock);
			return employeesSalary.subMap(salaryFrom, true, salaryTo, true).values().stream().flatMap(Set::stream)
					.toList();
		} finally {
			unlock(readEmployeesMonthLock);
		}
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		try {
			lock(readEmployeesDepartmentLock);
			return new ArrayList<>(employeesDepartment.getOrDefault(department, Collections.emptySet()));
		} finally {
			unlock(readEmployeesDepartmentLock);
		}
	}

	@Override
	public Employee getEmployee(long id) {
		try {
			lock(readEmployeesLock);
			return employees.get(id);
		} finally {
			unlock(readEmployeesLock);
		}
	}

	@Override
	public void save(String pathName) {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(pathName))){
			output.writeObject(getAllEmployees());
		} catch(Exception e) {
			throw new RuntimeException(e.toString()); //some error
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void restore(String pathName) {
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(pathName))) {
			List<Employee> allEmployees = (List<Employee>) input.readObject();
			allEmployees.forEach(this::addEmployee);
		}catch(FileNotFoundException e) {
			//empty object but no error
		} catch (Exception e) {
			throw new RuntimeException(e.toString()); //some error
		}

	}

	@Override
	public void updateSalary(long empId, int newSalary) {
		try {
			lock(readEmployeesLock, writeEmployeesSalaryLock);
		Employee employee = employees.get(empId);
		employee.setSalary(newSalary);
		removeIndexMap(employeesSalary, employee.getSalary(), employee);
		addIndexMap(employeesSalary, newSalary, employee);
		} finally {
			unlock(readEmployeesLock, writeEmployeesSalaryLock);
		}
		}

	@Override
	public void updateDepartment(long empId, String department) {
		try {
			lock(readEmployeesLock, writeEmployeesDepartmentLock);
		Employee employee = employees.get(empId);
		removeIndexMap(employeesDepartment, employee.getDepartment(), employee);
		addIndexMap(employeesDepartment, employee.getDepartment(), employee);
		} finally {
			unlock(readEmployeesLock, writeEmployeesDepartmentLock);
		}
	}
	
	private void lock(Lock...locks) {
		for(Lock l : locks) {
			l.lock();
		}
		
	}
	private void unlock(Lock...locks) {
		for(Lock l : locks) {
			l.unlock();
		}
	}

}