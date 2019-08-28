package ar.edu.um.comidar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.um.comidar.entity.Restaurant;
import ar.edu.um.comidar.repository.RestaurantRepository;

@Service
@Transactional
public class RestaurantService extends ServiceImplement<Restaurant, Long> {
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public void create(Restaurant entity) {
		super.create(entity);
	}

	@Override
	public void update(Restaurant entity) {
		super.update(entity);
	}

	@Override
	public void remove(Restaurant entity) {
		super.remove(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Restaurant findById(Long id) {
		return super.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Restaurant> findAll() {
		return super.findAll();
	}

}
