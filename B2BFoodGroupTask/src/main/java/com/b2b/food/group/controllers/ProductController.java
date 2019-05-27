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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.b2b.food.group.entities.ProductEntity;
import com.b2b.food.group.helpers.ResultBean;
import com.b2b.food.group.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{id}")
	public ResponseEntity<ResultBean> findById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id) {
		try {

			ProductEntity entity = productService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, entity));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

	@GetMapping("/search/{text}")
	public ResponseEntity<ResultBean> searchByTitleOrDescription(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("text") String searchWord) {

		List<ProductEntity> entity;
		try {
			entity = productService.searchByTitleOrDescription(searchWord);
		} catch (InterruptedException e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e));

		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, entity));

	}

	@GetMapping("/limit/{limit}")
	public ResponseEntity<ResultBean> findWithLimit(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("limit") int limit) {
		try {

			List<ProductEntity> entity = productService.findWithLimit(limit);
			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, entity));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<ResultBean> getByTitle(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("title") String title) {
		try {

			List<ProductEntity> entity = productService.findByTitle(title);

			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, entity));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

	@RequestMapping(value = "/create", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ResultBean> createProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("model") String modelString, @RequestParam("file") MultipartFile inputFile) {
		try {

			ProductEntity productEntity = productService.createProduct(modelString, inputFile);
			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, productEntity));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

	@RequestMapping(value = "/update", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<ResultBean> updateProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("model") String modelString, @RequestParam("file") MultipartFile inputFile) {
		try {

			ProductEntity productEntity = productService.updateProduct(modelString, inputFile);
			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, productEntity));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<ResultBean> deleteById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long id) {
		try {

			productService.deleteProductById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResultBean(true, HttpStatus.OK, null, null));

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultBean(false, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
		}
	}

}
