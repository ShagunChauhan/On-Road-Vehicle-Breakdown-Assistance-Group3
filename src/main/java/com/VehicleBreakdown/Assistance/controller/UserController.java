package com.VehicleBreakdown.Assistance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VehicleBreakdown.Assistance.model.User;
import com.VehicleBreakdown.Assistance.service.UserService;

import io.swagger.models.Response;

@RestController
@RequestMapping("/userapi")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/new")
	public User userRegistration(@Valid @RequestBody User user) {
		return userService.userRegistration(user);
	}
	
	@GetMapping("/user/all")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/byid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value="id") Long userId)
	{
		User user = userService.getUserById(userId).orElse(null);
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/user/update/byid/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable(value="id") Long userId,@Valid @RequestBody User userinfo)
	{
		User user = userService.getUserById(userId).orElse(null);
		user.setEmailId(userinfo.getEmailId());
		user.setPhoneNumber(userinfo.getPhoneNumber());
		user.setUserName(userinfo.getUserName());
		user.setUserPassword(userinfo.getUserPassword());
		
		User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(updatedUser);
	}
}
