<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bt.gov.ditt.does.Dto.MenuDto" %>
<%@ page import="bt.gov.ditt.does.Dto.UserRolePrivilege" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext">

<script>
    requirejs.config({
        baseUrl: '.'
    });
</script>
<link rel="stylesheet" href="<c:url value="/resources/assets/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/assets/css/dashboard.css"/>"/>
<%
    UserRolePrivilege userdet = (UserRolePrivilege) session.getAttribute("UserRolePrivilege");
    boolean hasAdmin = false;
    boolean hasBuilding = false;
    boolean hasSewerage = false;
    boolean hasWater = false;
    boolean hasGrievance = false;
    boolean hasEmployeeRecords = false;
/*    System.out.print("User_Role" + userdet.getCurrentRole().getRoleName());*/

%>
<%  List<MenuDto> menuLists = (List<MenuDto>) request.getSession().getAttribute("Menudetails");
%>

<div class="header py-2" style="background: #120f65;">
    <div class="container">
        <div class="d-flex">
            <a class="header-brand" href="#">
            </a>
            <h5 class="text-white"><br />Government to citizen service delivery initiative</h5>
            <div class="d-flex order-lg-2 ml-auto">
                <div class="dropdown">
                    <a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown">
                        <span class="avatar"><i class="fe fe-user glyph__icon"></i></span>
                  <span class="ml-2 d-none d-lg-block">
                  <span class="text-muted"><%=userdet.getFullName()%></span>
                  </span>.
                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
                        <a class="dropdown-item text-dark" href="#">
                            <i class="dropdown-icon fe fe-help-circle"></i> Need help?
                        </a>
                        <a class="dropdown-item text-dark" href="<c:url value="/common/logout"/>">
                            <i class="dropdown-icon fe fe-log-out"></i> Sign out
                        </a>
                        </div>
                    </div>
                </div>
            </div>
            <a href="#" class="header-toggler d-lg-none ml-3 ml-lg-0" data-toggle="collapse" data-target="#headerMenuCollapse">
                <span class="header-toggler-icon"></span>
            </a>
        </div>
    </div>
</div>
<div class="header collapse d-lg-flex p-0" id="headerMenuCollapse"style="background-color: #fff;">
    <div class="container">
        <div class="row pull-left" >
            <div class="col-lg order-lg-first">
                <strong>
                    <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                        <li class="nav-item">
                            <a href="#" onclick="loadPage('tasklist')" class="nav-link "style="color:black!important"><i class="fa fa-home"></i> Tasklist</a>
                        </li>
                        <% if (userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("1")){%>
                        <li class="nav-item"style="color:black!important">
                            <a href="javascript:void(0)" class="nav-link" style="color:black!important" data-toggle="dropdown"><i class="fa fa-building"></i>Building <i class="fa fa-chevron-down"></i></a>
                            <div class="dropdown-menu dropdown-menu-arrow">
                                <a href="#" onclick="loadPage('buildingForm');" style="color:black!important" class="dropdown-item ">Drawing Approval</a>
                            </div>
                        </li>
                        <%}%>
                        <li class="nav-item">
                            <a href="#" onclick="loadPage('/searchApplication')" class="nav-link "style="color:black!important"><i class="fa fa-search"></i> Track Application</a>
                        </li>
                        <% if (userdet.getCurrentRole().getRoleName().equals("DOES Operater") || userdet.getCurrentRole().getRoleName().equalsIgnoreCase("DZO_OPERATOR")){%>
                        <li class="nav-item" style="display: none">
                            <a href="#" onclick="loadPage('reports')" class="nav-link "style="color:black!important"><i class="fa fa-file-alt"></i> Reports</a>
                        </li>
                        <%} else {%>
                        <li class="nav-item">
                            <a href="#" onclick="loadPage('reports')" class="nav-link "style="color:black!important"><i class="fa fa-file-alt"></i> Reports</a>
                        </li>
                        <%}%>
                    </ul>
                </strong>
            </div>
        </div>
    </div>
</div>
<script>
    function loadPage(type) {
        var url='${pageContext.request.contextPath}/common/loadPage?parameter='+type;
        /*$("#mainAction").load(url);*/
        window.location=url;
    }
    function loadTasklist(){
        var url='${pageContext.request.contextPath}/common/loadTasklist';
        window.location=url;
    }
    function administrator(userId){
        window.location.href="https://www.citizenservices.gov.bt/tt-hr/LoginAction.do?userId="+ userId;
    }
</script>
