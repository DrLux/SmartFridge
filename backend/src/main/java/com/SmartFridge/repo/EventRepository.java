package com.smartfridge.repo;

import com.smartfridge.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.persistence.EntityManager;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findAllByYearEqualsAndMonthEqualsAndDayGreaterThanEqual(int year, int month, int day);
}

