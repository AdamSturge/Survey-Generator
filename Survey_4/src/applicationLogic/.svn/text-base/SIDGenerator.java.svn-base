package applicationLogic;

import java.util.Calendar;

public class SIDGenerator {

	public long generateSID(String AID){
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		long mill = cal.get(Calendar.MILLISECOND);
		
		
		long SID = day+month+year+hour+minute+second+mill;
		
		char c;
		int j;
		for(int i = 0; i < AID.length(); ++i){
			c = AID.charAt(i);
			j = (int)c;
			SID += j;
		}
		
		return SID;
		
	}
}
