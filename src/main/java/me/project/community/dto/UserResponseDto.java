package me.project.community.dto;

import lombok.Getter;
import lombok.Setter;
import me.project.community.domain.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {
    String id;
    LocalDateTime createdTime;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.createdTime = user.getCreateTime();
    }
}
