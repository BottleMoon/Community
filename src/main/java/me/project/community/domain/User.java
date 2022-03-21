package me.project.community.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
