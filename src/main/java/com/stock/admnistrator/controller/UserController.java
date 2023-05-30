package com.stock.admnistrator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock.admnistrator.model.User;
import com.stock.admnistrator.model.vos.UserDTO;
import com.stock.admnistrator.repositories.UserRepository;
import com.stock.admnistrator.services.UserService;


@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private UserService userService;

  @GetMapping(value = "/getUsers")  
  public List<User> getUsers() {
  	List<User> users = userRepository.findAll();
  	return users;
  }
  
  @GetMapping(value = "/getUserById")  
  public UserDTO getUserById(@RequestParam Long id) {
  	UserDTO user = userService.getById(id);
  	return user;
  }
  
  @PostMapping(value = "/saveUser")  
  public User saveUser(@RequestBody UserDTO userDto) {
  	User user = userService.saveUser(userDto);
  	return user;
  }

}
