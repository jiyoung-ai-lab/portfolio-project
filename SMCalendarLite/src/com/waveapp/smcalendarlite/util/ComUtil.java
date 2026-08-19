package com.waveapp.smcalendarlite.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;
 
import com.waveapp.smcalendarlite.R;
import com.waveapp.smcalendarlite.common.ComConstant;
import com.waveapp.smcalendarlite.common.VersionConstant;
import com.waveapp.smcalendarlite.info.ScheduleInfo;
import com.waveapp.smcalendarlite.info.SpecialDayInfo;

 
/**
 * 다양한 Text 형식, 금전형식, Date형식을 지원할 수 있는 Static Method를 제공한다.
 * @see
 */
public class ComUtil
{
    /**
     * CommonUtil constructor comment.
     */
    public ComUtil() {
        super();
    }

    /**
     * 숫자여부 체크. <BR><BR>
     * -. tuning complete
     * @param String
     * @return boolean
     */
    public static boolean isNumber(String str){
 
        if(str != null && !str.equals("")){            
            int strLen = str.length();
            for(int i = 0 ; i < strLen ; i++){
            	if(!('0'  <=  str.charAt(i)  &&  str.charAt(i)  <=  '9')) return false; 
            }
         }
        return true;
    }
 
    /**
     * null인 경우 공백문자/그외 trim값을  반환한다. <BR><BR>
     *
     * 사용예) setBlank( null )<BR>
     * 결 과 ) ""<BR><BR>
     *
     * @param pInstr String
     * @return java.lang.String
     */
    public static String setBlank( String pInstr ) {
    	
        String rStr = "";
        
        if ( pInstr != null ) {
            rStr = pInstr.trim();
            if ( pInstr.equals( "null" ) ) {
                rStr = "";
            }
        }
        return rStr;
    }

    /**
     * int 값을 string으로 변환하면서 null인 경우 공백문자/그외 trim값을  반환한다. <BR><BR>
     *
     * 사용예) setBlank( null )<BR>
     * 결 과 ) ""<BR><BR>
     *
     * @param i int
     * @return java.lang.String
     */
    public static String setBlank( int i ) {
        String rStr = intToString(i);
        

        if ( rStr != null ) {
            rStr = rStr.trim();
            if ( rStr.equals( "null" ) ) {
            	rStr = "";
            }
        }
        return rStr;
    }


    /**
     * 주어진 문자열(s)에서 특정문자열(find)을 지정문자열(to)로 변환
     * 주어진 문자열(s)이 null인 경우 빈문자열("")을 반환.
     * @param find
     * @param to
     * @param s
     * @return String
     * @deprecated
     */
    public static String rplcStr( String find, String to, String s ) {
        if ( s != null && !s.trim().equals( "" ) ) {
            String str = new String( s );
            StringBuffer content = new StringBuffer();


            int begin = -1, end = 0;


            while ( str.length() != 0 ) {
                begin = str.indexOf( find );
                if ( begin == -1 ) {
                    content.append( str );
                    break;
                } else {
                    end = begin + find.length();
                    content.append( str.substring( 0, begin ) );
                    content.append( to );
                    str = str.substring( end );
                }
            }
            return new String( content );


        } else {
            return "";
        }
    }


    /**
     * 주어진 문자열(s)에서 특정문자열(find)이 있는지의 여부
     * @param find
     * @param s
     * @return boolean
     */
    public static boolean isInStr( String find, String s ) {
        boolean retVal = false;
        if ( s != null && !s.trim().equals( "" ) ) {
            String str = new String( s );
            
	        int begin = -1;
	        //int end = 0;
	
	        begin = str.indexOf( find );
	        if ( begin != -1 ) {
	            return true;
	        }
        }
        return retVal;
    }
    /**
     * 주어진 문자열(s)에서 특정문자열(find)이 있는지의 여부(자리return)
     * @param find
     * @param s
     * @return int
     */
    public static int isInStrPos( String find, String s ) {
    	int begin = -1;
        //boolean retVal = false;
        if ( s != null && !s.trim().equals( "" ) ) {
            String str = new String( s );
	
	        begin = str.indexOf( find );
	        if ( begin != -1 ) {
	            return begin;
	        }
        }
        return begin;
    }


