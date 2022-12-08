package com.laptrinhjavaweb.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.ICRUDService;

@Service
public class UserService implements ICRUDService<UserDTO> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> results = new ArrayList<>();
		List<UserEntity> entities = userRepository.findAll();
		for (UserEntity item : entities) {
			UserDTO userDTO = userConverter.toDTO(item);
			results.add(userDTO);
		}
		return results;
	}

	@Override
	public List<UserDTO> findAll(Pageable pageable) {
		List<UserDTO> results = new ArrayList<>();
		List<UserEntity> entities = userRepository.findAll(pageable).getContent();
		for (UserEntity item : entities) {
			UserDTO userDTO = userConverter.toDTO(item);
			results.add(userDTO);
		}
		return results;
	}

	@Override
	public UserDTO save(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		if (userDTO.getId() != null) {
			UserEntity oldUserEntity = userRepository.findOne(userDTO.getId());
			userEntity = userConverter.toEntity(userDTO, oldUserEntity);
		} else {
			userEntity = userConverter.toEntity(userDTO);
		}
		userEntity = userRepository.save(userEntity);
		return userConverter.toDTO(userEntity);
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			userRepository.delete(item);
		}
	}

	@Override
	public int totalItem() {
		return (int) userRepository.count();
	}

}
