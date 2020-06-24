package corp.gruposfa.novo.utils;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
 
@Component
public class EmailHtmlSender {
 
    @Autowired
    private EmailSender emailSender;
 
    @Autowired
    private TemplateEngine templateEngine;
 
    public EmailStatus send(String to, String subject, String templateName, Context context) {
    	try {
        	templateEngine.addDialect(new Java8TimeDialect());
		} catch (Exception e) {}
        String body = templateEngine.process(templateName, context);
        return emailSender.sendHtml(to, subject, body);
    }
}