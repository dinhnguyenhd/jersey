package dinhnnguyen.techs.jersey.repositorys;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dinhnnguyen.techs.jersey.entitys.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = ?1 and u.password = ?2")
	public User login(String email, String password);

	@Query("SELECT u FROM User u WHERE u.email like %?1%")
	public List<User> searchByEmail(String email, Pageable pageable);

}
