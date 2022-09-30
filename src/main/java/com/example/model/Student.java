package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentid;
    private String firstname;
    private String lastname;
    private String fathername;
    private String mothername;
    private String gender;
    private String houseno;
    private String streetname;
    private String areaname;
    private Long pincode;
    private String state;
    private String nationality;
    @Email(message = "Email should be valid")
    private String emailid;
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
    private String phonenumber;
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
    private String alternativephonenumber;
    @Min(18)
    @Max(60)
    private int age;
}
