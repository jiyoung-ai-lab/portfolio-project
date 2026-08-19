package com.waveapp.smcalendarlite.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.waveapp.smcalendarlite.R;
import com.waveapp.smcalendarlite.SMActivity;
import com.waveapp.smcalendarlite.common.ComConstant;


public class gsCalendar extends SMActivity 
{
	
	Context m_context ;				/// context 
	
	LinearLayout m_targetLayout ;	/// 달력을 넣을 레이아웃
	ScrollView m_targetScroll ;		/// 달력을 넣을 animation layout
	
	Button [] m_controlBtn ;		/// 달력 컨트롤할 버튼 4개 [전년도, 다음년도, 전달, 다음달]
	//TextView [] m_viewTv ;			/// 년 월 일 표시할 텍스트뷰 3개[년, 월 , 일]
	
	Calendar m_Calendar ;			/// 사용할 달력
	Calendar m_TodayCal ;			/// 시스템시간기준 오늘 기준달력
	
	LinearLayout [ ] m_lineLy ;		/// 7라인 요일표시 + 최대 6주
	LinearLayout [ ] m_cellTopLy ;	/// click event 처리용
	
	FrameLayout[ ]  m_cellFrame ;   /// cell image handling 용(전방 흐린 이미지 처리 등..)
	ImageView[ ] 	m_cellView ;	/// 전방 흐린 이미지 (경우에 따라 사용)
	
    LinearLayout [ ] m_cellLy ;		/// 7칸 달력일자, 기념일 출력용 linearlayout    
    TextView [ ] m_cellDate ;		/// 날짜 textview    
    TextView [ ] m_cellHoliday  ;	/// 국공일 textview
    TextView [ ] m_cellLunar  ;		/// 음력 textview
    
    LinearLayout [ ] m_cellScheduleLin ;		//스케줄이 표시될 곳
    TextView [ ][ ]  m_cellSchedule ;			//스케줄 건수만큼 ( 달력일수 * 건수)
    //m_cellTwenteenImg
    
    LinearLayout [ ] m_horizontalLine ; /// 경계선 라인 가로
    LinearLayout [ ] m_verticalLine ;	/// 경계선 라인 세로
    
    int m_startPos ;				/// 요일을 찍기 시작 할 위치
    int m_lastDay ;					/// 그 달의 마지막날
    int m_selDay ;					/// 현재 선택된 날짜
    
    int m_clickPos;					/// 선택한 cell position (just 날짜 )
    boolean m_isFullCalendar = false;
//    ArrayList<String> m_calFullDate ;	/// 날짜 표시용 텍스트
    String[ ] m_calFullDate ;	/// 날짜 표시용 텍스트 (YYYYMMDD)
    
	////////////////////////////////////////    	
    
    float m_displayScale ;			/// 화면 사이즈에 따른 텍스트 크기 보정값 저장용
    float m_textSize ;				/// 텍스트 사이즈(위 라인의 변수와 곱해짐)
    float m_topTextSize ;			/// 요일텍스트 사이즈(역시 보정값과 곱해짐)
    float m_textSizeHoliday ;		/// 텍스트 사이즈(위 라인의 변수와 곱해짐)
    float m_textSizeSchedule ;		/// 텍스트 사이즈(위 라인의 변수와 곱해짐)
//    float m_textLunar ;				/// 텍스트 사이즈(위 라인의 변수와 곱해짐)
    float m_textGangi ;				/// 텍스트 사이즈(위 라인의 변수와 곱해짐)
    
    int m_displayWidth = 0;
    int m_displayHeight = 0;
    int m_tcHeight = 50 ;			/// 요일 들어가는 부분 한칸의 높이
    int m_cWidth = 50 ;				/// 한칸의 넓이
    int m_cHeight = 50 ;			/// 한칸의 높이
    int m_lineSize = 1 ;			/// 경계선의 굵기
    
    public static class gsCalendarColorParam
    {
    	public int m_lineColor 				= 0xff000000 ;	/// 경계선 색
        public int m_cellColor 				= 0xffffffff ;	/// 칸의 배경색
        public int m_topCellColor 			= 0xffcccccc ;	/// 요일 배경색
        public int m_textColor 				= 0xff000000 ;	/// 글씨색
        public int m_sundayTextColor 		= 0xffff0000 ;	/// 일요일 글씨색
        public int m_saturdayTextColor 		= 0xff0000ff ;	/// 토요일 글씨색
        public int m_topTextColor 			= 0xff000000 ; 	/// 요일 글씨색
        public int m_topSundayTextColor 	= 0xffff0000 ; 	/// 요일 일요일 글씨색
        public int m_topSaturdatTextColor 	= 0xff0000ff ; 	/// 요일 토요일 글씨색
        
        public int m_todayCellColor		= 0x999999ff ;	/// 선택날짜의 배경색
        public int m_todayTextColor		= 0xffffffff ;  /// 선택날짜의 글씨색
    }
    
    gsCalendarColorParam m_colorParam ;
    
    /// 있으면 적용하고 없으면 bgcolor로 처리함( 각각 개별적으로 )
    Drawable m_bgImgId = null ;				/// 전체 배경이미지
    Drawable m_cellBgImgId = null ;			/// 한칸의 배경 이미지
    Drawable m_topCellBgImgId = null ;		/// 상단 요일 들어가는 부분의 배경 이미지
    
    Drawable m_todayCellBgImgId = null ; 	/// 오늘 날짜의 배경 이미지
    Drawable m_selectCellBgImgId = null ; 	/// 선택 날짜의 배경 이미지
    
    Drawable m_scheduleCellBgImgId = null ; 	/// 스케줄 이미지
    //Drawable m_specialdayCellBgImgId = null ; 	/// 기념일
    
    /// 상단에 표시하는 요일 텍스트
    String [] m_dayText;
    ///////////////////////////////////////////
    
    Button m_preYearBtn ;			/// 전년도 버튼
    Button m_nextYearBtn ;			/// 다음년도 버튼
    Button m_preMonthBtn ;			/// 전월 버튼
    Button m_nextMonthBtn ;			/// 다음월 버튼
    
    ImageButton m_preYearImgBtn ;			/// 전년도 버튼
    ImageButton m_nextYearImgBtn ;			/// 다음년도 버튼
    ImageButton m_preMonthImgBtn ;			/// 전월 버튼
    ImageButton m_nextMonthImgBtn ;			/// 다음월 버튼
    
    ImageButton m_close ;					/// 창닫기버튼
    
    TextView m_yearTv ;				/// 년 표시용 텍스트
    TextView m_mothTv ;				/// 월 표시용 텍스트
    TextView m_dayTv ;				/// 날짜 표시용 텍스트
  	
    /// 생성자
	public gsCalendar( Context context, LinearLayout layout)
	{		
		
		m_context 		= context ;
		m_targetLayout 	= layout ;
		/// 오늘 날짜로 달력 생성
		m_Calendar 		= Calendar.getInstance( ) ;
		m_TodayCal		= getCalendar();
		layoutInitialization();

	} 
	public gsCalendar( Context context, LinearLayout layout, ScrollView scroll )
	{		
		
		m_context 		= context ;
		m_targetLayout 	= layout ;
		m_targetScroll 	= scroll;
		/// 오늘 날짜로 달력 생성
		m_Calendar 		= Calendar.getInstance( ) ;	
		m_TodayCal		= getCalendar();
		layoutInitialization();

	} 	
	public gsCalendar( Context context, LinearLayout layout, ScrollView scroll, boolean isfull )
	{		
		
		m_context 		= context ;
		m_targetLayout 	= layout ;
		m_targetScroll 	= scroll;
		/// 오늘 날짜로 달력 생성
		m_Calendar 			= Calendar.getInstance( ) ;	
		m_isFullCalendar 	= ViewUtil.isFullCalendarFromPreference(context);
		m_TodayCal			= getCalendar();
		layoutInitialization();

	} 	
	public gsCalendar( Context context, LinearLayout layout, Calendar cal )
	{
		m_context 		= context ;
		m_targetLayout 	= layout ;	
		/// 오늘 날짜로 달력 생성
		m_Calendar 		= (Calendar)cal.clone() ;
		m_TodayCal		= getCalendar();
		layoutInitialization();

	}	

    @Override
	public void onCreate(Bundle savedInstanceState) {    	
        super.onCreate(savedInstanceState);
	}
    	
