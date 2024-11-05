package com.example.hdvdemojpa.repository;

import com.example.hdvdemojpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
