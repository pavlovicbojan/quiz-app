package com.pavlovic.appquiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private User user;

    @Column
    private int score;

    @Column
    private LocalDateTime date = LocalDateTime.now();

}
