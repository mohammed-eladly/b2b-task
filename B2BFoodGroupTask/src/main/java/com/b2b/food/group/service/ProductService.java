package com.b2b.food.group.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.b2b.food.group.entities.ProductEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ProductService {

	public ProductEntity findById(Long id);

	public List<ProductEntity> findWithLimit(int limit);

	public ProductEntity createProduct(String modelString, MultipartFile inputFile)
			throws JsonParseException, JsonMappingException, IOException;

	public ProductEntity updateProduct(String modelString, MultipartFile inputFile)
			throws JsonParseException, JsonMappingException, IOException;

	public void deleteProductById(Long id);

	public List<ProductEntity> findByTitle(String title);

	public List<ProductEntity> searchByTitleOrDescription(String searchWord) throws InterruptedException;

}
