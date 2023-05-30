package com.stock.admnistrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stock.admnistrator.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value= "select * from tb_user tu"
			+ " where tu.id = :id",nativeQuery= true)
	public User getUserById(@Param("id") Long id);
}
