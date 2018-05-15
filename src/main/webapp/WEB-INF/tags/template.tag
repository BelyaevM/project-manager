<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="title" required="true" rtexprvalue="true"%>
<%@attribute name="content" fragment="true"%>
<%@attribute name="appscript" fragment="true"%>

<!DOCTYPE html>
<html>
<head>
<title>Project-manager - ${title }</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
</head>

<body id="body">
    <div class="menu">
    </div>
	<header class="site-header">
		<div class="header-content">
			<h2>
				<a href="/">Project-manager</a>
			</h2>
		</div>
	</header>
	<div class="content">
		<jsp:invoke fragment="content"></jsp:invoke>
	</div>

	<jsp:invoke fragment="appscript"></jsp:invoke>
</body>

</html>
