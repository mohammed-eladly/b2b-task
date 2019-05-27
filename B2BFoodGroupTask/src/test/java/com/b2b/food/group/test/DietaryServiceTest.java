package com.b2b.food.group.test;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.b2b.food.group.entities.DietaryEntity;
import com.b2b.food.group.helpers.FileHelper;
import com.b2b.food.group.service.DietaryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-mvc-servlet.xml")
public class DietaryServiceTest {

	private static final String RESOURCE_PATH = "src/test/resources/jsonfiles/dietary";

	@Autowired
	private DietaryService dietaryService;

	@Test
	public void testFindById() {
		DietaryEntity entity = dietaryService.findById(2L);
		System.out.println(entity);
	}

	@Test
	public void testFindAll() {
		List<DietaryEntity> list = dietaryService.findAll();

		System.out.println(list);
	}

	@Test
	public void testCreateDietary() {

		String[] filesNames = { "create-dietary" };

		for (String fileName : filesNames) {
			String modelString = FileHelper.readJsonFile(RESOURCE_PATH + "/" + fileName + ".txt");
			ObjectMapper mapper = new ObjectMapper();

			try {
				DietaryEntity model = mapper.readValue(modelString, DietaryEntity.class);

				DietaryEntity entity = dietaryService.createDietary(model);

				System.out.println(entity);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Test
	public void testUpdateDietary() {

		String[] filesNames = { "update-dietary" };

		for (String fileName : filesNames) {
			String modelString = FileHelper.readJsonFile(RESOURCE_PATH + "/" + fileName + ".txt");

			ObjectMapper mapper = new ObjectMapper();

			try {
				DietaryEntity model = mapper.readValue(modelString, DietaryEntity.class);

				DietaryEntity entity = dietaryService.updateDietary(model);

				System.out.println(entity);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testDeleteById() {
		dietaryService.deleteById(3L);
	}

	@Test
	public void testDeleteDietaryByFlagName() {
		dietaryService.deleteDietaryByFlagName("vegan");
	}

	@Before
	public void splitResult() {
		System.out.println("\n==========================================\n");
	}
}
