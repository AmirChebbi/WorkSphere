package com.WorkSphere.WorkSphere.DAOs.Performance;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "performance")
public class Performance {
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

    @Column(name = "overall_rating", nullable = false)
    private double overallRating;

    @OneToOne
    private UserEntity userEntity;

}
