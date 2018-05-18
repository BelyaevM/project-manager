<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:template title="Project Manager">
    <jsp:attribute name="content">
        <h1>JPA Test</h1>

        <div>
        
            <c:forEach items="${users}" var="user">
                <div>
                    ${user.id }: ${user.firstName } ${user.lastName }
                </div>
            </c:forEach>
        
        </div>
        
    </jsp:attribute>
    
</t:template>