package ar.edu.um.comidar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	public CategoryController() {
		super();
	}

	@GetMapping("/list")
	public String index(Model model) {
		return "page/category/index";
	}
}
