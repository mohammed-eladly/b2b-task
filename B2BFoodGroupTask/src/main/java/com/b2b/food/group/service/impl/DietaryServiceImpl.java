package com.b2b.food.group.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.food.group.dao.DietaryDao;
import com.b2b.food.group.entities.DietaryEntity;
import com.b2b.food.group.service.DietaryService;

@Service
public class DietaryServiceImpl implements DietaryService {

	@Autowired
	private DietaryDao dietaryDao;

	@Override
	public DietaryEntity findById(Long id) {

		return dietaryDao.findById(id);
	}

	@Override
	public List<DietaryEntity> findAll() {

		return dietaryDao.findAll();
	}

	@Override
	public DietaryEntity createDietary(DietaryEntity model) {
		return dietaryDao.createDietary(model);
	}

	@Override
	public DietaryEntity updateDietary(DietaryEntity model) {
		return dietaryDao.updateDietary(model);
	}

	@Override
	public void deleteById(Long id) {
		dietaryDao.deleteById(id);

	}

	@Override
	public void deleteDietaryByFlagName(String flagName) {
		dietaryDao.deleteDietaryByFlagName(flagName);

	}

}
