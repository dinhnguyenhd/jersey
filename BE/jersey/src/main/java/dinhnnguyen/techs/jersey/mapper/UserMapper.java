package dinhnnguyen.techs.jersey.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import dinhnnguyen.techs.jersey.entitys.User;
import dinhnnguyen.techs.jersey.request.form.FUser;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

	FUser toForm(User user);

	User toEntity(FUser form);

}
