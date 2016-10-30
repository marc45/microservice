package org.apache.micro.zipkin;

import java.util.HashMap;
import java.util.Map;


/**
 * ZipkinApplication
 *
 */
public class ZipkinApplication {
	public static void main(String[] args) {
		Map<String, String> data = new HashMap<>() ;
		data.put("a", "bcd") ;
		String v = data.putIfAbsent("a", "vvvv") ;
		System.out.println(v);
		
		
	
	}
}
