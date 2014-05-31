package com.emobuzz;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.emobuzz.dto.ContentDetailsDTO;
import com.emobuzz.emotion.EmotionHandler;
import com.emobuzz.util.DBDiscover;
import com.emobuzz.util.OEmbedApiClient;

public class DiscoverClient {
	
	private DBDiscover dbDiscover = new DBDiscover();
	
	
	
	public String getDiscoverContentHtml(String emoId,String uid){
		
		ArrayList<ContentDetailsDTO> listDto = null;
		boolean isUser = false;
		if(uid==null){
			listDto = dbDiscover.getDBDiscoverContent(emoId);
		}
		else{
			isUser = true;
			if(emoId!=null)
				emoId = EmotionHandler.getEmotionId(emoId);
			
			listDto = dbDiscover.getUserDiscoverContent(uid, emoId);
		}
		
		
		
		StringBuffer dataHtml = new StringBuffer();
		for (ContentDetailsDTO contentDetailsDTO : listDto) {
			String embed = contentDetailsDTO.getEmbedData();
			String img = null;
			if(embed!=null){
				try {
					JSONObject json = new JSONObject(embed);
					img = json.getString(OEmbedApiClient.JSON_IMAGE_URL);
				} catch (JSONException e) {e.printStackTrace();}
				
			}
			
			if(img==null)img="/img/thumb.png";
			dataHtml.append(getTrendDataHtml(contentDetailsDTO.getUrl(), img, contentDetailsDTO.getTitle(), contentDetailsDTO.getMajorEmoId()+"", contentDetailsDTO.getMajorEmoCount()+"",isUser));
		}
		
		return dataHtml.toString();
	}
	
	public ArrayList<String> getAllEmotionList(String uid,String activeEmo){
		ArrayList<String> dataList = new ArrayList<String>();
		
		List<Long> data = null;
		String uidParam = "";
				
		if(uid==null){
			data = dbDiscover.getDiscoverEmotion();
		}
		else{
			data = dbDiscover.getUserEmotion(uid);
			uidParam = "&u="+uid;
		}
		
		
		StringBuffer desktopHtml = new StringBuffer();
		StringBuffer mobHtml = new StringBuffer();
		
		
		
		for (Long id : data) {
			if(id!=null){
				String emoId = id.toString();
				String emotionName = EmotionHandler.getEmotionName(emoId);
				
				if(emotionName.equals(activeEmo)){
					desktopHtml.append(emoListHtml(true, emoId, emotionName,uidParam));
					mobHtml.append("<option value=\"/discover.jsp?e="+emotionName+uidParam+"\" selected>"+emotionName+"</option>");
				}
				else{
					desktopHtml.append(emoListHtml(false, emoId, emotionName,uidParam));
					mobHtml.append("<option value=\"/discover.jsp?e="+emotionName+uidParam+"\">"+emotionName+"</option>");
				}
				
			}
		}
		
		dataList.add(desktopHtml.toString());
		dataList.add(mobHtml.toString());
		
		return dataList;
	}
	
	public String emoListHtml(boolean active,String emoId,String emoName,String uid){
		StringBuffer dataHtml = new StringBuffer();
		
		String emoSrc=EmotionHandler.getEmotionImgBase64(emoId);
		String navActive = null;
		if(active){
			navActive = "emo-tag nav-active";
		}
		else{
			navActive = "";
		}
		dataHtml.append("<li>");
		
		dataHtml.append("<a class=\""+navActive+"\" href=\"/discover.jsp?e="+emoName+uid+"\"> "+emoName);
		dataHtml.append("<img class=\"emoji small pull-right side-emo-align\" src=\""+emoSrc+"\">");
		dataHtml.append("</a>");
		dataHtml.append("</li>");
		
		return dataHtml.toString();
	}
	
	
	
	public String getTrendDataHtml(String url,String imgUrl,String title,String emoId,String count,boolean isUser){

		String title_short = title.length() > 37 ? title.substring(0,37)+" ..." : title;
		
		String emoSrc = EmotionHandler.getEmotionImgBase64(emoId);
		String emoName = EmotionHandler.getEmotionName(emoId);
		
		StringBuffer dataHtml = new StringBuffer();
		
		dataHtml.append("<div  class=\"item-c w1\">");
		dataHtml.append("<a target=\"_blank\" href=\""+url+"\">");
		
		dataHtml.append("<div class=\"content_thumb\">");
		dataHtml.append("<img onerror=\"this.src='/img/thumb.png'\" title=\""+title+"\" alt=\""+title+"\" class=\"web_thumb\" src=\"/re?a=img&r="+imgUrl+"\" data-src=\""+imgUrl+"\">");
		dataHtml.append("</div>");
		
		dataHtml.append("<div class=\"example-title\">");
		dataHtml.append("<span class=\"inline-table title-float\">");
		dataHtml.append("<div class=\"itemhead\" title=\""+title+"\">"+title_short+"</div>");
		dataHtml.append("</span>");
		dataHtml.append("</div>");
		
		dataHtml.append("<span class=\"topbar\"><span class=\"side-emo\"> ");
		dataHtml.append("<img class=\"emoji\" src=\""+emoSrc+"\" id=\""+emoId+"\">");
		dataHtml.append("</span>");
		
		dataHtml.append("<span class=\"side-emo side-emo-txt\"> "+emoName);
		if(!isUser){
			dataHtml.append("<span>&nbsp;("+count+")</span>");
		}
		
		dataHtml.append("</span>");
		
		dataHtml.append("</a>");
		dataHtml.append("</div>");
		
		
		return dataHtml.toString();
	}
}
