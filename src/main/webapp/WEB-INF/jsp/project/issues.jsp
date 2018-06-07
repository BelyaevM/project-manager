<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template title="${project.name }">
    <jsp:attribute name="content">
        <h1>${project.name }</h1>
        
        <div>
            <a href="/project/overview/${project.id }">Overview</a>&nbsp;
            <a href="/issue/create/${project.id }">New issue</a>&nbsp;
        </div>
        <br>
        <h3>Issues</h3>
        <div>
            <c:choose>
                <c:when test="${project.getIssues().size() > 0 }">
	                <table class="datatable">
		                  <tr class="column-header">
		                      <th>ID</th>
		                      <th>Subject</th>
		                      <th>Author</th>
		                      <th>Assigned To</th>
		                      <th>Last updated</th>
		                  </tr>
			            <c:forEach items="${project.getIssues() }" var="issue">
	                    <tr>
	                        <td>${issue.id }</td>
	                        <td><a href="/issue/overview/${issue.id }">${issue.subject }</a></td>
	                        <td><img src="/avatar/small/${issue.owner.id }">${issue.owner.fullName }</td>
	                        <td><img src="/avatar/small/${issue.performer.id }">${issue.performer.fullName }</td>
	                        <td>${issue.updated }</td>
	                    </tr>
	                    </c:forEach> 		                
	                </table>
                </c:when>
	            <c:otherwise>
	                No any issues for this project.
	            </c:otherwise>
            </c:choose>
        </div>

    </jsp:attribute>
    
</t:template>