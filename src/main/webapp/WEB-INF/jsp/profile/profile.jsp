<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<t:template title="User profile">
    <jsp:attribute name="content">
        <h1>${user.firstName } ${user.lastName }</h1>
        
        <div style="padding:0.5em; float:left; background: #f0f0f0;">
            <img src="/avatar/big/${user.id }"><br>
            <a href="/avatar/upload">Upload</a>
        </div>
        <div style="padding:0.5em; float:left;">
            First name: <c:out value="${user.firstName }" /><br>
            Last name: <c:out value="${user.lastName }" /><br>
            Email: <c:out value="${user.email }" /><br>
        </div>
        
    </jsp:attribute>
    
</t:template>