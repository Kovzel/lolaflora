package com.kovzel.entity;

import javax.persistence.*;

@Entity
@Table(name = "flower")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String srcImage;
    private String name;
    private String description;
    private int numberOfSold;
    private int price;

    public int getNumberOfSold() {
        return numberOfSold;
    }

    public void setNumberOfSold(int numberOfSold) {
        this.numberOfSold = numberOfSold;
    }

    public String getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(String srcImage) {
        this.srcImage = srcImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}