package com.smartfridge.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.smartfridge.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByAge(int age);


}

