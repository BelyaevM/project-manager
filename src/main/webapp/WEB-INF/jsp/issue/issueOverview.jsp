<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template title="Issue">
    <jsp:attribute name="content">
    
        <h1>${issue.description }</h1>
        
        <div>            
            <a href="/issue/${issue.id }/activity/new">Add activity</a>&nbsp;&nbsp;
            <a href="">Update</a>
        </div>
        <br>

        <div>
            <span><img style="vertical-align: middle;" src="/avatar/big/${issue.owner.id }"> Added by: ${issue.owner.fullName } </span><br>
        </div>
        
        <div style="padding: 0.5em; margin-top: 0.5em; border: 1px solid #e4e4e4; width: 70%;">        
	        <h3>Last 10 activities</h3>
	        <div>
                <table class="datatable">
                  <tr class="column-header">
                      <th>User</th>
                      <th>Description</th>
                      <th>Spent time</th>
                      <th>Updated</th>
                  </tr>
    
                 <c:forEach items="${activityList }" var="activity">
                    <tr>
                        <td><img src="/avatar/small/${activity.user.id }" style="vertical-align: middle;"> ${activity.user.fullName }</td>
                        <td>${activity.description }</td>
                        <td>${activity.spentTime }</td>
                        <td>${activity.updated }</td>
                    </tr>
                 </c:forEach>             
                  
                </table>
	        </div>
        </div>

    </jsp:attribute>
    
</t:template>