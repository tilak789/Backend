package com.example.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "institutes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Institute  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instituteid;
    @NotNull(message = "institute name shouldn't be null")
    private String institutename;
    private String institutedescription;
    private String imageurl;
    private String instituteaddress;
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
    private String mobile;
    @Email(message = "invalid email address")
    private String email;
    @OneToMany
    @JoinColumn(name="instituteid")
   private Set<Course> courses= new HashSet<>();


}
