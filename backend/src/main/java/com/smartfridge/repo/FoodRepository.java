package com.smartfridge.repo;

import com.smartfridge.model.Event;
import com.smartfridge.model.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Long> {
    List<Food> findByCategory(String category);
    void deleteById(long id);
    Boolean existsById(long id);
    Food findById(long id);
    List<Food> findAll();
    List<Food> findAllByYearEqualsAndMonthEqualsAndDayIsLessThanEqual(int year, int month, int day);
}