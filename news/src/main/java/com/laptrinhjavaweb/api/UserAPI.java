package com.laptrinhjavaweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.api.output.UserOutput;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.Impl.UserService;

@CrossOrigin
@RestController
public class UserAPI {

	@Autowired
	private UserService userService;

	/**
	 * Show all data
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@GetMapping(value = "/user")
	public UserOutput showAllUser(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		UserOutput result = new UserOutput();
		if (page != null && limit != null) {
			result.setPage(page);
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setListResult(userService.findAll(pageable));
			result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
		} else {
			result.setListResult(userService.findAll());
		}
		return result;
	}

	/**
	 * Method create data
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/user")
	public UserDTO createUser(@RequestBody UserDTO model) {
		return (UserDTO) userService.save(model);
	}

	/**
	 * Method update data by {id}
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/user/{id}")
	public UserDTO updateUser(@RequestBody UserDTO model, @PathVariable("id") long id) {
		model.setId(id);
		return (UserDTO) userService.save(model);
	}

	/**
	 * Method delete data by {ids}
	 * 
	 * @param ids
	 */
	@DeleteMapping(value = "/user")
	public void deleteUser(@RequestBody long[] ids) {
		userService.delete(ids);
	}

}
