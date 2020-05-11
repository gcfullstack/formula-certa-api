package corp.gruposfa.novo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MethodsUtils {
	
	public String retirarCaracteresEspeciais(String str) {	
		String strFormatada = "";
		for (int i=0; i < str.length(); i++) {
			char charr = str.charAt(i);
			if (charr == 'á' || charr == 'à' || charr == 'ã' || charr == 'â') {
				strFormatada = strFormatada.concat("a");
			}else if (charr == 'é' || charr == 'è' || charr == 'ê') {
				strFormatada = strFormatada.concat("e");
			}else if (charr == 'í' || charr == 'ì' || charr == 'î') {
				strFormatada = strFormatada.concat("i");
			}else if (charr == 'ó' || charr == 'ò' || charr == 'õ' || charr == 'ô') {
				strFormatada = strFormatada.concat("o");
			}else if (charr == 'ú' || charr == 'ù' || charr == 'û') {
				strFormatada = strFormatada.concat("u");
			}else if (charr == 'ç') {
				strFormatada = strFormatada.concat("c");
			}else if (!str.substring(i, i+1).matches("[^A-Za-z0-9 ]")){
				strFormatada = strFormatada.concat(str.substring(i, i+1));
			}
		}
		
		return strFormatada;
	}

	public static void saveToFile(InputStream inStream, String target) throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}
	

	public static boolean deleteFile(String target) throws IOException {
		File file = new File(target); 
		return file.delete();  
	}
	
	public static String capitalizarString(String sentence) {
        String words[] = sentence.replaceAll("\\s+", " ").trim().split(" ");
        String newSentence = "";
        for (String word : words) {
            for (int i = 0; i < word.length(); i++)
                newSentence = newSentence + ((i == 0) ? word.substring(i, i + 1).toUpperCase(): 
                    (i != word.length() - 1) ? word.substring(i, i + 1).toLowerCase() : word.substring(i, i + 1).toLowerCase().toLowerCase() + " ");
        }

        return newSentence;
    }
	
	/**
	 * Método para formatar um Date em uma String
	 */
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
	
	/**
	 * Método para formatar uma string em Date
	 */
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

}
