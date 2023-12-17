package com.WorkSphere.WorkSphere.DAOs.Feedback;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "feedback")
public class FeedBack {
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
    private UserEntity sender;

    private String message;

}
