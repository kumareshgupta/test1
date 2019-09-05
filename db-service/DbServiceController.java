package com.demo;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

@RestController
public class DbServiceController {
	 static MongoClient  mongoClient ;
	 static MongoDatabase mongoDatabase;
	 private static MongoCollection<Document> mongoCollection;
	static {
		 mongoClient = new MongoClient( "localhost" , 27017 );
		 mongoDatabase = mongoClient.getDatabase("empdb"); 
		 mongoCollection = mongoDatabase.getCollection("mycol");
	}
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/empinfo")
	public String getEmployee() {
		String response = restTemplate.exchange("http://localhost:8666/info", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}).getBody();
			
		return response;
	}
	
	@RequestMapping("/employees")
	public String getEmployees(){
		FindIterable<Document> iterDoc = mongoCollection.find();
		/*
		 * Iterator iterator = iterDoc.iterator(); List<Employee> employees = new
		 * ArrayList<>();
		 * 
		 * while(iterator.hasNext()) { employees.add((Employee) iterator.next()); }
		 * System.out.println(employees.size()); return employees;
		 */
		
		return JSON.serialize(iterDoc);
		//return new BasicDBObject().toJson(iterDoc);
	}
	
	@Bean
	//@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}	
}
