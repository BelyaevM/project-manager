<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:message code="user.list.title" var="pageTitle" />

<t:template title="${pageTitle }">
    <jsp:attribute name="content">
        <h1>${pageTitle }</h1>
        
        <c:if test="${users.size() > 0 }">
            <table class="datatable">
              <tr class="column-header">
                  <th><spring:message code="form.label.id" /></th>
                  <th><spring:message code="form.label.email" /></th>
                  <th><spring:message code="form.label.name" /></th>
                  <th><spring:message code="form.label.roles" /></th>
                  <th><spring:message code="form.label.active" /></th>
                  <th><spring:message code="form.label.action" /></th>
              </tr>

             <c:forEach items="${users }" var="user">
                <tr>
                    <td><c:out value="${user.id }"/></td>
                    <td><c:out value="${user.email }"/></td>
                    <td><c:out value="${user.fullName }"/></td>
                    <td><c:out value="${user.highLevelRole }"/></td>
                    <td><c:out value="${user.enabled }" /></td>
                    <td><a href="/user/settings/${user.id }"><spring:message code="user.list.settings" /></a></td>
                </tr>
             </c:forEach>             
              
            </table>
        </c:if>
        <br>
        <div>
            <a href="/user/registration"><spring:message code="user.list.add.new" /></a>
        </div>
    
    </jsp:attribute>
    
</t:template>