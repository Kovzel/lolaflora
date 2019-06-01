package com.kovzel.entity.Dto;

import java.util.Date;

public class OrderDto {
    private Integer id;
    private Date date;
    private double price;

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }
}
