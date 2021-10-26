package com.thoughtworks.restful.repository;

import com.thoughtworks.restful.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByGender(String gender);
    List<Employee> findAllByCompanyId(Integer companyId);
}
