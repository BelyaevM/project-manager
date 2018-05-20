<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template title="Login">
    <jsp:attribute name="content">
        <h1><spring:message code="loginForm.title"/></h1>

        <div style="margin: 0 auto; width: 50%">
		    <form action="/login" method="post">
			    <c:if test="${param.error != null}">
			        <p><spring:message code="loginForm.error" /></p>
			    </c:if>
		        <label for="username"><spring:message code="form.label.email" /></label><input type="text" id="username" name="username" /><br> 
		        <label for="password"><spring:message code="form.label.password" /></label><input type="password" id="password" name="password" /><br>
		        <label for="remember-me"><spring:message code="form.label.rememberme" /></label><input type="checkbox" name="remember-me" id="remember-me">
		        <div class="form-actions">
		            <input type="submit" value='<spring:message code="form.label.login"/>' />
		        </div>
		        <sec:csrfInput />
		    </form>
		    <br>
	    </div>
                
    
    </jsp:attribute>
    
</t:template>