<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<t:template title="User activate">
    <jsp:attribute name="content">
        <h1>User activate</h1>
        
        <c:choose>
            <c:when test="${result == true }">
                Hello, Your account was succefully activate. Please proceed to <a href="/login">login</a> page.
            </c:when>
            <c:otherwise>
                Sorry, We could not activate Your account. Please contact with our support. 
            </c:otherwise>
        </c:choose>
        
    </jsp:attribute>
    
</t:template>