    /// 생성자
	public void layoutInitialization()
	{

		/// 표시할 각각의 레이어 생성
		m_lineLy 			= new LinearLayout[ 7 ] ;

		m_cellFrame 		= new FrameLayout[ 7 * 7 ] ;
		m_cellTopLy 		= new LinearLayout[ 7 * 7 ] ;
		m_cellView 			= new ImageView[ 7 * 7 ] ;
		
        m_cellLy 			= new LinearLayout[ 7 * 7 ] ;        
        m_cellDate 			= new TextView[ 7 * 7 ] ; 
        m_cellHoliday		= new TextView[ 7 * 7 ] ; 
//        m_cellSpecialday	= new ImageView[ 7 * 7 ] ;
        m_cellLunar			= new TextView[ 7 * 7 ] ;
        m_horizontalLine 	= new LinearLayout[ 6 ] ;
        m_verticalLine 		= new LinearLayout[ 6 * 7 ] ;    
        m_cellScheduleLin 	= new LinearLayout [ m_cellDate.length ] ; 
        
        /// 화면의 크기에 따른 보정값
        m_displayScale 	= m_context.getResources( ).getDisplayMetrics( ).density ;
        m_displayWidth 	= m_context.getResources( ).getDisplayMetrics( ).widthPixels;
        m_displayHeight = m_context.getResources( ).getDisplayMetrics( ).heightPixels;
        
        m_topTextSize 	= m_displayScale * 12.0f ;
        m_textSize 		= m_displayScale * 15.0f ;
        m_textSizeHoliday = m_displayScale * 6.0f ;
        m_colorParam 	= new gsCalendarColorParam( ) ;
	}	
	/// 달력을 생성한다.( 모든 옵션들[컬러값, 텍스트 크기 등]을 설정한 후에 마지막에 출력 할 때 호출)
	public void initCalendar(  )	
	{
		createViewItem( ) ;
        setLayoutParams( 0 ) ;
        setLineParam( ) ;
        if ( m_isFullCalendar ) {
        	setFullContentext( ) ;
        } else {
        	setContentext( ) ;
        }
        setOnEvent( ) ;
        printView( ) ;

	}	
	/// 달력2 ( popup)
	public void initCalendar2( )
	{
		createViewItem( ) ;
        setLayoutParams( 1 ) ;
        setLineParam( ) ;
        setContentext( ) ;
        setOnEvent( ) ;
        printView( ) ;

	}
	/// 달력2 ( week )
	public void initCalendar3( )
	{
		createViewItem( ) ;
        setLayoutParams( 2 ) ;
//        setLineParam( ) ;
        setContentext( ) ;
        setOnEventWeek( ) ;
        printView( ) ;

	}	
	/// 컬러값 파라메터 설정
	public void setColorParam( gsCalendarColorParam param )
	{
		m_colorParam = param ;
	}
	
	/// 배경으로 쓸 이미지를 설정
	public void setBackground( Drawable bg )
	{
		m_bgImgId = bg ;				
	}
	public void setCellBackground( Drawable bg )
	{
	    m_cellBgImgId = bg ;			
	}
	public void setTopCellBackground( Drawable bg )
	{
		m_topCellBgImgId = bg ;			
	}
	public void setScheduleCellBackground( Drawable bg )
	{
		m_scheduleCellBgImgId = bg ;			
	}
	/*
	 * 주간스케줄 등 미니달력에 사용 (기본)
	 */
	public void setCalendarSize( int width, int height  )
	{
		m_cWidth 	= ( width - ( m_lineSize * 6 ) ) / 7 ;
        m_cHeight 	= ( height - ( m_lineSize * 6 ) ) / 7 ;
        m_tcHeight 	= ( height - ( m_lineSize * 6 ) ) / 7 ;
	}	
	/*
	 * 화면 해상도에 따라 동적으로 사이즈 조정
	 */
	public void setCalendarSizeMain( int orientation,  int minusheight )
	{
		
/////////////////////////
//		ComUtil.showToast(m_context, "Scale :" + Float.toString(m_displayScale));
////		ComUtil.showToast(m_context, Float.toString(ViewUtil.getDensity(m_context)));
//		ComUtil.showToast(m_context, "Height :" + Float.toString(m_displayHeight));
//		ComUtil.showToast(m_context, "Width :" + Float.toString(m_displayWidth));		
//////////////////////
		
		
		//화면보정값추가
		int width = 0;
		int height = 0;
		if ( orientation == Configuration.ORIENTATION_LANDSCAPE ) {
			width = m_displayWidth + 5;
			height = (int) (m_displayHeight * m_displayScale);
		} else {
			width = m_displayWidth + 5;
			height = m_displayHeight - minusheight;
		}
		//주표기 부분은 3분의1로
        m_tcHeight = ( height / 7 ) / 4;
       //높이는 메뉴,날짜바,주 항목 뺀 뒤 보정값 추가후 처리
		m_cWidth = ( width - ( m_lineSize * 6 ) ) / 7 ;
        
        
        if ( m_displayScale == 2 ) {
        	m_cHeight = ( height - ( m_lineSize * 6 ) - m_tcHeight ) / 7;
        	
        } else if ( m_displayScale >= 3 ) {
        	
        	m_cHeight = ( height - ( m_lineSize * 6 ) - m_tcHeight - 14 ) / 7;
        	
        } else {
        	m_cHeight = ( height - ( m_lineSize * 6 ) - m_tcHeight - 14 ) / 7;
        }
        
        
	}
	
	/*
	 * 팝업달력 등에 사용 (해상도에 따라 resize)
	 */
	public void setCalendarSizeForPopup( int width, int height  )
	{
		
		width 		= (int) (width * m_displayScale);
		height 		= (int) (height * m_displayScale);
		
		m_cWidth 	= ( width - ( m_lineSize * 6 ) ) / 7 ;
        m_cHeight 	= ( height - ( m_lineSize * 6 ) ) / 7 ;
        m_tcHeight 	= ( height - ( m_lineSize * 6 ) ) / 7 ;
	}	

	
	public void setTextSize( float textSize, float topTextSize, float schTextSize,  float holTextSize, float  gangiTextSize)
	{
		m_topTextSize 		= m_displayScale * topTextSize ;
        m_textSize 			= m_displayScale * textSize ;
        m_textSizeHoliday 	= m_displayScale * holTextSize ;
        m_textSizeSchedule 	= m_displayScale * schTextSize ;
        m_textGangi 		= m_displayScale * gangiTextSize ;
        
	}
	/*
	 * 달력용 보정값
	 */	
	public void setTextSizePerScale( float textSize, float topTextSize, float schTextSize, float holTextSize )
	{
		//화면보정값추가
		//20141111  갤노트  dip = 4
/////////////////////////
//		int dip = m_context.getResources( ).getDisplayMetrics( ).densityDpi ;
//		ComUtil.showToast(m_context, Float.toString(dip));
//		ComUtil.showToast(m_context, Float.toString(m_displayScale));
//		ComUtil.showToast(m_context, Float.toString(m_displayHeight));
//		ComUtil.showToast(m_context, Float.toString(m_displayWidth));
/////////////////////////
		
		//갤탭만 별도처리
		if ( ( m_displayHeight > 800 || m_displayWidth > 800 ) && m_displayScale == 1 ) {
			
			m_topTextSize 		= ( topTextSize 	* 2)  + 5;
	        m_textSize 			= ( textSize 	 	* 2 ) + 5;
	        m_textSizeHoliday 	= ( holTextSize 	* 2 ) + 5;
	        m_textSizeSchedule 	= ( schTextSize 	* 2 ) + 5; 
	        
		} else if ( m_displayScale == 2 ) {
			
			m_topTextSize 		= m_displayScale * topTextSize 	- 1 ;
			m_textSize 			= m_displayScale * textSize  	- 1;
			m_textSizeHoliday 	= m_displayScale * holTextSize  - 1;
			m_textSizeSchedule 	= m_displayScale * schTextSize  - 1; 
			
		} else if ( m_displayScale >= 3 ) {

			m_topTextSize 		= ( 2 ) * topTextSize ;
	        m_textSize 			= ( 2 ) * textSize ;
	        m_textSizeHoliday 	= ( 2 ) * schTextSize ;
	        m_textSizeSchedule 	= ( 2 ) * schTextSize ;      
	        
		} else {
			m_topTextSize 		= m_displayScale * topTextSize ;
			m_textSize 			= m_displayScale * textSize ;
			m_textSizeHoliday 	= m_displayScale * holTextSize ;
			m_textSizeSchedule 	= m_displayScale * schTextSize ; 					
		}     
		
	}	
	
