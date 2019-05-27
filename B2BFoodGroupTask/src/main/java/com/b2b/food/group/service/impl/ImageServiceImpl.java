package com.b2b.food.group.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.b2b.food.group.dao.ImageDao;
import com.b2b.food.group.entities.ImageEntity;
import com.b2b.food.group.entities.ProductEntity;
import com.b2b.food.group.service.AWSS3Service;
import com.b2b.food.group.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ServletContext context;

	@Autowired
	private AWSS3Service s3Service;

	@Autowired
	private ImageDao imageDao;

	@Override
	public ImageEntity uploadImage(MultipartFile inputFile) throws IllegalStateException, IOException {

		ImageEntity imageEntity = new ImageEntity();

		String extension = FilenameUtils.getExtension(inputFile.getOriginalFilename());

		String path = context.getRealPath("/WEB-INF/uploaded") + File.separator + inputFile.getOriginalFilename() + ""
				+ extension;

		File file = new File(path);
		inputFile.transferTo(file);

		String imageUrl = s3Service.putObject(inputFile.getOriginalFilename(), file);

		imageEntity.setImageName(inputFile.getOriginalFilename());
		imageEntity.setImageUrl(imageUrl);

		file.delete();

		return imageEntity;
	}

	@Override
	public ImageEntity createImageEntity(ImageEntity entity) {

		return imageDao.createImage(entity);
	}

	@Override
	public void updateImage(ProductEntity model, ImageEntity uploadImage) {
		s3Service.deleteObject(model.getImageEntity().getImageName());

		ImageEntity imageFromDB = model.getImageEntity();
		imageFromDB.setImageName(uploadImage.getImageName());
		imageFromDB.setImageUrl(uploadImage.getImageUrl());

		ImageEntity updateImage = imageDao.updateImage(imageFromDB);
		model.setImageEntity(updateImage);

	}

	@Override
	public void deleteImage(ImageEntity entity) {
		s3Service.deleteObject(entity.getImageName());

	}

}
