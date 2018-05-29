<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:message code="profile.page.title" var="pageTitle" />

<t:template title="${pageTitle }">
    <jsp:attribute name="content">
        <h1>${pageTitle }</h1>
        
        <c:choose>
            <c:when test="${result == true }">
                <spring:message code="profile.page.activated" htmlEscape="false" />                
            </c:when>
            <c:otherwise>
                <spring:message code="profile.page.not.activated" />
            </c:otherwise>
        </c:choose>
        
    </jsp:attribute>
    
</t:template>