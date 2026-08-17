package com.govind.Workflow_Management.dto;

import java.time.LocalDateTime;

public record NotFoundError(LocalDateTime timestamp, int status, String message) {
}
