package com.govind.Workflow_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govind.Workflow_Management.entity.Ticket;
import com.govind.Workflow_Management.entity.TicketStatus;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByBoardId(Long boardId);

	List<Ticket> findByAssigneeId(Long assigneeId);

	List<Ticket> findByBoardIdAndStatus(Long boardId, TicketStatus status);
}
