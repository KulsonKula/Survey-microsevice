package com.example.survey.repository;

import com.example.survey.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findById(long id);

    Users findByUsernameAndPassword(String username, String Password);

}
