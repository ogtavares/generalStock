package com.stock.admnistrator.services;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.stock.admnistrator.exception.StockException;
import com.stock.admnistrator.model.User;
import com.stock.admnistrator.model.vos.UserDTO;
import com.stock.admnistrator.repositories.UserRepository;

@Service
@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserDTO getById(final Long userId) {
		try {
			User user = new User();
			UserDTO userDto = new UserDTO();

			user = userRepository.getUserById(userId);

			userDto = UserDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).cpf(user.getCpf()).birthday(user.getBirthday().toString())
					.inclusionDate(user.getInclusionDate()).build();
			
			return userDto;
		}catch(Exception e){
			 throw new StockException(" Error retrieving user please contact support", e);
		}

	}
	
	public User getByUsername(final String userEmail) {
		try {
			User user = new User();
//			UserDTO userDto = new UserDTO();

			user = userRepository.getUserByEmail(userEmail);
			
			if(user == null) {
				throw new UsernameNotFoundException(String.format("User %s not found", userEmail));
			}

//			userDto = UserDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).cpf(user.getCpf()).birthday(user.getBirthday().toString())
//					.inclusionDate(user.getInclusionDate()).build();
			
			return user;
		}catch(Exception e){
			 throw new StockException(" Error retrieving user please contact support", e);
		}

	}
	
	public List<UserDTO> listActiveUsers() {
		try {
			List<User> users = new ArrayList<User>();
			users = userRepository.listActiveUsers();
			
			List<UserDTO> usersDto = new ArrayList<UserDTO>();
			
			for(User user: users) {
				UserDTO userDto = new UserDTO();
				userDto = UserDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).cpf(user.getCpf()).birthday(user.getBirthday().toString())
						.inclusionDate(user.getInclusionDate()).build();
				
				usersDto.add(userDto);
			}
			
			return usersDto;
		}catch(Exception e){
			 throw new StockException(" Error retrieving users please contact support", e);
		}

	}
	
	public User saveUser(final UserDTO dto) {
		try {
			User existedCpfUser = userRepository.getUserByCpf(dto.getCpf());
			User existedEmailUser = userRepository.getUserByEmail(dto.getEmail());
			
			if(existedCpfUser != null) {
				throw new DataIntegrityViolationException(" This CPF is already in use");
			}
			
			if(existedEmailUser != null) {
				throw new DataIntegrityViolationException(" This email is already in use");
			}
			
			User user = new User();
			String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
			LocalDateTime birthday = LocalDateTime.parse(dto.getBirthday(), formatter);
			user = User.builder().name(dto.getName().toUpperCase()).email(dto.getEmail()).cpf(dto.getCpf()).birthday(birthday).inclusionDate(OffsetDateTime.now(ZoneId.of("GMT-3"))).build();

			userRepository.save(user);
			
			return user;
		}catch(Exception e){
			 throw new StockException(" Error saving user please contact support", e);
		}

	}
	
	public User setUserInactive(final Long userId) {
		try {
	        User user = userRepository.getUserById(userId);

	        if (user != null) {
	            user.setActive(false);
	            userRepository.save(user);
	        }

	        return user;
		}catch(Exception e){
			 throw new StockException(" Error deleting user please contact support", e);
		}

	    
	}
	
	public User updateUser(final UserDTO dto) {
		try {
			User user = new User();
			
			user = userRepository.getUserById(dto.getId());
			
			if(dto.getName() != null) {
				user.setName(dto.getName().toUpperCase());
			}
			
			if(dto.getEmail() != null) {
				User existedEmailUser = userRepository.getUserByEmail(dto.getEmail());
				
				if(existedEmailUser != null) {
					throw new DataIntegrityViolationException(" This email is already in use");
				}
				
				user.setEmail(dto.getEmail());
			}
			
			if(dto.getCpf() != null) {
				User existedCpfUser = userRepository.getUserByCpf(dto.getCpf());

				if(existedCpfUser != null) {
					throw new DataIntegrityViolationException(" This CPF is already in use");
				}
				
				user.setCpf(dto.getCpf());
			}
			
			if(dto.getBirthday() != null) {
				String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
				LocalDateTime birthday = LocalDateTime.parse(dto.getBirthday(), formatter);
				user.setBirthday(birthday);
			}
			

			userRepository.save(user);
			
			return user;
		}catch(Exception e) {
	        throw new StockException(" Error updating user info please contact support", e);
		}

	}
}
