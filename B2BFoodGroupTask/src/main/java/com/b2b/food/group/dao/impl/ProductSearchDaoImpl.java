package com.b2b.food.group.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.food.group.dao.ProductSearchDao;
import com.b2b.food.group.entities.ProductEntity;

@Repository
public class ProductSearchDaoImpl implements ProductSearchDao {

	@Autowired
	private SessionFactory sessionFacoty;

	@Override
	@Transactional
	public List<ProductEntity> searchByTitleOrDescription(String text) throws InterruptedException {

		List<ProductEntity> entities = null;
		Session session = sessionFacoty.getCurrentSession();

		FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(session);
		fullTextSession.createIndexer().startAndWait();

		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(ProductEntity.class).get();

		// Create lucene query
		// Set indexed field
//		org.apache.lucene.search.Query lucenceQuery = qb.keyword().onFields("title", "description")
//				.matching(text + "*").createQuery();

		org.apache.lucene.search.Query lucenceQuery = qb.simpleQueryString().onFields("title", "description")
				.matching(text + "*").createQuery();

		// Warp lucene query in org.hibernate.query.Query
		@SuppressWarnings("unchecked")
		org.hibernate.search.FullTextQuery query = fullTextSession.createFullTextQuery(lucenceQuery,
				ProductEntity.class);
		entities = query.getResultList();

		return entities;

	}

}
