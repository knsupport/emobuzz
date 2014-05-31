package com.emobuzz.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public class OEmbedApiClient {
	String URL_MALFORMED = "MALFORMED";

	String URL_UNKNOWN_HOST = "UNKNOWN_HOST";
	
	private static OEmbedApiClient singleton = null;
	
	public static OEmbedApiClient getInstance(){
		if(singleton==null){
			singleton = new OEmbedApiClient();
		}
		return singleton;
	}
	
	/**
	 * support@myeffecto.com
	 */
//	private static final String EMBEDLY_API_KEY = "1e8db78e97f24779942e4ea5c32345c9";
	
	/**
	 * shinoj@kuchnaya.com
	 */
	//private static final String EMBEDLY_API_KEY = "2f0c34896f5c4c2cabde39b2a7848c40";
	
	/**
	 * kartik@kuchnaya.com
	 */
	private static final String EMBEDLY_API_KEY = "359ad814c50d44bfaa537a52337092ae";
	
	
	private static final String EMBEDLY_URL = "https://api.embed.ly/1/oembed?key="+EMBEDLY_API_KEY;
	public static final String EXTRACT_URL = "http://api.embed.ly/1/extract?key="+EMBEDLY_API_KEY;
	
	public static final String JSON_ERROR_CODE = "error_code";
	public static final String JSON_IMAGE_URL = "thumbnail_url";
	public static final String JSON_TITLE = "title";
	public static final String JSON_DESCRIP = "description";
	
	public static final String EMBED_BLOCKER = "ERROR";
	
	public String urlFetch(String urlStr) throws IOException{

		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(false); 	// Get request
//		connection.setDoOutput(true); 	// post request
		connection.setDoInput(true);	// read response code
		connection.setReadTimeout(0);
		connection.connect();
		InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
		BufferedReader reader = new BufferedReader(inputStream);
		
		StringBuilder data = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			data.append(line);
		}
		reader.close();
		
		return data.toString();
	}
	
	private synchronized String connectToURL(String urlStr, String contentId) {
		String exception = null;
			try {
				return urlFetch(urlStr);
				
			} catch(SocketTimeoutException e){
				System.err.println("SocketTimeoutException , contentId : "+contentId );
				return null;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				exception = e.toString();
				System.err.println("Thumbnail image Fetch Faild, contentId : "+contentId + ", url : "+urlStr);
				System.err.println(" Exception : "+exception);
				return URL_MALFORMED;
			}
			catch (IOException e) {
				e.printStackTrace();
				exception = e.toString();
				System.err.println("Thumbnail image Fetch Faild, contentId : "+contentId + ", url : "+urlStr);
				System.err.println(" Exception : "+exception);
				return URL_UNKNOWN_HOST;
			}
	}
	
	public boolean isValidEmbedData(String embedData){
		if(embedData!=null){
			try {
				JSONObject embedJson = new JSONObject(embedData);
				if(embedJson.has(JSON_ERROR_CODE)){
					return false;
				}
				else{
					return true;
				}
				
			} catch (JSONException e) {}
		}
		
		return false;
	}
	
	public String getEmbedlyDataString(HashMap<String, String> params){
		
		if(params!=null){
			String url = params.get("url");
			String contentId = params.get("contentId");
			params.remove("contentId");
			if(url!=null){
				try 
				{
					url = URLEncoder.encode(url, "UTF-8");
					params.put("url", url);
					
				} catch (UnsupportedEncodingException e) {e.printStackTrace();}
				
				
				StringBuilder paramString = new StringBuilder();
				
				for(Entry<String, String> paramList : params.entrySet()){
					String key = paramList.getKey();
					String value = paramList.getValue();
					paramString.append("&"+key+"=");
					paramString.append(value+"=");
				}
				String dataUrl = EMBEDLY_URL + paramString.substring(0,paramString.length()-1);
				String result = connectToURL(dataUrl, contentId);
				return result;
			}
			else{
				System.err.println("url param Missing");
			}
		}
		else{
			System.err.println("Param cannot be null");
		}
		return null;
	}
	
	public JSONObject getEmbedlyDataJson(HashMap<String, String> params){
		
		String result = getEmbedlyDataString(params);
		try {
			if(result!=null){
				JSONObject jsonData = new JSONObject(result);
				return jsonData;
			}
			else{
				System.err.println("result : NULL");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONObject getEmbedlyDataJson(String url){
		HashMap<String, String> params = getUrlHashMap(url);
		JSONObject jsonData = getEmbedlyDataJson(params);
		return jsonData;
	}
	
	
	private HashMap<String, String> getUrlHashMap(String url){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("url", url);
		return params;
	}
	
	
	public String getEmbedlyDataString(String url){
		try{
			if (url != null) {
				
				HashMap<String, String> params = getUrlHashMap(url);
				
				String data = getEmbedlyDataString(params);
				
				if (data != null) {
				
					if (data.equals(URL_MALFORMED) || data.equals(URL_UNKNOWN_HOST)) {
					
						data = EMBED_BLOCKER;
					
					}
			
				
					return data;
				}
			}
		}
		catch(Exception e){
			System.err.println("Embedly for Website Exceptn : "+e.getStackTrace());
		}
	
		return null;
	}
	
	
	
	public boolean embedfetchAgain(String embed){
		
		if(embed!=null){
			
			try {
				
				JSONObject json = new JSONObject(embed);
				
				if(json.has("error_code")){
					int code = json.getInt("error_code");
					if( code==400 || code==404 ){
						return false; 
					}
					else{
						return true;
					}
				}
				else{
					return false;
				}
			
			} catch (JSONException e) {
			
				if(embed.equals(EMBED_BLOCKER)){
					return false;
				}
				else{
					
					return true;
				}

			}
		
		}
		else{
			
			return true;
		
		}
		
	}
	
}