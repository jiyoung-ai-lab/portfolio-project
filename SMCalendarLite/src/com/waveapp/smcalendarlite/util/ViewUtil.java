package com.waveapp.smcalendarlite.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.waveapp.smcalendarlite.R;
import com.waveapp.smcalendarlite.common.ComConstant;
import com.waveapp.smcalendarlite.database.InstallCheckDbAdaper;
import com.waveapp.smcalendarlite.database.LunarDataDbAdaper;
import com.waveapp.smcalendarlite.database.ScheduleDbAdaper;
import com.waveapp.smcalendarlite.database.SpecialDayDbAdaper;
import com.waveapp.smcalendarlite.database.UsermanagerDbAdaper;
import com.waveapp.smcalendarlite.info.ScheduleInfo;
import com.waveapp.smcalendarlite.service.SmWidgetUpdate_Service;

public class ViewUtil{
    /*
     * layout parameter 추가 (동적 화면 변동시 사용)
     * parameter: targetview(변경할view), rule(추가할규칙), guideview(기준이나참조할view)
     */
    public static void addViewLayoutParam( View targetview, int rule, View guideview ) {
    	ViewGroup.LayoutParams  params = (ViewGroup.LayoutParams) targetview.getLayoutParams();
    	if ( guideview != null && guideview.getId() > 0 ) {
    		((LayoutParams) params).addRule(rule, guideview.getId() );
    	} else {
    		((LayoutParams) params).addRule(rule);
    	}
    	
    	targetview.setLayoutParams(params);
    } 
	
	public static ColorFilter drawBackGroundColor( int color  ) {
    	ColorFilter filter = null;
    	if ( color != 0 ) {
	        filter = new PorterDuffColorFilter(color,
	        		PorterDuff.Mode.MULTIPLY);
	        //mDraw.setAlpha(50);
	        //mDraw.setColorFilter(filter);
    	}
    	return filter;
    }  
    /*
     * 기념일이미지 가져오기
     */
    public static Drawable  getEventImageResource(Context ctx, String event ) {
    	Drawable img = null ;
    	int i = ComUtil.stringToInt(event);
		switch (i) {
			case 0:
				img = ctx.getResources( ).getDrawable( R.drawable.sm_event_anniversary );	
				break;
			case 1:
				img = ctx.getResources( ).getDrawable( R.drawable.sm_event_birth );	
				break;
			case 2:
				img = ctx.getResources( ).getDrawable( R.drawable.sm_event_deathday );	
				break;
			default:
				img = ctx.getResources( ).getDrawable( R.drawable.sm_event_anniversary );	
				break;
		}		
    	
    	return img;
    } 
    
    /*
     * Flipper Next View 
     */
    public static void  nextFlipper(Context ctx, ViewFlipper flipper ) {
    	flipper.setInAnimation(AnimationUtils.loadAnimation(ctx,  R.anim.appear_from_right));
    	flipper.setOutAnimation(AnimationUtils.loadAnimation(ctx, R.anim.disappear_to_left));
    	flipper.showNext();
    	flipper.clearDisappearingChildren();	   	
    }
    
    /*
     * Flipper Previous View 
     */
    public static void  previousFlipper(Context ctx, ViewFlipper flipper ) {
    	flipper.setInAnimation(AnimationUtils.loadAnimation(ctx,  R.anim.appear_from_left));
    	flipper.setOutAnimation(AnimationUtils.loadAnimation(ctx, R.anim.disappear_to_right));
    	flipper.showPrevious();
    	flipper.clearDisappearingChildren();	   	
    }
    /*
     * LinearLayout View 속성
     */
    public static View setAttLinearView (  LinearLayout targetview, ViewGroup.LayoutParams params, 
    				int ori , int color) {
  		
			targetview.setLayoutParams( params ) ;
			targetview.setOrientation( ori );
			
			if  ( color != 0 )
				targetview.setBackgroundColor(color); 
		
			return targetview;
    }
    public static View setAttLinearView (  LinearLayout targetview, ViewGroup.LayoutParams params, 
    				int ori , Drawable draw ) {
  		
			targetview.setLayoutParams( params ) ;
			targetview.setOrientation( ori );
			
			if  ( draw != null )
				targetview.setBackgroundDrawable(draw); 
		
			return targetview;
    }    
    /*
     * TextView View 속성
     */    
    public static View setAttTextView (  TextView targetview, ViewGroup.LayoutParams params, 
    							   float textsize, int gravity, int leftmargin, int rightmargin, int weight, boolean moreline ) {
 
    	targetview.setLayoutParams( params ) ;
		
		targetview.setTextSize( textsize ) ;
		targetview.setGravity(gravity);
		
		if ( !moreline ) {
			targetview.setEllipsize(TruncateAt.END);
			targetview.setSingleLine(true);
		} else {
			targetview.setSingleLine(false);
		}
		
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) targetview.getLayoutParams();
		
