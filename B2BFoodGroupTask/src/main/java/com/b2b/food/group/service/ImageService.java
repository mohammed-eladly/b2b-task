package com.b2b.food.group.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.b2b.food.group.entities.ImageEntity;
import com.b2b.food.group.entities.ProductEntity;

public interface ImageService {
	public ImageEntity uploadImage(MultipartFile inputFile) throws IllegalStateException, IOException;

	public ImageEntity createImageEntity(ImageEntity entity);

	public void updateImage(ProductEntity model, ImageEntity uploadImage);

	public void deleteImage(ImageEntity entity);
}
