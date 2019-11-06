package com.library.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.entity.*;
import com.library.service.LibraryService;

@Controller
@RequestMapping("/book")
public class BookSearchController {
	
	@Autowired
	private LibraryService service;

	@GetMapping({"/search", "/search/?borrowersuccess","/search/?checkoutsuccess"})
	public String searchForm(@RequestParam(value="checkoutsuccess", required=false, defaultValue="")String checkoutsuccess,
								@RequestParam(value="borrowersuccess", required=false,defaultValue="")String borrowersuccess,
								Model model) {
		
		
		
		model.addAttribute("checkoutsuccess",checkoutsuccess);
		model.addAttribute("borrowersuccess",borrowersuccess);
		return "booksearch";
	}
	
	@PostMapping("/searchresult")
	public String searchResult(@RequestParam("keyword")String keyword, Model model) {
		
		List<Book> results = service.getResults(keyword);
		
		
		
		model.addAttribute("resultBooks",results);
		
		model.addAttribute("keyword", keyword);
		
		return "booksearch";
	}
}
