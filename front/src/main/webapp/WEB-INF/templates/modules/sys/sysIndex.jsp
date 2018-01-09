<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/templates/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>运营平台-采料网</title>
<link rel="stylesheet" href='<tags:assetslink path="/resources/style/system/manage/blue/template.css" />' />
<script src="${ctxStatic}/js/jquery-1.6.2.js"></script>
<script src="${ctxStatic}/js/jquery.shop.base.js"></script>
<%@include file="/WEB-INF/templates/include/dialog.jsp" %>
<script>
jQuery(document).ready(function(){
    pagestyle();
	//jQuery(".webmap a").click(function(){
	//    jQuery(".webmap_box").fadeIn('normal');
	// });
	jQuery(".close_map").click(function(){
	    jQuery(".webmap_box").fadeOut('normal');
	});
	jQuery(".webskin em a img").click(function(){
	      var webcss = jQuery(this).attr("webcss");
			jQuery.post("${ctx}/admin/set_websiteCss.htm",{
						"webcss":webcss
						},function(data){
						window.location.href="${ctx}/admin/index.htm";
							},"text");
		});
});
var ctx='${ctx}';//用于js
</script>
</head>
<body scroll="yes">
<div class="main">
  <div class="top">
    <div class="login">您登录的身份是：<shiro:principal property="name"/> &nbsp;&nbsp;| <a href="${ctx}/logout.do" target="_self">安全退出</a>|<a href="${ctx}/sys/user/modifyPwd.do" target="main_workspace">修改密码</a>| <a href="http://www.hicailiao.com" target="_blank">商城首页</a></div>
    <div class="logo"></div>
    <div class="nav">
      <ul>
        <li><a href="javascript:void(0);" class="home" id="common_operation_menu" onclick="openURL('show','common_operation')">首页</a></li>
        <c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
			<c:if test="${menu.parent.id eq '1' && menu.type eq 0 && menu.isShow eq '1'}">
				<li class="active"><a href="javascript:void(0);" class="active" id="menu_${menu.id}" onclick="openURL('show','${menu.id}')" >${menu.name}</a></li>
			</c:if>
		 </c:forEach>
      </ul>
    </div>
  </div>
  <div class="index" id="workspace">
    <div class="left">
      <div class="lefttop"> </div>
      <div class="left_ul">
        <ul class="ulleft" id="common_operation">
        <div class="leftone">常用操作</div>
          <li><a class="this" id="welcome_op" href="javascript:void(0);" onclick="openURL('url','${ctx}/admin/welcome.htm','main_workspace','welcome_op')">欢迎页面</a>
          </li>
          <li><a href="javascript:void(0);" id="set_site_op_q" onclick="openURL('url','${ctx}/admin/set_site.htm','main_workspace','set_site_op_q')">站点设置</a></li>
          <li><a href="javascript:void(0);" id="user_list_op_q" onclick="openURL('url','${ctx}/admin/user_list.htm','main_workspace','user_list_op_q')">会员管理</a></li>
          <li><a href="javascript:void(0);" id="store_list_op_q" onclick="openURL('url','${ctx}/admin/store_list.htm','main_workspace','store_list_op_q')">店铺管理</a></li>
          <li><a href="javascript:void(0);" id="goods_manage_op_q" onclick="openURL('url','${ctx}/admin/goods_list.htm','main_workspace','goods_manage_op_q')">商品管理</a></li>
          <li><a href="javascript:void(0);" id="order_list_op_q" onclick="openURL('url','${ctx}/admin/order_list.htm','main_workspace','order_list_op_q')">订单管理</a></li>
        </ul>
        <ul class="ulleft" id="leftMenuPanel" style="display:none;">
        </ul>
      </div>
    </div>
    <div class="content">
    <div class="navbar">
      <div style="line-height:20px;">您的位置：控制台><span id="top_nav_info">站点设置</span></div>
      </div>
      <iframe id="main_workspace" name="main_workspace" src="${ctx}/admin/welcome.htm" style="overflow:auto;height:550px" frameborder="0" width="100%" height="100%" scrolling="yes" onload="window.parent"></iframe>
    </div>
  </div>
</div>
</body>
</html>
