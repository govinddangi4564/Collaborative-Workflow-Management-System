package com.govind.Workflow_Management.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.govind.Workflow_Management.dto.NotFoundError;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<NotFoundError> notfoundException(ResourceNotFound e) {

		NotFoundError error = new NotFoundError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	// Thrown when a client saves a Ticket with an outdated @Version - the
	// Lost Update Anomaly the interview challenge is about. Returned as 409
	// instead of a silent overwrite or a raw 500.
	@ExceptionHandler(ObjectOptimisticLockingFailureException.class)
	public ResponseEntity<NotFoundError> optimisticLockException(ObjectOptimisticLockingFailureException e) {

		NotFoundError error = new NotFoundError(LocalDateTime.now(), HttpStatus.CONFLICT.value(),
				"This ticket was modified by someone else. Please reload and try again.");

		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
}
