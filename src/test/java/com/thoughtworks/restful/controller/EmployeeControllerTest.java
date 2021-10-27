package com.thoughtworks.restful.controller;

import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_employees_when_find_all_given_two_employee() throws Exception {
        //given
        Employee employee1 = new Employee("Ang", 18, "male", 999999, 1);
        Employee employee2 = new Employee("Jeany", 18, "female", 999999, 1);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //when
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Ang"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(999999))
                .andExpect(jsonPath("$[0].companyId").value(1));
    }


    @Test
    void should_return_employee_when_get_employee_given_id() throws Exception {
        //given
        Employee employee = new Employee("chen", 18, "male", 999999, 1);
        employeeRepository.save(employee);
        Integer employeeId = employee.getId();

        //when then
        mockMvc.perform(get("/employees/"+employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("chen"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.age").value(18));
    }

    @Test
    void should_return_employees_when_get_employees_given_gender() throws Exception {
        //given
        Employee employee1 = new Employee("chen", 18, "male", 999999, 1);
        Employee employee2 = new Employee("jeany", 18, "female", 999999, 1);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //when then
        mockMvc.perform(get("/employees")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("gender", "female"))
                .andExpect(jsonPath("$[0].name").value("jeany"))
                .andExpect(jsonPath("$[0].gender").value("female"));
    }

    @Test
    void should_create_employee_when_post_employee_given_employee_info() throws Exception {

        String employeeAsJson = "    {\n" +
                "        \"name\": \"jeany\",\n" +
                "        \"age\": 20,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 6000,\n" +
                "        \"companyId\":" + 1 + "\n" +
                "    }";

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("jeany"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.gender").value("male"));
    }

    @Test
    void should_return_employee_when_update_employee_given_employee_and_id() throws Exception {
        //given
        Employee employee = new Employee("chen", 18, "female", 999999, 1);
        employeeRepository.save(employee);
        Integer employeeId = employee.getId();

        //when then
        mockMvc.perform(put("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\":\"spy\",\n" +
                        "    \"age\":18,\n" +
                        "    \"gender\":\"male\",\n" +
                        "    \"salary\": 200,\n" +
                        "    \"companyId\":" + 1 + "\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("spy"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.age").value(18));
    }
}