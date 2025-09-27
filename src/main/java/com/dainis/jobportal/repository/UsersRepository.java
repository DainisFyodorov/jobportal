package com.dainis.jobportal.repository;

import com.dainis.jobportal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
