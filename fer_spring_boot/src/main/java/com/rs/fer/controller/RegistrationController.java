package com.rs.fer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;

@RestController
@RequestMapping("/api")
public class RegistrationController {
	@Autowired
	private FERService ferService;

	@PostMapping("/registration")
	public ResponseEntity<HttpStatus> save(@RequestBody User user) {
		return new ResponseEntity<HttpStatus>(
				ferService.registration(user) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
