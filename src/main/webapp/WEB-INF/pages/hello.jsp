<%@ page import="bt.gov.ditt.does.Dto.UserRolePrivilegeDES" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    UserRolePrivilegeDES userBean = (UserRolePrivilegeDES) session.getAttribute("UserRolePrivilege");
    System.out.println("UserName" + userBean.getJurisdictions()[0].getJurisdiction());
%>
<html>

<body>
<h1>${message}</h1>
<%if (userBean.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("1")){%>
    <div class="card">
        <div class="card-body">
            <h5 style="text-align:center;">Dzongkhag Dashboard</h5>
            <div class="row">
                <div class="col-md-3 col-sm-3 col-lg-3 col-xs-12">
                    <div class="card">
                        <div class="card-status bg-red-light"></div>
                        <div class="card-body text-center">
                            <a class="h5" href="#" >
                                Customer Operator Section
                                <br><br>
                            </a>
                            <c:forEach items="${totalApplication6}" var="count">
                                <p style="text-align:left;"><span style="font-weight: bold">Stages</span><label class="float-right"><span style="font-weight: bold">No. of Applications</span></label></p>
                                <p style="text-align:left;"> Initiated:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountInitiated}"/></span></label></p>
                                <p style="text-align:left;"> Claimed:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountClaimed}"/></span></label></p>
                                <p style="text-align:left;"> Approved:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountApproved}"/></span></label></p>
                                <p style="text-align:left;"> Hold:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountHold}"/></span></label></p>
                                <p style="text-align:left;"> Rejected:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountRejected}"/></span></label></p>

                                <p style="text-align:left;"> Forwarded to DES:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountForwarded}"/></span></label></p>

                                <p style="text-align:left;font-weight: bold">Total Application:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold; font-size: 25px"><c:out value="${count.rowCountTotal}"/></span></label></p>
                                <%--<label class="float-left"><span style="font-weight: bold">Total Application:</span></label>
                                <div style="font-weight: bold"><span style="font-size: x-large">${count.rowCountTotal}</span></div>--%>
                                <div class="progress progress-sm">
                                    <c:choose>
                                        <c:when test="${count.rowCountTotal > 100}">
                                            <div class="progress-bar bg-red" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=100 && count.rowCountTotal > 80}">
                                            <div class="progress-bar bg-orange" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=80 && count.rowCountTotal > 60}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=60 && count.rowCountTotal >40}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=40 && count.rowCountTotal>20}">
                                            <div class="progress-bar bg-azure-darker" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=20 && count.rowCountTotal>10}">
                                            <div class="progress-bar bg-cyan-dark" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=10 && count.rowCountTotal>=5}">
                                            <div class="progress-bar bg-gray" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="progress-bar bg-blue-lighter" style="width:${count.rowCountTotal}"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-3 col-lg-3 col-xs-12">
                    <div class="card">
                        <div class="card-status bg-cyan-dark"></div>
                        <div class="card-body text-center">
                            <a class="h5" href="#">
                                Architectural Section
                            </a>
                            <br><br>
                            <c:forEach items="${totalApplication0}" var="count">
                                <p style="text-align:left;"><span style="font-weight: bold">Stages</span><label class="float-right"><span style="font-weight: bold">No. of Applications</span></label></p>

                                <p style="text-align:left;">Initiated:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountInitiated}"/></span></label></p>
                                <p style="text-align:left;">Claimed:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountClaimed}"/></span></label></p>
                                <p style="text-align:left;"> Approved:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountApproved}"/></span></label></p>
                                <p style="text-align:left;"> Hold:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountHold}"/></span></label></p>
                                <p style="text-align:left;"> Rejected:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountRejected}"/></span></label></p>
                                <p style="text-align:left;"> Forwarded to DES:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountForwarded}"/></span></label></p>

                                <p style="text-align:left;font-weight: bold">Total Application:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold; font-size: 25px"><c:out value="${count.rowCountTotal}"/></span></label></p>
                                <%--<label class="float-left"><span style="font-weight: bold">Total Application:</span></label>
                                <div style="font-weight: bold"><span style="font-size: x-large">${count.rowCountTotal}</span></div>--%>
                                <div class="progress progress-sm">
                                    <c:choose>
                                        <c:when test="${count.rowCountTotal > 100}">
                                            <div class="progress-bar bg-red" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=100 && count.rowCountTotal > 80}">
                                            <div class="progress-bar bg-orange" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=80 && count.rowCountTotal > 60}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=60 && count.rowCountTotal >40}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=40 && count.rowCountTotal>20}">
                                            <div class="progress-bar bg-azure-darker" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=20 && count.rowCountTotal>10}">
                                            <div class="progress-bar bg-cyan-dark" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=10 && count.rowCountTotal>=5}">
                                            <div class="progress-bar bg-gray" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="progress-bar bg-blue-lighter" style="width:${count.rowCountTotal}"></div>
                                        </c:otherwise>

                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-3 col-lg-3 col-xs-12">
                    <div class="card">
                        <div class="card-status bg-yellow-dark"></div>
                        <div class="card-body text-center">
                            <a class="h5" href="#" >
                                Electrical Section
                                <br><br>
                            </a>
                            <c:forEach items="${totalApplication1}" var="count">
                                <p style="text-align:left;"><span style="font-weight: bold">Stages</span><label class="float-right"><span style="font-weight: bold">No. of Applications</span></label></p>
                                <p style="text-align:left;">Initiated:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountInitiated}"/></span></label></p>
                                <p style="text-align:left;">Claimed:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountClaimed}"/></span></label></p>
                                <p style="text-align:left;"> Approved:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountApproved}"/></span></label></p>
                                <p style="text-align:left;"> Hold:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountHold}"/></span></label></p>
                                <p style="text-align:left;"> Rejected:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountRejected}"/></span></label></p>
                                <p style="text-align:left;"> Forwarded to DES:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountForwarded}"/></span></label></p>
                                <p style="text-align:left;font-weight: bold">Total Application:
                                    <label class="float-right" style="margin-right: 40px">
                                        <span style="font-weight: bold; font-size: 25px">
                                            <c:out value="${count.rowCountTotal}"/>
                                        </span>
                                    </label>
                                </p>
                                <%--<label class="float-left"><span style="font-weight: bold">Total Application:</span></label>
                                <div style="font-weight: bold"><span style="font-size: x-large">${count.rowCountTotal}</span></div>--%>
                                <div class="progress progress-sm">
                                    <c:choose>
                                        <c:when test="${count.rowCountTotal > 100}">
                                            <div class="progress-bar bg-red" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=100 && count.rowCountTotal > 80}">
                                            <div class="progress-bar bg-orange" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=80 && count.rowCountTotal > 60}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=60 && count.rowCountTotal >40}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=40 && count.rowCountTotal>20}">

                                            <div class="progress-bar bg-azure-darker" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=20 && count.rowCountTotal>10}">
                                            <div class="progress-bar bg-cyan-dark" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=10 && count.rowCountTotal>=5}">
                                            <div class="progress-bar bg-gray" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="progress-bar bg-blue-lighter" style="width:${count.rowCountTotal}"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-3 col-lg-3 col-xs-12">
                    <div class="card">
                        <div class="card-status bg-orange-darkest"></div>
                        <div class="card-body text-center">
                            <a class="h5" href="#">
                                Structural Section
                                <br><br>
                            </a>
                            <c:forEach items="${totalApplication2}" var="count">
                                <p style="text-align:left;"><span style="font-weight: bold">Stages</span><label class="float-right"><span style="font-weight: bold">No. of Applications</span></label></p>
                                <p style="text-align:left;">Initiated:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountInitiated}"/></span></label></p>
                                <p style="text-align:left;">Claimed:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountClaimed}"/></span></label></p>
                                <p style="text-align:left;"> Approved:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountApproved}"/></span></label></p>
                                <p style="text-align:left;"> Hold:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountHold}"/></span></label></p>
                                <p style="text-align:left;"> Rejected:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountRejected}"/></span></label></p>
                                <p style="text-align:left;"> Forwarded to DES:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountForwarded}"/></span></label></p>

                                <p style="text-align:left;font-weight: bold">Total Application:
                                    <label class="float-right" style="margin-right: 40px">
                                        <span style="font-weight: bold; font-size: 25px">
                                            <c:out value="${count.rowCountTotal}"/>
                                        </span>
                                    </label>
                                </p>
                                <%--<label class="float-left"><span style="font-weight: bold">Total Application:</span></label>
                                <div style="font-weight: bold"><span style="font-size: x-large;">${count.rowCountTotal}</span></div>--%>
                                <div class="progress progress-sm">
                                    <c:choose>
                                        <c:when test="${count.rowCountTotal > 100}">
                                            <div class="progress-bar bg-red" style="width:${count.rowCountTotal}"></div>
                                        </c:when>

                                        <c:when test="${count.rowCountTotal<=100 && count.rowCountTotal > 80}">
                                            <div class="progress-bar bg-orange" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=80 && count.rowCountTotal > 60}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=60 && count.rowCountTotal >40}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=40 && count.rowCountTotal>20}">
                                            <div class="progress-bar bg-azure-darker" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=20 && count.rowCountTotal>10}">
                                            <div class="progress-bar bg-cyan-dark" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=10 && count.rowCountTotal>=5}">
                                            <div class="progress-bar bg-gray" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="progress-bar bg-blue-lighter" style="width:${count.rowCountTotal}"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%} else {%>
    <div class="card">
        <div class="card-body">
            <h5 style="text-align:center;">Department of Engineering Service Dashboard</h5>
            <div class="row">
                <div class="col-md-3 col-sm-3 col-lg-3 col-xs-12">
                    <div class="card">
                        <div class="card-status bg-red-light"></div>
                        <div class="card-body text-center">
                            <a class="h5" href="#">
                                Operator Section
                                <br><br>
                            </a>
                            <c:forEach items="${totalApplication7}" var="count">
                                <p style="text-align:left;"><span style="font-weight: bold">Stages</span><label class="float-right"><span style="font-weight: bold">No. of Applications</span></label></p>
                                <p style="text-align:left;">Initiated:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountInitiated}"/></span></label></p>
                                <p style="text-align:left;">Claimed:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountClaimed}"/></span></label></p>
                                <p style="text-align:left;"> Approved:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountApproved}"/></span></label></p>
                                <p style="text-align:left;"> Hold:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountHold}"/></span></label></p>
                                <p style="text-align:left;"> Rejected:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountRejected}"/></span></label></p>
                                <p style="text-align:left;"> Submitted to DZO:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountForwarded}"/></span></label></p>

                                <p style="text-align:left;font-weight: bold">Total Application:
                                    <label class="float-right" style="margin-right: 40px">
                                        <span style="font-weight: bold; font-size: 25px">
                                            <c:out value="${count.rowCountTotal}"/>
                                        </span>
                                    </label>
                                </p>
                               <%-- <label class="float-left"><span style="font-weight: bold">Total Application:</span></label>
                                <div style="font-weight: bold"><span style="font-size: x-large">${count.rowCountTotal}</span></div>--%>
                                <div class="progress progress-sm">
                                    <c:choose>
                                        <c:when test="${count.rowCountTotal > 100}">
                                            <div class="progress-bar bg-red" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=100 && count.rowCountTotal > 80}">
                                            <div class="progress-bar bg-orange" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=80 && count.rowCountTotal > 60}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=60 && count.rowCountTotal >40}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=40 && count.rowCountTotal>20}">
                                            <div class="progress-bar bg-azure-darker" style="width:${count.rowCountTotal}"></div>

                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=20 && count.rowCountTotal>10}">
                                            <div class="progress-bar bg-cyan-dark" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=10 && count.rowCountTotal>=5}">
                                            <div class="progress-bar bg-gray" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="progress-bar bg-blue-lighter" style="width:${count.rowCountTotal}"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-3 col-lg-3 col-xs-12">
                    <div class="card">
                        <div class="card-status bg-cyan-dark"></div>
                        <div class="card-body text-center">
                            <a class="h5" href="#">
                                Architectural Section
                                <br><br>
                            </a>
                            <c:forEach items="${totalApplication3}" var="count">
                                <p style="text-align:left;"><span style="font-weight: bold">Stages</span><label class="float-right"><span style="font-weight: bold">No. of Applications</span></label></p>
                                <p style="text-align:left;">Initiated:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountInitiated}"/></span></label></p>
                                <p style="text-align:left;">Claimed:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountClaimed}"/></span></label></p>
                                <p style="text-align:left;"> Approved:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountApproved}"/></span></label></p>
                                <p style="text-align:left;"> Hold:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountHold}"/></span></label></p>
                                <p style="text-align:left;"> Rejected:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountRejected}"/></span></label></p>
                                <p style="text-align:left;"> Submitted to DZO:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountForwarded}"/></span></label></p>

                                <p style="text-align:left;font-weight: bold">Total Application:
                                    <label class="float-right" style="margin-right: 40px">
                                        <span style="font-weight: bold; font-size: 25px">
                                            <c:out value="${count.rowCountTotal}"/>
                                        </span>
                                    </label>
                                </p>
                                <%--<label class="float-left"><span style="font-weight: bold">Total Application:</span></label>
                                <div style="font-weight: bold"><span style="font-size: x-large">${count.rowCountTotal}</span></div>--%>
                                <div class="progress progress-sm">
                                    <c:choose>
                                        <c:when test="${count.rowCountTotal > 100}">
                                            <div class="progress-bar bg-red" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal <=100 && count.rowCountTotal > 80}">

                                            <div class="progress-bar bg-orange" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal <=80 && count.rowCountTotal > 60}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal <=60 && count.rowCountTotal >40}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=40 && count.rowCountTotal>20}">
                                            <div class="progress-bar bg-azure-darker" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=20 && count.rowCountTotal>10}">
                                            <div class="progress-bar bg-cyan-dark" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=10 && count.rowCountTotal>=5}">
                                            <div class="progress-bar bg-gray" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="progress-bar bg-blue-lighter" style="width:${count.rowCountTotal}"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-3 col-lg-3 col-xs-12">
                    <div class="card">
                        <div class="card-status bg-yellow-dark"></div>
                        <div class="card-body text-center">
                            <a class="h5" href="#">
                                Electrical Section
                                <br><br>
                            </a>
                            <c:forEach items="${totalApplication4}" var="count">
                                <p style="text-align:left;"><span style="font-weight: bold">Stages</span><label class="float-right"><span style="font-weight: bold">No. of Applications</span></label></p>
                                <p style="text-align:left;">Initiated:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountInitiated}"/></span></label></p>
                                <p style="text-align:left;">Claimed:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountClaimed}"/></span></label></p>
                                <p style="text-align:left;"> Approved:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountApproved}"/></span></label></p>
                                <p style="text-align:left;"> Hold:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountHold}"/></span></label></p>
                                <p style="text-align:left;"> Rejected:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountRejected}"/></span></label></p>

                                <p style="text-align:left;"> Submitted to DZO:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountForwarded}"/></span></label></p>

                                <p style="text-align:left;font-weight: bold">Total Application:
                                    <label class="float-right" style="margin-right: 40px">
                                        <span style="font-weight: bold; font-size: 25px">
                                            <c:out value="${count.rowCountTotal}"/>
                                        </span>
                                    </label>
                                </p>
                                <%--<label class="float-left"><span style="font-weight: bold">Total Application:</span></label>
                                <div style="font-weight: bold"><span style="font-size: x-large">${count.rowCountTotal}</span></div>--%>
                                <div class="progress progress-sm">
                                    <c:choose>
                                        <c:when test="${count.rowCountTotal > 100}">
                                            <div class="progress-bar bg-red" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=100 && count.rowCountTotal > 80}">
                                            <div class="progress-bar bg-orange" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=80 && count.rowCountTotal > 60}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=60 && count.rowCountTotal >40}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=40 && count.rowCountTotal>20}">
                                            <div class="progress-bar bg-azure-darker" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=20 && count.rowCountTotal>10}">
                                            <div class="progress-bar bg-cyan-dark" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=10 && count.rowCountTotal>=5}">
                                            <div class="progress-bar bg-gray" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="progress-bar bg-blue-lighter" style="width:${count.rowCountTotal}"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-3 col-lg-3 col-xs-12">
                    <div class="card">
                        <div class="card-status bg-orange-darkest"></div>
                        <div class="card-body text-center">
                            <a class="h5" href="#">
                                Structural Section
                                <br><br>
                            </a>
                            <c:forEach items="${totalApplication5}" var="count">
                                <p style="text-align:left;"><span style="font-weight: bold">Stages</span><label class="float-right"><span style="font-weight: bold">No. of Applications</span></label></p>
                                <p style="text-align:left;">Initiated:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountInitiated}"/></span></label></p>
                                <p style="text-align:left;">Claimed:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountClaimed}"/></span></label></p>
                                <p style="text-align:left;"> Approved:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountApproved}"/></span></label></p>
                                <p style="text-align:left;"> Hold:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountHold}"/></span></label></p>
                                <p style="text-align:left;"> Rejected:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountRejected}"/></span></label></p>
                                <p style="text-align:left;"> Submitted to DZO:<label class="float-right" style="margin-right: 40px"><span style="font-weight: bold"><c:out value="${count.rowCountForwarded}"/></span></label></p>

                                <p style="text-align:left;font-weight: bold">Total Application:
                                    <label class="float-right" style="margin-right: 40px">
                                        <span style="font-weight: bold; font-size: 25px">
                                            <c:out value="${count.rowCountTotal}"/>
                                        </span>
                                    </label>
                                </p>
                                <%--<label class="float-left"><span style="font-weight: bold">Total Application:</span></label>
                                <div style="font-weight: bold"><span style="font-size: x-large;">${count.rowCountTotal}</span></div>--%>
                                <div class="progress progress-sm">
                                    <c:choose>
                                        <c:when test="${count.rowCountTotal > 100}">
                                            <div class="progress-bar bg-red" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=100 && count.rowCountTotal > 80}">
                                            <div class="progress-bar bg-orange" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=80 && count.rowCountTotal > 60}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal <=60 && count.rowCountTotal >40}">
                                            <div class="progress-bar bg-green" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=40 && count.rowCountTotal>20}">
                                            <div class="progress-bar bg-azure-darker" style="width:${count.rowCountTotal}"></div>
                                        </c:when>

                                        <c:when test="${count.rowCountTotal<=20 && count.rowCountTotal>10}">
                                            <div class="progress-bar bg-cyan-dark" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:when test="${count.rowCountTotal<=10 && count.rowCountTotal>=5}">
                                            <div class="progress-bar bg-gray" style="width:${count.rowCountTotal}"></div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="progress-bar bg-blue-lighter" style="width:${count.rowCountTotal}"></div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%}%>
</body>
</html>