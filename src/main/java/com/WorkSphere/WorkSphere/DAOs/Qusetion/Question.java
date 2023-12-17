package com.WorkSphere.WorkSphere.DAOs.Qusetion;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "questions")
public class Question {
    @SequenceGenerator(
            name = "submission_sequence",
            sequenceName = "submission_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "submission_sequence"
    )
    private long id;

    @ManyToOne
    private UserEntity userEntity;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

}
