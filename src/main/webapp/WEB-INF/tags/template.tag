<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="title" required="true" rtexprvalue="true"%>
<%@attribute name="content" fragment="true"%>
<%@attribute name="appscript" fragment="true"%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="project.title" /> - ${title }</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
</head>

<body id="body">
   <div class="menu">
        <div class="mainMenu">
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
		<jsp:invoke fragment="content"></jsp:invoke>
	</div>

    <script src="/js/jquery-3.3.1.min.js"></script>
	<jsp:invoke fragment="appscript"></jsp:invoke>
</body>

</html>
