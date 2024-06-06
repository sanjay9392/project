package com.rs.fer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.fer.service.FERService;

@RestController
@RequestMapping("/api")
public class ResetPasswordController {
	@Autowired
	private FERService ferService;

	@PutMapping("/resetPassword/{id}")
	public ResponseEntity<HttpStatus> reset(@PathVariable("id") int id, @RequestParam String currentPassword,
			@RequestParam String newPassword) {

		// 2.Call the service by passing the input
		boolean isResetPassword = ferService.resetPassword(newPassword, id, currentPassword);

		// 3.Show the status
		if (isResetPassword) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
