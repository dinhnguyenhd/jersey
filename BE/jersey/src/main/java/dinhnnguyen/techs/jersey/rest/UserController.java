package dinhnnguyen.techs.jersey.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import dinhnnguyen.techs.jersey.entitys.User;
import dinhnnguyen.techs.jersey.request.form.FLogin;
import dinhnnguyen.techs.jersey.request.form.FUser;
import dinhnnguyen.techs.jersey.services.UserService;

@Controller
@Path("/users")
@CrossOrigin(origins = "http://localhost:8090")
public class UserController {

	@Autowired
	private UserService userService;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> fetchAll() {
		return userService.list();
	}

	@GET
	@Path("/list/{page}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> showMore(@PathParam("page") int page) {
		return userService.showMore(page);
	}

	@GET
	@Path("/search/{email}/{page}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> searchByEmail(@PathParam("email") String email, @PathParam("page") int page) {
		return userService.searchByEmail(email, page);
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		User user = this.userService.getUserById(id);
		return Response.ok().entity(user).build();
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(FUser user) {
		User news = userService.create(user);
		return Response.ok(news).build();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Login(FLogin login) {

		User news = userService.login(login.getEmail(), login.getPassword());
		return Response.ok(news).build();
	}

	@PUT
	@Path("/update")
	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(FUser user) {
		userService.update(user);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/delete/{id}")
	public Response delete(@PathParam("id") long id) {
		userService.delete(id);
		return Response.status(202).entity("User deleted successfully !!").build();
	}
}