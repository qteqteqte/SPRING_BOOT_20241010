package com.example.demo.model.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAll();
    // List<Article> findAll();
}
