package model;

public class Employee {
	private String id;
	private String password;
	private String fullname;
	private String email;
	private String departmentId;

	public Employee() {
	}

	public Employee(String id, String password, String fullname, String email, String departmentId) {
		this.id = id;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.departmentId = departmentId;
	}

	// Getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %s - %s - %s", id, fullname, email, password, departmentId);
	}
}