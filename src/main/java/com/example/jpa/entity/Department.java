package com.example.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "DEPARTMENT",
uniqueConstraints = { @UniqueConstraint(columnNames = { "DEPT_NO" }) })
public class Department {

	private Integer deptId;
	private String deptNo;

	private String deptName;
	private String location;
	/**
	 * Set là kiểu tập hợp mà item là duy nhất (Hibernate dùng kiểu Set)
	 * List là kiểu tập hợp mà item có thể lập lại (JPA dùng kiểu List)
	 */
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Department() {
	}

	public Department(Integer deptId, String deptName, String location) {
		this.deptId = deptId;
		this.deptNo = "D" + this.deptId;
		this.deptName = deptName;
		this.location = location;
	}

	@Id 		//id này ko đc tự động gen ở SQL server
	@Column(name = "DEPT_ID")
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Column(name = "DEPT_NO", length = 20, nullable = false)
	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "DEPT_NAME", nullable = false)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "LOCATION")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	// chỗ này tạo SQL query SELECT riêng khi gọi hàm này => 
	// FetchType.LAZY chỉ gọi SQL khi truy vấn hàm này
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department") //id của department là field trên table Employee
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}
