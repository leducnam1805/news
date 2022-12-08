package com.laptrinhjavaweb.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.CategoryConverter;
import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.service.ICRUDService;

@Service
public class CategoryService implements ICRUDService<CategoryDTO> {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryConverter categoryConverter;

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryDTO> results = new ArrayList<>();
		List<CategoryEntity> entities = categoryRepository.findAll();
		for (CategoryEntity item : entities) {
			CategoryDTO categoryDTO = categoryConverter.toDTO(item);
			results.add(categoryDTO);
		}
		return results;
	}

	@Override
	public List<CategoryDTO> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = new CategoryEntity();
		if (categoryDTO.getId() != null) {
			CategoryEntity oldcategoryEntity = categoryRepository.findOne(categoryDTO.getId());
			categoryEntity = categoryConverter.toEntity(categoryDTO, oldcategoryEntity);
		} else {
			categoryEntity = categoryConverter.toEntity(categoryDTO);
		}
		categoryEntity = categoryRepository.save(categoryEntity);
		return categoryConverter.toDTO(categoryEntity);
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			categoryRepository.delete(item);
		}
	}

	@Override
	public int totalItem() {
		return 0;
	}

}
