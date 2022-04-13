package ru.sold_out.serving_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("")
	public String greeting(
	) {
		return "main/main";
	}

}
