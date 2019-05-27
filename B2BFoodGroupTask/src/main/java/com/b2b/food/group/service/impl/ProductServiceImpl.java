package com.b2b.food.group.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.b2b.food.group.dao.ProductDao;
import com.b2b.food.group.dao.ProductSearchDao;
import com.b2b.food.group.entities.ImageEntity;
import com.b2b.food.group.entities.ProductEntity;
import com.b2b.food.group.service.ImageService;
import com.b2b.food.group.service.ProductService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ImageService imageService;

	@Autowired
	private ProductSearchDao productSearchDao;

	@Override
	public ProductEntity findById(Long id) {
		return productDao.findById(id);
	}

	@Override
	public List<ProductEntity> findWithLimit(int limit) {
		return productDao.findWithLimit(limit);
	}

	@Override
	public ProductEntity createProduct(String modelString, MultipartFile inputFile)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();

		ProductEntity model = mapper.readValue(modelString, ProductEntity.class);

		ImageEntity uploadImage = imageService.uploadImage(inputFile);

		ImageEntity imageEntity = imageService.createImageEntity(uploadImage);

		model.setImageEntity(imageEntity);

		return productDao.createProduct(model);
	}

	@Override
	public ProductEntity updateProduct(String modelString, MultipartFile inputFile)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();

		ProductEntity model = mapper.readValue(modelString, ProductEntity.class);

		model = findById(model.getId());

		ImageEntity uploadImage = imageService.uploadImage(inputFile);

		imageService.updateImage(model, uploadImage);

		return productDao.updateProduct(model);
	}

	@Override
	public void deleteProductById(Long id) {
		ProductEntity findById = productDao.findById(id);

		productDao.deleteProductById(findById);

		imageService.deleteImage(findById.getImageEntity());

	}

	@Override
	public List<ProductEntity> findByTitle(String title) {
		return productDao.findByTitle(title);
	}

	@Override
	public List<ProductEntity> searchByTitleOrDescription(String searchWord) throws InterruptedException {
		return productSearchDao.searchByTitleOrDescription(searchWord);

	}

}
