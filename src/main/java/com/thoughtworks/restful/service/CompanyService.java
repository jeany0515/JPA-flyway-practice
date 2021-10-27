package com.thoughtworks.restful.service;

import com.thoughtworks.restful.entity.Company;
import com.thoughtworks.restful.exception.CompanyNotFoundException;
import com.thoughtworks.restful.repository.CompanyRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    public Company findById(Integer id) {
        return this.companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    public PageImpl<Company> findPagingCompanies(Pageable pageable) {
        return (PageImpl<Company>) this.companyRepository.findAll(pageable);
    }

    public Company createCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public Company editCompany(Integer id, Company updatedCompany) {
        Company originCompany = this.findById(id);

        if (Objects.nonNull(updatedCompany.getName())) {
            originCompany.setName(updatedCompany.getName());
        }
        return this.companyRepository.save(originCompany);
    }

    public void delete(Integer id) {
        Company company = this.findById(id);
        this.companyRepository.delete(company);
    }
}
