package com.emobuzz;
import java.io.IOException;

import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import com.emobuzz.dto.FollowerDTO;
import com.emobuzz.dto.UserDTO;
import com.emobuzz.util.DBFunctions;

@SuppressWarnings("serial")
public class EmoBuzzServlet extends HttpServlet {
	
	private DBFunctions dbFUn=null;
	public void init(){
		dbFUn=new DBFunctions();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		
		String action =req.getParameter("action");
		String fUser= req.getParameter("fUser");
		if(action!=null && fUser!=null){
			UserDTO userDTO= (UserDTO)req.getSession().getAttribute("login");
			
			resp.setContentType("text/json");
			FollowerDTO followerDTO=new FollowerDTO();
			followerDTO.setUid(userDTO.getUid());
			followerDTO.setFollowerId(Long.parseLong(fUser));
			if(userDTO!=null){
				JSONObject jsonObject = new JSONObject();
				if(action.equals("follow")){
					dbFUn.followUser(followerDTO);
					try {
						jsonObject.put("type", "unfollow");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}else if(action.equals("unfollow")){
					dbFUn.unfollowUser(followerDTO);
					try {
						jsonObject.put("type", "follow");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				resp.getWriter().println(jsonObject);
			}
		}
	}
}
