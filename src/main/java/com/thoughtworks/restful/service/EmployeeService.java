package com.thoughtworks.restful.service;

import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.repository.EmployeeRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public List<Employee> findEmployeesByGender(String gender) {
        return this.employeeRepository.findEmployeesByGender(gender);
    }

    public Employee createEmployee(Employee employee) {
        return this.employeeRepository.createEmployee(employee);
    }

    public Employee editEmployee(Integer id, Employee updatedEmployee) {
        Employee originEmployee = this.employeeRepository.findById(id);
        if (Objects.nonNull(updatedEmployee.getAge())) {
            originEmployee.setAge(updatedEmployee.getAge());
        }
        if (Objects.nonNull(updatedEmployee.getGender())) {
            originEmployee.setGender(updatedEmployee.getGender());
        }
        if (Objects.nonNull(updatedEmployee.getName())) {
            originEmployee.setName(updatedEmployee.getName());
        }
        if (Objects.nonNull(updatedEmployee.getSalary())) {
            originEmployee.setSalary(updatedEmployee.getSalary());
        }
        if (Objects.nonNull(updatedEmployee.getCompanyId())) {
            originEmployee.setCompanyId(updatedEmployee.getCompanyId());
        }
        return this.employeeRepository.updateEmployee(originEmployee);
    }

    public void delete(Integer id) {
        this.employeeRepository.delete(id);
    }
}
