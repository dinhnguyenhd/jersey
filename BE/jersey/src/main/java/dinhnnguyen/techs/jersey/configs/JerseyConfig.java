package dinhnnguyen.techs.jersey.configs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import dinhnnguyen.techs.jersey.rest.UserController;

@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(UserController.class);
		register(CorsFilter.class);
		
		
	}
}