package com.example.productservicecp.inheritancedemo.tableperclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "UserTablePerClass")
@Entity(name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // InheritanceType.TABLE_PER_CLASS: This strategy creates a separate table for each class in the hierarchy.
// creates table for all entity classes
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
}
