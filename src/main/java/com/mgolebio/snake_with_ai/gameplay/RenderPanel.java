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
        if(Game.over)
        {   g.setColor(Color.MAGENTA);
            String endMsg = "game over with result: "+ game.human.tail +"points";
            g.drawString(endMsg, (int) (getWidth() / 2 - endMsg.length() * 2.5f), (int) game.dim.getHeight() / 4);
            return;
        }
        // draw snake
        g.setColor(Color.ORANGE);
        for (int i = 0; i < game.human.getPoints().size(); ++i) {
            g.fillRect(game.human.getPoints().get(i).x * SIZE, game.human.getPoints().get(i).y * SIZE, SIZE, SIZE);
        }
        // draw fruit
        g.setColor(Color.white);
        for (int i = 0; i < game.fruitGenerator.getPoints().size(); ++i) {
            g.fillRect(game.fruitGenerator.getPoints().get(i).x * SIZE, game.fruitGenerator.getPoints().get(i).y * SIZE, SIZE, SIZE);
        }
    }


}