    /**
     * 주어진 문자열(s)에서 남은자리수에 "0" 채우기(앞쪽)
     * @param find
     * @param to
     * @param s
     * @return String
     */
    public static String fillSpaceToZero( String str, int len ) {
    	
    	StringBuffer returnString = new StringBuffer();
    	
    	if ( str != null ) {
    		String tStr = str.trim();
    		int sLen = tStr.length();
    		if ( !tStr.equals("") && sLen < len && sLen >= 0 ) {            
	            int filllen = len - sLen;
	            for ( int i = 0 ; i < filllen ; i++ ) {
	            	returnString.append("0");            	
	            }
    		}
    		returnString.append(tStr);
    	} else {
    		returnString.append("");
    	}
        return returnString.toString();
    }
    /**
     * 주어진 문자열(s)에서 남은자리수에 "0" 채우기(앞쪽)
     * @param find
     * @param to
     * @param s
     * @return String
     */
    public static String fillSpaceToZero( int num, int len ) {
    	
    	StringBuffer returnString = new StringBuffer();
    	
    	String str = Integer.toString(num);
    	
    	if ( str != null ) {
    		String tStr = str.trim();
    		int sLen = tStr.length();
    		if ( !tStr.equals("") && sLen < len && sLen >= 0 ) {            
	            int filllen = len - sLen;
	            for ( int i = 0 ; i < filllen ; i++ ) {
	            	returnString.append("0");            	
	            }
    		}
    		returnString.append(tStr);
    	}else {
    		returnString.append("");
    	}
    	
        return returnString.toString();
    }    
    /**
     * 주어진 문자열(s)에서 남은자리수에 blank 채우기(뒤쪽)
     * @param find
     * @param to
     * @param s
     * @return String
     */
    public static String fillSpaceToBlank( String str, int len ) {
    	StringBuffer returnString = new StringBuffer();
    	
    	if ( str != null ) {
    		String tStr = str.trim();
    		
    		returnString.append(tStr);
    		
    		int sLen = tStr.length();
    		if ( !tStr.equals("") && sLen < len && sLen >= 0 ) {            
	            int filllen = len - sLen;
	            for ( int i = 0 ; i < filllen ; i++ ) {
	            	returnString.append(" ");            	
	            }
    		}
    	}  else {
    		returnString.append("");
    	}
    	
        return returnString.toString();
    }
    /**
     * 주어진 문자열(s)에서 남은자리수에 blank 채우기(앞쪽)
     * @param find
     * @param to
     * @param s
     * @return String
     */
    public static String fillSpaceToBlankF( int num, int len ) {
    	StringBuffer returnString = new StringBuffer();
    	
       	String str = Integer.toString(num);
       	
    	if ( str != null ) {
    		String tStr = str.trim();
    		
    		int sLen = tStr.length();
    		if ( !tStr.equals("") && sLen < len && sLen >= 0 ) {            
	            int filllen = len - sLen;
	            for ( int i = 0 ; i < filllen ; i++ ) {
	            	returnString.append(" ");            	
	            }
	            returnString.append(tStr);
    		} else {
    			 returnString.append(tStr);
    		}
    	}  else {
    		returnString.append("");
    	}
    	
        return returnString.toString();
    }    
    /**
     * 구분문자(delim)로 주어진 문자열(pInsStr) 분리
     * @param pInsStr
     * @param delim
     * @return ArrayList
     * @deprecated
     */
    public static ArrayList<String> splitStr( String pInsStr, String delim ) {
        StringTokenizer st = new StringTokenizer( pInsStr );
        //int count = st.countTokens();
        ArrayList<String> retStr = new ArrayList<String>();
        while ( st.hasMoreTokens() ) {
            retStr.add( st.nextToken( delim ) );
        }
        return retStr;
    }


    /**
     * 문자열을 특정수 만큼만 잘라내기
     * @param s
     * @param i
     * @return String
     * @deprecated
     */
    public static String cutStr( String s, int i ) {
        if ( s != null && s.trim().length() > 0 ) {
            if ( s.length() > i ) {
                return ( s.substring( 0, i ) + ".." );
            } else {
                return s;
            }
        } else {
            return "";
        }
    }

