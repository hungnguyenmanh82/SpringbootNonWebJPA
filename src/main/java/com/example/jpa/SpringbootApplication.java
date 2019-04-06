package com.example.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa.dao.crud.insert.NativeInsertDAO;
import com.example.jpa.dao.crud.insert.PersistInsertDAO;
import com.example.jpa.entity.Department;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {
	

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("************* Start test ************************");
//		testInsert1();
		testInsert2();
//		testInsert3();

		
	}
	
	@Autowired //refer to singleton PersistInsertDAO
	PersistInsertDAO  persistInsertDAO;
	public void testInsert1(){
		Department dep = new Department(986,"information","Hanoi");
		persistInsertDAO.insertDepartment(dep);
		
		System.out.println("OK: *************** PersistInsertDAO");
	}
	
	@Autowired
	NativeInsertDAO nativeInsertDAO;
	// chạy 2 lần để test exception => vi duplicate Id
	public void testInsert2(){
		Department dep = new Department(999,"NativeSQL","SonTay");
		nativeInsertDAO.insertDepartment1(dep);
		
		System.out.println("OK: *************** NativeInsertDAO");
	}


	public void testInsert3(){
		Department dep = new Department(789,"@Transactional","Bavi");
		nativeInsertDAO.insertDepartment2(dep);
		
		System.out.println("OK: 333 *************** NativeInsertDAO");
	}
	
	
	
}
