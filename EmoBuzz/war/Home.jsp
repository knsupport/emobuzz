<%@page import="com.emobuzz.dto.FollowerDTO"%>
<%@page import="com.emobuzz.util.DBFunctions"%>
<%@page import="com.emobuzz.dto.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.emobuzz.DiscoverClient"%>
<%@page import="com.emobuzz.emotion.EmotionHandler"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title>EmoBuzz</title>
	<link rel="stylesheet" href="/css/bootstrap.css">
	<link rel="stylesheet" href="/css/bootstrap-responsive.css">
	<link rel="stylesheet" href="/css/styles.css">
	<link href="/css/discover.css" rel="stylesheet">
	<style>
		.emo{width: 35px !important; opacity:1 !important; display: inherit !important;}
		.item-c.w1 {width:30%;}
	
	</style>
</head>

<body>
<%!
	DiscoverClient discover = new DiscoverClient(); 
	DBFunctions dbFun=new DBFunctions();
%>
	<div id="support" class="modal hide fade" tabindex="-1" data-width="740">
	<div class="modal-header">
		<button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
		<strong class="modal-head pagination-centered">Install Guide</strong>
	</div>
	<div class="modal-body">
		<div class="row-fluid">
			<div class="span12">
				<ul id="myTab" class="nav nav-tabs">
              <li class="active"><a href="#code1" data-toggle="tab">Code</a></li>
              <li><a href="#instruction" data-toggle="tab">Instruction</a></li>
             
            </ul>
            <div id="myTabContent" class="tab-content" style="overflow: hidden !important;">
              <div class="tab-pane fade in active" id="code1">
              	<div class="alert alert-info pagination-centered" style="font-size: 1.4em;">
					Copy this code, follow instruction
				</div>
				<div class="row-fluid">
			<div class="span12">
				<textarea 	id="code"
							class="span12" 
							style="line-height:18px;font-size:0.9em;height: 11em; cursor: text;overflow: auto;font-family:Monaco,Menlo,Consolas,Courier New,monospace;"
							readOnly>javascript:(function(){ 	var d = document; 	var host = 'http://emoobuzz.appspot.com'; 	if (location.protocol == 'https:') { 		host = 'https://emoobuzz.appspot.com'; 	}  	var ef=d.createElement('iframe'); 	var efs=ef.style; 	ef.src=host+'/plugin.jsp?t='+encodeURIComponent(document.title)+'&u='+encodeURIComponent(window.location); 	efs.height='100%'; 	efs.width='100%'; 	efs.border='none'; 	 	var edSp=d.createElement('span'); 	edSp.onclick=function(){var e=d.getElementById('eDiv');e.parentNode.removeChild(e);}; 	edSp.innerHTML='X'; 	 	var edSps=edSp.style; 	edSps.position='absolute'; 	edSps.right='20px'; 	edSps.top='7px'; 	edSps.cursor='pointer'; 	edSps.textDecoration='underline';  	var ed=d.createElement('div'); 	ed.id='eDiv'; 	var eds=ed.style; 	eds.position='fixed'; 	eds.left='0'; 	eds.right='0'; 	eds.top='0'; 	eds.maxWidth='385px'; 	eds.height='160px'; 	eds.zIndex='1999999999'; 	eds.backgroundColor='#F7F7F7';  	ed.appendChild(ef); 	ed.appendChild(edSp); 	d.getElementsByTagName('body')[0].appendChild(ed); })();
							</textarea>
			</div>
		</div>
              
			</div>
		
		
              <div class="tab-pane fade" id="instruction">
              <ol>
              	<li>Copy the code and save it as bookmark, with a bookmark name</li>
              	<li>Whenever you want to tag content online, type the bookmark name on address bar</li>
              </ol>
              *Bookmarklet Prototype version
              </div>
        
            </div>
			
	</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn">Close</button>
	</div>
</div>
</div>
	<div id="banner">
		<div class="container masterhead">
			<h1 class="knob">
				EmoBuzz
			</h1>
			<h2 class="title">
				<!-- Discovering emotionally aspiring content of your Role models, Subject experts, Mentors. -->
				Discovering how your Role models, Subject experts & Mentors perceive online content emotionally.
			</h2>
			<!-- <div class="title">
				<div style="display:inline-table">
					<img class="emo"  src="data:image/png;base64,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"></img>
					<br>
					<span class="emo-name">Inspring</span>
				</div>
				<div style="display:inline-table;margin-left:20px;">
					<img class="emo"  src="data:image/png;base64,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"></img>
					<br>
					<span class="emo-name">Love</span>
				</div>
				<div style="display:inline-table;margin-left:20px;">
					<img class="emo"  src="data:image/png;base64,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"></img>
					<br>
					<span class="emo-name">Funny</span>
				</div>
				
			</div> -->
		</div>
	</div>
