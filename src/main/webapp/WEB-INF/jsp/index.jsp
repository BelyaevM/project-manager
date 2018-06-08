<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:message code="home.page.title" var="pageTitle" />

<t:template title="${pageTitle }">
	<jsp:attribute name="content">
		<h1>${pageTitle }</h1>

        <div class="info-block">
            <h3><spring:message code="home.page.issue.block" /></h3>

            <c:choose>
                <c:when test="${issues.size() != null && issues.size() > 0 }">
                        <table class="datatable">
                            <tr class="column-header">
                                <th><spring:message code="form.label.project" /></th>
                                <th><spring:message code="form.label.task" /></th>
                                <th><spring:message code="form.label.priority" /></th>
                                <th><spring:message code="form.label.status" /></th>
                            </tr>
                            <c:forEach items="${issues }" var="issue">
                                <tr>
                                    <td><a href="/project/overview/${issue.project.id }"><c:out value="${issue.project.name }"/></a></td>
                                    <td><a href="/issue/overview/${issue.id }"><c:out value="${issue.subject }"/></a></td>
                                    <td><c:out value="${issue.priorityStr }"/></td>
                                    <td><c:out value="${issue.statusStr }"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <!-- <a href="/issues/all"><spring:message code="home.page.view.all" /></a>  -->
                </c:when>
                <c:otherwise>
                    <spring:message code="home.page.issue.empty.list" />
                </c:otherwise>
            </c:choose>
        </div>
        <br/>
        <div class="info-block">
            <h3><spring:message code="home.page.activity.block" /></h3>
            <c:choose>
                <c:when test="${activities.size() != null && activities.size() > 0 }">
                      <table class="datatable">
                          <tr class="column-header">
                              <th><spring:message code="form.label.project" /></th>
                              <th><spring:message code="form.label.task" /></th>
                              <th><spring:message code="form.label.description" /></th>
                              <th><spring:message code="form.label.spent.time" /></th>
                          </tr>
                          <c:forEach items="${activities }" var="activity">
                              <tr>
                                  <td><a href="/project/overview/${activity.project.id }"><c:out value="${activity.project.name }"/></a></td>
                                  <td><a href="/issue/overview/${activity.issue.id }"><c:out value="${activity.issue.description }"/></a></td>
                                  <td><c:out value="${activity.description }"/></td>
                                  <td><c:out value="${activity.spentTime }"/></td>
                              </tr>
                          </c:forEach>
                      </table>
                </c:when>
                <c:otherwise>
                    <spring:message code="home.page.issue.empty.list" />
                </c:otherwise>
            </c:choose>
        </div>
	</jsp:attribute>
	
</t:template>