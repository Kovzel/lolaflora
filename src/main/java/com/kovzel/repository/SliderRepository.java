package com.kovzel.repository;

import com.kovzel.entity.Order;
import com.kovzel.entity.Slider;
import org.springframework.data.repository.CrudRepository;

public interface SliderRepository extends CrudRepository<Slider, Integer> {
}