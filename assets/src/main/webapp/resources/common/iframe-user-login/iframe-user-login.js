//关闭iframe
function hideFrame() {
	$("#loginFrame").css("display","none");
}

//显示iframe
function showFrame() {
	$("#loginFrame").css("display","block");
}

//重载页面
function reloadPage() {
	window.location.reload();
}

//调用完post方法得到返回数据data后回调处理data的函数
function ajaxCall(callback){
	var url = $("#loginFrame iframe").attr("src");
	url = url.split("user_dialog_login.htm")[0]+"check_user_login.htm";
	$.ajax({ 
		async: false,
		type : "POST",
		url : url,
		dataType : 'json',
		success : function(data){
			//回调函数
			callback(data);
		}
	}); 
}

// 绑定登录框关闭事件
$(window).bind("message", function(e){
	var msg = e.originalEvent.data;
	// 关闭事件
	if (msg == "loginCancel") {
		hideFrame();
	}else if(msg == "loginSuccess"){
		reloadPage();
	}
});
//对于同一个页面中ajax形式的post()进行统一的错误处理
var AjaxGlobalHandler = {
	InitiateError: function ( options ) {
		$.ajaxSetup({ cache: false });
		$(document).ajaxError( function ( e, xhr, opts ) {
			if ( xhr.status == 401 ){
				options.errorHandler();
			}
		});
	}
};