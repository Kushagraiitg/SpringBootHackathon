package com.example.demo;
//import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
//import java.net.http.HttpResponse;
import org.json.JSONObject;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class FetchStockData extends Object {
	static String stkName = "AAPL";
	@SuppressWarnings("unchecked")
	public static float getStkValue(String stkName) throws Exception {
		// TODO Auto-generated method stub
		// Host url
//		this.stkName = stkName;
		if(!StocksExceptionHandler.main(stkName)) {
			return -1;
		}
		String host = "https://yahoo-finance15.p.rapidapi.com";
		String charset = "UTF-8";
		// Headers for a request
		String x_rapidapi_host = "yahoo-finance15.p.rapidapi.com";
		String x_rapidapi_key = "7c297b1000mshd2497071a5e6952p10aad1jsn16806ff161eb";// Type here your key
		// Params
		String s = "AAPL";
		// Format query for preventing encoding problems
		String query = String.format("s=%s", URLEncoder.encode(s, charset));
		com.mashape.unirest.http.HttpResponse<JsonNode> response = null;
		try {
			response = Unirest
					.get("https://yahoo-finance15.p.rapidapi.com/api/yahoo/qu/quote/" + stkName + "/financial-data")
					.header("x-rapidapi-host", "yahoo-finance15.p.rapidapi.com")
					.header("x-rapidapi-key", "7c297b1000mshd2497071a5e6952p10aad1jsn16806ff161eb").asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = response.getBody().getObject();
		String str = ("" + ((JSONObject) ((JSONObject) obj.get("financialData")).get("currentPrice")).get("raw"));
//		if (str.equals("")) {
//			return 0;
//		}
//		System.out.println(Float.parseFloat(
//				"" + ((JSONObject) ((JSONObject) obj.get("financialData")).get("currentPrice")).get("raw")));
		return Float.parseFloat("" + ((JSONObject) ((JSONObject) obj.get("financialData")).get("currentPrice")).get("raw"));
	}
}