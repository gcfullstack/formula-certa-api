package corp.gruposfa.novo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
	
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
}
