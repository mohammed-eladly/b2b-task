package com.b2b.food.group.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.food.group.dao.DietaryDao;
import com.b2b.food.group.entities.DietaryEntity;

@Repository
public class DietaryDaoImpl implements DietaryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public DietaryEntity findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(DietaryEntity.class, id);
	}

	@Override
	@Transactional
	public List<DietaryEntity> findAll() {

		Session session = sessionFactory.getCurrentSession();
		List<DietaryEntity> resultList = session.createQuery("from DietaryEntity", DietaryEntity.class).getResultList();
		return resultList;
	}

	@Override
	@Transactional
	public DietaryEntity createDietary(DietaryEntity model) {

		Session session = sessionFactory.getCurrentSession();

		session.save(model);
		return model;
	}

	@Override
	@Transactional
	public DietaryEntity updateDietary(DietaryEntity model) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(model);
		return model;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {

		Session session = sessionFactory.getCurrentSession();

		DietaryEntity dietaryEntity = session.get(DietaryEntity.class, id);
		if (dietaryEntity != null)
			session.delete(dietaryEntity);

	}

	@Override
	@Transactional
	public void deleteDietaryByFlagName(String flagName) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete from DietaryEntity where flagName=:flagName");
		query.setParameter("flagName", flagName);
		query.executeUpdate();
	}

}
