package com.waveapp.smcalendarlite.util;
 

import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.waveapp.smcalendarlite.R;

public class gsCalendarDlg extends Dialog 
{
	
	private final Context mCtx;
	private LinearLayout lv ;
	public int mYear;
	public int mMonth;
	public int mDay;
	
	TextView tvs[] ;
	ImageButton btns[] ;
	ImageButton mClose ;

	public gsCalendarDlg( Context context  )
	{
		super( context );
		this.mCtx 		= context;			
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {    	
        super.onCreate(savedInstanceState);
        
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.calendar_mini);
        
        lv 		= (LinearLayout)findViewById( R.id.calendar_lLayout ) ;
        mClose  = (ImageButton)findViewById( R.id.close ) ;
        
        tvs = new TextView[3] ;
        tvs[0] = (TextView)findViewById( R.id.year) ;
        tvs[1] = (TextView)findViewById( R.id.month ) ;
        //tvs[2] = (TextView)findViewById( R.id.day ) ;
        
        btns = new ImageButton[4] ;
        btns[0] = (ImageButton)findViewById( R.id.previousyear ) ;
        btns[1] = (ImageButton)findViewById( R.id.nextyear ) ;
        btns[2] = (ImageButton)findViewById( R.id.previousmonth ) ;
        btns[3] = (ImageButton)findViewById( R.id.nextmonth ) ;
        
        Calendar iCal = Calendar.getInstance( ) ;
		iCal.set(getYear(), getMonth() - 1, getDay());
		
        myCalendar cal = new myCalendar( mCtx, lv, iCal ) ;
        
        //cal.setSizePixel( 420, 385 ) ;
        cal.setCalendarSizeForPopup( 240, 240 ) ;
        cal.setControl( btns ) ;
        cal.setControlClose(mClose, this);
        cal.setViewTarget( tvs ) ;
        cal.setTextSizePerScale(9, 7, 0, 0);
        cal.initCalendar2( ) ;	
        setDateBar();
        
	}
	   //언어값에 따라 날짜bar 구성 변경
    protected void setDateBar() {
        
        TextView year_tv 	= (TextView)findViewById( R.id.year_tv ) ;
        TextView month_tv 	= (TextView)findViewById( R.id.month_tv ) ;   
        TextView gap 		= (TextView)findViewById( R.id.gap ) ; 
                
        //정순, 역순 체크
        if ( ComUtil.isLanguageFront( mCtx ))  {        	
        	ViewUtil.addViewLayoutParam(year_tv, RelativeLayout.RIGHT_OF, tvs[0]);
        	ViewUtil.addViewLayoutParam(gap, RelativeLayout.RIGHT_OF, year_tv);
        	ViewUtil.addViewLayoutParam(tvs[1], RelativeLayout.RIGHT_OF, gap);
        	ViewUtil.addViewLayoutParam(month_tv, RelativeLayout.RIGHT_OF, tvs[1]);
        	
        	year_tv.setText(ComUtil.getStrResource(mCtx, R.string.year));
        	month_tv.setText(ComUtil.getStrResource(mCtx, R.string.month));
        } else {
        	ViewUtil.addViewLayoutParam(month_tv, RelativeLayout.RIGHT_OF, tvs[1]);
        	ViewUtil.addViewLayoutParam(gap, RelativeLayout.RIGHT_OF, month_tv);
        	ViewUtil.addViewLayoutParam(tvs[0], RelativeLayout.RIGHT_OF, gap);
        	ViewUtil.addViewLayoutParam(year_tv, RelativeLayout.RIGHT_OF, tvs[0]);
        	
        	year_tv.setText("");
        	month_tv.setText(",");
        }   
            	
    }
	public void setDate(int yyyy, int MM, int dd) {
		  this.mYear  = yyyy;
		  this.mMonth = MM;
		  this.mDay   = dd;	
	}
	
	public int getYear() {
		  return mYear; 
	}
	public int getMonth() {
		  return mMonth; 
	}
	public int getDay() {
		  return mDay; 
	}	
	/// 달력 객체를 상속받아서 날짜를 눌렀을때의 이벤트를 override한다~
	public class myCalendar extends gsCalendar
	{

		public myCalendar(Context context, LinearLayout layout) 
		{
			super(context, layout);

		}
		public myCalendar(Context context, LinearLayout layout, Calendar iCal) 
		{
			super(context, layout, iCal);
			
		}
		/// 이녀석을 override했음
		@Override
		public void myClickEvent(int yyyy, int MM, int dd) 
		{
			super.myClickEvent(yyyy, MM, dd);
			setDate(yyyy, MM, dd);			
			gsCalendarDlg.this.cancel();
			
		}

		@Override
		protected void onResume() {
			super.onResume();
		}

		@Override
		protected void onRestoreInstanceState(Bundle savedInstanceState) {
			super.onRestoreInstanceState(savedInstanceState);			
		}

	}
}		
