package com.b2b.food.group.service;

import java.util.List;

import com.b2b.food.group.entities.DietaryEntity;

public interface DietaryService {

	public DietaryEntity findById(Long id);

	public List<DietaryEntity> findAll();

	public DietaryEntity createDietary(DietaryEntity model);

	public DietaryEntity updateDietary(DietaryEntity model);

	public void deleteById(Long id);

	public void deleteDietaryByFlagName(String flagName);
}
