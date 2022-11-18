package com.pavlovic.appquiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "topic")
    private List<Question> questions;

    @OneToMany(mappedBy = "topic")
    private List<Result> results;
}
