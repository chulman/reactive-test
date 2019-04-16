<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>
        <spring:message code="welcome.title" text="Welcome"/>
    </title>
</head>
<body>
Locale : ${pageContext.response.locale}

<h2>
    <spring:message code="welcome.message" text="Welcome to the.."/>
</h2>
<br/>
</body>
</html>