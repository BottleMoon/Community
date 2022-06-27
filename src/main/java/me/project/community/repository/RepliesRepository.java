package me.project.community.repository;

import me.project.community.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepliesRepository extends JpaRepository<Reply, Long> {
    @Query("select r from reply r where r.board.id = :boardId order by r.groupId, r.createdTime")
    List<Reply> findAllByBoardId(Long boardId);

    @Query("select coalesce(max(r.groupId),0) from reply r where r.board.id = :boardId  ")
    Long maxGroupId(Long boardId);
}
