package com.b2b.food.group.dao;

import java.util.List;

import com.b2b.food.group.entities.ProductEntity;

public interface ProductSearchDao {

	public List<ProductEntity> searchByTitleOrDescription(String text) throws InterruptedException;
}
