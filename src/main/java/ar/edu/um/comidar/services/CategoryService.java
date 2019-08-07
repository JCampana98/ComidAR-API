package ar.edu.um.comidar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.um.comidar.entity.Category;
import ar.edu.um.comidar.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService extends ServiceImplement<Category, Long>{
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void create(Category entity) {
		super.create(entity);
	}

	@Override
	public void update(Category entity) {
		super.update(entity);
	}

	@Override
	public void remove(Category entity) {
		super.remove(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Category findById(Long id) {
		return super.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Category> findAll() {
		return super.findAll();
	}
}
