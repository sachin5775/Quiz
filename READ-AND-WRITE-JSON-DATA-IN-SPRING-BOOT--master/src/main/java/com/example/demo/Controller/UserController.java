package com.example.demo.Controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.UserService;
import com.example.demo.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/list")
	public Iterable<User> list() {
		return userService.list();
	}

	@PostMapping("/save")
	public User save(@RequestBody User user) throws IOException {
		
		return userService.addItem(user);
	}

	@GetMapping("/{userid}")
	public Optional<User> getuserbyId(@PathVariable Long userid) {
		return userService.getUserbyId(userid);
	}

}
