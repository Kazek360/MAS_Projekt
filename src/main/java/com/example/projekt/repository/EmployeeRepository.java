package com.example.projekt.repository;

import com.example.projekt.model._Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<_Employee, Long> {

}
