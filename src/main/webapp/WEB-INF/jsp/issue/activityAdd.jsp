<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:template title="Add project">
    <jsp:attribute name="content">
        <h1>Spent time</h1>
        <br>
        <div>
            ${issue.subject }
        </div>
	    <form:form action="/issue/${issue.id }/activity/new" modelAttribute="activityCreateForm">
	        <form:label path="created" for="due">Due to (dd/MM/yyyy)</form:label><form:input path="created" />&nbsp;<form:errors path="created" /><br><br>
	        <form:label path="spentTime" for="spentTime">Spent time (min)</form:label><form:input path="spentTime" />&nbsp;<form:errors path="spentTime" /><br><br>
	        <label for="description">Comment</label><form:input path="description" />&nbsp;<form:errors path="description" /><br>
	        
	        <div class="form-actions">
	            <input type="submit" value="Create" />
	        </div>
	    </form:form>
	    <br>
    </jsp:attribute>
    
</t:template>