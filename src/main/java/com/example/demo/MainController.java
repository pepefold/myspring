package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Disable cache
 * Cache-Control: no-cache, no-store, must-revalidate
 * Pragma: no-cache
 * Expires: 0
 * @author Dali
 *
 */
@RestController
@RequestMapping(path="/rest")
public class MainController {
	@Autowired
	private KeyInfoRepository repository;
	
	@GetMapping(path="/makes", produces = "application/json") // Map ONLY GET Requests
	public List<KeyInfo> getMakes () {
		List<KeyInfo> keys = this.repository.findDistinctMakes();
		return keys;
	}
	
	@GetMapping(path="/models", produces = "application/json") // Map ONLY GET Requests
	public List<KeyInfo> getModels (@RequestParam String make) {
		List<KeyInfo> keys = this.repository.findModelsForMake(make);
		return keys;
	}
	
	@GetMapping(path="/keyInfo", produces = "application/json") // Map ONLY GET Requests
	public List<KeyInfo> getKeyInfo (@RequestParam String make, @RequestParam String model) {
		List<KeyInfo> findByMakeAndByModel = repository.
				findInfo(make, model);
		return findByMakeAndByModel;
	}
	
	@GetMapping(path="/headers") // Map ONLY GET Requests
	public ResponseEntity<String> printHeaders (@RequestHeader MultiValueMap<String, String> headers) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		StringBuilder builder = new StringBuilder();
		
		// output request headers
		ResponseEntity<String> response = new ResponseEntity<String>(builder.toString(),
				HttpStatus.OK);
		return response;
	}
	
	/**
	 * Populate set of headers
	 * 
	 * @param builder the string builder to be populated
	 * 
	 * @param headerMessage the header of the output message, i.e. request or response
	 * 
	 * @param set the set of header values
	 */
	private void populateHeaders(StringBuilder builder, String headerMessage,
			Set<Entry<String, List<String>>> set) {
		builder.append(headerMessage).append("<br><br>");
		
		Iterator<Entry<String, List<String>>> iterator = set.iterator();
		
		while (iterator.hasNext()) {
			Entry<String, List<String>> next = iterator.next();
			List<String> value = next.getValue();
		
			for (String s : value) {
				builder.append(next.getKey()).append(" : ").append(s).append("<br>");
			}
		}
		
	}
}
