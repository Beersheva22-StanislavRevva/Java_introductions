package telran.net.company;

import java.io.*;
import java.util.*;

import telran.employees.*;
import telran.net.*;


public class CompanyProtocol implements Protocol {

private final Company company = new CompanyImpl();
	ResponseCode ok = ResponseCode.OK;
	
	@Override
	public Response getResponse(Request request) {
		
		return switch(request.type) {
		case "iterator" -> new Response (ok, (Serializable) company.iterator());
		case "addEmployee" -> new Response (ok, company.addEmployee((Employee) request.data));
		case "removeEmployee" -> new Response (ok, company.removeEmployee((long) request.data));
		case "getAllEmployees" -> new Response (ok, (Serializable) company.getAllEmployees());
		case "getEmployeesByMonthBirth" -> new Response (ok, new ArrayList <> (company.getEmployeesByMonthBirth((int) request.data)));
		case "getEmployeesByDepartment" -> new Response (ok, new ArrayList <> (company.getEmployeesByDepartment((String) request.data)));
		case "getEmployee" -> new Response (ok, (Serializable) company.getEmployee((long) request.data));
		case "getEmployeesBySalary" -> getEmployeesBySalary(request.data);
		case "save" -> save((String) request.data);
		default -> new Response(ResponseCode.WRONG_REQUEST, request.type + " wrong request");
		};
	}

	private Response save(String data) {
		company.save(data);
		return new Response (ok, null);
	}

	private Response getEmployeesBySalary(Serializable data) {
		int [] array = (int[]) data;
		return new Response (ok, new ArrayList <> (company.getEmployeesBySalary(array[0], array[1])));
	}
	

}
