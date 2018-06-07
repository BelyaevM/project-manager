<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:message code="access.denied.page.title" var="pageTitle" />

<t:template title="${pageTitle }">
    <jsp:attribute name="content">
		<h2>${pageTitle }</h2>
		<spring:message code="access.denied.page.msg" htmlEscape="false" />
    </jsp:attribute>
    
</t:template>
