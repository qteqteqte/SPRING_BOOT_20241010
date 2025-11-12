package com.example.demo.model.repository;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.*;

import com.example.demo.model.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAll();
    Page<Board> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    // List<Article> findAll();
}