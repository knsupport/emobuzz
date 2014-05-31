package com.emobuzz.servlet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
@SuppressWarnings("serial")
public class RedirectServlet extends HttpServlet {

	
	@Override
	public void init() throws ServletException {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String action = req.getParameter("a");
		String redirect = req.getParameter("r");
		String ipAddress = req.getRemoteAddr();

		if (redirect != null && !redirect.trim().isEmpty()) {
			if(action!=null){
				if (action.equals("img")) {
					viewOutSideImage(redirect, resp);
				}
				else{
					System.err.println("Unidentified action for redirect servlet");
				}
			}
			else{
				System.err.println("Action found null");
			}
		}
		else{
			System.err.println("Please Provide redirect Url, Value : " + redirect);
		}
	}
	
	public byte[] convertImgToByte(String strUrl) throws IOException{
		byte[] imageBytes = null;
			if(strUrl.startsWith("http")){
				URL url = null;
				URLFetchService urlFetchService = null;
				FetchOptions fetchOptions = null;
				try {
					url = new URL(strUrl);
					urlFetchService = URLFetchServiceFactory.getURLFetchService();
					fetchOptions = FetchOptions.Builder.withDefaults();
					
					// 15 second deadline for urlfetch. Default 5 seconds
					fetchOptions.setDeadline(20D);
					
					HTTPRequest req = new HTTPRequest(url, HTTPMethod.GET, fetchOptions);
					
					HTTPResponse httpResponse = urlFetchService.fetch(req);
					if (httpResponse.getResponseCode() == HttpURLConnection.HTTP_OK) 
					{
						imageBytes = httpResponse.getContent();
					}
				} catch (IOException e) {
					System.out.println("img fetch failed, url : "+strUrl);
				}
			}
		
		return imageBytes;
	}
	
	
	
	
	
	/*private Set<String> bot = null;*/
	
	/*private void setBot(){
		bot = new HashSet<String>();
		bot.add("facebookexternalhit");
	}*/
	
	/*private Set<String> getBotSet(){
		if(bot==null){
			setBot();
		}
		return bot;
		
	}*/
	
	public boolean isValidAgent(String uAgent) {
//		String uAgent = req.getHeader("User-Agent");
		
		if (uAgent != null && (uAgent.toLowerCase().contains("bot") || uAgent.toLowerCase().contains("facebook") ||uAgent.toLowerCase().contains("twitter") )) {
			return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		System.out.println(new RedirectServlet().encode(360));
	} 
	
	public static final String MAP_IMG = "img";
	public static final String MAP_TITLE = "ttl";
	public static final String MAP_DESC = "desc";
	public static final String MAP_TOT_COUNT = "totCt";
	public static final String MAP_EMO_COUNT = "emoCt";
	public static final String MAP_VIEW_COUNT = "vCt";
	public static final String MAP_URL = "url";
	
	
	
	
	public void serverRedirect(String url, HttpServletRequest req, HttpServletResponse resp){
		try {
		if(url!=null){
			resp.setStatus(301);
			resp.sendRedirect(url);
		}
		else{
			resp.setContentType("text/html");
			resp.sendError(204);
			resp.getWriter().println("<h2>Redirection Failed, Wrong param</h2>");
		}
		} catch (IOException e) {System.err.println(e.toString());}
		
	}
	
	
	
	private final static String KEY = "0123456789abcdefghijklmnopqrstuvwxyzABCD";
	private final static int KEY_LEN = KEY.length();

	public Long decode(String s) {
		Long num = 0L;
		for (char ch : s.toCharArray()) {
			num *= KEY_LEN;
			num += KEY.indexOf(ch);
		}
		return num;
	}

	public static String encode(long num) {
		StringBuilder sb = new StringBuilder();
		while (num != 0) {
			sb.append(KEY.charAt((int) (num % KEY_LEN)));
			num /= KEY_LEN;
		}
		return sb.reverse().toString();
	}
	
	private void viewOutSideImage(String strUrl,HttpServletResponse response)
	{
		try 
		{
			byte[] imageBytes=convertImgToByte(strUrl);
			
			if(imageBytes!=null){
				response.setContentType("image/png");
				response.addHeader("Cache-Control","max-age=172800, public");
				response.setDateHeader("Expires", 172800);
				ServletOutputStream oStream = response.getOutputStream();	
				oStream.write(imageBytes);
				oStream.flush();
			    oStream.close();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}