package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	/**
	 * Serialize object to json
	 * 
	 * @param value an object to be serialized
	 */
	public static String toJSON(Object value) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return value.toString();
		}
	}
	/**
	 * Disable cache by adding certain headers
	 * 
	 * @param response the reponse for which headers are added
	 */
	public static void disableCache(ResponseEntity<?> response) {
		HttpHeaders headers = response.getHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
	}
}
