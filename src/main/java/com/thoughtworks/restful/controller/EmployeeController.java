package com.thoughtworks.restful.controller;

import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.repository.EmployeeRepository;
import com.thoughtworks.restful.service.EmployeeService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> findAll() {
        return this.employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Integer id) {
        return this.employeeService.findById(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public PageImpl<Employee> findPagingEmployees(@PageableDefault Pageable pageable) {
        return this.employeeService.findPagingEmployees(pageable);
    }

    @GetMapping(params = "gender")
    public List<Employee> findEmployeesByGender(String gender) {
        return this.employeeRepository.findEmployeesByGender(gender);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return this.employeeRepository.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
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
        return this.employeeRepository.updateEmployee(originEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        this.employeeRepository.delete(id);
    }
}
