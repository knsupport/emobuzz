<%@page import="com.emobuzz.dto.FollowerDTO"%>
<%@page import="com.emobuzz.util.DBFunctions"%>
<%@page import="com.emobuzz.dto.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.emobuzz.DiscoverClient"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.emobuzz.emotion.EmotionHandler"%>
<%@page import="java.util.HashMap"%>
<%!
HashMap<String, String> emoMap = EmotionHandler.idToNameMap;
DiscoverClient discover = new DiscoverClient(); 
DBFunctions dbFun=new DBFunctions();
%>
<%
	String emo = request.getParameter("e");
	String uid = request.getParameter("u");
	
%>
<html><head>

		<title>EmoBuzz - Discover</title>

<meta charset="UTF-8">

<meta content="IE=edge, chrome=1" http-equiv="X-UA-Compatible">


<meta content="width=device-width, initial-scale=1.0" name="viewport">
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.css" rel="stylesheet">
<link href="/css/styles.css" rel="stylesheet">
<!-- <link rel="stylesheet" href="/css/toastr.min.css"> -->
<style type="text/css">.item-c.w1 {width:21%;}</style>
<link href="/css/discover.css" rel="stylesheet">
<style type="text/css"></style>
</head>
<%
	UserDTO userDto = (UserDTO)session.getAttribute("login");
%>
<body>
	<div tabindex="-1" class="modal hide fade" id="activityModal">
	<div class="modal-header">
		<span style="font-size:1.3em;"><i class="icon-bell"></i> Activity Feeds</span>
		<button class="close" aria-hidden="true" data-dismiss="modal" type="button">×</button>
	</div>
	<div class="modal-body">
		<div class="row-fluid">
			<div class="span12">
				<div id="recentCont">
					<h3 style="font-size:1.5em;" class="pagination-centered">No Activity Yet</h3>
				</div> 
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" type="button">Close</button>
	</div>
</div>
	<div class="navbar">
	<div class="navbar-inner">
		<div style="border-bottom:1px solid #ddd;padding-bottom:3px" class="container-fluid">
			<a id="logo" class="brand" href="/">
						<h4>
							<strong class="red">Emo</strong>Buzz
						</h4>
					</a>
			<%-- <span class="pull-right ">
				<%
					if(userDTO!=null  && uid!=null && !(""+userDTO.getUid()).equals(uid)){
						FollowerDTO followerDTO=new FollowerDTO();
						followerDTO.setUid(userDTO.getUid());
						followerDTO.setFollowerId(Long.parseLong(uid));
						if(dbFun.isFollowing(followerDTO)){%>
							<a title="Un Follow" data-action="unfollow" class="btn btn-info" id="followBtn">Unfollow</a>
						<%}else{%>
							<a title="Follow" data-action="follow" class="btn " id="followBtn">Follow</a>
						<%}				
					}
				
				%>
				
			</span>  --%>
			
		<!--  	<span class="pull-right ">
				<a title="Activiy Feeds" class="btn " data-toggle="modal" href="#activityModal" id="notifyBtn"><i class="icon-bell"></i><span id="notify"></span></a>
			</span>  -->
			  
			<ul class="discov-nav nav nav-pills pull-right">
              <!-- <li><a target="_blank" href="/">about </a></li> -->

            </ul> 
		</div>
	</div>
</div>
	<div style="" class="discover-cont container-fluid">
	<div class="row-fluid">
		
				<div class="span2" id="selEmoCont">
				<div class="sidebar">
					<% 
						/* if (userDto != null && (uid != null && uid.trim().equals(""+userDto.getUid()))) { */
							if (uid.equals("14")) {
							%>
								<img src="/img/facewithgun_400x400.jpg"><br /><br />
							<%
							} else if (uid.equals("17")) {
							%>
								<img src="/img/profile/arnab.jpg"><br /><br />
							<%
							}
						
					%>
					<ul id="tagSideBar" class="col-nav">
						<li>
							<%
							String uidParam="";
							if(uid!=null){
								uidParam="?u="+uid;
							}
							String active = "nav-active";
							if(emo!=null){
								active = "";
							}
							%>
							<a class="emo-tag <%=active %>" emo-name="All" emo-id="0" href="/discover<%=uidParam%>">
								All Emotions
							</a>
							<%
								ArrayList<String> htmlList = null; 
								
								htmlList =discover.getAllEmotionList(uid,emo);	
								
								
								String desktop = htmlList.get(0);
								String mobile = htmlList.get(1);
							%>
							<%=desktop%><%-- 
												<%
								if(htm.trim().length()==0){
							%>
								No Emotion
							<%			
								}
							%> 
						
					--%></li></ul>
				</div>
				<select onchange="location=this.options[this.selectedIndex].value;" class="camel-case" id="emo-select">
				
				<option value="/discover" selected>All</option>
					<%=mobile %>
				</select>
			</div>
		
		
		<div class="span10 embedfalse" id="master-container">
		
			<!-- <h4 class="muted1 camel-case" style="margin-left:15px;">
				<b><span id="emoCatog">Most</span> Rated Articles</b> 
			</h4> -->
			<%
	
			
			String html = null;
			
		
				html = discover.getDiscoverContentHtml(emo,uid);
			
		
			%>
			
			<%= html%> 
			

				<div id="scrollLoad" style="display: none;">
				<img alt="loading..." title="loading" src="/img/loading.gif">
			</div>
		</div>
		

	</div>
	</div>
		
<script src="/js/jquery-1.9.1.js"></script>
<script src="/js/bootstrap.js" type="text/javascript"></script>
<script src="/js/masonry.pkgd.min.js" type="text/javascript"></script>
<script src="/js/imagesloaded.pkgd.min.js" type="text/javascript"></script>
<script type="text/javascript">
var $container = $('#master-container');
$container.masonry({itemSelector: '.item-c'})
$container.imagesLoaded( function() {$container.masonry('destroy'); $container.masonry({itemSelector: '.item-c'}) });

$("#followBtn").click(function(){
	
	var obj={"action":$(this).attr("data-action"),"fUser":"<%=uid%>"}
	$.ajax({
 		type : 'get',
 		url : '/emobuzz',
 		cache: true,
 		data : obj,
 		success : function(data) {
 	 		var dt=data.type;
 	 		if(dt == "follow"){
 	 			$("#followBtn").attr("data-action","follow").removeClass("btn-info").text("Follow");
 	 			alert("You unfollowed this user");
 	 		}else{
 	 			$("#followBtn").attr("data-action","unfollow").addClass("btn-info").text("Unfollow");
 	 			alert("You are following this user");
 	 		}
 		},
 		complete : function(){
 			 
 		}
 	});
	
});


</script>

<!-- <script src="/js/activityFeed.js" type="text/javascript"></script> -->
</body>
</html>
