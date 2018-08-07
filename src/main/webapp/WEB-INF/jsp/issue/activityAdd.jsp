<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<t:template title="Add project">
    <jsp:attribute name="head">
        <link rel="stylesheet" type="text/css" href="/css/datepicker.css">
    </jsp:attribute>


    <jsp:attribute name="content">
        <h1>${issue.subject }: Activity report</h1>
	    <form:form action="/issue/${issueId }/activity/new" modelAttribute="activityCreateForm">
	       <form:hidden path="issueId"/>
	       <form:label path="created" for="due">Due to</form:label><form:input class="datepicker-here" data-date-format="dd/mm/yyyy" path="created" />&nbsp;<form:errors path="created" /><br><br>
	       <form:label path="spentTime" for="spentTime">Spent time (min)</form:label><form:input path="spentTime" />&nbsp;<form:errors path="spentTime" /><br><br>
	       <label for="description">Comment</label><form:input path="description" />&nbsp;<form:errors path="description" /><br>
	        
	       <div class="form-actions">
	           <input type="submit" value="Create" />
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