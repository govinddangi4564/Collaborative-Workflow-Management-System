package com.govind.Workflow_Management.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import com.govind.Workflow_Management.dto.TicketRevisionResponse;
import com.govind.Workflow_Management.entity.Ticket;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketRevisionService {

	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<TicketRevisionResponse> getHistory(Long ticketId) {
		AuditReader reader = AuditReaderFactory.get(entityManager);

		List<Object[]> rows = reader.createQuery().forRevisionsOfEntity(Ticket.class, false, true)
				.add(AuditEntity.id().eq(ticketId)).getResultList();

		return rows.stream().map(row -> {
			Ticket ticket = (Ticket) row[0];
			DefaultRevisionEntity revisionInfo = (DefaultRevisionEntity) row[1];
			RevisionType revisionType = (RevisionType) row[2];

			LocalDateTime revisionDate = Instant.ofEpochMilli(revisionInfo.getTimestamp())
					.atZone(ZoneId.systemDefault()).toLocalDateTime();

			return new TicketRevisionResponse(revisionInfo.getId(), revisionDate, revisionType.name(),
					ticket.getTitle(), ticket.getStatus() != null ? ticket.getStatus().name() : null,
					ticket.getPriority() != null ? ticket.getPriority().name() : null,
					ticket.getAssignee() != null ? ticket.getAssignee().getId() : null);
		}).collect(Collectors.toList());
	}
}
