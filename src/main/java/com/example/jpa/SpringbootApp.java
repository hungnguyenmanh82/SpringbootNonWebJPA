package com.example.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa.dao.crud.insert.NativeInsertDAO;
import com.example.jpa.dao.crud.insert.PersistInsertDAO;
import com.example.jpa.dao.crud.select.NativeSelectDAO;
import com.example.jpa.dao.crud.select.SelectDAO;
import com.example.jpa.entity.Department;
import com.example.jpa.entity.Employee;

@SpringBootApplication
public class SpringbootApp implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringbootApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//		testInsert1();
		//		testInsert2();
		//		testInsert3();

		//		testSelect1();
		//		testSelect2();
		//		testSelect3();

		//		testSelect4();
		//		testSelect5();
		//		testSelect6();
		testSelect7();

	}

	//================================================================================
	//
	//===============================================================================
	@Autowired //refer to singleton PersistInsertDAO
	PersistInsertDAO  persistInsertDAO;
	public void testInsert1(){
		System.out.println("************* Start testInsert1 ************************");
		Department dep = new Department(986,"information","Hanoi");
		persistInsertDAO.insertDepartment(dep);

		System.out.println("OK: *************** PersistInsertDAO");
	}


	//================================================================================
	//
	//===============================================================================
	@Autowired
	NativeInsertDAO nativeInsertDAO;
	// chạy 2 lần để test exception => vi duplicate Id
	public void testInsert2(){
		System.out.println("************* Start testInsert2 ************************");
		Department dep = new Department(999,"NativeSQL","SonTay");
		nativeInsertDAO.insertDepartment1(dep);

		System.out.println("OK: *************** NativeInsertDAO");
	}


	public void testInsert3(){
		System.out.println("************* Start testInsert3 ************************");
		Department dep = new Department(789,"@Transactional","Bavi");
		nativeInsertDAO.insertDepartment2(dep);

		System.out.println("OK: 333 *************** NativeInsertDAO");
	}

	//================================================================================
	//
	//===============================================================================
	@Autowired //refer to singleton PersistInsertDAO
	NativeSelectDAO nativeSelectDAO;  
	public void testSelect1(){
		System.out.println("************* Start testSelect1 ************************");
		Department dep = nativeSelectDAO.findDepartmentById1(20);

		System.out.println("OK: *******************************");
		System.out.println("  depId=" + dep.getDeptId());
		System.out.println("  depName=" + dep.getDeptName());
	}

	public void testSelect2(){
		System.out.println("************* Start testSelect2 ************************");
		Department dep = nativeSelectDAO.findDepartmentById2(20);

		System.out.println("OK: *******************************");
		System.out.println("   depId=" + dep.getDeptId());
		System.out.println("   depName=" + dep.getDeptName());
	}

	public void testSelect3(){
		System.out.println("************* Start testSelect3 ************************");
		List<Department> deps = nativeSelectDAO.findAllDepartment();

		for(Department dep: deps){
			System.out.println("OK: *******************************");
			System.out.println("     depId=" + dep.getDeptId());
			System.out.println("     depName=" + dep.getDeptName());
		}
	}

	//================================================================================
	//
	//===============================================================================
	@Autowired //refer to singleton PersistInsertDAO
	SelectDAO selectDAO;
	public void testSelect4(){
		System.out.println("************* Start testSelect4 ************************");
		List<Employee> emps = selectDAO.findAllEmployee();

		for(Employee emp: emps){
			System.out.println("OK: *******************************");
			System.out.println("     empId=" + emp.getEmpId());
			System.out.println("     empName=" + emp.getEmpName());
		}
	}

	public void testSelect5(){
		System.out.println("************* Start testSelect5 ************************");
		Department dep = selectDAO.findDepartmentById(20);

		System.out.println("OK: *******************************");
		System.out.println("   depId=" + dep.getDeptId());
		System.out.println("   depName=" + dep.getDeptName());
	}

	public void testSelect6(){
		System.out.println("************* Start testSelect6 ************************");
		List<Employee> emps = selectDAO.findEmployeeByDepNo("D10");

		for(Employee emp: emps){
			System.out.println("OK: *******************************");
			System.out.println("     empId=" + emp.getEmpId());
			System.out.println("     empName=" + emp.getEmpName());
		}
	}

	public void testSelect7(){
		System.out.println("************* Start testSelect7 ************************");
		Employee emp = selectDAO.findEmployeeById(7369);

		System.out.println("OK: *******************************");
		System.out.println("     empId=" + emp.getEmpId());
		System.out.println("     empName=" + emp.getEmpName());
		System.out.println("     DepNo=" + emp.getDepartment().getDeptNo());
	}
}
