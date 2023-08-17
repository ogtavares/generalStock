package com.stock.admnistrator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;


import com.stock.admnistrator.model.User;
import com.stock.admnistrator.model.vos.UserDTO;
import com.stock.admnistrator.services.UserService;


@RestController
public class UserController {  
  @Autowired
  private UserService userService;

  @GetMapping(value = "/listActiveUsers")  
  public ResponseEntity<?> listActiveUsers() {
	try {
	  	List<UserDTO> usersDto = userService.listActiveUsers();
	  	return new ResponseEntity<>(usersDto, HttpStatus.OK);
	}catch(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
  }
  
  @GetMapping(value = "/getUserById")  
  public ResponseEntity<?> getUserById(@NotNull @RequestParam(name = "id") Long id) {
	try {
	  	UserDTO user = userService.getById(id);
	  	return new ResponseEntity<>(user, HttpStatus.OK);
	}catch(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

  }
  
  @PostMapping(value = "/saveUser")  
  public ResponseEntity<?> saveUser(@NotNull @RequestBody UserDTO userDto) {
	try {
		User user = userService.saveUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}catch(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
  }
  
  @PostMapping(value = "/setUserInactive")  
  public ResponseEntity<?>  setUserInactive(@NotNull @RequestBody UserDTO dto) {
	  try {
	  	User user = userService.setUserInactive(dto.getId());
		return new ResponseEntity<>(user, HttpStatus.OK);
	  }catch(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }
  }
  
  @PostMapping(value = "/updateUser")  
  public ResponseEntity<?> updateUser(@NotNull @RequestBody UserDTO dto) {
	  try {
	  	User user = userService.updateUser(dto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	  }catch(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }
  }

}
