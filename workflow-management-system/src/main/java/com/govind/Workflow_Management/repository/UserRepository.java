package com.govind.Workflow_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govind.Workflow_Management.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
