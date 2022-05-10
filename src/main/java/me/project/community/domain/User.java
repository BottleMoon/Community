package me.project.community.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "createdtime")
    private LocalDateTime createTime;

    @Builder
    public User(String id, String password, LocalDateTime createdTime) {
        this.id = id;
        this.password = password;
        this.createTime = createdTime;
    }

}
