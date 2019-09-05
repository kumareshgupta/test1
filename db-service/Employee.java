package com.model.demo;

public class Employee {
	private Integer empId;
	private String empName;
	private String location;
	
	public Employee() {}
	
	public Employee(Integer empId, String empName, String location) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.location = location;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
		
}
