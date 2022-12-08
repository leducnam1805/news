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

import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.service.Impl.RoleService;

@CrossOrigin
@RestController
public class RoleAPI {

	@Autowired
	private RoleService roleService;

	/**
	 * Show all data new
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@GetMapping(value = "/role")
	public List<RoleDTO> showNew() {
		return roleService.findAll();
	}

	/**
	 * Method create data new
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/role")
	public RoleDTO createRole(@RequestBody RoleDTO model) {
		return (RoleDTO) roleService.save(model);
	}

	/**
	 * Method update data category by {id}
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/role/{id}")
	public RoleDTO updaterole(@RequestBody RoleDTO model, @PathVariable("id") long id) {
		model.setId(id);
		return (RoleDTO) roleService.save(model);
	}

	/**
	 * Method delete data new by {ids}
	 * 
	 * @param ids
	 */
	@DeleteMapping(value = "/role")
	public void deleteNew(@RequestBody long[] ids) {
		roleService.delete(ids);
	}
}
