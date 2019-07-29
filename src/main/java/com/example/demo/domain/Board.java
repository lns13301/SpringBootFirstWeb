package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Table @Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long index;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @Builder
    public Board(String title, String content, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
