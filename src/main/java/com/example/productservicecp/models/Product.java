package com.example.productservicecp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private Long price;
    @ManyToOne
    private Category category;
    //private List<String> allowedUSer; // we cant have a list here, remove this.
}

/*
the relation between product and category???
One product can have 1 category
One category can have many products.
 */
