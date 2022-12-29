package dinhnnguyen.techs.jersey.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;

import dinhnnguyen.techs.jersey.entitys.User;
import dinhnnguyen.techs.jersey.mapper.UserMapper;
import dinhnnguyen.techs.jersey.repositorys.UserRepository;
import dinhnnguyen.techs.jersey.request.form.FUser;

@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {
	int pageSize = 2;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;

	private List<FUser> users = new ArrayList<FUser>();

	@Cacheable(value = "customerInfo")
	public List<User> list() {
		return userRepository.findAll();
	}

	public User getUserById(long id) {
		Optional<User> us = userRepository.findById(id);
		return us.get() != null ? us.get() : null;
	}

	public User create(FUser form) {
		User newUser = this.userMapper.toEntity(form);
		return userRepository.save(newUser);

	}

	public void update(FUser form) {
		Optional<User> us = userRepository.findById(form.getId());
		if (us.get() != null) {
			User user = us.get();
			user.setEmail(form.getEmail());
			user.setName(form.getName());
			// user.setPassword(form.getPassword());
			this.userRepository.save(user);
		}
	}

	public void delete(long id) throws NotFoundException {
		Optional<User> us = userRepository.findById(id);
		if (us.get() == null)
			throw new NotFoundException("Record not found ");
		else {
			userRepository.deleteById(id);
		}
	}

	@Override
	public User login(String name, String password) {
		User user = userRepository.login(name, password);
		return user;
	}

	@Override
	public List<User> showMore(int page) {
		PageRequest pageInfo = PageRequest.of(page, pageSize);
		return userRepository.findAll(pageInfo).toList();

	}

	@Override
	public List<User> searchByEmail(String email, int page) {
		PageRequest pageInfo = PageRequest.of(page, pageSize);
		return userRepository.searchByEmail(email, pageInfo);
	}

}