		if ( leftmargin > 0 ) lp.leftMargin = leftmargin;
		if ( rightmargin > 0 ) lp.rightMargin = rightmargin;
		if ( weight > 0 ) lp.weight = weight;
		
		/* 변경된 값으로 적용 */
		targetview.setLayoutParams( lp ) ;
		
		return targetview;
    } 
    
    /*
     * Day of Week (요일) 리스트 Display 
     * -.3개이상연속되는 경우는 연결해서 처리
     */
    public static void getDayOfWeekTextSet(Context ctx, View parentview, View childview[] , ScheduleInfo schedule ) {

   	 	//초기화
    	((ViewGroup) parentview).removeAllViewsInLayout();
    	
    	//요일정보가 없는 경우 return
    	if ( schedule.getDayOfWeek() == null )  return;
   	 
    	//TextView size / style
//  		int cHeight 	= 28;
//   		int cWidth 		= 28;
//   		int cTextSize 	= 11;  
  		int cHeight 	= (int) (17 * getDensity(ctx));
   		int cWidth 		= (int) (18 * getDensity(ctx));
   		int cTextSize 	= 11 ;
//   		if ( getDensity(ctx) == 2 ) {
//   			cTextSize 	= (int) (6  * getDensity(ctx));
//   		}  
   		
    	ArrayList< String > weekArr  = new ArrayList< String  >();
    	ArrayList < String > arr = new ArrayList < String > () ;
    	
    	//1) 요일 Text Array 만듦 (TextView 생성 수만큼 Array)
   		int[] dayofweek = schedule.getDayOfWeek();
   		int len = dayofweek.length;

   		for ( int i = 0 ; i < len ; i++ ) {
			//다국어의 경우 요일명이 1글자이상인 경우 있음
			String weekStr = SmDateUtil.getDayOfWeekFromCode( ctx, dayofweek[ i ]);
			if ( weekStr.length() > 2 ) weekStr = weekStr.substring(0,2);
			
			//요일 존재시 buffer에 저장
			if ( dayofweek[ i ] > 0 && i < 6 ) {
				arr.add( weekStr );
			} else {
				 //마지막건은 예외처리
				if ( dayofweek[ i ] > 0 && i == 6 ) {
					arr.add( weekStr );
				}
				//Array Size에 따라 따라 요일연속성 체크
				int arrlen = arr.size();
				if ( arrlen == 1 ) {
					weekArr.add( arr.get(0).toString() );
				} else if ( arrlen == 2 ) {
					weekArr.add( arr.get(0).toString() );;
					weekArr.add( arr.get(1).toString() );
				} else if ( arrlen > 2 ) {
					weekArr.add(arr.get(0).toString() + "~" +  arr.get( arrlen - 1).toString());
				}
				arr.clear();
			}
		}
   		
   		//2) TextView 생성
   		 int cnt = weekArr.size();
   		 for ( int j = 0 ; j < cnt ; j++ ) {
   			 String weekFullText = weekArr.get(j).toString();
   			 
   			 int wCnt = weekFullText.length();
   			 if ( wCnt > 2  ) {
   				childview[ j ] = addDayofWeekTextView(ctx, parentview, cTextSize, cWidth * 2, cHeight);
   				((TextView) childview[ j ]).setText( weekFullText );					 
   			 } else {
   				childview[ j ] = addDayofWeekTextView(ctx, parentview, cTextSize, cWidth , cHeight);
   				((TextView) childview[ j ]).setText( weekFullText );					 
   			 }

   			 
   		 }
   	 }  
    /*
     * Month repeat (반복일) 리스트 Display 
     * -.array값 display
     */
    public static void getRepeatMonthTextSet(Context ctx, View parentview, View childview[] , ScheduleInfo schedule ) {

   	 	//초기화
    	((ViewGroup) parentview).removeAllViewsInLayout();
    	
    	//월반복일자 정보가 없는 경우 return
    	String repeatedate = schedule.getRepeatdate();
    	if ( repeatedate == null )  return;
    	
  		int cHeight 	= (int) (17 * getDensity(ctx));
   		int cWidth 		= (int) (17 * getDensity(ctx));
   		int cTextSize 	= 11 ;
   		
		childview[ 0 ] = addDayofWeekTextView(ctx, parentview, cTextSize, cWidth * 2, cHeight);
		((TextView) childview[ 0 ]).setText( ComUtil.getSpinnerText(ctx, R.array.arr_repeatdate_key, R.array.arr_repeatdate, repeatedate) );

   		

   	 }      
    public static View  addDayofWeekTextView ( Context ctx, View parentview, int tsize, int width, int height ) {
    	
    	TextView childview = new TextView( ctx ) ;
    	
    	((ViewGroup) parentview).addView( childview );	
    	
    	((TextView) childview).setGravity(Gravity.CENTER);
    	((TextView) childview).setBackgroundDrawable(ctx.getResources().getDrawable(R.drawable.sm_list_dayofweek));
    	((TextView) childview).setTextSize(tsize);
    	((TextView) childview).setTextColor(ctx.getResources().getColor(R.color.white));
	 
    	((TextView) childview).setLayoutParams (new LinearLayout.LayoutParams( width ,  height ));
    	

    	return childview;
    }
