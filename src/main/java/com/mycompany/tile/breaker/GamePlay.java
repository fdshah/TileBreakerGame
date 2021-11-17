/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tile.breaker;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author fenil
 */
public class GamePlay extends JPanel implements KeyListener,ActionListener {
    
    private boolean isPlay = false;
    private int score = 0;
    private int totalTiles = 21;
    private Timer timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballPositionX = 120;
    private int ballPositionY = 350;
    private int ballXDirection = -1;
    private int ballYDirection = -2;
    private MapGenerator map;
    
    public GamePlay() {
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D) g);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score, 590, 30);
        
        g.setColor(Color.yellow);
        g.fillRect(playerX, 550, 100, 8);
        
        //Ball color
        g.setColor(Color.green);
        g.fillOval(ballPositionX, ballPositionY, 20, 20);
        
        if(ballPositionY>570)
        {
            isPlay = false;
            ballXDirection = 0;
            ballYDirection = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over Score: "+score, 190, 300);
            
            //restart game
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to start: ", 190, 340);
        }
        
        if(totalTiles==0) {
            isPlay = false;
            ballYDirection = -2;
            ballXDirection = -1;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over Score: "+score, 190, 300);
        }
        
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(isPlay) {
            if(new Rectangle(ballPositionX, ballPositionY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
                ballYDirection = -ballYDirection;
            }
            A:
            for(int i=0; i<map.map.length; i++) {
                for(int j=0; j<map.map[0].length; j++) {
                    if(map.map[i][j]>0) {
                        int tileX = j*map.tileWidth + 80;
                        int tileY = i*map.tileHeight + 50;
                        int tilesWidth = map.tileWidth;
                        int tilesHeight = map.tileHeight;
                        
                        Rectangle rect = new Rectangle(tileX, tileY, tilesWidth, tilesHeight);
                        Rectangle ballrect = new Rectangle(ballPositionX, ballPositionY,20,20);
                        Rectangle tilesrect = rect;
                        if(ballrect.intersects(tilesrect)) {
                            map.setTileValue(0, i, j);
                            totalTiles--;
                            score+=5;
                            if(ballPositionX+19 <= tilesrect.x || ballPositionX+1 >= tilesrect.x+tilesWidth) {
                                ballXDirection =- ballXDirection;
                            } else {
                                ballYDirection = -ballYDirection;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPositionX+=ballXDirection;
            ballPositionY+=ballYDirection;
            if(ballPositionX<0) {
                ballXDirection = -ballXDirection;
            }
            if(ballPositionY<0) {
                ballYDirection = -ballYDirection;
            }
            if(ballPositionX > 670) {
                ballXDirection = -ballXDirection;
            }
        }
        repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!isPlay) {
                ballPositionX = 120;
                ballPositionY = 350;
                ballXDirection = -1;
                ballYDirection = -2;
                score = 0;
                playerX = 310;
                totalTiles = 21;
                map = new MapGenerator(3,7);
                repaint();
            }
        }
    }
    
    public void moveRight(){
        isPlay = true;
        playerX+=20;
    }
    
    public void moveLeft(){
        isPlay = true;
        playerX-=20;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
