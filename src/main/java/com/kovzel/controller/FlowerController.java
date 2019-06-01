package com.kovzel.controller;

import com.kovzel.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/flowers")
public class FlowerController {

    private final FlowerRepository flowerRepository;

    @Autowired
    public FlowerController(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }
}
