package com.kovzel.repository;

import com.kovzel.entity.Flower;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FlowerRepository extends CrudRepository<Flower, Integer> {
    default ArrayList<Flower> getPopularFlowers() {
        ArrayList<Flower> flowers = new ArrayList<>();
        for (Flower flower : this.findAll()) {
            if (flowers.size() < 3) {
                flowers.add(flower);
            } else {
                Flower minPopFlower = getMinPopFlower(flowers);
                if (flower.getNumberOfSold() > minPopFlower.getNumberOfSold()) {
                    flowers.remove(minPopFlower);
                    flowers.add(flower);
                }
            }
        }
        return flowers;
    }

    default Flower getMinPopFlower(ArrayList<Flower> flowers) {
        Flower minPopFlower = null;
        for (Flower popFlower : flowers) {
            if (minPopFlower != null) {
                if (popFlower.getNumberOfSold() < minPopFlower.getNumberOfSold()) {
                    minPopFlower = popFlower;
                }
            } else {
                minPopFlower = popFlower;
            }
        }
        return minPopFlower;
    }
}

