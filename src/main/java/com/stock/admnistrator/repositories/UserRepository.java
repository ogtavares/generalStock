package com.stock.admnistrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.admnistrator.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
