package com.example.jpa.dao.crud.select;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.entity.Department;

@Repository           //singleton
public class NativeSelectDAO {
    @Autowired
    private EntityManager entityManager;  //vì @repository là singleton nên => entityManager là singleton
    
    //cách 1:
    // bất kỳ lệnh SQL nào cũng phải ở trong 1 transaction (nếu ko sẽ có lỗi exception)
    // chạy 2 lần để test exception => vi duplicate Id
    @Transactional
    public Department findDepartmentById1(int depId){
    	//phai liet ke du column cua table tuong ung voi Department.class , ko can dung thu tu van ok
    	Query q = entityManager.createNativeQuery("SELECT dept_id, dept_name, dept_no, location FROM department WHERE dept_id = :id",Department.class);
    	q.setParameter("id", depId); // ":id"

    	Department dep = (Department) q.getSingleResult();
    	return dep;
    }
    
    //cách 2:
    // bất kỳ lệnh SQL nào cũng phải ở trong 1 transaction (nếu ko sẽ có lỗi exception)
    @Transactional
    public Department findDepartmentById2(int depId){
    	//Lưu ý "?" thứ tự rất quan trọng
    	//phai liet ke du column cua table tuong ung voi Department.class , ko can dung thu tu van ok
    	Query q = entityManager.createNativeQuery("SELECT dept_id, dept_name, dept_no, location FROM department WHERE dept_id = ?",Department.class)
											    	.setParameter(1, depId);  //thứ tự bắt đầu từ 1
 	
    	// @return the number of entities updated or deleted
    	Department dep = (Department) q.getSingleResult();
    	return dep;
    }
    
    @Transactional
    public List<Department> findAllDepartment(){
    	//phai liet ke du column cua table tuong ung voi Department.class , ko can dung thu tu van ok
    	Query q = entityManager.createNativeQuery("SELECT dept_id, dept_name, dept_no, location FROM department",Department.class);
    	return q.getResultList();
    }
}
