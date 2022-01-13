package me.project.community.repository;


import me.project.community.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardsRepository extends JpaRepository<Board,Long> {
}
