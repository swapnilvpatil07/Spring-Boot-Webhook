package com.service.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.model.StockInfo;

public class Utility {

	public static String readQuotes(String org) {
		// TODO Auto-generated method stub
		HashMap<String, String> list = new HashMap<>();
		String csvFile = "resources/quotes.csv";
		String cvsSplitBy = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] company = line.split(cvsSplitBy);
				list.put(company[0], company[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(list);
		return getStockCd(list, org);
	}

	public static String getStockCd(Map list, String org) {
		String stkCd = "";
		int cnt = 0;
		Iterator<Map.Entry<String, String>> it = list.entrySet().iterator();
		while (it.hasNext() && cnt == 0) {
			Map.Entry<String, String> pair = it.next();
			if (pair.getKey().toString().contains(org)) {
				System.out.println("Name: " + pair.getKey());
				stkCd = pair.getValue();
				cnt++;
			}
		}
		System.out.println("Stock Code: " + stkCd);
		return stkCd;
	}

	public StockInfo getStockInfo(String CmpNm) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		StockInfo stockInfo = null;
		String stkCode = readQuotes(CmpNm);
		stockInfo = new StockInfo();
		stockInfo.setL("2352");
		/*StringBuilder url = new StringBuilder()
				.append("http://finance.google.co.uk/finance/info?client=ig&q=NSE:" + stkCode.toUpperCase());
		String response = "";
		// Call service
		try {
			response = restTemplate.getForObject(url.toString(), String.class);
			response = response.trim().replace("//", "").replaceAll("\\[", "").replaceAll("\\]", "");
			//ObjectMapper mapper = new ObjectMapper();
			//stockInfo = mapper.readValue(response, StockInfo.class);
		} catch (Exception e) {
			// TODO: handle exception
			response = "";
		}*/
		//System.out.println("RES: " + response);
		return stockInfo;
	}
}
