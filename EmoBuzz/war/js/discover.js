var redirectBlock = true;
$(function(){
	var ajaxLimit = 50;
	if(isRect){
		ajaxLimit = 27;
	}
	var prevHost = "";
	var $container = $('#container');
	 $container.fadeIn(1000);
	 $('#scrollLoad').hide();
	 $container.masonry({itemSelector: '.item-c'})
	 $container.imagesLoaded( function() {
		 $container.masonry('destroy');
		 $container.masonry({itemSelector: '.item-c'}) 
	 });

	 $("#ucLink").click(function() {
		$("#ucModal").modal("show"); 
	 });

	var ajaxStat = false;
	function sentAjax(obj,append){
		ajaxStat = true;
		var scrollTo = $(window).scrollTop();
		$.ajax({
	 		type : 'get',
	 		url : '/discover',
	 		cache: true,
	 		data : obj,
	 		success : function(data) {
		 		var html = $(data).html();
		 		if(html){
		 			if(append){
			 			$container.append(data)
		 			}
		 			else{
		 				$container.html("");
			 			$container.html(data)
		 			}
		 			$container.masonry('destroy');
		 			$container.masonry({itemSelector: '.item-c'});
		 			ajaxStat = false;
		 		}
		 		else{
		 			if(!append){
		 				$container.masonry('destroy');
			 			$container.html("<div class='alert alert-waring pagination-centered discov-blank'>No Content Found</div>");
		 			}
		 			else{
		 				ajaxStat = true;
		 				console.log("content ajax blocked")
		 			}
		 		}
	 		},
	 		complete : function(){
	 			$(window).scrollTop(scrollTo);
	 			if(append){
	 				$('#scrollLoad').hide();
	 			}
	 			else{
	 				$("#container").hideLoading();
	 			} 
	 		}
	 	}); 
	}
	function chkScroll(ele){
        var viewTop =$(window).scrollTop();
        var viewBottom = (viewTop +$(window).height());

        var containerBottom= Math.floor(ele.offset().top + ele.innerHeight());
        var scrollBuffer=40;
        
        if ((containerBottom - scrollBuffer) <= viewBottom){
            return true;
        }
        return false;
    }
	$("#container").delegate(".emo-title", "mouseenter mouseleave", function(event) {
		if (event.type == 'mouseenter') {
			$(".emo-title").tooltip();
		}
	});
	$(window).scroll(function(e) {
		var ele = $("#container");
		if(chkScroll(ele) && !ajaxStat){
			var count = ele.children().last().attr("no");
			var frm = ele.children().last().attr("to");
			var rect = 0;
			if(isRect){
				rect =1;
			}
			var obj = null;
			if(count < ajaxLimit){
				if(catId!=null){
					obj = {	eid : prevEmoId,host : hostname,xhr : '1',from : frm,recent : rect,cat:catId};
					if(headHide){obj = {	eid : prevEmoId,host : hostname,xhr : '1',from : frm,recent : rect,cat:catId,head:0};}
				}
				else{
					obj = {	eid : prevEmoId,host : hostname,xhr : '1',from : frm,recent : rect};
					if(hostname!="null"){obj = {eid : prevEmoId,host : hostname,xhr : '1',from : frm,recent : rect,navras:'0'};}
					if(headHide){obj = {	eid : prevEmoId,host : hostname,xhr : '1',from : frm,recent : rect,head:0};}
				}
			
				$('#scrollLoad').show();
				sentAjax(obj,true);
			}
		}
	});
	
	var framCurrent=1;
	$(".ifrm-widget").click(function(){
		var ele = $(this);
		$(".ifrm-widget.active").removeClass("active");
		ele.addClass("active");
		var a = ele.attr("action");
		if(a==="feeds"){
			if(framCurrent!=1){
				framCurrent=1;
				setIfrmWidget(ele.attr("url"));
			}
		}
		else{
			if(framCurrent!=2){
				framCurrent=2;
				setIfrmWidget(ele.attr("url"));
			}
		}
	});
	
	function siteWidget(url,loadDiv, loadmethod){
		var widget = document.createElement('iframe');
		widget.src=url;
		widget.scrolling="yes";
		widget.className="sideWiget";
		widget.onload = loadmethod;
		$(widget).ready(loadmethod);
		return widget;
	}
	var loading = false;
	function setIfrmWidget(url){
		var ele =  $("#iframeHold");
		var loadmethod = function(){ ele.hideLoading();loading=false;}
		ele.html(siteWidget(url,ele, loadmethod));
		 if(!loading){
			ele.showLoading();
			 loading=true;
		 }
	}
	/*$("#contentCat").change(function(){
		var val = $(this).val();
		if(val!=0){window.location = "discover?cat="+val;}
		else{window.location = "discover";}
	});*/
	
	var siteFrmLoad = false;
	function setSiteIframe(url){
		url = "/discover/"+url+"?head=0&isEffecto=true";
		var ele =  $("#siteFrameHold");
		var loadmethod = function(){ ele.hideLoading();siteFrmLoad=false;}
		ele.html(siteWidget(url,$("#iframeHold"),loadmethod));
		 if(!siteFrmLoad){
			 ele.showLoading();
			 siteFrmLoad=true;
		 }
	}
	$("#siteList").change(function(){
		var url = $(this).val();
		setSiteIframe(url);
	});
	
	var title = $("title").html();
	$('#activityModal').on('hide', function() {
		$("#notifyBtn").removeClass("btn-success");	
		$("#notify").html("");
		$("title").html(title);
	});
	
	$(".moreEmo").click(function(){
		$(".emoXtra").show(200);
		$(".lessEmo").show();
		$(this).hide();
	});
	$(".lessEmo").click(function(){
		$(".emoXtra").hide(200);
		$(".moreEmo").show();
		$(this).hide();
	});
	
	 $container.masonry('destroy');
	 $container.masonry({itemSelector: '.item-c'}) 
});