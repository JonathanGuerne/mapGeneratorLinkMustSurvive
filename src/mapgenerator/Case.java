package mapgenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author jonathan.guerne
 */
public class Case {

    int x, y, w, h;

    Image img;
    String code;
    boolean isEnemmiSpawn = false;

    public Case(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void paint(Graphics g, ImageObserver obs) {

        if (!"0".equals(code)) {
            if (img != null) {
                g.drawImage(img, x, y, obs);
            }
        }

        if (isEnemmiSpawn) {
            g.fillRect(x + 8, y + 8, 16, 16);
        }
        g.drawRect( x,  y,  w,  h);
    }

    public boolean isTouched(int x, int y) {
        if (x >= this.x && x <= this.x + w) {
            if (y >= this.y && y <= this.y + h) {
                return true;
            }
        }
        return false;
    }

}
