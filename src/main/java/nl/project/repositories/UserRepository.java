package nl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.project.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUserName(String username);

}