package com.smartfridge.repo;

import java.util.Calendar;
import java.util.List;

import com.smartfridge.model.Category;
import org.springframework.data.repository.CrudRepository;

import com.smartfridge.model.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {
	List<Food> findByCategory(Category category);
	@Override
	void deleteById(Long id);
}

