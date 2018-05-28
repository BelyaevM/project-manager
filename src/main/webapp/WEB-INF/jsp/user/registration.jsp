<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<t:template title="Login">
    <jsp:attribute name="content">
        <h1>Registration New User</h1>
        <br>
	    <form:form action="/user/registration" modelAttribute="user">
	        <label for="username">Email</label><form:input path="email" />&nbsp;<form:errors path="email" /><br>
	        <label for="firstName">First name</label><form:input path="firstName" />&nbsp;<form:errors path="firstName" /><br>
	        <label for="lastName">Last name</label><form:input path="lastName" />&nbsp;<form:errors path="lastName" /><br>
	        <label for="password">Password</label><form:password path="password"/>&nbsp;<form:errors path="password" /><br>
	        <label for="passwordConfirm">Password confirmation</label><form:password path="passwordConfirm"/>&nbsp;<form:errors path="passwordConfirm" /><br>
	        <sec:csrfInput/>
	        <div class="form-actions">
	            <input type="submit" value="Register" />
	        </div>
	    </form:form>
	    <br>
    </jsp:attribute>
    
</t:template>