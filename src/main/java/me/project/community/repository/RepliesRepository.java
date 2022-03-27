package me.project.community.repository;

import me.project.community.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepliesRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where r.board.id = :boardId")
    List<Reply> findAllByBoardId(Long boardId);
}
