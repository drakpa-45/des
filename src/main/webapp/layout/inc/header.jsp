
<%--
  Created by IntelliJ IDEA.
  User: deepak
  Date: 5/17/19
  Time: 10:14 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Language" content="en"/>
    <meta name="msapplication-TileColor" content="#2d89ef">
    <meta name="theme-color" content="#4188c9">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <title>Home</title>
</head>
<body>
<div class="header py-3" style="background:#120f65;">
    <div class="container">
        <div class="d-flex">
            <img src="<c:url value="/resources/images/logo.png"/>" class="header-brand-img" alt="tabler logo"
                 style="height:80px; width:80px;">
            <h3 class="text-white mt-3">&nbsp;&nbsp;Government to citizen service delivery initiative</h3>
            <div class="d-flex order-lg-2 ml-auto mt-5">
                <%--<div class="dropdown">--%>
                    <a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown">
                        <span class="avatar" style="background-image: url(/resources/assets/images/ip.jpeg)"></span>
                            <span class="ml-2 d-none d-lg-block">
                        <span class="text-muted">Tshewang Tenzin</span>
                            <small class="text-muted d-block mt-1">Administrator</small>
                            </span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
                        <a class="dropdown-item" href="#">
                            <i class="dropdown-icon fe fe-help-circle"></i> Need help?
                        </a>
                        <a class="dropdown-item" href="/common/logout">
                            <i class="dropdown-icon fe fe-log-out"></i> Sign out
                        </a>
                    </div>
                <%--</div>--%>
            </div>
            </div>
        </div>
    </div>
<div class="header collapse d-lg-flex p-0" id="headerMenuCollapse">
    <div class="container">
        <div class="row pull-left">
            <div class="col-lg order-lg-first">
                <nav class="navbar navbar-expand-lg navbar-dark">
                      <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                      </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">

                            <%--Material menu start--%>
                            <li class="nav-item" >
                                <a href="javascript:void(0)" class="menu-padding"  data-toggle="dropdown" > Master Management  &nbsp;<i class="fa fa-chevron-down"></i></a>
                                <ul class="dropdown-menu dropdown-menu-arrow">
                                    <li class="has-dropdown"> <a class="dropdown-item">Labour </a>
                                        <ul class="dropdown-sub-menu">
                                            <li><a href="${pageContext.request.contextPath}/labour/labourType"  class="dropdown-item ">Labour Type</a></li>
                                            <li><a href="${pageContext.request.contextPath}/labour/labourDetail" class="dropdown-item ">Labour</a></li>
                                        </ul>
                                    </li>
                                    <li class="has-dropdown">
                                        <a class="dropdown-item ">Material</a>
                                            <ul class="dropdown-sub-menu">
                                                <li><a href="${pageContext.request.contextPath}/material/materialSubType" class="dropdown-item ">Material Category</a></li>
                                                <li><a href="${pageContext.request.contextPath}/material/TypeDetails" class="dropdown-item ">Material Type</a></li>
                                                <li><a href="${pageContext.request.contextPath}/material/fetch_details" class="dropdown-item ">Material</a></li>
                                            </ul>
                                    </li>
                                    <li class="has-dropdown">
                                        <a class="dropdown-item ">Machinery</a>
                                        <ul class="dropdown-sub-menu">
                                            <li><a href="${pageContext.request.contextPath}/machinery/machineryTypeDetails" class="dropdown-item ">Machinery Type</a></li>
                                            <li><a href="${pageContext.request.contextPath}/machinery/fetchDetails" class="dropdown-item ">Machinery </a></li>
                                        </ul>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/job/fetch_details" class="dropdown-item ">Job Type</a></li>
                                    <li><a href="${pageContext.request.contextPath}/job/jobMasterDetails" class="dropdown-item ">Job</a></li>
                                    <li><a href="${pageContext.request.contextPath}/percentage/fetch_details" class="dropdown-item ">Percentage</a></li>
                                    <li><a href="#" class="dropdown-item ">Year</a></li>
                                    <li><a href="${pageContext.request.contextPath}/location/getData" class="dropdown-item ">Base Location</a></li>
                                    <li><a href="${pageContext.request.contextPath}/vendor/fetch_details" class="dropdown-item ">Vendor</a></li>
                                </ul>
                            </li>
                            <%--Material tab end!!--%>
                            &nbsp; &nbsp;&nbsp;&nbsp;
                            <li class="nav-item">
                                <a href="javascript:void(0)" class="menu-padding"  data-toggle="dropdown" > Rates &nbsp;<i class="fa fa-chevron-down"></i></a>
                                <ul class="dropdown-menu dropdown-menu-arrow" style=" margin-top:2px;">
                                    <li class="has-dropdown"> <a href="${pageContext.request.contextPath}/vendor/vendor_rate" class="dropdown-item ">Vendor Rates</a></li>
                                    <li class="has-dropdown"> <a href="${pageContext.request.contextPath}/vendor/vendor_rate_evu" class="dropdown-item ">Rate Evaluation</a></li>
                                </ul>
                            </li>
                            &nbsp; &nbsp;&nbsp;&nbsp;
                            <li class="nav-item">
                                <a href="javascript:void(0)" class="menu-padding" style="" data-toggle="dropdown" > Report  &nbsp;<i class="fa fa-chevron-down"></i></a>
                                <ul class="dropdown-menu dropdown-menu-arrow" style=" margin-top:2px;">

                                    <li class="has-dropdown"> <a href="${pageContext.request.contextPath}/report/BhutanScheduleRate" class="dropdown-item ">Bhutan Schedule of Rates</a></li>
                                    <li class="has-dropdown"> <a href="${pageContext.request.contextPath}/report/generate_lmc" class="dropdown-item ">Generates New LMC</a></li>
                                </ul>
                            </li>
                            &nbsp; &nbsp;&nbsp;&nbsp;
                            <li class="nav-item">
                                <a class="menu-padding"   href="<c:url value="/custom/getData"></c:url>">
                                    &nbsp;Custom Job <span class="sr-only">(current)</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</div>
</body>
</html>