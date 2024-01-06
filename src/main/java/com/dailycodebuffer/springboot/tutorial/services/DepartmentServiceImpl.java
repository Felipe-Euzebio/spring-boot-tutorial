package com.dailycodebuffer.springboot.tutorial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailycodebuffer.springboot.tutorial.entities.Department;
import com.dailycodebuffer.springboot.tutorial.exceptions.ObjectNotFoundException;
import com.dailycodebuffer.springboot.tutorial.repositories.DepartmentRepository;
import com.dailycodebuffer.springboot.tutorial.utils.ExtraUtils;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDeparment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> fetchDepartmentList() {
		return departmentRepository.findAll();
	}

	@Override
	public Department fetchDepartmentById(Long departmentId) throws ObjectNotFoundException {
		Optional<Department> department = departmentRepository.findById(departmentId);
		
		if(!department.isPresent()) {
			throw new ObjectNotFoundException("Department not available");
		}
		
		return department.get();
	}

	@Override
	public void deleteDepartmentById(Long departmentId) {
		departmentRepository.deleteById(departmentId);
	}

	@Override
	public Department updateDepartment(Long departmentId, Department department) {
		return departmentRepository.findById(departmentId)
			.map(dp -> {
				BeanUtils.copyProperties(department, dp, ExtraUtils.getNullPropertyNames(department));
				return departmentRepository.save(dp);
			})
			.orElse(null);
	}

	@Override
	public Department fetchDepartmentByName(String departmentName) {
		return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
	}

}
