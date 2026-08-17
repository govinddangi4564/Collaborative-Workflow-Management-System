package com.govind.Workflow_Management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.govind.Workflow_Management.Exception.ResourceNotFound;
import com.govind.Workflow_Management.dto.ProjectRequest;
import com.govind.Workflow_Management.entity.Project;
import com.govind.Workflow_Management.repository.ProjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectService {

	private ProjectRepository projectRepo;

	public Project addProject(ProjectRequest dto) {
		Project project = new Project();
		project.setName(dto.name());
		project.setDescription(dto.description());
		return projectRepo.save(project);
	}

	public List<Project> readAll() {
		return projectRepo.findAll();
	}

	public Project findById(Long id) {
		return projectRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Project not found"));
	}

	public Project updateProject(Long id, ProjectRequest dto) {
		Project project = findById(id);
		project.setName(dto.name());
		project.setDescription(dto.description());
		return projectRepo.save(project);
	}

	public void deleteProject(Long id) {
		Project project = findById(id);
		projectRepo.delete(project);
	}
}
