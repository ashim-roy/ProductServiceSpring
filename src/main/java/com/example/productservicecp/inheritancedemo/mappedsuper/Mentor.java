package com.example.productservicecp.inheritancedemo.mappedsuper;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity  // @Entity is used to mark a Java class as a persistent entity.
public class Mentor extends User{
    private int rating;

}
