package me.project.community.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Board {
    @Id
    private Long id;

    private String title;

    private String content;
}
