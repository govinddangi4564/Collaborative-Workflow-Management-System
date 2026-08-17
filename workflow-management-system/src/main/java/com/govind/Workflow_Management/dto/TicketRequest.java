package com.govind.Workflow_Management.dto;

import com.govind.Workflow_Management.entity.TicketPriority;
import com.govind.Workflow_Management.entity.TicketType;

public record TicketRequest(String title, String description, TicketType type, TicketPriority priority,
		Long boardId, Long assigneeId) {
}
