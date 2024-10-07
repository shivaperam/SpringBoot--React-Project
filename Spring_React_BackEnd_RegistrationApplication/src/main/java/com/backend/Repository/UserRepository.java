package com.backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.backend.entity.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
}
