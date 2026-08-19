package com.waveapp.smcalendarlite.util;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ViewFlipper;

public class SMViewFlipper extends ViewFlipper {
    public SMViewFlipper(Context context) 
    {
        super(context);
    }
 
    public SMViewFlipper(Context context, AttributeSet attrs) 
    {
        super(context, attrs);
    }
 
    @Override
    protected void onDetachedFromWindow() 
    {
        if (Integer.parseInt(Build.VERSION.SDK) >= 7) 
        {                          
            try 
            {                                  
                super.onDetachedFromWindow();                          
            } 
             catch (IllegalArgumentException e) 
            {                                  
                Log.e("SMViewFlipper", "Android issue 6191: http://code.google.com/p/android/issues/detail?id=6191");                          
            } 
            finally 
            {                                  
                super.stopFlipping();                          
            }                  
        }  
        else 
        {                          
            super.onDetachedFromWindow();                  
        }  
    }
}