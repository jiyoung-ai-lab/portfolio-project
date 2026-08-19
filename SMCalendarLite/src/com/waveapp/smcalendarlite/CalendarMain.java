package com.waveapp.smcalendarlite;
 
import java.text.Collator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.waveapp.smcalendarlite.adapter.SpecialdayListAdapter2;
import com.waveapp.smcalendarlite.common.ComConstant;
import com.waveapp.smcalendarlite.database.LunarDataDbAdaper;
import com.waveapp.smcalendarlite.database.ScheduleDbAdaper;
import com.waveapp.smcalendarlite.database.SpecialDayDbAdaper;
import com.waveapp.smcalendarlite.database.UsermanagerDbAdaper;
import com.waveapp.smcalendarlite.handler.ComMenuHandler;
import com.waveapp.smcalendarlite.handler.MenuHandler;
import com.waveapp.smcalendarlite.handler.MessageHandler;
import com.waveapp.smcalendarlite.info.ScheduleInfo;
import com.waveapp.smcalendarlite.info.SpecialDayInfo;
import com.waveapp.smcalendarlite.link.BannerLink;
import com.waveapp.smcalendarlite.util.ComUtil;
import com.waveapp.smcalendarlite.util.RecycleUtil;
import com.waveapp.smcalendarlite.util.SmDateUtil;
import com.waveapp.smcalendarlite.util.ViewUtil;
import com.waveapp.smcalendarlite.util.gsCalendar;
import com.waveapp.smcalendarlite.util.gsCalendar.gsCalendarColorParam;

public class CalendarMain extends SMActivity implements 
					OnClickListener, OnItemClickListener, OnDrawerScrollListener

  {
	
	LinearLayout viewroot;
	TextView tvs[] ;

	ImageButton btns[] ;

	ViewFlipper mFlipper;
	SlidingDrawer mSlider;
	ListView listView;
	LinearLayout lv;
	SpecialdayListAdapter2 mAdapter;

    private int mYear;
    private int mMonth;
    private int mDay;
    
    long mId;
    String mSpecialdayName;
    String  mMessage; 
    
	int m_nPreScrollPosX = 0;    //scroll 용
	int m_nPreScrollPosY = 0;    //scroll 용
	int m_nPreCellPosX   = 0;    //cell용
	int m_nPreCellPosY   = 0;    //cell용
	int m_nPreActionMove = 0;    //cell용

	class myGsCalendar extends gsCalendar
	{

		Context mCtx ;
		LinearLayout mLv ;
		ScrollView mScroll ;
		boolean mIsFull ;
		
		public myGsCalendar(Context context, LinearLayout layout, ScrollView scroll, boolean isfull ) 
		{
			super(context, layout, scroll, isfull);
			this.mCtx 		= context;
			this.mLv 		= layout;
			this.mScroll 	= scroll;
		}
		
		@Override
		public void myClickEvent(int yyyy, int MM, int dd) 
		{
			super.myClickEvent(yyyy, MM, dd);
			
			//스케줄정보 존재여부에 따라 화면 분기
			mYear 	= yyyy;
			mMonth 	= MM;
			mDay 	= dd; 
			
			/// 선택된 날짜는 배경 이미지를 변경
	        cal.setTodayStyle(  CalendarMain.this.getResources( ).getColor(R.color.calcellselback)) ;
	        cal.setSelectedDay( CalendarMain.this.getResources( ).getDrawable( R.drawable.sm_click_cell ), 
	        		mYear, mMonth, mDay, cal.getClickPosition()) ;	    
	        
	        /*
		      //Context menu
	        ((Activity) mCtx).registerForContextMenu(lv);
	        ((Activity) mCtx).openContextMenu(lv);
	        
	       
	       */
	        SchedulePop dlg = new SchedulePop(mCtx);
	        dlg.setDate(mYear, mMonth, mDay);
	        dlg.show( ) ;
			
		}
		
		@Override
		public void myLongClickEvent(int yyyy, int MM, int dd ) 
		{
			super.myLongClickEvent(yyyy, MM, dd );
			unregisterForContextMenu(lv);
			
			mYear 	= yyyy;
			mMonth 	= MM;
			mDay 	= dd; 

			/// 선택된 날짜는 배경 이미지를 변경
	        cal.setTodayStyle(  CalendarMain.this.getResources( ).getColor(R.color.calcellselback)) ;
	        cal.setSelectedDay( CalendarMain.this.getResources( ).getDrawable( R.drawable.sm_click_cell ), 
	        		mYear, mMonth, mDay, cal.getClickPosition()) ;	 
	        
			boolean isDataExist = cal.isSelectedDayExistSchedule();
			if ( isDataExist == true ) {
				callScheduleTabForDate();
			}   else {
				callScheduleManager();
			}	        
			
			
		}	
		/*
		 * next/previous page 
		 *  return true -> stop
		 * @see com.waveapp.smcalendar.util.gsCalendar#myTouchEvent(android.view.View, android.view.MotionEvent)
		 */
		@Override
		public boolean myTouchEvent(View v, MotionEvent event) {
			

			//super.myTouchEvent( v,event );
			//scroll 의 경우 action down 발생하지 않음--> move 로 체크
			
			if (  v == mScroll ) {
//				Log.w(">>>>>>>>>>>>>>>>>>>> mScroll   :", Integer.toString(event.getAction()));
//				Log.w(">>>>>>>>>>>>>>>>>>>> mScrollX  :", Integer.toString((int)event.getRawX()));
//				Log.w(">>>>>>>>>>>>>>>>>>>> mScrollY  :", Integer.toString((int)event.getRawY()));
//				Log.w(">>>>>>>>>>>>>>>>>>>> mScrollX2 :", Integer.toString((int)event.getRawX()));
//				Log.w(">>>>>>>>>>>>>>>>>>>> mScrollY2 :", Integer.toString((int)event.getRawY()));				
				if (event.getAction() == MotionEvent.ACTION_MOVE)
	 			{
		   			//최초 좌표저장
		   			if ( m_nPreScrollPosX == 0 && m_nPreScrollPosY == 0 ) {
		   				m_nPreScrollPosX = (int)event.getRawX();
		   				m_nPreScrollPosY = (int)event.getRawY();
		   			}
				}	
					
				if (event.getAction() == MotionEvent.ACTION_UP)
				{
					//event.get
					int nPosX = (int)event.getRawX();
					int nPosY = (int)event.getRawY();
					
					//최초 좌표정보와 같을 경우 skip
					if ( m_nPreScrollPosX == 0 && m_nPreScrollPosY == 0 ) return false;
					
					int absX =  Math.abs( nPosX - m_nPreScrollPosX ) ;
					int absY =  Math.abs( nPosY - m_nPreScrollPosY ) ;
					
					//x, y 좌표 gap 비교  : y변경값이 클경우 scroll 우선처리
					if  ( absX <=  absY ) {
						m_nPreScrollPosX = 0;
						m_nPreScrollPosY = 0;
						return false;
					}
					
					if ( nPosX  < m_nPreScrollPosX )
					{
						MoveNextView();
						return true;
					}
					else if (nPosX > m_nPreScrollPosX)
					{
						MovewPreviousView();
						return true;
					}
					return true;
				}	
			} else	{
				//action_move가 중간에 처리된 경우만 스크롤처리
//				Log.w(">>>>>>>>>>>>>>>>>>>> mOther   :", Integer.toString(event.getAction()));
//				Log.w(">>>>>>>>>>>>>>>>>>>> mOtherX  :", Integer.toString((int)event.getRawX()));
//				Log.w(">>>>>>>>>>>>>>>>>>>> mOtherY  :", Integer.toString((int)event.getRawY()));
//				Log.w(">>>>>>>>>>>>>>>>>>>> mOtherX2 :", Integer.toString((int)event.getRawX()));
//				Log.w(">>>>>>>>>>>>>>>>>>>> mOtherY2 :", Integer.toString((int)event.getRawY()));
				
		   		if (event.getAction() == MotionEvent.ACTION_DOWN)
				{
		   			//최초 좌표저장
		   			if ( m_nPreCellPosX == 0 && m_nPreCellPosY == 0 ) {
		   				m_nPreCellPosX = (int)event.getRawX();
		   				m_nPreCellPosY = (int)event.getRawY();

		   			}
				}
		   		if (event.getAction() == MotionEvent.ACTION_MOVE)
				{
		   			//ACTION_MOVE 처리여부
		   			if ( m_nPreCellPosX != 0 || m_nPreCellPosY != 0 ) {
		   				m_nPreActionMove = 1;

		   			}
				}				
		   		if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP)
				{
		   			
		   			if ( m_nPreActionMove > 0 ) {
						int nPosX = (int)event.getRawX();
						int nPosY = (int)event.getRawY();
						
						//최초 좌표정보와 같을 경우 skip
						if ( m_nPreCellPosX == 0 && m_nPreCellPosY == 0 ) return false;

						int absX =  Math.abs( nPosX - m_nPreCellPosX ) ;
						int absY =  Math.abs( nPosY - m_nPreCellPosY ) ;
						
						//x, y 좌표 gap 비교  : y변경값이 클경우 scroll 우선처리
						if  ( absX <=  absY ) {
							m_nPreCellPosX = 0;
							m_nPreCellPosY = 0;
							m_nPreActionMove = 0;
							return false;
						}
						
						if ( nPosX  < ( m_nPreCellPosX) ) {
							MoveNextView();
							return true;
						} else if (nPosX > ( m_nPreCellPosX )) {
							MovewPreviousView();
							return true;
						}		   				
		   			}

				}
			}

			return false;
		}
		
	    private void MoveNextView()
	    {	    	
	    	cal.nextMonth();
			viewRefresh();
			ViewUtil.nextFlipper(mCtx, mFlipper);
	    }
	    
	    private void MovewPreviousView()
	    {	    	
	    	cal.preMonth();
			viewRefresh();
			ViewUtil.previousFlipper(mCtx, mFlipper);
	    }			
		
	}
	
	
	myGsCalendar cal ;

	SpecialDayDbAdaper mSpecialDbHelper;
	
	ArrayList<ScheduleInfo> 		mScheduleList;
	ArrayList<SpecialDayInfo> 		mSpecialList;	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	
        setContentView(R.layout.calendar_main);
        
        Resources  res = getResources();
        
        //release 할 전체 view
        viewroot = (LinearLayout)findViewById( R.id.viewroot ) ;
        
        /// 달력을 띄울 대상 레이아웃
        lv = (LinearLayout)findViewById( R.id.calendar_lLayout ) ;
        ScrollView scroll = (ScrollView)findViewById( R.id.sc_cal ) ;
        listView 	= (ListView) findViewById(android.R.id.list);
