package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ICRUDService<T> {
	
	List<T> findAll();
	
	List<T> findAll(Pageable pageable);

	T save(T t);

	void delete(long[] ids);

	int totalItem();

}
