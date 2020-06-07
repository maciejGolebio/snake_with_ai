package com.mgolebio.snake_with_ai.gameplay;

import java.awt.*;
import java.awt.Point;
import java.util.ArrayList;

public interface GameObject extends Runnable {
    void move();
    ArrayList<Point> getPoints();
}
