package com.songw.slp.restController;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.songw.slp.HomeController;
import com.songw.slp.common.StatusEnum;
import com.songw.slp.dao.AuthorRepository;
import com.songw.slp.model.Author;
import com.songw.slp.model.Data;
import com.songw.slp.model.Journey;
import com.songw.slp.model.Message;
import com.songw.slp.model.Status;
import com.songw.slp.model.findAllTutorials;
import com.songw.slp.validation.tutorial;

@Controller
public class RestController {

	private AuthorRepository authorRepository;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	public RestController(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	@GetMapping("/journeys")
	@ResponseBody
	public ResponseEntity<Message> getJourney () throws Exception {
		Map<String, String[]> map = new HashMap<>();
		
		String[] array = new String[3];
		
		for (int i = 0; i < 3; i++) {
			array[i] = String.valueOf(i);
		}
		
		map.put("passengerKeys", array);
		

		Message message = new Message();
		HttpHeaders headers= new HttpHeaders();

		List<Journey> journeyList = authorRepository.getJourneyList();
		message.setStatus(StatusEnum.NOT_FOUND);
		message.setMessage("성공 코드");
		message.setData(map);
		
		return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
	}	
	
	@GetMapping("/author/{id}")
	@ResponseBody
	public ResponseEntity<Message> getAuthor (@PathVariable("id") int id) throws Exception {
		
		Author author = authorRepository.findAuthorById(id);
		
		if(author.getStatus().equals(Status.Confirmed)) {
			
			System.out.println("comfiremd!!");
			
		}
		
		Message message = new Message();
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		message.setStatus(StatusEnum.NOT_FOUND);
		message.setMessage("성공 코드");
		message.setData(author);
		
		return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/author")
	@ResponseBody
	public ResponseEntity<Message> getAuthorList () throws Exception {
		List<Map<String,String>> authorList = new LinkedList<Map<String,String>>();
		authorList = authorRepository.findAuthorList();
		
		authorList.forEach( x -> {
			
			ObjectMapper objectMapper = new ObjectMapper();			
			
			Author author = objectMapper.convertValue(x.get("authorVo"), Author.class);
			
			logger.info("id : " + author.getId());
			logger.info("age : " + author.getAge());
			logger.info("name : " + author.getName());
		});

		Message message = new Message();
		HttpHeaders headers= new HttpHeaders();

		headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));

		
		message.setStatus(StatusEnum.OK);
		message.setMessage("성공 코드");
		message.setData("");
		
		return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
		
	}	
	
	
	@RequestMapping(value = "/author/{idx}",  method = RequestMethod.DELETE,  consumes = MediaType.APPLICATION_JSON_VALUE)
	public Author createAuthor () {	
		logger.info("@PatchMapping");	
		return null;
	}

	@RequestMapping(value="/tutorials/{customerNumber}", method=RequestMethod.POST)
	@ResponseBody
	@Validated
	public ResponseEntity<Message> getTutorials (@PathVariable("customerNumber") @tutorial int customerNumber) throws Exception {
		
		Message message = new Message();
		HttpHeaders headers= new HttpHeaders();
		String query = "";
		Data data = new Data();
        Gson gson = new Gson();

		try {
	
			// customerNumber에 해당하는 전체 리스트
			query = "{\"query\":\"{\\n  findAllTutorials(customerNumber:\\\""+ customerNumber +"\\\"){\\n    id\\n    title\\n    description\\n    author{\\n      id\\n      name\\n    }\\n contact{\\n      id\\n      customerNumber\\n email\\n phoneNumber\\n   }\\n  }\\n}\",\"variables\":{}}";
			
			data = gson.fromJson(HeaderPostMethod(query).toString(), Data.class);			
	        findAllTutorials findAllTutorials = data.getData();
	        
	        findAllTutorials.getFindAllTutorials().stream().forEach(tutorial -> 
	        	System.out.println("id : " + tutorial.getId())
	        );
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		message.setStatus(StatusEnum.OK);
		message.setMessage("성공 코드");
		message.setData(data);
		
		return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
	}
	
	private String HeaderPostMethod(String input) throws Exception {

		HttpURLConnection conn = null;
		StringBuilder sb = new StringBuilder();
		
		try {
		
			URL url = new URL("http://localhost:8080/apis/graphql");
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true);
	        
	        OutputStream os= conn.getOutputStream();
	        os.write(input.getBytes("euc-kr"));
	        os.flush();
	        os.close();
	        
	
	        int responseCode = conn.getResponseCode();
	        if (responseCode == 400) {
	            System.out.println("400 ERROR");
	        } else if (responseCode == 401) {
	            System.out.println("401 ERROR");
	        } else if (responseCode == 500) {
	            System.out.println("500:: 서버 에러, 문의 필요");
	        } else {
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	        }       
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        
		return sb.toString(); 
	}


    public String getFileContent(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        StringBuilder sb = new StringBuilder("");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append(line).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
	
}