	/*
	 * 음력달력용 보정값
	 */
	public void setTextSizePerScale( float textSize, float topTextSize, float schTextSize, float  lunarTextSize, float  gangiTextSize)
	{
		
		//화면보정값추가
//		int dip = m_context.getResources( ).getDisplayMetrics( ).
		//갤탭만 별도처리  ->  못함
		if ( m_displayScale == 1 ) {
			
			m_topTextSize 		= topTextSize 	+ 0.5f;
	        m_textSize 			= textSize 		+ 0.5f;
	        m_textSizeHoliday 	= schTextSize 	+ 0.5f;
	        m_textSizeSchedule 	= schTextSize 	+ 0.5f;
	        m_textGangi 		= gangiTextSize + 0.5f; 	        
	        
		} else if ( m_displayScale == 2 ) {
			
			m_topTextSize 		= m_displayScale * topTextSize 	- 1 ;
			m_textSize 			= m_displayScale * textSize  	- 1;
			m_textSizeHoliday 	= m_displayScale * schTextSize  - 1;
			m_textSizeSchedule 	= m_displayScale * schTextSize  - 1; 
			m_textGangi 		= m_displayScale * gangiTextSize - 1;
			
		} else if ( m_displayScale >= 3 ) {

//			m_topTextSize 		= ( m_displayScale - 1 )  * topTextSize ;
//	        m_textSize 			= ( m_displayScale - 1 )  * textSize ;
//	        m_textSizeHoliday 	= ( m_displayScale - 1 )  * schTextSize ;
//	        m_textSizeSchedule 	= ( m_displayScale - 1 )  * schTextSize ;
//	        m_textGangi 		= ( m_displayScale - 1 )  * gangiTextSize ;
	        
	        m_topTextSize 		= ( 2 )  * topTextSize ;
	        m_textSize 			= ( 2 )   * textSize ;
	        m_textSizeHoliday 	= ( 2 )   * schTextSize ;
	        m_textSizeSchedule 	= ( 2 )   * schTextSize ;
	        m_textGangi 		= ( 2 )   * gangiTextSize ;

		} else {
			m_topTextSize 		= m_displayScale * topTextSize ;
	        m_textSize 			= m_displayScale * textSize ;
	        m_textSizeHoliday 	= m_displayScale * schTextSize ;
	        m_textSizeSchedule 	= m_displayScale * schTextSize ;
	        m_textGangi 		= m_displayScale * gangiTextSize ;					
		}	

	}	
	/*
	 * 스케줄 최대 건수 해상도에 따라 처리
	 */
	public int getScheduleMaxCnt( )
	{
//		//기본 3칸 
		int cnt = 3;
		
		return cnt;
        
	}	
	//////////////////// 선택한 날짜칸에 변화를 주는 함수 //////////////////////////
	/// 이녀석이 불러졌을때 상태는 날짜가 오늘로 선택되어있거나 뭔가 선택했을 것임
	/// 그럼으로 m_cellLy[ 날짜 + m_startPos ].setTextColor( ) ;
	/// m_startPos가 구해져 있으니 날짜를 더하면 해당 날짜칸을 마음대로 바꿀 수 있음 
	/// ////////////////////////////////////////////////////////////////////
	///오늘 하루전날짜까지의 스타일 일괄변경, 그리고 당일날 스타일 변경
	/// -> array 읽어서 처리하는 방법으로 변경
	public void setTodayStyle( int cellColor )
	{

		//오늘날짜이전까지 일괄처리(날짜칸만큼) : 주이름 보여주는 부분만 제외	
		int len = m_cellDate.length;
		String today 	= SmDateUtil.getDateFromCal(m_TodayCal);
		for ( int i = 7 ; i < len ; i++ ) {
			String celldate = m_calFullDate[ i ].toString();
			
			if ( celldate != null && celldate.length() == 8 ) {
				//오늘
				if ( today.equals( celldate ) ) {
					
					Drawable md = m_context.getResources().getDrawable(R.drawable.sm_today_cell);
					m_cellTopLy[ i ].setBackgroundDrawable(md);
					
				//지난달력
				} else if ( Double.parseDouble( today ) > Double.parseDouble( celldate )  ) {
					
					m_cellTopLy[ i ].setBackgroundColor( cellColor ) ;
					
				//다음달력
				} else {
					
					m_cellTopLy[ i ].setBackgroundColor( m_colorParam.m_cellColor ) ;	
					
				}	
			} else {
				if ( i < 14 && 
						( m_TodayCal.get( Calendar.YEAR ) > m_Calendar.get( Calendar.YEAR ) ||
						(m_TodayCal.get( Calendar.YEAR ) == m_Calendar.get( Calendar.YEAR ) &&
								 m_TodayCal.get( Calendar.MONTH ) >= m_Calendar.get( Calendar.MONTH ))) ) {
					m_cellTopLy[ i ].setBackgroundColor( cellColor ) ;
				}
			}
		}
	}	
	/*
	 * 선택된 주 배경색 변경
	 */
	public void setWeekStyle( int cellColor , ArrayList<String> dateList )
	{
		String startday = "";
		
		//format : MM( 0 삭제)
		String calmonth  	=  (m_Calendar.get( Calendar.MONTH ) + 1 ) + "";
		
		//1.달력과 동일한 달의 첫번째 일자 가져오기
		int len = dateList.size();
		
		for ( int i = 0 ; i < len ; i++ ) {
			String date = dateList.get(i).toString();
			if ( date != null && date.trim().length() == 8 ) {
				String month = ComUtil.intToString(ComUtil.stringToInt(date.substring(4,6)));
				
				if ( calmonth != null &&  month != null && calmonth.equals(month)) {
					
					startday = ComUtil.intToString(ComUtil.stringToInt(date.substring(6)));
					break;					
				}
			}			
		}
		
		
		int week = 0;		
		//1.주수 가져오기
		int totpos = m_startPos + m_lastDay ;
		for ( int i = 0 ; i < totpos ; i++ ) {
			//시작순번 set
			if ( m_cellDate[ i ] != null && m_cellDate[ i ].getText().toString().equals(startday) ) {				 
				week = ( i / 7 );	
				break;
			}
		}
		
		//2.주수에 해당하는 칸 색처리(첫번째 일자와 일치하는 주수에 해당되는 날짜 색변경)
		int cellCnt = m_cellTopLy.length;
		for ( int i = 7 ; i < cellCnt ; i++ ) {
			
			//해당순번에 해당하는 일자 모두 color change
			if ( week != 0 ) {
				if (( i / 7)  ==  week ) {
					m_cellTopLy[ i ].setBackgroundColor( cellColor ) ;
				} else {
					m_cellTopLy[ i ].setBackgroundColor( m_colorParam.m_cellColor ) ;
				}
			}
		}		
	}

	/// 선택된 날짜칸에 변화를 주기위한 함수 2호
	public void setSelectedDayTextColor( int textColor )
	{
		m_colorParam.m_todayTextColor = textColor ;
		m_cellDate[ m_Calendar.get( Calendar.DAY_OF_MONTH ) + m_startPos - 1 ].setTextColor( textColor ) ;
	}
	 
	/// 선택된 날짜칸에 변화를 주기위한 함수 3호
	public void setSelectedDay( Drawable bgimg, int year, int month, int day , int pos )
	{
		int len = m_calFullDate.length;
		
		if ( pos < 7 ) return ;
		
		for ( int i = 7 ; i < len ; i++ ) {
			String cellDate = m_calFullDate [ i ].toString();
			if ( cellDate != null && cellDate.length() == 8 ) {
				if ( SmDateUtil.getDateFormat(year, month, day).equals( cellDate )) {
					m_selectCellBgImgId = bgimg ;
					m_cellTopLy[ i ].setBackgroundDrawable( bgimg ) ;						
				}
			}
			
		}

	}

	/// 선택된날짜에 스케줄이나 휴일정보가 있는지 여부 return
	public boolean isSelectedDayExistSchedule(  )
	{
		boolean ret = false;
		int linCnt = m_cellScheduleLin[ m_Calendar.get( Calendar.DAY_OF_MONTH ) + m_startPos - 1 ].getChildCount();
		if ( linCnt > 0 )  ret = true;
		
		TextView tv = m_cellHoliday[ m_Calendar.get( Calendar.DAY_OF_MONTH ) + m_startPos - 1 ];
		if ( tv != null )  ret = true;	
		
//		if ( m_cellSpecialday[ m_Calendar.get( Calendar.DAY_OF_MONTH ) + m_startPos - 1 ] != null ) {
//			Drawable dw = m_cellSpecialday[ m_Calendar.get( Calendar.DAY_OF_MONTH ) + m_startPos - 1 ].getBackground();
//			if ( dw != null )  ret = true;			
//		}
	
		
		return ret ;
	}	

	
	/// 레이아웃과 버튼 그리고 경계션으로 쓸 라인용 레이아웃들을 생성한다.
	// 2011.05.25 image button 추가 
	public void createViewItem( )
	{
		//가로세로 줄 
        for( int i = 0 ; i < 13 ; i++ )
        {
        	//세로줄
        	if( i % 2 == 0 )
        	{
	        	m_lineLy[i/2] = new LinearLayout( m_context ) ;
	        	m_targetLayout.addView( m_lineLy[i/2] ) ;
        	
	        	for( int j = 0 ; j < 13 ; j++ )
	        	{
	        		
	        		if( j % 2 == 0 )
		        	{
	        			int pos = ( ( i / 2 ) * 7 ) + ( j / 2 ) ;
	        			//달력 한칸에 들어갈 공통객체선언
	        			//단,기념일, 스케줄 항목의 경우 아래에 동적으로 처리	   
	        			m_cellFrame[ pos ] 	= new FrameLayout( m_context ) ;
	        			m_cellView[ pos ] 	= new ImageView( m_context ) ;
	        			
	        			m_cellTopLy[ pos ] 	= new LinearLayout( m_context ) ;
		        		m_cellLy[ pos ] 	= new LinearLayout( m_context ) ;
		        		m_cellDate[ pos ]   = new TextView( m_context ) ;
		        		
		        		m_lineLy[ i / 2 ].addView( m_cellFrame[ pos ] ) ;
		        		m_cellFrame[ pos ].addView( m_cellTopLy[ pos ] ) ;
		        		m_cellFrame[ pos ].addView( m_cellView[ pos ] ) ;
		        		m_cellTopLy[ pos ].addView( m_cellLy[ pos ] ) ;
		        		m_cellLy[ pos ].addView( m_cellDate[ pos ] ) ;
		        		
		        	}
	        		else
	        		{
	        			int pos = ( ( i / 2 ) * 6 ) + ( j - 1 ) / 2 ;
	        			
	        			//Log.d( "pos2", "" +  pos ) ;
	        			m_verticalLine[ pos ] = new LinearLayout( m_context ) ;
		        		m_lineLy[ i / 2 ].addView( m_verticalLine[ pos ] ) ;
	        		}
	        	}
        	}
        	//가로줄
        	else
        	{
        		m_horizontalLine[ ( i - 1 ) / 2 ] = new LinearLayout( m_context ) ;
	        	m_targetLayout.addView( m_horizontalLine[ ( i - 1 ) / 2 ] ) ;
	        	
	        	
        	}
        }
	}
	
	/// 레이아웃과 버튼의 배경색, 글씨색 등 ViewParams를 셋팅
	//201100601 : 날짜를 표기하는 글짜색깔의 경우 setcontext 단계로 이동(가변적인 항목으로 초기화필요)
	public void setLayoutParams( int rate )
	{
		
		//해상도별로 처리할 때 사용될 값
		int schMaxCnt = getScheduleMaxCnt();
		
		/// 메인 레이아웃은 세로로 나열
		m_targetLayout.setOrientation( LinearLayout.VERTICAL ) ;
		/// 만약 전체 배경이 있으면 넣어줌
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT ) ;
		FrameLayout.LayoutParams paramFr = new FrameLayout.LayoutParams( FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT ) ;

//		FrameLayout.LayoutParams paramFrFill = new FrameLayout.LayoutParams( FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT ) ;
		