//    public static View  addDayofWeekTextView2 ( Context ctx, View parentview, int tsize, int width, int height, Drawable dw ) {
//    	
//    	TextView childview = new TextView( ctx ) ;
//    	
//    	((ViewGroup) parentview).addView( childview );	
//    	
//    	((TextView) childview).setGravity(Gravity.CENTER);
//    	((TextView) childview).setBackgroundDrawable(ctx.getResources().getDrawable(R.drawable.sm_list_dayofweek));
////    	((TextView) childview).setBackgroundDrawable(dw);
//    	((TextView) childview).setTextSize(tsize);
//    	((TextView) childview).setTextColor(ctx.getResources().getColor(R.color.white));
//	 
//    	((TextView) childview).setLayoutParams (new LinearLayout.LayoutParams( width ,  height ));
//    	
//
//    	return childview;
//    }   
    /*
     * 언어정보 동적변경(다국어지원)
     * -.설정파일과 동일하게 처리, 불일치시 언어정보 변경
     * -.언어정보 변경시 해당 페이지 refresh처리
     */
    public static void  setLocaleFromPreference ( Context ctx, Class<?> cname ) {

    	String prefLang = ComConstant.LOCALE;
    	
    	//설정내 언어값과 app config 정보 불일치시 config변경처리
        Locale cfgLocale = ctx.getResources().getConfiguration().locale; 
        
//        ComUtil.showToast(ctx, cfgLocale.getCountry());
//        ComUtil.showToast(ctx, cfgLocale.getLanguage());
        if ( prefLang != null && !prefLang.equals(cfgLocale.getLanguage())) {
        	String language = prefLang;
        	
        	Configuration config = ctx.getResources().getConfiguration();
        	Locale nLocale = new Locale(language);
        	Locale.setDefault(nLocale);
        	config.locale = nLocale;
        	
        	ctx.getResources().updateConfiguration(config, ctx.getResources().getDisplayMetrics()); 
   	
        }

    }   
    /*
     * 전체달력 여부 값 가져오기
     * -.환경설정에서 전체달력 체크여부 값 가져오기
     */
    public static boolean  isFullCalendarFromPreference  ( Context ctx  ) {
    	
        //선택언어에 따른 변수정보 setting (string.xml에서 comconstant value setting)
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
        boolean fullcalendar = sharedPref.getBoolean("fullcalendar_pref", true);
     	return fullcalendar;

    } 
    /*
     * 위젯배경 값 초기화
     */
    public static void  setWidgetStyleFromPreference ( Context ctx ) {

     	
        //배경테마 기본 설정값 없는 경우 idx 0으로 setting
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor sharedEditor = sharedPref.edit();
        
        String widgetback = sharedPref.getString("widgetback_pref", "");
        
        String [ ] arr =  ctx.getResources().getStringArray(R.array.arr_widgetback_key);
        
        if(widgetback == null ||( widgetback != null && widgetback.trim().equals(""))){
            if ( arr != null && arr.length > 0 ) {
            	widgetback = arr[0].toString();
            	sharedEditor.putString("widgetback_pref", widgetback.trim());
            	sharedEditor.commit();
            }        	
        }

        //선택된 위젯배경테마에 따라 layout 및 배경색 선택
        setWidgetStyle(ctx, widgetback);

    } 
    /*
     * 위젯배경값 가져오기
     * 0:배경값
     * 1:글짜색
     */
    public static void  setWidgetStyle ( Context ctx, String widgetback ) {

        //선택된 위젯배경테마에 따라 layout 및 배경색 선택
        if ( widgetback != null && !widgetback.trim().equals("")) {

        	//반투명회색(w)
        	if ( widgetback.equals("tgw")) {
        		ComConstant.WIDGET_BACK 	 	= R.drawable.sm_widget_back;
        		ComConstant.WIDGET_FONTCOLOR 	= R.color.white;
        	//투명(w)	
        	} else if ( widgetback.equals("tw")) {
        		ComConstant.WIDGET_BACK  		= 0;
        		ComConstant.WIDGET_FONTCOLOR 	= R.color.white;      	
        	//투명(b)
        	} else if ( widgetback.equals("tg")) {
        		ComConstant.WIDGET_BACK  		= 0;
        		ComConstant.WIDGET_FONTCOLOR 	= R.color.black;
        	}
        } else {
    		ComConstant.WIDGET_BACK 	 	= R.drawable.sm_widget_back;
    		ComConstant.WIDGET_FONTCOLOR 	= R.color.white;        	
        }

    }    

    /*
     * 해상도에 따른 dip 산출 
     */
    public static int  getDipFromDensity  ( Context ctx, int pixcel  ) {
    	float density	= getDensity(ctx);
    	int dip = (int) (pixcel * (160 / density));      
    	return dip;
    } 
    /*
     * density 
     */
    public static float  getDensity  ( Context ctx ) {
    	float density	= ctx.getResources( ).getDisplayMetrics( ).density ;
    	return density;
    } 
    
    /*
     * 위젯에서 사용되는 일정정보(일일)
     */
    
    public static ArrayList<ScheduleInfo> getTodaySchedule ( Context context , String scheduledate ) {
		
    	ArrayList<ScheduleInfo> m_ScheduleList = new ArrayList<ScheduleInfo>();
		//일자별스케줄 조회후 view에 setting
		// 1.기념일,휴일
		// 2.스케줄
    	SpecialDayDbAdaper mDbSpecialHelper = new SpecialDayDbAdaper(context);
		LunarDataDbAdaper mDbLunarHelper = new LunarDataDbAdaper(context);
    	mDbSpecialHelper.open();  
		mDbLunarHelper.open();    	
    	m_ScheduleList.addAll(getSpecialdayPerDate( context, mDbSpecialHelper, mDbLunarHelper, scheduledate ));
    	mDbLunarHelper.close();
		mDbSpecialHelper.close();
		
    	ScheduleDbAdaper mDbScheduleHelper = new ScheduleDbAdaper(context);
    	mDbScheduleHelper.open();     
    	UsermanagerDbAdaper mDbUser = new UsermanagerDbAdaper(context);
        mDbUser.open();    	
    	m_ScheduleList.addAll(getSchedulePerDate( context, mDbScheduleHelper, mDbUser, scheduledate ));
    	mDbUser.close();
    	mDbScheduleHelper.close();
		return m_ScheduleList;		

	}
    /*
     * 위젯에서 사용되는 일정정보(기간)
     */
    
    public static  ArrayList<ArrayList<ScheduleInfo>> getPeriodSchedule ( Context context , String [] scheduledateArr ) {
		
    	ArrayList<ArrayList<ScheduleInfo>> m_ScheduleList = new  ArrayList<ArrayList<ScheduleInfo>>();
    	
    	//예외처리
    	if ( scheduledateArr == null || (scheduledateArr != null && scheduledateArr.length <= 0)) {
    		return m_ScheduleList;
    	}
    	 
		//일자별스케줄 조회후 view에 setting
		// 1.기념일,휴일
		// 2.스케줄
    	SpecialDayDbAdaper mDbSpecialHelper = new SpecialDayDbAdaper(context);
		LunarDataDbAdaper mDbLunarHelper = new LunarDataDbAdaper(context);
    	ScheduleDbAdaper mDbScheduleHelper = new ScheduleDbAdaper(context);   
    	UsermanagerDbAdaper mDbUser = new UsermanagerDbAdaper(context);
    	mDbSpecialHelper.open();  
		mDbLunarHelper.open();  
    	mDbScheduleHelper.open();  
        mDbUser.open();  		
		ArrayList<ScheduleInfo> sList = null;
		int len = scheduledateArr.length;
    	for ( int i = 0 ; i < len ; i++) {    		
    		sList = new ArrayList<ScheduleInfo>();
    		String scheduledate = scheduledateArr[i].toString();
    		if ( scheduledate != null && scheduledate.length() == 8 ) {
    			sList.addAll(getSpecialdayPerDate( context, mDbSpecialHelper, mDbLunarHelper, scheduledate ));
    			sList.addAll(getSchedulePerDate( context, mDbScheduleHelper, mDbUser, scheduledate ));
    			m_ScheduleList.add(i,sList);
    		}
    	}
    	mDbUser.close();
    	mDbScheduleHelper.close();    	
    	mDbLunarHelper.close();
		mDbSpecialHelper.close();

		return m_ScheduleList;		

	}   
    /*
     * 일별 기념일, 공휴일조회(위젯에서 사용)
     */
    public static ArrayList<ScheduleInfo> getSpecialdayPerDate( Context context, SpecialDayDbAdaper mDbSpecialHelper, LunarDataDbAdaper mDbLunarHelper, String scheduledate ) {

    	Cursor cursol = null;
    	Cursor curlun = null;
    	int arrayLength = 0;
    	ArrayList<ScheduleInfo> scheduleList = new ArrayList<ScheduleInfo>() ;
    	
    	String year = scheduledate.substring(0, 4);
    	String monthday = scheduledate.substring(4);

    	//양력
    	if ( scheduledate != null && scheduledate.length() == 8 ) {
    		cursol = mDbSpecialHelper.fetchSpecialDayForSolar( year, monthday );
    	}

    	//음력
    	if ( scheduledate != null && scheduledate.length() == 8 ) {
    		String [] sDate = LunarDataDbAdaper.getSolarToLunar(context, mDbLunarHelper, scheduledate);
    		year = SmDateUtil.getDateToStr(sDate[0], ComConstant.GUBUN_YEAR);
    		monthday = SmDateUtil.getDateToStr(sDate[0], ComConstant.GUBUN_MONTH)
    				+  SmDateUtil.getDateToStr(sDate[0], ComConstant.GUBUN_DAY);
    		curlun = mDbSpecialHelper.fetchSpecialDayForLunar( year, monthday, sDate[1]);
    	}  

        //data setting
    	//양력(국공일,기념일 모두 포함)  -> 국공일의 경우 local 체크
    	arrayLength = cursol.getCount();
		for(int i = 0 ; i < arrayLength ; i++)
		{
			String locale = ComUtil.setBlank(cursol.getString(cursol.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_LOCALE)));
			String gubun = ComUtil.setBlank(cursol.getString(cursol.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_GUBUN)));
			if ( locale != null && gubun != null && gubun.trim().equals(ComConstant.PUT_BATCH)
					&& !locale.trim().equals(ComConstant.NATIONAL)) {
				///////////////
			} else {
				ScheduleInfo info = new ScheduleInfo();
				long id = cursol.getLong((cursol.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_ID)));
				info.setId(id);
				info.setScheduleName(cursol.getString(cursol.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_NAME)));
				info.setSubName(cursol.getString(cursol.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_SUBNAME)));
				info.setScheduleDate(scheduledate);
				info.setCycle(cursol.getString(cursol.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_REPEATYN)));
				info.setHolidayYn(cursol.getString(cursol.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_HOLIDAYYN)));
				info.setScheduleGubun(gubun);
				//양력인 경우 입력한 날짜
				String syear = cursol.getString(cursol.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_YEAR));
				String smonthday = cursol.getString(cursol.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_MONTHDAY));
				
				String fulltext = ComUtil.getSpinnerText(context, R.array.arr_leap_key, R.array.arr_leap, "0")
								+ " " 
								+ SmDateUtil.getDateFullFormat(context, syear + smonthday, false, false);
				info.setDayOfWeekFullText(fulltext);
				
				scheduleList.add(info);
			}

			//다음건 처리
			cursol.moveToNext();
		}

    	//음력
    	arrayLength = curlun.getCount();
		for(int i = 0 ; i < arrayLength ; i++)
		{
			ScheduleInfo info = new ScheduleInfo();
			long id = curlun.getLong((curlun.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_ID)));
			info.setId(id);
			info.setScheduleName(curlun.getString(curlun.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_NAME)));
			info.setSubName(curlun.getString(curlun.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_SUBNAME)));
			info.setScheduleDate(scheduledate);
			info.setCycle(curlun.getString(curlun.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_REPEATYN)));
			info.setScheduleGubun(curlun.getString(curlun.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_GUBUN)));
			info.setHolidayYn(curlun.getString(curlun.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_HOLIDAYYN)));
			
			//음력인 경우 임시로 음력일자 setting
			String syear = curlun.getString(curlun.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_YEAR));
			String smonthday = curlun.getString(curlun.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_MONTHDAY));
			String sleap = curlun.getString(curlun.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_LEAP));
			String fulltext = ComUtil.getSpinnerText(context, R.array.arr_leap_key, R.array.arr_leap, sleap)
							+ " " 
							+ SmDateUtil.getDateFullFormat( context, syear + smonthday, false, false);
			
			info.setDayOfWeekFullText(fulltext);
			
			scheduleList.add(info);
			//다음건 처리
			curlun.moveToNext();
		}

		if ( cursol != null ) cursol.close();
		if ( curlun != null ) curlun.close();
		
		return scheduleList;
    }
	/*
     * 일별 스케줄 db에서 조회 
     */
    public static ArrayList<ScheduleInfo> getSchedulePerDate(  Context context, ScheduleDbAdaper mDbScheduleHelper, UsermanagerDbAdaper mDbUser, String scheduledate ) {
    	
    	// issue : 데이터건수에 따른 처리 필요 (to-do)
    	// 1. 일자별 스케줄 db select -> scheduleinfo에 set
    	// 2. ExpandedView에 setting
    	//    -. parent : string,  child : scheduleinfo array
    	Cursor cur;
    	ArrayList<ScheduleInfo> scheduleList = new ArrayList<ScheduleInfo>() ;
    	Calendar iCal = Calendar.getInstance( ) ;

    	//일별 스케줄 정보 db select
    	cur = mDbScheduleHelper.fetchSchedulePerDate(scheduledate);
        
        //data setting
    	int arrayLength = cur.getCount();    	
		for(int i = 0 ; i < arrayLength ; i++)
		{
			ScheduleInfo info = new ScheduleInfo();
			long userid = cur.getLong((cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_USERID)));
			info.setUserId(userid);
			info.setUserName(UsermanagerDbAdaper.getUserName(context, mDbUser, userid));
			info.setUserColor(UsermanagerDbAdaper.getUserColor(context, mDbUser, userid));
			long scheduleid = cur.getLong((cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_SCHDULEID)));
			info.setId(scheduleid);				
			info.setScheduleName(cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_NAME)));
			
			String sCycle = cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_CYCLE));
			info.setCycle(sCycle);
			
			info.setStartDate(cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_STARTDATE)));
			info.setEndDate(cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_ENDDATE)));
			info.setStartTime(cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_STARTTIME)));
			info.setEndTime(cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_ENDTIME)));	
			info.setAlarmYn(cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_ALARM)));
			info.setAllDayYn(cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_ALLDAYYN)));
			info.setAlarm2(cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_ALARM2)));
			info.setRepeatdate(cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_REPEATDATE)));

			//요일별 value set
			String sDayOfWeek = "";
			int[] arrDayofweek = new int [7];		
			if (sCycle != null && sCycle.trim().equals("Y")) {
						
				arrDayofweek[0] = ComUtil.setYesReturnValue(
						cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_SUN)), ComConstant.DAYOFWEEK_SUNDAY);
				arrDayofweek[1] = ComUtil.setYesReturnValue(
						cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_MON)), ComConstant.DAYOFWEEK_MONDAY);
				arrDayofweek[2] = ComUtil.setYesReturnValue(
						cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_TUE)), ComConstant.DAYOFWEEK_TUESDAY);
				arrDayofweek[3] = ComUtil.setYesReturnValue(
						cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_WEN)), ComConstant.DAYOFWEEK_WEDNESDAY);
				arrDayofweek[4] = ComUtil.setYesReturnValue(
						cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_THU)), ComConstant.DAYOFWEEK_THURSDAY);
				arrDayofweek[5] = ComUtil.setYesReturnValue(
						cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_FRI)), ComConstant.DAYOFWEEK_FRIDAY);
				arrDayofweek[6] = ComUtil.setYesReturnValue(
						cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_SAT)), ComConstant.DAYOFWEEK_SATURDAY);
				info.setDayOfWeek(arrDayofweek);
				
				sDayOfWeek =  ComUtil.setYesReturnValue(
								cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_SUN)), ComUtil.getStrResource(context, R.string.sunday)).substring(0,1)
					+ "" + ComUtil.setYesReturnValue(
								cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_MON)), ComUtil.getStrResource(context, R.string.monday)).substring(0,1)
					+ "" + ComUtil.setYesReturnValue(
								cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_TUE)), ComUtil.getStrResource(context, R.string.tuesday)).substring(0,1)
					+ "" + ComUtil.setYesReturnValue(
								cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_WEN)), ComUtil.getStrResource(context, R.string.wednesday)).substring(0,1)
					+ "" + ComUtil.setYesReturnValue(
								cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_THU)), ComUtil.getStrResource(context, R.string.thursday)).substring(0,1)
					+ "" + ComUtil.setYesReturnValue(
								cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_FRI)), ComUtil.getStrResource(context, R.string.friday)).substring(0,1)
					+ "" + ComUtil.setYesReturnValue(
								cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_SAT)), ComUtil.getStrResource(context, R.string.saturday)).substring(0,1);
			}				
			info.setDayOfWeekFullText(sDayOfWeek);
			
			
			String dbstartdate = info.getStartDate();
			String dbenddate   = info.getEndDate();		
			//매주  : 요일 체크
			//없음  : 기간전부
			String [] loop ;			
			if ( sCycle  != null && sCycle.equals("Y")) {
				loop = SmDateUtil.getDateFromDayOfWeekArr(arrDayofweek, scheduledate, scheduledate, dbstartdate, dbenddate, iCal);
			} else if ( sCycle  != null && sCycle.equals("M")) {
				loop = SmDateUtil.getDateFromEveryMonth(info.getRepeatdate(), scheduledate, scheduledate, dbstartdate, dbenddate, iCal);
				
			} else {
				loop = new String [] { scheduledate };				
			}

			//해당되는 날짜만큼 처리
			//ExpendableList 객체에 데이터 add (정규스케줄의 경우 한스케줄에 대해 여러일자가 도출될수 있음)
			if ( loop != null ) {
				
				int len = loop.length;
				for ( int idate = 0 ; idate < len ; idate++ ) {
					info.setScheduleDate(loop[idate].toString());
					info.setScheduleGubun("S");
					scheduleList.add(info);
				}
				
			}
			//다음건 처리
			cur.moveToNext();
		}

    	
		if ( cur != null ) cur.close();
