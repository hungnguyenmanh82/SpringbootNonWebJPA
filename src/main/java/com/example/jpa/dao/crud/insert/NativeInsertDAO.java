package com.example.jpa.dao.crud.insert;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.entity.Department;

/**
 * SQL native sample nen tham khao: 
 * https://github.com/hungnguyenmanh82/Javacore_TestJDBC
 *
 */
@Repository           //singleton
public class NativeInsertDAO {
    @Autowired
    private EntityManager entityManager;  //vì @repository là singleton nên => entityManager là singleton
    
    //cách 1:
    // bất kỳ lệnh SQL nào cũng phải ở trong 1 transaction (nếu ko sẽ có lỗi exception)
    // chạy 2 lần để test exception => vi duplicate Id
    @Transactional
    public void insertDepartment1(Department dep){
    	Query q = entityManager.createNativeQuery("INSERT INTO department (dept_id, dept_name, dept_no, location) VALUES (:id, :name, :no, :lo)");
    	q.setParameter("id", dep.getDeptId());
    	q.setParameter("name",dep.getDeptName());
    	q.setParameter("no", dep.getDeptNo());
    	q.setParameter("lo", dep.getLocation());
    	
    	// @return the number of entities updated or deleted
    	int result = q.executeUpdate();
    }
    
    //cách 2:
    // bất kỳ lệnh SQL nào cũng phải ở trong 1 transaction (nếu ko sẽ có lỗi exception)
    @Transactional
    public void insertDepartment2(Department dep){
    	//Lưu ý "?" thứ tự rất quan trọng
    	Query q = entityManager.createNativeQuery("INSERT INTO department (dept_id, dept_name, dept_no, location) VALUES (?,?,?,?)")
											    	.setParameter(1, dep.getDeptId())  //thứ tự bắt đầu từ 1
											    	.setParameter(2,dep.getDeptName())
											    	.setParameter(3, dep.getDeptNo())
											    	.setParameter(4, dep.getLocation());
    	
    	// @return the number of entities updated or deleted
    	int result = q.executeUpdate();

    }
}
