package nistagram.adminservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nistagram.adminservice.model.enums.CategoryType;

@RestController
@RequestMapping(value = "api/categorytype")
public class CategoryTypeController {
	
	@GetMapping("/get-category-types")
	public ResponseEntity<List<CategoryType>> getReportTypes() {
		List<CategoryType> response = new ArrayList<CategoryType>(Arrays.asList(CategoryType.values()));
		return new ResponseEntity<List<CategoryType>>(response, HttpStatus.OK);
	}

}
