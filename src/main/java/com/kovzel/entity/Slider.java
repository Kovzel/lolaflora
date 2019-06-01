package com.kovzel.entity;

import javax.persistence.*;

@Entity
@Table(name = "slider")
public class Slider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String srcImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(String srcImage) {
        this.srcImage = srcImage;
    }
}
