package com.rs.fer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;

@Controller
@RequestMapping("/api")
public class DeleteExpenseController {
	@Autowired
	private FERService ferService;

	@GetMapping("/DeleteExpenseOptions/{userId}")
	public ResponseEntity<List<Expense>> getExpenseOptions(@PathVariable("userId") int userId) {

		// 2.Call the service by passing the input
		List<Expense> expenses = ferService.getExpenseOptions(userId);
		return (!expenses.isEmpty()) ? new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK)
				: new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/DeleteExpense/{expenseId}")
	public ResponseEntity<Integer> delete(@PathVariable("expenseId") int expenseId) {

		// 2.Call the service by passing the input
		boolean isDeleteExpense = ferService.deleteExpense(expenseId);

		// 3.Show the status
		if (isDeleteExpense) {
			return new ResponseEntity<Integer>(HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
	}
}
