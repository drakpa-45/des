<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>
<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: deepak
  Date: 5/16/19
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><sitemesh:write property='title'/></title>
    <sitemesh:write property='head'/>
    <jsp:include page="inc/css.jsp"></jsp:include>
    <jsp:include page="inc/js.jsp"></jsp:include>
    <jsp:include page="inc/context.jsp"></jsp:include>
</head>

<body>

<%--<jsp:include page="inc/header2.jsp"/>--%>
<div class="col-12" style="margin-top:20px;min-height: 440px;">
    <div class="col-12">
        <div class="col-12" id="mainLayout">
            <sitemesh:write property='body'/>
        </div>
    </div>
</div>
<jsp:include page="inc/footer.jsp"></jsp:include>
</body>
<script>
    $('[data-toggle="tooltip"]').tooltip();
    function validateAttach(vl, id, checkId) {
        if (vl != "") {
            $('#' + id).prop('class', 'alert badge-info');
            $('#' + checkId).prop('class', 'fa fa-check pl-2');
        }
        else {
            $('#' + id).prop('class', 'alert badge-danger');
            $('#' + checkId).prop('class', 'fa fa-times pl-2');
        }
    }
</script>
</html>
