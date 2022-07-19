package com.example.beautysalon.repository;

import com.example.beautysalon.model.Employee;
import com.example.beautysalon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
