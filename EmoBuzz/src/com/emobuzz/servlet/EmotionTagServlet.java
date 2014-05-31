package com.emobuzz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emobuzz.dto.UserDTO;
import com.emobuzz.util.Utility;

@SuppressWarnings("serial")
public class EmotionTagServlet extends HttpServlet {
	Utility utility = null;
	
	@Override
	public void init() throws ServletException {
		utility = new Utility();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("a");
		HttpSession session = req.getSession();
		
		if (action.equals("tag")) {
			String eId = req.getParameter("eId");
			int emoId = Integer.parseInt(eId);

			String eName = req.getParameter("eName");
			String title = req.getParameter("t");
			String url = req.getParameter("u");
			
			
			UserDTO userDTO = (UserDTO) session.getAttribute("login");
			String mess = utility.setEmotiCon(userDTO, emoId, url, eName, title);
			resp.getWriter().println(mess);
			
			System.out.println("eid = " + eId + "\n" + "ename = " + eName + "\n" + "title = " + title + "\n" + "url = " + url);
			System.out.println("-------------------------------------------------------------------");
		}
		else if(action.equals("embed")){
			String url = req.getParameter("url");
			String cid = req.getParameter("cid");
			utility.updateEmbedData(url, cid);
		}
	}

}
