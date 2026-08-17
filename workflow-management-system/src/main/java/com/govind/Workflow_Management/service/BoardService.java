package com.govind.Workflow_Management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.govind.Workflow_Management.Exception.ResourceNotFound;
import com.govind.Workflow_Management.dto.BoardRequest;
import com.govind.Workflow_Management.entity.Board;
import com.govind.Workflow_Management.entity.Project;
import com.govind.Workflow_Management.repository.BoardRepository;
import com.govind.Workflow_Management.repository.ProjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {

	private BoardRepository boardRepo;
	private ProjectRepository projectRepo;

	public Board addBoard(Long projectId, BoardRequest dto) {
		Project project = projectRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFound("Project not found"));

		Board board = new Board();
		board.setName(dto.name());
		board.setProject(project);
		return boardRepo.save(board);
	}

	public List<Board> readByProject(Long projectId) {
		return boardRepo.findByProjectId(projectId);
	}

	public Board findById(Long id) {
		return boardRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Board not found"));
	}

	public Board updateBoard(Long id, BoardRequest dto) {
		Board board = findById(id);
		board.setName(dto.name());
		return boardRepo.save(board);
	}

	public void deleteBoard(Long id) {
		Board board = findById(id);
		boardRepo.delete(board);
	}
}
