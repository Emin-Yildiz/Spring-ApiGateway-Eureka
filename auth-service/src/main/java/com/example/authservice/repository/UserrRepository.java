package com.example.authservice.repository;

import com.example.authservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserrRepository extends JpaRepository<User, UUID> {

    Optional<User> findByName(String name);
}
