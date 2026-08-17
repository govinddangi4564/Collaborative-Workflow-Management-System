package com.govind.Workflow_Management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.govind.Workflow_Management.Exception.ResourceNotFound;
import com.govind.Workflow_Management.dto.CommentRequest;
import com.govind.Workflow_Management.entity.Comment;
import com.govind.Workflow_Management.entity.Ticket;
import com.govind.Workflow_Management.entity.User;
import com.govind.Workflow_Management.repository.CommentRepository;
import com.govind.Workflow_Management.repository.TicketRepository;
import com.govind.Workflow_Management.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

	private CommentRepository commentRepo;

	private TicketRepository ticketRepo;

	private UserRepository userRepo;

	public Comment addComment(Long ticketId, CommentRequest dto) {
		Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(() -> new ResourceNotFound("Ticket not found"));

		User author = userRepo.findById(dto.authorId()).orElseThrow(() -> new ResourceNotFound("Author not found"));

		Comment comment = new Comment();
		comment.setContent(dto.content());
		comment.setTicket(ticket);
		comment.setAuthor(author);

		return commentRepo.save(comment);
	}

	public List<Comment> readByTicket(Long ticketId) {
		return commentRepo.findByTicketId(ticketId);
	}

	public void deleteComment(Long id) {
		Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Comment not found"));
		commentRepo.delete(comment);
	}
}
