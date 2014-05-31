package com.emobuzz.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.commons.beanutils.BeanUtils;

import com.emobuzz.db.ContentDetails;
import com.emobuzz.db.ContentEmotionsMap;
import com.emobuzz.db.EmotionTagged;
import com.emobuzz.db.Followers;
import com.emobuzz.db.User;
import com.emobuzz.dto.ContentDetailsDTO;
import com.emobuzz.dto.ContentEmotionsMapDTO;
import com.emobuzz.dto.EmotionTagDTO;
import com.emobuzz.dto.FollowerDTO;
import com.emobuzz.dto.UserDTO;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DBFunctions {
	
	
	private PersistenceManager pm;
	public void createUser(){
		pm=PMF.get().getPersistenceManager();
		
	}
	
	public synchronized Object selection(String query,PersistenceManager pm)
	{
		Object object = pm.newQuery(query).execute();
		return object;
	}
	
	public synchronized Object selection(String query)
	{
		pm = PMF.get().getPersistenceManager();
		Object object = pm.newQuery(query).execute();
		return object;
	}
	
	public synchronized void createEntity(Object object)
	{
		pm = PMF.get().getPersistenceManager();
		pm.makePersistent(object);
		if(!pm.isClosed()){
			pm.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public synchronized long generatepk(String queryString, long idToStart, String coloumnName)
	{
		
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try
			{
				
				Query query = pm.newQuery(queryString +" where "+coloumnName+" != null ORDER BY "+coloumnName+" DESC");
				query.setRange(0, 2);
				
				List<Long> user1 = (List<Long>) query.execute();
				if (user1.size() >= 1)
				{
					long idTo=  user1.get(0)+1;
			
					pm.close();
					return idTo;
				}
	
				else
				{
					pm.close();
			
					return idToStart;
				}
			}
			catch (Exception e)
			{
				pm.close();
				
				return idToStart;
			}
		
	}
	
	public UserDTO insertUser(String userMail){
		
		UserDTO userDTO=new UserDTO();
		User user=new User();
		Long id=1L;
		
		id=generatepk("select uid from " + User.class.getName(), id, "uid");
		user.setUid(id);
		user.setEmail(userMail);
		
		userDTO.setEmail(userMail);
		userDTO.setUid(id);
		
		Key key = KeyFactory.createKey(User.class.getSimpleName(), id);
		user.setKey(key);
		
		createEntity(user);
		return userDTO;
	}
	
	
	/**
	 * Check if user is already available
	 * @param email
	 * @return
	 */
	public UserDTO checkIfUserAvailable(String email){
		UserDTO userDTO=null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " +User.class.getName()+ " where email == '" + email + "'";
		List<User> users=(List<User>)selection(query,pm);
		if(users!=null && users.size()>0){
			User user=  users.get(0);
			userDTO=new UserDTO();
			userDTO.setEmail(email);
			userDTO.setUid(user.getUid());
		}
		pm.close();
		return userDTO;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public boolean checkContent(String url){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " +ContentDetails.class.getName()+ " where url == '" + url + "'";
		List<ContentDetails> tagsNames=(List<ContentDetails>)selection(query,pm);
		if(tagsNames!=null && tagsNames.size()>0){
			return true;
		}
		pm.close();
		return false;
	}	

	/**
	 * Get Url based Content
	 * @param url
	 * @return
	 */
	public ContentDetailsDTO getContent(String url){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ContentDetailsDTO contentDetailsDTO=null;
		String query = "select from " +ContentDetails.class.getName()+ " where url == '" + url + "'";
		List<ContentDetails> contentList=(List<ContentDetails>)selection(query,pm);
		if(contentList!=null && contentList.size()>0){
			
			contentDetailsDTO=new ContentDetailsDTO();
			ContentDetails contentDetails= (ContentDetails)contentList.get(0);
			contentDetails.setTotalFeedbackCount(contentDetails.getTotalFeedbackCount()+1L);
			try {
				BeanUtils.copyProperties(contentDetailsDTO, contentDetails);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			pm.makePersistent(contentDetails);
		}
		pm.close();
		return contentDetailsDTO;
	}	
	
	
	
	public ContentEmotionsMapDTO getNupdateContentEmotionMap(Long contentId, int emoId){
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ContentEmotionsMapDTO contentEmotionMapDTO=null;
		String query = "select from " +ContentEmotionsMap.class.getName()+ " where cid == " + contentId + " && eid == "+emoId;
		List<ContentEmotionsMap> contentList=(List<ContentEmotionsMap>)selection(query,pm);
		if(contentList!=null && contentList.size()>0){
			
			contentEmotionMapDTO=new ContentEmotionsMapDTO();
			ContentEmotionsMap contentDetails= (ContentEmotionsMap)contentList.get(0);
			
			long ttlEmotionCount=contentDetails.getTotalEmotionCount();
			contentDetails.setTotalEmotionCount(ttlEmotionCount+1L);
			
			try {
				BeanUtils.copyProperties(contentEmotionMapDTO, contentDetails);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			pm.makePersistent(contentDetails);
		}
		pm.close();
		return contentEmotionMapDTO;
		
	}
	
	
	public void updateContentEmotionMap(ContentEmotionsMapDTO contentEmotionMapDTO){
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ContentEmotionsMap contentEmoMap= pm.getObjectById(ContentEmotionsMap.class, contentEmotionMapDTO.getContentEmotionMapId());
		if(contentEmoMap!=null){
			long ttlEmoCount=contentEmoMap.getTotalEmotionCount();
			contentEmoMap.setTotalEmotionCount(ttlEmoCount+1L);
			pm.makePersistent(contentEmoMap);
		}
		pm.close();
	}
	
	
	public ContentEmotionsMapDTO insertContentEmotionMap(Long contentId, int emoId){
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ContentEmotionsMapDTO contentEmotionMapDTO=null;
		String query = "select from " +ContentEmotionsMap.class.getName()+ " where cid == " + contentId + " && eid == "+emoId;
		List<ContentEmotionsMap> contentList=(List<ContentEmotionsMap>)selection(query,pm);
		if(contentList!=null && contentList.size()>0){
			
			contentEmotionMapDTO=new ContentEmotionsMapDTO();
			ContentEmotionsMap contentDetails= (ContentEmotionsMap)contentList.get(0);
			
			long ttlEmotionCount=contentDetails.getTotalEmotionCount();
			contentDetails.setTotalEmotionCount(ttlEmotionCount+1L);
			
			try {
				BeanUtils.copyProperties(contentEmotionMapDTO, contentDetails);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			pm.makePersistent(contentDetails);
		}
		pm.close();
		return contentEmotionMapDTO;
		
	}
	
	public void updateContent(long contentId, String embed){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ContentDetails contentDetails = pm.getObjectById(ContentDetails.class,contentId);
		contentDetails.setEmbedData(embed);
		pm.makePersistent(contentDetails);
		pm.close();
	}
	
	public final ContentDetailsDTO insertContentDetails(final ContentDetailsDTO contentDetailsDTO) 
	{
		
		Long cid = generatepk("select cid from "+ ContentDetails.class.getName(), 1L, "cid");
		
		ContentDetails contentDetails=new ContentDetails();
		contentDetails.setCid(cid);
		contentDetails.setMajorEmoId(contentDetailsDTO.getMajorEmoId());
		contentDetails.setMajorEmoCount(1L);
		contentDetails.setMajorEmoName(contentDetailsDTO.getMajorEmoName());
		contentDetails.setTitle(contentDetailsDTO.getTitle());
		contentDetails.setTotalFeedbackCount(1L);
		contentDetails.setUrl(contentDetailsDTO.getUrl());
		
		Key key = KeyFactory.createKey(ContentDetails.class.getSimpleName(), cid);
		contentDetails.setKey(key);
		createEntity(contentDetails);
		
		contentDetailsDTO.setCid(cid);
		return contentDetailsDTO;
				
	}
	
	public final String insertContentEmotionMap(final ContentEmotionsMapDTO contentEmotionMapDTO) 
	{
		Long contentEmoMapId = generatepk("select contentEmotionMapId from "	+ ContentEmotionsMap.class.getName(), 1L, "contentEmotionMapId");
		
		ContentEmotionsMap contentEmotionMap=new ContentEmotionsMap();
		contentEmotionMap.setCid(contentEmotionMapDTO.getCid());
		contentEmotionMap.setContentEmotionMapId(contentEmoMapId);
		contentEmotionMap.setEid(contentEmotionMapDTO.getEid());
		contentEmotionMap.setEmotionName(contentEmotionMapDTO.getEmotionName());
		contentEmotionMap.setTotalEmotionCount(1L);
		
		Key key = KeyFactory.createKey(ContentEmotionsMap.class.getSimpleName(), contentEmoMapId);
		contentEmotionMap.setKey(key);
		createEntity(contentEmotionMap);

		return null;
				
	}
	
	/**
	 * 
	 * @param emotionTaggedDTO
	 * @return
	 */
	public final String insertEmotionTaggedContent(final EmotionTagDTO emotionTaggedDTO) 
	{
		Long emoTaggedId = generatepk("select emotionTagId from "+ EmotionTagged.class.getName(), 1L, "emotionTagId");
		EmotionTagged emoTagged=new EmotionTagged();
		emoTagged.setCategoryId(emotionTaggedDTO.getCategoryId());
		emoTagged.setContentId(emotionTaggedDTO.getContentId());
		emoTagged.setContentUrl(emotionTaggedDTO.getContentUrl());
		emoTagged.setEmoId(emotionTaggedDTO.getEmoId());
		emoTagged.setEmotionTagId(emoTaggedId);
		emoTagged.setTitle(emotionTaggedDTO.getTitle());
		emoTagged.setTitleToLowerCase(emotionTaggedDTO.getTitle());
		emoTagged.setUserId(emotionTaggedDTO.getUserId());
		emoTagged.setDate(emotionTaggedDTO.getDate());
		emoTagged.setEmotionName(emotionTaggedDTO.getEmotionName());
		
		Key key = KeyFactory.createKey(EmotionTagged.class.getSimpleName(), emoTaggedId);
		emoTagged.setKey(key);
		createEntity(emoTagged);
		return null;
				
	}
	
	/**
	 * To check if emotion is been tagged for same content by same user
	 * @param emoTaggedDTO
	 * @return
	 */
	public boolean checkNUpdateEmoTagged(EmotionTagDTO emoTaggedDTO){
		boolean retVal=true;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select contentId from " +EmotionTagged.class.getName()+ " where contentUrl == '" + emoTaggedDTO.getContentUrl() + "' && emoId == "+emoTaggedDTO.getEmoId() +" && userId == "+emoTaggedDTO.getUserId();
		List<Long> contentList=(List<Long>)selection(query,pm);
		if(contentList!=null && contentList.size()>0){
			retVal = true;
		}else{
			retVal = false;
		}
		pm.close();
		return retVal;
	}
	/**
	 * To check whether user is following this user
	 * @param followerDTO
	 * @return
	 */
	public boolean isFollowing(FollowerDTO followerDTO){
		boolean retVal=true;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " +Followers.class.getName()+ " where uid == " + followerDTO.getUid() + " && followerId == "+followerDTO.getFollowerId();
		List<Followers> followersList=(List<Followers>)selection(query,pm);
		if(followersList!=null && followersList.size()>0){
			retVal= true;
		}else{
			retVal =false;
		}
		pm.close();
		return retVal;
	}
	/**
	 * Create follower mapping
	 * @param followerDTO
	 * @return
	 */
	public FollowerDTO followUser(FollowerDTO followerDTO){
		
		Followers followers=new Followers();
		Long id=1L;
		
		id=generatepk("select fId from " + Followers.class.getName(), id, "fId");
		followers.setUid(followerDTO.getFollowerId());
		followers.setUid(followerDTO.getUid());
		
		
		Key key = KeyFactory.createKey(Followers.class.getSimpleName(), id);
		followers.setKey(key);
		
		createEntity(followers);
		followerDTO.setfId(id);
		
		return followerDTO;
		
	}
	
	/**
	 * 
	 */
	public void unfollowUser(FollowerDTO followerDTO){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " +Followers.class.getName()+ " where uid == " + followerDTO.getUid() + " && followerId == "+followerDTO.getFollowerId();
		List<Followers> followersList=(List<Followers>)selection(query,pm);
		if(followersList!=null){
			pm.deletePersistentAll(followersList);
		}
		pm.close();
	}
	
	/**
	 * Get Followers List
	 * @param userId
	 * @return
	 */
	public ArrayList<Long> getFollowersList(long userId){
		ArrayList<Long> retList=null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select followerId from " +Followers.class.getName()+ " where uid == " + userId ;
		List<Long> followersList=(List<Long>)selection(query,pm);
		if(followersList!=null){
			retList=new ArrayList<Long>();
			for (Iterator<Long> iterator = followersList.iterator(); iterator.hasNext();) {
				Long long1 = (Long) iterator.next();
				retList.add(long1);
			}
		}
		return retList;
		
	}
	
	public void getUserData(long userId,int emoId){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String userString="userId == "+userId;
		
		if(emoId!=0){
			userString=userString+" && emoId =="+emoId;
		}
		Query query = pm.newQuery("select contentId, count(contentId) from " +EmotionTagged.class.getName()+ " where "+userString);
		query.setGrouping("contentId");
		
		List<Object[]> followersList = (List<Object[]>) query.execute();
		if(followersList!=null){
			
		}
	}
	
	public void getFollowersContent(ArrayList<Long> followersString,int emoId){
		
		String userString="";
		for (int i=0;i<followersString.size();i++) {
			Long long1 = (Long) followersString.get(i);
			userString=i==0 ? userString+" userId == "+long1 : userString+ " || userId == "+long1;
		}
		
		if(emoId!=0){
			userString= "("+userString+") && emoId=="+emoId;
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery("select contentId, count(contentId) from " +EmotionTagged.class.getName()+ " where "+userString);
		query.setGrouping("contentId");
		
		List<Object[]> followersList = (List<Object[]>) query.execute();
		if(followersList!=null){
			
		}
	}
	
}
