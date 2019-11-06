package com.library.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.entity.Fines;
import com.library.service.LibraryService;

@Controller
@RequestMapping("/fines")
public class FinesController {
	
	@Autowired
	private LibraryService impl;

	@GetMapping("/search")
	public String getFinesForm() {
		
		return "finesform";
	}
	
	@PostMapping({"/fineslist", "/fineslist/?cardiderror"})
	public String displayFines(@RequestParam("cardid")String cardid,@RequestParam(value="pastfines", required=false)String pastfine, Model model) {
		
		if(cardid=="" || Integer.parseInt(cardid)<=0) {
			
			model.addAttribute("cardiderror","Enter the Card id. It should not be empty and should be > 0");
			
			return "finesform";
		}
		
		else {
			
			if(!impl.isCardIdValid(Integer.parseInt(cardid))) {
				
				model.addAttribute("cardiderror","Enter the Card id which is in database");
				
				return "finesform";
			}
			
			else {
				List<Fines> fines = impl.getFines(Integer.parseInt(cardid));
				
				
				model.addAttribute("cardid",cardid);
				
				List<Fines> currentfines = new ArrayList<Fines>();
				
				for(Fines f :fines) {
					if(!f.isPaid()) {
						currentfines.add(f);
					}
				}
				
				
				
				float totalfine = 0;
				
				for(Fines f:fines) {
					if(!f.isPaid()) {
						totalfine = totalfine + f.getFineAmount();
					}
				}
				
				String totfine = "Total Fine is - $" + Float.toString(totalfine);
				
				model.addAttribute("totalfine",totfine);
				
				if(pastfine==null) {
					model.addAttribute("fines",currentfines);
				}
				else {
					model.addAttribute("fines",fines);
				}
				
				return "finesform";
			}
			
			
		}
		
		
	}
	
	@GetMapping("/payfine")
	public String payFines(@RequestParam("loanid")int loanid, Model model) {
		
		List<Fines> fines = impl.payFines(loanid);
		
		List<Fines> currentfines = new ArrayList<Fines>();
		
		for(Fines f :fines) {
			if(!(f.isPaid())) {
				currentfines.add(f);
			}
		}
		
		//model.addAttribute("currentfines",currentfines);
		
		model.addAttribute("fines",currentfines);
		
		return "finesform";
	}
	
}
