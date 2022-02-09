package me.project.community.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.project.community.dto.BoardRequestDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "board")
@NoArgsConstructor
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Long id;

    @Column(name = "title")
    private  String title;

    @Column(name = "content")
    private  String content;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Builder
    public Board(BoardRequestDto boardRequestDto){
        this.id = boardRequestDto.getId();
        this.title = boardRequestDto.getTitle();
        this. content = boardRequestDto.getContent();
        this.createdDate = LocalDateTime.now();
    }
}
