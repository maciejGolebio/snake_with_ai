package com.mgolebio.snake_with_ai.gameplay;

import java.awt.*;
import java.util.ArrayList;

public class EnemySnake extends Snake{
    public int direction;
    public Point head;
    public ArrayList<Point> points = new ArrayList<>();


    @Override
    public void move() {
        points.add(new Point(head.x, head.y));
        head = new Point(head.x, head.y - 1);
    }

    @Override
    public ArrayList<Point> getPoints() {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void setFruitHasBeenEaten() {

    }
}
