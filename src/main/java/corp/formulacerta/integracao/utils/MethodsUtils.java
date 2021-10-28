package corp.formulacerta.integracao.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MethodsUtils {

	public static Date addDaysInDate(Date data, Integer days) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	public static String formatarDataString(Date data, String formato) {
		String dataFormatada = null;
		if (data != null) {
			SimpleDateFormat format = new SimpleDateFormat(formato);
			try {
				dataFormatada = format.format(data);
			} catch (Exception e) {
			}
		}
		return dataFormatada;
	}

}
