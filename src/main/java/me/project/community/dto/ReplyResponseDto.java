package me.project.community.dto;


import lombok.Getter;
import lombok.Setter;
import me.project.community.domain.Reply;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyResponseDto {
    Long id;
    String content;
    LocalDateTime createdTime;
    Long parentsId;
    String userId;
    Long boardId;

    public ReplyResponseDto(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
        this.createdTime = reply.getCreatedTime();
        this.parentsId = reply.getReply() == null ? 0 : reply.getReply().getId();
        this.userId = reply.getUser().getId();
        this.boardId = reply.getBoard().getId();
    }

}
