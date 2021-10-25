package com.thoughtworks.restful.controller;

import com.thoughtworks.restful.entity.Company;
import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.repository.CompanyRepository;
import com.thoughtworks.restful.repository.EmployeeRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyController(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable Integer id) {
        return this.companyRepository.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> findEmployeesByCompanyId(@PathVariable Integer id) {
        return this.employeeRepository.findByCompanyId(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public PageImpl<Company> findPagingCompanies(@PageableDefault Pageable pageable) {
        return this.companyRepository.findPagingCompanies(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Company createCompany(@RequestBody Company employee) {
        return this.companyRepository.createCompany(employee);
    }

    @PutMapping("/{id}")
    public Company editCompany(@PathVariable Integer id, @RequestBody Company updatedCompany) {
        Company originCompany = this.companyRepository.findById(id);

        if (Objects.nonNull(updatedCompany.getName())) {
            originCompany.setName(updatedCompany.getName());
        }
        return this.companyRepository.updateCompany(originCompany);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        this.companyRepository.delete(id);
    }
}
