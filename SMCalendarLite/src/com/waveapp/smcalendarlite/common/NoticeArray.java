package com.waveapp.smcalendarlite.common;

import android.content.Context;

public final class NoticeArray  {
	
	final Context mCtx;
	
	//private  strArr ;
    
    
    public NoticeArray(Context ctx) {
        this.mCtx = ctx;
    }

    /*
     * 공지 추가시 Max Key 주의
     */
	public String [][] getNoticeLiteKr()
	{
		//key, title, content       
		String[][] strArr = new String[][] { 

        		{	
        			"001"
        		,	"SM Calendar Lite에 오신걸 환영합니다"
        		,	"SM Calendar Lite를 구매해주신 여러분께 감사드립니다.\n\n" +
    				"사용자별로 스케줄을 관리할 수 있어 자녀,배우자 등 다른 사용자들의 스케줄을 종합적으로 관리할 수 있습니다.\n\n" +
    				"그 외 일상 생활에 필요한 음력달력,기념일,\n" +
    				"할일(To-do)관리 기능을 제공하고 있으며\n" +
    				"전체,월간,주간,일간 스케줄 조회화면 및 일정을 시간대별로 확인할 수 있는 화면이 구성되어 있습니다.\n\n" +
    				"그외 기존 일정을 가져올 수 있는 기능이 구현되어 있으니 처음 설치하신 분들은 이점 참조하시여 사용해주시기 바랍니다.\n\n" +
    				"Lite버전은 구매 전 어플을 미리 사용해 보고 필요한 어플인지,원하는 기능이 구현 되었는지" +
        			"확인하시기 위해 제공하는 버전입니다.\n\n\n" +
            		"Lite 버전의 차이점\n\n"+
            		"1) 간지(음력)달력 : 한달만 제공\n" +
            		"2) 광고 : 정식버전에서는 삭제\n" +
            		"3) 일정/기념일공유 : 정식버전에서 사용가능\n\n\n"  +
        			"광고화면이 불편하시거나 제공된 모든 기능을 사용하고 싶으신 분들은\n" +
        			"정식버전을 구매해주시면 감사하겠습니다.\n" +
        			"정식버전의 경우 유료버전 임을 유념해주시기 바랍니다.\n" +
        			"어플 사용에 불편한 점은 개발자에게 문의해 주십시요.\n" +
        			"고객분들이 원하시는 기능을 제공하는 어플이 되도록 노력하겠습니다.\n\n" +   

        			"감사합니다."
        		,   "20111101"
        		} 
        		
          	   ,{
	   				"002"
	       		,	"주요기능 소개"
	       		,	"주요 기능 및 특징 \n\n" +
	       			"● 다중사용자 등록가능 \n" +
	       			"    여러 사용자를 관리할 수 있으며 사용자별로 일정정보를 관리할 수 있는 기능을 제공합니다.\n" +
	       			"사용자별로 색상을 지정할 수 있으며 일정조회시 해당색상이 제공됩니다.\n\n" +
	       			"● 일정관리 \n" +
	       			"    전체,월간(달력),주간,일간 스케줄 조회화면 및  " +
	       			"일정을 시간대별로 확인할 수 있는 화면이 구성되어 있습니다.\n\n" +
	       			"● 기념일관리\n" +
	       			"    매년 반복,일회성 기념일을 양력,음력 모두 관리할 수 있으며  년단위로 조회 가능합니다.음력지원합니다.\n\n" +
	       			"● 할일관리\n" +
	       			"    할일을 간단한 메모형태로 관리하실 수 있습니다.\n\n" +
	       			"● 국공일제공\n" +
	       			"    현재 대한민국 국경일만 제공 되며 향후 추가될 예정입니다.\n\n" +
	       			"● 음력지원\n" +
	       			"    1920년~2020년까지 제공되고 있으며 고객요청시 추가될 예정입니다.\n\n" +
	       			"● 음력달력(60갑자)제공\n" +
	       			"    띠,60갑자정보를 제공하는 별도의 달력이 구현되어있습니다.\n" +
	       			"상단 정보메뉴에서 확인하실 수 있으며 유사한 기능성달력이 추가될 예정입니다.\n\n" +	       			
	       			"● 알람제공\n" +
	       			"    일정별 알람서비스를 제공하고 있으며 " +
	       			"알람모드(소리,진동)는 설정에서 변경하실 수 있습니다.\n\n" +
	       			"● 공유기능\n" +
	       			"    기념일,일정정보를 공유하실 수 있습니다.\n" +
	       			"(현재 문자메시지, 카카오톡, 에버노트 지원).\n\n" +
	       			"● 설정변경\n" +
	       			"    알람모드(소리,진동)변경,\n" +
	       			"다국어지원(한국어,영어),\n" +
	       			"일정,기념일,할일 백업/복구기능(SD카드로),\n" +
	       			"다른일정가져오기(단말기내 일정)\n" +
	       			"단,다른일정 내보내기는 해당어플에서 매주 반복일정이 적용되지 않아 구현되지 않았습니다.\n"
	       		,   "20111101"        			
        	   } 
          	   ,{
	   				"004"
	       		,	"v1.4.3 업데이트 안내(달력전체보기,오늘일정 위젯추가)"
	       		,	"1) 월 단위로 보여지는 달력 화면이 이전,다음달 까지 볼 수 있게 변경되었습니다.(메인달력만 해당)\n" +
	       			"월 단위로 보고 싶으시면 설정에서 변경하실 수 있습니다.\n" +
	       			"● 설정 > 화면제어 > 전체달력 체크\n\n" +
	       			"2) 오늘일정 위젯화면이 추가되었습니다.(Size 4x1) \n" +
	       			"단, 안드로이드2.2이상만 가능합니다.(하위 버전은 지원되지 않습니다)\n\n" +
	       			" 주간일정, 달력은 향후 추가될 예정이며 위젯설정 변경 기능은 다음 버전에 적용될 예정입니다.\n\n" +
	       			" 위젯 사용시 오류나 화면이상이 있으시면 개발자에게 연락주시면 감사하겠습니다.\n\n"
	       		,   "20111210"        			
          	   }   
          	   ,{
	   				"005"
	       		,	"v1.4.6 업데이트 안내(기념일 추가/언어추가지원/고해상도 단말기 지원)"
	       		,	"1) 다국어 지원 1차반영\n" +
       				" -.기념일(공유일기준):중국,일본,미국 추가\n" +
       				" -.언어지원 : 중국(번체),일본어 추가.\n" +
	       			"● 언어변경   : 설정 > 언어선택\n" +
	       			"● 기념일변경 : 설정 > 국가공휴일\n\n" +
	       			"2) 1200 * 780 단말기에서 발생한 화면 밀림현상 보정하여 반영하였습니다." +
	       			"화면 밀림이나 이상이 계속 발생할 경우 개발자에게 문의해주시면 감사하겠습니다\n" 
	       		,   "20111210"        			
          	   }           	   
          	   ,{
	   				"006"
	       		,	"v1.5.0 업데이트(기념일,할일,일정알람추가 및 매월반복일정 기능추가 등 )"
	       		,	"업데이트가 늦은점 우선 사과드립니다.점차적으로 기능을 강화할 예정입니다.\n" +
	       			"특히 디자인과 위젯은 1.5버전이 안정된 이후로 진행할 예정이니 참조해 주시기 바랍니다.\n\n" +
	       			"** 주요 업데이트 내용 **\n" +
	       			"1) 알람기능강화\n" +
	       			" -.기념일,할일 알람 추가\n" +
	       			"   알람시간은 설정에 등록된 시간을 기준으로 처리됩니다.설정>기본알람시간에서 수정가능합니다.\n" +
	       			"   최초 기본알람시간: 오전8:00 \n"+
	       			" -.일정알람 추가 :  종료시간기준 알람등록이 가능합니다.\n\n" +
	       			"2) 매월반복일정 등록가능\n" +
	       			" -.특정일(말일포함) 월반복일정 등록이 가능합니다.\n\n" +
	       			"3) 달력화면 변경\n" +
	       			" -.음력항목추가(매월양력1일,음력1,15일)\n " +
	       			" -.기념일을 아이콘->리스트로 보이게 수정.\n\n" +
	       			"4) 기념일 그룹항목 추가\n" +
	       			" -.기념일 그룹항목 등록 및 전체기념일(홈메뉴) 화면 추가.\n" +
	       			"    그룹별 정보조회 기능은 다음버전에 반영예정\n\n" +
	       			"5) 기타\n" +
	       			" -.공지화면 메뉴로 이동.\n" +
	       			" -.화면변경 요청 및 알려진 버그 수정\n\n"
	       		,   "20120507"        			
       	   }        
        	};		
        
		return strArr;
	}
	public String [][] getNoticeFormallyKr()
	{
		//key, title, content       
		String[][] strArr = new String[][] { 

        		{	
        			"001"
        		,	"SM Calendar 오신걸 환영합니다"
        		,	"SM Calendar를 구매해 주신 여러분께 감사드립니다.\n\n" +
        			"사용자별로 스케줄을 관리할 수 있어 자녀,배우자 등 다른 사용자들의 스케줄을 종합적으로 관리할 수 있습니다.\n\n" +
        			"그 외 일상 생활에 필요한 음력달력,기념일,\n" +
        			"할일(To-do)관리 기능을 제공하고 있으며\n" +
        			"전체,월간,주간,일간 스케줄 조회화면 및 일정을 시간대별로 확인할 수 있는 화면이 구성되어 있습니다.\n\n" +
        			"그외 기존 일정을 가져올 수 있는 기능이 구현되어 있으니 처음 설치하신 분들은 이점 참조하시여 사용해주시기 바랍니다.\n\n" +
        			"어플 사용에 불편한 점은 개발자에게 문의해 주십시요.\n" +
        			"고객분들이 원하시는 기능을 제공하는 어플이 되도록 노력하겠습니다.\n\n" +   
        			"감사합니다."
        		,   "20111101"
     		
        		}  	
         	   ,{
	   				"002"
	       		,	"주요기능 소개"
	       		,	"주요 기능 및 특징 \n\n" +
	       			"● 다중사용자 등록가능 \n" +
	       			"    여러 사용자를 관리할 수 있으며 사용자별로 일정정보를 관리할 수 있는 기능을 제공합니다.\n" +
	       			"사용자별로 색상을 지정할 수 있으며 일정조회시 해당색상이 제공됩니다.\n\n" +
	       			"● 일정관리 \n" +
	       			"    전체,월간(달력),주간,일간 스케줄 조회화면 및  " +
	       			"일정을 시간대별로 확인할 수 있는 화면이 구성되어 있습니다.\n\n" +
	       			"● 기념일관리\n" +
	       			"    매년 반복,일회성 기념일을 양력,음력 모두 관리할 수 있으며  년단위로 조회 가능합니다.음력지원합니다.\n\n" +
	       			"● 할일관리\n" +
	       			"    할일을 간단한 메모형태로 관리하실 수 있습니다.\n\n" +
	       			"● 국공일제공\n" +
	       			"    현재 대한민국 국경일만 제공 되며 향후 추가될 예정입니다.\n\n" +
	       			"● 음력지원\n" +
	       			"    1920년~2020년까지 제공되고 있으며 고객요청시 추가될 예정입니다.\n\n" +
	       			"● 음력달력(60갑자)제공\n" +
	       			"    띠,60갑자정보를 제공하는 별도의 달력이 구현되어있습니다.\n" +
	       			"상단 정보메뉴에서 확인하실 수 있으며 유사한 기능성달력이 추가될 예정입니다.\n\n" +	       			
	       			"● 알람제공\n" +
	       			"    일정별 알람서비스를 제공하고 있으며 " +
	       			"알람모드(소리,진동)는 설정에서 변경하실 수 있습니다.\n\n" +
	       			"● 공유기능\n" +
	       			"    기념일,일정정보를 공유하실 수 있습니다.\n" +
	       			"(현재 문자메시지, 카카오톡, 에버노트 지원).\n\n" +
	       			"● 설정변경\n" +
	       			"    알람모드(소리,진동)변경,\n" +
	       			"다국어지원(한국어,영어),\n" +
	       			"일정,기념일,할일 백업/복구기능(SD카드로),\n" +
	       			"다른일정가져오기(단말기내 일정)\n" +
	       			"단,다른일정 내보내기는 해당어플에서 매주 반복일정이 적용되지 않아 구현되지 않았습니다.\n"
	       		,   "20111101"        			
         	   }    
         	   ,{
	   				"003"
	       		,	"일정 가져오기/내보내기 방법"
	       		,	"타 일정어플에 등록된 일정을 SMCalendar로 가져올 수 있는 서비스입니다.\n\n" +
	       			"현재 일정 공유가 가능한 어플은 단말기내에 기본으로 제공되는 일정App(내달력)에 한정되어 있으나 향후 고객요청에 따라 추가 할 예정입니다.\n\n" +
	       			"구글캘린더를 사용하시는 경우 단말기 일정App에서 가져온뒤 SM Caledar로 복사하는 방식을 이용하시면 됩니다. \n" +
	       			"구글캘린더 연동은 개발 중이며 빠른 시일내에 서비스를 제공할 예정이오니 고객분들의 양해부탁드립니다.\n\n" +
	       			"기존 일정 어플의 경우 동기화(변경사항을 즉시 동적으로 반영) 기능을 이용하지만 저희 어플의 경우 일정을 복사해오는 방식을 도입하였습니다.\n" +
	       			"타 어플에서 발생한 오류로 일정이 모두 삭제되거나 변동이 발생하는 것을 미연에 방지하고자 고객이 선택한 일정에 한해 처리할 수 있도록 화면이 구성되어 있으며 " +
	       			"기능개선요청을 해주시면 참조하여  반영하겠습니다.\n\n" +
	       			"현재 제약사항으로는 SM Calendar에서 제공되는 반복일정은  매주 반복일정에 한정되어 있어\n" +
	       			"월,격주,특정일반복 일정은 가져올수 없으니 양해 부탁드립니다.\n\n" +
	       			"● 공유가능 어플 \n" +
	       			"    단말기내 일정App\n(구글캘린더 사용자의 경우 단말기내 일정App으로 가져오신뒤 처리하시면 됩니다)\n\n" +
	       			"● 공유방법 \n" +
	       			"    가져오기\n" +
	       			"홈메뉴->설정->일정가져오기->내달력->일정체크->가져오기 버튼\n\n" +
	       			"● 제약사항 \n" +
	       			"    반복일정의 경우 월,격주,특정일 반복은 가져올 수 없습니다.\n\n" 
	       			
	       		,   "20111101"        			
        	   }
          	   ,{
	   				"004"
	       		,	"v1.4.3 업데이트 안내(달력전체보기,오늘일정 위젯추가)"
	       		,	"1) 월 단위로 보여지는 달력 화면이 이전,다음달 까지 볼 수 있게 변경되었습니다.(메인달력만 해당)\n" +
	       			"월 단위로 보고 싶으시면 설정에서 변경하실 수 있습니다.\n" +
	       			"● 설정 > 화면제어 > 전체달력 체크\n\n" +
	       			"2) 오늘일정 위젯화면이 추가되었습니다.(Size 4x1) \n" +
	       			"단, 안드로이드2.2이상만 가능합니다.(하위 버전은 지원되지 않습니다)\n\n" +
	       			" 주간일정, 달력은 향후 추가될 예정이며 위젯설정 변경 기능은 다음 버전에 적용될 예정입니다.\n" +
	       			" 위젯 사용시 오류나 화면이상이 있으시면 개발자에게 연락주시면 감사하겠습니다.\n\n"
	       		,   "20111210"        		
         	   } 
          	   ,{
	   				"005"
	       		,	"v1.4.6 업데이트 안내(기념일 추가/언어추가지원/고해상도 단말기 지원)"
	       		,	"1) 다국어 지원 1차반영\n" +
	       			" -.기념일(공유일기준):중국,일본,미국 추가\n" +
	       			" -.언어지원 : 중국(번체),일본어 추가.\n" +
	       			"● 언어변경   : 설정 > 언어선택\n" +
	       			"● 기념일변경 : 설정 > 국가공휴일\n\n" +
	       			"2) 1200 * 780 단말기에서 발생한 화면 밀림현상 보정하여 반영하였습니다." +
	       			"화면 밀림이나 이상이 계속 발생할 경우 개발자에게 문의해주시면 감사하겠습니다\n" 
	       		,   "20111210"        			
         	   }    
          	   ,{
	   				"006"
	       		,	"v1.5.0 업데이트(기념일,할일,일정알람추가 및 매월반복일정 기능추가 등 )"
	       		,	"업데이트가 늦은점 우선 사과드립니다.점차적으로 기능을 강화할 예정입니다.\n" +
	       			"특히 디자인과 위젯은 1.5버전이 안정된 이후로 진행할 예정이니 참조해 주시기 바랍니다.\n\n" +
	       			"** 주요 업데이트 내용 **\n" +
	       			"1) 알람기능강화\n" +
	       			" -.기념일,할일 알람 추가\n" +
	       			"   알람시간은 설정에 등록된 시간을 기준으로 처리됩니다.설정>기본알람시간에서 수정가능합니다.\n" +
	       			"   최초 기본알람시간: 오전8:00 \n"+
	       			" -.일정알람 추가 :  종료시간기준 알람등록이 가능합니다.\n\n" +
	       			"2) 매월반복일정 등록가능\n" +
	       			" -.특정일(말일포함) 월반복일정 등록이 가능합니다.\n\n" +
	       			"3) 달력화면 변경\n" +
	       			" -.음력항목추가(매월양력1일,음력1,15일)\n " +
	       			" -.기념일을 아이콘->리스트로 보이게 수정.\n\n" +
	       			"4) 기념일 그룹항목 추가\n" +
	       			" -.기념일 그룹항목 등록 및 전체기념일(홈메뉴) 화면 추가.\n" +
	       			"    그룹별 정보조회 기능은 다음버전에 반영예정\n\n" +
	       			"5) 기타\n" +
	       			" -.공지화면 메뉴로 이동.\n" +
	       			" -.화면변경 요청 및 알려진 버그 수정\n\n"
	       		,   "20120507"        			
        	   }                	   
        	};		
        
		return strArr;
	}
	/*
	 * 영문 공지사항
	 */
	public String [][] getNoticeLiteEn()
	{
		//key, title, content       
		String[][] strArr = new String[][] { 

        		{	
        			"001"
        		,	"Welcome to SM Calendar Lite"
        		,	"SM Calendar Lite and thanks you for your purchase.\n\n" +
    				"Users can manage the schedule for each child, spouse, or other users' schedules can be managed comprehensively.\n\n" +
    				"Resources needed for daily life lunar calendar, anniversary, " +
    				"to-do (To-do) management capabilities, and a full, monthly, weekly " +
    				"and daily schedule views by hour of the schedule to see the view and the view that is configured.\n\n" +
    				"Addition, the ability to import existing schedule is implemented, so you want to see the first installation advantages over those who may use it.\n\n" +
    				"Lite versions of all applications in advance of purchase is reported to the necessary applications, " +
        			"the desired functionality has been implemented to check is the version that provides for.\n\n\n" +
            		"Lite version of the difference\n\n"+
            		"1) Lunar calendar : Provide a month.\n" +
            		"2) Advertising Banner : In the full version deleted\n" +
            		"3) Sharing : Full version is available\n\n\n"  +
        			"Ad banner for all of the functionality provided by other passengers " +
        			"who wish to use for purchasing the full version would be appreciated.\n" +
        			"For the full version please note that the paid version.\n" +
        			"Application developers using the inconvenience, please contact.\n" +
        			"Customers to provide the desired functionality so that applications will work.\n\n" +   

        			"Thank you."
        		,   "20111101"
        		} 
        		
          	   ,{
	   				"002"
	       		,	"About the main features"
	       		,	"Main Features and Benefits \n\n" +
	       			"● Multiple users can be registered \n" +
	       			"    You can manage multiple users on a per-user basis with the ability to manage the schedule information is available.\n" +
	       			"Per user can specify the color, that color is constant lookup.\n\n" +
	       			"● Calendar \n" +
	       			"    Whole, the monthly (calendar), weekly, daily schedule and the schedule by time viewed the screen to see a screen that is composed of  " +
	       			"Over time to determine a schedule that is composed of the view.\n\n" +
	       			"● Managing Anniversary\n" +
	       			"    Repeated every year, one-time lift the anniversary, to manage both the lunar and can be viewed as a one-year: the lunar calendar support: to support the lunar.\n\n" +
	       			"● Managing to do\n" +
	       			"    What to do can be administered in the form of short notes.\n\n" +
	       			"● National Holidays\n" +
	       			"    National holiday, Republic of Korea is currently provided ten thousand will be added in future.\n\n" +
	       			"● Support lunar\n" +
	       			"    1920-2020 is available and will be added upon request.\n\n" +
	       			"● The lunar calendar provides\n" +
	       			"    Bands, 60 gabza separate calendars that provide information are implemented.\n" +
	       			"Information can be found at the top of the menu, the calendar will be added similar functionality.\n\n" +	       			
	       			"● Provide an alarm\n" +
	       			"    Schedule alarm services and alarm mode (sound, vibration) can change the settings.\n\n" +
	       			"● Sharing\n" +
	       			"    Anniversary, the schedule information you can share.\n" +
	       			"(The current text message, kakaotok, EverNote Support).\n\n" +
	       			"● Change settings\n" +
	       			"    Alarm mode (sound, vibration) change,\n" +
	       			"Multi-language support (English, Korean),\n" +
	       			"Schedule, anniversary, do the backup / restore capability (SD card),\n" +
	       			"Get a different schedule (My cell phone schedule)\n" +
	       			"However, exporting the other calendar applications do not apply in a weekly repeat schedule has not been implemented.\n"
	       		,   "20111101"        			
        	   } 	   
          	   ,{
	   				"004"
	       		,	"v1.4.3 Update Information (See the entire calendar, today schedule added the widget)."
	       		,	"1) The calendar screen is displayed by month earlier, " +
	       			"has been made until next month to see (the main calendar only)\n" +
	       			"If you want to see on a monthly basis you can change settings.\n" +
	       			"● Settings> Screen Control> Check the full calendar\n\n" +
	       			"2) Widgets have been added to the today schedule.(Size 4x1)\n" +
	       			"However, the Android 2.2 or later is available (sub-version is not supported)\n\n" +	       			
	       			" Weekly schedule, the calendar will be added, " +
	       			"and the ability to change widget settings to be applied to the next version is expected to.\n" +
	       			" If you have errors over the screen when using the widget, contact the developer would be appreciated.\n\n"
	       		,   "20111210"        			
         	   }  
          	   ,{
	   				"005"
	       		,	"v1.4.6 Update Guide (Anniversary Add/Additional language support/high-resolution device support)"
	       		,	"1) Reflect the primary Multiple language support\n" +
	       			" -.Anniversary(holiday basis): China, Japan and the United States added\n" +
	       			" -.Language Support: Chinese (Traditional), Japanese, add.\n" +
	       			"● Change language: Preference> Language Selection\n" +
	       			"● Anniversary Change: Preference> National Holidays\n\n" +
	       			"2)Occurred in 1200 * 780 screen terminal was clipped corrected to reflect." +
	       			"If you continue to experience the jungle and over the screen developers would appreciate it if you contact us.\n" 
	       		,   "20111210"        			
        	   } 
          	   ,{
	   				"006"
	       		,	"Update v1.5.0 (Anniversary,TO-DO, schedule, alarm, such as adding additional features and monthly recurring events)"
	       		,	"Apologize for the late update, point first. Function is expected to strengthen gradually.\n" +
	       			"In particular, the design and widget version 1.5 is scheduled to proceed to a stable future.\n\n" +
	       			"** Major Updates **\n" +
	       			"1) Enhanced alarm\n" +
	       			" -.Anniversary, TO-DO added alarm\n" +
	       			"   Registered on the set alarm time will be processed based on the time. Preference > Default alarm time will be fixed in..\n" +
	       			"   The first basic alarm time: 8:00 AM\n"+
	       			" -.Add schedule alarm : End time alarms based on must be registered.\n\n" +
	       			"2) Can register monthly recurring schedule\n" +
	       			" -.Specific day (including the last day) Monthly Recurring Event registration is available.\n\n" +
	       			"3) Change the calendar view\n" +
	       			" -.Add the lunar calendar items (lift one day a month, a lunar 1,15)\n " +
	       			" -.Anniversary of the icon -> list of modifications appear to be.\n\n" +
	       			"4) Adding group entries anniversary\n" +
	       			" -.Registration and Full Day anniversary group entry (home menu) added view.\n" +
	       			"    Group information lookup function will be reflected in the next version.\n\n" +
	       			"5) Other\n" +
	       			" -.Go to the Bulletin Home-menu.\n" +
	       			" -.View, known as change requests and bug fixes.\n\n"
	       		,   "20120507"        			
          	   }                 	   
        	};		
        
		return strArr;
	}	
	public String [][] getNoticeFormallyEn()
	{
		//key, title, content       
		String[][] strArr = new String[][] { 

        		{	
        			"001"
        		,	"Welcome to SM Calendar"
        		,	"SM Calendar and thanks you for your purchase.\n\n" +
    				"Users can manage the schedule for each child, spouse, or other users' schedules can be managed comprehensively.\n\n" +
    				"Resources needed for daily life lunar calendar, anniversary, " +
    				"to-do (To-do) management capabilities, and a full, monthly, weekly " +
    				"and daily schedule views by hour of the schedule to see the view and the view that is configured.\n\n" +
    				"Addition, the ability to import existing schedule is implemented, so you want to see the first installation advantages over those who may use it.\n\n" +
        			"Application developers using the inconvenience, please contact.\n" +
        			"Customers to provide the desired functionality so that applications will work.\n\n" +         			

        			"Thank you."
        		,   "20111101"
     		
        		}  	
         	   ,{
	   				"002"
   	       		,	"About the main features"
	       		,	"Main Features and Benefits \n\n" +
	       			"● Multiple users can be registered \n" +
	       			"    You can manage multiple users on a per-user basis with the ability to manage the schedule information is available.\n" +
	       			"Per user can specify the color, that color is constant lookup.\n\n" +
	       			"● Calendar \n" +
	       			"    Whole, the monthly (calendar), weekly, daily schedule and the schedule by time viewed the screen to see a screen that is composed of  " +
	       			"Over time to determine a schedule that is composed of the view.\n\n" +
	       			"● Managing Anniversary\n" +
	       			"    Repeated every year, one-time lift the anniversary, to manage both the lunar and can be viewed as a one-year: the lunar calendar support: to support the lunar.\n\n" +
	       			"● Managing to do\n" +
	       			"    What to do can be administered in the form of short notes.\n\n" +
	       			"● National Holidays\n" +
	       			"    National holiday, Republic of Korea is currently provided ten thousand will be added in future.\n\n" +
	       			"● Support lunar\n" +
	       			"    1920-2020 is available and will be added upon request.\n\n" +
	       			"● The lunar calendar provides\n" +
	       			"    Bands, 60 gabza separate calendars that provide information are implemented.\n" +
	       			"Information can be found at the top of the menu, the calendar will be added similar functionality.\n\n" +	       			
	       			"● Provide an alarm\n" +
	       			"    Schedule alarm services and alarm mode (sound, vibration) can change the settings.\n\n" +
	       			
	       			"● Sharing\n" +
	       			"    Anniversary, the schedule information you can share.\n" +
	       			"(The current text message, kakaotok, EverNote Support).\n\n" +
	       			"● Change settings\n" +
	       			"    Alarm mode (sound, vibration) change,\n" +
	       			"Multi-language support (English, Korean),\n" +
	       			"Schedule, anniversary, do the backup / restore capability (SD card),\n" +
	       			"Get a different schedule (My cell phone schedule)\n" +
	       			"However, exporting the other calendar applications do not apply in a weekly repeat schedule has not been implemented.\n"
	       		,   "20111101"        			
         	   }    
         	   ,{
	   				"003"
   	       		,	"Calendar import / export methods"
	       		,	"Registered in other calendar applications on the schedule is a service that can be imported into SM Calendar.\n\n" +
	       			"You can share your calendar with the built-in applications within the terminal schedule App (My Calendar), " +
	       			"but limited to the future is expected to be added according to customer requests.\n\n" +
	       			"If you're using Google Calendar Calendar App from a terminal to copy SM Caledar the way back is via.\n" +
	       			"Google Calendar integration is under development and scheduled to provide services as soon as possible Please note that guests' sludge.\n\n" +
	       			"If the synchronization of the existing calendar application (dynamically reflect the changes immediately) feature, " +
	       			"but we have to copy applications coming in the way the schedule was introduced.\n" +
	       			"Errors that occur in other applications or to delete all the schedule changes that will occur " +
	       			"to prevent the customer to be processed only for the selected schedule consists of screen " +
	       			"If you let a request to see the improvements will be reflected.\n\n" +
	       			"Limitations include the current repeats SMCalendar provided by a limited schedule recurring events per week\n" +
	       			"Month, bi-weekly, repeat schedule can bring a certain day eopeuni Thank you for your understanding.\n\n" +
	       			"● Applications can be shared \n" +
	       			"    Cell phone My Calendar App\n" +
	       			"(If your cell phone calendar with Google Calendar App I get to be copied into coming back.)\n\n" +
	       			"● How to share \n" +
	       			"    Import\n" +
	       			"Home Menu -> Settings -> Import Calendar -> My Calendars -> Calendar check -> Import button\n\n" +
	       			"● Restrictions \n" +
	       			"    For recurring events month, biweekly, repeat one specific day can not be imported.\n\n" 
	       			
	       		,   "20111101"        			
        	   }  
          	   ,{
	   				"004"
	       		,	"v1.4.3 Update Information (See the entire calendar, today schedule added the widget)."
	       		,	"1) The calendar screen is displayed by month earlier, " +
	       			"has been made until next month to see (the main calendar only)\n" +
	       			"If you want to see on a monthly basis you can change settings.\n" +
	       			"● Settings> Screen Control> Check the full calendar\n\n" +
	       			"2) Widgets have been added to the today schedule.(Size 4x1)\n" +
	       			"However, the Android 2.2 or later is available (sub-version is not supported)\n\n" +	       			
	       			" Weekly schedule, the calendar will be added, " +
	       			"and the ability to change widget settings to be applied to the next version is expected to.\n" +
	       			" If you have errors over the screen when using the widget, contact the developer would be appreciated.\n\n"
	       		,   "20111210"        			
        	   } 
          	   ,{
	   				"005"
	       		,	"v1.4.6 Update Guide (Anniversary Add/Additional language support/high-resolution device support)"
	       		,	"1) Reflect the primary Multiple language support\n" +
	       			" -.Anniversary(holiday basis): China, Japan and the United States added\n" +
	       			" -.Language Support: Chinese (Traditional), Japanese, add.\n" +
	       			"● Change language: Preference> Language Selection\n" +
	       			"● Anniversary Change: Preference> National Holidays\n\n" +
	       			"2)Occurred in 1200 * 780 screen terminal was clipped corrected to reflect." +
	       			"If you continue to experience the jungle and over the screen developers would appreciate it if you contact us.\n" 
	       		,   "20111210"        			
          	   }    
          	   ,{
	   				"006"
	       		,	"Update v1.5.0 (Anniversary,TO-DO, schedule, alarm, such as adding additional features and monthly recurring events)"
	       		,	"Apologize for the late update, point first. Function is expected to strengthen gradually.\n" +
	       			"In particular, the design and widget version 1.5 is scheduled to proceed to a stable future.\n\n" +
	       			"** Major Updates **\n" +
	       			"1) Enhanced alarm\n" +
	       			" -.Anniversary, TO-DO added alarm\n" +
	       			"   Registered on the set alarm time will be processed based on the time. Preference > Default alarm time will be fixed in..\n" +
	       			"   The first basic alarm time: 8:00 AM\n"+
	       			" -.Add schedule alarm : End time alarms based on must be registered.\n\n" +
	       			"2) Can register monthly recurring schedule\n" +
	       			" -.Specific day (including the last day) Monthly Recurring Event registration is available.\n\n" +
	       			"3) Change the calendar view\n" +
	       			" -.Add the lunar calendar items (lift one day a month, a lunar 1,15)\n " +
	       			" -.Anniversary of the icon -> list of modifications appear to be.\n\n" +
	       			"4) Adding group entries anniversary\n" +
	       			" -.Registration and Full Day anniversary group entry (home menu) added view.\n" +
	       			"    Group information lookup function will be reflected in the next version.\n\n" +
	       			"5) Other\n" +
	       			" -.Go to the Bulletin Home-menu.\n" +
	       			" -.View, known as change requests and bug fixes.\n\n"
	       		,   "20120507"        			
         	   }           	   
        	};		
        
		return strArr;
	}
	public String [][] getNoticeLiteJa()
	{
		//key, title, content       
		String[][] strArr = new String[][] { 

        		{	
        			"001"
        		,	"SM Calendar Liteへようこそ!"
        		,	"SM Calendar Liteを購入してくださった皆さんに感謝します.\n\n" +
    				"ユーザーごとのスケジュールを管理することができ、子供、配偶者など、他のユーザーのスケジュールを総合的に管理することができます.\n\n" +
    				"その他の日常生活に必要な旧暦カレンダー、記念日、TODOの管理機能を提供しており、 \n" +
    				"全体、月間、週間、日間のスケジュール照会画面、およびスケジュールを時間帯別に確認できる画面が構成されています.\n\n" +
    				"その他、既存のスケジュールをインポートする機能が実装されていますので最初にインストールされた方は、利点を参照したいてご使用ください.\n\n" +
        			"Lite版は、購入前にアプリをあらかじめ使って見て、必要なアプリなのか、必要な機能が実装されていることを確認して提供されるバージョンです。\n\n\n" +
            		"Liteバージョンの相違点\n\n"+
            		"1) 旧暦カレンダー：ハンダルマン提供\n" +
            		"2) 広告：製品版では削除\n" +
            		"3) スケジュール/記念日共有：フルバージョンで使用可能\n\n\n"  +
        			"広告画面が不便か、提供されるすべての機能を使用して、\n" +
        			"方はフルバージョンを購入していただければ幸いです.\n" +
        			"フルバージョンの場合は、有料版であることをユニョムヘジュください.\n" +
        			"アプリケーションの使用に不便な点は、開発者にお問合せ下さい.\n" +
        			"お客様にご希望の機能を提供するアプリになるように努力します.\n\n" +   

        			"ありがとう."
        		,   "20111201"
        		} 
        		
          	   ,{
	   				"002"
	       		,	"主な機能紹介"
	       		,	"主な機能と特徴 \n\n" +
	       			"● 複数のユーザー登録が可能 \n" +
	       			"    複数のユーザーを管理することができ、ユーザーごとにスケジュール情報を管理する機能を提供しています.\n" +
	       			"ユーザーごとに色を指定することができ、一定の照会時に、その色が用意されています.\n\n" +
	       			"● スケジュール管理 \n" +
	       			"    全体、月間（カレンダー）、週間、日間、スケジュール照会画面と  " +
	       			"全体、月間（カレンダー）、週間、日間のスケジュール照会画面、およびスケジュールを時間帯別に確認できる画面が構成されています.\n\n" +
	       			"● 記念日の管理\n" +
	       			"    毎年繰り返し、一回の記念日を太陽暦、太陰暦の両方を管理し、年単位で検索が可能です。旧暦サポートしています.\n\n" +
	       			"● ToDo管理\n" +
	       			"    すべきことを簡単なメモの形で管理することができます.\n\n" +
	       			"● 国民の祝日を提供\n" +
	       			"    現在、韓国、米国、中国、日本のPuの仕事が提供され、今後追加される予定です.\n\n" +
	       			"● 太陰暦のサポート\n" +
	       			"    旧暦のサポート1920年〜2020年まで提供されており、顧客の要求時に追加される予定です\n\n" +
	       			"● 旧暦提供\n" +
	       			"    帯、60甲子の情報を提供する別のカレンダーが実装されています.\n" +
	       			"上の情報メニューで確認することができ、同じような機能性のカレンダーが追加される予定です.\n\n" +	       			
	       			"● アラームを提供\n" +
	       			"    イルジョンビョルアラームサービスを提供しており、アラームモード（音、振動）は設定で変更することができます.\n\n" +
	       			"● 共有機能\n" +
	       			"    記念日、予定表情報を共有することができます.\n" +
	       			"（現在のテキストメッセージ、カカオトク、エバーノートサポート）.\n\n" +
	       			"● 設定を変更する\n" +
	       			"    アラームモード（音、振動）を変更する.\n" +
	       			"● ウィジェットを提供\n" +
	       			"    今日のスケジュール（4x1）を提供し、続けて追加される予定.\n" +	       			
	       			"多国語サポート（韓国語、英語、中国語、日語）.\n" +
	       			"スケジュール、記念日、TODOなどのバックアップ/リカバリ機能（SDカード）,\n" +
	       			"他の日程を取得する（端末機内のスケジュール）\n" +
	       			"ただし、他のカレンダーをエクスポートするには、そのアプリでは、毎週定期的な予定は適用されず、実装されていません.\n"
	       		,   "20111201"        			
        	   }
          	   ,{
	   				"006"
	       		,	" V1.5.0を更新（記念日、タスク、スケジュール、アラームなど、追加機能や毎月の定期的なイベントの追加など）"
	       		,	"ポイントは、まず遅い更新をお詫び申し上げます。機能は徐々に強化することが期待されている。\n" +
	       			"特に、設計やウィジェットバージョン1.5が安定した未来に進むために予定されています。\n\n" +
	       			"**メジャーアップデート**\n" +
	       			"1) 強化されたアラーム\n" +
	       			" -.記念日、TO-DOは追加されたアラーム\n" +
	       			"   アラーム設定時に登録されている時間に基づいて処理されます。プリファレンス]> [デフォルトのアラーム時間はインチ固定されます\n" +
	       			"   最初の基本的なアラーム時刻：8:00\n"+
	       			" -.アラームのスケジュールを追加します。に基づいて、終了時刻アラームを登録する必要があります。\n\n" +
	       			"2) 毎月の定期的なスケジュールを登録することができます\n" +
	       			" -.特定の日（最終日を含む）毎月の定期的なイベント登録が可能です。\n\n" +
	       			"3) カレンダービューを変更する\n" +
	       			" -.旧暦項目（旧暦1,15、一日の月を持ち上げる）を追加\n " +
	       			" -.アイコンの記念日は - 修正の>リストがあるように見えます。\n\n" +
	       			"4) グループエントリ周年を追加\n" +
	       			" -.登録と一日記念グループエントリ（ホームメニュー）ビューを追加しました。\n" +
	       			"    グループ情報のルックアップ関数は次のバージョンに反映されます。\n\n" +
	       			"5) その他\n" +
	       			" -.掲示板ホームメニューに移動します。\n" +
	       			" -.変更要求やバグの修正として知られているビュー。\n\n"
	       		,   "20120507"        			
         	   }            	   
        	};		
        
		return strArr;
	}
	public String [][] getNoticeFormallyJa()
	{
		//key, title, content       
		String[][] strArr = new String[][] { 

        		{	
        			"001"
        		,	"SM Calendarへようこそ!"
        		,	"SM Calendarを購入してくださった皆さんに感謝します.\n\n" +
    				"ユーザーごとのスケジュールを管理することができ、子供、配偶者など、他のユーザーのスケジュールを総合的に管理することができます.\n\n" +
    				"その他の日常生活に必要な旧暦カレンダー、記念日、TODOの管理機能を提供しており、 \n" +
    				"全体、月間、週間、日間のスケジュール照会画面、およびスケジュールを時間帯別に確認できる画面が構成されています.\n\n" +
    				"その他、既存のスケジュールをインポートする機能が実装されていますので最初にインストールされた方は、利点を参照したいてご使用ください.\n\n" +
        			"アプリケーションの使用に不便な点は、開発者にお問合せ下さい.\n" +
        			"お客様にご希望の機能を提供するアプリになるように努力します.\n\n" +   

        			"ありがとう."
        		,   "20111201"
     		
        		}  	
           	   ,{
	   				"002"
	       		,	"主な機能紹介"
	       		,	"主な機能と特徴 \n\n" +
	       			"● 複数のユーザー登録が可能 \n" +
	       			"    複数のユーザーを管理することができ、ユーザーごとにスケジュール情報を管理する機能を提供しています.\n" +
	       			"ユーザーごとに色を指定することができ、一定の照会時に、その色が用意されています.\n\n" +
	       			"● スケジュール管理 \n" +
	       			"    全体、月間（カレンダー）、週間、日間、スケジュール照会画面と  " +
	       			"全体、月間（カレンダー）、週間、日間のスケジュール照会画面、およびスケジュールを時間帯別に確認できる画面が構成されています.\n\n" +
	       			"● 記念日の管理\n" +
	       			"    毎年繰り返し、一回の記念日を太陽暦、太陰暦の両方を管理し、年単位で検索が可能です。旧暦サポートしています.\n\n" +
	       			"● ToDo管理\n" +
	       			"    すべきことを簡単なメモの形で管理することができます.\n\n" +
	       			"● 国民の祝日を提供\n" +
	       			"    現在、韓国、米国、中国、日本のPuの仕事が提供され、今後追加される予定です.\n\n" +
	       			"● 太陰暦のサポート\n" +
	       			"    旧暦のサポート1920年〜2020年まで提供されており、顧客の要求時に追加される予定です\n\n" +
	       			"● 旧暦提供\n" +
	       			"    帯、60甲子の情報を提供する別のカレンダーが実装されています.\n" +
	       			"上の情報メニューで確認することができ、同じような機能性のカレンダーが追加される予定です.\n\n" +	       			
	       			"● アラームを提供\n" +
	       			"    イルジョンビョルアラームサービスを提供しており、アラームモード（音、振動）は設定で変更することができます.\n\n" +
	       			"● 共有機能\n" +
	       			"    記念日、予定表情報を共有することができます.\n" +
	       			"（現在のテキストメッセージ、カカオトク、エバーノートサポート）.\n\n" +
	       			"● 設定を変更する\n" +
	       			"    アラームモード（音、振動）を変更する.\n" +
	       			"● ウィジェットを提供\n" +
	       			"    今日のスケジュール（4x1）を提供し、続けて追加される予定.\n" +	       			
	       			"多国語サポート（韓国語、英語、中国語、日語）.\n" +
	       			"スケジュール、記念日、TODOなどのバックアップ/リカバリ機能（SDカード）,\n" +
	       			"他の日程を取得する（端末機内のスケジュール）\n" +
	       			"ただし、他のカレンダーをエクスポートするには、そのアプリでは、毎週定期的な予定は適用されず、実装されていません.\n"
	       		,   "20111201"        			
       	   }  
         	   ,{
	   				"003"
	       		,	"スケジュールのインポート/エクスポートする方法"
	       		,	"他の日程アプリに登録されたスケジュールをSM Calendarにインポートすることができるサービスです.\n\n" +
	       			"現在の予定表の共有が可能なアプリは、端末内に組み込まれている一定のApp（ネダルリョク）に限られているが、今後の顧客の要求に応じて追加する予定です.\n\n" +
	       			"Googleカレンダーをお使いの場合、端末のスケジュールAppでガジョオンドィSM Caledarにコピーする方法をご利用ください. \n" +
	       			"Googleカレンダーの連携は、開発中であり、近日中にサービスを提供する予定ですのでお客様のご了承下さい.\n\n" +
	       			"既存の予定表アプリの場合、同期（変更内容を即座に動的に反映）の機能を利用するが、私達のアプリケーションの場合、予定表をコピーしてくる方式を導入しています.\n" +
	       			"他のアプリケーションでエラーが発生したスケジュールがすべて削除されたり変動が発生するのを未然に防止しようと、お客様が選択した日程に限り処理できるように画面が構成されており、 " +
	       			"機能拡張のリクエストをすれば参照して反映させていただきます.\n\n" +
	       			"現在の制限事項としては、SM Calendarで提供される反復スケジュールは、毎週のスケジュールに限られており\n" +
	       			"月、隔週、特定の日の繰り返し周期は、取得することもありませんので、ご了承下さい.\n\n" +
	       			"● 共有できるアプリ \n" +
	       			"    端末機内のスケジュールApp\nGoogleカレンダーのユーザーの場合、端末機内のスケジュールAppでガジョオシンドィ処理してください）\n\n" +
	       			"● 共有の方法 \n" +
	       			"    インポート\n" +
	       			"[ホーム]メニュー ->設定 ->スケジュールのインポート->ネダルリョク->スケジュールチェック ->[インポート]ボタン\n\n" +
	       			"● 制約事項 \n" +
	       			"    定期的な予定の場合は月、隔週、特定の日の繰り返しはインポートされません.\n\n" 
	       			
	       		,   "20111201"        			
        	   }
          	   ,{
	   				"006"
	       		,	" V1.5.0を更新（記念日、タスク、スケジュール、アラームなど、追加機能や毎月の定期的なイベントの追加など）"
	       		,	"ポイントは、まず遅い更新をお詫び申し上げます。機能は徐々に強化することが期待されている。\n" +
	       			"特に、設計やウィジェットバージョン1.5が安定した未来に進むために予定されています。\n\n" +
	       			"**メジャーアップデート**\n" +
	       			"1) 強化されたアラーム\n" +
	       			" -.記念日、TO-DOは追加されたアラーム\n" +
	       			"   アラーム設定時に登録されている時間に基づいて処理されます。プリファレンス]> [デフォルトのアラーム時間はインチ固定されます\n" +
	       			"   最初の基本的なアラーム時刻：8:00\n"+
	       			" -.アラームのスケジュールを追加します。に基づいて、終了時刻アラームを登録する必要があります。\n\n" +
	       			"2) 毎月の定期的なスケジュールを登録することができます\n" +
	       			" -.特定の日（最終日を含む）毎月の定期的なイベント登録が可能です。\n\n" +
	       			"3) カレンダービューを変更する\n" +
	       			" -.旧暦項目（旧暦1,15、一日の月を持ち上げる）を追加\n " +
	       			" -.アイコンの記念日は - 修正の>リストがあるように見えます。\n\n" +
	       			"4) グループエントリ周年を追加\n" +
	       			" -.登録と一日記念グループエントリ（ホームメニュー）ビューを追加しました。\n" +
	       			"    グループ情報のルックアップ関数は次のバージョンに反映されます。\n\n" +
	       			"5) その他\n" +
	       			" -.掲示板ホームメニューに移動します。\n" +
	       			" -.変更要求やバグの修正として知られているビュー。\n\n"
	       		,   "20120507"        			
        	   }            	   
        	};		
        
		return strArr;
	}  
	public String [][] getNoticeLiteZh()
	{
		//key, title, content       
		String[][] strArr = new String[][] { 

        		{	
        			"001"
        		,	"歡迎 SM Calendar Lite"
        		,	"感謝您購買 SM Calendar Lite.\n\n" +
    				"每個用戶可以管理子女，配偶，以及其他用戶的日程安排可以全面管理.\n\n" +
    				"日常生活所需資源農曆週年，任務管理功能，以及一個完整的，每月，每週和每日的日程，看屏幕，屏幕配置的是小時時間表的意見.\n\n" +
    				"其他有能力導入現有的時間表實現的，所以首先要看到對這些誰已經安裝了可以使用的優勢.\n\n" +
        			"Lite版本的應用程序提前購買之前上報必要的應用，實現所需的功能，以確保所提供的版本.\n\n\n" +
            		"Lite 版本之間的差異\n\n"+
            		"1) 農曆日曆：提供一個月.\n" +
            		"2) 廣告：在刪除的完整版本.\n" +
            		"3) 日曆 /記念日：完整版可從\n\n\n"  +
        			"廣告顯示誰希望使用購買完整版的所有功能提供其他乘客，將不勝感激.\n" +
        			"有關完整的版本，請知道這是一個付費版本.\n" +
        			"App，請聯繫我們使用不便的開發商。.\n" +
        			"客戶提供所需的功能，使應用程序的工作.\n\n" +   

        			"謝謝."
        		,   "20111201"
        		} 
        		
          	   ,{
	   				"002"
	       		,	"公司的主要特點"
	       		,	"主要特點和優點 \n\n" +
	       			"● 多個用戶可以註冊 \n" +
	       			"    您可以管理每個用戶的基礎上，有能力管理時間表內的資料提供多個用戶.\n" +
	       			"每個用戶可以指定顏色，該顏色是不變的查找.\n\n" +
	       			"● 日曆 \n" +
	       			"    整體而言，每月（日曆），每週，每天的日程和日曆視圖顯示一個屏幕，您可以為每個區域配置.\n\n" +
	       			"● 纪念日\n" +
	       			"    每年重複，一次性解除紀念日農曆來管理，並且可以作為一年圖片：農曆的支持.\n\n" +
	       			"● 必做之事\n" +
	       			"    通道可以進行管理，在短期票據形式.\n\n" +
	       			"● 提供全國性的節日\n" +
	       			"    目前，韓國，美國，中國和日本，節假日將提供預計將在未來加入.\n\n" +
	       			"● 支持月球\n" +
	       			"    1920年至2020年，將可根據要求添加.\n\n" +
	       			"● 提供陰曆\n" +
	       			"    樂隊，60突然分開日曆實施提供信息.\n" +
	       			"信息可以在菜單頂部發現，日曆將添加類似的功能.\n\n" +	       			
	       			"● 提供報警\n" +
	       			"    附表報警服務和報警方式（聲音，振動）可以更改設置.\n\n" +
	       			"● 共享\n" +
	       			"   紀念日，您可以共享您的日曆信息.\n" +
	       			"(目前的文字信息，KakaoTalk，EverNote).\n\n" +
	       			"● 更改設置\n" +
	       			"    報警方式（聲音，振動）改變.\n" +
	       			"● 所提供的部件\n" +
	       			"    在今天的時間表（4X1），提供繼續增加.\n" +	       			
	       			"多語言支持（英語，韓國，中國，及以上）.\n" +
	       			"附表紀念日，做一個備份 /恢復功能（SD卡）.\n" +
	       			"獲取不同的時間表（手機日曆）\n" +
	       			"然而，出口的其他日曆應用程序並不適用於每週重複時間表尚未落實.\n"
	       		,   "20111201"        			
        	   }
          	   ,{
	   				"006"
	       		,	"更新V1.5.0（週年，待辦事項，日程表，鬧鐘，如增加額外的功能和每月定期活動）"
	       		,	"道歉後期的更新，第一點。預計功能逐步加強。\n" +
	       			"在特別的設計和部件1.5版預定進行到一個穩定的未來\n\n" +
	       			"** 主要更新 **\n" +
	       			"1) 增強報警\n" +
	       			" -.週年之際，請勿添加報警\n" +
	       			"   註冊設置報警時間將處理基於時間。偏好>預設報警時間將固定英寸\n" +
	       			"   第一個基本的報警時間：上午8:00\n"+
	       			" -.加入調度報警：結束時間的基礎上的報警，必須進行登記。\n\n" +
	       			"2) 可以註冊每月定期計劃\n" +
	       			" -.特定的一天（包括最後一天），每月定期活動登記。\n\n" +
	       			"3) 更改日曆視圖\n" +
	       			" -.添加的的陰曆項目（解除一天一月份，農曆1,15）\n " +
	       			" -.週年的圖標 - >修改列表似乎是。\n\n" +
	       			"4) 添加組條目週年\n" +
	       			" -.登記和全部紀念日組條目（主菜單）添加視圖。\n" +
	       			"   集團的信息查詢功能，將反映在未來的版本。\n\n" +
	       			"5) 其他\n" +
	       			" -.公告首頁菜單。\n" +
	       			" -.查看，被稱為變更請求和缺陷修復。\n\n"
	       		,   "20120507"        			
         	   }                 	   
        	};		
        
		return strArr;
	}
	public String [][] getNoticeFormallyZh()
	{
		//key, title, content       
		String[][] strArr = new String[][] { 

        		{	
        			"001"
        		,	"歡迎 SM Calendar"
        		,	"感謝您購買 SM Calendar.\n\n" +
    				"每個用戶可以管理子女，配偶，以及其他用戶的日程安排可以全面管理.\n\n" +
    				"日常生活所需資源農曆週年，任務管理功能，以及一個完整的，每月，每週和每日的日程，看屏幕，屏幕配置的是小時時間表的意見.\n\n" +
    				"其他有能力導入現有的時間表實現的，所以首先要看到對這些誰已經安裝了可以使用的優勢.\n\n" +        			
        			"App，請聯繫我們使用不便的開發商。.\n" +
        			"客戶提供所需的功能，使應用程序的工作.\n\n" +   

        			"謝謝."
        		,   "20111201"
     		
        		}  	
           	   ,{
	   				"002"
	       		,	"主要特點"
	       		,	"主要特點和優點 \n\n" +
	       			"● 多個用戶可以註冊 \n" +
	       			"    您可以管理每個用戶的基礎上，有能力管理時間表內的資料提供多個用戶.\n" +
	       			"每個用戶可以指定顏色，該顏色是不變的查找.\n\n" +
	       			"● 日曆 \n" +
	       			"    整體而言，每月（日曆），每週，每天的日程和日曆視圖顯示一個屏幕，您可以為每個區域配置.\n\n" +
	       			"● 纪念日\n" +
	       			"    每年重複，一次性解除紀念日農曆來管理，並且可以作為一年圖片：農曆的支持.\n\n" +
	       			"● 必做之事\n" +
	       			"    通道可以進行管理，在短期票據形式.\n\n" +
	       			"● 提供全國性的節日\n" +
	       			"    目前，韓國，美國，中國和日本，節假日將提供預計將在未來加入.\n\n" +
	       			"● 支持月球\n" +
	       			"    1920年至2020年，將可根據要求添加.\n\n" +
	       			"● 提供陰曆\n" +
	       			"    樂隊，60突然分開日曆實施提供信息.\n" +
	       			"信息可以在菜單頂部發現，日曆將添加類似的功能.\n\n" +	       			
	       			"● 提供報警\n" +
	       			"    附表報警服務和報警方式（聲音，振動）可以更改設置.\n\n" +
	       			"● 共享\n" +
	       			"   紀念日，您可以共享您的日曆信息.\n" +
	       			"(目前的文字信息，KakaoTalk，EverNote).\n\n" +
	       			"● 更改設置\n" +
	       			"    報警方式（聲音，振動）改變.\n" +
	       			"● 所提供的部件\n" +
	       			"    在今天的時間表（4X1），提供繼續增加.\n" +	       			
	       			"多語言支持（英語，韓國，中國，及以上）.\n" +
	       			"附表紀念日，做一個備份 /恢復功能（SD卡）.\n" +
	       			"獲取不同的時間表（手機日曆）\n" +
	       			"然而，出口的其他日曆應用程序並不適用於每週重複時間表尚未落實.\n"
	       		,   "20111201"        			
           	   }   
         	   ,{
	   				"003"
	       		,	"日曆導入 /導出方法"
	       		,	"在其他日曆應用 SM Calendar時間表註冊是一個可以被導入到服務.\n\n" +
	       			"您可以與內置的時間表內的終端應用程序（我的日曆）的應用，但僅限於未來預計將根據客戶的要求添加您的日曆.\n\n" +
	       			"如果您使用從進度落後 SM Caledar複製終端谷歌日曆應用程序是通過方式。 \n" +
	       			"谷歌日曆整合正在開發和計劃提供服務，盡快請注意，客人“污泥.\n\n" +
	       			"如果現有的日曆應用程序同步（動態的變化立即反映）功能，但我們要複製應用程序在被介紹的方式來安排.\n" +
	       			"在其他應用程序的錯誤，或刪除所有的日程將發生變化，以防止顧客只對選定的時間表處理出現的畫面改進包括將反映看，如果你讓這個請求. \n\n" +
	       			"限制包括當前重複 SMCalendar有限的時間表由每週星期一提供經常性的活動，雙週，重複安排一個特定的一天無法連接到拿到感謝信.\n\n" +
	       			"● 應用程序可以共享 \n" +
	       			"    在日曆應用程序\n智能手機（如谷歌日曆用戶的處理後得到的客艙終端的時間表，如果應用程序）\n\n" +
	       			"● 如何分享 \n" +
	       			"    進口\n" +
	       			"主菜單 ->設置->導入日曆->我的日曆 ->日曆查詢->導入按鈕\n\n" +
	       			"● 限制 \n" +
	       			"    對於每月經常性活動，每兩週，重複某一天不能進口.\n\n" 
	       			
	       		,   "20111201"        			
        	   }
           	   ,{
	   				"006"
	       		,	"更新V1.5.0（週年，待辦事項，日程表，鬧鐘，如增加額外的功能和每月定期活動）"
	       		,	"道歉後期的更新，第一點。預計功能逐步加強。\n" +
	       			"在特別的設計和部件1.5版預定進行到一個穩定的未來\n\n" +
	       			"** 主要更新 **\n" +
	       			"1) 增強報警\n" +
	       			" -.週年之際，請勿添加報警\n" +
	       			"   註冊設置報警時間將處理基於時間。偏好>預設報警時間將固定英寸\n" +
	       			"   第一個基本的報警時間：上午8:00\n"+
	       			" -.加入調度報警：結束時間的基礎上的報警，必須進行登記。\n\n" +
	       			"2) 可以註冊每月定期計劃\n" +
	       			" -.特定的一天（包括最後一天），每月定期活動登記。\n\n" +
	       			"3) 更改日曆視圖\n" +
	       			" -.添加的的陰曆項目（解除一天一月份，農曆1,15）\n " +
	       			" -.週年的圖標 - >修改列表似乎是。\n\n" +
	       			"4) 添加組條目週年\n" +
	       			" -.登記和全部紀念日組條目（主菜單）添加視圖。\n" +
	       			"   集團的信息查詢功能，將反映在未來的版本。\n\n" +
	       			"5) 其他\n" +
	       			" -.公告首頁菜單。\n" +
	       			" -.查看，被稱為變更請求和缺陷修復。\n\n"
	       		,   "20120507"        			
        	   }                    	   
        	};		
        
		return strArr;
	}  	
}
