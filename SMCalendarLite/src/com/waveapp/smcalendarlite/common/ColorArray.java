package com.waveapp.smcalendarlite.common;

import android.content.Context;
import android.content.res.Resources;

import com.waveapp.smcalendarlite.R;

public final class ColorArray  {
	
	private final Context mCtx;
	
	private Integer[] colorArr ;
    
    
    public ColorArray(Context ctx) {
        this.mCtx = ctx;
    }

	public Integer [] getColorArray()
	{
        Resources res = mCtx.getResources();
        
        colorArr = new Integer[] {
        	//1)red °è¿­
       		res.getColor(R.color.crimson)
        	,res.getColor(R.color.red1)       		
       		,res.getColor(R.color.salmon) 
       		,res.getColor(R.color.coral)
       		,res.getColor(R.color.sandybrown) 
       		,res.getColor(R.color.orange1)
       		,res.getColor(R.color.chocolate)    				
       		,res.getColor(R.color.sienna)  
       		,res.getColor(R.color.yellow1)
       		,res.getColor(R.color.goldenrod)
       		,res.getColor(R.color.tan) 	
       		,res.getColor(R.color.darkkhaki)
       		,res.getColor(R.color.yellowgreen)
       		,res.getColor(R.color.green1)   
       		,res.getColor(R.color.lightseagreen)        		
       		,res.getColor(R.color.greenyellow)
       		,res.getColor(R.color.forestgreen)  
       		,res.getColor(R.color.steelblue)
       		,res.getColor(R.color.skyblue)
       		,res.getColor(R.color.darkturquoise)
       		,res.getColor(R.color.deepskyblue)        
       		,res.getColor(R.color.blue1) 
       		,res.getColor(R.color.royalblue)
       		,res.getColor(R.color.slateblue)       		
       		,res.getColor(R.color.darkblue1)
       		,res.getColor(R.color.mediumpurple)
       		,res.getColor(R.color.darkorchid)
       		,res.getColor(R.color.mediumorchid)
       		,res.getColor(R.color.plum)
       		,res.getColor(R.color.violet)        		 
       		,res.getColor(R.color.puple1)       		
       		,res.getColor(R.color.gainsboro) 
       		,res.getColor(R.color.darkgray1) 

       		
           };		
        
		return colorArr;
	}
   
}
