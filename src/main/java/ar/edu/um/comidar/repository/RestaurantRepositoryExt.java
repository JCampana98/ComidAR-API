package ar.edu.um.comidar.repository;

import java.util.List;

import ar.edu.um.comidar.entity.Restaurant;

public interface RestaurantRepositoryExt {
	List<Restaurant> searchByQuery(final String query);
}
