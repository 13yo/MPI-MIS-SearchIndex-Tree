package de.mpg.mis.neuesbibliothekssystem.misTree;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Boot {

    /**
     * @param args
     */
    public static void main(String[] args) {
	ApplicationContext ctx = new ClassPathXmlApplicationContext(
		"classpath*:META-INF/applicationContext.xml");
	Start s = (Start) ctx.getBean("start");
	try {
	    s.demo();
	    System.exit(0);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
