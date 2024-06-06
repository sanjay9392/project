package com.rs.fer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;

@RestController
@RequestMapping("/api")
public class ExpenseReportController {
	@Autowired
	private FERService ferService;

	@GetMapping("/expenseReport/{userId}")
	public ResponseEntity<List<Expense>> report(@PathVariable("userId") int userId, @RequestParam String type,
			@RequestParam String fromDate, @RequestParam String toDate, HttpServletRequest request) {

		// 2.Call the service by passing the input
		List<Expense> expenses = ferService.getExpenseReport(userId, type, fromDate, toDate);

		if (!expenses.isEmpty()) {

			return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
		}
		return new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
	}
}
