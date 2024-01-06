package com.dailycodebuffer.springboot.tutorial.repositories;

import org.springframework.stereotype.Repository;
import com.dailycodebuffer.springboot.tutorial.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	public Department findByDepartmentName(String departmentName);
	
	public Department findByDepartmentNameIgnoreCase(String departmentName);
	
}
