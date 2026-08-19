package com.waveapp.smcalendarlite.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;

import com.waveapp.smcalendarlite.R;
import com.waveapp.smcalendarlite.common.ComConstant;
import com.waveapp.smcalendarlite.database.LunarDataDbAdaper;
import com.waveapp.smcalendarlite.info.ScheduleInfo;


/**
* <pre>
* 날짜와 관련된 유용한 함수들의 모음.*
*/


public class SmDateUtil {

	/**
	* 핸드폰의 현재년월일시간을 가져온다
	* format : yyyy-MM-dd HH:mm:ss
	* @return day+time
	**/
	public static String getToday() {
		String str = getTodayDate() + " " + getNowTimeMili();
		return str;
	}

	
	/**
	* 오늘 날짜를 받아온다. yyyy-MM-dd
	* @return		오늘날짜 (달은 +1 처리)
	**/
	
	public static String getTodayDate() {
		
		Calendar calendar = Calendar.getInstance();
		
		StringBuffer returnString = new StringBuffer();

		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.YEAR)), 4));
		returnString.append(ComConstant.SEPERATE_DATE);
		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.MONTH ) +  1) , 2));
		returnString.append(ComConstant.SEPERATE_DATE);
		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.DATE)), 2));


		return returnString.toString();
	}
	
	/**
	* 오늘 날짜를 받아온다. yyyyMMdd
	* @return		오늘날짜 (달은 +1 처리)
	**/
	
	public static String getTodayDefault() {
		
		Calendar calendar = Calendar.getInstance();
		
		StringBuffer returnString = new StringBuffer();

		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.YEAR)), 4));
		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.MONTH ) +  1) , 2));
		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.DATE)), 2));


		return returnString.toString();
	}
	/**
	* 현재 시간(시분초)을 받아온다. hh:mi:ss
	* @return		현재시간
	**/
	public static String getNowTime() {
		
		Calendar calendar = Calendar.getInstance();
		
		StringBuffer returnString = new StringBuffer();

		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)), 2));
		returnString.append(ComConstant.SEPERATE_TIME);
		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.MINUTE)), 2));
		returnString.append(ComConstant.SEPERATE_TIME);
		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.SECOND)), 2));


		return returnString.toString();
	}
	/**
	* 현재 시간(시분초)을 받아온다. hh:mi:ss:ssss
	* @return		현재시간
	**/
	public static String getNowTimeMili() {
		
		Calendar calendar = Calendar.getInstance();
		
		StringBuffer returnString = new StringBuffer();

		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)), 2));
		returnString.append(ComConstant.SEPERATE_TIME);
		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.MINUTE)), 2));
		returnString.append(ComConstant.SEPERATE_TIME);
		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.SECOND)), 2));
		returnString.append(ComConstant.SEPERATE_TIME);
		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.MILLISECOND)), 3));


		return returnString.toString();
	}
	/**
	* 현재 시간 (시분)을 받아온다. hh:mi
	* @return		현재시간
	**/
	public static String getNowHourMinute() {
		
		Calendar calendar = Calendar.getInstance();
		
		StringBuffer returnString = new StringBuffer();

		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)), 2));
		returnString.append(ComConstant.SEPERATE_TIME);
		returnString.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.MINUTE)), 2));

		return returnString.toString();
	}


	/**
	* 현재 시간기준 정시정보를 가져온다(다국적format)  hhmi
	* @return		현재시간
	**/
	public static String getNowHourMinuteOclock( int addtime ) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, addtime);
		
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(ComUtil.fillSpaceToZero(
				Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)), 2));
		strbuf.append("00");
		
		return strbuf.toString();
	}


     /**
      * Method Desciption : HTML태그 안의 내용을 불러와서 return한다.
      * 
      * @param message HTML Tag
      * @return Html Tag안의 Text
      */
     public static String stripHTMLTags(String message) {
         StringBuffer returnMessage = new StringBuffer(message);
         int startPosition = message.indexOf("<"); // first opening brace
         int endPosition = message.indexOf(">"); // first closing braces
         while (startPosition != -1) {
             returnMessage.delete(startPosition, endPosition + 1); // remove the tag
             startPosition = (returnMessage.toString()).indexOf("<"); // look for the next opening brace
             endPosition = (returnMessage.toString()).indexOf(">"); // look for the next closing brace
         }
         return returnMessage.toString();
     }

     /**
      * 오늘 일자 기준으로 diff만큼 더한(뺀) 일를 지정된 Format의 날짜 표현형식으로 돌려준다. <BR><BR>
      *
      * 사용예) getEvalDate( "20020202", -1 )<BR>
      * 결 과 ) 2002/02/01<BR><BR>
      *
      * Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
      *
      * @param date String
      * @param diff int
      * @return java.lang.String
      */
     public static String getEvalDate( String date, int diff ) {
         String evalDate = "";


         if ( date != null ) {
             SimpleDateFormat formatter = new SimpleDateFormat( "yyyy/MM/dd" );
             Calendar Today = Calendar.getInstance();


             Today.set( Integer.parseInt( date.substring( 0, 4 ) ),
                 Integer.parseInt( date.substring( 4, 6 ) ) - 1,
                 Integer.parseInt( date.substring( 6 ) ) );
             Today.add( Calendar.DATE, diff );

             evalDate = formatter.format( Today.getTime() );
         }


         return evalDate;
     }
 /////////////////////////////추가(김진호)////////////////////////////////////////////////
     ////////////////////////////////////////////////////////////////////////////
     // 지정한 년도의 총 날짜 수를 구한다.
     //
     public static int getDaysInYear(int y) {
       if (y > 1582) {
           if (y % 400 == 0)
               return 366;
           else if (y % 100 == 0)
               return 365;
           else if (y % 4 == 0)
               return 366;
           else
               return 365;
       }
       else if (y == 1582)
           return 355;
       else if (y > 4) {
           if (y % 4 == 0)
               return 366;
           else
               return 365;
       }
       else if (y > 0)
           return 365;
       else
           return 0;
     }




     ////////////////////////////////////////////////////////////////////////////
     // 지정한 년도, 지정한 월의 총 날짜 수를 구한다.
     //
     public static int getDaysInMonth(int m, int y) {
       if (m < 1 || m > 12)
           throw new RuntimeException("Invalid month: " + m);


       int[] b = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
       if (m != 2 && m >= 1 && m <= 12 && y != 1582)
           return b[m - 1];
       if (m != 2 && m >= 1 && m <= 12 && y == 1582)
           if (m != 10)
               return b[m - 1];
           else
               return b[m - 1] - 10;


       if (m != 2)
           return 0;


       // m == 2 (즉 2월)
       if (y > 1582) {
           if (y % 400 == 0)
               return 29;
           else if (y % 100 == 0)
               return 28;
           else if (y % 4 == 0)
               return 29;
           else
               return 28;
       }
       else if (y == 1582)
           return 28;
       else if (y > 4) {
           if (y % 4 == 0)
               return 29;
           else
               return 28;
       }
       else if (y > 0)
           return 28;
       else
           throw new RuntimeException("Invalid year: " + y);
     }




     ////////////////////////////////////////////////////////////////////////////
     // 지정한 년도의 첫날 부터 지정한 월의 지정한 날 까지의 날짜 수를 구한다.
     //
     public static int getDaysFromYearFirst(int d, int m, int y) {
       if (m < 1 || m > 12)
           throw new RuntimeException("Invalid month " + m + " in " + d + "/" + m + "/" + y);


       int max = getDaysInMonth(m, y);
       if (d >= 1 && d <= max) {
           int sum = d;
           for (int j = 1; j < m; j++)
               sum += getDaysInMonth(j, y);
           return sum;
       }
       else
           throw new RuntimeException("Invalid date " + d + " in " + d + "/" + m + "/" + y);
     }


     ////////////////////////////////////////////////////////////////////////////
     // 2000년 1월 1일 부터 지정한 년, 월, 일 까지의 날짜 수를 구한다.
     // 2000년 1월 1일 이전의 경우에는 음수를 리턴한다.
     //


     public static int getDaysFrom21Century(int d, int m, int y) {
        if (y >= 2000) {
            int sum = getDaysFromYearFirst(d, m, y);
            for (int j = y - 1; j >= 2000; j--)
                sum += getDaysInYear(j);
            return sum - 1;
        }
        else if (y > 0 && y < 2000) {
            int sum = getDaysFromYearFirst(d, m, y);
            for (int j = 1999; j >= y; j--)
                 sum -= getDaysInYear(y);
            return sum - 1;
        }
        else
            throw new RuntimeException("Invalid year " + y + " in " + d + "/" + m + "/" + y);
      }


     //////////////////////////////////////////////////////////////////////
     // 2000년 1월 1일 부터 지정한 년, 월, 일 까지의 날짜 수를 구한다.
     // 2000년 1월 1일 이전의 경우에는 음수를 리턴한다.
     //
     public static int getDaysFrom21Century(String s) {
       int d, m, y;
       if (s.length() == 8) {
           y = Integer.parseInt(s.substring(0, 4));
           m = Integer.parseInt(s.substring(4, 6));
           d = Integer.parseInt(s.substring(6));
           return getDaysFrom21Century(d, m, y);
       }
       else if (s.length() == 10) {
           y = Integer.parseInt(s.substring(0, 4));
           m = Integer.parseInt(s.substring(5, 7));
           d = Integer.parseInt(s.substring(8));
           return getDaysFrom21Century(d, m, y);
       }
       else if (s.length() == 11) {
           d = Integer.parseInt(s.substring(0, 2));
           String strM = s.substring(3, 6).toUpperCase();
           String[] monthNames = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
                                   "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
           m = 0;
           for (int j = 1; j <= 12; j++) {
               if (strM.equals(monthNames[j-1])) {
                   m = j;
                   break;
               }
           }
           if (m < 1 || m > 12)
               throw new RuntimeException("Invalid month name: " + strM + " in " + s);
           y = Integer.parseInt(s.substring(7));
           return getDaysFrom21Century(d, m, y);
       }
       else{
    	   return 0;
       }
//           throw new RuntimeException("Invalid date format: " + s);
     }


     /////////////////////////////////////////////
     // (양 끝 제외) 날짜 차이 구하기
     //
     public static int getDaysBetween(String s1, String s2) {
       int y1 = getDaysFrom21Century(s1);
       int y2 = getDaysFrom21Century(s2);
       return y1 - y2 - 1;
     }




     /////////////////////////////////////////////
     // 날짜 차이 구하기
     //
     public static int getDaysDiff(String s1, String s2) {
//    	 Log.w(">>>>> S1 : ", s1);
//    	 Log.w(">>>>> S2 : ", s2);
       int y1 = getDaysFrom21Century(s1);
       int y2 = getDaysFrom21Century(s2);
       return y1 - y2;
     }

     /////////////////////////////////////////////
     // (양 끝 포함) 날짜 차이 구하기
     //
     public static int getDaysFromTo(String s1, String s2) {
       int y1 = getDaysFrom21Century(s1);
       int y2 = getDaysFrom21Century(s2);
       return y1 - y2 + 1;
     }
     

     /**
      * 받아온 string값에서 년/월/일 int 값 추출
      * (yyyy-mm-dd)
      */
     
     public static int getDateToInt(String str, int gubun) {
    	 int ret = 0;
    	 
    	 if ( str != null && !str.equals("") && str.length() == 8) {
    		 switch (gubun) {
				case ComConstant.GUBUN_YEAR:
					ret =  ComUtil.stringToInt(str.substring(0, 4));
					break;
				case ComConstant.GUBUN_MONTH:
					ret =  ComUtil.stringToInt(str.substring(4, 6));
					break;
				case ComConstant.GUBUN_DAY:
					ret =  ComUtil.stringToInt(str.substring(6));
					break;
				default:
					ret =  ComUtil.stringToInt(str);
					break;
    		 }
		}
       
       return ret;
     }
     
     /**
      * 받아온 string값에서 년/월/일 String 값 추출
      * (yyyy-mm-dd)
      */ 
    
     public static String getDateToStr(String str, int gubun) {
    	 String ret = "";

    	 if ( str != null && !str.equals("") && str.length() == 8) {
    		 switch (gubun) {
				case ComConstant.GUBUN_YEAR:
					ret =  ComUtil.setBlank(str.substring(0, 4));
					break;
				case ComConstant.GUBUN_MONTH:
					ret =  ComUtil.setBlank(str.substring(4, 6));
					break;
				case ComConstant.GUBUN_DAY:
					ret =  ComUtil.setBlank(str.substring(6));
					break;
				default:
					ret =  ComUtil.setBlank(str);
					break;
    		 }
		}
       
       return ret;
     }  
       
     /**
      * 받아온 string값에서 시/분/초 str 값 추출
      * (hhmm)
      */  
     public static String getTimeToStr(String str, int gubun) {
    	 String ret = "";
    	 if ( str != null && !str.equals("") && str.length() == 4) {
    		 switch (gubun) {
				case ComConstant.GUBUN_HOUR:
					ret =  ComUtil.setBlank(str.substring(0, 2));
					break;
				case ComConstant.GUBUN_MINUTE:
					ret =  ComUtil.setBlank(str.substring(2));
					break;
				default:
					ret =  ComUtil.setBlank(str);
					break;
			}
    	 }
       
       return ret;
     } 
     /**
      * 받아온 string값에서 시/분/초 int 값 추출
      * (hhmm)
      */     
     public static int getTimeToInt(String str, int gubun) {
    	 int ret = 0;
    	 if ( str != null && !str.equals("") && str.length() == 4) {
    		 switch (gubun) {
				case ComConstant.GUBUN_HOUR:
					ret =  ComUtil.stringToInt(str.substring(0, 2));
					break;
				case ComConstant.GUBUN_MINUTE:
					ret =  ComUtil.stringToInt(str.substring(2));
					break;
				default:
					ret =  ComUtil.stringToInt(str);
					break;
			}
    	 }
       
       return ret;
     }    
  
     /**
      * 년,월,일 int  -> format(YYYYMMDD) string 으로 변환
      */     

     public static String getDateFormat ( int year, int month, int day) {   	 
    	 
    	 StringBuilder str =           
 			new StringBuilder()                    
 			// Month is 0 based so add 1                    
    	 	.append(ComUtil.fillSpaceToZero(Integer.toString(year), 4))
    	 	.append(ComUtil.fillSpaceToZero(Integer.toString(month), 2))
    	 	.append(ComUtil.fillSpaceToZero(Integer.toString(day), 2))
    	 	.append("");
	  
    	 return str.toString(); 
     }    
     /**
      * 년,월일 int  -> format(YYYYMMDD) string 으로 변환
      */     

     public static String getDateFormat ( int year, int monthday) {   	 
    	 
    	 StringBuilder str =           
 			new StringBuilder()                    
 			// Month is 0 based so add 1                    
    	 	.append(ComUtil.fillSpaceToZero(Integer.toString(year), 4))
    	 	.append(ComUtil.fillSpaceToZero(Integer.toString(monthday), 4))
    	 	.append("");
	  
    	 return str.toString(); 
     }  
     /**
      * 월일 int  -> format(MMDD) string 으로 변환
      */     

     public static String getMonthDayFormat ( int month, int day) {   	 
    	 
    	 StringBuilder str =           
 			new StringBuilder()                    
 			// Month is 0 based so add 1                    
 	 		.append(ComUtil.fillSpaceToZero(Integer.toString(month), 2))
 	 		.append(ComUtil.fillSpaceToZero(Integer.toString(day), 2))
    	 	.append("");
	  
    	 return str.toString(); 
     } 
     /**
      * 년월 int  -> format(YYYYMM) string 으로 변환
      */     

     public static String getYearMonthFormat ( int year, int month) {   	 
    	 
    	 StringBuilder str =           
 			new StringBuilder()                    
 			// Month is 0 based so add 1                    
 	 		.append(ComUtil.fillSpaceToZero(Integer.toString(year), 4))
 	 		.append(ComUtil.fillSpaceToZero(Integer.toString(month), 2))
    	 	.append("");
	  
    	 return str.toString(); 
     }     
     /**
      * 시분  -> format(HHMM) string 으로 변환
      */     

     public static String getTimeFormat ( int hour, int minute ) {   	 
    	 
    	 StringBuilder str =           
 			new StringBuilder()                    
 			// Month is 0 based so add 1                    
		    	.append(ComUtil.fillSpaceToZero(Integer.toString(hour), 2))
		 	 	.append(ComUtil.fillSpaceToZero(Integer.toString(minute), 2))
		 	 	.append("");
	  
    	 return str.toString(); 
     } 

 	/**
 	* 날짜 format (국가별 format) 단, 요일은 선택
 	* YYYYMMDD  -> YYYY년MM월DD일 요일
 	* YYYYMMDD  -> week, MM DD, YYYY,  
 	* --> 영문 fullname option 추가 (유럽권은 미적용)
 	*/
 	public static String getDateFullFormat( Context ctx, String pSrcDate, boolean isDayofWeek, boolean isFullName )	{
 		String tSepDate = "";

 		//한국 등 아시아권 (YYYY년MM월DD일 요일)
 		if ( ComUtil.isLanguageFront( ctx )) {
 			
 	 		String pSep1 = ComUtil.getStrResource(ctx, R.string.year);
 	 		String pSep2 = ComUtil.getStrResource(ctx, R.string.month);
 	 		String pSep3 = ComUtil.getStrResource(ctx, R.string.day);
 	 		//년,월,일	
 	 		if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 8 )	{
 	 			tSepDate = pSrcDate.substring(0, 4) + pSep1 
 	 					 + pSrcDate.substring(4, 6) + pSep2 
 	 					 + pSrcDate.substring(6)	+ pSep3 ;
 	 			if ( isDayofWeek ) {
 	 	 			tSepDate = tSepDate + " " + getDayOfWeekFromDate(ctx, pSrcDate);
 	 	 		} 
 	 		//년,월	
 	 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 6 )	{
 	 			tSepDate = pSrcDate.substring(0, 4) + pSep1 
					 	 + pSrcDate.substring(4) + pSep2; 	
 	 		
 	 		//월,일
 	 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 4 )	{
 	 			
 	 			tSepDate = pSrcDate.substring(0, 2) + pSep2 
					 	 + pSrcDate.substring(2) + pSep3;  	 	
  	 		} else {
 	 			tSepDate = pSrcDate;
 	 		} 	
 	 		
 		//영어권(week, MM DD, YYYY)(MM, YYYY)
 		} else if( !ComUtil.isLanguageFront( ctx ) ) {
 			
 	 		if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 8 )	{
 	 			
 	 			//주
 	 			if ( isDayofWeek ) {
 	 	 			tSepDate = getDayOfWeekFromDate(ctx, pSrcDate) + ",";
 	 	 		}
 	 			//영문월명
 	 			if ( isFullName ) {
 	 	 			tSepDate = getMonthForEngFull(pSrcDate.substring(4, 6)) + " ";
 	 	 		} else {
 	 	 			tSepDate = getMonthForEng(pSrcDate.substring(4, 6)) + " ";
 	 	 		}
 	 			//그외
 	 			tSepDate = tSepDate + pSrcDate.substring(6)	+ ","
 	 					 			+ pSrcDate.substring(0, 4);
 	 			
 	 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 6 )	{
 	 			//영문월명
 	 			if ( isFullName ) {
 	 	 			tSepDate = getMonthForEngFull(pSrcDate.substring(4)) + "," ;
 	 	 		} else {
 	 	 			tSepDate = getMonthForEng(pSrcDate.substring(4)) + "," ;
 	 	 		}
 	 			tSepDate = tSepDate + pSrcDate.substring(0, 4);
 	 			
 	 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 4 )	{
 	 			
 	 			tSepDate = pSrcDate.substring(2) + "," ;
 	 			//영문월명
 	 			if ( isFullName ) {
 	 	 			tSepDate = tSepDate + getMonthForEngFull(pSrcDate.substring(0, 2));
 	 	 		} else {
 	 	 			tSepDate = tSepDate + getMonthForEng(pSrcDate.substring(0, 2));
 	 	 		}	
 	 			
 	 		} else {
 	 			
 	 			tSepDate = pSrcDate;
 	 			
 	 		} 	 			
 		}

 		return tSepDate;
 	}
 	/**
 	* 일자 format (국가별 format) 단, 요일은 선택
 	* DD  ->  DD일 요일
 	* DD  ->  Day DD week 
 	*/
 	public static String getDayFullFormat( Context ctx, String pSrcDate, boolean isDayofWeek )	{
 		String tSepDate = "";

 		//한국 (YYYY년MM월DD일 요일)
 		if ( ComUtil.isLanguageFront( ctx ) ) {
 			
 	 		String pSep3 = ComUtil.getStrResource(ctx, R.string.day);
 	 		//일	
 	 		if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 8 )	{
 	 			tSepDate =  Integer.parseInt(pSrcDate.substring(6))	+ pSep3 ;
 	 			tSepDate =  tSepDate + " / " ;
 	 			if ( isDayofWeek ) {
 	 	 			tSepDate = tSepDate  + getDayOfWeekFromDate(ctx, pSrcDate);
 	 	 		} 
 
 	 		} else {
 	 			tSepDate = pSrcDate;
 	 		} 	
 	 		
 		//영어권
 		} else if( !ComUtil.isLanguageFront( ctx ) ) {
 			String pSep3 = ComUtil.getStrResource(ctx, R.string.day);
 	 		if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 8 )	{
 	 			tSepDate =  pSep3 + pSrcDate.substring(6) ;
 	 			
 	 			if ( isDayofWeek ) {
 	 	 			tSepDate = tSepDate + getDayOfWeekFromDate(ctx, pSrcDate);
 	 	 		}
 	 		} else {
 	 			tSepDate = pSrcDate;
 	 		} 	 			
 		}

 		return tSepDate;
 	} 	
 	/**
 	* 날짜 format/구분별 (국가별 format) -> 년, 월, 일별
 	* YYYYMMDD  -> YYYY년, MM월, DD일, 요일
 	* YYYYMMDD  -> YYYY, MM, DD, week,  
 	*/
 	public static String getDateFormatPerGubun( Context ctx, String pSrcDate,  int gubun, boolean isnotzero, boolean isFullName )	{
 		String tSepDate = "";

 		//한국 (YYYY년, MM월, DD일, 요일)
 		if ( ComUtil.isLanguageFront( ctx ) ) {
 			
 	 		//년,월,일	
 	 		if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 8 )	{
	    		 switch (gubun) {
					case ComConstant.GUBUN_YEAR:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(0, 4));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						tSepDate = tSepDate + ComUtil.getStrResource(ctx, R.string.year);
						break;
					case ComConstant.GUBUN_MONTH:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(4, 6));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						tSepDate = tSepDate + ComUtil.getStrResource(ctx, R.string.month);
						break;
					case ComConstant.GUBUN_DAY:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(6));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						break;
					default:
						tSepDate = pSrcDate;
						break;
	    		 } 	 	 			
 	 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 6 )	{
	    		 switch (gubun) {
					case ComConstant.GUBUN_YEAR:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(0, 4));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						tSepDate = tSepDate + ComUtil.getStrResource(ctx, R.string.year);
						break;
					case ComConstant.GUBUN_MONTH:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(4));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						tSepDate = tSepDate + ComUtil.getStrResource(ctx, R.string.month);
						break;
					default:
						tSepDate = pSrcDate;
						break;
	    		 }  	 			
 	 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 4 )	{
	    		 switch (gubun) {					
					case ComConstant.GUBUN_MONTH:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(0,2));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						tSepDate = tSepDate + ComUtil.getStrResource(ctx, R.string.month);
						break;
					case ComConstant.GUBUN_DAY:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(2));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						tSepDate = tSepDate + ComUtil.getStrResource(ctx, R.string.day);
						break;						
					default:
						tSepDate = pSrcDate;
						break;
	    		 }  
 	 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 2 )	{
	    		 switch (gubun) {					
					case ComConstant.GUBUN_MONTH:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(0));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						tSepDate = tSepDate + ComUtil.getStrResource(ctx, R.string.month);
						break;
					case ComConstant.GUBUN_DAY:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(0));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						tSepDate = tSepDate + ComUtil.getStrResource(ctx, R.string.day);
						break;						
					default:
						tSepDate = pSrcDate;
						break;
	    		 }  
	 		}
 	 		
 		//영어권(week, MM DD, YYYY)(MM, YYYY)
 		} else if( !ComUtil.isLanguageFront( ctx ) ) {
 			//년,월,일	
 	 		if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 8 )	{
		   		 switch (gubun) {
					case ComConstant.GUBUN_YEAR:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(0, 4));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						break;
					case ComConstant.GUBUN_MONTH:
						tSepDate = ComUtil.setBlank(pSrcDate.substring(4, 6));
		 	 			//영문월명
		 	 			if ( isFullName ) {
		 	 				tSepDate = getMonthForEngFull(tSepDate);
		 	 	 		} else {
		 	 	 			tSepDate = getMonthForEng(tSepDate);
		 	 	 		}
						break;
					case ComConstant.GUBUN_DAY:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(6));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						break;
					default:
						tSepDate = pSrcDate;
						break;
				 } 	 	 
 	 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 6 )	{
		   		 switch (gubun) {
					case ComConstant.GUBUN_YEAR:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(0, 4));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						break;
					case ComConstant.GUBUN_MONTH:
						tSepDate = ComUtil.setBlank(pSrcDate.substring(4));
		 	 			//영문월명
		 	 			if ( isFullName ) {
		 	 				tSepDate = getMonthForEngFull(tSepDate);
		 	 	 		} else {
		 	 	 			tSepDate = getMonthForEng(tSepDate);
		 	 	 		}						
						break;
					default:
						tSepDate = pSrcDate;
						break;
				 }  	 			
 	 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 4 )	{
		   		 switch (gubun) {
					case ComConstant.GUBUN_MONTH:
						tSepDate = ComUtil.setBlank(pSrcDate.substring(0, 2));
		 	 			//영문월명
		 	 			if ( isFullName ) {
		 	 				tSepDate = getMonthForEngFull(tSepDate);
		 	 	 		} else {
		 	 	 			tSepDate = getMonthForEng(tSepDate);
		 	 	 		}	
						break;
					case ComConstant.GUBUN_DAY:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(2));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						break;
					default:
						tSepDate = pSrcDate;
						break;
				 } 	 			
 	 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 2 )	{
		   		 switch (gubun) {
					case ComConstant.GUBUN_MONTH:
						tSepDate = ComUtil.setBlank(pSrcDate.substring(0));
		 	 			//영문월명
		 	 			if ( isFullName ) {
		 	 				tSepDate = getMonthForEngFull(tSepDate);
		 	 	 		} else {
		 	 	 			tSepDate = getMonthForEng(tSepDate);
		 	 	 		}	
						break;
					case ComConstant.GUBUN_DAY:
						tSepDate =  ComUtil.setBlank(pSrcDate.substring(0));
						if ( isnotzero )
							tSepDate = Integer.toString(Integer.parseInt(tSepDate));
						break;
					default:
						tSepDate = pSrcDate;
						break;
				 } 
 	 		}
 		}	

 		return tSepDate;
 	} 	
 	/**
 	* 날짜간략 format (국가별 format) 단, 요일은 선택
 	* YYYYMMDD  -> YYYY/MM/DD 요일
 	* YYYYMMDD  -> week, MM DD, YYYY,  
 	*/
 	public static String getDateSimpleFormat( Context ctx, String pSrcDate, String sep, boolean isDayofWeek )	{
 		
 		String tSepDate = "";
 
 		//년,월,일	
 		if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 8 )	{
 			tSepDate = pSrcDate.substring(0, 4) + sep 
 					 + pSrcDate.substring(4, 6) + sep 
 					 + pSrcDate.substring(6) ;
 			if ( isDayofWeek ) {
 	 			tSepDate = tSepDate + " " + getDayOfWeekFromDate(ctx, pSrcDate);
 	 		} 
 		//년,월	
 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 6 )	{
 			tSepDate = pSrcDate.substring(0, 4) + sep 
				 	 + pSrcDate.substring(4); 	
 		
 		//월,일
 		} else if (pSrcDate != null && !pSrcDate.trim().equals("") && pSrcDate.length() == 4 )	{
 			
 			tSepDate = pSrcDate.substring(0, 2) + sep 
				 	 + pSrcDate.substring(2);  	 	
 			
 		} else {
 			tSepDate = pSrcDate;
 		} 	

 		return tSepDate;
 	} 	

 	/*
 	 * 영문 월표기값 구하기
 	 */
 	public static String getMonthForEng( String month )	{
 		String tStr = "";
 		if (month != null && !month.trim().equals("") && ComUtil.isNumber(month))	{
 			int imonth = ComUtil.stringToInt(month);
 			tStr = ComConstant.ENGMONTH[ imonth-1 ] ;
 		}
 		return tStr ;
 		
 	}

 	/*
 	 * 영문 월표기값 구하기(Full Name
 	 */
 	public static String getMonthForEngFull( String month )	{
 		String tStr = "";
 		if (month != null && !month.trim().equals("") && ComUtil.isNumber(month))	{
 			int imonth = ComUtil.stringToInt(month);
 			tStr = ComConstant.ENGMONTHFULL[ imonth-1 ] ;
 		}
 		return tStr ;
 		
 	} 	
 	/**
 	* 시간 구분자 추가 (국가별 format) 
 	* HHMMSS -> 오전 HH:MM:SS  HHMM ->  오전 HH:MM
 	* HHMMSS ->  HH:MM:SS AM/PM  HHMM ->  HH:MM AM/PM
 	*/
 	public static String getTimeFullFormat( Context ctx, String pSrcTime )	{
 		
 		String tSepTime = "";
 		
 		if ( pSrcTime != null && pSrcTime.length() > 2 ){
 			
 	 		String[] nTime = new String[2];
 	 		String nElseTime = ""; 			
 			
 			nTime = getTimeZone(ctx, pSrcTime.substring(0, 2)) ;
 			nElseTime = pSrcTime.substring(2);
 	 		
 	 		//한국 (오전 HH:MM:SS 오전 HH:MM)
 	 		if ( ComUtil.isLanguageFront( ctx )) {
 	 	 		
 	 			tSepTime = nTime[0] + "" + getSepTime (nTime[1] + nElseTime);	
 	 	 		
 	 		//영어권(HH:MM:SS AM/PM)
 	 		} else if( !ComUtil.isLanguageFront( ctx )) { 			
 	 	 		
 	 			tSepTime = getSepTime (nTime[1] + nElseTime) + " "  + nTime[0]  ;	
 	 		}  			
 			
 		} else {
 			tSepTime = pSrcTime;
 		}
			
 		return tSepTime;
 	}
 	/**
 	* 시간 구분자 추가 (국가별 format) 
 	* HHMMSS -> 오전 HH:MM:SS  HHMM ->  오전 HH:MM
 	* HHMMSS ->  HH:MM:SS AM/PM  HHMM ->  HH:MM AM/PM
 	*/
 	public static String getTimeFullFormat( Context ctx, String pSrcTime, boolean sep )	{
 		
 		String tSepTime = "";
 		
 		if ( pSrcTime != null && pSrcTime.length() > 2 ){
 			
 	 		String[] nTime = new String[2];
 	 		String nElseTime = ""; 
 	 		String sepStr = "";
 	 		if ( sep ) {
 	 			 sepStr = "\n";
 	 		}
 			
 			nTime = getTimeZone(ctx, pSrcTime.substring(0, 2)) ;
 			nElseTime = pSrcTime.substring(2);
 	 		
 	 		//한국 (오전 HH:MM:SS 오전 HH:MM)
 	 		if ( ComUtil.isLanguageFront( ctx ) ) {
 	 	 		
 	 			tSepTime = nTime[0] +  sepStr + getSepTime (nTime[1] + nElseTime);	
 	 	 		
 	 		//영어권(HH:MM:SS AM/PM)
 	 		} else if( !ComUtil.isLanguageFront( ctx )) { 			
 	 	 		
 	 			tSepTime = nTime[0] +  sepStr  + getSepTime (nTime[1] + nElseTime)   ;	
 	 		}  			
 			
 		} else {
 			tSepTime = pSrcTime;
 		}
			
 		return tSepTime;
 	} 	
 	/*
 	 * 시간대 구하기(다국어지원)
 	 */
 	public static String [] getTimeZone( Context ctx, String hour )	{
 		String[] tStr = new String[2];
 		if (hour != null && !hour.trim().equals("") && ComUtil.isNumber(hour))	{
 			
 			int ihour = ComUtil.stringToInt(hour);
 			
			if ( ihour >= 0 && ihour < 12 ) {
				tStr[0] = ComUtil.getStrResource(ctx, R.string.am);
				tStr[1] = hour;
			} else  if ( ihour == 12 ) {
				tStr[0] = ComUtil.getStrResource(ctx, R.string.pm);
				tStr[1] = hour  ;
			} else  {
				tStr[0] = ComUtil.getStrResource(ctx, R.string.pm);
				tStr[1] = ComUtil.fillSpaceToZero(ComUtil.intToString(ihour - 12), 2) ;
			}
 		}
 		return tStr ;
 		
 	} 	

 	/*
 	 * 시간 format ( : )
 	 */
 	public static String getSepTime( String pStrTime )	{
 		
 		String tSepTime = "";
 		
 		String pSep = ComConstant.SEPERATE_TIME;
 		
 		if (pStrTime != null && !pStrTime.trim().equals("")&&  pStrTime.length() == 4 )	{
 			
 			tSepTime = pStrTime.substring(0, 2) + pSep + pStrTime.substring(2);
 			
 		} else if (pStrTime != null && !pStrTime.trim().equals("")&&  pStrTime.length() == 6 )	{
 			
 			tSepTime = pStrTime.substring(0, 2) + pSep + pStrTime.substring(2, 4) + pSep + pStrTime.substring(4);
 		
 		} else {
 			
 			tSepTime = pStrTime;
 			
 		}
 		
 		return tSepTime;
 	}
	
 	/**
 	* 특정일 요일 구하기(다국어지원)
 	* YYYYMMDD  -> 요일
 	*/
 	public static String getDayOfWeekFromDate( Context ctx, String pDate)	{
 		String tStr 	= "";
 		Calendar iCal 	= Calendar.getInstance( ) ;
 		int year 	= SmDateUtil.getDateToInt(pDate, ComConstant.GUBUN_YEAR);
 		int month 	= SmDateUtil.getDateToInt(pDate, ComConstant.GUBUN_MONTH);
 		int day 	= SmDateUtil.getDateToInt(pDate, ComConstant.GUBUN_DAY);
 		
 		iCal.set( Calendar.YEAR, 	year ) ;
 		iCal.set( Calendar.MONTH, 	month - 1 ) ;
 		iCal.set( Calendar.DAY_OF_MONTH, 	day ) ;
 		
 		int day_of_week = iCal.get ( Calendar.DAY_OF_WEEK );
 		
 		switch (day_of_week) {
		case 1: 
			tStr = ComUtil.getStrResource(ctx, R.string.sunday);
			break;
		case 2: 
			tStr = ComUtil.getStrResource(ctx, R.string.monday);
			break;
		case 3: 
			tStr = ComUtil.getStrResource(ctx, R.string.tuesday);
			break;
		case 4: 
			tStr = ComUtil.getStrResource(ctx, R.string.wednesday);
			break;
		case 5: 
			tStr = ComUtil.getStrResource(ctx, R.string.thursday);
			break;
		case 6: 
			tStr = ComUtil.getStrResource(ctx, R.string.friday);
			break;
		case 7: 
			tStr = ComUtil.getStrResource(ctx, R.string.saturday);
			break;
		default:
			break;
		}
 		return tStr;
 	} 	

	/**
 	* 요일코드 ->요일명 구하기(다국어지원)
 	* 요일명
 	*/
 	public static String getDayOfWeekFromCode( Context ctx, int dayofweek)	{
 		
 		String tStr 	= "";
 		
 		switch (dayofweek) {
		case 1: 
			tStr = ComUtil.getStrResource(ctx, R.string.sunday);
			break;
		case 2: 
			tStr = ComUtil.getStrResource(ctx, R.string.monday);
			break;
		case 3: 
			tStr = ComUtil.getStrResource(ctx, R.string.tuesday);
			break;
		case 4: 
			tStr = ComUtil.getStrResource(ctx, R.string.wednesday);
			break;
		case 5: 
			tStr = ComUtil.getStrResource(ctx, R.string.thursday);
			break;
		case 6: 
			tStr = ComUtil.getStrResource(ctx, R.string.friday);
			break;
		case 7: 
			tStr = ComUtil.getStrResource(ctx, R.string.saturday);
			break;
		default:
			break;
		}
 		return tStr;
 	} 		
 	/**
 	* 요일배열로 특정기간 날짜정보 List 가져오기
 	* YYYYMMDD 
 	* 단, 요일코드가 0일 경우에는 skip
 	*/ 	
 	public static String [] getDateFromDayOfWeekArr(int[] dayofweek, String fromdate, String todate
 			, String dbstartdate, String dbenddate, Calendar iCal)	{
 		
 		String []  sArr = null;
 		ArrayList<String> list = new ArrayList<String>();
 		
 		//요일코드 유효성체크
 		int weeklen = dayofweek.length;
 		if ( dayofweek.length != 7 ) return sArr;

 		//조회기간 & db기간 비교  -> 실제 조회할 기간 산출
 		String sDate = "";
 		String eDate ="";
 		if ( Integer.parseInt(fromdate)  >=  Integer.parseInt (dbstartdate))  sDate = fromdate;
 		else sDate = dbstartdate;
 		if ( Integer.parseInt(todate)  <=  Integer.parseInt (dbenddate))  eDate = todate;
 		else eDate = dbenddate;
 		
 		//Calendar 생성 ( 주수 확인 및 이월 처리 )
 		int fYear 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_YEAR);
 		int fMonth 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_MONTH);
 		int fDay 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_DAY);
 		
 		iCal.set( Calendar.YEAR, 			fYear ) ;
 		iCal.set( Calendar.MONTH, 			fMonth - 1) ;
 		iCal.set( Calendar.DAY_OF_MONTH, 	fDay ) ;
 		
 		//시작일자, 시작요일 setting
		String gDate = sDate; 		
 		int calweek = iCal.get(Calendar.DAY_OF_WEEK);
 		
 		//날짜 차이 계산
 		int dayDiff = getDaysFromTo( eDate, sDate );
 		
