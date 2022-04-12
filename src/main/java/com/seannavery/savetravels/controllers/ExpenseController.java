package com.seannavery.savetravels.controllers;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seannavery.savetravels.models.Expense;
import com.seannavery.savetravels.services.ExpenseService;

import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class ExpenseController {
	private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }
    
    @RequestMapping("/expenses/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseService.findExpense(id);
        model.addAttribute("expense", expense);
        return "show.jsp";
    }
    
    @RequestMapping("/expenses")
    public String index(Model model, @ModelAttribute("expense") Expense expense) {
        List<Expense> expenses = expenseService.allExpenses();
        model.addAttribute("expenses", expenses);
        return "index.jsp";
    }
    
    @RequestMapping(value="/expenses/create", method=RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("expense") Expense expense, BindingResult result) {
        if (result.hasErrors()) {
        	List<Expense> expenses = expenseService.allExpenses();
            model.addAttribute("expenses", expenses);
            return "index.jsp";
        } else {
            expenseService.createExpense(expense);
            return "redirect:/expenses";
        }
    }
    
    
}