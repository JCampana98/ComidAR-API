package ar.edu.um.comidar.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.um.comidar.entity.Restaurant;
import ar.edu.um.comidar.repository.RestaurantRepositoryExt;

@org.springframework.stereotype.Service
public class RestaurantSearchImpl implements RestaurantRepositoryExt {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
    @Transactional(readOnly = true)
    public List<Restaurant> searchByQuery(final String text) {
        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        
    	QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Restaurant.class).get();
    	Query query = queryBuilder
    			.keyword()
    			.onFields("name","description","direction","telephone")
    			.matching(text)
    			.createQuery();
    	
        return fullTextEntityManager.createFullTextQuery(query, Restaurant.class).getResultList();
    }
}
