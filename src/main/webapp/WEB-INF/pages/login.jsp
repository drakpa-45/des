<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>G2C login</title>
</head>
<body>
<div class="card card-primary" style="margin-top:25px;">
    <div class="card-body">
        <div class="row">
            <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
           </div>
            <div class="col-md-4 col-sm-4-col-lg-4 col-xs-12">
                <h5>For official only</h5>
                <hr/>
                <c:if test="${not empty error}">
                    <div style="color:red; font-weight: bold; margin: 30px 0px;">${error}</div>
                </c:if>
                <c:if test="${not empty expired}">
                    <div style="color:blue; font-weight: bold; margin: 30px 0px;">${expired}</div>
                </c:if>
                <form name='login' id="loginformId" action="<c:url value="/common/login"/>" method='POST'>
                        ${success_message}
                    <div class="form-group">
                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                            <input type="text" placeholder="User Name" required name="userId" id="userId" class="form-control">
                            <span class="text-danger" id="user_Err"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                            <input type="password" placeholder="Password" required name="password" name="password" id="password" class="form-control">
                            <span class="text-danger" id="pass_Err"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12 col-sm-12-col-lg-12 col-xs-12">
                            <button type="submit" class="btn btn-primary"><i class="fa fa-user"></i>Login</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>