package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Project extends JFrame {

    static int x = 100;
    static int y = 100;
    static final int pixel = 10;
    static int points = 0;

    static JLabel icon;
    static JLabel scoreLabel;
    static JLabel[] dots = new JLabel[6];
    static JFrame frame;

    public static void main(String[] args) {
        main_window();
    }

    public static void main_window() {
        frame = new JFrame("Змейка V1.0");
        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon snake = new ImageIcon("Z:\\javanet\\scoped_dir50396_1460610613\\project\\src\\project\\snake.png");
        icon = new JLabel(snake);
        icon.setBounds(x, y, snake.getIconWidth(), snake.getIconHeight());
        frame.add(icon);
        scoreLabel = new JLabel("Points: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setBounds(390, 10, 100, 30);
        frame.add(scoreLabel);

        Random rand = new Random();
        for (int i = 0; i < dots.length; i++) {
            JLabel dot = new JLabel();
            dot.setOpaque(true);
            dot.setBackground(Color.RED);
            dot.setBounds(rand.nextInt(460), rand.nextInt(340), 10, 10);
            frame.add(dot);
            dots[i] = dot;
        }

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                move(e);
            }
        });

        frame.setVisible(true);
    }

    public static void move(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            y -= pixel;
        } else if (key == KeyEvent.VK_DOWN) {
            y += pixel;
        } else if (key == KeyEvent.VK_LEFT) {
            x -= pixel;
        } else if (key == KeyEvent.VK_RIGHT) {
            x += pixel;
        }

        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > 470) x = 470;
        if (y > 360) y = 360;

        icon.setLocation(x, y);
        Rectangle snakeBounds = icon.getBounds();
        for (int i = 0; i < dots.length; i++) {
            if (dots[i] != null && dots[i].isVisible()) {
                Rectangle dotBounds = dots[i].getBounds();
                if (snakeBounds.intersects(dotBounds)) {
                    dots[i].setVisible(false);
                    points++;
                    scoreLabel.setText("Points: " + points);
                }
            }
        }
    }
}
