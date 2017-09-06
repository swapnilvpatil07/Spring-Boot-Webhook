package com.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.model.StockInfo;
import com.service.util.Utility;

@RestController
public class Controller {
	@RequestMapping(value = "/webhook/{test}", method = RequestMethod.GET)
	public ResponseEntity<?> testCall(@PathVariable("test") String test) {
		System.out.println("Service Call..");
		return new ResponseEntity<String>(test, HttpStatus.OK);
	}

	@RequestMapping(value = "/webhook", method = RequestMethod.POST)
	public @ResponseBody WebhookResponse getStockInfo(@RequestBody String body) {
		System.out.println("Service Called..");
		StockInfo info = new Utility().getStockInfo("infosys");
		return new WebhookResponse("Showing stock price: " + info.getL(), "Stock Price");
	}
}
