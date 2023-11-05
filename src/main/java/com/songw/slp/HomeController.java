package com.songw.slp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
        Runnable d = new CheckThread();
        Runnable d1 = new CheckThread();
        Runnable d2 = new CheckThread();
        Runnable d3 = new CheckThread();

        Thread thread = new Thread(d);
        Thread thread1 = new Thread(d1);
        Thread thread2 = new Thread(d2);
        Thread thread3 = new Thread(d3);

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();

		
		return "home";
	}
	
}

class CheckThread implements Runnable{

	private static int a = 1;
	

    @Override
    public void run() {
        try{
        	
        	
    		List<Integer> list = new ArrayList<>();
    		list.add(1);
    		list.add(2);
    		list.add(3);

    		
    		a = 1;

    		list.stream()
    		.forEach(v->{
    		    a++;
    		});
    		System.out.println(a); // output : 6

        }catch (Exception e){
        }

    }
}