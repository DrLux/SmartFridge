package com.smartfridge.repo;

import com.smartfridge.model.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAllByYearEqualsAndMonthEqualsAndDayGreaterThanEqual(int year, int month, int day);
    List<Event> findAllByYearEqualsAndMonthEqualsAndDayIsLessThanEqual(int year, int month, int day);
    void deleteById(long id);
    Boolean existsById(long id);
}