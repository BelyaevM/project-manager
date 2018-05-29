<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:message code="user.registration.title" var="pageTitle" />

<t:template title="${pageTitle }">
    <jsp:attribute name="content">
        <h1>${pageTitle }</h1>
        <br>
	    <form:form action="/user/registration" modelAttribute="user">
	        <label for="email"><spring:message code="form.label.email" /></label><form:input path="email" />&nbsp;<form:errors path="email" /><br>
	        <label for="firstName"><spring:message code="form.label.firstName" /></label><form:input path="firstName" />&nbsp;<form:errors path="firstName" /><br>
	        <label for="lastName"><spring:message code="form.label.lastName" /></label><form:input path="lastName" />&nbsp;<form:errors path="lastName" /><br>
	        <label for="password"><spring:message code="form.label.password" /></label><form:password path="password"/>&nbsp;<form:errors path="password" /><br>
	        <label for="passwordConfirm"><spring:message code="form.label.password.confirm" /></label><form:password path="passwordConfirm"/>&nbsp;<form:errors path="passwordConfirm" /><br>
	        <sec:csrfInput/>
	        <div class="form-actions">
	            <input type="submit" value="Register" />
	        </div>
	    </form:form>
	    <br>
    </jsp:attribute>
    
</t:template>