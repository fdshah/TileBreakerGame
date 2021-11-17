/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tile.breaker;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
/**
 *
 * @author fenil
 */
public class MapGenerator {
    public int map[][];
    public int tileWidth;
    public int tileHeight;
    
    public MapGenerator(int row, int column) {
        map = new int[row][column];
        for(int []map1 : map) {
            for(int j=0; j<map[0].length; j++) {
                map1[j] = 1;
            }
        }
        tileWidth = 540/column;
        tileHeight = 150/row;
        
    }
    
    public void draw(Graphics2D g) {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                if(map[i][j]>0) {
                    g.setColor(Color.red);
                    g.fillRect(j*tileWidth+80, i*tileHeight+50, tileWidth, tileHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*tileWidth+80, i*tileHeight+50, tileWidth, tileHeight);
                }
            }
        }
    }
    
    public void setTileValue(int value, int row, int column) {
        map[row][column] = value;
    }
}
