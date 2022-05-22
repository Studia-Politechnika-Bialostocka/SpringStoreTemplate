package pawww.example.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import javax.servlet.ServletContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

@SpringBootApplication
public class StoreApplication implements ServletContextAware {
	private ServletContext servletContext;
	@Autowired
	private Environment env;

	public StoreApplication(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}



	@Override
	public void setServletContext(ServletContext servletContext) {
		double minVal=Double.parseDouble(env.getProperty("server.context-parameters.val"));
		System.out.println(env.getProperty("server.context-parameters.val"));
		servletContext.setAttribute("minimumOrderValue", minVal);

		String promoDateStr=env.getProperty("server.context-parameters.promo_date");
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

		Date promoDate = null;
		try {
			promoDate = formatter.parse(promoDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(promoDate);
		servletContext.setAttribute("promoDate", promoDate);
	}


}
