package test.startgame;

import eatfood.FoodManager;

import java.awt.*;

public class TestFoodManager {

    public static void main(String[] args) {

        FoodManager fm =
                new FoodManager();

        Point food =
                fm.spawnFood();

        System.out.println(
                "Food Position: "
                        + food
        );
    }
}