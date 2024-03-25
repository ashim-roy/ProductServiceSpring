package com.example.productservicecp.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_mentor") // specifies the name of the entity table in the single table strategy as mentor in the database schema
@DiscriminatorValue(value = "1") // sets the value of the discriminator column for the Mentor entity in the single table to 1
public class Mentor extends User {
    private int rating;

}
