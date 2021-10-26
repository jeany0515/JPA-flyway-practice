package com.thoughtworks.restful.service;

import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void should_return_1_employee_when_find_all_given_1_employee() {
        //given
        Employee employee = new Employee("Ang", 18, "male", 999999, 1);
        List<Employee> employees = Collections.singletonList(employee);
        when(employeeRepository.findAll()).thenReturn(employees);

        //when
        List<Employee> actual = employeeService.findAll();

        //then
        assertEquals(employees, actual);
    }

    @Test
    void should_return_employee_1_when_find_by_id_given_one_employee_with_id1() {
        //given
        Employee employee = new Employee("Ang", 18, "male", 999999, 1);
        when(employeeRepository.findById(1)).thenReturn(employee);

        //when
        Employee actual = employeeService.findById(1);

        //then
        assertEquals(employee, actual);
    }

    @Test
    void should_return_1_employee_and_paging_data_when_find_paging_employees_given_one_employee_with_id1() {
        //given
        Employee employee = new Employee("Ang", 18, "male", 999999, 1);
        List<Employee> employees = Collections.singletonList(employee);
        PageRequest pageRequest = PageRequest.of(0, 1);
        PageImpl<Employee> employeePage = new PageImpl<>(employees, pageRequest, 1);
        when(employeeRepository.findPagingEmployees(any())).thenReturn(employeePage);

        //when
        PageImpl<Employee> actual = employeeService.findPagingEmployees(pageRequest);

        //then
        assertEquals(employeePage, actual);
    }
}