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
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
    @Column
    private Long correctAnswerId;
    @ManyToOne
    private Topic topic;

}
