package com.alife.qrcodegenerator.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class ApplicationInformationController {

	
	@RequestMapping(value="/ping",method={RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,String> ping(){
		Map<String, String> map = new HashMap<>();
		map.put("status", "Working");
		return map;
	}
	
}
