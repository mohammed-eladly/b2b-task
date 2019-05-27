package com.b2b.food.group.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.food.group.dao.ProductDao;
import com.b2b.food.group.entities.ProductEntity;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public ProductEntity findById(Long id) {
		Session currentSession = sessionFactory.getCurrentSession();

		return currentSession.get(ProductEntity.class, id);
	}

	@Override
	@Transactional
	public List<ProductEntity> findWithLimit(int limit) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ProductEntity> query = currentSession.createQuery("from ProductEntity", ProductEntity.class);

		query.setMaxResults(limit);

		return query.getResultList();

	}

	@Override
	@Transactional
	public ProductEntity createProduct(ProductEntity model) {
		Session session = sessionFactory.getCurrentSession();

		session.save(model);
		return model;
	}

	@Override
	@Transactional
	public ProductEntity updateProduct(ProductEntity model) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(model);
		return model;
	}

	@Override
	@Transactional
	public void deleteProductById(ProductEntity productEntity) {
		Session session = sessionFactory.getCurrentSession();
//		ProductEntity productEntity = session.get(ProductEntity.class, id);
		session.delete(productEntity);

	}

	@Override
	@Transactional
	public List<ProductEntity> findByTitle(String title) {
		Session session = sessionFactory.getCurrentSession();

//		Query<ProductEntity> query = session.createQuery("from ProductEntity p where p.title=:productTitle",
//				ProductEntity.class);
//		query.setParameter("productTitle", title);

		Query<ProductEntity> query = session.createQuery(
				"from ProductEntity p where p.title like concat('%',:productTitle,'%')", ProductEntity.class);

		query.setParameter("productTitle", title);

		return query.list();
	}

}
