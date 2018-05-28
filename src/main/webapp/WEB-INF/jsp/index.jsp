<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<t:template title="Project Manager">
	<jsp:attribute name="content">
		<h1>Home page</h1>

        <div>
            <h2>Test (remove on producion).</h2>
            <a href="/test/jdbc">Test jdbc</a><br>
            <a href="/test/jpa">Test jpa</a><br>
        </div>

	</jsp:attribute>
	
</t:template>