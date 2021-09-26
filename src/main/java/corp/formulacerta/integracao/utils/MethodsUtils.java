package corp.formulacerta.integracao.utils;

import java.util.Calendar;
import java.util.Date;

public class MethodsUtils {
	
	public static Date addDaysInDate(Date data, Integer days) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

}