// 		//마지막일자 구하기(이월처리에 사용)
// 		iChangeCal.set( Calendar.DATE,  1 ) ;
// 		iChangeCal.add( Calendar.MONTH, 1 ) ;
// 		iChangeCal.add( Calendar.DATE, -1 ) ;		
// 		int lastDay = iChangeCal.get( Calendar.DAY_OF_MONTH ) ;
//		
		//날짜만큼 체크된 요일에 대한 날짜 리스트 생성
		for ( int i = 0; i < dayDiff ; i++) {
			//calweek = ( calweek + i ) % 7 ;
			String week = Integer.toString(calweek);
			for ( int j = 0; j < weeklen ; j++) {
				int dbweek = dayofweek[j];
				if ( dbweek >= 0 && dbweek <= 7) {
					if ( Integer.parseInt(week) == dbweek ){
						list.add(gDate);
					}
				}
			}
			//날짜 + 1 처리(월경계를 벗어나는 경우가 있어 변경처리)
			iCal.add( Calendar.DATE, 1 ) ;	
			gDate = getDateFormat(iCal.get(Calendar.YEAR)
								, iCal.get(Calendar.MONTH) + 1
								, iCal.get(Calendar.DAY_OF_MONTH)); 
//			gDate = Integer.toString(Integer.parseInt(gDate) + 1);
			calweek = ( calweek  % 7 ) + 1;
			
//			// 이월여부 확인 -> 한달이 지난경우 월 +1 연산후 해당월 첫날로변경
//			if ( lastDay < SmDateUtil.getDateToInt(gDate, ComConstant.GUBUN_DAY)) {
//				iCal.add(Calendar.MONTH,  1);
//				iCal.set(Calendar.DAY_OF_MONTH, 1 ) ;
//				gDate = getDateFormat(iCal.get(Calendar.YEAR)
//						, iCal.get(Calendar.MONTH) + 1
//						, iCal.get(Calendar.DAY_OF_MONTH)); 
//			}
			
		}
		
 		if ( list != null ) 
 			sArr = list.toArray(new String [0]);
 		
 		return sArr;
 	} 
 	/**
 	* 일자기준으로 처리 List 가져오기
 	* YYYYMMDD 
 	* 단, 요일코드가 0일 경우에는 skip
 	*
 	*/
 	public static String [] getDateFromDate(String fromdate, String todate
 			, String dbstartdate, String dbenddate, Calendar iCal)	{
 		String []  sArr = null;
 		ArrayList<String> list = new ArrayList<String>();

 		//조회기간 & db기간 비교  -> 실제 조회할 기간 산출
 		//조회기간 & db기간 비교  -> 실제 조회할 기간 산출
 		String sDate = "";
 		String eDate ="";
 		if ( Integer.parseInt(fromdate)  >=  Integer.parseInt (dbstartdate))  sDate = fromdate;
 		else sDate = dbstartdate;
 		if ( Integer.parseInt(todate)  <=  Integer.parseInt (dbenddate))  eDate = todate;
 		else eDate = dbenddate;
 
 		
 		//Calendar 생성 ( 주수 확인 및 이월 처리 )
 		int fYear 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_YEAR);
 		int fMonth 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_MONTH);
 		int fDay 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_DAY);
 		
 		iCal.set( Calendar.YEAR, 			fYear ) ;
 		iCal.set( Calendar.MONTH, 			fMonth - 1) ;
 		iCal.set( Calendar.DAY_OF_MONTH, 	fDay ) ;
 		
 		//시작일자, 시작요일 setting
		String gDate = sDate; 	
 		
 		//날짜 차이 계산
 		int dayDiff = getDaysFromTo( eDate, sDate );
 		
