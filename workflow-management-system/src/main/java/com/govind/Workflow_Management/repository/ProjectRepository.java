package com.govind.Workflow_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govind.Workflow_Management.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
