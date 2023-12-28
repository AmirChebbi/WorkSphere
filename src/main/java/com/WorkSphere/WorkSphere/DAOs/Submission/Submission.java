package com.WorkSphere.WorkSphere.DAOs.Submission;

import com.WorkSphere.WorkSphere.DAOs.Task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "submssions")
public class Submission {

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

    @OneToOne
    private Task task;

    @Column(name = "submision_date", nullable = false)
    private Date submissionDate;

    @Column(name = "rating")
    private double rating = -1;


    public Submission(long id, Task task, Date submissionDate) {
        this.id = id;
        this.task = task;
        this.submissionDate = submissionDate;
    }
}

