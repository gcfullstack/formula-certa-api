package corp.formulacerta.integracao.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    	
	String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd' 'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSSZ","yyyy-MM-dd" };
    	
        SimpleDateFormat format;
        String date = jsonParser.getText();
        ParseException exception = null;
        
        for (int i=0; i < formats.length; i++) {
        	format = new SimpleDateFormat(formats[i]);
	        try {
	            return format.parse(date);
	        } catch (ParseException e) {
	        	exception = e;
	        }
        }
        
        throw new RuntimeException(exception);
    }

}