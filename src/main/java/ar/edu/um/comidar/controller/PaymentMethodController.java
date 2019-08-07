package ar.edu.um.comidar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/paymentmethod")
public class PaymentMethodController {

	public PaymentMethodController() {
		super();
	}

	@GetMapping("/list")
	public String index(Model model) {
		return "page/paymentmethod/index";
	}
}
