package com.example.repo;
import java.util.Optional;

import com.example.model.UserDto;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserDto, Integer> {
	
	UserDto findByEmail(String email);

	UserDto findByUsernameOrEmail(String username, String email);

	UserDto findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);




}