<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true"%>
<%@attribute name="content" fragment="true"%>
<%@attribute name="appscript" fragment="true"%>
<%@attribute name="head" fragment="true"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="project.title" /> - ${title }</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<jsp:invoke fragment="head" />
</head>

<body id="body">
   <div class="menu">
        <div class="mainMenu">
            <sec:authorize access="hasRole('ADMIN')">
                <a href="/user/list"><spring:message code="main.menu.label.users.list" /></a><br>
            </sec:authorize>
        </div>
        <div class="rightMenu">
            <c:url value="${requestScope['javax.servlet.forward.request_uri']}" var="langEn">
                <c:param name="lang" value="en"/>
            </c:url>
            <c:url value="${requestScope['javax.servlet.forward.request_uri']}" var="langRu">
                <c:param name="lang" value="ru"/>
            </c:url>
            <a href="${langEn }"><spring:message code="english.label" /></a>
            <a href="${langRu }"><spring:message code="russian.label" /></a>            
            <sec:authorize access="isAuthenticated()">
                &nbsp;&nbsp;
                <a href="/profile/view"><spring:message code="menu.label.profile" /></a>
                <form id="logoutForm" action="/logout" method="post">
                    <sec:csrfInput/>
                    <a class="logout" href="<c:url value="/logout" />" onclick="$('#logoutForm').submit(); return false;"><spring:message code="form.label.logout" /></a>
                </form>                
            </sec:authorize>
        </div>
        <div class="sep"></div>
    </div>
	<header class="site-header">
		<div class="header-content">
			<h2>
				<a href="/"><spring:message code="project.title" /></a>
			</h2>
		</div>
	</header>
	<div class="content">
		<jsp:invoke fragment="content" />
	</div>

    <script src="/js/jquery-3.3.1.min.js"></script>
	<jsp:invoke fragment="appscript" />
</body>

</html>
