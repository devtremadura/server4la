package com.devtremadura.cuatrola.repository;

import com.devtremadura.cuatrola.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
