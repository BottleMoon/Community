package me.project.community.dto;

import lombok.Getter;
import me.project.community.domain.Board;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    Long id;
    String title;
    String content;
    LocalDateTime createdDate;

    public BoardResponseDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdDate = board.getCreatedDate();
    }
}
