package me.project.community.dto;


import lombok.Getter;

@Getter
public class ReplyRequestDto {
    Long id;
    String content;
    Long parentsId;

}
