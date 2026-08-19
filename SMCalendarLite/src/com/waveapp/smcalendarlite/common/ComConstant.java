package com.waveapp.smcalendarlite.common;

import android.content.Context;
import android.view.View;

/*
 * °øÅëº¯¼ö ¸ðÀ½..
 * -.±âº» ¿µ¾î: ÇÑ±ÛÀÇ °æ¿ì ÇÊ¿ä¿¡ µû¶ó setting
 */
public  final class ComConstant {
	
	//***************** Áß¿ä: ÀÓÀÇº¯°æ ºÒ°¡*****************	
	//01:Á¤½Ä, 02:lite
//	public static  String APPID 	= "02";  -> VersionConstant ·Î ÀÌµ¿
	
	public static  String LOCALE 	= "ko";
	public static  String NATIONAL	= "KR";
	public static  String CAL_YEAR 	= "yyyy";
	public static  String CAL_MONTH = "mm";
	public static  String CAL_DAY 	= "dd";
	public static  int TOPVISABLE	= View.VISIBLE;
	
	public static  int CNT_DAYSCHEDULE 	= 0;
	
	public static  String FULL_MESSAGE 	=  "message" ;

	public static  int WIDGET_BACK 		= 0;
	public static  int WIDGET_FONTCOLOR	= 0;
	
	//¾ð¾î(ÇÑ±¹,¿µ¾î,Áß±¹¾î,ÀÏº»¾î)
	public static  final String LOCALE_KO 	= "ko";
	public static  final String LOCALE_EN 	= "en";
	public static  final String LOCALE_ZH 	= "zh";
	public static  final String LOCALE_JA 	= "ja";
	//³ª¶ó(±â³äÀÏ¿¡¼­ »ç¿ë¿¹Á¤) : ÇÑ±¹¸¸ °°ÀÌ »ç¿ë
	public static  final String NATIONAL_KO = "KR";
	public static  final String NATIONAL_US	= "US";
	public static  final String NATIONAL_CN = "CN";
	public static  final String NATIONAL_JP = "JP";
	
	
	public static  final String PUT_BATCH 	= "B";
	public static  final String PUT_USER 	= "U";
	
	//alarm
	public static  final int ALARM_REQ 			= 7;
	public static  final int ALARM_SPC_REQ 		= 8;
	public static  final int ALARM_TODO_REQ 	= 9;
	public static  String ALARM_GUBUN 			= "schedule";
	public static  final String ALARM_TITLE 	= "title";
	public static  final String ALARM_SUBNAME 	= "subname";
	public static  String ALARM_DEFAULTTIME     = "0800";
	public static  final int ALARM_SPE_CODE		= 11;
	public static  final int ALARM_SPC_CODE 	= 12;
	public static  final int ALARM_TODO_CODE 	= 13;	
	//widget
	public static  final int WIDGET_REQ 		= 90;
	public static  final int WIDGET_DAILY_REQ 	= 91;
	public static  final int WIDGET_CALENDAR_REQ = 92;
	public static  final int WIDGET_WEEKLY_REQ 	= 93;
	public static  final int WIDGET_SIXDAY_REQ 	= 94;
		
	//db ¹öÀü : history À¯Áö ÇÊ¼ö (2013/2/10 : 3¹öÀü 2014³â ±â³äÀÏ ¹Ý¿µ)
	//						   2014/12/20 : 4¹öÀü 2015³â ±â³äÀÏ ¹Ý¿µ)
	public static  final int 	DATABASE_VERSION 			= 4;
	public static  final String DATABASE_NAME 				= "smdb";
	
	public static  final String DATABASE_TABLE_INSTALLCHECK = "installcheck";	
	public static  final String DATABASE_TABLE_SPECIALDAY 	= "specialday";
	public static  final String DATABASE_TABLE_SCHEDULE 	= "schedule";
	public static  final String DATABASE_TABLE_NEWSCHEDULE 	= "newschedule";
	public static  final String DATABASE_TABLE_USERMANAGER 	= "usermanager";
	public static  final String DATABASE_TABLE_LUNARDATA 	= "lunardata";
	public static  final String DATABASE_TABLE_TODOMEMO 	= "todomemo";
	public static  final String DATABASE_TABLE_NOTICE	 	= "notice";
	
	
	public static  final String DATABASE_INDEX1_USERMANAGER = "usermanageridx1";
	public static  final String DATABASE_INDEX1_LUNARDATA 	= "lunaridx1";
	public static  final String DATABASE_INDEX1_SPECIALDAY 	= "specialidx1";
	public static  final String DATABASE_INDEX2_SPECIALDAY 	= "specialidx2";	
	public static  final String DATABASE_INDEX1_SCHEDULE 	= "scheduleidx1";
	public static  final String DATABASE_INDEX2_SCHEDULE 	= "scheduleidx2";
	public static  final String DATABASE_INDEX1_NOTICE 		= "noticeidx1";
	
