package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;

import telran.employees.*;


class CompanyImplTest {

	Employee employee1 = new Employee (121, "John", LocalDate.of(1981, 1, 1), "department1", 11000);
	Employee employee2 = new Employee (122, "Jack", LocalDate.of(1982, 2, 2), "department2", 12000);
	Employee employee3 = new Employee (123, "Jim", LocalDate.of(1983, 3, 3), "department3", 13000);
	Employee employee4 = new Employee (124, "Jake", LocalDate.of(1984, 4, 4), "department4", 14000);
	
	
	CompanyImpl createCompanyImpl () {
	CompanyImpl companyImpl = new CompanyImpl();
	companyImpl.addEmployee(employee1);
	companyImpl.addEmployee(employee2);
	companyImpl.addEmployee(employee3);
	companyImpl.addEmployee(employee4);
	return companyImpl;
	}
	
	
	@Test
	void getAllEmployeesTest() {
		CompanyImpl companyImpl = createCompanyImpl();
		List<Employee> list = companyImpl.getAllEmployees();
		assertEquals(employee1, list.get(0));
		assertEquals(employee4, list.get(3));
	}
	
	@Test
	void removeEmployeeTest() {
		CompanyImpl companyImpl = createCompanyImpl();
		companyImpl.removeEmployee(121);
		companyImpl.removeEmployee(122);
		companyImpl.removeEmployee(123);
		companyImpl.removeEmployee(124);
		assertEquals(0, companyImpl.getAllEmployees().size());
	}

	@Test
	void getByMonthDepartmentSalaryTest() {
		CompanyImpl companyImpl = createCompanyImpl();
		assertEquals(employee4, companyImpl.getEmployee(124));
		assertEquals(employee1, companyImpl.getEmployeesByMonthBirth(1).get(0));
		assertEquals(employee2, companyImpl.getEmployeesByDepartment("department2").get(0));
		assertTrue(companyImpl.getEmployeesBySalary(11500, 13500).contains(employee2));
		assertFalse(companyImpl.getEmployeesBySalary(11500, 13500).contains(employee4));
				
	}
	
	@Test
	void saveTest() {
		CompanyImpl companyImpl = createCompanyImpl();
		companyImpl.save("companyImpl.txt");
		companyImpl.removeEmployee(121);
		companyImpl.removeEmployee(122);
		companyImpl.removeEmployee(123);
		companyImpl.removeEmployee(124);
		companyImpl.restore("companyImpl.txt");
		assertEquals("John",companyImpl.getEmployee(121).getName());
		assertEquals(14000 ,companyImpl.getEmployee(124).getSalary());
	}
	
}