		if( m_bgImgId != null )
		{
			m_targetLayout.setBackgroundDrawable( m_bgImgId ) ;
		}
		
		for( int i = 0 ; i < 13 ; i++ )
		{
			if( i % 2 == 0 )
        	{
				/// 각 라인을 구성하는 레이아웃들은 가로로 나열~
				m_lineLy[i/2].setOrientation( LinearLayout.HORIZONTAL ) ;
				m_lineLy[i/2].setLayoutParams(	/// 레이아웃 사이즈는 warp_content로 설정 
						new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT ) ) ;
	        	
				/// 한칸한칸 옵션을 설정
	        	for( int j = 0 ; j < 7 ; j++ )
	        	{
	        		int cellnum = ( ( i / 2 ) * 7 ) + j ;
	        		
	        		//param.setMargins( 1, 1, 1, 1 ) ;	/// 마진을 1씩 줘서 라인을 그린다.
	        		
	        		/// 한칸한칸 들어가는 버튼
	        		m_cellDate[ cellnum ].setGravity( Gravity.CENTER ) ;
	        		
	        		/// 이하는 배경색 글씨색 글씨 크기 설정하는 부분
	        		/// 첫라인은 월화수목금토일 표시하는 부분
	        		if( i == 0 )	
	        		{
		        		//param.setMargins( 1, 1, 1, 1 ) ;	/// 마진을 1씩 줘서 라인을 그린다.
//	        			m_cellTopLy[ cellnum ].setLayoutParams( param ) ;
	        			m_cellTopLy[ cellnum ].setLayoutParams( paramFr ) ;
	        			
		        		m_cellLy[ cellnum ].setLayoutParams( param ) ;
		        		
	        			/// 요일 표시하는 부분의 넓이 높이 /정렬방식		        		
	        			m_cellDate[ cellnum ].setLayoutParams( new LinearLayout.LayoutParams( m_cWidth, m_tcHeight ) ) ;
	        			m_cellDate[ cellnum ].setGravity( Gravity.CENTER ) ;
	        			/// 배경과 글씨색
	        			if( m_topCellBgImgId != null )
	        			{
	        				m_cellTopLy[ cellnum ].setBackgroundDrawable( m_topCellBgImgId ) ;
	        			}
	        			else
	        			{
	        				m_cellTopLy[ cellnum ].setBackgroundColor( m_colorParam.m_topCellColor ) ;
	        			}
	        			
	        			/// 토요일과 일요일은 다른 컬러로 표시한다.
	            		switch( j )
	    	    		{
	    	    		case 0:
	    	    			m_cellDate[ cellnum ].setTextColor( m_colorParam.m_topSundayTextColor ) ;
	    	    			break ;
	    	    		case 6:
	    	    			m_cellDate[ cellnum ].setTextColor( m_colorParam.m_topSaturdatTextColor ) ;
	    	    			break ;
	    	    		default:
	    	    			m_cellDate[ cellnum ].setTextColor( m_colorParam.m_topTextColor ) ;
	    	    			break ;
	    	    		}
	            		
	            		/// 글씨 크기	            		
	            		m_cellDate[ cellnum ].setTextSize( m_topTextSize ) ;
	        		}
	        		else			/// 이하는 날짜 표시하는 부분
	        		{
	        		
	        			//볼드체로 변경
	        			m_cellDate[ cellnum ].setTypeface(Typeface.DEFAULT, Typeface.BOLD);
	        			/// 숫자 표시되는 부분의 넓이와 높이 
	        			m_cellFrame[ cellnum ].setLayoutParams( param ) ;
	        			m_cellView[ cellnum ].setLayoutParams( new FrameLayout.LayoutParams( m_cWidth, m_cHeight ) ) ;
	        			m_cellTopLy[ cellnum ].setLayoutParams( new FrameLayout.LayoutParams( m_cWidth, m_cHeight ) ) ;	        			
	        			
	        			//0:메인 달력 1:popup 2:미니(주간스케줄)
	        			if ( rate  == 0 ) {
	        				m_cellLy[ cellnum ].setLayoutParams( new LinearLayout.LayoutParams( m_cWidth  , m_cHeight / (schMaxCnt + 1 ) ) ) ;
	        				m_cellDate[ cellnum ].setLayoutParams( new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT  , LinearLayout.LayoutParams.FILL_PARENT  )  ) ;
	        				m_cellDate[ cellnum ].setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER);
	        				m_cellDate[ cellnum ].setIncludeFontPadding(false);
	        			} else if ( rate  == 1 ) {
	        				m_cellDate[ cellnum ].setGravity( Gravity.CENTER ) ;
	        				m_cellLy[ cellnum ].setLayoutParams( param ) ;
	        				m_cellDate[ cellnum ].setLayoutParams( new LinearLayout.LayoutParams( m_cWidth * rate , m_cHeight * rate  )  ) ;
		      			} else if ( rate  == 2 ) {
		      				m_cellDate[ cellnum ].setGravity( Gravity.CENTER ) ;
	        				m_cellLy[ cellnum ].setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT  , LinearLayout.LayoutParams.FILL_PARENT  )  ) ;
	        				m_cellDate[ cellnum ].setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT  , LinearLayout.LayoutParams.FILL_PARENT  )  ) ;
		      			}
	        			
	        			/// bg와 글씨색  -> setcontext로 이동 
	        			
	        			if( m_cellBgImgId != null )
	        			{
	        				m_cellTopLy[ cellnum ].setBackgroundDrawable( m_cellBgImgId ) ;
	        			}
	        			else
	        			{
	        				m_cellTopLy[ cellnum ].setBackgroundColor( m_colorParam.m_cellColor ) ;
	        			}
	        			
	            		/// 글씨 크기
	            		m_cellDate[ cellnum ].setTextSize( m_textSize ) ;
	            		//정렬방향 ( 숫자가 보이는 부분만 처리)
	            		m_cellTopLy[cellnum].setOrientation( LinearLayout.VERTICAL ) ;
	            		m_cellLy[cellnum].setOrientation( LinearLayout.HORIZONTAL ) ;
	            		
	        		}
	        		
	        		
	        	}
        	}
		}
	}
	
	public void setLineParam( )
	{
		for( int i = 0 ; i < 6 ; i ++ )
		{
			m_horizontalLine[ i ].setBackgroundColor( m_colorParam.m_lineColor ) ;	/// 라인색
			m_horizontalLine[ i ].setLayoutParams(	/// 가로 라인이니까 가로는 꽉 세로는 두께만큼 
						new LinearLayout.LayoutParams( LinearLayout.LayoutParams.FILL_PARENT, m_lineSize ) ) ;
		}
		for( int i = 0 ; i < 7 ; i ++ )
		{
			for( int j = 0 ; j < 6 ; j++ )
	    	{
	    		int pos = ( i * 6 ) + j ;
	    		m_verticalLine[ pos ].setBackgroundColor( m_colorParam.m_lineColor ) ; /// 라인색
	    		m_verticalLine[ pos ].setLayoutParams(	/// 세로 라인이니까 세로는 쭉~ 가로는 두께만큼 
						new LinearLayout.LayoutParams( m_lineSize, LinearLayout.LayoutParams.FILL_PARENT ) ) ;
	    	}
		}
	}
	
	/// 달력을 구성하는 년 월 일을 셋팅하기
	// -. 날짜 색깔도 여기서 셋팅(기념일이나 여러요인으로 날짜색깔이 중간에 변경되는 경우 초기화가 필요)
	public void setContentext( )
	{
		//해당칸에 대한 일자정보 보관	***중요
		m_calFullDate = new String [ m_cellDate.length ];
		
		//요일값 setting
		m_dayText = new String []{ ComUtil.getStrResource(m_context, R.string.sunday), ComUtil.getStrResource(m_context, R.string.monday), ComUtil.getStrResource(m_context, R.string.tuesday)
		     , ComUtil.getStrResource(m_context, R.string.wednesday), ComUtil.getStrResource(m_context, R.string.thursday), ComUtil.getStrResource(m_context, R.string.friday)
		     , ComUtil.getStrResource(m_context, R.string.saturday) };
		

		//화면초기화 ( text , background 일괄 clear )
		int len = m_cellDate.length;
		for( int i = 7 ; i < len ; i++ ) {
			
			int celPosition = i % 7 ;
			
			/// background
			if( m_cellBgImgId != null )
			{
				m_cellTopLy[ i ].setBackgroundDrawable( m_cellBgImgId ) ;
				
			}
			else
			{
				m_cellTopLy[ i ].setBackgroundDrawable(null);
				m_cellTopLy[ i ].setBackgroundColor( m_colorParam.m_cellColor ) ;
			}
			
//			if( m_specialdayCellBgImgId != null )
//			{
//				m_cellDate[ i ].setBackgroundDrawable(null);
//				
//			}		
			
			//text color
    		switch( celPosition )
    		{
    		case 0:
    			m_cellDate[  i  ].setTextColor( m_colorParam.m_sundayTextColor ) ;
    			break ;
    		case 6:
    			m_cellDate[  i  ].setTextColor( m_colorParam.m_saturdayTextColor ) ;
    			break ;
    		default:
    			m_cellDate[  i  ].setTextColor( m_colorParam.m_textColor ) ;
    			break ;
    		}				
		}
		/// 달력을 하나 복사해서 작업한다.
		Calendar iCal 		= (Calendar) m_Calendar.clone( ) ;
		
		/// 날짜를 겟~
		m_selDay = iCal.get( Calendar.DATE ) ;
		
		/// 날짜를 1로 셋팅하여 달의 1일이 무슨 요일인지 구함
		iCal.set( Calendar.DATE, 1 ) ;
		m_startPos = 7 + iCal.get( Calendar.DAY_OF_WEEK ) - Calendar.SUNDAY ;
		
		/// 1달 더해서 다음달 1일로 만들었다가 1일을 빼면 달의 마지막날이 구해짐
		iCal.add( Calendar.MONTH, 1 ) ;
		iCal.add( Calendar.DATE, -1 ) ;
		
        m_lastDay = iCal.get( Calendar.DAY_OF_MONTH ) ;         /// 해달 달의 마지막날 겟~          

        /// 0부터 6번칸까지는 월화수목금토일~ 로 채워넣음        
		for( int k = 0 ; k < 7 ; k++ )
    	{
			m_cellDate[ k ].setText(  m_dayText[k] ) ;
			m_calFullDate [ k ] = "";
    	}
		
		/// 7번부터 처음 시작위치 전까지는 공백으로 채움
		//--> 이전달정보로 변경(to-do)
		for( int i = 7 ; i < m_startPos ; i++ )
		{
			m_cellDate[ i ].setText( "" ) ;
			m_calFullDate [ i ] = "";
		}

		/// 시작위치부터는 1부터 해서 달의 마지막날까지 숫자로 채움
		String thisyear 	= ComUtil.fillSpaceToZero(iCal.get( Calendar.YEAR ), 4)  ; 
		String thismonth 	= ComUtil.fillSpaceToZero(iCal.get( Calendar.MONTH ) + 1, 2)  ; 
		for( int i = 0 ; i < m_lastDay ; i++ )
		{
			String day = ( i + 1 ) + "";
			m_cellDate[ i + m_startPos ].setText( day ) ;
			m_calFullDate [ i + m_startPos ] = thisyear + thismonth + ComUtil.fillSpaceToZero( day, 2 );
		
		} 	
		
		/// 마지막날부터 끝까지는 공백으로 채움
		for( int i = m_startPos + m_lastDay ; i < 49 ; i++ )
		{        	
			m_cellDate[ i ].setText( "" ) ;
			m_calFullDate[ i ] = "";
		}
	}	
	/// 달력을 구성하는 년 월 일을 셋팅하기 ( TO-DO  : 이전,다음월 정보도 포함한 형태로 출력)
	// -. 날짜 색깔도 여기서 셋팅(기념일이나 여러요인으로 날짜색깔이 중간에 변경되는 경우 초기화가 필요)
	public void setFullContentext( )
	{
		Drawable pastDw = m_context.getResources().getDrawable(R.drawable.sm_premonth_back);
		//해당칸에 대한 일자정보 보관	***중요
		m_calFullDate = new String [ m_cellDate.length ];
		
		//요일값 setting
		m_dayText = new String []{ ComUtil.getStrResource(m_context, R.string.sunday), ComUtil.getStrResource(m_context, R.string.monday), ComUtil.getStrResource(m_context, R.string.tuesday)
		     , ComUtil.getStrResource(m_context, R.string.wednesday), ComUtil.getStrResource(m_context, R.string.thursday), ComUtil.getStrResource(m_context, R.string.friday)
		     , ComUtil.getStrResource(m_context, R.string.saturday) };
		
		//화면초기화 ( text , background 일괄 clear )
		int len = m_cellDate.length;
		for( int i = 7 ; i < len ; i++ ) {
			
			int celPosition = i % 7 ;
			
			/// background
			if( m_cellBgImgId != null )
			{
				m_cellTopLy[ i ].setBackgroundDrawable( m_cellBgImgId ) ;
				
			}
			else
			{
				m_cellTopLy[ i ].setBackgroundDrawable(null);
				m_cellTopLy[ i ].setBackgroundColor( m_colorParam.m_cellColor ) ;
			}
			
			//text color
    		switch( celPosition )
    		{
    		case 0:
    			m_cellDate[  i  ].setTextColor( m_colorParam.m_sundayTextColor ) ;
    			break ;
    		case 6:
    			m_cellDate[  i  ].setTextColor( m_colorParam.m_saturdayTextColor ) ;
    			break ;
    		default:
    			m_cellDate[  i  ].setTextColor( m_colorParam.m_textColor ) ;
    			break ;
    		}				
		}
		/// 달력을 하나 복사해서 작업한다.
		Calendar iCal 		= (Calendar) m_Calendar.clone( ) ;
		Calendar iPreCal 	= (Calendar) m_Calendar.clone( ) ;
		Calendar iNextCal 	= (Calendar) m_Calendar.clone( ) ;
		
		/// 날짜를 겟~
		m_selDay = iCal.get( Calendar.DATE ) ;
		
		/// 날짜를 1로 셋팅하여 달의 1일이 무슨 요일인지 구함
		iCal.set( Calendar.DATE, 1 ) ;
		m_startPos = 7 + iCal.get( Calendar.DAY_OF_WEEK ) - Calendar.SUNDAY ;
		
		/// 1달 더해서 다음달 1일로 만들었다가 1일을 빼면 달의 마지막날이 구해짐
		iCal.add( Calendar.MONTH, 1 ) ;
		iCal.add( Calendar.DATE, -1 ) ;
		
        m_lastDay = iCal.get( Calendar.DAY_OF_MONTH ) ;         /// 해달 달의 마지막날 겟~          

        /// 0부터 6번칸까지는 월화수목금토일~ 로 채워넣음        
		for( int k = 0 ; k < 7 ; k++ )
    	{
			m_cellDate[ k ].setText(  m_dayText[k] ) ;
			m_calFullDate [ k ] = "";
    	}
		
		/// 7번부터 처음 시작위치 전까지는 공백으로 채움
		//--> 이전달정보로 변경
		int pre_startday = 0;

        if (  m_startPos > 7 ) {
        	//이전달 마지막일(1일로 set -> 1일 빼기)
        	iPreCal.set( Calendar.DATE, 1 ) ;
        	iPreCal.add( Calendar.DATE, -1 ) ;
        	int pre_lastday = iPreCal.get( Calendar.DAY_OF_MONTH ) ;
        	pre_startday 	= pre_lastday - iPreCal.get( Calendar.DAY_OF_WEEK )  + Calendar.SUNDAY;
        } 

		for( int i = 7 ; i < m_startPos ; i++ )
		{

			if ( pre_startday == 0 ) {
				m_cellDate[ i ].setText( "" ) ;
				m_calFullDate [ i ] = "";	
				m_cellView[ i ].setBackgroundDrawable(null);
				
			} else {
				String year  = ComUtil.fillSpaceToZero ( iPreCal.get( Calendar.YEAR ), 4 );
				String month = ComUtil.fillSpaceToZero ( iPreCal.get( Calendar.MONTH ) + 1 , 2);
				String predate = pre_startday + i - 7 + "" ;
				m_cellDate[ i ].setText( predate ) ;
				m_calFullDate [ i ] = year + month + ComUtil.fillSpaceToZero( predate, 2 );	
				m_cellView[ i ].setBackgroundDrawable( pastDw );	    		
			}
			
		}

		/// 시작위치부터는 1부터 해서 달의 마지막날까지 숫자로 채움
		String thisyear  = ComUtil.fillSpaceToZero(iCal.get( Calendar.YEAR ), 4)  ; 
		String thismonth = ComUtil.fillSpaceToZero(iCal.get( Calendar.MONTH ) + 1, 2)  ; 
		for( int i = 0 ; i < m_lastDay ; i++ )
		{
			int pos = i + m_startPos;
			String day = ( i + 1 ) + "";
			m_cellDate[ pos ].setText( day ) ;
			m_calFullDate [ pos ] = thisyear + thismonth + ComUtil.fillSpaceToZero( day, 2 );
			m_cellView[ pos ].setBackgroundDrawable(null);
		
		} 	
		
		/// 마지막날부터 끝까지는 공백으로 채움
		//-> 다음달 정보로 변경
		int next_startday = 0;

        if (  m_startPos + m_lastDay < 49 ) {
        	//이전달 마지막일(1일로 set -> 1일 빼기)
        	iNextCal.set( Calendar.DATE, 1 ) ;
        	iNextCal.add( Calendar.MONTH, 1 ) ;
        	next_startday 	= iNextCal.get( Calendar.DAY_OF_MONTH ) ;
        } 	

		for( int i = m_startPos + m_lastDay ; i < 49 ; i++ )
		{       
			if ( next_startday == 0 ) {
				m_cellDate[ i ].setText( "" ) ;
				m_calFullDate[ i ] = "";	
				m_cellView[ i ].setBackgroundDrawable(null);
			} else {
				String year  = ComUtil.fillSpaceToZero ( iNextCal.get( Calendar.YEAR ), 4 );
				String month = ComUtil.fillSpaceToZero ( iNextCal.get( Calendar.MONTH ) + 1 , 2);
				String nextdate = next_startday + i -  ( m_startPos + m_lastDay ) + "";
				m_cellDate[ i ].setText( nextdate ) ;
				m_calFullDate[ i ] = year + month + ComUtil.fillSpaceToZero( nextdate, 2 );	
				m_cellView[ i ].setBackgroundResource(R.drawable.sm_premonth_back);
			}			
		}
	}
	
	//날짜배열에서 날짜정보가져오기
	public String getCellDateText(  int calLen  ) {
		
		String str  = "";
		str = m_cellDate[ calLen ].getText().toString();
		return str;		
		
	}
	//날짜객체에서 날짜정보가져오기(월일)
	public String getCellDateTextFromArray(  int calLen  ) {
		
		String str  = "";
		
		if ( m_calFullDate[ calLen ] != null && m_calFullDate[ calLen ].length() == 8 )
			str = m_calFullDate[ calLen ].substring(4).toString();
		else 
			str = "";
		
		return str;		
	}
	//날짜객체에서 날짜정보가져오기(년월일)
	public String getCellDateTextFromFullArray(  int calLen  ) {
		
		String str  = "";		
		str = m_calFullDate[ calLen ].toString();		
		if ( str == null ) str = "";		
		return str;		
	}	
	//날짜객체에서 글짜색깔가져오기
	public int getCellDateTextColor(  int calLen  ) {
		int txtcolor  ;
		txtcolor = m_cellDate[ calLen ].getTextColors().getDefaultColor();
		return txtcolor;		
	}	//날짜객체에서 사이즈 가져오기
	public int getCellDateSize( ) {	
		int len = m_cellDate.length	;
		return len;		
	}	
	
	public Calendar getCalendar( ) {	
		Calendar iCal = (Calendar) m_Calendar.clone( ) ;
		return iCal;
	}
	/*
	 * 보여지는 달력의 실제 시작일, 종료일
	 */
	public String getStartDateInCalendar( ) {	
		int len = m_calFullDate.length;
		for ( int i = 0 ; i < len ; i ++ ) {
			String sdate = m_calFullDate[ i ].toString();
			if ( sdate != null && sdate.length() == 8) {
				return sdate;
			}
		}
		return "";
	}
	public String getEndDateInCalendar( ) {	
		
		int len = m_calFullDate.length;
		for ( int i = len - 1 ; i >= 0 ; i-- ) {
			String edate = m_calFullDate[ i ].toString();
			if ( edate != null && edate.length() == 8) {
				return edate;
			}
		}
		return "";
	}	
	//마지막 날짜 가져오기
	public int getLastDate( ) {	
		int day = m_lastDay;
	
		return day;
	}	
	/*
	 * 간지달력용
	 */
	//하단 음력일자,60갑자정보 layout 생성 및 초기화
	public void setScheduleLinearLayoutFL(  int calLen  ) {
		//하단 음력 정보가 담긴 linaearlayout 삭제
		for( int i = 0 ; i < calLen ; i++ ) {
			m_cellTopLy[ i ].removeView(m_cellScheduleLin[ i ]);
		}
		
		m_cellSchedule 	= new TextView [ calLen ][ 2 ] ; 
	}

	
	/*
	 * 스케줄 및 휴일정보용
	 */
	public void setCellScheduleBackGround( int calLen, int totalSch, Drawable bg  ) {
		m_cellSchedule[ calLen ][ totalSch ].setBackgroundDrawable(bg);		
	}
	public void setCellScheduleBackGroundForSpecial( int calLen, int totalSch) {
//		Drawable md = m_context.getResources( ).getDrawable( R.drawable.sm_cal_specialday_cell );		
//		m_cellSchedule[ calLen ][ totalSch ].setBackgroundDrawable(md);			
		m_cellSchedule[ calLen ][ totalSch ].setTextColor(m_context.getResources().getColor(R.color.caldaytext));
//		m_cellSchedule[ calLen ][ totalSch ].setTypeface(Typeface.DEFAULT, Typeface.BOLD);
	}

	public void setCellScheduleText( int calLen, int totalSch, String str ) {	

		if ( str != null && !str.trim().equals("")) {
			m_cellSchedule[ calLen ][ totalSch ].setEllipsize(TruncateAt.END);
		}
		m_cellSchedule[ calLen ][ totalSch ].setText(str);
		
	}
	public String getCellScheduleText( int calLen, int totalSch ) {	

		if ( m_cellSchedule[ calLen ][ totalSch ] != null ) {
			return m_cellSchedule[ calLen ][ totalSch ].getText().toString();
		} else {
			return "";
		}
		
	}	
	public void setCellHolidayText( int calLen, String str ) {

		//임시조치
		if ( str != null && str.trim().equals("")) str = "  ";
		
		m_cellHoliday[ calLen ].setText(str);	
	}	
	
	public void setCellHolidayTextColor( int calLen , String holidayYn) {
		
		if ( holidayYn != null && holidayYn.equals("Y")){
			m_cellHoliday[ calLen ].setTextColor(m_colorParam.m_sundayTextColor);
			m_cellDate[ calLen ].setTextColor(m_colorParam.m_sundayTextColor);	
		} else {
			m_cellHoliday[ calLen ].setTextColor(m_colorParam.m_textColor);
		}		
		
	}	
	public void setCellLunarBackground( int calLen , Drawable bg) {
		
		if ( bg != null ){
			m_cellLunar[ calLen ].setBackgroundDrawable(bg);
		} else {
			m_cellLunar[ calLen ].setBackgroundDrawable(null);
		}		
		
	}	
	/*
	 * 글자로 표기될 경우 디자인 다시
	 */
	public void setCellLunarText (int calLen , String str ) {
		
		if ( str != null && !str.trim().equals("")){
			m_cellLunar[ calLen ].setText(str);
		} else {
			m_cellLunar[ calLen ].setText("");
		}	
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT  );
		params.leftMargin = 3;
		m_cellLunar[ calLen ].setLayoutParams (params);
		m_cellLunar[ calLen ].setGravity(Gravity.CENTER);
		m_cellLunar[ calLen ].setTextSize(m_textSizeSchedule);
		m_cellLunar[ calLen ].setSingleLine(true);
		m_cellLunar[ calLen ].setTextColor(m_context.getResources().getColor(R.color.listtext));
		
		
	}				
	//스케줄 layout 생성
	public void setScheduleLinearLayout(  int calLen , int totalSch ) {
		
		//스케줄 정보가 담긴 linaearlayout 삭제
		for( int i = 0 ; i < calLen ; i++ ) {
			m_cellTopLy[ i ].removeView(m_cellScheduleLin[ i ]);
		}
		
		m_cellSchedule 	= new TextView [ calLen ][ totalSch ] ; 
		//스케줄 정보  linaearlayout 생성
		for( int i = 7 ; i < calLen ; i++ ) {
			m_cellScheduleLin[ i ] = new LinearLayout( m_context ) ;
			m_cellTopLy[ i ].addView( m_cellScheduleLin[ i ] ) ;
			m_cellScheduleLin[ i ].setGravity(Gravity.BOTTOM);
			LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT );
			parms.weight = 1;
			m_cellScheduleLin[ i ].setLayoutParams (parms);			
			m_cellScheduleLin[ i ].setOrientation(LinearLayout.VERTICAL);
		}
	}
	//스케줄 정보 건건이 생성
	public void setScheduleTextLayout(  int calPositon , int schPosition, int schMaxCnt ) {
	 
		//최대 스케줄 건수를 값으로 받아서 + 1
		//스케줄 정보  list TextView 생성	
		LinearLayout.LayoutParams schParm = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.FILL_PARENT, ( m_cHeight / (schMaxCnt + 1)));
		
		m_cellSchedule[ calPositon ][ schPosition ] = new TextView( m_context ) ;
		m_cellScheduleLin[ calPositon ].addView( m_cellSchedule[ calPositon ][ schPosition ] ) ;
		m_cellSchedule[ calPositon ][ schPosition ].setGravity( Gravity.TOP |Gravity.LEFT) ;
		m_cellSchedule[ calPositon ][ schPosition ].setMaxLines(1);
		m_cellSchedule[ calPositon ][ schPosition ].setSingleLine(true);
		m_cellSchedule[ calPositon ][ schPosition ].setTextSize(m_textSizeSchedule);
		m_cellSchedule[ calPositon ][ schPosition ].setTextColor(Color.WHITE);
		m_cellSchedule[ calPositon ][ schPosition ].setMaxHeight( m_cHeight / 4 );
		m_cellSchedule[ calPositon ][ schPosition ].setLayoutParams (schParm);	
		
		if ( m_scheduleCellBgImgId != null )
			m_cellSchedule[ calPositon ][ schPosition ].setBackgroundDrawable(m_scheduleCellBgImgId);

		
	}
	//스케줄 정보 건건이 생성(음력달력용)
	public void setScheduleTextLayoutForLunar(  int calPositon , int schPosition, int schMaxCnt ) {
	 
		//최대 스케줄 건수를 값으로 받아서 + 1
		//스케줄 정보  list TextView 생성	
		LinearLayout.LayoutParams schParm = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.FILL_PARENT, ( m_cHeight / (schMaxCnt + 1)));
		
		m_cellSchedule[ calPositon ][ schPosition ] = new TextView( m_context ) ;
		m_cellScheduleLin[ calPositon ].addView( m_cellSchedule[ calPositon ][ schPosition ] ) ;
		m_cellSchedule[ calPositon ][ schPosition ].setGravity( Gravity.TOP |Gravity.RIGHT) ;
		m_cellSchedule[ calPositon ][ schPosition ].setMaxLines(1);
		m_cellSchedule[ calPositon ][ schPosition ].setSingleLine(true);
		m_cellSchedule[ calPositon ][ schPosition ].setTextSize(m_textGangi);
		m_cellSchedule[ calPositon ][ schPosition ].setTextColor(m_context.getResources().getColor(R.color.listtext));
		m_cellSchedule[ calPositon ][ schPosition ].setMaxHeight( m_cHeight / 4 );
		m_cellSchedule[ calPositon ][ schPosition ].setLayoutParams (schParm);	
		
		if ( m_scheduleCellBgImgId != null )
			m_cellSchedule[ calPositon ][ schPosition ].setBackgroundDrawable(m_scheduleCellBgImgId);

		
	}	
	/*
	 * 공휴일 layout 생성
	 * -. 기념일의 경우 동적으로 생성되는 정보이기 때문에 객체를 삭제한후 재생성해야함
	 */
	public void reCreateHolidayText ( int calLen ) {
		
		for( int i = 0 ; i < calLen ; i++ ) {
			m_cellLy[ i ].removeView(m_cellHoliday[i]);
			m_cellHoliday[i] = null;
			
			setHolidayTextView( i );
		}
	}
	public void setHolidayTextView(  int calPositon  ) {
		 
		//기념일 textview 생성 (기 존재시 skip)
		if ( m_cellHoliday[ calPositon ] != null ) {
			//////////////////////

		} else {
			LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT );
			parms.weight=1;
						
			m_cellHoliday[ calPositon ] = new TextView( m_context ) ;
			m_cellLy[ calPositon ].addView( m_cellHoliday[ calPositon ] ) ;
			if ( ComConstant.NATIONAL.equals(ComConstant.NATIONAL_US)) {
				m_cellHoliday[ calPositon ].setGravity( Gravity.LEFT | Gravity.CENTER_VERTICAL  ) ;
				m_cellHoliday[ calPositon ].setSingleLine(true);
				m_cellHoliday[ calPositon ].setEllipsize(null);
				m_cellHoliday[ calPositon ].setMaxLines(1);
			} else {
				m_cellHoliday[ calPositon ].setGravity( Gravity.RIGHT | Gravity.CENTER_VERTICAL ) ;	
				m_cellHoliday[ calPositon ].setEllipsize(null);
				m_cellHoliday[ calPositon ].setMaxLines(1);
			}	
			
			m_cellHoliday[ calPositon ].setTextSize(m_textSizeHoliday);
			m_cellHoliday[ calPositon ].setLayoutParams (parms);
			
		}

	}	

	public void removeLunarView ( int calLen ) {
		
		for( int i = 0 ; i < calLen ; i++ ) {
			m_cellLy[ i ].removeView(m_cellLunar[i]);
			m_cellLunar[i] = null;
		}
	}
	public void setLunarView(  int calPositon  ) {
		 
		//음력 textview 생성
		if ( m_cellLunar[ calPositon ] == null  ) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  m_cWidth / 4 ,   m_cWidth / 4  );
