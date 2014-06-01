<!DOCTYPE html>
<%@page import="com.emobuzz.util.Utility"%>
<%@page import="com.emobuzz.util.DBFunctions"%>
<%@page import="com.emobuzz.dto.UserDTO"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>EmoBuzz Login</title>
	<link rel="stylesheet" href="/css/bootstrap.css">
	<link rel="stylesheet" href="/css/bootstrap-responsive.css">
	
	<style>
		@media (max-width:320px) {
		    #login {
		    	margin-top: 8px;
		    }
		}
	</style>
</head>
<body>
<%!
	Utility utility =new Utility();
%>
<%
	UserDTO userDTO = (UserDTO)session.getAttribute("login"); 
	if(userDTO!=null){
		RequestDispatcher dispatcher = request.getRequestDispatcher("/plugin.jsp");
		dispatcher.forward(request, response);
	}else{
		String action= request.getParameter("action");
		if(action!=null && action.equals("login")){
			String email= request.getParameter("email");
			String title = request.getParameter("t");
			String url = request.getParameter("u");
			userDTO=utility.registerOrLoginUser(email);
			session.setAttribute("login", userDTO);
			response.sendRedirect("/plugin.jsp?t="+title+"&u="+url);
			return;
		}else {
			String title = request.getParameter("t");
			if (title == null) {
				title = "Untitled";
			}
			String url = request.getParameter("u");
%>
			<div class="container pagination-centered">
				<h3 style="margin-bottom: 0;">Login</h3>
			</div>
			<hr style="margin-bottom: 18px;">
			<div class="pagination-centered">
				<div>
					<form class="form-horizontal" action="/emobuzzlogin.jsp" method="get" onsubmit="return ebValidate();">
						<div class="control-group">
							<div class="controls" style="margin: 0;">
								<input type="hidden" id="action" class="input" name="action" placeholder="login" value="login"/>
								<input type="hidden" class="input" name="t" value="<%=title%>"/>
								<input type="hidden" class="input" name="u" value="<%=url%>"/>

								<input type="email" id="emailId" class="input" name="email" placeholder="Email">
								<button class="btn btn-info" id="login" type="submit">EmoBuzz Login</button>
							</div>
						</div>
					</form>
				</div>
			</div>
<%
		}
	}
%>
</body>
<script type="text/javascript">
	function ebValidate() {
		var email = document.getElementById("emailId").value;
		if (email == null || email == undefined || email == "") {
			alert("Enter Email Id");
			return false;
		}
	}
</script>
</html>