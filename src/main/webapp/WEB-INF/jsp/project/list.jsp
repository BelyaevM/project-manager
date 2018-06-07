<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:message code="projects.list.page.title" var="pageTitle" />

<t:template title="${pageTitle }">
    <jsp:attribute name="content">
        <h1>${pageTitle }</h1>
        
        <div>
            <sec:authorize access="hasRole('MANAGER')">
                <a href="/project/add">Create project</a>
            </sec:authorize>
        </div>
        <br>
        <c:choose>
            <c:when test="${projectList.size() > 0 }">
	            <table class="datatable">
	              <tr class="column-header">
	                  <th>ID</th>
	                  <th>Name</th>
	                  <th>Description</th>
	                  <th>Manager</th>
	                  <th>Created</th>
	                  <th>Updated</th>
	              </tr>
	
	             <c:forEach items="${projectList }" var="project">
	                <tr>
	                    <td>${project.id }</td>
	                    <td><a href="/project/overview/${project.id}">${project.name }</a></td>
	                    <td>${project.description }</td>
	                    <td>${project.manager.firstName }&nbsp;${project.manager.lastName }</td>
	                    <td>${project.created }</td>
	                    <td>${project.updated }</td>
	                </tr>
	             </c:forEach>             
	              
	            </table>
            </c:when>
            <c:otherwise>
                Projects not found.
            </c:otherwise>
        </c:choose>
    
    </jsp:attribute>
    
</t:template>