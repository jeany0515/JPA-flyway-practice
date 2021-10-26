package com.thoughtworks.restful.service;

import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.exception.EmployeeNotFoundException;
import com.thoughtworks.restful.repository.EmployeeRepository;
import com.thoughtworks.restful.repository.NewEmployeeRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private final NewEmployeeRepository newEmployeeRepository;

    public EmployeeService(NewEmployeeRepository newEmployeeRepository) {
        this.newEmployeeRepository = newEmployeeRepository;
    }

    public List<Employee> findAll(){
        return this.newEmployeeRepository.findAll();
    }

    public Employee findById(Integer id) {
        return this.newEmployeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public PageImpl<Employee> findPagingEmployees(Pageable pageable) {
        return (PageImpl<Employee>) this.newEmployeeRepository.findAll(pageable);
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return this.newEmployeeRepository.findAllByGender(gender);
    }

    public Employee createEmployee(Employee employee) {
        return this.newEmployeeRepository.save(employee);
    }

    public Employee editEmployee(Integer id, Employee updatedEmployee) {
        Employee originEmployee = this.findById(id);
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
        return this.newEmployeeRepository.save(originEmployee);
    }

    public void delete(Integer id) {
        Employee employee = this.findById(id);
        this.newEmployeeRepository.delete(employee);
    }
}
