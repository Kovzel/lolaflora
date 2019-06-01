package com.kovzel.controller;

import com.kovzel.entity.Flower;
import com.kovzel.entity.Order;
import com.kovzel.repository.FlowerRepository;
import com.kovzel.repository.OrderRepository;
import com.kovzel.repository.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping
public class MainController {
    private ArrayList<Flower> lastFlowers;
    private ArrayList<Flower> shoppingCart;
    private final FlowerRepository flowerRepository;
    private final OrderRepository orderRepository;
    private final SliderRepository sliderRepository;

    @Autowired
    public MainController(
            FlowerRepository flowerRepository, OrderRepository orderRepository, SliderRepository sliderRepository) {
        this.flowerRepository = flowerRepository;
        this.orderRepository = orderRepository;
        this.sliderRepository = sliderRepository;
        shoppingCart = new ArrayList<>();
        lastFlowers = new ArrayList<>();
    }

    @GetMapping("")
    public String getAllFlowers(Model model) {
        model.addAttribute("flowers", flowerRepository.findAll());
        model.addAttribute("cart", shoppingCart.size());
        model.addAttribute("sliders", sliderRepository.findAll());
        return "index";
    }

    @GetMapping("/payment")
    public String getPayment(Model model) {
        int price = 0;
        for (Flower flower : shoppingCart) {
            price += flower.getPrice();
        }
        model.addAttribute("cartSize", shoppingCart.size());
        model.addAttribute("price", price);
        model.addAttribute("cast", shoppingCart);
        model.addAttribute("order", new Order());
        return "bying";
    }

    @GetMapping("/history")
    public String getHistory(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "profile";
    }

    @PostMapping("/history")
    public String addOrder(Model model, @ModelAttribute Order order) throws IllegalAccessException {
        if (!shoppingCart.isEmpty()) {
            int price = 0;
            ArrayList<Flower> flowers = new ArrayList<>();
            for (Flower flower : shoppingCart) {
                boolean coincidence = false;
                for (Flower flower1 : flowers) {
                    if (flower.getId().equals(flower1.getId())) {
                        flower1.setNumberOfSold(flower1.getNumberOfSold() + 1);
                        coincidence = true;
                    }
                }
                if (!coincidence) {
                    flower.setNumberOfSold(flower.getNumberOfSold() + 1);
                    flowers.add(flower);
                }
                price += flower.getPrice();
            }
            for (Flower flower : flowers) {
                flowerRepository.save(flower);
            }
            shoppingCart.clear();
            order.setPrice(price);
            order.setStatus("not ready");
            order.setFlowers(shoppingCart);
            order.setDate(new Date());
            orderRepository.save(order);
        }
        model.addAttribute("orders", orderRepository.findAll());
        return "profile";
    }

    @GetMapping("/search/")
    String getFlower(@RequestParam String text, Model model) throws IllegalAccessException {
        Integer id = null;
        for (Flower flower : flowerRepository.findAll()) {
            if (text.equals(flower.getName())) {
                id = flower.getId();
            }
        }
        Flower flower = flowerRepository.findById(id).get();
        if (id != null) {
            return getPreparedTemplate(model, flower, false);
        } else {
            throw new IllegalAccessException("This flower doesn't exist");
        }
    }

    @GetMapping("/flowers/{id}")
    public String getFlower(@PathVariable("id") int id, Model model) {
        Flower flower = flowerRepository.findById(id).get();
        return getPreparedTemplate(model, flower, false);
    }

    @PostMapping("/flowers/{id}")
    public String putMethod(@PathVariable int id, Model model) {
        Flower flower = flowerRepository.findById(id).get();
        shoppingCart.add(flower);
        return getPreparedTemplate(model, flower, true);
    }

    private String getPreparedTemplate(Model model, Flower flower, boolean isRedirect) {
        if (!isRedirect) {
            for (Flower flowerView : lastFlowers) {
                if (flowerView.getId().equals(flower.getId())) {
                    lastFlowers.remove(flowerView);
                    break;
                }
            }
            lastFlowers.add(flower);
            if (lastFlowers.size() == 4) {
                lastFlowers.remove(1);
            }
        }
        model.addAttribute("popFlowers", flowerRepository.getPopularFlowers());
        model.addAttribute("lastFlowers", lastFlowers);
        model.addAttribute("flower", flower);
        model.addAttribute("cart", shoppingCart.size());
        return isRedirect
                ? "redirect:/flowers/" + flower.getId()
                : "card";
    }
}