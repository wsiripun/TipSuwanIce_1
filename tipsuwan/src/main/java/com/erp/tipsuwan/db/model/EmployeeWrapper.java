package com.erp.tipsuwan.db.model;
import java.util.List;
public class EmployeeWrapper {
	
	private List<Employee> employeeList;

	public EmployeeWrapper() {
	}

	public List<Employee> getList() {
		return employeeList;
	}

	public void setList(List<Employee> list) {
		this.employeeList = list;
	}	
	
	@Override
	public String toString() {
		return "EmployeeWrapper [EmployeeList=" + employeeList + "]";
	}
}