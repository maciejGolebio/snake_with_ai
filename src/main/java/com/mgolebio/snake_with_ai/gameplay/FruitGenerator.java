package com.mgolebio.snake_with_ai.gameplay;

import lombok.Setter;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
@Setter
public class FruitGenerator implements GameObject {
    //private List<Snake> objects;
    private Snake human;
    private Snake bot;
    public final Point fruit;
    public volatile boolean isEaten = true;
    Random random = new Random();

    public FruitGenerator() {
        fruit = new Point();
        fruit.x = random.nextInt(78);
        fruit.y = random.nextInt(66);
    }


//    public void setObjects(ArrayList<Snake> objects) {
//        this.objects = Collections.synchronizedList(objects);
//    }

    @Override
    public void move() {

        Set<Point> points = new HashSet<>();
        synchronized (human.points){
            points.addAll(human.points);
        }
        synchronized (bot.points){
            points.addAll(bot.points);
        }
        int x = random.nextInt(79);
        int y = random.nextInt(66);
        boolean repeat = false;
        while (true) {
            for (Point point : points) {
                if (point.x == x) {
                    x = random.nextInt((Game.WIDTH - 4 * Game.SIZE) / Game.SIZE);
                    repeat = true;
                }
                if (point.y == y) {
                    y = random.nextInt((Game.HEIGHT - 2 * Game.SIZE) / Game.SIZE);
                    repeat = true;
                }

            }
            if (!repeat) break;
            repeat = false;
        }
        synchronized (fruit) {
            fruit.x = x;
            fruit.y = y;
        }
        isEaten=false;

    }


    @Override
    public ArrayList<Point> getPoints() {
        return null;
    }

    public boolean collision() {
        synchronized (bot.head){
            if(bot.head.equals(fruit)){
                bot.setFruitHasBeenEaten();
                return true;
            }
        }
        synchronized (human.head){
            if(human.head.equals(fruit)){
                human.setFruitHasBeenEaten();
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        if (!isEaten) {
            isEaten=(collision());
        } else {
            move();
        }
    }
}
