package com.thoughtworks.restful.service;

import com.thoughtworks.restful.entity.Company;
import com.thoughtworks.restful.repository.CompanyRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    public Company findById(Integer id) {
        return this.companyRepository.findById(id);
    }

    public PageImpl<Company> findPagingCompanies(Pageable pageable) {
        return this.companyRepository.findPagingCompanies(pageable);
    }

    public Company createCompany(Company company) {
        return this.companyRepository.createCompany(company);
    }

    public Company editCompany(Integer id, Company updatedCompany) {
        Company originCompany = this.companyRepository.findById(id);

        if (Objects.nonNull(updatedCompany.getName())) {
            originCompany.setName(updatedCompany.getName());
        }
        return this.companyRepository.updateCompany(originCompany);
    }

    public void delete(Integer id) {
        this.companyRepository.delete(id);
    }
}
