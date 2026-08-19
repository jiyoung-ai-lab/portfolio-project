package com.waveapp.smcalendarlite.common;

import android.app.Application;

public class GlobalParameter extends  Application {

		// 화면구분(임시)
		  private String gFlag;
		//calendar 값 return 처리용
		  private String gCalendarStr;

	  
		  public String getFlagString() 
		  { 
		    return gFlag; 
		  } 
		 
		  public void setFlagString(String globalString) 
		  { 
		    this.gFlag = globalString; 
		  } 

		  public String getCalendarString() 
		  { 
		    return gCalendarStr; 
		  } 
		 
		  public void setCalendarString(String globalString) 
		  { 
		    this.gCalendarStr = globalString; 
		  } 
}
