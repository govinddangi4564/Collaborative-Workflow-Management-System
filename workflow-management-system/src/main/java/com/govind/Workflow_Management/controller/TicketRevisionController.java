package com.govind.Workflow_Management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.govind.Workflow_Management.dto.TicketRevisionResponse;
import com.govind.Workflow_Management.service.TicketRevisionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets/{id}/history")
public class TicketRevisionController {

	private TicketRevisionService revisionService;

	@GetMapping
	public List<TicketRevisionResponse> getHistory(@PathVariable Long id) {
		return revisionService.getHistory(id);
	}
}
