package com.laptrinhjavaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.service.Impl.CategoryService;

@CrossOrigin
@RestController
public class CategoryAPI {

	@Autowired
	private CategoryService categoryService;

	/**
	 * Show all data new
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@GetMapping(value = "/category")
	public List<CategoryDTO> showNew() {
		return categoryService.findAll();
	}

	/**
	 * Method create data new
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/category")
	public CategoryDTO createCategory(@RequestBody CategoryDTO model) {
		return (CategoryDTO) categoryService.save(model);
	}

	/**
	 * Method update data category by {id}
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/category/{id}")
	public CategoryDTO updateCategory(@RequestBody CategoryDTO model, @PathVariable("id") long id) {
		model.setId(id);
		return (CategoryDTO) categoryService.save(model);
	}

	/**
	 * Method delete data new by {ids}
	 * 
	 * @param ids
	 */
	@DeleteMapping(value = "/category")
	public void deleteNew(@RequestBody long[] ids) {
		categoryService.delete(ids);
	}
}
