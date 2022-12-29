package dinhnnguyen.techs.jersey.services;

import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.springframework.data.domain.Page;

import dinhnnguyen.techs.jersey.entitys.User;
import dinhnnguyen.techs.jersey.request.form.FUser;

public interface UserService {

	public List<User> list();

	public User getUserById(long id);

	public User create(FUser form);

	public User login(String name, String password);

	public void update(FUser user);

	public void delete(long id) throws NotFoundException;

	public List<User> showMore(int page);
	
	public List<User> searchByEmail(String email, int page);
	
	
	
	
}