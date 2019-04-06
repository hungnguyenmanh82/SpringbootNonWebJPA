package com.example.jpa.dao.crud.select;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.entity.Department;
import com.example.jpa.entity.Employee;



/**
 * Lenh giong het Hibernate => tham khao code cua Hibernate (ten function va tham so giong nhau)
 *
 */
@Repository           //singleton
public class FindDAO {
	@Autowired
	private EntityManager entityManager;  //vì @repository là singleton nên => entityManager là singleton

	// bất kỳ lệnh SQL nào cũng phải ở trong 1 transaction (nếu ko sẽ có lỗi exception) (đã test)
	@Transactional
	public Department findDepartmentById(int id){
		Department dep = entityManager.find(Department.class, id);
		return dep;
	}

	@Transactional
	public List<Employee> findDepartmentById2(int id){
		String sql = "Select e from " + Employee.class.getName() + " e "
                + " where e.department.deptNo= :deptNo "; // ":deptNo" tương tự "?" trên SQL
		
		Query query = entityManager.createQuery(sql);
		
		List<Employee> deps = query.getResultList();
		return deps;
	}
	
	@Transactional
	public List<Employee> findAllDepartment(String deptNo){
		// Tạo một câu lệnh HQL query object.
		// HQL Có tham số.
		// Tương đương với Native SQL:
		// Select e.* from EMPLOYEE e cross join DEPARTMENT d
		// where e.DEPT_ID = d.DEPT_ID and d.DEPT_NO = :deptNo;

		// truy vấn theo java Entity class, ko phải theo SQL Table
		// 1 java Entity class chứa dữ liệu của nhiếu SQL table
		//e: là object của Employee Class
		//e.empName: là tên member of Employee java Class
		//e.department: la Department java class
		//e.department.deptNo:  la field cua la Department java class
		// Employee java class ko nhất thiết phải chứa hết các column của Employee SQL table
		String sql = "Select e from " + Employee.class.getName() + " e "
				                          + " where e.department.deptNo= :deptNo "; // ":deptNo" tương tự "?" trên SQL

		//
		Query query = entityManager.createQuery(sql);
		query.setParameter("deptNo", deptNo);
		

		return query.getResultList();
	}
}
