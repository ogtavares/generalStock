package com.stock.admnistrator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.admnistrator.model.User;
import com.stock.admnistrator.repositories.UserRepository;


@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping(value = "/getUsers")  
  public List<User> getUsers() {
  	List<User> users = userRepository.findAll();
  	return users;
  }

  // other controller methods
}
