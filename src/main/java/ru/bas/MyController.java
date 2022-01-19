package ru.bas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {
	
	
	@GetMapping("/test")
	public String getString() {
		return "test";
	}
	

}
