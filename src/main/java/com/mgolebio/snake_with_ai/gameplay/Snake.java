package com.mgolebio.snake_with_ai.gameplay;

import java.awt.*;
import java.util.ArrayList;

public abstract class Snake implements GameObject{
    public int direction;
    public Point head;
    public ArrayList<Point> points = new ArrayList<>();
    public int tail=1;
    public abstract void setFruitHasBeenEaten();

}
