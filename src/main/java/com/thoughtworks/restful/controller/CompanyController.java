package com.thoughtworks.restful.controller;

import com.thoughtworks.restful.entity.Company;
import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.repository.EmployeeRepository;
import com.thoughtworks.restful.service.CompanyService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService, EmployeeRepository employeeRepository) {
        this.companyService = companyService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Company> findAll() {
        return this.companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable Integer id) {
        return this.companyService.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> findEmployeesByCompanyId(@PathVariable Integer id) {
        return this.employeeRepository.findAllByCompanyId(id);
    }

    @GetMapping(params = {"page", "size"})
    public PageImpl<Company> findPagingCompanies(@PageableDefault Pageable pageable) {
        return this.companyService.findPagingCompanies(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company createCompany(@RequestBody Company company) {
        return this.companyService.createCompany(company);
    }

    @PutMapping("/{id}")
    public Company editCompany(@PathVariable Integer id, @RequestBody Company updatedCompany) {
        return this.companyService.editCompany(id, updatedCompany);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        this.companyService.delete(id);
    }
}
