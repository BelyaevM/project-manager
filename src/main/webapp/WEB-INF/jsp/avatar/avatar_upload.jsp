<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:message code="avatar.upload.title" var="pageTitle" />

<t:template title="${pageTitle }">
    <jsp:attribute name="content">
        <h1>${pageTitle }</h1>

        <form:form method="POST" action="/avatar/upload" enctype="multipart/form-data">
		    <table>
		        <tr>
		            <td><spring:message code="form.label.file" /></td>
		            <td><input type="file" name="files" /></td>
		        </tr>
		        <tr>
		            <td><input type="submit" value="<spring:message code="form.label.upload.submit" />" /></td>
		        </tr>
		    </table>
		</form:form>
        
    </jsp:attribute>
    
</t:template>