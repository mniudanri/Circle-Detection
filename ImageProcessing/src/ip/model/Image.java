package ip.model;

import java.awt.image.BufferedImage;

/**
 *
 * @author niudanri
 */
/*
 *Class to store pixel of image
 */

public class Image {
    private int[][] imageOri; 
    private int[][] imageOutput;
    private int width;
    private int height;
    
    public Image(int w, int h){
        this.imageOutput = new int[w][h];
        this.imageOri = new int[w][h];
    }
    
    public int[][] getImageOri(){
        return imageOri;
    }
    
    public void setImageOri(int w, int h, int pixel){
        this.imageOri[w][h] = pixel;
        width = w;
        height = h;
    }
    
    public int[][] getImageOutput(){
        return imageOutput;
    }
    
    public int getImageOutput(int w, int h){
        return imageOutput[w][h];
    }
    
    public void setImageOutput(int w, int h, int pixel){
        this.imageOutput[w][h] = pixel;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public static BufferedImage getBufferedImage(int[][] img, int type){
        
        int w = img.length;
        int h = img[0].length;
        BufferedImage image = new BufferedImage(w, h, type);
        
        int wpx = 0, hpx = 0;
        for(int i =0; i< w; i++){
            for(int j =0; j< h; j++){
                int pix = ((0 & 0xFF) << 24) |
                    ((img[i][j] & 0xFF) << 16) |
                    ((img[i][j] & 0xFF) << 8)  |
                    ((img[i][j] & 0xFF) << 0);
                image.setRGB(i, j, pix);
            }
        }
        return image;
        
    }
    
    public static int[] get1DImage(int[][] img2D){
        int[] onedpixel = new int[img2D.length * img2D[0].length];
        int idx = 0;
        
        for(int i = 0; i < img2D.length; i++){
            for(int j = 0; j < img2D[0].length; j++){
//                Color c = new Color(img2D[i][j]);
//                onedpixel[i] = c.getRGB();
                onedpixel[idx] = img2D[i][j];
                
                idx++;
            }
        }
        return onedpixel;
    }
}
