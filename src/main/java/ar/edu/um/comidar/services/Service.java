package ar.edu.um.comidar.services;

import java.io.Serializable;
import java.util.List;

public interface Service<T,ID extends Serializable>  {
	void create(final T entity);
	void update(final T entity);
	void remove(final T entity);
	T findById(final ID id);
	List<T> findAll();
}
