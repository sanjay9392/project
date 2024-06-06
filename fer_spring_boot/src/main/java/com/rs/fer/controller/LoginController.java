package com.rs.fer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.fer.service.FERService;

@RestController
@RequestMapping("/api")
public class LoginController {
	@Autowired
	FERService ferService;

	@GetMapping("/login")
	public ResponseEntity<Integer> authenticateUser(@RequestParam String username, @RequestParam String password) {

		// 2.Get the Service by passing the input
		int userId = ferService.login(username, password);

		// 3.Show the status
		if (userId > 0) {
			return new ResponseEntity<Integer>(userId, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
	}
}
