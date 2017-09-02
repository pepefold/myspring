package com.example.demo;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class MainController {
	
	@GetMapping(path="/") // Map ONLY GET Requests
	public ResponseEntity<String> printHeaders (@RequestHeader MultiValueMap<String, String> headers) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		StringBuilder builder = new StringBuilder();
		
		// output request headers
		Set<Entry<String, List<String>>> set = headers.entrySet();
		populateHeaders(builder, "Request Headers", set);
		
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
