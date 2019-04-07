package com.example.jpa.dao.crud.update;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * SQL native sample nen tham khao: 
 * https://github.com/hungnguyenmanh82/Javacore_TestJDBC
 *
 */
@Repository           //singleton
public class NativeUpdateDAO {
    @Autowired
    private EntityManager entityManager;  //vì @repository là singleton nên => entityManager là singleton
    
    //cách 1:
    // bất kỳ lệnh SQL nào cũng phải ở trong 1 transaction (nếu ko sẽ có lỗi exception)
    @Transactional
    public void updateDepartmentName(int depId, String DepName){
    	
    	//Update: dung SQL native sẽ tối ưu hơn là dùng JSQL
    	Query q = entityManager.createNativeQuery("UPDATE department SET dept_name=? WHERE dept_id=?")
    							.setParameter(1, DepName)  //1 = ? vi tri 1 
    							.setParameter(2, depId);   //2 = ? vi tri 2
    	
    	
    	// @return the number of entities updated or deleted
    	int result = q.executeUpdate();
    }
   

}
