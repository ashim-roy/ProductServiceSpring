package com.example.productservicecp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    // writing custom generator...
    /*
    @GeneratedValue(generator = "generator_name")
    @GenericGenerator(name = "generator_name", strategy = "uudi2")
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
