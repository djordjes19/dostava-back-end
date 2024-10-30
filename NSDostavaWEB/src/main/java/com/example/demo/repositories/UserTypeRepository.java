package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

	UserType findByName(String string);

}
