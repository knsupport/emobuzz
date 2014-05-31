<%@page import="com.emobuzz.dto.UserDTO"%>
<%
	UserDTO userDTO = (UserDTO) session.getAttribute("login");
	if (userDTO == null) {
		/* response.sendRedirect("/emobuzzlogin.jsp"); */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/emobuzzlogin.jsp");
		dispatcher.forward(request, response);
	}
%>
