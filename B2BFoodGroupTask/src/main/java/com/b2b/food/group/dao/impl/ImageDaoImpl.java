
package com.b2b.food.group.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.food.group.dao.ImageDao;
import com.b2b.food.group.entities.ImageEntity;

@Repository
public class ImageDaoImpl implements ImageDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public ImageEntity findById(Long id) {

		Session session = sessionFactory.getCurrentSession();

		return session.get(ImageEntity.class, id);

	}

	@Override
	@Transactional
	public ImageEntity findByName(String imageName) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<ImageEntity> query = currentSession.createQuery("from ImageEntity i where i.imageName =: imageName",
				ImageEntity.class);
		query.setParameter("imageName", imageName);

		return query.getSingleResult();
	}

	@Override
	@Transactional
	public ImageEntity createImage(ImageEntity model) {
		Session session = sessionFactory.getCurrentSession();

		session.save(model);
		return model;
	}

	@Override
	@Transactional
	public ImageEntity updateImage(ImageEntity model) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(model);
		return model;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();

		ImageEntity imageEntity = session.get(ImageEntity.class, id);

		session.delete(imageEntity);

	}

	@Override
	@Transactional
	public void deleteByName(String name) {
		ImageEntity entity = findByName(name);

		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);

	}

}
