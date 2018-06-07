<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<t:template title="${project.name }">
    <jsp:attribute name="content">
        <h1>${project.name }</h1>
        
        <div>
            <a href="/project/issues/${project.id }"><spring:message code="project.overview.view.issues.action" /></a>&nbsp;
            <a href="/issue/create/${project.id }"><spring:message code="project.overview.new.issues.action" /></a>&nbsp;
            <a href="/project/settings/${project.id }"><spring:message code="project.overview.settings.action" /></a>
        </div>
        <div class="info-block" id="project-description">
	        <h3><spring:message code="project.overview.description" /></h3>
	        <span>${project.description }</span>
        </div>

        <div class="info-block">        
	        <h3><spring:message code="project.overview.members" /></h3>
	        <div>
	            <spring:message code="project.overview.manager" />: <c:out value="${project.manager.fullName }" />
	            <c:forEach items="${project.getUsers() }" var="user">
	                <c:if test="${user.getId() ne project.getManager().getId() }">
	                    <div><spring:message code="project.overview.developer" />: ${user.fullName }</div>
	                </c:if>
	            </c:forEach>
	            <br>
	            <a href="/project/managementDevelopers/${project.id }"><spring:message code="project.overview.management.developers" /></a>
	        </div>
        </div>

    </jsp:attribute>
    
</t:template>