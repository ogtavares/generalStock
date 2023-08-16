package com.stock.admnistrator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stock.admnistrator.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value= "select * from tb_user tu"
			+ " where tu.id = :id and tu.active = true",nativeQuery= true)
	public User getUserById(@Param("id") Long id);
	
	@Query(value= "select * from tb_user tu"
			+ " where tu.active = true",nativeQuery= true)
	public List<User> listActiveUsers();
}
