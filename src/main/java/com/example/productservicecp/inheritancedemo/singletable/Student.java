package com.example.productservicecp.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_student")
@DiscriminatorValue(value = "2") // sets the value of the discriminator column for the Student entity in the single table to 2
public class Student extends User {
    private int psp;
    private String batch;
}
