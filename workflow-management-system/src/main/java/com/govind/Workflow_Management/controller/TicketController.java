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

import com.govind.Workflow_Management.dto.TicketRequest;
import com.govind.Workflow_Management.dto.TicketUpdateRequest;
import com.govind.Workflow_Management.entity.Ticket;
import com.govind.Workflow_Management.service.TicketService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

	private TicketService ticketService;

	@PostMapping
	public ResponseEntity<Ticket> addTicket(@RequestBody TicketRequest dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.addTicket(dto));
	}

	@GetMapping
	public List<Ticket> readByBoard(@RequestParam Long boardId) {
		return ticketService.readByBoard(boardId);
	}

	@GetMapping("/{id}")
	public Ticket findById(@PathVariable Long id) {
		return ticketService.findById(id);
	}

	@PutMapping("/{id}")
	public Ticket updateTicket(@PathVariable Long id, @RequestBody TicketUpdateRequest dto) {
		return ticketService.updateTicket(id, dto);
	}

	@DeleteMapping("/{id}")
	public String deleteTicket(@PathVariable Long id) {
		ticketService.deleteTicket(id);
		return "Ticket successfully deleted.";
	}
}