    /***
     * 라디오버튼 id 값으로 "Y" 값 return
     * @param inVal
     * @return String
     */
    public static String setRadioCheckYN( Boolean inVal ) {
    	String sReturn = "";
        try {
            if ( inVal.booleanValue() == true) sReturn = "Y";
            else sReturn = "";
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return sReturn;
    }   
    /***
     * 체크박스 체크시 "Y" 값 return
     * @param inVal
     * @return String
     */
    public static String setCheckYN( Boolean inVal ) {
    	String sReturn = "";
        try {
            if ( inVal.booleanValue() == true) sReturn = "Y";
            else sReturn = "";
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return sReturn;
    }     
    /***
     * "Y"값인 경우 체크박스 체크
     * @param inVal
     * @return boolean
     */
    public static boolean getCheckYN( String inVal ) {
    	Boolean checkYN = false;
        try {
            if ( inVal != null && inVal.equals("Y")) checkYN = true;
            else checkYN = false;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return checkYN;
    } 
    /***
     * "Y"값인 경우 해당 radio botton 체크
     * @param inVal
     * @return boolean
     */
    public static int getRadioCheckYN( String inVal,  int id ) {
    	int chkRadio = 0;
        try {
            if ( inVal != null && inVal.equals("Y")) chkRadio =  id;
            else return chkRadio = 0;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return chkRadio;
    }    
    /***
     * 타입변환(string  -> int)
     * @param inVal
     * @return i
     */
    public static int stringToInt( String inVal ) {
    	int i = 0 ;
        try {
            if ( inVal != null && !inVal.equals("") && !inVal.equals("null")) i = Integer.parseInt(inVal);
            else i = 0;
        } catch ( Exception e ) {
        	Log.w(">>>>>>>>>>>>>>>>>>>>> inVal:", inVal);
			
            e.printStackTrace();
        }
        return i;
    } 
    /***
     * 타입변환(string  -> int)
     * @param inVal
     * @return i
     */
    public static String intToString( int inVal ) {
    	String sStr = "" ;
        try {
        	sStr = Integer.toString(inVal);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return sStr;
    }  
    /***
     * 타입변환(string  -> int)
     * @param inVal
     * @return i
     */
    public static Long stringToLong( String inVal ) {
    	Long i = (long) 0;
        try {
            if ( inVal != null && !inVal.equals("")) i = Long.parseLong(inVal);
            else i = (long) 0;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return i;
    } 
    /***
     * 타입변환(string  -> long)
     * @param inVal
     * @return i
     */
    public static String longToString( long inVal ) {
    	String sStr = "" ;
        try {
        	sStr = Long.toString(inVal);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return sStr;
    } 


	/**
	* 문자열을 치한하여 결과를 반환한다.
	* 예제) replaceString("ABCDEFG", "F", "")
	*/
	public static String replaceString(String pSrcString, String pOldPattern, String pNewPattern)	{
		String srcString = null;
		String tgtString = new String();


		if (pSrcString == null)	{
			tgtString = "";
		}
		else	{
			srcString = new String(pSrcString);
			tgtString = srcString.replaceAll(pOldPattern, pNewPattern);
		}


		return tgtString;
	}

	/**
	* 숫자에 3 자리마다 , 를 추가함
	* @deprecated
	*/
	public static String addComma(long pm_lValue){
		boolean blnMinus = false;
		if (pm_lValue < 0) {
			blnMinus = true;
			pm_lValue = (-1)*pm_lValue;
		}
		String lm_sString = Long.toString(pm_lValue);
		int lm_iLen = lm_sString.length();
		String lm_sRet = "";


		if (lm_iLen <= 0) return "";


		int size = 3;
		int cnt = lm_iLen / size;
		int pos = lm_iLen % size;
		String result = "";
		for( int i = cnt ; i > 0 ; i-- ){
			result = "," + lm_sString.substring(pos+(i-1)*size, pos+i*size) + result ;
		}


		if( pos == 0 )
			lm_sRet = result.substring(1);
		else
			lm_sRet = lm_sString.substring(0,pos) + result;


		if (blnMinus) lm_sRet = "-" + lm_sRet;
		return lm_sRet;
	}//end addComma

	/**
	* 한글 -> 영문
	*/
	public static String K2E(String str) throws UnsupportedEncodingException {
		if(str== null) return "";
		return new String(str.getBytes("KSC5601"), "8859_1");
	}
	
	/**
	* 영문 -> 한글
	*/
	public static String E2EUC(String str) throws UnsupportedEncodingException {
		if(str== null) return "";
//		return new String(str.getBytes("8859_1"), "EUC-KR");
		return str;
	}
	
	/**
	* Return URL Encoding...
	*/
	public static String encodeURL(String url) {
		
		if(url== null) return "";


		StringBuffer result = new StringBuffer("");


		for (int x = 0 ; x < url.length() ; x++) {
			result.append(Integer.toHexString((int)url.charAt(x)));
		}
		
		return result.toString() ;
	}


	/**
	* 전화번호를 자른다.
	* '-' 은 빼고 집어넣는다. 
	*/
	public static String[] getSplitTel(String tel) {
		String [] telno = new String[3];
		if(tel.length()>0){
			if(tel.length()>10){
				telno[0] = tel.substring(0, 3);
				telno[1] = tel.substring(3, 7);
				telno[2] = tel.substring(7);
			}else if("02".equals(tel.substring(0, 2))){
				if(tel.length()<10){
					telno[0] = tel.substring(0,2);
					telno[1] = tel.substring(2,5);
					telno[2] = tel.substring(5);
				}else{
					telno[0] = tel.substring(0,2);
					telno[1] = tel.substring(2,6);
					telno[2] = tel.substring(6);				
				}
			}else if(tel.length()==10){
				telno[0] = tel.substring(0,3);
				telno[1] = tel.substring(3,6);
				telno[2] = tel.substring(6);	
			}	
		}
		return telno ;
	}    
	
	/**
	* 필수항목에 값이 null or space 인 경우 err alart 처리
	*/	
	public static boolean getNullCheck(String str) {
		
		if ( str != null && !str.trim().equals("")){
			return true;
		} else {
			return false;
		}		
	}

	/**
	* 영문 -> 한글
	*/
	public static String E2K(String str) throws UnsupportedEncodingException {
		if(str== null) return "";
		return new String(str.getBytes("8859_1"), "EUC-KR");
	}
	
	/**
	* spinner get ( db key 값 -> spinner key position )
    * @param Context, id(array id), parm1(key value)
    * @return position
	*/
	public static int getSpinner(Context ctx, int id, String parm1) {
		int position = 0;
		Resources  res 	= ctx.getResources();		
        String[] sKeyArr = res.getStringArray(id);
        int len = sKeyArr.length;

        for ( int i = 0  ; i < len ; i++){
        	String sTemp = sKeyArr[i].toString();
        	if ( sTemp != null && parm1 != null && !sTemp.equals("") 
        			&& sTemp.equals(parm1)) {
        		position = i;
        		break;
        	}
        }
        return position;        		
	}
	
	/**
	* spinner set ( spinner key -> db key 값 )
    * @param Context, id(array id), Spinner
    * @return position
	*/	
	public static  String setSpinner(Context ctx, int id, Spinner sp) {		
		Resources  res 	= ctx.getResources();
		int position 	= (int) sp.getSelectedItemId();
        String[] sKeyArr = res.getStringArray(id);
        String str 		= sKeyArr[position].toString();
        
        return str;        		
	}
	
	/**
	* spinner get ( db key 값 -> spinner string )
    * @param Context, keyarr(array id), arr(array id), value
    * @return string
	*/		
	public static String getSpinnerText(Context ctx, int keyarr, int arr, String value) {
		String str = "";
		Resources  res = ctx.getResources();		
        String[] sKeyArr 	= res.getStringArray(keyarr);
        String[] sArr 		= res.getStringArray(arr);
        int len = sKeyArr.length;
        for ( int i = 0  ; i < len ; i++){
        	String sTemp = sKeyArr[i].toString();
        	if ( sTemp != null && value != null && sTemp.equals(value)) {
        		str = sArr[i].toString();
        		break;
        	}
        }
        return str;        		
	}
		
	/*
     * spinner get (from Db)
     * param : db key list, selected id
     */
	public static int getSpinnerFromDb(ArrayList<Long> keyList, long id ) {
		int position = 0;
		int len = keyList.size();
        for ( int i = 0  ; i < len ; i++){
        	long keyId = keyList.get(i);
        	if ( keyId == id ) {
        		position = i;
        		break;
        	}
        }
        return position;        		
	}
	
	public static  Long setSpinnerFromDb(ArrayList<Long> keyList, Spinner sp) {	
		
		int position 	= (int) sp.getSelectedItemId();
		Long key = (long)0;
		if ( keyList.size() > 0 ) {
			key 		= keyList.get(position);
		}
        
        return key;        		
	}
	/*
	 * "Y" 값일 경우 원하는 int value return
	 */
	public static  int setYesReturnValue(String str, int value ) {	
		int ret = 0 ;
		
		if ( str != null && str.equals("Y")) {
			ret = value;
		}
        
        return ret;        		
	}		
	/*
	 * "Y" 값일 경우 원하는 String value return (단, 아닌경우 one space)
	 */
	public static  String setYesReturnValue(String str, String value ) {	
		String ret = " " ;
		
		if ( str != null && str.trim().equals("Y")) {
			ret = value;
		}
        
        return ret;        		
	}	

	/*
	 * 문자배열 만들기 (1자리씩 잘라서 배열로 만듬)
	 */	
	public static String [] getArrayFromString ( String str ) {

			ArrayList< String > list  = new ArrayList<String>();
			
			int len = str.length();
			
			for ( int i = 0 ; i < len ; i++ ) {
				list.add(str.substring(i, i+1));
			}
			
			return (String[])list.toArray();
	}	

	/*
	 * 기념일 구분값에 따른 한글text
	 */
	public static String getSpecialDayText ( Context ctx, String gubun, String subname ) {
		
		String  str = "";

		
		if ( gubun != null && gubun.equals(ComConstant.PUT_BATCH)) {
			if ( subname != null && !subname.trim().equals("")) {
				str = subname;
				return str;
			}			
			str = ComUtil.getStrResource(ctx, R.string.pre_holiday);
		} else if ( gubun != null &&  gubun.equals(ComConstant.PUT_USER)) {
			str = ComUtil.getStrResource(ctx, R.string.specialday);
		} else {
			str = subname;
		}
		
		return str;
		
    }
	/*
	 * 현재 언어정보 가져오기 (iso 639-1)
	 * -. en : english, ko:korean, jp : japan, cz:china( 외 기본 영어로 ) -> 국가코드에 따라 간체,번체로 분기
	 * 다국어 지원시 처리방안 필요(버전을 따로 가야할듯)
	 */
	public static String getPhoneLanguage ( Context ctx ) {
		
		String  str = ctx.getResources().getConfiguration().locale.getLanguage();
		
//		Log.w(">>>>>>>>>>>>>>>>>>getPhoneCgetPhoneLanguageountry : ", "getPhoneLanguage;" + str);
//		ComUtil.showToast(ctx, "getPhoneLanguage;" + str);
		
		//값이 없거나 지원언어가 아닌 경우 영어로
		if ( str == null  || 
				( str != null && str.trim().equals("")) ||
				(( str != null && !str.trim().equals(ComConstant.LOCALE_KO)) &&
				 ( str != null && !str.trim().equals(ComConstant.LOCALE_JA)) &&
				 ( str != null && !str.trim().equals(ComConstant.LOCALE_ZH))) )  {
			str = ComConstant.LOCALE_EN;
		}
		
		return str;
		
    }
	/*
	 * 현재 나라정보 가져오기 (iso 639-1)
	 * -. en_US : 미국, ko_KR : 한국, jp_JR : 일본, cz_CN:중국( 외 기본 미국으로 )
	 * 다국어 지원시 처리방안 필요(버전을 따로 가야할듯)
	 */
	public static String getPhoneCountry ( Context ctx ) {
		
		String  str = ctx.getResources().getConfiguration().locale.getCountry();
		
//		Log.w(">>>>>>>>>>>>>>>>>>getPhoneCountry : ", "getPhoneCountry;" + str);
//		ComUtil.showToast(ctx, "getPhoneCountry;" + str);
		
		//값이 없거나 지원언어가 아닌 경우 미국으로
		if ( str == null  || 
				( str != null && str.trim().equals("")) ||
				(( str != null && !str.trim().equals(ComConstant.NATIONAL_KO)) &&
				 ( str != null && !str.trim().equals(ComConstant.NATIONAL_JP)) &&
				 ( str != null && !str.trim().equals(ComConstant.NATIONAL_US)) &&
				 ( str != null && !str.trim().equals(ComConstant.NATIONAL_CN)) ))  {
			
//				str = ComConstant.NATIONAL_US;
				str = "";
		}
		
		return str;
		
    }	
	/*
	 * 언어 배열 구분 (정순, 역순)
	 */
	public static boolean isLanguageFront ( Context ctx ) {
		
		//정순 (아시아권)
		if ( ( ComConstant.LOCALE != null && ComConstant.LOCALE.trim().equals(ComConstant.LOCALE_KO)) ||
			 ( ComConstant.LOCALE != null && ComConstant.LOCALE.trim().equals(ComConstant.LOCALE_JA)) ||
			 ( ComConstant.LOCALE != null && ComConstant.LOCALE.trim().equals(ComConstant.LOCALE_ZH)))  {
			
				return true;
		//역순(미주,유럽권)
		} else {
			
			return false;
			
		}
    }		
	/*
	 * string.xml 에서 값 가져오기
	 */
	public static String getStrResource ( Context ctx , int id ) {
		
		String  str = ctx.getResources().getString(id);
		
		return str;
		
    }	
    /*
     * 백업파일명 만들기
     * format: name-option
     */
    public static String makeBackupFileName( String option ) { 
    	StringBuffer filename = new StringBuffer();
    	filename.append( "smdata" );
    	if ( option != null && !option.equals("")) {
    		filename.append( "_"  );
    		filename.append( option );
    	}
    	
		return filename.toString();
    	
    }	

    /*
     * 타이틀바 action명 만들기(건수가 있는 경우 건수 추가)
     */
    public static String setActionTitle( Context ctx, int id, int cnt ) {
    	
    	StringBuffer  str = new StringBuffer();
    	
    	str.append(ComUtil.getStrResource(ctx, id));
    	
    	if ( cnt > 0 ) {
    		str.append("(");
    		str.append(ComUtil.intToString(cnt));
    		str.append(")");
    	}
    	
    	return str.toString() ;
    }
    /*
     * 타이틀바 action명 만들기(manager용)
     */
    public static String setActionTitle( Context ctx, int id, String addstr ) {
    	
    	StringBuffer  str = new StringBuffer();
    	
    	//아시아, 미주 format상이
    	if ( ComUtil.isLanguageFront( ctx )) {
    		
        	str.append(ComUtil.getStrResource(ctx, id));
        	
        	if ( addstr != null && addstr.trim().length() > 0 ) {
        		str.append(" ");
        		str.append(addstr);
        	}
        	    		
    	} else {        	
        	if ( addstr != null && addstr.trim().length() > 0 ) {
        		
        		str.append(addstr);
        		str.append(" ");
        	}
        	str.append(ComUtil.getStrResource(ctx, id));
    	}

    	return str.toString() ;
    }
	
    /*
     * 스케줄 내용 메시지로 만들기 (공유메시지용) : 날짜 선택시
     */
    public static String makeScheduleMsg( Context ctx, ScheduleInfo info, String date ) {

    	StringBuffer buffer = new StringBuffer();

    	buffer.append(info.getScheduleName());
    	
    	if ( info.getUsername() != null && !info.getUsername().trim().equals("")){  
    		buffer.append("(");     
        	buffer.append(info.getUsername()); 
        	buffer.append(")");   
    	}
    	
    	buffer.append("\n");
    	if ( date != null && !date.trim().equals("")){
    		buffer.append(SmDateUtil.getDateSimpleFormat(ctx, date, ComConstant.SEPERATE_DOT, true));
        	buffer.append("\n");    		
    	} else {
        	if ( info.getStartDate() != null && !info.getStartDate().trim().equals("")){
        		buffer.append(SmDateUtil.getDateSimpleFormat(ctx, info.getStartDate(),ComConstant.SEPERATE_DOT, false)
        				+ "~" + SmDateUtil.getDateSimpleFormat(ctx, info.getEndDate(), ComConstant.SEPERATE_DOT,false));	 
        		buffer.append("\n"); 
        		buffer.append(setYesReturnValue(info.getCycle(), ComUtil.getStrResource(ctx, R.string.everyweek)));
        		
        		if ( info.getDayOfWeekFullText() != null && !info.getDayOfWeekFullText().trim().equals("")){
        			buffer.append(" ");
        			buffer.append(info.getDayOfWeekFullText());	
        			buffer.append("\n"); 
        		}
        	}   		
    	}
    	
    	if ( info.getAllDayYn() != null && !info.getAllDayYn().trim().equals("")){
    		buffer.append(setYesReturnValue(info.getAllDayYn(), ComUtil.getStrResource(ctx, R.string.allday)));
    	} else {
        	if ( info.getStartTime() != null && !info.getStartTime().trim().equals("")){
        		buffer.append(SmDateUtil.getTimeFullFormat(ctx, info.getStartTime())
        				+ "~" + SmDateUtil.getTimeFullFormat(ctx, info.getEndTime()));	    		
        	}    		
    	}

    	 
    	
    	return buffer.toString() ;
    }
    /*
     * 스케줄 내용 메시지로 만들기 (공유메시지용)
     */
    public static String makeScheduleMsg( Context ctx, SpecialDayInfo info ) {

    	StringBuffer buffer = new StringBuffer();

    	buffer.append(info.getName());
    	buffer.append("\n");
    	if ( info.getSolardate() != null && !info.getSolardate().trim().equals("")){
    		buffer.append(SmDateUtil.getDateSimpleFormat(ctx, info.getSolardate(), ComConstant.SEPERATE_DOT, true));
        	buffer.append("\n");    		
    	}
    	
    	return buffer.toString() ;
    } 
    /*
     * 윤달여부 text or image set ( 0:양력, 1:음력, 2:음력윤달)
     * -. 기념일등록정보에서만 사용(음력정보는 구분값이 틀림)
     */
    public static String getLeapText( Context ctx, String leap ) {

    	String str = "";

    	if ( leap != null && leap.equals("1")) {
    		str = "(-)";
    	} else if ( leap != null && leap.equals("2")) {
    		str = ComUtil.getStrResource(ctx, R.string.yun) + "(-)";
    	} else {
    		str = "(+)";
    	}
    	
    	return str ;
    }     
    /*
     * 버전 정보 가져오기
     */
    public static String getAppVersion(Context ctx) {
    	String version ="";
		try {
			version = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
    	return version;
    }
    public static int getAppVersionCode(Context ctx) {
    	int code = 0;
		try {
			code = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
    	return code;
    }    
    /*
     * 패키지명가져오기
     */
    public static String getAppPackage(Context ctx) {
    	String name ="";
		try {
			name = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).packageName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
    	return name;
    }    
    /*
     * e-mail validation
     */
    public static Boolean isEmail( Context ctx, String str ) {
    	Boolean ret = false;
    	
    	int pos1 = isInStrPos("@" , str );
    	int pos2 = isInStrPos("." , str);
    	//"@" 존재여부 체크(문장사이에 존재여부 체크)
    	if (  pos1 > 0 && pos1 < str.length() - 1 ) {
    		if ( pos2 > 0 && pos2 < str.length() - 1  ) {
    			ret = true;
    		}
    	}

    	return ret;
    }
    /*
     * 기능제한여부
     */
    public static Boolean isFormallVer(Context ctx) {
    	Boolean ret = true;
    	
		if ( VersionConstant.APPID.equals(VersionConstant.APP_LITE)) {
			ComUtil.showToast(ctx, ComUtil.getStrResource(ctx, R.string.msg_lite_limite));
			ret =  false; 
		}
    	return ret;
    }
    /*
     * sdcard 사용가능여부체크 
     * -.backup : 쓰기가능여부 체크
     * -.restore : readonly라도 가능
     */
     public static boolean hasStorage( boolean requireWriteAccess ) {
    	
    	String state = Environment.getExternalStorageState();
    	
    	if ( Environment.MEDIA_MOUNTED.equals(state)) {
    		//if ( requireWriteAccess ) {
    			//boolean writable = checkFsWritable();
    			return true;
//    		} else {
//    			return true;
//    		}
    	} else if ( !requireWriteAccess && Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
    		return true;
    		
    	}
    	return false;
    } 
   
     /*
      * 개행문자를 다른 문자로 변환 ( \n -> %n)
      */
    public static String changeEnterKeyToOther( String str ) {
//     	String str 
     	if ( str != null && !str.trim().equals("")) {
     		
     		return ComUtil.replaceString(str, "\n", "%n");
     	} else {
     		return str;
     	}
     	
     }    
    /*
     * 다른문자를 개행문자로 변환 ( %n  -> \n)
     */
   public static String changeOtherToEnterKey( String str ) {
//    	String str 
    	if ( str != null && !str.trim().equals("")) {
    		
    		return ComUtil.replaceString(str,  "%n", "\n");
    	} else {
    		return str;
    	}
    	
    }     
    /*
     * 메시지 창
     */
	public static void showToast(Context ctx, CharSequence msg) {
		Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();    
	}	
	public static void showToastLong(Context ctx, CharSequence msg) {
		Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();    
	}		
}