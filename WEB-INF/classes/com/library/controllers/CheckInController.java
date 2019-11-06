package com.library.controllers;
import com.library.entity.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.service.LibraryService;

@Controller
@RequestMapping("/checkin")
public class CheckInController {

	@Autowired
	private LibraryService service;
	
	@GetMapping({"/search", "/search/?checkouterror"})
	public String checkinform(@RequestParam(value="checkouterror", required=false) String checkouterror, Model model) {
		
		model.addAttribute("checkouterror",checkouterror);
		
		return "checkinform";
	}
	
	@GetMapping({"/checkinsearchlist", "/checinsearchlist/?checkinsuccess"})
	public String postCheckIn(@RequestParam("loanId")int loanid, Model model) {
		
		List<BookLoan> loans = service.updateLoan(loanid);
		
		model.addAttribute("loans",loans);
		
		model.addAttribute("checkinsuccess","Check-In successful!!!!");
		
		return "checkinform";
	}
	
	@PostMapping({"/checkinsearchlist", "/checkinsearchlist/?message"})
	public String checkInSearch(@RequestParam("isbn")String isbn, 
								@RequestParam("card_id")String cardid, @RequestParam("bName")String bname, Model model ) {
		
		List<BookLoan> loans = new ArrayList<BookLoan>();
		
		if(!(isbn=="")) {
			loans = service.getBookLoans(isbn);
			model.addAttribute("loans",loans);
			model.addAttribute("isbn",isbn);
		}
		
		else if(!(cardid=="")) {
			int cardidd=Integer.parseInt(cardid);
			loans = service.getBookLoansByCardid(cardidd);
			model.addAttribute("loans",loans);
			model.addAttribute("cardid",cardid);
		}
		
		else if(!(bname=="")) {
			loans = service.getBookLoans(bname);
			model.addAttribute("loans",loans);
			model.addAttribute("bname",bname);
		}
		
		else {
			model.addAttribute("message","Enter atleast one attribute to search loans");
			return "checkinform";
		}
		
		if(loans.isEmpty()) {
			model.addAttribute("wronginput","No Loans found for the input data.");
		}
		
		return "checkinform";
	}
}
