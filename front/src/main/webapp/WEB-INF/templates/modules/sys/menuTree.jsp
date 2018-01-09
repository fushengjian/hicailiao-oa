<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/templates/include/taglib.jsp"%>
<div class="leftone">${currentMenu.name }相关</div>
<c:set var="menuList" value="${fns:getMenuList()}"/>
<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
	<c:if test="${menu.parent.id eq (not empty param.parentId ?param.parentId:'1') && menu.type eq 0 && menu.isShow eq '1'}">
		<c:choose>
			<c:when test="${not empty menu.href }">
				<li><a href="javascript:void(0);" id='menu_op_${menu.id }' onclick="openURL('url','${fn:indexOf(menuChild.href, '://') eq -1?ctx:''}${not empty menu.href?menu.href:'/404'}','main_workspace','menu_op_${menu.id }')">${menu.name }</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="javascript:void(0);"id="menu_p_${menu.id }" suffix="menu_p_${menu.id }_"><img id="menu_p_${menu.id }_img" src='<tags:assetslink path="/resources/style/system/manage/blue/images/contract.jpg"/>' width="13" height="13" />${menu.name }</a>
					<ul id="menu_p_${menu.id }_info">
						<c:forEach items="${menuList}" var="menuChild" >
							<c:if test="${menuChild.parent.id eq menu.id && menuChild.type eq 0 &&menuChild.isShow eq '1'}">
						     	<li><a id="menu_op_${menuChild.id}" href="javascript:void(0);" onclick="openURL('url','${fn:indexOf(menuChild.href, '://') eq -1?ctx:''}${not empty menuChild.href?menuChild.href:'/404'}','main_workspace','menu_op_${menuChild.id}')">${menuChild.name }</a></li>
						     </c:if>
					    </c:forEach>
					</ul>
				 </li>
			</c:otherwise>
		</c:choose>
 	</c:if>
 </c:forEach>
