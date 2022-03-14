package com.songw.slp.pnr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.songw.slp.dao.Person;

@Controller
public class pnrController {

	@RequestMapping("/json")
	@ResponseBody()
	public Object printJSON() {
		Person person = new Person();
		
		return person;
	}
	
	
}
