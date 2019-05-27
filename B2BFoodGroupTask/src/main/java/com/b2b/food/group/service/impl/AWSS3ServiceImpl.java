package com.b2b.food.group.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.b2b.food.group.loaders.ResourceLoader;
import com.b2b.food.group.service.AWSS3Service;

@Service
public class AWSS3ServiceImpl implements AWSS3Service {

	@Autowired
	private ResourceLoader resourceLoader;

	// uploading object
	public String putObject(String key, File file) {

		PutObjectRequest putObjectRequest = new PutObjectRequest(resourceLoader.getBucketName(), key, file);

		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read); // all users or authenticated

		putObjectRequest.setAccessControlList(acl);

		resourceLoader.getS3Client().putObject(putObjectRequest);

		return resourceLoader.getS3Client().getUrl(resourceLoader.getBucketName(), key).toString();

	}

	// listing objects
	public ObjectListing listObjects(String bucketName) {
		return resourceLoader.getS3Client().listObjects(bucketName);
	}

	// get an object
	public S3Object getObject(String bucketName, String objectKey) {
		return resourceLoader.getS3Client().getObject(bucketName, objectKey);
	}

	// copying an object
	public CopyObjectResult copyObject(String sourceBucketName, String sourceKey, String destinationBucketName,
			String destinationKey) {
		return resourceLoader.getS3Client().copyObject(sourceBucketName, sourceKey, destinationBucketName,
				destinationKey);
	}

	// deleting an object
	public void deleteObject(String objectKey) {
		resourceLoader.getS3Client().deleteObject(resourceLoader.getBucketName(), objectKey);
	}

	// deleting multiple Objects
	public DeleteObjectsResult deleteObjects(DeleteObjectsRequest delObjReq) {
		return resourceLoader.getS3Client().deleteObjects(delObjReq);
	}

}
