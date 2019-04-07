package com.example.jpa.dao.crud.delete;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.entity.Department;

@Repository           //singleton
public class NativeDeleteDAO {
    @Autowired
    private EntityManager entityManager;  //vì @repository là singleton nên => entityManager là singleton
    
    // bất kỳ lệnh SQL nào cũng phải ở trong 1 transaction (nếu ko sẽ có lỗi exception)
    @Transactional
    public void DeleteDepartmentById(int depId){
    	Query q = entityManager.createNativeQuery("DELETE FROME department WHERE dept_id=?")
    						  .setParameter(1, depId);
    	
    	// @return the number of entities updated or deleted
    	int result = q.executeUpdate();
    }
    
    // bất kỳ lệnh SQL nào cũng phải ở trong 1 transaction (nếu ko sẽ có lỗi exception)
    @Transactional
    public void insertDepartmentByName(String depName){
    	//Lưu ý "?" thứ tự rất quan trọng
    	Query q = entityManager.createNativeQuery("DELETE FROME department WHERE dept_no=?")
    							.setParameter(1, depName);

    	
    	// @return the number of entities updated or deleted
    	int result = q.executeUpdate();

    }
}
