<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:template title="${project.name} developers">
    <jsp:attribute name="content">
        <h1>Developers</h1>
        
        <form:form action="/project/managementDevelopers" modelAttribute="projectUsersForm">
            <form:hidden path="projectId"/>
	        <c:forEach items="${users }" var="user">
	           <span><form:checkbox path="usersId" label="${user.lastName } ${user.firstName }" value="${user.id }"/>
	            </span>
	        </c:forEach>
	        
            <div class="form-actions">
                <input type="submit" value="Confirm" />
            </div>
        </form:form>
        
    </jsp:attribute>
    
</t:template>