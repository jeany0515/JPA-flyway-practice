package com.thoughtworks.restful.repository;

import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.exception.EmployeeNotFoundException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees;

    public EmployeeRepository() {
        this.employees = new ArrayList<>();
    }

    public List<Employee> findAll() {
        return this.employees;
    }

    public Employee findById(Integer id) {
        return this.employees.stream()
                .filter(item -> id.equals(item.getId()))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public PageImpl<Employee> findPagingEmployees(Pageable pageable) {
        List<Employee> employeeList = this.employees.stream()
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(employeeList, pageable, this.employees.size());
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return this.employees.stream()
                .filter(item -> gender.equals(item.getGender()))
                .collect(Collectors.toList());

    }

    public Employee createEmployee(Employee employee) {
        int newId = this.employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0) + 1;

        employee.setId(newId);
        this.employees.add(employee);

        return employee;
    }

    public Employee updateEmployee(Employee updatedEmployee) {
        Employee employee = this.employees.stream()
                .filter(item -> updatedEmployee.getId().equals(item.getId()))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        this.employees.remove(employee);
        this.employees.add(updatedEmployee);
        return employee;
    }

    public void delete(Integer id) {
        Employee employee = this.employees.stream()
                .filter(item -> id.equals(item.getId()))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        this.employees.remove(employee);
    }

    public List<Employee> findByCompanyId(Integer id) {
        return this.employees.stream()
                .filter(item -> id.equals(item.getId()))
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        this.employees.clear();
    }
}
