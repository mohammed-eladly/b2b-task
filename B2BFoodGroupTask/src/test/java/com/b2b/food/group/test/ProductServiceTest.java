package com.b2b.food.group.test;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import com.b2b.food.group.entities.ProductEntity;
import com.b2b.food.group.helpers.FileHelper;
import com.b2b.food.group.service.ProductService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-mvc-servlet.xml")
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	private static final String RESOURCE_PATH = "src/test/resources/jsonfiles/product";

	@Test
	public void testFindById() {
		ProductEntity entity = productService.findById(1L);

		System.out.println(entity);
	}

	@Test
	public void testFindWithLimit() {
		List<ProductEntity> entity = productService.findWithLimit(100);

		System.out.println(entity);
	}

	@Test
	public void testCreateProduct() {

		String[] filesNames = { "create-product" };
		String[] images = { "corn-flex-2.jpeg" };

		if (filesNames.length == images.length) {
			for (int i = 0; i < filesNames.length; i++) {
				String fileName = filesNames[i];
				String image = images[i];
				String modelString = FileHelper.readJsonFile(RESOURCE_PATH + "/" + fileName + ".txt");

				File img = new File(RESOURCE_PATH + "images/" + image);

				MultipartFile multipartFile = convertFileToMultipartFile(img);

				try {
					ProductEntity createProduct = productService.createProduct(modelString, multipartFile);

					System.out.println(createProduct);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		fail("Arrays Not Equal for Json and Images Test Files");
	}

	@Test
	public void testUpdateProduct() {

		String[] filesNames = { "update-product" };
		String[] images = { "corn-flex-2.jpeg" };

		if (filesNames.length == images.length) {
			for (int i = 0; i < filesNames.length; i++) {
				String fileName = filesNames[i];
				String image = images[i];
				String modelString = FileHelper.readJsonFile(RESOURCE_PATH + "/" + fileName + ".txt");

				File img = new File(RESOURCE_PATH + "images/" + image);

				MultipartFile multipartFile = convertFileToMultipartFile(img);

				try {
					ProductEntity createProduct = productService.updateProduct(modelString, multipartFile);

					System.out.println(createProduct);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		fail("Arrays Not Equal for Json and Images Test Files");
	}

	@Test
	public void testDeleteProductById() {
		productService.deleteProductById(4L);
	}

	@Test
	public void testFindByTitle() {
		List<ProductEntity> entity = productService.findByTitle("cor");

		System.out.println(entity);
	}

	@Test
	public void testSearchByTitleOrDescription() {

		try {
			List<ProductEntity> list = productService.searchByTitleOrDescription("wor");
			System.out.println(list);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private MultipartFile convertFileToMultipartFile(File file) {

		MultipartFile multipartFile = null;
		try {
			FileInputStream input = new FileInputStream(file);
			multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return multipartFile;
	}

	@After
	public void splitResult() {
		System.out.println("\n==========================================\n");
	}

}
