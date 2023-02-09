package com.example.diningreview.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.diningreview.Model.User;

public interface UserRepository extends CrudRepository<User,Long>{
    Optional<User> findByDisplayName(String displayName);
}
