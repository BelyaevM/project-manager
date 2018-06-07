<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<t:template title="Add project">
    <jsp:attribute name="content">
        <h1>Create project</h1>
        <br>
	    <form:form action="/project/settings" modelAttribute="projectSettingsForm">
            <form:hidden path="id"/>
	        <label for="username">Name</label><form:input path="name" />&nbsp;<form:errors path="name" /><br>
	        <form:label path="description" for="firstName">Description</form:label><form:textarea path="description" cols="60" rows="10" />&nbsp;<form:errors path="description" /><br>
	        <form:label path="prjStatus" for="prjStatus">Status</form:label><form:select path="prjStatus" items="${projectStatuses }" />&nbsp;<form:errors path="prjStatus" /><br><br>
	        <sec:csrfInput/>
	        <div class="form-actions">
	            <input type="submit" value="Update project" />
	        </div>
	    </form:form>
	    <br>
    </jsp:attribute>
    
</t:template>