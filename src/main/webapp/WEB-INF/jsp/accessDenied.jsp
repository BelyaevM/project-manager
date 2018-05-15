<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:template title="Project Manager">
    <jsp:attribute name="content">
		<h2>Sorry, you do not have permission to view this page.</h2>
		 
		Click <a href="<c:url value="/" /> ">here</a> to go back to the Homepage.        
    </jsp:attribute>
    
</t:template>
