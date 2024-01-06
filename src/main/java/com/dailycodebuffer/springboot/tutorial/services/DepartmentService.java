package com.dailycodebuffer.springboot.tutorial.services;

import java.util.List;

import com.dailycodebuffer.springboot.tutorial.entities.Department;
import com.dailycodebuffer.springboot.tutorial.exceptions.ObjectNotFoundException;

public interface DepartmentService {

	public Department saveDeparment(Department department);

	public List<Department> fetchDepartmentList();

	public Department fetchDepartmentById(Long departmentId) throws ObjectNotFoundException;

	public void deleteDepartmentById(Long departmentId);

	public Department updateDepartment(Long departmentId, Department department);
	
	public Department fetchDepartmentByName(String departmentName);

}
