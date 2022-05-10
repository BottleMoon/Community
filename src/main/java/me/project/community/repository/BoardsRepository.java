package me.project.community.repository;


import me.project.community.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardsRepository extends JpaRepository<Board, Long> {
    @Query("select b from board b where b.user.id = :userId ")
    List<Board> findAllByUserId(String userId);
}
