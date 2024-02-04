package com.WorkSphere.WorkSphere.DAOs.Task;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tasks")
public class Task {

    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "doer_email", referencedColumnName = "Email")
    private UserEntity doer;

    private Date submissionDeadline;

    private boolean isSubmitted = false;

}
