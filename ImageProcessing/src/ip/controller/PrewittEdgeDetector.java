package ip.controller;

import ip.model.Image;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author niudanri
 */

public class PrewittEdgeDetector {
    double theta[][];
    
    public void detectEdges(Image img){
        
        int[][] data = img.getImageOutput();
        int height = data[0].length; 
        int width = data.length; 
        int[][] xmask = { {-1,0,1},
                                 {-1,0,1},
                                 {-1,0,1} };
        int[][] ymask = { {1,1,1},
                                 {0,0,0},
                                 {-1,-1,-1} };
        
        int[][] map = intensityMap(data);
        int[][] gradX = new int[width][height];
        int[][] gradY = new int[width][height];
        BufferedImage magGrad = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        
        for(int x = 0; x<width; x++){
            for(int y = 0; y<height; y++){
                //Checks if on boundary of image
                if(x==0 || x==width-1 ||
                    y==0 || y==height-1){	
                        gradX[x][y] = 0;
                        gradY[x][y] = 0;
                    }
                else{
                    int sumX = 0, sumY = 0; 
                    for(int i=-1; i<2; i++){
                            for(int j=-1; j<2; j++){
                                    sumX+=map[x+j][y+i] * xmask[i+1][j+1] / 3;
                                    sumY+=map[x+j][y+i] * ymask[i+1][j+1] / 3;
                            }
                    }
                    gradX[x][y] = sumX;
                    gradY[x][y] = sumY; 
                    int hyp = (int)Math.hypot(gradX[x][y], gradY[x][y]); 
                    if(hyp > 255){
                        magGrad.setRGB(x, y, 255);
                        
                        img.setImageOutput(x, y, hyp);
                    }
                    else{
                        magGrad.setRGB(x, y, hyp);
                        
                        img.setImageOutput(x, y, hyp);
                    }
                }
            }
        } 
    }
     
    private static int[][] intensityMap(int[][] data) {
        int[][] returnArray = new int[data.length][data[0].length];
        
        for(int i = 0; i<data.length; i++){
            for(int j = 0; j<data[0].length; j++){
                returnArray[i][j] = data[i][j];
            }
        }
        return returnArray;
    }
}