<div class="content" style="background-color: #fff;">
	<div class="container" style="background-color: #fff;">
		<div class="row">
			<div class="span12 text-center">
				<!-- <h3>Start tagging</h3> -->
				<!-- <div> -->
					<div class="row text-center visible-phone visible-tablet hidden-desktop" style="height:150px;border-right:1px solid #ddd;">
						<div>
							<a href="#support" class="btn btn-large" data-toggle="modal">Android </a>
						</div>
					</div>
					<!-- <div class="row text-center hidden-phone hidden-tablet visible-desktop" >
						<div>
							<a title="EmoBuzz Tag" 
							href="javascript:(function(){ 	var d = document; 	var host = 'http://emoobuzz.appspot.com'; 	if (location.protocol == 'https:') { 		host = 'https://emoobuzz.appspot.com'; 	}  	var ef=d.createElement('iframe'); 	var efs=ef.style; 	ef.src=host+'/plugin.jsp?t='+encodeURIComponent(document.title)+'&u='+encodeURIComponent(window.location); 	efs.height='100%'; 	efs.width='100%'; 	efs.border='none'; 	 	var edSp=d.createElement('span'); 	edSp.onclick=function(){var e=d.getElementById('eDiv');e.parentNode.removeChild(e);}; 	edSp.innerHTML='X'; 	 	var edSps=edSp.style; 	edSps.position='absolute'; 	edSps.right='20px'; 	edSps.top='7px'; 	edSps.cursor='pointer'; 	edSps.textDecoration='underline';  	var ed=d.createElement('div'); 	ed.id='eDiv'; 	var eds=ed.style; 	eds.position='fixed'; 	eds.left='0'; 	eds.right='0'; 	eds.top='0'; 	eds.maxWidth='385px'; 	eds.height='160px'; 	eds.zIndex='1999999999'; 	eds.backgroundColor='#F7F7F7';  	ed.appendChild(ef); 	ed.appendChild(edSp); 	d.getElementsByTagName('body')[0].appendChild(ed); })();"
							class="btn btn-info btn-large">EmoBuzz Tag</a>
						</div>
						<br/>
						 <div>
							<span>Drag-Drop above button to bookmark toolbar of your browser.</span>
						</div> 
					</div> -->
					
				<!-- </div> -->
			</div>
		</div>
		
		
	</div>
</div>


<div style="" class="discover-cont container-fluid">
	<div class="row-fluid">
		<div class="span4 knob">
          	<a href="/discover/16"><div style="display:inline;width:120px;height:300px;"><img class="img-circle" style="height:250px;width:250px;" src="/img/profile/jimcarrey.jpg" onerror="$(this).attr('src' ,'/posting/user.png')"></div></a>
          	<h4><small>12.3M Followers</small></h4>
          	<h4>Jim Carrey</h4>
        </div>
        <div class="span4 knob">
          	<a href="/discover/16"><div style="display:inline;width:120px;height:300px;"><img class="img-circle" style="height:250px;width:250px;" src="/img/profile/arnab.jpg" onerror="$(this).attr('src' ,'/posting/user.png')"></div></a>
          	<h4><small>23.4K Followers</small></h4>
          	<h4>Arnab Goswami</h4>
        </div>
        <div class="span4 knob">
          	<a href="/discover/14"><div style="display:inline;width:120px;height:300px;"><img class="img-circle" style="height:250px;width:250px;" src="/img/profile/Alok.jpg" onerror="$(this).attr('src' ,'/posting/user.png')"></div></a>
          	<h4><small>11.1K Followers</small></h4>
          	<h4>Alok Kejriwal</h4>
        </div>
        <div class="span4 knob" style="margin-left:0;">
          	<a href="/discover"><div style="display:inline;width:120px;height:300px;"><img class="img-circle" style="height:250px;width:250px;" src="/img/profile/KiranBedi.jpeg" onerror="$(this).attr('src' ,'/posting/user.png')"></div></a>
          	<h4><small>2.25M Followers</small></h4>
          	<h4>Kiran Bedi</h4>
        </div>
		<div class="span4 knob">
          	<a href="/discover"><div style="display:inline;width:120px;height:300px;"><img class="img-circle" style="height:250px;width:250px;" src="/img/profile/DevP.jpg" onerror="$(this).attr('src' ,'/posting/user.png')"></div></a>
          	<h4><small>15.4K Followers</small></h4>
          	<h4>Devdutt Pattanaik</h4>
        </div>
	</div>
</div>
<!-- <div class="content row-fluid text-center">
			<br><br><br>
			<a href="/discover" class="btn btn-large" style="margin-bottom: 100px">Emotion Discovery</a>
			<iframe class="span12" frameBorder="0" src="http://127.0.0.1:8888/discover" style="height:400px;"></iframe>
</div> -->
<%--<%
	String emo = request.getParameter("e");
	String uid = request.getParameter("u");
	
%>
 <div style="" class="discover-cont container-fluid">
	<div class="row-fluid">
		
				<div class="span2" id="selEmoCont">
				<div class="sidebar">
					<ul id="tagSideBar" class="col-nav">
						<li>
							<%
							String active = "nav-active";
							if(emo!=null){
								active = "";
							}
							
							%>
							<a class="emo-tag <%=active %>" emo-name="All" emo-id="0" href="/discover">
								All Emotions
							</a>
							<%
								ArrayList<String> htmlList = null; 
								
								htmlList =discover.getAllEmotionList(uid,emo);	
								
								
								String desktop = htmlList.get(0);
								String mobile = htmlList.get(1);
							%>
							<%=desktop%></li></ul>
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
				String html = discover.getDiscoverContentHtml(emo,null);
			%>
			
			<%= html%> 
			

				<div id="scrollLoad" style="display: none;">
				<img alt="loading..." title="loading" src="/img/loading.gif">
			</div>
		</div>
		

	</div>
	</div> --%>

</body>
<script src="/js/jquery-1.9.1.js"></script>
<script src="/js/bootstrap.js"></script>
<script src="/js/masonry.pkgd.min.js" type="text/javascript"></script>
<script src="/js/imagesloaded.pkgd.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$("#code").click(function(){$("#code").focus().select();});

	var $container = $('#master-container');
	$container.masonry({itemSelector: '.item-c'})
	$container.imagesLoaded( function() {$container.masonry('destroy'); $container.masonry({itemSelector: '.item-c'}) });
</script>
</html>
