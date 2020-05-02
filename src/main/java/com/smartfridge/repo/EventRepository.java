package com.smartfridge.repo;

import com.smartfridge.model.Event;
import org.springframework.data.repository.CrudRepository;

import javax.swing.border.EmptyBorder;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findAllByYearEqualsAndMonthEqualsAndDayGreaterThanEqual(int year, int month, int day);
	void deleteById(long id);
	Boolean existsById(long id);
}

