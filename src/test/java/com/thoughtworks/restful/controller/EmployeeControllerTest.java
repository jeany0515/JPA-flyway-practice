package com.thoughtworks.restful.controller;

import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_employees_when_find_all_given_two_employee() throws Exception {
        //given
        Employee employee1 = new Employee("Ang", 18, "male", 999999);
        Employee employee2 = new Employee("Jeany", 18, "female", 999999);
        employeeRepository.createEmployee(employee1);
        employeeRepository.createEmployee(employee2);
        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Ang\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 999999\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Jeany\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"female\",\n" +
                "        \"salary\": 999999\n" +
                "    }\n" +
                "]";
        //when
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }


    @Test
    void should_return_employee1_when_find_all_given_employee1() throws Exception {
        //given
        Employee employee = new Employee("Ang", 18, "male", 999999);
        employeeRepository.createEmployee(employee);
        String expected =
                "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"Ang\",\n" +
                        "        \"age\": 18,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"salary\": 999999\n" +
                        "    }";
        //when
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void should_return_employee1_and_paging_data_when_find_all_given_1_employee() throws Exception {
        //given
        Employee employee = new Employee("Ang", 18, "male", 999999);
        employeeRepository.createEmployee(employee);
        String expected ="{\n" +
                "    \"content\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"Ang\",\n" +
                "            \"age\": 18,\n" +
                "            \"gender\": \"male\",\n" +
                "            \"salary\": 999999\n" +
                "        }\n" +
                "    ],\n" +
                "    \"pageable\": {\n" +
                "        \"sort\": {\n" +
                "            \"sorted\": false,\n" +
                "            \"empty\": true,\n" +
                "            \"unsorted\": true\n" +
                "        },\n" +
                "        \"pageNumber\": 0,\n" +
                "        \"pageSize\": 10,\n" +
                "        \"offset\": 0,\n" +
                "        \"unpaged\": false,\n" +
                "        \"paged\": true\n" +
                "    },\n" +
                "    \"last\": true,\n" +
                "    \"totalPages\": 1,\n" +
                "    \"totalElements\": 1,\n" +
                "    \"sort\": {\n" +
                "        \"sorted\": false,\n" +
                "        \"empty\": true,\n" +
                "        \"unsorted\": true\n" +
                "    },\n" +
                "    \"numberOfElements\": 1,\n" +
                "    \"size\": 10,\n" +
                "    \"number\": 0,\n" +
                "    \"first\": true,\n" +
                "    \"empty\": false\n" +
                "}";
        //when
        mockMvc.perform(get("/employees?page=0&pageSize=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }
}