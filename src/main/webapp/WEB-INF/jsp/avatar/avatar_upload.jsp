<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<t:template title="Load avatar">
    <jsp:attribute name="content">
        <h1>Select file</h1>

        <form:form method="POST" action="/avatar/upload" enctype="multipart/form-data">
		    <table>
		        <tr>
		            <td>Select a file to upload</td>
		            <td><input type="file" name="files" /></td>
		        </tr>
		        <tr>
		            <td><input type="submit" value="Submit" /></td>
		        </tr>
		    </table>
		</form:form>
        
    </jsp:attribute>
    
</t:template>