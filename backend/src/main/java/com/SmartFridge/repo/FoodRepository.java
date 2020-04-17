package com.smartfridge.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.smartfridge.model.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {
	List<Food> findByName(String name);
}

