package com.mgolebio.snake_with_ai.gameplay;

import javax.swing.*;
import java.awt.*;

import static com.mgolebio.snake_with_ai.gameplay.Game.SIZE;

public class RenderPanel extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Game game = Game.game;
        // draw board
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        if (Game.over) {
            g.setColor(Color.MAGENTA);
            String endMsg = "game over with result: " + (game.human.tail - 1) + "points";
            g.drawString(endMsg, (int) (getWidth() / 2 - endMsg.length() * 2.5f), (int) game.dim.getHeight() / 4);

        }
        // draw fruit
        g.setColor(new Color(233, 50, 58));
        synchronized (game.fruitGenerator.fruit) {
            g.fillRect(game.fruitGenerator.fruit.x * SIZE, game.fruitGenerator.fruit.y * SIZE, SIZE, SIZE);
        }
        // draw frog
        g.setColor(new Color(82, 177, 147));
        synchronized (game.frog.head){
            g.fillRect(game.frog.head.x * SIZE, game.frog.head.y * SIZE, SIZE, SIZE);
        }
        // draw snake
        g.setColor(new java.awt.Color(103, 79, 160));
        synchronized (game.human.getPoints()) {
            for (int i = 0; i < game.human.getPoints().size(); ++i) {
                g.fillRect(game.human.getPoints().get(i).x * SIZE, game.human.getPoints().get(i).y * SIZE, SIZE, SIZE);
            }
        }
        // draw enemy snake
        g.setColor(new java.awt.Color(252, 196, 4));
        synchronized (game.enemySnake.getPoints()) {
            for (int i = 0; i < game.enemySnake.getPoints().size(); ++i) {
                g.fillRect(game.enemySnake.getPoints().get(i).x * SIZE, game.enemySnake.getPoints().get(i).y * SIZE, SIZE, SIZE);
            }
        }

    }
}
