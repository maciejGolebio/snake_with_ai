package com.mgolebio.snake_with_ai.gameplay;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Snake implements GameObject{
    public int direction;
    public Point head;
    public final List<Point> points = Collections.synchronizedList(new ArrayList<Point>());
    public int tail=1;
    public abstract  void setFruitHasBeenEaten();

}
