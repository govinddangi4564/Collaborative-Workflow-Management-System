package com.govind.Workflow_Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govind.Workflow_Management.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByProjectId(Long projectId);
}
