$(function(){
	var prevHost = "";
	var $container = $('#container');
	 $container.removeClass("hide").addClass("show");
	 $('#scrollLoad').hide();
	 $container.masonry({itemSelector: '.item-c'})
	 $container.imagesLoaded( function() {
		 $container.masonry('destroy');
		 $container.masonry({itemSelector: '.item-c'}) 
	 });
	var ajaxStat = false;
	function sentAjax(obj,append){
		ajaxStat = true;
		var scrollTo = $(window).scrollTop();
		$.ajax({
	 		type : 'get',
	 		url : '/sites',
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

			var obj = null;
			if(count < 25){
				if(catId!=null){
					obj = {	xhr : '1',from : frm,cat:catId};
				}
				else{
					obj = {	xhr : '1',from : frm};
				}
				
				$('#scrollLoad').show();
				sentAjax(obj,true);
			}
		}
	});

	$("#contentCat").change(function(){
		var val = $(this).val();
		if(val!=0){
			window.location = "sites?cat="+val;
		}
		else{
			window.location = "sites";
		}
	});
});