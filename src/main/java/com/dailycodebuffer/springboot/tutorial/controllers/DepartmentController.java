package com.dailycodebuffer.springboot.tutorial.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodebuffer.springboot.tutorial.entities.Department;
import com.dailycodebuffer.springboot.tutorial.exceptions.ObjectNotFoundException;
import com.dailycodebuffer.springboot.tutorial.services.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@PostMapping
	public Department saveDepartment(@Valid @RequestBody Department department) {
		return departmentService.saveDeparment(department);
	}
	
	@GetMapping
	public List<Department> fetchDepartmentList(){
		//logger.info("fetchDepartmentList -> DepartmentController");
		return departmentService.fetchDepartmentList();
	}
	
	@GetMapping("/{id}")
	public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws ObjectNotFoundException {
		return departmentService.fetchDepartmentById(departmentId);
	}
	
	@DeleteMapping("/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
		departmentService.deleteDepartmentById(departmentId);
		return "Department deleted successfully!";
	}
	
	@PutMapping("/{id}")
	public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department) {
		return departmentService.updateDepartment(departmentId, department);
	}
	
	@GetMapping("/name/{name}")
	public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
		return departmentService.fetchDepartmentByName(departmentName);
	}
	
}
