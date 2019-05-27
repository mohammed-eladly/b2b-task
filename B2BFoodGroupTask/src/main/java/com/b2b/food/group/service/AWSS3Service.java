package com.b2b.food.group.service;

import java.io.File;

public interface AWSS3Service {
	public String putObject(String key, File file);

	public void deleteObject(String objectKey);

}
