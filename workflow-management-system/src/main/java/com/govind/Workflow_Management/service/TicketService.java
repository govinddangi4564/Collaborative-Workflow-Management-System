package com.govind.Workflow_Management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.govind.Workflow_Management.Exception.ResourceNotFound;
import com.govind.Workflow_Management.dto.TicketRequest;
import com.govind.Workflow_Management.dto.TicketUpdateRequest;
import com.govind.Workflow_Management.entity.Board;
import com.govind.Workflow_Management.entity.Ticket;
import com.govind.Workflow_Management.entity.TicketStatus;
import com.govind.Workflow_Management.entity.User;
import com.govind.Workflow_Management.repository.BoardRepository;
import com.govind.Workflow_Management.repository.TicketRepository;
import com.govind.Workflow_Management.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketService {

	private TicketRepository ticketRepo;
	private BoardRepository boardRepo;
	private UserRepository userRepo;

	public Ticket addTicket(TicketRequest dto) {
		Board board = boardRepo.findById(dto.boardId()).orElseThrow(() -> new ResourceNotFound("Board not found"));

		Ticket ticket = new Ticket();
		ticket.setTitle(dto.title());
		ticket.setDescription(dto.description());
		ticket.setType(dto.type());
		ticket.setPriority(dto.priority());
		ticket.setStatus(TicketStatus.TODO);
		ticket.setBoard(board);

		if (dto.assigneeId() != null) {
			User assignee = userRepo.findById(dto.assigneeId())
					.orElseThrow(() -> new ResourceNotFound("Assignee not found"));
			ticket.setAssignee(assignee);
		}

		return ticketRepo.save(ticket);
	}

	public List<Ticket> readByBoard(Long boardId) {
		return ticketRepo.findByBoardId(boardId);
	}

	public Ticket findById(Long id) {
		return ticketRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Ticket not found"));
	}

	public Ticket updateTicket(Long id, TicketUpdateRequest dto) {
		Ticket ticket = findById(id);

		ticket.setTitle(dto.title());
		ticket.setDescription(dto.description());
		ticket.setType(dto.type());
		ticket.setStatus(dto.status());
		ticket.setPriority(dto.priority());

		if (dto.assigneeId() != null) {
			User assignee = userRepo.findById(dto.assigneeId())
					.orElseThrow(() -> new ResourceNotFound("Assignee not found"));
			ticket.setAssignee(assignee);
		} else {
			ticket.setAssignee(null);
		}

		if (dto.version() != null) {
			ticket.setVersion(dto.version());
		}

		return ticketRepo.saveAndFlush(ticket);
	}

	public void deleteTicket(Long id) {
		Ticket ticket = findById(id);
		ticketRepo.delete(ticket);
	}
}
