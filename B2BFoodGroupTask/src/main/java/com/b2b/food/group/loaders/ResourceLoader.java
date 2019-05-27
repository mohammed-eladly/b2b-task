package com.b2b.food.group.loaders;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Component
@PropertySources({ @PropertySource("classpath:config.properties") })
public class ResourceLoader {

	@Value("${bucket_name}")
	private String bucketName;

	@Value("${access_key}")
	private String accessKey;

	@Value("${secret_key}")
	private String secretKey;

	private AWSCredentials credentials;
	private AmazonS3 s3Client;

	@PostConstruct
	public void init() {
		credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

		s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1).build();

	}

	public String getBucketName() {
		return bucketName;
	}

	public AmazonS3 getS3Client() {
		return s3Client;
	}

}