// 		//마지막일자 구하기(이월처리에 사용)
// 		iCal.set( Calendar.DATE,  1 ) ;
// 		iCal.add( Calendar.MONTH, 1 ) ;
//		iCal.add( Calendar.DATE, -1 ) ;		
// 		int lastDay = iCal.get( Calendar.DAY_OF_MONTH ) ;
		
		
 		for ( int i = 0; i < dayDiff ; i++) {
			
			list.add(gDate);
			
			//날짜 + 1 처리(월경계를 벗어나는 경우가 있어 변경처리)
			iCal.add( Calendar.DATE, 1 ) ;	
			gDate = getDateFormat(iCal.get(Calendar.YEAR)
								, iCal.get(Calendar.MONTH) + 1
								, iCal.get(Calendar.DAY_OF_MONTH)); 
			
//			// 이월여부 확인 -> 한달이 지난경우 월 +1 연산후 해당월 첫날로변경
//			if ( lastDay < SmDateUtil.getDateToInt(gDate, ComConstant.GUBUN_DAY)) {
//				iCal.add(Calendar.MONTH,  1);
//				iCal.set(Calendar.DAY_OF_MONTH, 1 ) ;
//				gDate = getDateFormat(iCal.get(Calendar.YEAR)
//						, iCal.get(Calendar.MONTH) + 1
//						, iCal.get(Calendar.DAY_OF_MONTH)); 
//			}
 		}
 		
 		if ( list != null ) 
 			sArr = list.toArray(new String [0]);
 		
 		return sArr;
 	} 	 
 	/**
 	 * 매월 반복일정일 경우 해당되는 기간에 해당되는 일자 return
 	 * 반복일자가 말일일 경우는 별도 처리
 	 **/
 	public static String [] getDateFromEveryMonth(String repeatedate, String fromdate, String todate
 			, String dbstartdate, String dbenddate, Calendar iCal)	{
 		
 		String []  sArr = null;
 		ArrayList<String> list = new ArrayList<String>();
 
 		//조회기간 & db기간 비교  -> 실제 조회할 기간 산출
 		String sDate = "";
 		String eDate = "";
 		if ( Integer.parseInt(fromdate)  >=  Integer.parseInt (dbstartdate))  sDate = fromdate;
 		else sDate = dbstartdate;
 		if ( Integer.parseInt(todate)  <=  Integer.parseInt (dbenddate))  eDate = todate;
 		else eDate = dbenddate;
 		
 		//Calendar 생성(시작일자 기준)
 		int fYear 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_YEAR);
 		int fMonth 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_MONTH);
 		int fDay 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_DAY);
 		
 		iCal.set( Calendar.YEAR, 			fYear ) ;
 		iCal.set( Calendar.MONTH, 			fMonth - 1) ;
 		iCal.set( Calendar.DAY_OF_MONTH, 	fDay ) ;
 		
 		//날짜 차이 계산
 		int dayDiff = getDaysFromTo( eDate, sDate );
