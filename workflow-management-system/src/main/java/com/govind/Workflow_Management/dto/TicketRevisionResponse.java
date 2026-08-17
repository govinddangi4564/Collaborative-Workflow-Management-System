package com.govind.Workflow_Management.dto;

import java.time.LocalDateTime;

public record TicketRevisionResponse(int revisionNumber, LocalDateTime revisionDate, String revisionType,
		String title, String status, String priority, Long assigneeId) {
}