	public static  final int ACTIVITY_CREATE 	= 0;
	public static  final int ACTIVITY_EDIT 		= 1;
	public static  final int ACTIVITY_DELETE 	= 2;
	public static  final int ACTIVITY_SELECT 	= 3;
	public static  final int ACTIVITY_SELECT_USER	= 4;
	public static  final int ACTIVITY_PHONE_SELECT	= 10;
	public static  final int ACTIVITY_PHONE_CALL	= 11;	
	
	public static  final int DATE_DIALOG_ID 	= 0;
	public static  final int TIME_DIALOG_ID 	= 1;
	public static  final int TIME_DIALOG_ID_S 	= 2;
	public static  final int TIME_DIALOG_ID_E 	= 3;
	
	public static  final int DIALOG_SIMPLE_MESSAGE 		= 10;
	public static  final int DIALOG_YES_NO_MESSAGE 		= 12;
	public static  final int DIALOG_YES_MESSAGE 		= 13;
	public static  final int DIALOG_NO_MESSAGE 			= 14;
	public static  final int DIALOG_CALENDAR 			= 16;
	public static  final int DIALOG_INPUTCHECK 			= 20;
	public static  final int DIALOG_PROGRESSBAR			= 30;
	
	public static  final int GUBUN_YEAR 	= 0;
	public static  final int GUBUN_MONTH 	= 1;
	public static  final int GUBUN_DAY 		= 2;
	public static  final int GUBUN_HOUR 	= 3;
	public static  final int GUBUN_MINUTE	= 4;
	public static  final int GUBUN_SECOND	= 5;
	
	public static  final String SEPERATE_DATE 	= "/";
	public static  final String SEPERATE_TIME 	= ":";
	public static  final String SEPERATE_DOT 	= ".";
	
	//Calendar code(DayofWeek) : don'Change
	public static  final int DAYOFWEEK_SUNDAY	= 1;
	public static  final int DAYOFWEEK_MONDAY	= 2;
	public static  final int DAYOFWEEK_TUESDAY	= 3;
	public static  final int DAYOFWEEK_WEDNESDAY= 4;
	public static  final int DAYOFWEEK_THURSDAY	= 5;
	public static  final int DAYOFWEEK_FRIDAY	= 6;
	public static  final int DAYOFWEEK_SATURDAY	= 7;

	public static  final String MSG_BTN_YES				= "OK";
	public static  final String MSG_BTN_NO				= "NO";
	public static  final String MSG_BTN_CANCEL			= "CANCEL";
	public static  final String MSG_BTN_DONE			= "DONE";
	
	
	public static  final String SCHEDULE_GUBUN 	=  "schedule" ;
	public static  final String USER_GUBUN 		=  "user" ;
	public static  final String SPECIAL_GUBUN 	=  "specialday" ;
	public static  final String TODO_GUBUN 		=  "todo" ;
	
	
	public static  final String CALCHOICE_GUBUN =  "phone" ;
	
	public static  final String FOLDER_FROMLITE =  "" ;
	
	public static  final String [] ENGMONTH	
				=  { "Jan", "Feb", "Mar", "Apr", "May", "Jun" 
					,"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"  };
	public static  final String [] ENGMONTHFULL
				=  { "January", "February", "March", "April", "May", "June" 
					,"July", "August", "September", "October", "November", "December"  };	
	
	public static  final String [] GANGI	
				=  { "ÀÚ", "Ãà", "ÀÎ", "¹¦", "Áø", "»ç" 
					,"¿À", "¹Ì", "½Å", "À¯", "¼ú", "ÇØ"  };
	public static  final String [] GANGIEn	
				=  { "Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake" 
					,"Horse", "Lamb", "Monkey", "Rooster", "Dog", "Boar"  }; 	
	public static  final String [] GANGIHAN	
				=  { "í­", "õä", "ìÙ", "ÙÖ", "òã", "ÞÓ" 
					,"çí", "Ú±", "ãé", "ë·", "âù", "ú¤"  };
	
	public static  final String [] GAN	
				=  { "°©", "À»", "º´", "Á¤", "¹«", "±â" 
					,"°æ", "½Å", "ÀÓ", "°è"  }; 
	public static  final String [] GANEn	
				=  { "Gap", "Eul", "Byeong", "Jeong", "Mu", "Gi" 
					,"Gyeong", "Sin", "Im", "Gye"  }; 

	public static  final String [] GANHAN	
				=  { "Ë£", "ëà", "Ü°", "ïË", "Ùæ", "Ðù" 
					,"ÌÒ", "ãô", "ìó", "Í¤"  }; 
	
	
	/////////////////////////////////////////////////////////////

	
	public static final void setConstantStringForLocale( Context ctx, String language ) {
		
        LOCALE =  language;
		
	}
	public static final void setConstantStringForCountry( Context ctx, String country ) {
		
		NATIONAL =  country;
		
	}
//	public static final void setConstantStringWidgetBack( Context ctx, String back ) {
//		
//		WIDGET_BACK =  back;
//		
//	}	
}
