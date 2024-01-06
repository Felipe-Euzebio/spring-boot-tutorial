package com.dailycodebuffer.springboot.tutorial.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dailycodebuffer.springboot.tutorial.entities.Department;
import com.dailycodebuffer.springboot.tutorial.repositories.DepartmentRepository;

@SpringBootTest
class DepartmentServiceTest {
	
	@Autowired
	private DepartmentService departmentService;
	
	@MockBean
	private DepartmentRepository departmentRepository;

	@BeforeEach
	void setUp() throws Exception {
		
		Department department = Department.builder()
				.departmentName("Marketing")
				.departmentAddress("Fortaleza")
				.departmentCode("MKT")
				.departmentId(1L)
				.build();
		
		Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("Marketing"))
			.thenReturn(department);
		
	}

	@Test
	@DisplayName("Get data based on valid department name")
//	@Disabled
	public void whenValidDepartmentNameThenDepartmentShouldFound() {
		
		String departmentName = "Marketing";
		Department found = departmentService.fetchDepartmentByName(departmentName);
		
		assertEquals(departmentName, found.getDepartmentName());
		
	}

}
