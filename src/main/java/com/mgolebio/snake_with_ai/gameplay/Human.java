package com.mgolebio.snake_with_ai.gameplay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Human extends Snake implements KeyListener {
    public Human(int direction) {
        this.direction = direction;
        head = new Point(10, 10);
        points.add(new Point(head.x, head.y));

    }

    @Override
    public void move() {
        points.add(new Point(head.x, head.y));
        if (direction == Game.UP) {
            if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
                head.y -=1; //new Point(head.x, head.y - 1);
            } else {
                Game.over = true;
            }

        } else if (direction == Game.DOWN) {
            if (head.y + 1 < 66 && noTailAt(head.x, head.y + 1)) {
                head.y +=1 ;//new Point(head.x, head.y + 1);
            } else {
                Game.over = true;
            }
        } else if (direction == Game.LEFT) {
            if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
                head.x -= 1;//new Point(head.x - 1, head.y);
            } else {
                Game.over = true;
            }
        } else if (direction == Game.RIGHT) {
            if (head.x + 1 < 79 && noTailAt(head.x + 1, head.y)) {
                head.x+=1;// new Point(head.x + 1, head.y);
            } else {
                Game.over = true;
            }
        }
        if (points.size() > super.tail) {
            points.remove(0);
        }

    }

    @Override
    public List<Point> getPoints() {
        return points;
    }


    @Override
    public void run() {
        move();
    }

    public boolean noTailAt(int x, int y) {
        Point head = new Point(x, y);
        for (int i = 0; i < points.size(); ++i) {
            if (points.get(i).equals(head)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();


        if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != Game.RIGHT) {
            direction = Game.LEFT;
        }

        if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != Game.LEFT) {
            direction = Game.RIGHT;
        }

        if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != Game.DOWN) {
            direction = Game.UP;
        }

        if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != Game.UP) {
            direction = Game.DOWN;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public synchronized void setFruitHasBeenEaten() {
        super.tail += 1;
    }

    @Override
    public synchronized void setFruitHasBeenEaten(int x) {
        super.tail+=x;
    }
}
