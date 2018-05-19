<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:template title="Project Manager">
	<jsp:attribute name="content">
		<h1><spring:message code="title.label"/></h1>

        <div>
            <h2>Test (remove on producion).</h2>
            <a href="/test/jdbc">Test jdbc</a><br>
            <a href="/test/jpa">Test jpa</a><br>
        </div>
        
        <form method="post" action="/logout">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        	<input type="submit" value="Logout">
        </form>
        
	</jsp:attribute>
	
</t:template>