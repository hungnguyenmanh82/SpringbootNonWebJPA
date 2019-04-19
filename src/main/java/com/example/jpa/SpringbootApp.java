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

/*
 * Vẫn config như Springboot web. Nhưng thay đổi ở file  resources/application.properties
 */

@SpringBootApplication
public class SpringbootApp  {

	/**
	 * pom.xml the same web-application
	 * application.properties cần config thêm
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApp.class, args);
		
		//see Non-Webapp.java implement CommandLineRunner.class
	}


}
