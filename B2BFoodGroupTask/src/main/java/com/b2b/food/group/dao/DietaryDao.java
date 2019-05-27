package com.b2b.food.group.dao;

import java.util.List;

import com.b2b.food.group.entities.DietaryEntity;

public interface DietaryDao {

	// CRUD

	public DietaryEntity findById(Long id);

	public List<DietaryEntity> findAll();

	public DietaryEntity createDietary(DietaryEntity model);

	public DietaryEntity updateDietary(DietaryEntity model);

	public void deleteById(Long id);

	public void deleteDietaryByFlagName(String flagName);

}
