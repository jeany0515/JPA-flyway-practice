package com.thoughtworks.restful.repository;

import com.thoughtworks.restful.entity.Company;
import com.thoughtworks.restful.entity.Employee;
import com.thoughtworks.restful.exception.CompanyNotFoundException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    private final List<Company> companies;

    public CompanyRepository() {
        this.companies = new ArrayList<>();
    }

    public List<Company> findAll() {
        return this.companies;
    }

    public Company findById(Integer id) {
        return this.companies.stream()
                .filter(item -> id.equals(item.getId()))
                .findFirst()
                .orElseThrow(CompanyNotFoundException::new);
    }

    public PageImpl<Company> findPagingCompanies(Pageable pageable) {
        List<Company> companyList = this.companies.stream()
                .skip((long) (pageable.getPageNumber() + 1) * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl<>(companyList, pageable, this.companies.size());
    }


    public Company createCompany(Company company) {
        int newId = this.companies.stream()
                .mapToInt(Company::getId)
                .max()
                .orElse(0) + 1;

        company.setId(newId);
        this.companies.add(company);

        return company;
    }

    public Company updateCompany(Company updatedCompany) {
        Company company = this.companies.stream()
                .filter(item -> updatedCompany.getId().equals(item.getId()))
                .findFirst()
                .orElseThrow(CompanyNotFoundException::new);
        this.companies.remove(company);
        this.companies.add(updatedCompany);
        return company;
    }

    public void delete(Integer id) {
        Company company = this.companies.stream()
                .filter(item -> id.equals(item.getId()))
                .findFirst()
                .orElseThrow(CompanyNotFoundException::new);
        this.companies.remove(company);
    }

}
