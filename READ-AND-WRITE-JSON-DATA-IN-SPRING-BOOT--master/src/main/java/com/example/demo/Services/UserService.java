package com.example.demo.Services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {
	private UserRepository userRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final String jsonFilePath = "C:\\DEV\\ITALY\\API\\Microservices1\\READ-AND-WRITE-JSON-DATA-IN-SPRING-BOOT--master\\src\\main\\resources\\json\\users.json";

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Iterable<User> list() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public Iterable<User> save(List<User> users) {
		return userRepository.saveAll(users);
	}

	public User addItem(User user) throws IOException {
		List<User> users = objectMapper.readValue(new File(jsonFilePath),
				objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
		users.add(user);

		objectMapper.writeValue(new File(jsonFilePath), users);
		return userRepository.save(user);
	}
	
	public Optional<User> getUserbyId(Long id) {
		return userRepository.findById(id);
	}
}