//		mDbUser.close();
		
		return scheduleList;
		
    } 
    
	/*
	 * 설정xml에서 알람모드값을 가져와 처리(소리,진동)
	 */
    public static  String getAlarmModeFromPref( Context ctx ) {
		
        //선택언어에 따른 변수정보 setting (string.xml에서 comconstant value setting)
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor sharedEditor = sharedPref.edit();

        //설정 불러오기
        String alarmmode = sharedPref.getString("alarm_pref","");
         
        //preference 에 알람모드가 설정되지 않은 경우 강제로 "S"
        if(alarmmode == null ||( alarmmode != null && alarmmode.trim().equals(""))){
        	sharedEditor.putString("alarm_pref", "S");
        	sharedEditor.commit();
        }
        
        return alarmmode ;
        
	}
    
    /*
     * Exist Check Database
     */
    public static  boolean isdatabaseExist( Context context  ) {
    	boolean ret = true;

        
        InstallCheckDbAdaper mCheckDbHelper = new InstallCheckDbAdaper(context);
        mCheckDbHelper.open();
        ret = mCheckDbHelper.fetchInstallCheck();
        mCheckDbHelper.close();
    	
    	//오류로인한삭제방지로 default true
    	return ret;    	
    }
    /*
     * 위젯 갱신 : 응답속도 문제로 별도 service로 처리
     */
    public static void  updateAllWidget  ( Context ctx  ) {
    	
    	Calendar iCal = Calendar.getInstance();
    	long starttime = iCal.getTimeInMillis() + 500; 
    	
    	Intent intent = new Intent( ctx,  SmWidgetUpdate_Service.class );
    	PendingIntent mAlarmSender = PendingIntent.getService(ctx, ComConstant.WIDGET_REQ, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        
    	//Alarm Service Start
        AlarmManager am = (AlarmManager)ctx.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, starttime, mAlarmSender); 
    	
    	return ;

    }    
	    
