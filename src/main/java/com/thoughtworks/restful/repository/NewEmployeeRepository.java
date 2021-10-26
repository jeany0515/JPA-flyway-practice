package com.thoughtworks.restful.repository;

import com.thoughtworks.restful.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewEmployeeRepository extends JpaRepository<Employee, Integer> {
}
