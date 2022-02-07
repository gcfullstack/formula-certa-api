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

	public static Date formatarStringData(String data, String formato) {
		Date dataFormatada = null;
		if (data != null) {
			SimpleDateFormat format = new SimpleDateFormat(formato);
			try {
				dataFormatada = format.parse(data);
			} catch (Exception e) {
			}
		}
		return dataFormatada;
	}

	public static String getImageFromCodFormaFarmaceutica(Integer codForma) {
		switch (codForma) {
		case 1: // CAPSULAS
			return "https://i.imgur.com/xmlQJhq.png";
		case 2: // CREMES
			return "https://i.imgur.com/nr2Z1B6.png";
		case 4: // SHAMPOO
			return "https://i.imgur.com/iR2AVXo.png";
		case 11: // POMADA
			return "https://i.imgur.com/zwkkMAg.png";
		case 12: // XAROPE
			return "https://i.imgur.com/gEnIFlp.png";
		case 15: // ENVELOPES
			return "https://i.imgur.com/gZGr9nf.png";
		case 16: // BISCOITOS
			return "https://i.imgur.com/Q6oQOJI.png";
		default:
			return "https://i.imgur.com/Q6oQOJI.png";
		}
	}
}
