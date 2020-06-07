package com.mgolebio.snake_with_ai.gameplay;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class FruitGenerator implements GameObject {
    private final ArrayList<Snake>  objects;
    private ArrayList<Point> fruits = new ArrayList<>();
    public AtomicBoolean isEaten = new AtomicBoolean(false);
    Random random = new Random();

    public FruitGenerator(ArrayList<Snake> objects) {
        this.objects = objects;
        fruits.add(new Point());
        move();
    }

    @Override
    public void move() {

        synchronized (objects) {
            Set<Point> points = new HashSet<>();
            for (GameObject go : objects) {
                points.addAll(go.getPoints());
            }
            int x = random.nextInt((Game.WIDTH - 4*Game.SIZE) / Game.SIZE);
            int y = random.nextInt((Game.HEIGHT - 2*Game.SIZE) / Game.SIZE);
            boolean repeat = false;
            while (true) {
                for (Point point : points) {
                    if (point.x == x) {
                        x = random.nextInt((Game.WIDTH - 4*Game.SIZE) / Game.SIZE);
                        repeat = true;
                    }
                    if (point.y == y) {
                        y = random.nextInt((Game.HEIGHT - 2*Game.SIZE) / Game.SIZE);
                        repeat = true;
                    }

                }
                if (!repeat) break;
                repeat = false;
            }
            fruits.get(0).x = x;
            fruits.get(0).y = y;
            isEaten.set(false);
        }

    }


    @Override
    public ArrayList<Point> getPoints() {
        return fruits;
    }

    public boolean collision() {
        for (int i = 0; i <objects.size() ; i++) {
            if(objects.get(i).head.x == getPoints().get(0).x && objects.get(i).head.y == getPoints().get(0).y){
                objects.get(i).setFruitHasBeenEaten();
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        if (!isEaten.get()) {
            isEaten.set(collision());
        } else {
            move();
        }
    }
}
