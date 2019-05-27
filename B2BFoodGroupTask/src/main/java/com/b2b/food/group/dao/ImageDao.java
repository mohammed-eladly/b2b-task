package com.b2b.food.group.dao;

import com.b2b.food.group.entities.ImageEntity;

public interface ImageDao {

	// CRUD

	public ImageEntity findById(Long id);

	public ImageEntity findByName(String imageName);

	public ImageEntity createImage(ImageEntity model);

	public ImageEntity updateImage(ImageEntity model);

	public void deleteById(Long id);

	public void deleteByName(String name);
}
