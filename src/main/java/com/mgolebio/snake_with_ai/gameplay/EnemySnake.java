package com.mgolebio.snake_with_ai.gameplay;


import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class EnemySnake extends Snake {

    public List<Point> human;
    private Random random = new Random();

    public EnemySnake(int direction, List<Point> human) {
        this.direction = direction;
        this.head = new Point(69, 56);
        this.points.add(new Point(head.x, head.y));
        this.human = Collections.synchronizedList(human);
    }

    public int lossFunction(int hipoteseDirection) {
        if (hipoteseDirection == Game.UP) {
            if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
                Point tmp = new Point(head.x, head.y - 1);
                for (int i = 0; i < human.size(); ++i) {
                    if (tmp.equals(human.get(i))) {

                        return 1000 * 1000;
                    }
                }
                synchronized (Game.game.fruitGenerator.fruit) {
                    return Math.abs(Game.game.fruitGenerator.fruit.x - head.x)
                            + Math.abs((head.y - 1) - Game.game.fruitGenerator.fruit.y);
                }
            } else {
                return 1000 * 1000;
            }
        } else if (hipoteseDirection == Game.DOWN) {

            if (head.y + 1 <= Game.BOARD_HEIGHT && noTailAt(head.x, head.y + 1)) {

                Point tmp = new Point(head.x, head.y + 1);
                for (int i = 0; i < human.size(); ++i) {
                    if (tmp.equals(human.get(i))) {

                        return 1000 * 1000;
                    }
                }
                synchronized (Game.game.fruitGenerator.fruit) {
                    return Math.abs(Game.game.fruitGenerator.fruit.x - head.x)
                            + Math.abs((head.y + 1) - Game.game.fruitGenerator.fruit.y);
                }
            } else {

                return 1000 * 1000;
            }
        } else if (hipoteseDirection == Game.LEFT) {

            if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
                Point tmp = new Point(head.x - 1, head.y);
                for (int i = 0; i < human.size(); ++i) {
                    if (tmp.equals(human.get(i))) {
                        return 1000 * 1000;
                    }
                }
                synchronized (Game.game.fruitGenerator.fruit) {
                    return Math.abs((head.x - 1) - Game.game.fruitGenerator.fruit.x) + Math.abs(head.y - Game.game.fruitGenerator.fruit.y);
                }
            } else {
                return 1000 * 1000;
            }
        } else if (hipoteseDirection == Game.RIGHT) {
            if (head.x + 1 <= Game.BOARD_WIDTH && noTailAt(head.x + 1, head.y)) {
                Point tmp = new Point(head.x + 1, head.y);
                for (int i = 0; i < human.size(); ++i) {
                    if (tmp.equals(human.get(i))) {
                        return 1000 * 1000;
                    }
                }
                synchronized (Game.game.fruitGenerator.fruit) {
                    return Math.abs((head.x + 1) - Game.game.fruitGenerator.fruit.x) + Math.abs(head.y - Game.game.fruitGenerator.fruit.y);
                }
            } else {

                return 1000 * 1000;
            }
        }

        return 1000 * 1000;
    }

    public boolean noTailAt(int x, int y) {
        for (Point point : points) {
            if (point.equals(new Point(x, y))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void move() {
        int best = 100000000;
                for (int i = 0; i < 4; i++) {
            int eval = lossFunction(i);
            if (eval < best) {
                best = eval;
                direction = i;
            }
        }

        points.add(new Point(head.x, head.y));
        synchronized (head) {
            //System.out.println("in sn");
            if (direction == Game.UP) {
                if (head.y - 1 >= 0) {
                    head.y -= 1; // = new Point(head.x, head.y - 1);
                } else {
                    setAfterCollision();
                }

            } else if (direction == Game.DOWN) {
                if (head.y + 1 <= Game.BOARD_HEIGHT) {
                    head.y += 1; //= new Point(head.x, head.y + 1);
                } else {
                    setAfterCollision();
                }
            } else if (direction == Game.LEFT) {
                if (head.x - 1 >= 0) {
                    head.x -= 1;// = new Point(head.x - 1, head.y);
                } else {
                    setAfterCollision();
                }
            } else if (direction == Game.RIGHT) {
                if (head.x + 1 <= Game.BOARD_WIDTH) {
                    head.x += 1;// = new Point(head.x + 1, head.y);
                } else {
                    setAfterCollision();
                }
            }
            if (points.size() > super.tail) {
                points.remove(0);
            }
        }
    }

    public void setAfterCollision(){
        Game.game.human.setFruitHasBeenEaten(10);
        super.tail=1;
        points.clear();
        head.x = random.nextInt(Game.BOARD_WIDTH);
        head.y = random.nextInt(Game.BOARD_HEIGHT);
        points.add(new Point(head.x,head.y));
    }

    @Override
    public List<Point> getPoints() {
        return points;
    }

    @Override
    public void run() {
        move();
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
