package com.example.productservicecp.inheritancedemo.mappedsuper;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// @Entity  // @Entity is used to mark a Java class as a persistent entity.
@Getter
@Setter
@MappedSuperclass //create tables only for child classes. It is used to define the inheritance strategy for the entity class. It is used to provide the mapping information for the inheritance hierarchy... I
public class User {
    @Id  // @Id annotation in JPA serves the primary purpose of identifying a field or property as the primary key of an entity class
    @GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValue annotation is used to define generation strategies for the values of primary keys.
    private Long id;
    private String name;
    private String email;
    private String password;
}
