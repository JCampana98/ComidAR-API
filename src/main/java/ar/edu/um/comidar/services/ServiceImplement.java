package ar.edu.um.comidar.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.um.comidar.services.Service;

public class ServiceImplement<T, ID extends Serializable> implements Service<T, ID> {

	@Autowired
	protected JpaRepository<T, ID> dao;
	
	@Override
	public void create(T entity) {
		dao.saveAndFlush(entity);
	}

	@Override
	public void update(T entity) {
		dao.saveAndFlush(entity);
	}

	@Override
	public void remove(T entity) {
		dao.delete(entity);
		dao.flush();
	}

	@Override
	public T findById(ID id) {
		return dao.findById(id).get();
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}
	
}
