package me.project.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.project.community.domain.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BoardResponseDto {
    Long id;
    String title;
    String content;
    String userid;
    LocalDateTime createdDate;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userid = board.getUser().getId();
        this.createdDate = board.getCreatedDate();
    }
}