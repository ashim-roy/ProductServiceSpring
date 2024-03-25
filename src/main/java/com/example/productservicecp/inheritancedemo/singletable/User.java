package com.example.productservicecp.inheritancedemo.singletable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "UserTablePerClass")
@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // creates a single table for all entity classes
@DiscriminatorColumn(name = "user_type",
        discriminatorType = DiscriminatorType.INTEGER)
// creates a column to differentiate between entity classes in the single table by using a discriminator column

@DiscriminatorValue(value = "0") // sets the value of the discriminator column for the User entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
}

// st_user will create a single table and all teh attributes of student and mentor will be here.
//st_user will have a column user_type which will have value 1 for mentor and 2 for student. discriminator column is used to differentiate between entity classes in the single table by using a discriminator column
// one disadvantage of single table is that it will have null values for the attributes which are not present in the entity class.
// for example, mentor will have rating and student will have psp and batch. so, for student, rating will be null and for mentor, psp and batch will be null.
// also, single table will have all the attributes of all the entity classes. so, if we have 10 entity classes, single table will have all the attributes of all the 10 entity classes.
// so, single table is not a good approach if we have many entity classes. in that case, we should use table per class or joined table approach.
// another disadvantage is that single table will have null values for the attributes which are not present in the entity class. so, it will have many null values.
// sparsh and null values are not good for the performance of the application. so, single table is not a good approach if we have many null values.