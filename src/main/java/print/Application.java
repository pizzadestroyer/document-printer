package print;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {
	
	public static void main(String[] args) {
		try {
			ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
			DocumentPrinter printer = context.getBean(DocumentPrinter.class);
			printer.printDocument(new TestData("asd", "bas", 3, BigDecimal.TEN));
			((ConfigurableApplicationContext)context).close();	
		} catch (PrinterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
} 
