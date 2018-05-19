<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:template title="Login">
    <jsp:attribute name="content">
        <h1>Login</h1>

        <div style="margin: 0 auto; width: 50%">
		    <form action="/login" method="post">
			    <c:if test="${param.error != null}">
			        <p>Invalid username and password.</p>
			    </c:if>
			    <c:if test="${param.logout != null}">
			        <p>You have been logged out.</p>
			    </c:if>		    
		        <label for="username">Email</label><input type="text" id="username" name="username" /><br> 
		        <label for="password">Password</label><input type="password" id="password" name="password" /><br>
		        <label for="remember-me">Remember me</label><input type="checkbox" name="remember-me" id="remember-me">
		        <div class="form-actions">
		            <input type="submit" value="Log in" />
		        </div>
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		    </form>
		    <br>
	    </div>
                
    
    </jsp:attribute>
    
</t:template>