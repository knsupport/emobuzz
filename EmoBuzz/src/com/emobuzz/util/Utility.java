package com.emobuzz.util;

import java.util.Date;

import com.emobuzz.dto.ContentDetailsDTO;
import com.emobuzz.dto.ContentEmotionsMapDTO;
import com.emobuzz.dto.EmotionTagDTO;
import com.emobuzz.dto.UserDTO;

public class Utility {
	
	private DBFunctions dbFunctions=new DBFunctions();
	private OEmbedApiClient oembed = new OEmbedApiClient();
	private QueueExecution queueExe = new QueueExecution();
	
	public void embedQueue(String contentId,String url){
		
		queueExe.setParameters(new String[]{"a", "cid", "url"});
		queueExe.setValue(new String[]{"embed", contentId, url});
		
		queueExe.fireLocalQueue("tag", QueueExecution.GET);
	}
	
	public void updateEmbedData(String url,String contentIdStr){
		
		String embedData = oembed.getEmbedlyDataString(url);
		
		if(contentIdStr!= null && url!=null){
			Long contenId = Long.parseLong(contentIdStr);
			dbFunctions.updateContent(contenId, embedData);
		}
	}
	
	public String setEmotiCon(UserDTO userDTO,int emoId,String url,String emoName,String title){
			
			EmotionTagDTO emoTagDTO=new EmotionTagDTO(); 
			emoTagDTO.setContentUrl(url);
			emoTagDTO.setUserId(userDTO.getUid());
			emoTagDTO.setEmoId(emoId);
			
			if(!dbFunctions.checkNUpdateEmoTagged(emoTagDTO)){
				ContentDetailsDTO contentDetails = dbFunctions.getContent(url);
			
				if(contentDetails==null){
					contentDetails=new ContentDetailsDTO();
					contentDetails.setMajorEmoCount(1L);
					contentDetails.setMajorEmoId(emoId);
					contentDetails.setMajorEmoName(emoName);
					contentDetails.setTitle(title);
					contentDetails.setTotalFeedbackCount(1L);
					contentDetails.setUrl(url);
					contentDetails=dbFunctions.insertContentDetails(contentDetails);
					
					embedQueue(contentDetails.getCid()+"", contentDetails.getUrl());
				}
				
				long cId=contentDetails.getCid();
				
				ContentEmotionsMapDTO contentEMotionMapDTO= dbFunctions.getNupdateContentEmotionMap(cId, emoId);
				if(contentEMotionMapDTO==null){
					contentEMotionMapDTO=new ContentEmotionsMapDTO();
					contentEMotionMapDTO.setEid(emoId);
					contentEMotionMapDTO.setEmotionName(emoName);
					contentEMotionMapDTO.setTotalEmotionCount(1L);
					contentEMotionMapDTO.setDate(new Date());
					contentEMotionMapDTO.setCid(cId);
					dbFunctions.insertContentEmotionMap(contentEMotionMapDTO);
				}
				
				emoTagDTO.setCategoryId(1);
				emoTagDTO.setContentId(cId);
				emoTagDTO.setContentUrl(contentDetails.getUrl());
				emoTagDTO.setDate(new Date());
				emoTagDTO.setEmoId(emoId);
				emoTagDTO.setTitle(title);
				emoTagDTO.setEmotionName(emoName);
				
				emoTagDTO.setUserId(userDTO.getUid());
				dbFunctions.insertEmotionTaggedContent(emoTagDTO);
				return "Tagged with "+emoName;
			}else{
				return "Already tagged with "+emoName;
			}
			
	}
	
	public UserDTO registerOrLoginUser(String email){
		UserDTO userDTO= dbFunctions.checkIfUserAvailable(email);
		if(userDTO==null){
			userDTO=dbFunctions.insertUser(email);
		}
		return userDTO;
		
	}
	
}
