<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:message code="projects.list.page.title" var="pageTitle" />

<t:template title="${pageTitle }">
    <jsp:attribute name="content">
        <h1>${pageTitle }</h1>
        
        <div>
            <sec:authorize access="hasRole('MANAGER')">
                <a href="/project/add"><spring:message code="project.list.create" /></a>
            </sec:authorize>
        </div>
        <br>
        <c:choose>
            <c:when test="${projectList.size() > 0 }">
	            <table class="datatable">
	              <tr class="column-header">
	                  <th><spring:message code="form.label.id" /></th>
	                  <th><spring:message code="form.label.name" /></th>
	                  <th><spring:message code="form.label.description" /></th>
	                  <th><spring:message code="project.overview.manager" /></th>
	                  <th><spring:message code="project.list.created" /></th>
	                  <th><spring:message code="project.list.updated" /></th>
	              </tr>
	
	             <c:forEach items="${projectList }" var="project">
	                <tr>
	                    <td><c:out value="${project.id }"/></td>
	                    <td><a href="/project/overview/${project.id}"><c:out value="${project.name }" /></a></td>
	                    <td><c:out value="${project.description }"/></td>
	                    <td><c:out value="${project.manager.fullName }"/></td>
	                    <td><c:out value="${project.created }"/></td>
	                    <td><c:out value="${project.updated }"/></td>
	                </tr>
	             </c:forEach>             
	              
	            </table>
            </c:when>
            <c:otherwise>
                <spring:message code="project.list.empty" />
            </c:otherwise>
        </c:choose>
    
    </jsp:attribute>
    
</t:template>