package ar.edu.um.comidar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.um.comidar.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
