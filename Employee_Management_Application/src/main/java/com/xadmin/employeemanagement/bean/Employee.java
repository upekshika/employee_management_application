package com.xadmin.employeemanagement.bean;

public class Employee {
	
	private int id;
	private String name;
	private String nic;
	private String department_name;
	private String designation;
	private String joined_date;
	
	
	public Employee(String name, String nic, String department_name, String designation_date, String joined_date, String designation) {
		super();
		this.name = name;
		this.nic = nic;
		this.department_name = department_name;
		this.designation = designation;
		this.joined_date = joined_date;
	}
	public Employee(int id, String name, String nic, String department_name, String designation_date,
			String joined_date, String designation) {
		super();
		this.id = id;
		this.name = name;
		this.nic = nic;
		this.department_name = department_name;
		this.designation = designation;
		this.joined_date = joined_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getJoined_date() {
		return joined_date;
	}
	public void setJoined_date(String joined_date) {
		this.joined_date = joined_date;
	}
	
	

}
   

