package ar.edu.um.comidar.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import ar.edu.um.comidar.entity.Dish;
import ar.edu.um.comidar.entity.Restaurant;
import ar.edu.um.comidar.services.DishService;
import ar.edu.um.comidar.services.DocumentService;
import ar.edu.um.comidar.services.RestaurantService;

@Controller
@RequestMapping("/admin/dish")
public class DishController {

	private final static Logger logger = LoggerFactory.getLogger(RestaurantController.class);
	
	@Autowired
	private DishService dishService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private DocumentService imageService;
	
	public DishController() {
		super();
	}

	@GetMapping("/list")
	public String index(Model model) {
		model.addAttribute("dishList", dishService.findAll());
		
		return "page/dish/index";
	}
	
	@GetMapping("/new")
	public String newCategory(Model model){
		Dish dish = new Dish();
		model.addAttribute("dish", dish);
		model.addAttribute("restaurantList", restaurantService.findAll());
		return "page/dish/new";
	}
	
	@PostMapping("/new")
	public String newCategory(@Valid @ModelAttribute("dish") Dish dish, BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws UploadErrorException, DbxException, IOException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
		if(!result.hasErrors()) {
			dish.setCreationDate(timestamp);
			dish.setLastUpdateDate(timestamp);
			dishService.create(dish);
			dish.setImageUrl("/dishes/" + dish.getDishId() + "." + dish.getDishImage().getExtension());
			dish.setModelUrl("/dishes/models/" + dish.getDishId() + "." + dish.getDishModel().getExtension());
			imageService.uploadDocument(dish.getDishImage().getFile().getInputStream(), dish.getImageUrl());
			imageService.uploadDocument(dish.getDishModel().getFile().getInputStream(), dish.getModelUrl());
			dishService.update(dish);
			redirectAttributes.addFlashAttribute("message","Actualizacion realizada");
			redirectAttributes.addFlashAttribute("css","alert-success");
			return "redirect:/admin/dish/list";
		} else {
			for(ObjectError error : result.getAllErrors()) {
				logger.error("Validacion {}",error.getDefaultMessage());
			}
		}		
		return "page/dish/new";
	}
	
	@GetMapping("/update")
	public String updateCategory(@RequestParam(value="id",required=true) Long id, Model model){
		model.addAttribute("dish",dishService.findById(id));
		model.addAttribute("restaurantList", restaurantService.findAll());
		
		return "page/dish/update";
	}
	
	@PostMapping("/update")
	public String updateCategory(@Valid @ModelAttribute("dish") Dish dish, BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws UploadErrorException, DbxException, IOException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
		if(!result.hasErrors()) {
			dish.setLastUpdateDate(timestamp);
			dish.setImageUrl("/dishes/" + dish.getDishId() + "." + dish.getDishImage().getExtension());
			dish.setModelUrl("/dishes/models/" + dish.getDishId() + "." + dish.getDishModel().getExtension());
			dishService.update(dish);
			if(!dish.getDishImage().getFile().isEmpty()) {
				imageService.deleteDocument(dish.getImageUrl());
				imageService.deleteDocument(dish.getModelUrl());
				imageService.uploadDocument(dish.getDishImage().getFile().getInputStream(), dish.getImageUrl());
				imageService.uploadDocument(dish.getDishModel().getFile().getInputStream(), dish.getModelUrl());
			}
			redirectAttributes.addFlashAttribute("message","Actualizacion realizada");
			redirectAttributes.addFlashAttribute("css","alert-success");
			return "redirect:/admin/dish/list";
		} else {
			for(ObjectError error : result.getAllErrors()) {
				logger.error("Validacion {}",error.getDefaultMessage());
			}
		}
		
		return "page/dish/update";
	}
	
	@GetMapping("/delete")
	public String deleteCategory(@RequestParam(value="id",required=true) Long id, final RedirectAttributes redirectAttributes) throws UploadErrorException, DbxException, IOException{
		Dish dish = dishService.findById(id);
		dishService.remove(dish);
		redirectAttributes.addFlashAttribute("message","Se ha eliminado el usuario exitosamente");
		redirectAttributes.addFlashAttribute("css","alert-success");
		
		return "redirect:/admin/dish/list";
	}
	
	@GetMapping("/list/search")
	public ResponseEntity<List<Dish>> sendDishListByRestaurantId(@RequestParam(name="restaurantId") String id) throws UploadErrorException, DbxException, IOException{
		List<Dish> dishList = dishService.findAll();
		List<Dish> remove = new ArrayList<>();
		Restaurant restaurantAux = restaurantService.findById(Long.valueOf(id));
		
		for (Dish dish : dishList) {
			if (!dish.getRestaurant().equals(restaurantAux)) {
				remove.add(dish);
			} else {
				dish.setImageTemporaryUrl(imageService.getDocumentURL(dish.getImageUrl()));
				//dish.setModelTemporaryUrl(imageService.getDocumentURL(dish.getModelUrl()));
			}
		}
		
		dishList.removeAll(remove);
		
		return new ResponseEntity<>(dishList,HttpStatus.OK);
	}

}
