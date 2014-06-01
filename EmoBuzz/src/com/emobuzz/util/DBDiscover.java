package com.emobuzz.util;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.emobuzz.db.ContentDetails;
import com.emobuzz.db.ContentEmotionsMap;
import com.emobuzz.db.EmotionTagged;
import com.emobuzz.dto.ContentDetailsDTO;
import com.google.appengine.api.datastore.Text;

public class DBDiscover {
	
	@SuppressWarnings("unchecked")
	public final List<Long> getDiscoverEmotion(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String queryStr = "select DISTINCT eid from " +ContentEmotionsMap.class.getName();
		Query query =  pm.newQuery(queryStr);
		List<Long> contentList= null;
		try {
			contentList = (List<Long>) query.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pm.setDetachAllOnCommit(true);
		pm.close();
		
		return contentList;
	}
	
	@SuppressWarnings("unchecked")
	public final List<Long> getUserEmotion(String uid){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String queryStr = "select DISTINCT emoId from " +EmotionTagged.class.getName()+" where userId=="+uid;
		Query query =  pm.newQuery(queryStr);
		List<Long> contentList= null;
		try {
			contentList = (List<Long>) query.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pm.setDetachAllOnCommit(true);
		pm.close();
		return contentList;
	}
	
	private static String discoverContent = "select title,majorEmoId,majorEmoCount,embedData,totalFeedbackCount,url,cid";
	private ContentDetailsDTO getDtoForArray(Object[] obj){
		
		ContentDetailsDTO contDto = new ContentDetailsDTO();
		contDto.setTitle(obj[0]!=null?(String) obj[0]:null);
		contDto.setMajorEmoId(obj[1]!=null?(Long) obj[1]:null);
		contDto.setMajorEmoCount(obj[2]!=null?(Long) obj[2]:null);
		Text text = obj[3]!=null?(Text) obj[3]:null;
		if(text!=null){
			contDto.setEmbedData(text.getValue());
		}
		contDto.setTotalFeedbackCount(obj[4]!=null?(Long) obj[4]:null);
		contDto.setUrl(obj[5]!=null?(String) obj[5]:null);
		return contDto;
	}
	

	public final ArrayList<ContentDetailsDTO> getUserDiscoverContent(String uidStr,String emoIdStr){
		ArrayList<ContentDetailsDTO> listDto = new ArrayList<ContentDetailsDTO>() ;
		long userId = Long.parseLong(uidStr);
		int emoId = 0;
		if(emoIdStr!=null){
			emoId = Integer.parseInt(emoIdStr);
		}
		
		List<Object[]> contentIdList = getUserData(userId, emoId);
		
		if(!contentIdList.isEmpty()){
			StringBuffer contentWhere = new StringBuffer();
			
			for (Object[] object : contentIdList) {
				long contentId = (long) object[0];
				contentWhere.append("|| cid=="+contentId);
			}
			
		
			String whereContentId = contentWhere.substring(2,contentWhere.length());
			
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String queryStr  = discoverContent+" from " +ContentDetails.class.getName()+" where "+whereContentId;
			
			Query query =  pm.newQuery(queryStr);
			
			
			List<Object[]> contentList= null;
			try {
				contentList = (List<Object[]>) query.execute();
				for (Object[] obj : contentList) {
					for (Object[] object : contentIdList) {
						if (object[0].equals(obj[6])) {
							obj[1] = object[2];
						}
					}
					ContentDetailsDTO contDto = getDtoForArray(obj);
					listDto.add(contDto);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			pm.setDetachAllOnCommit(true);
			pm.close();
		}
			
		
		
		return listDto;
	}
	
	public List<Object[]> getUserData(long userId,int emoId){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String userString="userId == "+userId;
		
		if(emoId!=0){
			userString=userString+" && emoId =="+emoId;
		}
		Query query = pm.newQuery("select DISTINCT contentId, emotionName, emoId from " +EmotionTagged.class.getName()+ " where "+userString);
		query.setOrdering("emotionTagId desc");
		List<Object[]> followersList = (List<Object[]>) query.execute();
		pm.close();
		
		return followersList;
	}
	
	public final ArrayList<ContentDetailsDTO> getDBDiscoverContent(String emoId){
		ArrayList<ContentDetailsDTO> listDto = new ArrayList<ContentDetailsDTO>() ;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String queryStr = null;
		if(emoId==null){
			queryStr = discoverContent+" from " +ContentDetails.class.getName()+" where majorEmoCount>0";
		}
		else{
			queryStr = discoverContent+" from " +ContentDetails.class.getName()+" where majorEmoCount>0 && majorEmoName=='"+emoId+"'";
		}
		Query query =  pm.newQuery(queryStr);
		query.setOrdering("cid desc");
		List<Object[]> contentList= null;
		try {
			contentList = (List<Object[]>) query.execute();
			for (Object[] obj : contentList) {
				ContentDetailsDTO contDto = getDtoForArray(obj);
				listDto.add(contDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		pm.setDetachAllOnCommit(true);
		pm.close();
		
		return listDto;
	}
}
