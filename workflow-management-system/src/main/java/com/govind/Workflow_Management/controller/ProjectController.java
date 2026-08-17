package com.govind.Workflow_Management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.govind.Workflow_Management.dto.ProjectRequest;
import com.govind.Workflow_Management.entity.Project;
import com.govind.Workflow_Management.service.ProjectService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

	private ProjectService projectService;

	@PostMapping
	public ResponseEntity<Project> addProject(@RequestBody ProjectRequest dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.addProject(dto));
	}

	@GetMapping
	public List<Project> readAll() {
		return projectService.readAll();
	}

	@GetMapping("/{id}")
	public Project findById(@PathVariable Long id) {
		return projectService.findById(id);
	}

	@PutMapping("/{id}")
	public Project updateProject(@PathVariable Long id, @RequestBody ProjectRequest dto) {
		return projectService.updateProject(id, dto);
	}

	@DeleteMapping("/{id}")
	public String deleteProject(@PathVariable Long id) {
		projectService.deleteProject(id);
		return "Project successfully deleted.";
	}
}