//		
		//해당기간에 년월이 정보만 추출, 년월이 변경되는 경우에 해당하는 일자유효성 체크후 유효한 경우 list add
 		String tempYearMonth = "";
		String day = "";
		for ( int i = 0; i < dayDiff ; i++) {
			String yearmonth = getYearMonthFormat(iCal.get(Calendar.YEAR)
												, iCal.get(Calendar.MONTH) + 1);
			//월이 바뀌는 경우 년월 정보 갱신(월말일자의 경우 해당월에 마지막일자를 setting)		
			if ( tempYearMonth != null && !tempYearMonth.trim().equals(yearmonth)) {
				tempYearMonth = yearmonth;
				if ( repeatedate != null && repeatedate.trim().equals("99")) {
					day = ComUtil.intToString(iCal.getActualMaximum(Calendar.DAY_OF_MONTH));
				} else {
					day = repeatedate;
				}
			}
			
			//일자 일치하는 경우 list add(단, 유효일자에 한해 처리)
			if ( day != null && day.trim().equals(ComUtil.intToString(iCal.get(Calendar.DAY_OF_MONTH)))) {
				String validdate = SmDateUtil.getValideDateForMonth(yearmonth, ComUtil.fillSpaceToZero(day, 2));
				if ( validdate != null && !validdate.trim().equals("")){
					list.add(validdate);
				}				
			}

			//날짜 + 1 처리(월경계를 벗어나는 경우가 있어 변경처리)
			iCal.add( Calendar.DATE, 1 ) ;
			
		}
		
 		if ( list != null ) 
 			sArr = list.toArray(new String [0]);
 		
 		return sArr;
 	} 
	/**
 	* 최신 유효스케줄 일자 가져오기(매주)
 	* YYYYMMDD 
 	* 단, 요일코드가 0일 경우에는 skip
 	*/ 	
 	public static Calendar getLatestDateFromDayOfWeek(int[] dayofweek, String fromdate
 				, String dbstartdate, String dbenddate, String dbstarttime, String alarm )	{
 		
 		Calendar iCal = Calendar.getInstance();
 		iCal.setTimeInMillis(System.currentTimeMillis());
 		Calendar iCalToday = (Calendar) iCal.clone();
 		
 		int alarmmin = Integer.parseInt(alarm);
 		
 		//요일코드 유효성체크
 		int weeklen = dayofweek.length; 		
 		if ( dayofweek.length != 7 ) return null;

 		//조회기간 & db기간 비교  -> 실제 조회할 기간 산출
 		String sDate = "";
 		String eDate = "";
 		
 		if ( Integer.parseInt(fromdate)  >=  Integer.parseInt (dbstartdate))  sDate = fromdate;
 		else sDate = dbstartdate;
 		
 		//종료일의 경우 알람은 오늘이후일자중  유효값 허용함으로 무조건 db종료일자기준으로 처리
 		eDate = dbenddate;
 		
 		//Calendar 생성 ( 주수 확인 및 이월 처리 )
 		int fYear 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_YEAR);
 		int fMonth 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_MONTH);
 		int fDay 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_DAY);
 		int fHour 	= SmDateUtil.getTimeToInt(dbstarttime, ComConstant.GUBUN_HOUR);
 		int fMinute	= SmDateUtil.getTimeToInt(dbstarttime, ComConstant.GUBUN_MINUTE);
 		int fSecond	= 0;
 		int fMiliSecond	= 0;
 		
 		iCal.set( Calendar.YEAR, 			fYear ) ;
 		iCal.set( Calendar.MONTH, 			fMonth - 1) ;
 		iCal.set( Calendar.DAY_OF_MONTH, 	fDay ) ;
 		iCal.set( Calendar.HOUR_OF_DAY, 	fHour ) ;
 		iCal.set( Calendar.MINUTE, 			fMinute ) ;
 		iCal.set( Calendar.SECOND, 			fSecond ) ;
 		iCal.set( Calendar.MILLISECOND,     fMiliSecond);
 		
 		int calweek = iCal.get(Calendar.DAY_OF_WEEK);
 		
 		//날짜 차이 계산
 		int dayDiff = getDaysFromTo( eDate, sDate );
		
		//날짜만큼 체크된 요일에 대한 날짜 리스트 생성
		for ( int i = 0; i < dayDiff ; i++) {
			String week = Integer.toString(calweek);
			for ( int j = 0; j < weeklen ; j++) {
				int dbweek = dayofweek[j];
				if ( dbweek >= 0 && dbweek <= 7) {
					if ( Integer.parseInt(week) == dbweek ){
						iCal.add(Calendar.MINUTE, - alarmmin);
				        
						if ( iCal.after(iCalToday)) {
							return iCal;
						} else {
							iCal.add(Calendar.MINUTE, alarmmin);
						}
					}
				}
			}
			//날짜 + 1 처리
			calweek = ( calweek  % 7 ) + 1;
			iCal.add(Calendar.DAY_OF_MONTH, 1 ) ;
		}
		
 		return null;
 	} 	
 	/**
 	* 최신 유효스케줄 일자 가져오기(한번만)
 	* YYYYMMDD 
 	* 단, 요일코드가 0일 경우에는 skip
 	* fromdate 는 필수 (dbstartdate, dbenddate 없는 경우 fromdate 기준)
 	*
 	*/
 	public static Calendar getLatestDateFromDate(String fromdate
 			, String dbstartdate, String dbenddate, String dbstarttime, String alarm)	{
 		
 		Calendar iCal = Calendar.getInstance();
 		iCal.setTimeInMillis(System.currentTimeMillis());
 		Calendar iCalToday = (Calendar) iCal.clone();
 		
 		int alarmmin = Integer.parseInt(alarm);
 		
 		//조회기간 & db기간 비교  -> 실제 조회할 기간 산출
 		String sDate = "";
 		String eDate ="";
 		
 		//시작,종료일자가 없는 경우 예외처리
 		if ( dbstartdate == null || ( dbstartdate != null && dbstartdate.trim().equals(""))) {
 			dbstartdate = fromdate;
 		}
 		if ( dbenddate == null || ( dbenddate != null && dbenddate.trim().equals(""))) {
 			dbenddate = fromdate;
 		} 		
 		if ( Integer.parseInt(fromdate)  >=  Integer.parseInt (dbstartdate))  sDate = fromdate;
 		else sDate = dbstartdate;
 		
 		//종료일의 경우 알람은 오늘이후일자중  유효값 허용함으로 무조건 db종료일자기준으로 처리
 		eDate = dbenddate;
 		
 		//Calendar 생성 ( 주수 확인 및 이월 처리 )
 		int fYear 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_YEAR);
 		int fMonth 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_MONTH);
 		int fDay 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_DAY);
 		int fHour 	= SmDateUtil.getTimeToInt(dbstarttime, ComConstant.GUBUN_HOUR);
 		int fMinute	= SmDateUtil.getTimeToInt(dbstarttime, ComConstant.GUBUN_MINUTE);
 		int fSecond	= 0;
 		int fMiliSecond	= 0;

 		iCal.set( Calendar.YEAR, 			fYear ) ;
 		iCal.set( Calendar.MONTH, 			fMonth - 1) ;
 		iCal.set( Calendar.DAY_OF_MONTH, 	fDay ) ;
 		iCal.set( Calendar.HOUR_OF_DAY, 	fHour ) ;
 		iCal.set( Calendar.MINUTE, 			fMinute ) ;
 		iCal.set( Calendar.SECOND, 			fSecond ) ;
 		iCal.set( Calendar.MILLISECOND,     fMiliSecond);
 		
 		//날짜 차이 계산
 		int dayDiff = getDaysFromTo( eDate, sDate );
 		
 		if ( dayDiff >= 0 ) {
			iCal.add(Calendar.MINUTE, - alarmmin);
			if ( iCal.after(iCalToday)){
				return iCal;
			} else {
				iCal.add(Calendar.MINUTE, alarmmin);
			}
 		}
 		
 		return null;
 	} 
	/**
 	* 최신 유효스케줄 일자 가져오기(매월)
 	* YYYYMMDD 
 	*/ 	
 	public static Calendar getLatestDateFromMonth(String repeatedate, String fromdate
 				, String dbstartdate, String dbenddate, String dbstarttime, String alarm )	{
 		
 		Calendar iCal = Calendar.getInstance();
 		iCal.setTimeInMillis(System.currentTimeMillis());
 		Calendar iCalToday = (Calendar) iCal.clone();
 		
 		int alarmmin = Integer.parseInt(alarm);

 		//조회기간 & db기간 비교  -> 실제 조회할 기간 산출
 		String sDate = "";
 		String eDate = "";
 		
 		if ( Integer.parseInt(fromdate)  >=  Integer.parseInt (dbstartdate))  sDate = fromdate;
 		else sDate = dbstartdate;
 		
 		//종료일의 경우 알람은 오늘이후일자중  유효값 허용함으로 무조건 db종료일자기준으로 처리
 		eDate = dbenddate;
 		
 		//Calendar 생성 ( 주수 확인 및 이월 처리 )
 		int fYear 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_YEAR);
 		int fMonth 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_MONTH);
 		int fDay 	= SmDateUtil.getDateToInt(sDate, ComConstant.GUBUN_DAY);
 		int fHour 	= SmDateUtil.getTimeToInt(dbstarttime, ComConstant.GUBUN_HOUR);
 		int fMinute	= SmDateUtil.getTimeToInt(dbstarttime, ComConstant.GUBUN_MINUTE);
 		int fSecond	= 0;
 		int fMiliSecond	= 0;
 		
 		iCal.set( Calendar.YEAR, 			fYear ) ;
 		iCal.set( Calendar.MONTH, 			fMonth - 1) ;
 		iCal.set( Calendar.DAY_OF_MONTH, 	fDay ) ;
 		iCal.set( Calendar.HOUR_OF_DAY, 	fHour ) ;
 		iCal.set( Calendar.MINUTE, 			fMinute ) ;
 		iCal.set( Calendar.SECOND, 			fSecond ) ;
 		iCal.set( Calendar.MILLISECOND,     fMiliSecond);
 		
 		//날짜 차이 계산
 		int dayDiff = getDaysFromTo( eDate, sDate );
		
		//해당기간에 년월 정보만 추출, 년월이 변경되는 경우에 해당하는 일자유효성 체크후 유효한 경우 list add
 		String tempYearMonth = "";
		for ( int i = 0; i < dayDiff ; i++) {
			
			String yearmonth = getYearMonthFormat(iCal.get(Calendar.YEAR)
												, iCal.get(Calendar.MONTH) + 1);

			if ( tempYearMonth != null && !tempYearMonth.trim().equals(yearmonth)) {
				tempYearMonth = yearmonth;
				String day = "";
				if ( repeatedate != null && repeatedate.trim().equals("99")) {
					day = ComUtil.intToString(iCal.getActualMaximum(Calendar.DAY_OF_MONTH));
				} else {
					day = repeatedate;
				}
				if ( iCal.get(Calendar.DAY_OF_MONTH) == ComUtil.stringToInt(day)){
					String validdate = SmDateUtil.getValideDateForMonth(yearmonth, ComUtil.fillSpaceToZero(day, 2));
					if ( validdate != null && !validdate.trim().equals("")){
						iCal.add(Calendar.MINUTE, - alarmmin);			        
						if ( iCal.after(iCalToday)) {
							return iCal;
						} else {
							iCal.add(Calendar.MINUTE, alarmmin);
						}
					}				
				}
			}			
			
			iCal.add(Calendar.DAY_OF_MONTH, 1 ) ;
		}
			
 		return null;
 	} 	
 	/**
 	* 스케줄 일자별 스케줄 data set
 	* 일자(YYYYMMDD), DD, Id, ScheduleName, userId, userName
 	* 월요일   -> YYYYMMDD 
 	*/
 	public static ScheduleInfo [] getScheduleInfoSet(String [] arr, long id, String schedulename, long userid, String username)	{
 		
 		ArrayList<ScheduleInfo> list = new ArrayList<ScheduleInfo>();
 		
 		if (arr != null ) {
	 		int arrLen = arr.length;	
	 		ScheduleInfo sch ;
	 		for ( int i = 0; i < arrLen ; i++) {
	 			sch = new ScheduleInfo();
	 			sch.setScheduleDate(arr[i].toString());
	 			sch.setScheduleId(id);
	 			sch.setScheduleName(schedulename);
	 			sch.setUserId(userid);
	 			sch.setUserName(username);
	 			
	 			list.add(sch);
	 			
	 		}	
 		}
 		
 		return (ScheduleInfo[]) list.toArray();
 	
 	} 

	/**
 	* 달력객체에서 날짜가져오기 (YYYYMMDD)
 	* 일자(YYYYMMDD  - YYYYMMDD)
 	*/
 	public static String getDateFromCal( Calendar iCal )	{
 		
 		String date =  "";
 		if ( iCal != null ) {
 	 		date = 
		 		ComUtil.fillSpaceToZero(
						Integer.toString(iCal.get(Calendar.YEAR)), 4)
			+	ComUtil.fillSpaceToZero(
						Integer.toString(iCal.get(Calendar.MONTH ) +  1) , 2)
			+   ComUtil.fillSpaceToZero(
						Integer.toString(iCal.get(Calendar.DATE)), 2);			
 		}


 		

 		return date;
 	
 	}  	
	/**
 	* d-day 계산 (오늘일자 기준)
 	* 일자(YYYYMMDD  - YYYYMMDD)
 	*/
 	public static int getDateGapFromToday( String todate )	{
 		int dayDiff = 0;
 		Calendar iCal = Calendar.getInstance();

 		String fromToday = 
		 		ComUtil.fillSpaceToZero(
						Integer.toString(iCal.get(Calendar.YEAR)), 4)
			+	ComUtil.fillSpaceToZero(
						Integer.toString(iCal.get(Calendar.MONTH ) +  1) , 2)
			+   ComUtil.fillSpaceToZero(
						Integer.toString(iCal.get(Calendar.DATE)), 2);

 		//valid check 
 		if ( todate != null && todate.length() == 8 ) {
 			if ( isDate ( todate ) ) {
 	 			/////////////
 		 		if ( todate != null && !todate.trim().equals("")) {
 		 			dayDiff = getDaysDiff( fromToday, todate );
 		 		} else {
 		 			dayDiff = 0;
 		 		}			
 			}
 		}
 		
 		return dayDiff;
 	
 	} 	
 	/*
 	 * 날짜 유효성체크
 	 */
 	public static boolean isDate(String dt){ 
        try{ 
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
            format.setLenient(false);  
            format.parse(dt);                
        } catch (ParseException e) { return false; } 
        catch ( IllegalArgumentException e ) { return false; } 
          
        return true; 
    } 	
	/**
 	* d-day String 값 
 	* format : D-000, D+000
 	*/
 	public static String getDDayString( int gap )	{
 		String str = "";

 		if  ( gap == 0 )
 			str = "Today";
 		else if (( gap >  0 ))
 			str = "D+" + Integer.toString(gap);
 		else 
 			str = "D" + Integer.toString(gap);
 		return str;
 	
 	} 	

 	/**
 	* 년도별 solardate 구하기 (년도정보가 없는 경우 현재년도기준)
 	* 윤달의 경우 solardate가 존재하는 최근 년도로 일력을 구함
 	* -.for db
 	*/
 	public static String getSolarFromDb( Context ctx, LunarDataDbAdaper adaper, String leap, String date )	{
 		
 		String solar = "";
 		
 		if ( date ==  null || ( date != null && 
 							      ( date.trim().equals("") || date.trim().length() != 8 ))) return solar;
 		
 		//String year  = getDateToStr(date, ComConstant.GUBUN_YEAR);
 		
// 		//년도정보가 없는 경우 현재년도 구함
// 		if ( year == null || ( year != null && year.trim().equals("")) 
// 				|| ( year != null && year.length() != 4 ) ) {
// 			Calendar iCal = Calendar.getInstance(); 			
// 			year = Integer.toString(iCal.get(Calendar.YEAR));
// 		}
// 		
// 		
// 		solar = LunarDataDbAdaper.getLunarToSolar( ctx, adaper,  leap, date );
 		
 		//윤달의 경우 해당년도 양력이 없을수 있음  -> 평달로 재산출 -> 20120507 평달산출안함
 		
// 		if ( solar == null || ( solar != null && solar.trim().equals("")) 
// 				|| ( solar != null && solar.length() != 8 ) ) {
 			solar = LunarDataDbAdaper.getLunarToSolar( ctx, adaper,  "1", date );
// 			solar = LunarDataDbAdaper.getLunarToSolar( ctx, adaper, leap, date );
// 		}
 		
 		return solar;
 	
 	} 
 	
 	/**
 	* 해당년월 + 날짜에 대한 유효성 체크후 유효일자 return(미유효시 blank return)
 	*/
 	public static String getValideDateForMonth( String yearmonth, String day ) {
		
		String date = "";
		
		if (( yearmonth != null && yearmonth.trim().length() == 6 ) && 
		    ( day != null && day.trim().length() == 2 )){
			Calendar tempCal = Calendar.getInstance();
			tempCal.set(ComUtil.stringToInt(yearmonth.substring(0, 4))
					, ComUtil.stringToInt(yearmonth.substring(4))
					, ComUtil.stringToInt(day));
			if ( tempCal.isLenient()) {
				date =  yearmonth + day;
			} else {
				date = "";				
			}			
		}
		
		return date;
	}
	
}

