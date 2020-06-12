package com.mgolebio.snake_with_ai.gameplay;

import lombok.Setter;

import java.awt.*;
import java.util.Random;

@Setter
public class Frog implements GameObject {
    public final Point head;
    private Snake human;
    private Snake bot;
    Random random = new Random();
    public boolean isEaten = false;

    public Frog(){
        this.head = new Point();
        head.x = random.nextInt(Game.BOARD_WIDTH);
        head.y = random.nextInt(Game.BOARD_HEIGHT);
    }


    @Override
    public void move() {
        int direction = random.nextInt(4);
        if (direction == Game.UP) {
            if (head.y - 1 >= 0) {
                head.y -= 1;             }
        } else if (direction == Game.DOWN) {
            if (head.y + 1 < Game.BOARD_HEIGHT) {
                head.y +=1 ;            }
        } else if (direction == Game.LEFT) {
            if (head.x - 1 >= 0) {
                head.x -= 1;            }
        } else if (direction == Game.RIGHT) {
            if (head.x + 1 < 79) {
                head.x+=1;
            }
        }
    }


    @Override
    public void run() {
        if(!isEaten){
            isEaten = collision();
            if(!isEaten)
                move();
        }else {
            isEaten = false;
            head.x = random.nextInt(Game.BOARD_WIDTH);
            head.y = random.nextInt(Game.BOARD_HEIGHT);
        }
    }

    private boolean collision() {
        synchronized (bot.points) {
            for (Point b : bot.points) {
                if (b.equals(this.head)) {
                    bot.setFruitHasBeenEaten(3);
                    return true;
                }
            }
        }
        synchronized (human.points) {
            for (Point h : human.points) {
                if (h.equals(this.head)) {
                    human.setFruitHasBeenEaten(3);
                    return true;
                }
            }
        }
        return false;
    }
}
