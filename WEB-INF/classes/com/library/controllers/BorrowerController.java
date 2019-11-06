package com.library.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.entity.Borrower;
import com.library.service.LibraryService;

@Controller
@RequestMapping("/borrower")
public class BorrowerController {
	
	@Autowired
	private LibraryService service;
	
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmer);
	}
	
	@GetMapping("/addborrower")
	public String addBorrower(Model model) {
		
		Borrower br = new Borrower();
		
		model.addAttribute("borrower",br);
		
		return "borrowerform";
	}

	@PostMapping("/saveBorrower")
	public String saveBorrower(@Valid @ModelAttribute("borrower")Borrower br, BindingResult bres, Model model) {
		
		if(bres.hasErrors()) {
			return "borrowerform";
		}
		
		else {
			
			if(service.checkDuplicate(br)) {
				service.addBorrower(br);
				
				model.addAttribute("borrowersuccess","Borrower added Successfully!!!");
				
				return "redirect:/book/search";
			}
			
			else {
				model.addAttribute("message","Borrower already present in the database");
				return "borrowerform";
			}
			
		}
	}
}
