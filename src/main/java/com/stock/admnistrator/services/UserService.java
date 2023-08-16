package com.stock.admnistrator.services;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.stock.admnistrator.model.User;
import com.stock.admnistrator.model.vos.UserDTO;
import com.stock.admnistrator.repositories.UserRepository;

@Service
@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserDTO getById(Long id) {
		User user = new User();
		UserDTO userDto = new UserDTO();

		user = userRepository.getUserById(id);

		userDto = UserDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).cpf(user.getCpf()).birthday(user.getBirthday().toString())
				.inclusionDate(user.getInclusionDate()).build();
		
		return userDto;
	}
	
	public User saveUser(UserDTO dto) {
		User user = new User();
		String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		LocalDateTime birthday = LocalDateTime.parse(dto.getBirthday(), formatter);
		user = User.builder().name(dto.getName().toUpperCase()).email(dto.getEmail()).cpf(dto.getCpf()).birthday(birthday).inclusionDate(OffsetDateTime.now(ZoneId.of("GMT-3"))).build();

		userRepository.save(user);
		
		return user;
	}
	
	public User setUserInactive(Long userId) {
		User user = new User();
		user = userRepository.getUserById(userId);
		
		user.setActive(false);
		
		userRepository.save(user);
		
		return user;
	}
	
	public User updateUser(UserDTO dto) {
		User user = new User();
		
		user = userRepository.getUserById(dto.getId());
		
		if(dto.getName() != null) {
			user.setName(dto.getName().toUpperCase());
		}
		
		if(dto.getEmail() != null) {
			user.setEmail(dto.getEmail());
		}
		
		if(dto.getCpf() != null) {
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
	}
}
