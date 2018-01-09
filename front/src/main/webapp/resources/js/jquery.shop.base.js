var pagestyle=function(){
   jQuery("#workspace .left").css("width","10%")
   jQuery("#workspace .content").css({"width":"90%","height":jQuery(window).height()});
   try{
        var iframe = jQuery("#main_workspace");
		var h = jQuery(window).height() - iframe.offset().top;
		var w = jQuery(window).width() - iframe.offset().left;
		var cw=jQuery("#workspace .content").css("width").replace("px","");
		if(h < 300) h = 300;
		if(w >= cw) w = cw-3;
		iframe.height(h);
		iframe.width(w);
		jQuery(".statement").css("top",jQuery(window).height()-60);
     }catch (ex){}

};
//
String.prototype.replaceAll  = function(s1,s2){
   return this.replace(new RegExp(s1,"gm"),s2);
}
function openURL(args){
	 var type=arguments[0];
	 var item_id="";
	 var url="";
	 if(type=="show"){
	  item_id=arguments[1];
	 }
	 if(type=="url"){
	   url=arguments[1];
	 }
	 var content_id=arguments[2];
	 var url_id=arguments[3];
	 var parent_id=arguments[4];
	 //item_id不为空，显示数据
	 if(type=="show"){
		if(item_id == 'common_operation'){//固定的常用操作
		   jQuery(".ulleft").hide();
		   jQuery("#"+item_id).show();
		   var current_css=jQuery("#"+item_id+"_menu").attr("class");
		    //修改样式
		   jQuery(".nav a").removeClass("this");
		   jQuery(".nav a").removeClass("home");
		   jQuery("#"+item_id+"_menu").addClass("this");
		   //打开默认的第一个url
		   var first_li=jQuery("#"+item_id+" li").first();
		   var first_a=jQuery("#"+item_id+" a").first();
		   jQuery(".ulleft a").removeClass("this");
		   first_a.addClass("this");
		   jQuery("#top_nav_info").html(first_a.html());
		   var html=first_li.html().match(/openURL\('(.*)'\)/ig);
		   if(html!=null){
		   var arg = html[0].split(",");
		   		jQuery("#main_workspace",parent.document.body).attr("src",arg[1].replaceAll("'",""));
		   }else{

		   }
		}else{//菜单加载

		  jQuery.ajax({type:'get',
             url: ctx+'/sys/menu/tree.do?parentId='+item_id,
             success:function(data){
        	   jQuery("#leftMenuPanel").html(data);
        	   jQuery(".ulleft").hide();
          	   jQuery("#leftMenuPanel").show();
          	   var current_css=jQuery("#menu_"+item_id).attr("class");
          	    //修改样式
          	   jQuery(".nav a").removeClass("this");
          	   jQuery(".nav a").removeClass("home");
          	   jQuery("#menu_"+item_id+"").addClass("this");
          	   //打开默认的第一个url
          	   var first_li=jQuery("#leftMenuPanel li").first();
          	   var first_a=jQuery("#leftMenuPanel a[id^=menu_op]").first();
          	   jQuery(".ulleft a").removeClass("this");
          	   first_a.addClass("this");
          	   jQuery("#top_nav_info").html(first_a.html());
          	   var html=first_li.html().match(/openURL\('(.*)'\)/ig);
          	   if(html!=null){
          	   var arg = html[0].split(",");
          	   		jQuery("#main_workspace",parent.document.body).attr("src",arg[1].replaceAll("'",""));
          	   }else{

          	   }
          	   jQuery("a[id^=menu_p_]").click(function(){
          		  var suffix=jQuery(this).attr("suffix");
          		  if(jQuery("#"+suffix+"info").css("display")=="block"){
          		      jQuery("#"+suffix+"info").hide();
          			  jQuery("#"+suffix+"img").attr("src",ctx+"/resources/style/system/manage/blue/images/spread.jpg");
          		   }else{
          		      jQuery("#"+suffix+"info").show();
          			  jQuery("#"+suffix+"img").attr("src",ctx+"/resources/style/system/manage/blue/images/contract.jpg");
          		   }
          		});
             }
		  });
		}
	 }
	//url不为空加载请求数据
	if (type == "url") {
		if (parent_id != undefined) {
			jQuery(".ulleft").hide();
			jQuery(".nav a").removeClass("this");
			jQuery(".nav a").removeClass("home");
			jQuery("#menu_"+parent_id).addClass("this");
			var ulleft_menu = jQuery(".ulleft:has(#"+url_id+")");
			if (ulleft_menu.length > 0) {
				ulleft_menu.show();
			} else {
				jQuery.ajax({
					type: 'get',
					url: ctx+'/sys/menu/tree.do?parentId='+parent_id,
					success: function(data) {
						jQuery(".ulleft").hide();
						jQuery("#leftMenuPanel").html(data);
						jQuery("#"+url_id).addClass("this");
						jQuery("#leftMenuPanel").show();

						jQuery("a[id^=menu_p_]").click(function() {
							var suffix = jQuery(this).attr("suffix");
							if (jQuery("#"+suffix+"info").css("display") == "block") {
								jQuery("#"+suffix+"info").hide();
								jQuery("#"+suffix+"img").attr("src",ctx+"/resources/style/system/manage/blue/images/spread.jpg");
							} else {
								jQuery("#"+suffix+"info").show();
								jQuery("#"+suffix+"img").attr("src",ctx+"/resources/style/system/manage/blue/images/contract.jpg");
							}
						});
					}
				});
			}
			jQuery(".webmap_box").fadeOut('normal');
		}
		jQuery(".ulleft a").removeClass("this");
		jQuery("#"+url_id).addClass("this");
		if (url != undefined) {
			jQuery("#"+content_id,parent.document.body).attr("src",url)
		}
		jQuery("#top_nav_info").html(jQuery("#"+url_id).html());
	}
	//
	pagestyle();
}

//默认执行的初始化
jQuery(document).ready(function(){
  document.body.parentNode.style.overflow="hidden";
  jQuery(window).resize(pagestyle);
});
