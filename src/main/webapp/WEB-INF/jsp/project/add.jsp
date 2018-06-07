<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:template title="Add project">
    <jsp:attribute name="content">
        <h1>Create project</h1>
        <br>
	    <form:form action="/project/add" modelAttribute="projectForm">

	        <label for="username">Name</label><form:input path="name" />&nbsp;<form:errors path="name" /><br>
	        <label for="firstName">Description</label><form:textarea path="description" cols="60" rows="10" />&nbsp;<form:errors path="description" /><br>
	        
	        <div class="form-actions">
	            <input type="submit" value="Create project" />
	        </div>
	    </form:form>
	    <br>
    </jsp:attribute>
    
</t:template>