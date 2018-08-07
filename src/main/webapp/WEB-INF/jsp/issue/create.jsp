<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:message code="issue.create.title" var="pageTitle" />

<t:template title="${pageTitle }">
    <jsp:attribute name="head">
        <link rel="stylesheet" type="text/css" href="/css/datepicker.css">
    </jsp:attribute>

    <jsp:attribute name="content">
        <h1>${pageTitle }</h1>
        <br>
	    <form:form action="/issue/create" modelAttribute="issueCreateForm">
            <form:hidden path="projectId"/>
	        <label for="username">Subject</label><form:input path="subject" />&nbsp;<form:errors path="subject" /><br>
	        <label for="firstName">Description</label><form:textarea path="description" cols="60" rows="10" />&nbsp;<form:errors path="description" /><br>
	        <form:label path="${perfomerId}" for="performerId">Assigned to</form:label><form:select path="performerId" items="${performers}" />&nbsp;<form:errors path="performerId" /><br><br>
	        <form:label path="${due}" for="due">Due to</form:label><form:input path="due" class="datepicker-here" data-date-format="dd/mm/yyyy" />&nbsp;<form:errors path="due" /><br><br>
	        <form:label path="${issueStatus}" for="issueStatus">Status</form:label><form:select path="issueStatus" items="${issueStatuses }" />&nbsp;<form:errors path="issueStatus" /><br><br>
	        <form:label path="${priority}" for="issueStatus">Priority</form:label><form:select path="priority" items="${issuePriorities }" />&nbsp;<form:errors path="priority" /><br><br>
	        
	        <div class="form-actions">
	            <input type="submit" value="Create issue" />
	        </div>
	    </form:form>
	    <br>
    </jsp:attribute>
    <jsp:attribute name="appscript">
        <script src="/js/datepicker.min.js"></script>
        <script>
            $('.datepicker-here').datepicker({
                todayButton: new Date(),
                autoClose: true
            })        
        </script>
    </jsp:attribute>
    
</t:template>