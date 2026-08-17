package com.govind.Workflow_Management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.govind.Workflow_Management.dto.CommentRequest;
import com.govind.Workflow_Management.entity.Comment;
import com.govind.Workflow_Management.service.CommentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets/{ticketId}/comments")
public class CommentController {

	private CommentService commentService;

	@PostMapping
	public ResponseEntity<Comment> addComment(@PathVariable Long ticketId, @RequestBody CommentRequest dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(ticketId, dto));
	}

	@GetMapping
	public List<Comment> readByTicket(@PathVariable Long ticketId) {
		return commentService.readByTicket(ticketId);
	}

	@DeleteMapping("/{commentId}")
	public String deleteComment(@PathVariable Long ticketId, @PathVariable Long commentId) {
		commentService.deleteComment(commentId);
		return "Comment successfully deleted.";
	}
}
