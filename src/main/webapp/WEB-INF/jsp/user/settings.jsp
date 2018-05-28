<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<t:template title="User settings">
    <jsp:attribute name="content">
        <h1>User settings</h1>
        
        <form:form action="/user/settings" modelAttribute="userSettings">
            <form:hidden path="userId"/>

            <div>
		        <c:forEach items="${allRoles }" var="role">
		           <span><form:checkbox path="roles" label="${role }" value="${role }"/></span><br>
		        </c:forEach>
	        </div>
	        
	        <form:label path="enabled" for="enabled">Enabled</form:label><form:checkbox path="enabled" id="enabled"/>
	        
	        
            <div class="form-actions">
                <input type="submit" value="Confirm" />
            </div>
        </form:form>
        
    </jsp:attribute>
    
</t:template>