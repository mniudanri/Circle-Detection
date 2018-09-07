package ip.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author niudanri
 */

/*
 *Class to store List of image 
 *Image and Path of file(s) will be stored in this class
 *
 */
public class ListImage {
    private List<Image> lstImage;
    private List<String> lstPath;
    
    public ListImage(){
        lstImage = new ArrayList<Image>();
        lstPath = new ArrayList<String>();
    }
    
    public List<Image> getListImage(){
        return lstImage;
    }
    
    public List<String> getListPath(){
        return lstPath;
    }
    
    public void setImage(Image img, String path){
        lstImage.add(img);
        lstPath.add(path);
    }
    public int getTotalImage(){
        return lstImage.size();
    }
}
