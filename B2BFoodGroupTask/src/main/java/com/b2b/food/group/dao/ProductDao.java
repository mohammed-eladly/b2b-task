package com.b2b.food.group.dao;

import java.util.List;
import com.b2b.food.group.entities.ProductEntity;

public interface ProductDao {

	// CRUD

	public ProductEntity findById(Long id);

	public List<ProductEntity> findWithLimit(int limit);

	public ProductEntity createProduct(ProductEntity model);

	public ProductEntity updateProduct(ProductEntity model);

	public void deleteProductById(ProductEntity entity);

	public List<ProductEntity> findByTitle(String title);

	

}
