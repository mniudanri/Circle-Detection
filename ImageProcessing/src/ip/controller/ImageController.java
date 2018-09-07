/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.controller;

import ip.model.Image;
import ip.model.ListImage;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author niudanri
 */
public class ImageController {
    public ListImage image;
    
    public ImageController(){
        image = new ListImage();
    }
    
    public void pathToImage(String path){
        File file = new File(path);
        BufferedImage bi = null;
        
        try {
            bi = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(ImageController.class.getName()).log(Level.SEVERE, null, ex);
        }
                       
        ImageIcon imgicon = new ImageIcon(bi);
        Image img = new Image(imgicon.getIconWidth(), imgicon.getIconHeight()); 
        int height = imgicon.getIconHeight(); 
        int width = imgicon.getIconWidth();
        
        PixelGrabber pxlgrabber = new PixelGrabber(imgicon.getImage(), 0, 0, width, height, false);
        pxlgrabber.startGrabbing();
        
        int[] pixel1d;
        
        try{
            if(pxlgrabber.grabPixels()){
                pixel1d = (int[])pxlgrabber.getPixels();
                
                int wpx = 0;
                int hpx = 0;
                
                for(int i =0;i<pixel1d.length;i++){
                     int temp = pixel1d[i];
                     
                     img.setImageOri(wpx, hpx, temp);
                     img.setImageOutput(wpx, hpx, temp);
                     
                     wpx++;
                     
                     if (wpx == width){
                        wpx=0;
                        hpx++;
                    }
                }
                image.setImage(img, path);
                
                BufferedImage sourceImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                    for(int i =0; i< width; i++){
                        for(int j =0; j< height;j++){
                            sourceImage.setRGB(i, j, img.getImageOri()[i][j]);
                        }
                    }

                try{
                    File outputfile = new File("imageAwal.jpg");
                    ImageIO.write(sourceImage, "jpg", outputfile);
                    }catch(IOException ie){}

            }
        }catch(InterruptedException ex){}
    }
    
    public ListImage getListImage(){
        return image;
    }
}

