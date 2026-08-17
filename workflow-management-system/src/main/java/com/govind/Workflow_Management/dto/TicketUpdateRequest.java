package com.govind.Workflow_Management.dto;

import com.govind.Workflow_Management.entity.TicketPriority;
import com.govind.Workflow_Management.entity.TicketStatus;
import com.govind.Workflow_Management.entity.TicketType;

/**
 * {@code version} must be the value the client last read the ticket with
 * (e.g. from the GET /tickets/{id} response). The service writes it back
 * onto the managed entity before saving, so Hibernate's
 * {@code UPDATE ... WHERE id = ? AND version = ?} only succeeds if nobody
 * else has saved a newer version in between.
 */
public record TicketUpdateRequest(String title, String description, TicketType type, TicketStatus status,
		TicketPriority priority, Long assigneeId, Long version) {
}
