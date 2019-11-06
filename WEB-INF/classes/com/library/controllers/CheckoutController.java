package com.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.service.LibraryService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	
	@Autowired
	private LibraryService impl;

	@GetMapping("/cardidform")
	public String cardIdForm(@RequestParam("isbn")String isbn, Model model) {
		
		model.addAttribute("isbn",isbn);
		
		return "cardidform";
	}
	
	@PostMapping("/checkoutform")
	public String borrowerForm(@RequestParam("isbn")String isbn, @RequestParam("card_id") int cardid, Model model) {
		
		if(impl.isCardIdValid(cardid)) {
			int loans = impl.getLoans(cardid);
			if(loans<3)
			{
				
				impl.createBookLoan(isbn, cardid);
				
				model.addAttribute("loans", loans);
				
				model.addAttribute("isbn",isbn);
				model.addAttribute("card_id",cardid);
				
				model.addAttribute("checkoutsuccess","Successfully checked out!!");
				
				return "redirect:/book/search";
			}
			
			else {
				String str = "You already have maximum loans. Please check in first";
				model.addAttribute("checkouterror",str);
				return "redirect:/checkin/search";
			}
			
		}
		
		else {
			model.addAttribute("cardiderror","Invalid Card ID entered. Doesn't exist in the database. For new Card ID create borrower.");
			model.addAttribute("isbn",isbn);
			return "cardidform";
		}
		
		
		
	}
}
