<%@page import="com.emobuzz.DiscoverClient"%>
<%@page import="com.emobuzz.util.Utility"%>
<%
DiscoverClient d = new DiscoverClient();
 d.getAllEmotionList("1");
%>