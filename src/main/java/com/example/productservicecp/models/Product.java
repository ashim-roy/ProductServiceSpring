package com.example.productservicecp.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Product extends BaseModel {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private Category category;
    private List<String> allowedUSer;
}
