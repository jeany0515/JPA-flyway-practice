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
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
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

    @GetMapping(params = {"page", "size"})
    public PageImpl<Employee> findPagingEmployees(@PageableDefault Pageable pageable) {
        return this.employeeService.findPagingEmployees(pageable);
    }

    @GetMapping(params = "gender")
    public List<Employee> findEmployeesByGender(String gender) {
        return this.employeeService.findEmployeesByGender(gender);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return this.employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        return this.employeeService.editEmployee(id, updatedEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        this.employeeService.delete(id);
    }
}
