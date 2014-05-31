<%@include file="/emobuzzlogincheck.jsp"%>
<%@page import="com.emobuzz.emotion.EmotionHandler"%>
<%!
	EmotionHandler emotionHandler = null;
	public void jspInit() {
		emotionHandler = new EmotionHandler();
	}
%>
<%UserDTO userDto = null;
	if(session.getAttribute("login")!=null)
	 userDto = (UserDTO)session.getAttribute("login");
%>
<html>
<head>
<style>
	body {
		margin: 0;
		font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
		font-size: 14px;
		line-height: 20px;
		color: #333333;
		background-color: #ffff;
	}

	.kk {
		padding: 10px;
		background-color: red;
		color: #fff;
		cursor: pointer;
	}

	div {
		text-align: center;
		margin-top: 10px;
	}

	.emo-span {
		width: 12%;
		display: inline-block;
		padding: 10px;
		cursor: pointer;
	}

	img {
		height: 33px;
		margin-bottom: 4px;
	}

	.emo-name {
		margin-top: 10px;
	}
	
	#emo-msg-div {
		margin-top: 10%;
	}
	
	#msg-close {
		cursor: pointer;
		border: 1px solid;
		padding-left: 2px;
		padding-right: 2px
	}
</style>
</head>

<body>
<%
	String title = request.getParameter("t");
	if (title == null) {
		title = "Untitled";
	}
	String url = request.getParameter("u");
	
	/* System.out.println("title = " + title + "\n" + "url = " + url);
	System.out.println("-------------------------------------------------------------------"); */
%>
<div id="emo-holder">
	<%
		for (int i = 1; i <= 4; i++) {
			String emoId = i + "";
			String emoName = emotionHandler.getEmotionName(emoId);
	%>
			<div class="emo-span" id="<%=i%>" onclick="tagContent('<%=emoId%>', '<%=emoName%>')">
				<img src="<%=emotionHandler.getEmotionImgBase64(emoId)%>">
				<span class="emo-name"><%=emoName %></span>
			</div>
	<%
		}
	%>

	<!-- <label class="kk" onclick="tagContent('Awesome')">Awesome</label>
	<label class="kk" onclick="tagContent('Love')">Inspiring</label>
	<label class="kk" onclick="tagContent('Like')">Like</label>
	<label class="kk" onclick="tagContent('Bad')">Dislike</label> -->
	
	<img id="load-img" src="/img/loading.gif" style="display:none;position: absolute;right: 46%;top: 20%;">
</div>
<div id="emo-msg-div" style="display:none;">
	<span id="emo-msg"></span>
	<span id="msg-close" onclick="hideMsg()">x</span>
</div>
<div>
	<%
		String param = "";
		if(userDTO!=null)
			param = "?u="+userDTO.getUid();
	%>
	<a target="_blank" href="/discover.jsp<%=param%>" >View My Clicks</a>
</div>
</body>
<script>
	var eHolderEle = document.getElementById("emo-holder");
	var eMsgEle = document.getElementById("emo-msg-div");
	var eImgEle = document.getElementById("load-img");
	function tagContent(eId, eName) {
		eHolderEle.style.opacity = "0.1";
		eImgEle.style.display="";
		var title = encodeURIComponent("<%=title%>");
		var url = encodeURIComponent("<%=url%>");
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("GET", "/tag?a=tag&eId="+eId+"&eName="+eName+"&t="+title+"&u="+url, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				// alert(xmlhttp.responseText);
				// alert("Tagged Successfully");
				eHolderEle.style.display = "none";
				eHolderEle.style.opacity = "";
				eImgEle.style.display="none";
				
				eMsgEle.style.display = "";
				document.getElementById("emo-msg").innerHTML = xmlhttp.responseText;
				
				
			}
		}
	}
	
	function hideMsg() {
		eMsgEle.style.display = "none";
		eHolderEle.style.display = "";
	}
</script>

<%-- javascript:(function(){
	var d = document;
	var host = "http://emoobuzz.appspot.com";
	if (location.protocol == "https:") {
		host = "https://emoobuzz.appspot.com";
	}

	var ef = d.createElement('iframe');
	ef.src = host+'/plugin.jsp';
	ef.style.height = "100%";
	ef.style.width = "100%";
	ef.style.border = "none";

	var ed = d.createElement('div');
	ed.style.position = "fixed";
	ed.style.left = "0";
	ed.style.right = "0";
	ed.style.top = "0";
	ed.style.maxWidth = "500px";
	ed.style.height = "25%";
	ed.style.zIndex = "1999999999";
	ed.style.backgroundColor = "#F7F7F7";

	ed.appendChild(ef);
	d.getElementsByTagName('body')[0].appendChild(ed);
})(); --%>
<!-- edSp.onclick = function(){edSp.parentNode.removeChild();}; -->
<!-- javascript:(function(){
	var d=document;
	var host='http://localhost:8888';

	var ef=d.createElement('iframe');
	var efs=ef.style;
	ef.src=host+'/plugin.jsp?t='+encodeURIComponent(document.title)+'&u='+encodeURIComponent(window.location);
	efs.height='100%';
	efs.width='100%';
	efs.border='none';
	
	var edSp=d.createElement('span');
	edSp.onclick=function(){var e=d.getElementById('eDiv');e.parentNode.removeChild(e);};
	edSp.innerHTML='X';
	
	var edSps=edSp.style;
	edSps.position='absolute';
	edSps.right='10px';
	edSps.cursor='pointer';
	edSps.textDecoration='underline';

	var ed=d.createElement('div');
	ed.id='eDiv';
	var eds=ed.style;
	eds.position='fixed';
	eds.left='0';
	eds.right='0';
	eds.top='0';
	eds.maxWidth='500px';
	eds.height='25%';
	eds.zIndex='1999999999';
	eds.backgroundColor='#F7F7F7';

	ed.appendChild(ef);
	ed.appendChild(edSp);
	d.getElementsByTagName('body')[0].appendChild(ed);
})(); -->
</html>
