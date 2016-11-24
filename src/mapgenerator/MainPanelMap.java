/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author jonathan.guerne
 */
public class MainPanelMap extends javax.swing.JPanel {

    final int nbLigne = 18;

    Image imgTemp = null;
    String CodeTemp = "0";

    boolean spawnMob = false;

    int layer = 0;
    int x = 1;
    int y = 1;

    Case layer0[][] = new Case[nbLigne][nbLigne];
    Case layer1[][] = new Case[nbLigne][nbLigne];
    Case layer2[][] = new Case[nbLigne][nbLigne];

    Case listLayer[][][] = {layer0, layer1, layer2};

    boolean displayLayer1 = true;
    boolean displayLayer2 = true;

    /**
     * Creates new form MainPanel
     */
    public MainPanelMap() {
        initComponents();

        //initialisation des layer
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbLigne; j++) {
                layer0[i][j] = (new Case(i * 32, j * 32, 32, 32));
                layer0[i][j].code = "0";

                layer1[i][j] = new Case(i * 32, (j * 32), 32, 32);
                layer1[i][j].code = "0";

                layer2[i][j] = new Case((i * 32), (j * 32), 32, 32);
                layer2[i][j].code = "0";
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(Util.loadImage("plan"), 0, 0, mainframe.getWidth(), mainframe.getHeight(), this);

        drawLayer(layer0, g);

        if (displayLayer1) {
            drawLayer(layer1, g);
        }
        if (displayLayer2) {
            drawLayer(layer2, g);
        }
    }

    public void drawLayer(Case layer[][], Graphics g) {
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbLigne; j++) {
                layer[i][j].paint(g, this);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

//        Case layerTemp[][] = listLayer[layer];
//        for (int i = 0; i < nbLigne; i++) {
//            for (int j = 0; j < nbLigne; j++) {
//                if (layerTemp[i][j].isTouched(evt.getX(), evt.getY())) {
//                    Case c = layerTemp[i][j];
//                    if (!spawnMob) {
//                        layerTemp[i][j].img = imgTemp;
//                        layerTemp[i][j].code = CodeTemp;
//                    } else {
//                        layerTemp[i][j].isEnemmiSpawn = !layerTemp[i][j].isEnemmiSpawn;
//                    }
//                    repaint();
//                }
//            }
//        }

    }//GEN-LAST:event_formMouseClicked

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        Case layerTemp[][] = listLayer[layer];
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbLigne; j++) {
                if (layerTemp[i][j].isTouched(evt.getX(), evt.getY())) {
                    Case c = layerTemp[i][j];
                    if (evt.getButton() == MouseEvent.BUTTON3) {
                        if (!spawnMob) {
                            c.img = null;
                            c.code = "0";
                        } else {
                            c.isEnemmiSpawn = false;
                        }
                    } else {
                        if (!spawnMob) {
                            layerTemp[i][j].img = imgTemp;
                            layerTemp[i][j].code = CodeTemp;
                        } else {
                            layerTemp[i][j].isEnemmiSpawn = !layerTemp[i][j].isEnemmiSpawn;
                        }
                    }
                    repaint();
                }
            }
        }
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}