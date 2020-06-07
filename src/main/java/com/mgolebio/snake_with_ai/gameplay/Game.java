package com.mgolebio.snake_with_ai.gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game implements ActionListener {
    public static final int DELAY = 50;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 700;
    public static final int SIZE = 10;
    public static Game game;
    public Human human;
    public FruitGenerator fruitGenerator;
    ArrayList<Snake> gameObjects;

    public Dimension dim;
    public JFrame jframe;
    public RenderPanel renderPanel;
    public Timer timer = new Timer(DELAY, this);
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public int direction = DOWN, score, time;
    public static volatile boolean  over = false;
    ScheduledExecutorService executorService;

    public Game() {
        human = new Human(direction);
        gameObjects = new ArrayList<>();
        gameObjects.add(human);
        fruitGenerator = new FruitGenerator(gameObjects);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setResizable(false);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(human);
        executorService = Executors.newScheduledThreadPool(2);
        startGame();

    }

    private void startGame() {

        executorService.scheduleAtFixedRate(human, 0, DELAY, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(fruitGenerator, 0, DELAY, TimeUnit.MILLISECONDS);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();
        if (over) {
            executorService.shutdown();
            timer.stop();

        }
    }

    public static void main(String[] args) {
        game = new Game();
    }
}
