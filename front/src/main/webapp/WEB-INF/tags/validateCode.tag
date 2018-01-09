<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/templates/include/taglib.jsp"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="验证码输入框名称"%>
<%@ attribute name="inputCssStyle" type="java.lang.String" required="false" description="验证框样式"%>
<div class="yzm"></div>
 <div class="ytxt">
   <input name="${name}" type="text" id="${name}"  style="text-transform:uppercase;" autocomplete="false"/>
 </div>
 <div class="yzmimg"><img style="cursor:pointer;" src="${pageContext.request.contextPath}/servlet/validateCodeServlet" id="code_img" 
 	onclick="$(this).attr('src','${pageContext.request.contextPath}/servlet/validateCodeServlet?'+new Date().getTime());" width="59" height="27" /></div>