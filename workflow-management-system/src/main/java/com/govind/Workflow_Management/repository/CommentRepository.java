package com.govind.Workflow_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govind.Workflow_Management.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByTicketId(Long ticketId);
}
