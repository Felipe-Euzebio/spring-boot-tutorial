package com.dailycodebuffer.springboot.tutorial.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.dailycodebuffer.springboot.tutorial.entities.Department;

@DataJpaTest
class DepartmentRepositoryTest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() throws Exception {
		
		Department department = Department.builder()
				.departmentName("Mechanical Engineering")
				.departmentAddress("Delhi")
				.departmentCode("ME-011")
				.build();
		
		entityManager.persist(department);
		
	}

	@Test
	public void whenFindByIdThenReturnDepartment() {

		String departmentName = "Mechanical Engineering";
		Department department = departmentRepository.findById(1L).get();
		
		assertEquals(departmentName, department.getDepartmentName());
		
	}

}
