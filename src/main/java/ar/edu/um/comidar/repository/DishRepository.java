package ar.edu.um.comidar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.um.comidar.entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long>{

}
