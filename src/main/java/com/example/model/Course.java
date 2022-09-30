package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseid;
    @NotNull(message = "username shouldn't be null")
    private String coursename;
    private String coursedescription;
    private int courseduration;
    private String coursetime;
    private int nostudents;
    @OneToMany
    @JoinColumn(name="course-id")
    private Set<Student> students = new HashSet<>();



}
