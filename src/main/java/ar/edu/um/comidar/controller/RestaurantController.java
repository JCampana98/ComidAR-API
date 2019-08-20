package ar.edu.um.comidar.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.um.comidar.entity.Restaurant;
import ar.edu.um.comidar.services.RestaurantService;

@Controller
@RequestMapping("/admin/restaurant")
public class RestaurantController {
	
	private final static Logger logger = LoggerFactory.getLogger(RestaurantController.class);
	
	@Autowired
	private RestaurantService restaurantService;

	public RestaurantController() {
		super();
	}

	@GetMapping("/list")
	public String restaurants(Model model) {

		model.addAttribute("restaurantList", restaurantService.findAll());
		
		return "page/restaurant/index";
	}
	
	@GetMapping("/new")
	public String newRestaurant(Model model){
		Restaurant restaurant = new Restaurant();
		model.addAttribute("restaurant", restaurant);
		
		return "page/restaurant/new";
	}
	
	@PostMapping("/new")
	public String newRestaurant(@Valid @ModelAttribute("restaurant") Restaurant restaurant, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		if(!result.hasErrors()) {
			restaurant.setCreationDate(timestamp);
			restaurant.setLastUpdateDate(timestamp);
			restaurantService.create(restaurant);
			redirectAttributes.addFlashAttribute("message","Actualizacion realizada");
			redirectAttributes.addFlashAttribute("css","alert-success");
			return "redirect:/admin/restaurant/list";
		} else {
			for(ObjectError error : result.getAllErrors()) {
				logger.error("Validacion {}",error.getDefaultMessage());
			}
		}
		
		return "page/restaurant/new";
	}
	
	@GetMapping("/update")
	public String updateRestaurant(@RequestParam(value="id",required=true) Long id, Model model){
		model.addAttribute("restaurant",restaurantService.findById(id));
		
		return "page/restaurant/update";
	}
	
	@PostMapping("/update")
	public String updateRestaurant(@Valid @ModelAttribute("restaurant") Restaurant restaurant, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
		if(!result.hasErrors()) {
			restaurant.setCreationDate(restaurantService.findById(restaurant.getRestaurantId()).getCreationDate());
			restaurant.setLastUpdateDate(timestamp);
			restaurantService.update(restaurant);
			redirectAttributes.addFlashAttribute("message","Actualizacion realizada");
			redirectAttributes.addFlashAttribute("css","alert-success");
			return "redirect:/admin/restaurant/list";
		} else {
			for(ObjectError error : result.getAllErrors()) {
				logger.error("Validacion {}",error.getDefaultMessage());
			}
		}
		
		return "page/restaurant/update";
	}
	
	@GetMapping("/delete")
	public String deleteRestaurant(@RequestParam(value="id",required=true) Long id, final RedirectAttributes redirectAttributes){
		restaurantService.remove(restaurantService.findById(id));
		redirectAttributes.addFlashAttribute("message","Se ha eliminado el usuario exitosamente");
		redirectAttributes.addFlashAttribute("css","alert-success");
		
		return "redirect:/admin/restaurant/list";
	}

	@GetMapping("/list/all")
	public ResponseEntity<List<Restaurant>> sendRestaurantList(){
		return new ResponseEntity<>(restaurantService.findAll(),HttpStatus.OK);
	}
	
}