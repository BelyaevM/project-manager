<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:message code="user.list.title" var="pageTitle" />

<t:template title="${pageTitle }">
    <jsp:attribute name="head">
        <sec:csrfMetaTags/>
    </jsp:attribute>

    <jsp:attribute name="content">
        <h1>${pageTitle }</h1>
        
        <c:if test="${users.size() > 0 }">
           <div style="padding: 0.5em;">
	          <input class="inputForm" id="userFilter" type="text" placeholder="Last or first name" />
	          <input type="button" onclick="ajaxEmployerListUpdate($('#userFilter'));" value="Search">
           </div>
        
            <table class="datatable" id="usersList">
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
    
         <div style="display: none;">
	         <table>
	             <tr id="templateRow">
	                 <td></td>
	                 <td></td>
	                 <td></td>
	                 <td></td>
	                 <td></td>
	                 <td><a class="templateEdit" href=""><spring:message code="user.list.settings" /></a></td>
	             </tr>
	         </table>
          </div>

    </jsp:attribute>
    
    <jsp:attribute name="appscript">
        <script src="/js/projmanager.js"></script>
    </jsp:attribute>
    
</t:template>