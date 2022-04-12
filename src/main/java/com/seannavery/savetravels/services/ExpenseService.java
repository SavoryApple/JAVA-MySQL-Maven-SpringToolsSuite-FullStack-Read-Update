package com.seannavery.savetravels.services;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.seannavery.savetravels.models.Expense;
import com.seannavery.savetravels.repositories.ExpenseRepository;

@Service
public class ExpenseService {
	// adding the expense repository as a dependency
	private final ExpenseRepository expenseRepository;

	public ExpenseService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	// returns all the expenses
	public List<Expense> allExpenses() {
		return expenseRepository.findAll();
	}

	// creates an expense
	public Expense createExpense(Expense e) {
		return expenseRepository.save(e);
	}

	// deletes an expense
	public void deleteExpense(Long id) {
		expenseRepository.deleteById(id);
	}

	// updates an expense
	public Expense updateExpense(Long id, String name, String vendor, String description, Double amount) {
		Expense expenseToUpdate = this.findExpense(id);
		expenseToUpdate.setName(name);
		expenseToUpdate.setVendor(vendor);
		expenseToUpdate.setDescription(description);
		expenseToUpdate.setAmount(amount);
		expenseRepository.save(expenseToUpdate);
		return expenseToUpdate;
	}

	// retrieves an expense
	public Expense findExpense(Long id) {
		Optional<Expense> optionalExpense = expenseRepository.findById(id);
		if (optionalExpense.isPresent()) {
			return optionalExpense.get();
		} else {
			return null;
		}
	}
}