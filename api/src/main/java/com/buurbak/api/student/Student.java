package com.buurbak.api.student;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity @Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private long id;
    private String name;
    private String email;
    @Transient
    private int age;
    private LocalDate dateOfBirth;

    public Student(String name, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
