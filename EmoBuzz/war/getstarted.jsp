<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="description" content="Now Tag your favorite articles by emotion">
<meta name='og:image' content='/img/reader.png'>
<meta name="keywords" content="Plugin, Emotions, Feedback System, Emotional Response, Blog, Response">
<meta name="author" content="MyEffecto Admin">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Get Started - MyEffecto</title>
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/bootstrap-responsive.css">

<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic">
<link rel="stylesheet" href="/css/styles.css">
<link rel="stylesheet" href="/css/effectostyles.css">
<!--[if IE 7]>
    <link rel="stylesheet" href="css/fontello-ie7.css"><![endif]-->

<!-- <script src="//ajax.googleapis.com/ajax/li12bs/jquery/1.10.2/jquery.min.js"></script> -->
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<style >
.big{font-size: 5em !important;} .icon-facebook-rect{font-size:95px;margin-left:4px;margin-right:-10px;}
@media (max-width :320px) {.bkmark{float:inherit;}}
.tagSpan{display: inline-table; border-right: 1px solid rgb(221, 221, 221); padding-right: 40px; margin-right: 25px;}
@media (max-width :360px) {.feature-bottom{border-bottom:1px solid #ddd;padding-bottom:35px}.guideText{font-size:16px !important} .tagSpan{border-right:none;margin-right:0px;padding-right:0px;border-bottom:1px solid #DDDDDD;margin-bottom:25px;padding-bottom:25px;}}
</style>
</head>
<body>
	<%-- <%@ include file="/Nav/main-in-nav.jsp"%> --%>
	<div id="banner">
		<div class="container"><br>
			<div class="masterhead">
				<h1 class="title">
					<i class="icon-book-open"></i>
				</h1>
			</div>
			<h2 class="title"><b style="color:#999999">Emo</b><b class="red">Buzz</b> - Capturing <em class="red">Emotional Experience</em></h2><br>
			<br>
			<div class="pagination-centered">
				<span class="tagSpan">
			
				<%@include file="/bookmarklet.jsp" %><br>
					<span style="font-size:14px;"><b>Drag and Drop</b>  above button <br>to browser bookmark tab. <a href="/how_to_tag_desktop">Instructions</a></span>
		
				</span>
				<span style="display:inline-table">
			
				<a href="/how_to_tag" class="btn btn-large" style="margin-bottom:15px;">
						For Mobile
					</a>
					<br>
						<span style="font-size:14px;">Available on <br>Android, Windows, iPhone/iPad</span>
		
				</span> 
			</div>
			<br><br>
		</div>
	</div>

	<div id="content" style="background-color: #fff;" >
		<div class="container">

			<div class="row">
				<div class="span4 muted feature-bottom">
					<h2 class="title padDown">
						Tag by <em class="red">Emotion </em>
					</h2>
					<p class="text-center">
						<i class="icon-emo-happy big muted"></i>
					</p>
					<p class="knob guideText">
						<br />Tag and save articles
					</p>
				</div>
				<div class="span4 muted feature-bottom">
					<h2 class="title padDown">
						Share with <em class="red">Emotion</em>
					</h2>
					<p class="text-center">
						<i class="icon-twitter big" style="font-size:374% !important;margin-right:4%;"></i>
						<i class="icon-facebook-rect big"></i>
						<i class="icon-gplus big" style="font-size:374% !important;margin-left:5%;"></i>
					</p>
					<p class="knob guideText ">
						<br />Tag with emotion and share
					</p>
				</div>
				<div class="span4 muted">
					<h2 class="title padDown">
						Discover and <em class="red">Explore </em>
					</h2>
					<p class="text-center">
						<i class="icon-search big muted"></i>
					</p>
					<p class="knob guideText">
						<br />Find what others have tagged
					</p>
				</div>
			</div>
		</div>
	</div>
			<div id="content" >
		<div class="container">
			<div class="row">		
				<div class="span12">
			<h6 class="bkmark title">
					<a id="extension" href="/userinfo?action=extension" class="btn btn-large title" 
					style="margin-right: 15px;margin-bottom: 9px;">
						Browser Extension
					</a>
				<%
					String msg = request.getParameter("browser");
					if (msg != null && msg.trim().equals("other")) {
				%>
						<div class="alert alert-info span7 offset2" id="extAlert">
							<h4 class="camel-case">
								Extension in this browser is not supported.
							</h4>
						</div>
				<%
					}
				%>
				
	
			</h6>
			</div> 			
				<div class="span12">
					<p class="text-center muted knob guideText ">
						Available on<br><br>
						
					<a href="/chrome" target="_blank" >	 <i class="icon-chrome muted " title="Chrome"></i> Chrome</a> |
					<a href="/ie" target="_blank"><i class="icon-ie muted" title="Internet Explorer"></i>	Internet Explorer</a> |
					<a href="/firefox" target="_blank">	<i class="icon-firefox muted" title="FireFox"></i> Firefox </a>
					</p>
				</div>
			</div>
		</div>
	</div>
<%-- 	<div id="content" style="background-color: #fff;" >
		<div class="container">
			<div class="row">
				<div class="span12">
					<br />
					<h4 class="muted text-center">
						<em> What are you waiting for? </em>
					</h4>
					<h3 class="title">
						Connect with MY<strong>EFFECTO</strong>. Spread the change -
						<%
						if (usr == null) {
					%>
						<a href="/login" class="btn btn-large btn-info"> Log In </a>
						<%
							} else {
						%><a href="/confgEmoji" data-toggle="modal"
							class="btn btn-large btn-danger addContentPlugin">CREATE-PLUGIN</a>
						<%
							}
						%>
					</h3>
					<br />
				</div>
			</div>
		</div>
	</div> --%>
	<%-- <%@ include file="/Footer/footer.jsp"%> --%>
</body>
<script type="text/javascript">
	/* $("#extension").click(function() {
		$("#extension").showLoading();
		$.ajax({
			type : "get",
			url : "/effecto",
			data : {
				action : "extension",
				url : "http://crossrider.com/install/47334"
			},
			success : function(data) {
				$("#extension").hideLoading();
				if (data) {
					$("#extAlert").html("FireFox Plugin not available");
					$("#extAlert").fadeToggle("slow");
					setTimeout(function() {$("#extAlert").fadeToggle("slow");}, 5000);
				}
			}
		});
	}); */
</script>
<script src="/js/bootstrap.js"></script>
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script>  
				window.onload = function() {
					$("#bookmarklet").tooltip();
				};
</script>
</html>