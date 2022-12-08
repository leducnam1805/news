package com.laptrinhjavaweb.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.RoleConverter;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.service.ICRUDService;

@Service
public class RoleService implements ICRUDService<RoleDTO> {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleConverter roleConverter;

	@Override
	public List<RoleDTO> findAll() {
		List<RoleDTO> results = new ArrayList<>();
		List<RoleEntity> entities = roleRepository.findAll();
		for (RoleEntity item : entities) {
			RoleDTO roleDTO = roleConverter.toDTO(item);
			results.add(roleDTO);
		}
		return results;
	}

	@Override
	public List<RoleDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO save(RoleDTO roleDTO) {
		RoleEntity roleEntity = new RoleEntity();
		if (roleDTO.getId() != null) {
			RoleEntity oldRoleEntity = roleRepository.findOne(roleDTO.getId());
			roleEntity = roleConverter.toEntity(roleDTO, oldRoleEntity);
		} else {
			roleEntity = roleConverter.toEntity(roleDTO);
		}
		roleEntity = roleRepository.save(roleEntity);
		return roleConverter.toDTO(roleEntity);
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			roleRepository.delete(item);
		}

	}

	@Override
	public int totalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
