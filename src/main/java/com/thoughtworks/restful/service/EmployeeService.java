package com.thoughtworks.restful.service;

import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.repository.EmployeeRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll(){
        return this.employeeRepository.findAll();
    }

    public Employee findById(Integer id) {
        return this.employeeRepository.findById(id);
    }

    public PageImpl<Employee> findPagingEmployees(Pageable pageable) {
        return this.employeeRepository.findPagingEmployees(pageable);
    }
}
