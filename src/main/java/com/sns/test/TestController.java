package com.sns.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@ResponseBody
	@RequestMapping("/test/1")
	public String ex01() {
		
		return "hello world";
		
	}
	
	@ResponseBody
	@RequestMapping("/test/2")
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("aaaa", 1234);
		map.put("bbb", 1234);
		map.put("ccc", 1234);
		map.put("ddd", 1234);
	
	return map;
	}
	
	@RequestMapping("/test/3")
	public String test3() {
		
		return "test/test";
	}
	
}
