package com.rs.fer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;

@Controller
@RequestMapping("/api")
public class EditExpenseController {
	@Autowired
	private FERService ferService;

	@GetMapping("/EditExpenseOptions/{userId}")
	public ResponseEntity<List<Expense>> getExpenseOptions(@PathVariable("userId") int userId) {

		// 2.Call the service by passing the input
		List<Expense> expenses = ferService.getExpenseOptions(userId);
		return (!expenses.isEmpty()) ? new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK)
				: new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/EditExpense/{expenseId}")
	public ResponseEntity<Expense> getExpense(@PathVariable("expenseId") int expenseId) {

		// 2.Call the service by passing the input
		Expense expense = ferService.getExpense(expenseId);

		// 3.Show the status
		if (expense != null) {
			return new ResponseEntity<>(expense, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/editExpense/{userId}")
	public ResponseEntity<HttpStatus> update(@PathVariable("userId") int userId, @RequestBody Expense expense) {

		// 2.Call the service by passing the input
		boolean isEditExpense = ferService.editExpense(expense);

		// 3.Show the status
		if (isEditExpense) {

			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
}
