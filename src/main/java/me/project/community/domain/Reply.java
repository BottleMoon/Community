package me.project.community.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "reply")
@Getter
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdTime;

    private Long groupId;

    //대댓글
    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Reply reply;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Reply(Long id, String content, LocalDateTime createdTime, Long groupId, Reply reply, User user, Board board) {
        this.id = id;
        this.content = content;
        this.createdTime = createdTime;
        this.groupId = groupId;
        this.reply = reply;
        this.user = user;
        this.board = board;
    }
}
