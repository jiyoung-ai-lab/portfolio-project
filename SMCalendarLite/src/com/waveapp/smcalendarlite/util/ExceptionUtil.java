package com.waveapp.smcalendarlite.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import android.content.Context;
import android.os.Environment;

/*
 * 로그파일 생성 및 서버로 보내기
 */
public class ExceptionUtil {
	
	Context mCtx ;
	private static String m_strLogFileFolderPath = ""; 
	private static String m_strLogFileName = "filelog.txt"; 
	
	public ExceptionUtil(Context ctx) {
        this.mCtx = ctx;
    }	
	
	public static void initialize(Context ctx) {
		
		// SD 카드가 있으면 SD 카드 경로, 없을 경우는 그냥 Root 
		boolean bSDCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); 
		if (bSDCardExist == true) {
			m_strLogFileFolderPath = Environment.getExternalStorageDirectory().getAbsolutePath(); 

		} else {
			m_strLogFileFolderPath = ""; 

		}

	}
		
	public static void setFileName(String strFileName) {
		m_strLogFileName = strFileName; 

	}

	public static void reset() {
		File file = new File(m_strLogFileFolderPath + "/" + m_strLogFileName); 
		file.delete(); 
		write("SnowFileLog.reset()"); 

	}
	public static void write(String strMessage, Object ... args) {
		String _strMessage = strMessage; 
		if ( (strMessage == null) || (strMessage.length() == 0) ) return;

		if (args.length != 0) {
			_strMessage = String.format(strMessage, args); 
		}
		
		_strMessage = getCurrentTime() + " " + _strMessage + "\n"; 
		File file = new File(m_strLogFileFolderPath + "/" + m_strLogFileName); 
		FileOutputStream fos = null; 

		try {
			fos = new FileOutputStream(file, true); 
			if (fos != null) 
			{   
				fos.write(_strMessage.getBytes()); 
			} 

		} catch(Exception e) {           
			e.printStackTrace(); 
		} finally {          
			try   {               
				if (fos != null)   
				{             
					fos.close();       
				}       
			} catch(Exception e) {  
				e.printStackTrace(); 
			}   
		} 

	}
	private static String getCurrentTime() 	{ 
		Calendar calendar = Calendar.getInstance(); 
		String strTime = String.format("%d-%d-%d %d:%d:%d", calendar.get(Calendar.YEAR), 
		calendar.get(Calendar.MONTH) + 1,                                                            
		calendar.get(Calendar.DAY_OF_MONTH),                           
		calendar.get(Calendar.HOUR_OF_DAY),                           
		calendar.get(Calendar.MINUTE),                             
		calendar.get(Calendar.SECOND)); 
		return strTime; 
	} 

}