//        tvToday 		= (TextView)findViewById(R.id.tv_today);
        
        //view flipper
        mFlipper = (ViewFlipper)findViewById(R.id.vf_cal);
        mSlider = (SlidingDrawer)findViewById(R.id.sd_list);
        
        /// 년 월 일 표시할 텍스트뷰
        tvs = new TextView[3] ;
        tvs[0] = (TextView)findViewById( R.id.year ) ;
        tvs[1] = (TextView)findViewById( R.id.month ) ;
        tvs[2] = null ; /// 일은 표시하지 않음
        
        /// 누르면 년 월 일 조절할 버튼
        btns = new ImageButton[4] ;
        btns[0] = (ImageButton)findViewById( R.id.previousyear ) ;
        btns[1] = (ImageButton)findViewById( R.id.nextyear ) ;
        btns[2] = (ImageButton)findViewById( R.id.previousmonth ) ;
        btns[3] = (ImageButton)findViewById( R.id.nextmonth ) ;

        /// 달력객체 생성
        cal = new myGsCalendar( this, lv , scroll,  ViewUtil.isFullCalendarFromPreference(this)  ) ;
        
        /// 색상 설정할 객체 생성
        gsCalendarColorParam cParam = new gsCalendarColorParam( ) ;

        cParam.m_cellColor 			= res.getColor(R.color.calcellback);
        cParam.m_textColor 			= res.getColor(R.color.caldaytext);
        cParam.m_saturdayTextColor 	= res.getColor(R.color.calsaturdat);
        cParam.m_sundayTextColor 	= res.getColor(R.color.calsunday); 
        cParam.m_lineColor 			= res.getColor(R.color.calline);
        
        cParam.m_topCellColor 		= res.getColor(R.color.calcellweek);
        cParam.m_topTextColor 		= res.getColor(R.color.caldaytext);
        cParam.m_topSundayTextColor 	= res.getColor(R.color.caldaytext);
        cParam.m_topSaturdatTextColor 	= res.getColor(R.color.caldaytext);  

      //스케줄 이미지 setting
        cal.setScheduleCellBackground( getResources( ).getDrawable( R.drawable.sm_cal_schedule_cell ) ) ;
        
        /// 셋팅한 값으로 색상값 셋~
        cal.setColorParam( cParam ) ;
        
        /// 배경으로 사용할 이미지 얻기-togo
        //Drawable img = getResources( ).getDrawable( R.drawable.bg ) ;
        // 배경 이미지 셋~
        //cal.setBackground( img ) ;

        //화면방향체크
        Configuration conf =  getResources().getConfiguration();
        if ( Configuration.ORIENTATION_LANDSCAPE == conf.orientation ) {
        	//가로
            //공통 top menu setting        	
            ComMenuHandler.callComTopMenuAction(this, ComUtil.setActionTitle(this, R.string.calendar, 0 ), View.INVISIBLE);

        } else {
        	//세로
            //공통 top menu setting
            ComMenuHandler.callComTopMenuAction(this, ComUtil.setActionTitle(this, R.string.calendar, 0 ), View.VISIBLE);

        }   
        
        //달력사이즈 setting ( 메뉴와 날짜바 적용후 보정처리)
        LinearLayout lin_date = (LinearLayout)findViewById( R.id.lin_date ) ;
        LinearLayout.LayoutParams dateParam = (LayoutParams) lin_date.getLayoutParams();		
        cal.setCalendarSizeMain( conf.orientation, ( ComMenuHandler.getComMenuHeight( this ) + dateParam.height )) ;
        
        //광고배너(addmob)
        BannerLink banner = new BannerLink();
        banner.callBannerLink(this); 
        
        ///글짜크기
        cal.setTextSizePerScale(9, 7, 6, 6);
        
        /// 누르면 반응할 버튼들 셋팅
        cal.setControl( btns ) ;
        
        /// 년 월 일을 띄울 텍스트뷰 셋팅
        cal.setViewTarget( tvs ) ;

        
        //방향전환시 달력초기화 방지를 위한 코딩... 아 정말 어디까지 해야하나.... -.-
        if (savedInstanceState != null) {
        	String calyear 	= savedInstanceState.getString(ComConstant.CAL_YEAR);
        	String calmonth = savedInstanceState.getString(ComConstant.CAL_MONTH);
        	String calday = savedInstanceState.getString(ComConstant.CAL_DAY);
        	if ( calyear != null && calmonth != null && !calyear.trim().equals("") && !calmonth.trim().equals(""))
        		cal.changeLandscape(calyear, calmonth, calday);
        }
        
        //달력 화면 초기셋팅
        cal.initCalendar() ;      
        
        //클릭이벤트
        btns[0].setOnClickListener(this); 
        btns[1].setOnClickListener(this);        
        btns[2].setOnClickListener(this); 
        btns[3].setOnClickListener(this); 
        mSlider.setOnDrawerScrollListener(this);
        
    	//날짜bar layout 변경
        setDateBar();
    }

    
    @Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
    
   //언어값에 따라 날짜bar 구성 변경
    protected void setDateBar() {
    	
        TextView year_tv 	= (TextView)findViewById( R.id.year_tv ) ;
        TextView month_tv 	= (TextView)findViewById( R.id.month_tv ) ;   
        TextView gap 		= (TextView)findViewById( R.id.gap ) ; 
                
        if ( ComUtil.isLanguageFront(this))  {        	
        	ViewUtil.addViewLayoutParam(year_tv, RelativeLayout.RIGHT_OF, tvs[0]);
        	ViewUtil.addViewLayoutParam(gap, RelativeLayout.RIGHT_OF, year_tv);
        	ViewUtil.addViewLayoutParam(tvs[1], RelativeLayout.RIGHT_OF, gap);
        	ViewUtil.addViewLayoutParam(month_tv, RelativeLayout.RIGHT_OF, tvs[1]);
        	
        	year_tv.setText(ComUtil.getStrResource(this, R.string.year));
        	month_tv.setText(ComUtil.getStrResource(this, R.string.month));
        } else {
        	ViewUtil.addViewLayoutParam(month_tv, RelativeLayout.RIGHT_OF, tvs[1]);
        	ViewUtil.addViewLayoutParam(gap, RelativeLayout.RIGHT_OF, month_tv);
        	ViewUtil.addViewLayoutParam(tvs[0], RelativeLayout.RIGHT_OF, gap);
        	ViewUtil.addViewLayoutParam(year_tv, RelativeLayout.RIGHT_OF, tvs[0]);
        	
        	year_tv.setText("");
        	month_tv.setText(",");
        }  
            	
    }
	protected void viewRefresh() { 

		//drag 좌표정보 초기화
		m_nPreScrollPosX = 0;
		m_nPreScrollPosY = 0;
		m_nPreCellPosX = 0;
		m_nPreCellPosY = 0;
		m_nPreActionMove = 0;
		
		/// 1) 날짜별 backgroud setting
		/// 2) 선택된 날짜는 배경 이미지를 변경
        cal.setTodayStyle(  CalendarMain.this.getResources( ).getColor(R.color.calcellselback)) ;
        cal.setSelectedDay( CalendarMain.this.getResources( ).getDrawable( R.drawable.sm_click_cell ), 
        		mYear, mMonth, mDay, cal.getClickPosition()) ;	 
   	
        
    	//1.DB Open / value reset(순서중요)
        mSpecialDbHelper = new SpecialDayDbAdaper(this);
        mSpecialDbHelper.open();
        //국공일
        setHoliday();	
        //기념일
        setSpecialDay(); 
        mSpecialDbHelper.close();
        
        //일정
        setScheduleDay();
        
        //음력(한중일만)
        if ( ComConstant.LOCALE.equals(ComConstant.LOCALE_KO) ||
   			 ComConstant.LOCALE.equals(ComConstant.LOCALE_ZH) ||
   			 ComConstant.LOCALE.equals(ComConstant.LOCALE_JA) ) {
        	 setLunarDateView();
        }
    }

    @Override
	public void onClick(View v) {
		if ( v == btns[0] ) {
			
			cal.preYear();
			
		} else if ( v == btns[1] ) {
			
			cal.nextYear();
			
		} else if ( v == btns[2] ) {
			
			cal.preMonth();
			
		}  else if ( v == btns[3] ) {
			
			cal.nextMonth();

		}  
		
		if ( v == btns[0] || v == btns[1] || v == btns[2] || v == btns[3] ) {
			viewRefresh();	
		
		}
	}
    /*
     * menu create
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = this.getMenuInflater(); 
        inflater.inflate(R.menu.com_option_menu, menu);
        return true;
    }
    
    @Override
	public boolean onPrepareOptionsMenu(Menu menu) {
    	boolean  ret = super.onPrepareOptionsMenu(menu);  
        MenuHandler mh = new MenuHandler(this);
        mh.preMenuEvent(menu);  	
		return ret;
	}


	@Override
    public boolean onOptionsItemSelected( MenuItem item) {
    	MenuHandler menu = new MenuHandler(this);
    	boolean ret = menu.onMenuItemEvent ( item );    
        return ret;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	
    	super.onCreateContextMenu(menu, view, menuInfo); 
    	
    	if ( view == lv ) {    	
    		//선택된 날짜 
    		String sDate = SmDateUtil.getDateFormat(mYear, mMonth, mDay);    		
	    	menu.setHeaderTitle( SmDateUtil.getDateFullFormat(this, sDate, true, false) );
	    	menu.setHeaderIcon(R.drawable.sm_menu_more);
	    	MenuInflater inflater = getMenuInflater(); 
	        inflater.inflate(R.menu.cal_ctx_menu, menu);
	        
    	} else if ( view == listView ) {   
    		//선택된 기념일명 
	    	menu.setHeaderTitle( mSpecialdayName );
	    	menu.setHeaderIcon(R.drawable.sm_menu_more);
	        
	    	MenuInflater inflater = getMenuInflater(); 
	        inflater.inflate(R.menu.specialday_ctx_menu, menu);
	        
    	}
    }
        
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	MenuHandler menuHd = new MenuHandler(this);
    	
    	switch(item.getItemId()) {
		    case R.id.menu_schedule_date:
		    	callScheduleTabForDate();
		        return true; 
	        case R.id.menu_schedule_new:
	        	callScheduleManager();
	            return true;            
	        case R.id.menu_specialday_new:
	        	callSpecialdayManager();
	            return true; 
	        case R.id.menu_todo_new:
	        	callTodoManager();
	            return true; 		 
		    ///////////////////////////////////////////   위는 달력, 아래는 리스트 메뉴
	//        case R.id.menu_specialday_new:
	//        	mId = (long)0;        	
	//        	callSpecialdayManager(ComUtil.getStrResource(this, R.string.add));
	//            return true;
	        case R.id.menu_specialday_modify:
	        	callSpecialdayManager(ComUtil.getStrResource(this, R.string.modify));
	            return true; 
		    case R.id.menu_specialday_copy:
		    	callSpecialdayManager(ComUtil.getStrResource(this, R.string.copy));
		        return true; 
		    case R.id.menu_specialday_delete:
		    	callSpecialdayManager(ComUtil.getStrResource(this, R.string.delete));
		        return true; 	
		    case R.id.menu_kakaotalk:		    	
		    	menuHd.linkToKakaoTalk( mMessage, "");
		        return true;
		    case R.id.menu_evernote: 
		    	menuHd.linkToEverNote( mSpecialdayName, mMessage );
		        return true;	 		        
		    case R.id.menu_sms:	 
		    	menuHd.callSMSView( mMessage);
		        return true;	 
	    }         
    	
        return super.onContextItemSelected(item);

    }
  
    /*
     * 호출화면 : 스케줄등록(ScheduleManager)
     */
	private void callScheduleManager( ) { 
		String date = SmDateUtil.getDateFormat(mYear, mMonth, mDay);

		Bundle bundle = new Bundle();
		if ( date != null && !date.trim().equals("")) {
			bundle.putString(ScheduleDbAdaper.KEY_STARTDATE, date);
			bundle.putString(ScheduleDbAdaper.KEY_ENDDATE, date);
			bundle.putString(ComConstant.SCHEDULE_GUBUN, ComUtil.getStrResource(this, R.string.add));
		}
        Intent mIntent = new Intent(this, ScheduleManager.class);
        mIntent.putExtras(bundle);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(mIntent, ComConstant.ACTIVITY_EDIT);
        
	}	

    /*
     * 호출화면 : 스케줄리스트-tab
     */	
	private void callScheduleTabForDate( ) { 
		
		String sDate = SmDateUtil.getDateFormat(mYear, mMonth, mDay);
		
		Bundle bundle = new Bundle();
		if ( sDate != null && !sDate.trim().equals("")) {
			bundle.putString(ScheduleDbAdaper.KEY_STARTDATE, sDate);
			bundle.putString(ScheduleDbAdaper.KEY_ENDDATE, sDate);
		}
        Intent mIntent = new Intent(this, ScheduleTabForDate.class);
        mIntent.putExtras(bundle);	
        mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    	startActivityForResult(mIntent, ComConstant.ACTIVITY_SELECT);
	}	
    /*
     * 호출화면 : 기념일등록(SpecialdayManager)
     */	
    
	private void callSpecialdayManager(  ) { 
		String year = ComUtil.setBlank(mYear);
		String monthday = SmDateUtil.getMonthDayFormat(mMonth, mDay);
		Bundle bundle = new Bundle();
		if ( monthday != null && !monthday.trim().equals("")) {
			bundle.putString(SpecialDayDbAdaper.KEY_YEAR, year);
			bundle.putString(SpecialDayDbAdaper.KEY_MONTHDAY, monthday);
			bundle.putString(ComConstant.SPECIAL_GUBUN, ComUtil.getStrResource(this, R.string.add));
		}		
        Intent mIntent = new Intent(this, SpecialdayManager.class);
        mIntent.putExtras(bundle);	
        mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(mIntent, ComConstant.ACTIVITY_EDIT);
	}
	private void callSpecialdayManager( String gubun ) { 
		
		Bundle bundle = new Bundle();
		bundle.putLong(SpecialDayDbAdaper.KEY_ID, mId); 
		bundle.putString(ComConstant.SPECIAL_GUBUN, gubun);
		
        Intent mIntent = new Intent(this, SpecialdayManager.class);
        mIntent.putExtras(bundle);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(mIntent, ComConstant.ACTIVITY_EDIT);        
	}	
    /*
     * 호출화면 : todo
     */	
	private void callTodoManager( ) { 

		Bundle bundle = new Bundle();
		bundle.putString(ComConstant.SPECIAL_GUBUN, ComUtil.getStrResource(this, R.string.add));
		
        Intent mIntent = new Intent(this, TodoManager.class);
        mIntent.putExtras(bundle);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(mIntent, ComConstant.ACTIVITY_EDIT);
        
	}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {        
    	super.onSaveInstanceState(outState);
    	
    	//화면 전환시 값 reset 방지용
    	String year = ComUtil.fillSpaceToZero(cal.getThisYear(), 4);
    	String month = ComUtil.fillSpaceToZero(cal.getThisMonth(), 2);
    	String day = ComUtil.fillSpaceToZero(cal.getThisDay(), 2);
		if ( year != null && !year.trim().equals("")) {
			outState.putSerializable(ComConstant.CAL_YEAR, 	year);
			outState.putSerializable(ComConstant.CAL_MONTH, month);
			outState.putSerializable(ComConstant.CAL_DAY, 	day);
		}

    }	    
    @Override 
    protected void onRestoreInstanceState(Bundle outState){
    	super.onRestoreInstanceState(outState);
    }	
   
    @Override    
    protected void onResume() {        
    	super.onResume();
    	
    	//스케줄 및 휴일정보 
        viewRefresh();
    }        
    @Override    
    protected void onPause() {  
    	super.onPause();
    }
    @Override
    protected void onDestroy() { 
    	super.onDestroy();

    	unregisterForContextMenu(lv);
        unregisterForContextMenu(listView);
        
        RecycleUtil.recursiveRecycle(viewroot);
        RecycleUtil.recursiveRecycle(lv);
        
        if ( listView != null ) listView = null;
        if ( mFlipper != null ) mFlipper = null;
        
    }

	/*
     * 스케줄 정보 가져오기 
     */
	public void setScheduleDay( )
    {
		
		//1. Db에서 스케줄 정보가져오기 ( ScheduleInfor 객체로 return) 
		//-. 달력기준 년월일 기간에 따른 db정보 가져오기 ( 달력이다보니 시작종료일이 월초부터 말까지로 처리되는 ..)
		//-. cycle 정보가 있는 경우에는 매주처리 / 없는 경우에는 적용일자 모두 처리

		Calendar iCal 	= Calendar.getInstance();
		//달력의 경우 월단위로 출력되기 때문에 시작일 기준으로 조회
		String fromDay 	= cal.getStartDateInCalendar();
		String endDay 	= cal.getEndDateInCalendar();
		
		getScheduleFromDB(iCal, fromDay, endDay);
	
	}	
	
	/*
	 * 국가공휴일
	 */
	public void setHoliday( )
	{
		//1. Db에서 스케줄 정보가져오기 ( ScheduleInfor 객체로 return) 
		//-. 달력기준 년월일 기간에 따른 db정보 가져오기 ( 달력이다보니 시작종료일이 월초부터 말까지로 처리되는 ..)
		//-. cycle 정보가 있는 경우에는 매주처리 / 없는 경우에는 적용일자 모두 처리

		Calendar iCal 	= Calendar.getInstance();
		String fromDay 	= cal.getStartDateInCalendar();
		String endDay 	= cal.getEndDateInCalendar();
		
		getHolidayFromDB(iCal, fromDay, endDay);
		
		int totalSpe = mSpecialList.size();
		int calLen =  cal.getCellDateSize();
		
		cal.reCreateHolidayText( calLen );		
		
		for ( int i = 0 ; i < totalSpe ; i++ ){
			
			SpecialDayInfo spc = new SpecialDayInfo();
			spc = mSpecialList.get(i);
			
			String sMonthDate 	=  spc.getMonth() + spc.getDay();
			String name 		= spc.getName();
			String holidayYn 	= spc.getHolidayYn();
			
			//기념일 중복시 휴일 우선으로 1건만 처리
			for( int j = 0 ; j < calLen ; j++ ) {
				String sCalMonthDay = cal.getCellDateTextFromArray(j);
				if ( sCalMonthDay != null && !sCalMonthDay.equals("")) {	
					if ( sMonthDate != null && sMonthDate.equals(sCalMonthDay)) {
						cal.setCellHolidayText( j , name );
						cal.setCellHolidayTextColor( j , holidayYn );
					}
				}
			}
		}			
	}
	
	/*
	 * 고객기념일
	 */
	public void setSpecialDay( )
	{
		//1. Db에서 기념일정보가져오기 ( SpecialdayInfo 객체로 return) 
		//-. 달력기준 년월일 기간에 따른 db정보 가져오기 ( 달력이다보니 시작종료일이 월초부터 말까지로 처리되는 ..)
		//-. repeate 정보가 있는 경우 년반복

		Calendar iCal 	= Calendar.getInstance();

		String fromDay 	= cal.getStartDateInCalendar();
		String endDay 	= cal.getEndDateInCalendar();
		
		getSpecialDayFromDB(iCal, fromDay, endDay);

		int totalSpe = mSpecialList.size();
		
		///////////////////
		//음력 때문에 정렬후 처리.... (후덜)
		Collections.sort(mSpecialList ,myComparator);
		
		//하단 기념일 리스트 setting    	
        mAdapter = new SpecialdayListAdapter2(this, R.layout.specialday_dayrow, mSpecialList);
        listView.setAdapter(mAdapter);	
        mAdapter.notifyDataSetChanged();

        TextView mHandle 		= (TextView)findViewById(R.id.tv_slide);
        
        mHandle.setText(ComUtil.getStrResource(this, R.string.specialday) 
        	    + "( " + ComUtil.intToString(totalSpe) + " )" );
		listView.setOnItemClickListener(this);
	}

	/*
	 * schedule data(year, month fix)
	 */
    public void getScheduleFromDB( Calendar iCal, String fromdate, String todate ) {

    	int calLen =  cal.getCellDateSize();
        int[] cellScheuleCnt = new int[calLen];
        
        //해상도에 따라 스케줄 최대수 변경
        int cellSchMaxCnt = cal.getScheduleMaxCnt();
        
        //첫번째 7개는 Skip (dayofweek)
		for ( int ini = 0 ; ini < calLen ; ini++) {
			cellScheuleCnt[ini] = 0 ;
		} 
		
		if (fromdate != null && todate != null) {
			//Data select 
			ScheduleDbAdaper mDbHelper = new ScheduleDbAdaper(this);
	        mDbHelper.open();
			Cursor cur = mDbHelper.fetchFromToScheduleForCal(fromdate, todate);
			mDbHelper.close();
			
			//초기화
			UsermanagerDbAdaper mDbUser = new UsermanagerDbAdaper(this);
			mDbUser.open();
			
			//loop
			int totalSch = cur.getCount();
			cal.setScheduleLinearLayout( calLen, cellSchMaxCnt );
			
			//임시 : 기념일 추가
			int totalSpe = mSpecialList.size();
						
			for ( int sp = 0 ; sp < totalSpe ; sp++ ){
				
				SpecialDayInfo spc = new SpecialDayInfo();
				spc = mSpecialList.get(sp);
				
				String sMonthDate 	=  spc.getMonth() + spc.getDay();
				for( int j = 0 ; j < calLen ; j++ ) {
					String sCalMonthDay = cal.getCellDateTextFromArray(j);
					
					if (( sMonthDate != null && sMonthDate.equals(sCalMonthDay) ) && cellScheuleCnt[j] < cellSchMaxCnt  ) {	
						cal.setScheduleTextLayout( j, cellScheuleCnt[j] , cellSchMaxCnt );
//						cal.setCellScheduleBackGround ( j , cellScheuleCnt[j] , md );
						cal.setCellScheduleBackGroundForSpecial ( j , cellScheuleCnt[j]);
						
						cal.setCellScheduleText( j , cellScheuleCnt[j] , spc.getName() ) ;								
						cellScheuleCnt[j]++;
					
					//날짜별 최대 스케줄보다 많은 경우 추가정보보여줌
					} else if (( sMonthDate != null && sMonthDate.equals(sCalMonthDay) ) && cellScheuleCnt[j] == cellSchMaxCnt  ) {
//						Drawable md = this.getResources( ).getDrawable( R.drawable.sm_cal_schedule_cell );
//						cal.setCellScheduleBackGround ( j , cellScheuleCnt[j] - 1 , md );
						cal.setCellScheduleBackGroundForSpecial ( j , cellScheuleCnt[j] - 1 );
						String moretext =  "(+)"  +  cal.getCellScheduleText( j , cellSchMaxCnt - 1 )  ;
						cal.setCellScheduleText( j , cellScheuleCnt[j] - 1 , moretext);
						cellScheuleCnt[j]++;
					}
						
				}

			}
			
			/////////////////
			for(int i = 0 ; i < totalSch ; i++) {
				
				String schedulename = cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_NAME));
				long userid 		= cur.getLong((cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_USERID)));
				int usercolor 		= UsermanagerDbAdaper.getUserColor(this, mDbUser, userid);
				String cycle 		= cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_CYCLE));
				String dbstartdate 	= cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_STARTDATE));
				String dbenddate 	= cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_ENDDATE));
				String repeatedate 	= cur.getString(cur.getColumnIndexOrThrow(ScheduleDbAdaper.KEY_REPEATDATE));
				
				
				//요일별 value set
				int[] arrDayofweek = new int [7];				
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

				//매주  : 요일 체크
				//매월  : 해당달 해당일
				//없음  : 기간전부
				
				String [] loop ;
				if ( cycle  != null && cycle.equals("Y")) {
					loop = SmDateUtil.getDateFromDayOfWeekArr(arrDayofweek, fromdate, todate, dbstartdate, dbenddate, iCal);
				} else if ( cycle  != null && cycle.equals("M")) {
					loop = SmDateUtil.getDateFromEveryMonth(repeatedate, fromdate, todate, dbstartdate, dbenddate, iCal);
				} else {
					loop = SmDateUtil.getDateFromDate(fromdate, todate, dbstartdate, dbenddate, iCal);
				}
				if ( loop != null) {
					int loopLen = loop.length;
					Drawable md = this.getResources( ).getDrawable( R.drawable.sm_cal_schedule_cell );	
					md.setColorFilter(ViewUtil.drawBackGroundColor ( usercolor ));
					
					for ( int k = 0 ; k < loopLen ; k++ ) {
						String sMonthDate 	=  loop[k].toString().substring(4);
						for( int j = 0 ; j < calLen ; j++ ) {
							//날짜별 최대 스케줄만큼 스케줄명 setting
							String sCalMonthDay = cal.getCellDateTextFromArray(j);
							if ( sCalMonthDay != null && !sCalMonthDay.equals("")) {				
									
								if (( sMonthDate != null && sMonthDate.equals(sCalMonthDay) ) && cellScheuleCnt[j] < cellSchMaxCnt  ) {	
										
									cal.setScheduleTextLayout( j, cellScheuleCnt[j] , cellSchMaxCnt );
									cal.setCellScheduleBackGround ( j , cellScheuleCnt[j] , md );
									cal.setCellScheduleText( j , cellScheuleCnt[j] , schedulename ) ;								
									cellScheuleCnt[j]++;
								
								//날짜별 최대 스케줄보다 많은 경우 추가정보보여줌(4번째부터)
								} else if (( sMonthDate != null && sMonthDate.equals(sCalMonthDay) ) && cellScheuleCnt[j] == cellSchMaxCnt  ) {
									
//									cal.setCellScheduleBackGround ( j , cellScheuleCnt[j] - 1 , md );
//									String moretext =  "(+)"  + schedulename ;
//									cal.setCellScheduleText( j , cellScheuleCnt[j] - 1 , moretext );
									String moretext =  "(+)"  + cal.getCellScheduleText( j , cellSchMaxCnt - 1 ) ;
									cal.setCellScheduleText( j , cellScheuleCnt[j] - 1 ,  moretext);
									cellScheuleCnt[j]++;
								}								
							}
						}
					}	
				}
				
				cur.moveToNext();
			}
			
			if ( cur != null ) cur.close();
			mDbUser.close();
		}
		
		
	}    
	/*
	 * 국가공휴일 조회(양력만 - 음력없음)  -> 다국적지원(국적별로 조회)
	 * holiday data(year, year+month+day : fromadate, todate)
	 * -> 휴일 중복처리 필요
	 */
    public void getHolidayFromDB( Calendar iCal, String fromdate, String todate ) {	

    	int dbcnt = 0;
    	
    	if (fromdate != null && todate != null) {
			
			mSpecialList = new ArrayList<SpecialDayInfo>();
			
			//시작일,종료일중 년도가 변경되는 경우 분기해서 1번더 처리..
			String startYear = "";
			String startMDay = "";
			String endYear 	= "";
			String endMDay 	= "";
			if (( fromdate != null && fromdate.length() == 8 ) &&  ( todate != null && todate.length() == 8 )) {
				startYear	= fromdate.substring( 0 , 4 );
				startMDay	= fromdate.substring( 4 );
				endYear 	= todate.substring( 0 , 4 );
				endMDay		= todate.substring( 4 );
				
				if ( startYear.equals(endYear))  {
					dbcnt = 1;
				} else {
					dbcnt = 2;
				}
				
			}
			
			Cursor cur = null;

			for(int loop = 0 ; loop < dbcnt ; loop++) {
				if ( dbcnt == 1 ) {
					//첫번째년도 					
					cur = mSpecialDbHelper.fetchMonthHoliDay(ComConstant.NATIONAL, startYear, startMDay, endMDay); 
				} else {
					//두번째년도
					if ( loop == 0 ) {
						cur = mSpecialDbHelper.fetchMonthHoliDay(ComConstant.NATIONAL, startYear, startMDay, "9999"); 
						
					} else if (  loop == 1 ) {
						cur = mSpecialDbHelper.fetchMonthHoliDay(ComConstant.NATIONAL, endYear, "0101", endMDay);
					}
				}
				int len = cur.getCount();
				for(int i = 0 ; i < len ; i++) {
					SpecialDayInfo spe = new SpecialDayInfo();	
					spe.setId(cur.getLong(cur.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_ID)));				
					spe.setName(cur.getString(cur.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_NAME)));
					spe.setHolidayYn(cur.getString(cur.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_HOLIDAYYN)));
					spe.setRepeatYn(cur.getString(cur.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_REPEATYN)));
					spe.setMonthDay(cur.getString(cur.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_MONTHDAY)));
					if ( spe.getRepeatYn() != null && spe.getRepeatYn().equals("Y")) {
						spe.setYear(startYear);
					} else {
						spe.setYear(cur.getString(cur.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_YEAR)));
					}
					if (spe.getMonthDay() != null && spe.getMonthDay().length() == 4 ) {
						spe.setMonth(spe.getMonthDay().substring(0,2));	
						spe.setDay(spe.getMonthDay().substring(2));	
					}
					
					mSpecialList.add(spe);
					
					cur.moveToNext();
				}
				
				if ( cur != null ) cur.close();
			}

			
		}	
	}
	/*
	 * 기념일조회 (1:양력 2:음력  ==> 합쳐서 return)
	 * -.양력 : from~to (기간) cursor
	 * -.음력 : date 조회기간만큼 loop 
	 */
    public void getSpecialDayFromDB( Calendar iCal, String fromdate, String todate ) {	
		
    	int len = 0;
    	int dbcnt = 0;
    	
        if ( fromdate == null || ( fromdate != null && fromdate.trim().length() != 8) 
        	|| todate == null || ( todate != null && todate.trim().length() != 8)) return;
       	
		mSpecialList = new ArrayList<SpecialDayInfo>();
		
		//시작일,종료일중 년도가 변경되는 경우 분기해서 1번더 처리..
		String startYear = "";
		String startMDay = "";
		String endYear 	= "";
		String endMDay 	= "";
		startYear	= fromdate.substring( 0 , 4 );
		startMDay	= fromdate.substring( 4 );
		endYear 	= todate.substring( 0 , 4 );
		endMDay		= todate.substring( 4 );
		
		if ( startYear.equals(endYear))  {
			dbcnt = 1;
		} else {
			dbcnt = 2;
		}
		
		Cursor cur = null;

		//1:양력
		for(int loop = 0 ; loop < dbcnt ; loop++) {
			String thisyear = "";
			if ( dbcnt == 1 ) {
				thisyear = startYear;
				cur = mSpecialDbHelper.fetchSpecialDayForCal( ComConstant.PUT_USER, thisyear, startMDay, endMDay );
			} else {				
				if ( loop == 0 ) {
					thisyear = startYear;
					cur = mSpecialDbHelper.fetchSpecialDayForCal( ComConstant.PUT_USER, thisyear, startMDay,  "9999" );
					
				} else if (  loop == 1 ) {
					thisyear = endYear;
					cur = mSpecialDbHelper.fetchSpecialDayForCal( ComConstant.PUT_USER, thisyear, "0101", endMDay );
				}
			}

			len = cur.getCount();
			
			for(int i = 0 ; i < len ; i++) {
				
				SpecialDayInfo spe = new SpecialDayInfo();
				spe.setId( cur.getLong(cur.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_ID)));
				spe.setName( ComUtil.setBlank(cur.getString(cur.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_NAME))));

				spe.setEvent(cur.getString(cur.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_EVENT)));
				
				String monthday = cur.getString(cur.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_MONTHDAY));
				
				if (monthday != null && monthday.trim().length() == 4 ) {
					spe.setSolardate( thisyear + monthday );
					spe.setMonth(monthday.trim().substring(0,2));	
					spe.setDay(monthday.trim().substring(2));	
				}
				
				mSpecialList.add(spe);

				cur.moveToNext();
			}
			
			if ( cur != null ) cur.close();
		}

		
		//2:음력 (기간만큼 loop) 조회는 음력으로 날짜setting은 양력으로
		//--> 급변경 : 등록된 기념일 모두 가져와서 양력환산 (임시조치)
		// -->윤달오류수정 해야함 
		// -->다음해로 넘어간 기념일 정보 조회 기능 추가
		// -->조회기간이 이전,다음월이 포함되어 전체적으로 변경
		//////////////////////////////////////////////////////////////////////////////////////////////
		//1.양력기간에 대한 음력날짜 모두 가져옴. (반복 인 경우 월일, 아닌경우 년월일사용)
		LunarDataDbAdaper mLunarDb = new LunarDataDbAdaper(this);
	    mLunarDb.open();
	    cur = mLunarDb.fetchSolarToLunarPeriod( fromdate, todate ); 
  
	    //2.기념일정보 모두가져오기 == 요건 좀 생각을...
		Cursor curspecial = mSpecialDbHelper.fetchAllSpecialDayLunar( ComConstant.PUT_USER );
		
		int lenspe = curspecial.getCount();
		
		long[] sId 			= new long[lenspe];
		String[] sName 		= new String[lenspe];
		String[] sleap 		= new String[lenspe];
		String[] syear 		= new String[lenspe];
		String[] slunarMD 	= new String[lenspe];
		String[] srepeat 	= new String[lenspe];
		String[] sEvent 	= new String[lenspe];
		
		for(int j = 0 ; j < lenspe ; j++) {
			long id 		= curspecial.getLong(curspecial.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_ID));
			String name 	= ComUtil.setBlank(curspecial.getString(curspecial.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_NAME)));
			String event 	= ComUtil.setBlank(curspecial.getString(curspecial.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_EVENT)));
			String sLeap  	= ComUtil.setBlank(curspecial.getString(curspecial.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_LEAP)));
			String repeat 	= ComUtil.setBlank(curspecial.getString(curspecial.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_REPEATYN)));
			String year 	= curspecial.getString(curspecial.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_YEAR));
			String lunarmd 	= curspecial.getString(curspecial.getColumnIndexOrThrow(SpecialDayDbAdaper.KEY_MONTHDAY));
			sId[j] 		= id;
			sName[j] 	= name;
			sleap[j] 	= sLeap;
			syear[j] 	= year;
			slunarMD[j] = lunarmd;
			srepeat[j]	= repeat;
			sEvent[j] 	= event;

			curspecial.moveToNext();
		}
		if ( curspecial != null ) curspecial.close();
		
	    //3. 음력날짜만큼 기념일 정보와 비교
		//윤달은 윤년이 아닌경우 평달로 처리
	    len = cur.getCount();
		for(int i = 0 ; i < len ; i++) {
			String leap  = cur.getString(cur.getColumnIndexOrThrow(LunarDataDbAdaper.KEY_LEAP));
			String lunar = cur.getString(cur.getColumnIndexOrThrow(LunarDataDbAdaper.KEY_LUNAR));
			String solar = cur.getString(cur.getColumnIndexOrThrow(LunarDataDbAdaper.KEY_SOLAR));

			
			for(int j = 0 ; j < lenspe ; j++) {
				boolean isWrite = false;
				String sLeap 	= sleap[j];
				String sRepeat 	= srepeat[j];  //반복여부
				String sYear	= syear[j];
				String sLunarMD	= slunarMD[j]; //month+day
				//반복, 비반복으로 분기
				if ( sRepeat != null && sRepeat.trim().equals("Y")) {	
					//반복인 경우 월일 비교
					if ( sLunarMD != null && sLunarMD.length() == 4 && sLunarMD.equals(lunar.substring( 4 ))) {							
						if ( sLeap.equals("1") && leap.equals(sLeap) ) {
							isWrite = true;
						} else if ( sLeap.equals("2") ) {
							isWrite = true;
						}
//						if ( leap.equals(sLeap) ) {
//							isWrite = true;
//						}
					}
				} else {
					//반복이 없는 경우 년월일 비교
					if ( sLunarMD != null && sLunarMD.length() == 4 && sLunarMD.equals(lunar.substring( 4 ))
							&& sYear.equals(lunar.substring( 0 , 4 ))) {
						if ( sLeap.equals("1") && leap.equals(sLeap) ) {
							isWrite = true;
						} else if ( sLeap.equals("2") ) {
							isWrite = true;
						}
//						if ( leap.equals(sLeap) ) {
//							isWrite = true;
//						}						
					}
				}


				if ( isWrite ) {
					SpecialDayInfo spe = new SpecialDayInfo();
					spe.setId(sId[j]);
					spe.setName(sName[j]);
					spe.setEvent(sEvent[j]);
					spe.setSolardate( solar );
					spe.setMonth(solar.substring(4,6));	
					spe.setDay(solar.substring(6));	
					mSpecialList.add(spe);	
				}
			}
			cur.moveToNext();
			
		}
    	
    	if ( cur != null ) cur.close();
    	mLunarDb.close();
	
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		
		SpecialDayInfo info = new SpecialDayInfo();
		info = mSpecialList.get(position);
		mId = info.getId();
		mSpecialdayName = info.getName();
		mMessage = ComUtil.makeScheduleMsg(this, info);
		
		registerForContextMenu(listView);
		openContextMenu(listView);
		//unregisterForContextMenu(listView);
		
	}
	/*
	 * Message Dialog Set
	 */
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);
		removeDialog(id);
		dialog = null;
		
	}		
    @Override
    protected Dialog onCreateDialog(int id) { 
    	MessageHandler msgHd = new MessageHandler(this);
    	Dialog di;
    	switch (id) {    

		case ComConstant.DIALOG_YES_NO_MESSAGE:
			di = msgHd.onCreateDialog(this, ComConstant.DIALOG_YES_NO_MESSAGE, 
					ComUtil.getStrResource(this, R.string.app_name), R.string.msg_exit, "");
			di.setOnCancelListener(new DialogInterface.OnCancelListener() {						
				@Override
					public void onCancel(DialogInterface dialog) { 
				        moveTaskToBack(true);
				        finish();
				        System.exit(0); 

					}
			});
					
			return di;
		}
    return null;
    }	
	/*
	 * 뒤로가기 버튼 클릭시 프로그램 종료
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		 switch(keyCode){
		     case KeyEvent.KEYCODE_BACK:
		    	 showDialog(ComConstant.DIALOG_YES_NO_MESSAGE);
					return true;
		     default:
		    	 break;
		 }
		return false;			

	}

	@Override
	public void onScrollStarted() {
		ImageView arrow = (ImageView)findViewById( R.id.img_arrow ) ;

		if ( mSlider.isOpened()) {
			arrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.sm_small_uparrow));
		} else {
			arrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.sm_small_downarrow));
		}		
		
	}

	//Comparator 를 만든다. 
    private final static Comparator<SpecialDayInfo> myComparator 
    		= new Comparator<SpecialDayInfo>() { 
    	
        private final Collator   collator = Collator.getInstance(); 
        
        @Override 
	    public int compare(SpecialDayInfo fromobj,SpecialDayInfo toobj) { 
        	return collator.compare(fromobj.getSolardate(), toobj.getSolardate()); 
	    } 
	 };

	@Override
	public void onScrollEnded() {
		// TODO Auto-generated method stub
		
	} 
	/*
	 * 음력일자 (음력1,15일, 양력 1일 display. 단 기존데이터 있는 경우 +1일 view)
	 */	
	private void setLunarDateView ( ) {

		String fromDay 	= cal.getStartDateInCalendar();
		String endDay 	= cal.getEndDateInCalendar();

		boolean showable = false;
		
		int calLen =  cal.getCellDateSize();		
		cal.removeLunarView(calLen);
		
        //음력일자 가져오기(기간)
        LunarDataDbAdaper mLunarDbHelper = new LunarDataDbAdaper(this);
        mLunarDbHelper.open();
        Cursor cur = mLunarDbHelper.fetchSolarToLunarPeriod( fromDay, endDay ); 
	    int len = cur.getCount();
		for(int i = 0 ; i < len ; i++) {
			String leap  = cur.getString(cur.getColumnIndexOrThrow(LunarDataDbAdaper.KEY_LEAP));
			String lunar = cur.getString(cur.getColumnIndexOrThrow(LunarDataDbAdaper.KEY_LUNAR));
			String solar = cur.getString(cur.getColumnIndexOrThrow(LunarDataDbAdaper.KEY_SOLAR));
			
			String sMonthDate 	=  solar.substring( 4 );			
			if ( lunar != null && lunar.trim().length() == 8 ) {
				//음력일자 setting
				int lunarmonth	= SmDateUtil.getDateToInt(lunar, ComConstant.GUBUN_MONTH);
				int lunarday	= SmDateUtil.getDateToInt(lunar, ComConstant.GUBUN_DAY);
				int solarday	= SmDateUtil.getDateToInt(solar, ComConstant.GUBUN_DAY);

					for( int j = 0 ; j < calLen ; j++ ) {
						String sCalMonthDay = cal.getCellDateTextFromArray(j);
						if ( sCalMonthDay != null && !sCalMonthDay.equals("")) {	
							if ( sMonthDate != null && sMonthDate.equals(sCalMonthDay)) {
								if ( cal.isExistHolidayText( j )) {
									if (  lunarday == 1 || lunarday == 15 || solarday == 1 ) {
										showable = true;
									}									
								} else {
									if ((  lunarday == 1 || lunarday == 15 || solarday == 1 ) || showable ){
										StringBuffer buffer = new StringBuffer();
										if ( leap != null && leap.equals("2")) {
											buffer.append(ComUtil.getStrResource(this, R.string.yun));
										}								
										buffer.append(Integer.toString(lunarmonth));
										buffer.append(".");
										buffer.append(Integer.toString(lunarday));	
										cal.setLunarView( j );
										cal.setCellLunarText( j , buffer.toString());
										showable = false;
									}
								}
								break;
						}						
					}
				}
			}

			cur.moveToNext();			
			
		}
    	
    	if ( cur != null ) cur.close();
		mLunarDbHelper.close();		

	}

	/*
	 * 할일조회 (종료일 또는 월반복+종료일)
	 */
	/*
    public void getTodoMemoFromDB( Calendar iCal, String fromdate, String todate ) {	
		
    	int len = 0;
    	int dbcnt = 0;

        if ( fromdate == null || ( fromdate != null && fromdate.trim().length() != 8) 
        	|| todate == null || ( todate != null && todate.trim().length() != 8)) return;
       	
        ArrayList<TodoMemoInfo> mTodoList = new ArrayList<TodoMemoInfo>();


    	TodoMemoDbAdaper mTodoDbHelper;
    	mTodoDbHelper = new TodoMemoDbAdaper(this);
    	mTodoDbHelper.open();		
		Cursor cur =  mTodoDbHelper.fetchTodolistForPeriod( fromdate, todate );
		mTodoDbHelper.close();
		
		len = cur.getCount();
		
		for(int i = 0 ; i < len ; i++) {
			
			TodoMemoInfo todo = new TodoMemoInfo();
			todo.setId( cur.getLong(cur.getColumnIndexOrThrow(TodoMemoDbAdaper.KEY_ID)));
			String repeat = ComUtil.setBlank(cur.getString(cur.getColumnIndexOrThrow(TodoMemoDbAdaper.KEY_REPEAT)));
			String finishterm = ComUtil.setBlank(cur.getString(cur.getColumnIndexOrThrow(TodoMemoDbAdaper.KEY_FINISHTERM)));

			//매월반복
			
			
			if ( repeat != null && repeat.trim().equals("Y") ) {
				String day = ComUtil.setBlank(cur.getString(cur.getColumnIndexOrThrow(TodoMemoDbAdaper.KEY_REPEATDATE)));
				SmDateUtil.getValideDateForMonth(yearmonth, day)
			}

			if ( finishterm != null && finishterm.trim().length() > 0 ) {
				
			}
			spe.setEvent(cur.getString(cur.getColumnIndexOrThrow(TodoMemoDbAdaper.KEY_EVENT)));
			
			String monthday = cur.getString(cur.getColumnIndexOrThrow(TodoMemoDbAdaper.KEY_MONTHDAY));
			
			if (monthday != null && monthday.trim().length() == 4 ) {
				spe.setSolardate( thisyear + monthday );
				spe.setMonth(monthday.trim().substring(0,2));	
				spe.setDay(monthday.trim().substring(2));	
			}
			
			mTodoList.add(spe);

			cur.moveToNext();
		}
		
		if ( cur != null ) cur.close();

	
	}
	*/
	
}