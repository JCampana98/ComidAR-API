package ar.edu.um.comidar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	public IndexController() {
		super();
	}

	@GetMapping("/")
	public String index(Model model) {
		return "page/index";
	}
}
