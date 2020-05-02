package com.smartfridge.repo;

import com.smartfridge.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<Users, Long> {
    List<Users> findByName(String name);
}


