<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:template title="Create issue">
    <jsp:attribute name="content">
        <h1>Create new issue for project </h1>
        <br>
	    <form:form action="/issue/create" modelAttribute="issueCreateForm">
            <form:hidden path="projectId"/>
	        <label for="username">Subject</label><form:input path="subject" />&nbsp;<form:errors path="subject" /><br>
	        <label for="firstName">Description</label><form:textarea path="description" />&nbsp;<form:errors path="description" /><br>
	        <form:label path="${perfomerId}" for="performerId">Assigned to</form:label><form:select path="performerId" items="${performers}" />&nbsp;<form:errors path="performerId" /><br><br>
	        <form:label path="${due}" for="due">Due to (dd/MM/yyyy)</form:label><form:input path="due" />&nbsp;<form:errors path="due" /><br><br>
	        <form:label path="${issueStatus}" for="issueStatus">Status</form:label><form:select path="issueStatus" items="${issueStatuses }" />&nbsp;<form:errors path="issueStatus" /><br><br>
	        <form:label path="${priority}" for="issueStatus">Priority</form:label><form:select path="priority" items="${issuePriorities }" />&nbsp;<form:errors path="priority" /><br><br>
	        
	        <div class="form-actions">
	            <input type="submit" value="Create issue" />
	        </div>
	    </form:form>
	    <br>
    </jsp:attribute>
    
</t:template>