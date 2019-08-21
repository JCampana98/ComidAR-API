package ar.edu.um.comidar.controller;

import java.io.IOException;
import java.sql.Timestamp;
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

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.UploadErrorException;

import ar.edu.um.comidar.entity.Restaurant;
import ar.edu.um.comidar.services.RestaurantService;
import ar.edu.um.comidar.services.ImageService;
import ar.edu.um.comidar.services.RestaurantSearchImpl;

@Controller
@RequestMapping("/admin/restaurant")
public class RestaurantController {
	
	private final static Logger logger = LoggerFactory.getLogger(RestaurantController.class);

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private RestaurantSearchImpl restaurantSearchService;

	@Autowired
	private ImageService imageService;
	
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
	public String newRestaurant(@Valid @ModelAttribute("restaurant") Restaurant restaurant, BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws UploadErrorException, DbxException, IOException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		if(!result.hasErrors()) {
			restaurant.setImageUrl("/restaurants/" + restaurant.getRestaurantId() + "." + restaurant.getRestaurantImage().getExtension());
			restaurant.setCreationDate(timestamp);
			restaurant.setLastUpdateDate(timestamp);
			restaurantService.create(restaurant);
			imageService.uploadImage(restaurant.getRestaurantImage().getFile().getInputStream(), restaurant.getImageUrl());
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
	public String updateRestaurant(@Valid @ModelAttribute("restaurant") Restaurant restaurant, BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws UploadErrorException, DbxException, IOException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
		if(!result.hasErrors()) {
			restaurant.setImageUrl("/restaurants/" + restaurant.getRestaurantId() + "." + restaurant.getRestaurantImage().getExtension());
			restaurant.setCreationDate(restaurantService.findById(restaurant.getRestaurantId()).getCreationDate());
			restaurant.setLastUpdateDate(timestamp);
			imageService.deleteImage(restaurant.getImageUrl());
			imageService.uploadImage(restaurant.getRestaurantImage().getFile().getInputStream(), restaurant.getImageUrl());
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
	@GetMapping("/list/search")
	public ResponseEntity<List<Restaurant>> sendRestaurantListSearch(){
		return new ResponseEntity<>(restaurantSearchService.searchByQuery("mick"),HttpStatus.OK);
	}
	
}