package userAdmin.org.service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import userAdmin.org.service.Model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    
}
