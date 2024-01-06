package com.dailycodebuffer.springboot.tutorial.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dailycodebuffer.springboot.tutorial.entities.Department;
import com.dailycodebuffer.springboot.tutorial.services.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentService departmentService;
	
	private Department department;
	
	private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

	@BeforeEach
	void setUp() throws Exception {
		
		// Output Object
		department = Department.builder()
			.departmentAddress("Colorado")
			.departmentCode("IT-08")
			.departmentName("IT/Support")
			.departmentId(2L)
			.build();
		
	}

	@Test
	void testSaveDepartment() throws JsonProcessingException, Exception {

		// Input Object
		Department inputDepartment = Department.builder()
			.departmentAddress("Colorado")
			.departmentCode("IT-08")
			.departmentName("IT/Support")
			.build();
		
		Mockito.when(departmentService.saveDeparment(inputDepartment))
			.thenReturn(department);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/departments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ow.writeValueAsString(inputDepartment)))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

	@Test
	void testFetchDepartmentById() throws Exception {
		
		Mockito.when(departmentService.fetchDepartmentById(2L))
			.thenReturn(department);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/departments/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.departmentName")
						.value(department.getDepartmentName()));
		
	}

}
