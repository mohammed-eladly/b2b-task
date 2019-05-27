package com.b2b.food.group.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2b.food.group.entities.DietaryEntity;
import com.b2b.food.group.helpers.ResultBean;
import com.b2b.food.group.service.DietaryService;

@RestController
@RequestMapping("/dietary")
public class DietaryController {

	@Autowired
	private DietaryService dietaryService;

	@GetMapping("/{id}")
	public ResponseEntity<ResultBean> findById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id) {
		try {

			DietaryEntity entity = dietaryService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, entity));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

	@GetMapping
	public ResponseEntity<ResultBean> findAll(HttpServletRequest request, HttpServletResponse response) {
		try {

			List<DietaryEntity> entity = dietaryService.findAll();

			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, entity));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

	@PostMapping
	public ResponseEntity<ResultBean> createDietary(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DietaryEntity model) {
		try {

			DietaryEntity createdDietary = dietaryService.createDietary(model);

			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, createdDietary));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

	@PutMapping
	public ResponseEntity<ResultBean> updateDietary(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DietaryEntity model) {
		try {

			DietaryEntity createdDietary = dietaryService.updateDietary(model);

			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, createdDietary));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

	@DeleteMapping("/Id/{id}")
	public ResponseEntity<ResultBean> deleteByID(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long id) {
		try {

			dietaryService.deleteById(id);

			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, null));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

	@DeleteMapping("/name/{name}")
	public ResponseEntity<ResultBean> deleteByID(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String name) {
		try {

			dietaryService.deleteDietaryByFlagName(name);

			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, null));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

}