//			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  LayoutParams.WRAP_CONTENT ,   LayoutParams.WRAP_CONTENT  );
			params.topMargin = 3;
			
			m_cellLunar[ calPositon ] = new TextView( m_context ) ;
			m_cellLy[ calPositon ].addView( m_cellLunar[ calPositon ] );
			m_cellLunar[ calPositon ].setLayoutParams (params);	
		}

	}

	public boolean isExistHolidayText(  int calPositon  ) {
		 boolean ret = false ;
		//두번째 view 가 국공일항목
		if (  m_cellHoliday[ calPositon ] != null &&  !m_cellHoliday[ calPositon ].getText().equals("")) {
			ret = true; 
		} 
		return ret;
	}

	/// 각 버튼들에 setOnClickListener 주기
	public void setOnEvent( )
	
	{

		//다음/이전페이지 처리용 
		if ( m_targetScroll != null ){
			m_targetScroll.setOnTouchListener( new ScrollView.OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					myTouchEvent( v, event );
					return false;
				}
			} ) ;				
		}

		//m_celly  : click event 로 변경
		/// 월화수목금토일 들어가있는 부분에는 눌러도 반응할 필요 없음
		// 2000601 스케줄이나 기념일정보가 없는 경우 스케줄등록화면 open
		for( int i = 7 ; i < 49 ; i++ )
		{
			final int k = i ;
			m_cellTopLy[i].setClickable(true);
			
			m_cellTopLy[i].setOnClickListener( new Button.OnClickListener( ) 
			{
				@Override
				public void onClick(View v) 
				{
//					if( m_cellDate[k].getText( ).toString( ).length() > 0 )
					if( m_calFullDate[ k ] != null && m_calFullDate[ k ].toString( ).length() == 8 )			
					{
//						m_Calendar.set( Calendar.DATE, Integer.parseInt( m_cellDate[k].getText( ).toString( ) ) ) ;
						Calendar clickCal = (Calendar) m_Calendar.clone();
						clickCal.set( Calendar.YEAR, 		 Integer.parseInt( m_calFullDate[ k ].substring(0, 4) ) ) ;				
						clickCal.set( Calendar.MONTH, 		 Integer.parseInt( m_calFullDate[ k ].substring(4, 6) ) - 1 ) ;				
						clickCal.set( Calendar.DAY_OF_MONTH, Integer.parseInt( m_calFullDate[ k ].substring(6) ) ) ;
						
						setClickPosition( k ) ;		
						
						if( m_dayTv != null )
			    			m_dayTv.setText( clickCal.get( Calendar.DAY_OF_MONTH ) + "" ) ;
						printView( ) ;
										
						myClickEvent( 	clickCal.get( Calendar.YEAR ),
										clickCal.get( Calendar.MONTH ) + 1,
										clickCal.get( Calendar.DAY_OF_MONTH ) ) ;
					}
				}
			
			} ) ;
			m_cellTopLy[i].setOnLongClickListener( new Button.OnLongClickListener( ) 
			{

				@Override
				public boolean onLongClick(View v) {
					if( m_calFullDate[ k ] != null && m_calFullDate[ k ].toString( ).length() == 8 )		
					{
						Calendar clickCal = (Calendar) m_Calendar.clone();
						clickCal.set( Calendar.YEAR, 		 Integer.parseInt( m_calFullDate[ k ].substring(0, 4) ) ) ;				
						clickCal.set( Calendar.MONTH, 		 Integer.parseInt( m_calFullDate[ k ].substring(4, 6) ) - 1 ) ;				
						clickCal.set( Calendar.DAY_OF_MONTH, Integer.parseInt( m_calFullDate[ k ].substring(6) ) ) ;

						setClickPosition( k ) ;	
						
						if( m_dayTv != null )
			    			m_dayTv.setText( clickCal.get( Calendar.DAY_OF_MONTH ) + "" ) ;
						printView( ) ;
							
						myLongClickEvent( 	clickCal.get( Calendar.YEAR ),
											clickCal.get( Calendar.MONTH ) + 1,
											clickCal.get( Calendar.DAY_OF_MONTH )) ;
						
											
						
					}

					return false;
				}				
			} ) ;	
			m_cellTopLy[i].setOnLongClickListener( new Button.OnLongClickListener( ) 
			{

				@Override
				public boolean onLongClick(View v) {
					if( m_calFullDate[ k ] != null && m_calFullDate[ k ].toString( ).length() == 8 )		
					{
						Calendar clickCal = (Calendar) m_Calendar.clone();
						clickCal.set( Calendar.YEAR, 		 Integer.parseInt( m_calFullDate[ k ].substring(0, 4) ) ) ;				
						clickCal.set( Calendar.MONTH, 		 Integer.parseInt( m_calFullDate[ k ].substring(4, 6) ) - 1 ) ;				
						clickCal.set( Calendar.DAY_OF_MONTH, Integer.parseInt( m_calFullDate[ k ].substring(6) ) ) ;

						setClickPosition( k ) ;	
						
						if( m_dayTv != null )
			    			m_dayTv.setText( clickCal.get( Calendar.DAY_OF_MONTH ) + "" ) ;
						printView( ) ;	
						myLongClickEvent( 	clickCal.get( Calendar.YEAR ),
											clickCal.get( Calendar.MONTH ) + 1,
											clickCal.get( Calendar.DAY_OF_MONTH )) ;
						
											
						
					}

					return false;
				}				
			} ) ;
			m_cellTopLy[i].setOnTouchListener( new LinearLayout.OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					myTouchEvent( v, event );
					return false;
				}
			} ) ;				
		}

	}
	/// 각 버튼들에 setOnClickListener 주기
	public void setOnEventWeek( )
	
	{
		boolean isclick = false;
		//가로칸(주수 단위로 처리할 경우 하단에 기술)
		int weekLine = m_lineLy.length;
		for( int i = 0 ; i < weekLine ; i++ )
		{
			if ( i == 6  ) {
				 if( m_cellDate[i * 7].getText( ).toString( ).length() > 0 ) {
					 isclick = true;
				 } else {
					 isclick = false;
				 }
				
			} else {
				isclick = true;
			}
			
			if ( isclick ) {
				
				m_lineLy[i].setClickable(true);
				final int k = i ;
				m_lineLy[i].setOnClickListener( new Button.OnClickListener( ) 
				{
					@Override
					public void onClick(View v) 
					{
						if ( k > 0 ) {

							for( int l = (k * 7) ; l < (k * 7) + 6 ; l++ ) {
								if( m_calFullDate[ l ] != null && m_calFullDate[ l ].toString( ).length() == 8 )	
								{
									Calendar clickCal = (Calendar) m_Calendar.clone();
									clickCal.set( Calendar.YEAR, 		 Integer.parseInt( m_calFullDate[ l ].substring(0, 4) ) ) ;				
									clickCal.set( Calendar.MONTH, 		 Integer.parseInt( m_calFullDate[ l ].substring(4, 6) ) - 1 ) ;				
									clickCal.set( Calendar.DAY_OF_MONTH, Integer.parseInt( m_calFullDate[ l ].substring(6) ) ) ;
									
									setClickPosition( l ) ;
									
									myClickEvent( 	clickCal.get( Calendar.YEAR ),
													clickCal.get( Calendar.MONTH ) + 1,
													clickCal.get( Calendar.DAY_OF_MONTH ) ) ;
									
									break;
								}							
							}
						}

					}
				
				} ) ;
			}				
			}


		
	}	
	/// 달력을 띄운 다음 년 월 일을 출력해줌
	public void printView( )
	{
		/// 텍스트 뷰들이 있으면 그 텍스트 뷰에다가 년 월 일을 적어넣음
		if( m_yearTv != null )
			m_yearTv.setText( m_Calendar.get( Calendar.YEAR ) + "" ) ;
		if( m_mothTv != null ) {
			//언어권에 따라 배열 순서 다름 
			if ( ComUtil.isLanguageFront(m_context)) {
				m_mothTv.setText( ( m_Calendar.get( Calendar.MONTH ) + 1 ) + "" ) ;				
			} else  {
				m_mothTv.setText(SmDateUtil.getMonthForEng(( m_Calendar.get( Calendar.MONTH ) + 1 ) + "" )) ;
			}
		}
			
		if( m_dayTv != null )
			m_dayTv.setText( m_Calendar.get( Calendar.DAY_OF_MONTH ) + "" ) ;

	} 

	public String getThisYear( ){		
		return m_Calendar.get( Calendar.YEAR ) + "";		
	}	
	public String getThisMonth( ){
		return ( m_Calendar.get( Calendar.MONTH ) + 1 ) + "";
	}	
	public String getThisDay( ){
		return m_Calendar.get( Calendar.DAY_OF_MONTH ) + "";
	}			
	/// 년도와 월을 앞~ 뒤~로 
	// -. 스케줄 정보 추가
	public void preYear( )
	{
		m_Calendar.add( Calendar.YEAR, -1 ) ;
        if ( m_isFullCalendar ) {
        	setFullContentext( ) ;
        } else {
        	setContentext( ) ;
        }
		printView( ) ;
	}
	public void nextYear( )
	{
		m_Calendar.add( Calendar.YEAR, 1 ) ;
        if ( m_isFullCalendar ) {
        	setFullContentext( ) ;
        } else {
        	setContentext( ) ;
        }
		printView( ) ;

	}
	public void preMonth( )
	{
		m_Calendar.add( Calendar.MONTH, -1 ) ;
        if ( m_isFullCalendar ) {
        	setFullContentext( ) ;
        } else {
        	setContentext( ) ;
        }
		printView( ) ;

		
	}
	public void nextMonth( )
	{
		m_Calendar.add( Calendar.MONTH, 1 ) ;
        if ( m_isFullCalendar ) {
        	setFullContentext( ) ;
        } else {
        	setContentext( ) ;
        }
		printView( ) ;

	} 
	
	public void changeCal( Calendar cal )
	{
		m_Calendar = (Calendar) cal.clone();
        if ( m_isFullCalendar ) {
        	setFullContentext( ) ;
        } else {
        	setContentext( ) ;
        }
		
	}

	//화면전환시 날짜조회조건 값 setting
	public void changeLandscape( String calyear, String calmonth, String calday )
	{
		
		m_Calendar.set( Calendar.YEAR, Integer.parseInt(calyear) ) ;
		m_Calendar.set( Calendar.MONTH, Integer.parseInt(calmonth) - 1 ) ;
		m_Calendar.set( Calendar.DAY_OF_MONTH, Integer.parseInt(calday) ) ;

	}  	
	/// 텍스트뷰를 넣어주면 각각 뿌려줌 (빈게 들어있으면 안뿌림)
	public void setViewTarget( TextView [] tv ) 
	{
		m_yearTv = tv[0] ;
		m_mothTv = tv[1] ;
        m_dayTv = tv[2] ;
	}
        
	/// 버튼을 넣어주면 알아서 옵션 넣어줌 (역시나 빈게 있으면 이벤트 안넣음)
	
	public void setControl( Button [] btn )
	{
		m_preYearBtn = btn[0] ;
        m_nextYearBtn = btn[1] ;
        m_preMonthBtn = btn[2] ;
        m_nextMonthBtn = btn[3] ;
        
        if( m_preYearBtn != null )
           m_preYearBtn.setOnClickListener( new Button.OnClickListener( ) 
           {
				@Override
				public void onClick(View v) 
				{
					preYear( ) ;
				}
			} ) ;
        if( m_nextYearBtn != null )
            m_nextYearBtn.setOnClickListener( new Button.OnClickListener( ) 
            {
				@Override
				public void onClick(View v) 
				{
					nextYear( ) ;
				}
			} ) ;
        if( m_preMonthBtn != null )
            m_preMonthBtn.setOnClickListener( new Button.OnClickListener( ) 
            {
				@Override
				public void onClick(View v) 
				{
					preMonth( ) ;
				}
			} ) ;
        if( m_nextMonthBtn != null )
            m_nextMonthBtn.setOnClickListener( new Button.OnClickListener( ) 
            {
				@Override
				public void onClick(View v) 
				{
					nextMonth( ) ;
				}
			} ) ;
	}
	
	/// 버튼을 넣어주면 알아서 옵션 넣어줌 (역시나 빈게 있으면 이벤트 안넣음) -> 이미지버튼
	public void setControl( ImageButton [] btn )
	{
		m_preYearImgBtn = btn[0] ;
        m_nextYearImgBtn = btn[1] ;
        m_preMonthImgBtn = btn[2] ;
        m_nextMonthImgBtn = btn[3] ;
        
        if( m_preYearImgBtn != null )
        	m_preYearImgBtn.setOnClickListener( new Button.OnClickListener( ) 
           {
				@Override
				public void onClick(View v) 
				{
					preYear( ) ;
				}
			} ) ;
        if( m_nextYearImgBtn != null )
        	m_nextYearImgBtn.setOnClickListener( new Button.OnClickListener( ) 
            {
				@Override
				public void onClick(View v) 
				{
					nextYear( ) ;
				}
			} ) ;
        if( m_preMonthImgBtn != null )
        	m_preMonthImgBtn.setOnClickListener( new Button.OnClickListener( ) 
            {
				@Override
				public void onClick(View v) 
				{
					preMonth( ) ;
				}
			} ) ;
        if( m_nextMonthImgBtn != null )
        	m_nextMonthImgBtn.setOnClickListener( new Button.OnClickListener( ) 
            {
				@Override
				public void onClick(View v) 
				{
					nextMonth( ) ;
				}
			} ) ;
	}	
	/// 버튼을 넣어주면 알아서 옵션 넣어줌 (역시나 빈게 있으면 이벤트 안넣음) -> 이미지버튼
	public void setControlClose( ImageButton btn, final Dialog dlg )
	{
		m_close = btn ;
        
        if( m_close != null )
        	m_close.setOnClickListener( new Button.OnClickListener( ) 
           {
				@Override
				public void onClick(View v) 
				{
					dlg.dismiss();
				}
			} ) ;
        
	}		
	/// 원하는 포멧대로 날짜를 구해줌 
	/// 예) 
	/// String today = getData( "yyyy-MM-dd" )이런식?
	public String getData( String format )
	{
		SimpleDateFormat sdf = new SimpleDateFormat( format, Locale.US ) ;
		return sdf.format( new Date( m_Calendar.getTimeInMillis( ) ) ) ;
	}
	
	/// 달력에서 날짜를 클릭하면 이 함수를 부른다.
	public void myClickEvent( int yyyy, int MM, int dd )
	{
//		Log.d( "yyyy", "" + yyyy ) ;
//		Log.d( "MM", "" + MM ) ;
//		Log.d( "dd", "" + dd ) ;
	}
	/// 달력에서 날짜를 클릭하면 이 함수를 부른다.
	public void myLongClickEvent( int yyyy, int MM, int dd )
	{
//		Log.d( "yyyy2", "" + yyyy ) ;
//		Log.d( "MM2", "" + MM ) ;
//		Log.d( "dd2", "" + dd ) ;
		
	}  
	
	/// 달력에서 날짜를 클릭하면 이 함수를 부른다.
	public void setClickPosition( int pos )
	{
		m_clickPos = pos;
	}	
	
	/// 달력에서 날짜를 클릭하면 이 함수를 부른다.
	public int getClickPosition( )
	{
		return m_clickPos; 
	}	
	
	/// 달력에서 touch event
	public boolean myTouchEvent( View v, MotionEvent event )
	{
		return false;
		//Log.d( "myTouchEvent!!!!", "" + "myTouchEvent" ) ;
		
	} 	
	public int pixelToDip( int arg )
	{
		m_displayScale = m_context.getResources( ).getDisplayMetrics( ).density ;
		return (int) ( arg * m_displayScale ) ;
	}
	
	public gsCalendarColorParam getBasicColorParam( )
	{
		return new gsCalendarColorParam( ) ;
	}
    @Override
	protected void onStart() {
		super.onStart();
	}   
    @Override    
    protected void onResume() {
    	super.onResume(); 
    	
    }        
    @Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);		
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
	}

	@Override    
    protected void onPause() {  
		super.onPause();
    }
	@Override
	protected void onDestroy() { 	
        super.onDestroy(); 
        RecycleUtil.recursiveRecycle(m_targetLayout);
        
    }
}

