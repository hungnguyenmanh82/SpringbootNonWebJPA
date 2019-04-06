package com.example.jpa.dao.crud.insert;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.entity.Department;

@Repository           //singleton
public class PersistInsertDAO {
    @Autowired
    private EntityManager entityManager;  //vì @repository là singleton nên => entityManager là singleton
    
    // bất kỳ lệnh SQL nào cũng phải ở trong 1 transaction (nếu ko sẽ có lỗi exception) (đã test)
    @Transactional
    public void insertDepartment(Department dep){
    	entityManager.persist(dep);
    }
}
