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
public class SpringbootApp  {

	/**
	 * config the same web-application
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApp.class, args);
		
		//see Non-Webapp.java implement CommandLineRunner.class
	}


}
