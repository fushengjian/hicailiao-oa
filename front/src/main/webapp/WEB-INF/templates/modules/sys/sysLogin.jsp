<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/templates/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>采料网运营平台登录</title>
</head>
<link rel="stylesheet" href='<tags:assetslink path="/resources/style/system/manage/blue/login.css" />' />
<script src="${ctxStatic }/js/jquery-1.6.2.js"></script>
<script>
function refreshCode(){
	jQuery("#code_img").attr("src","$!webPath/verify.htm?d"+new Date().getTime());
}
function login(){
	jQuery("#theForm").submit();
}
var EnterSubmit = function(evt){
evt = window.event || evt;
 if (evt.keyCode == 13)
 {
  login();
 }
}
window.document.onkeydown=EnterSubmit;
jQuery(document).ready(function(){
     if(top.location!=this.location)top.location=this.location;//跳出框架在主窗口登录
	jQuery('#username').focus();
});
</script>
<body>
<form name="theForm" id="theForm" action="${ctx}/login.do" method="post">
<%String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);%>
	<div id="messageBox" class="<%=error==null?"hide":""%>" style="text-align:center;">
		<span id="loginError" style="color:red;"><%=error==null?"":"com.tomowork.shop.manage.sys.security.CaptchaException".equals(error)?"验证码错误, 请重试.":"用户或密码错误, 请重试." %></span>
	</div>
<div class="main_body">
  <div class="login_top"> </div>
  <div class="login_mid">
    <div class="login_left"></div>
    <div class="login_m">
      <ul>
        <li>
          <div class="usename"></div>

          <div class="ntxt">
            <input name="username" type="text" id="username"  autocomplete="false"/>
          </div>
        </li>
        <li>
          <div class="password"></div>
          <div class="ptxt">
            <input name="password" type="password" id="password"  autocomplete="false"/>
          </div>
        </li>
        <c:if test="${isValidateCodeLogin}">
         <li>
          	<tags:validateCode name="validateCode" />
        </li>
        </c:if>
      </ul>
    </div>
    <div class="login_r"></div>
  </div>
  <div class="login_mid2">
    <div class="login_left2"></div>
    <div class="login_m2">
      <div class="m1">
        <input name="" type="button"  value="" onclick="login();" style="cursor:pointer"/>
        <input name="login_role" type="hidden" id="login_role" value="admin" />
      </div>
      <div class="m2">
        <input name="" type="reset"  value=""  style="cursor:pointer"/>
      </div>
    </div>
    <div class="login_r2"></div>
  </div>
  <div class="bottom">
    <div class="bottoml"></div>
    <div class="bottomm"></div>
    <div class="bottomr"></div>
  </div>
  <div class="tell">
    <p>Copyright ${fns:getCurrentYear()} © <a href="http://www.hicailiao.com" target="_blank"><span style="color: #2662AC;">海南采料科技有限公司</span></a> </p>
    </div>
</div>
</form>
</body>
</html>
