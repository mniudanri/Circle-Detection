package ip.controller;

import ip.model.Image;
import java.awt.Color;

/**
 *
 * @author niudanri
 */
public class ImageProcessor {
    private Image image;
    private int threshold;
    
    public ImageProcessor(Image image){
        this.image = image;
        threshold = 60;
    }
    
    public void doProcess(){
        grayscaling(); //1. Grayscale
        edgedetection(); //2. Edge detection
        binarizing(); //3. Binarize
    }
    
    public void grayscaling(){
        int[][] img = image.getImageOri();
        
        for(int i = 0; i < img.length; i++){
            for(int j = 0; j < img[0].length; j++){
                Color c = new Color(img[i][j]);

                int alpha = c.getAlpha(); 
                int red = c.getRed(); 
                int green = c.getGreen(); 
                int blue = c.getBlue(); 

                int gray =(int)(red * 0.299 + green * 0.587 + blue * 0.114);  //grayscale
                
                image.setImageOutput(i, j, gray);
            }
        }
    }
    
    public void edgedetection(){
        PrewittEdgeDetector prewitt = new PrewittEdgeDetector();
        prewitt.detectEdges(image);
    }
    
    public void binarizing(){
        
        OtsuThresholder otsu = new OtsuThresholder();
        
        double threshold = otsu.getOtsuThreshold(image);
//        System.out.println("threshold: "+threshold);
        
        int max = 0;
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++){
                if(image.getImageOutput(i, j) < threshold){
                    image.setImageOutput(i, j, 0);
                }
                else if(image.getImageOutput(i, j) >= threshold){
                    image.setImageOutput(i, j, 255);
                }
            }
        }
    }
}
