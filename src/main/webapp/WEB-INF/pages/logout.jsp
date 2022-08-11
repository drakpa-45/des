<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%
    session.invalidate();
    response.sendRedirect("https://www.citizenservices.gov.bt/cas/logout");
%>
</body>
</html>