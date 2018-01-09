<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="path" type="java.lang.String" required="true" description="路径"%>

<spring:eval expression="assetsUriFactory.createURI(path, null, null)" />
