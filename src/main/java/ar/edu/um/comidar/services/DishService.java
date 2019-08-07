package ar.edu.um.comidar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.um.comidar.entity.Dish;
import ar.edu.um.comidar.repository.DishRepository;

@Service
@Transactional
public class DishService extends ServiceImplement<Dish, Long> {
	@Autowired
	private DishRepository dishRepository;

	@Override
	public void create(Dish entity) {
		super.create(entity);
	}

	@Override
	public void update(Dish entity) {
		super.update(entity);
	}

	@Override
	public void remove(Dish entity) {
		super.remove(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Dish findById(Long id) {
		return super.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Dish> findAll() {
		return super.findAll();
	}
}
