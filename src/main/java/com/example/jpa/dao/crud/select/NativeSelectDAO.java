package com.example.jpa.dao.crud.select;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.entity.Department;

/**
 * nên dùng JPA  SQL native 
 +  vẫn tận dụng đc ORM từ JPA anotation java class
 +  dùng Proguard để change name của JavaClass members vẫn ok.
 +  nếu dùng JSQL thì khi đổi tên class member sẽ phải đổi tên ở JSQL nữa rất bất tiện.
 +  source code tối ưu hơn => vd: Update
 +  tach Lazy-load làm bằng tay sẽ tối ưu hơn.

 */

/**
 * SQL native sample nen tham khao: 
 * https://github.com/hungnguyenmanh82/Javacore_TestJDBC
 * 
 * Ưu điểm lớn nhất của ORM là mapping => giảm thời gian phát triển. Cần tận dụng nó.
 * Convert dữ liệu nên tiến hành ở getter/setter sẽ hay hơn nhiều
 */
@Repository           //singleton
public class NativeSelectDAO {
    @Autowired
    private EntityManager entityManager;  //vì @repository là singleton nên => entityManager là singleton
    
    //cách 1:
    // bất kỳ lệnh SQL nào cũng phải ở trong 1 transaction (nếu ko sẽ có lỗi exception)
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
    	//phai liet ke du column cua table tuong ung voi Department.class , ko can dung thu tu van ok (vì map theo ten ở Department.class)
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
