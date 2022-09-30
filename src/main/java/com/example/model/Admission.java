package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "Admission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admissionid;
    @Column(name = "studentname")
    @NotBlank(message = "Give name")
    private String studentname;
    @Column(name = "studentdob")
    @Temporal(TemporalType.DATE)
    private Date studentdob;
    @Column(name = "address")

    private String address;
    @Column(name = "mobile")
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
    private String mobile;
    @Column(name = "age")
    @Min(18)
    @Max(60)
    private int age;
    private int instituteid;
    private int courseid;
}
