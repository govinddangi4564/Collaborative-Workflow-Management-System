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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.govind.Workflow_Management.dto.BoardRequest;
import com.govind.Workflow_Management.entity.Board;
import com.govind.Workflow_Management.service.BoardService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/boards")
public class BoardController {

	private BoardService boardService;

	@PostMapping
	public ResponseEntity<Board> addBoard(@RequestParam Long projectId, @RequestBody BoardRequest dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(boardService.addBoard(projectId, dto));
	}

	@GetMapping
	public List<Board> readByProject(@RequestParam Long projectId) {
		return boardService.readByProject(projectId);
	}

	@GetMapping("/{id}")
	public Board findById(@PathVariable Long id) {
		return boardService.findById(id);
	}

	@PutMapping("/{id}")
	public Board updateBoard(@PathVariable Long id, @RequestBody BoardRequest dto) {
		return boardService.updateBoard(id, dto);
	}

	@DeleteMapping("/{id}")
	public String deleteBoard(@PathVariable Long id) {
		boardService.deleteBoard(id);
		return "Board successfully deleted.";
	}
}