//    /*
//     * 해상도에 따른 확대비
//     */
//    public static int  getRadioFromDensity  ( Context ctx, int pixcel  ) {
//    	float density	= ctx.getResources( ).getDisplayMetrics( ).density ;
//    	int dip			= ctx.getResources( ).getDisplayMetrics( ).densityDpi ;
//    	
//    	int radio = 160 / ( dip / density ) ;
////    	int dip = (int) (pixcel * (160 / density));      
//    	return dip;
//    }  

//	/*
//	 * top menu pref
//	 */
//    public static void setTopMenuVisableToPref ( Context ctx, int topvisable ) {
//		
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
//        SharedPreferences.Editor sharedEditor = sharedPref.edit();
//    	sharedEditor.putString("topmenuview_pref", Integer.toString(topvisable));
//    	sharedEditor.commit(); 
//    	
//    	ComConstant.TOPVISABLE = topvisable;
//	}
//    //최초...
//    public static void getTopMenuVisableFromPref ( Context ctx ) {
//		
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
//        SharedPreferences.Editor sharedEditor = sharedPref.edit();
//
//        String topvisable = sharedPref.getString("topmenuview_pref","");
//         
//        //preference 에 언어설정이 안되어있는 경우 단말기 locale 정보로 setting
//        if(topvisable == null ||( topvisable != null && topvisable.trim().equals(""))){
//        	sharedEditor.putString("topmenuview_pref", Integer.toString(0));
//        	sharedEditor.commit();
//        } 
//   	
//    	ComConstant.TOPVISABLE =  Integer.parseInt(topvisable);
//        
//	}    
  

}
