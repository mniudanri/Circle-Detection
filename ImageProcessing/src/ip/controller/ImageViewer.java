/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.controller;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import ip.model.Image;
import javax.swing.JLabel;

/**
 *
 * @author niudanri
 */
public class ImageViewer {
    public void viewImage(int[][] img, JLabel label, int type){
        BufferedImage bi = Image.getBufferedImage(img, type);
        label.setText("");
        label.setIcon(new ImageIcon(bi.getScaledInstance(label.getWidth(), label.getHeight(), bi.SCALE_DEFAULT)));
    }
}
