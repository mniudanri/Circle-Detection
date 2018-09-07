/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.controller;

import ip.model.Image;
/**
 *
 * @author niudanri
 */

public class OtsuThresholder {
 
    private int histData[];
    private int maxLevelValue;
    private int threshold;

    public OtsuThresholder()
    {
        histData = new int[256];
    }
    
    public double getOtsuThreshold(Image image) {
        
        int[] grayScaleValues = Image.get1DImage(image.getImageOutput());
        
        int ptr;
 
		ptr = 0;
		while (ptr < histData.length) histData[ptr++] = 0;
		 
		ptr = 0;
		maxLevelValue = 0;
		while (ptr < grayScaleValues.length)
		{
			int h = 0xFF & grayScaleValues[ptr];
			histData[h] ++;
			if (histData[h] > maxLevelValue) maxLevelValue = histData[h];
			ptr ++;
		}

		 
		int total = grayScaleValues.length;

		float sum = 0;
		for (int t=0 ; t<256 ; t++) sum += t * histData[t];

		float sumB = 0;
		int wB = 0;
		int wF = 0;

		float varMax = 0;
		threshold = 0;

		for (int t=0 ; t<256 ; t++)
		{
			wB += histData[t];					 
			if (wB == 0) continue;

			wF = total - wB;						 
			if (wF == 0) break;

			sumB += (float) (t * histData[t]);

			float mB = sumB / wB;				 
			float mF = (sum - sumB) / wF;		 

			 
			float varBetween = (float)wB * (float)wF * (mB - mF) * (mB - mF);	

			 
			if (varBetween > varMax) {
				varMax = varBetween;
				threshold = t;
			}
		}

		return threshold;
 
    }
}
