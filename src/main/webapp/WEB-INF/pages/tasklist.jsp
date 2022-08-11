<%@ page import="bt.gov.ditt.does.Dto.UserRolePrivilegeDto" %>
<%@ page import="bt.gov.ditt.does.Dto.TasklistDto" %>
<%@ page import="java.util.List" %>
<%@ page import="bt.gov.ditt.does.Dto.UserRolePrivilege" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sess = request.getSession();
    UserRolePrivilege userdet = (UserRolePrivilege) sess.getAttribute("UserRolePrivilege");
%>
<body>

<div id="mainContent" class="container">
    <div class="my-3 my-md-5">
        <form name="taskListForm" id="taskListForm" enctype="multipart/form-data">
            <div class="container mdi-format-horizontal-align-center">
                <%if (userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("1")){%>
                <div class="row">
                    <span style="font-size: 20px;color:#064CA7">&nbsp;&nbsp;&nbsp;<b>Dzongkhag</b>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        Tasklist
                    </small>
                </span>


                </div>
                <%} else {%>
                <div class="row">
                 <span style="font-size: 20px;color:#064CA7">&nbsp;&nbsp;&nbsp;<b>DES</b>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        Tasklist
                    </small>
                </span>

                </div>
                    <%}%>
                <div class="card">
                    <div class="card-header bg-transparent border-success">
                        <h4 class="card-title">Group Task</h4>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table card-table table-vcenter text-nowrap" id="group_task">
                                <thead>
                                <tr>
                                    <th>Action</th>
                                    <th>Application Number</th>
                                    <th>Task</th>
                                    <th>Action Taken By</th>
                                    <th>Action Date</th>
                                </tr>
                                </thead>
                                <tbody id="groupTaskBody">
                                <% int j = 1;%>
                                <c:forEach var="list" items="${groupTaskList}">

                                    <tr id="groupRow_<%=j%>">
                                        <td id="actionRow_<%=j%>">
                                            <a href="#" id="claim_button_<%=j%>" onclick="claimTasklist(<%=j%>,'${list.applicationNo}','${list.task_Id}')" class="btn btn-primary">
                                                <i class="fa fa-chevron-down"></i> Claim
                                            </a>
                                        </td>
                                        <td>${list.applicationNo}</td>
                                        <td>${list.applicationStatus}</td>
                                        <td>${list.createdBy}</td>
                                        <td>${list.stringCreatedOn}</td>
                                    </tr>
                                <% j++;%>
                                </c:forEach>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
                </br>
                <div class="card">
                    <div class="card-header bg-transparent border-success mb-3">
                        <h4 class="card-title">My Task</h4>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table card-table table-center text-nowrap table-hover table-striped table-bordered" id="my_task">
                                <thead>
                                <tr>
                                    <th>Action</th>
                                    <th>Application Number</th>
                                    <th>Task</th>
                                    <th>Status</th>
                                    <th>Action Taken By</th>
                                    <th>Action Date</th>
                                </tr>
                                </thead>
                                <tbody id="mytaskBody">
                                <c:forEach var="list" items="${myTasklist}">
                                    <tr id="my_<%=j%>">
                                        <td id="actionCol_<%=j%>">
                                            <a href="#" id="unclaim_button_<%=j%>" onclick="unClaimTasklist(<%=j%>,'${list.applicationNo}','${list.task_Id}')" class="btn btn-primary"> <i class="fa fa-chevron-up"></i>  Unclaim
                                            </a>
                                            <a href="#" id="open_button_<%=j%>" onclick="openTasklistApplication('${list.applicationNo}','${list.task_Id}')"  class="btn btn-success">
                                                <i class="fa fa-chevron-right mr-1"></i> Open
                                            </a>
                                        </td>
                                        <td>${list.applicationNo}</td>
                                        <td>${list.applicationStatus}</td>
                                        <td>${list.task_Stage_Name}</td>
                                        <td>${list.createdBy}</td>
                                        <td>${list.stringModifiedOn}</td>
                                    </tr>
                                    <% j++;%>
                                </c:forEach>
                                </tbody>
                            </table>
                            <script>
                                function claimTasklist(rowId, appNo, taskId) {
                                    var link = "openTasklistApplication('" + appNo + "','" + taskId + "')";
                                    var row = $('#groupRow_' + rowId).html();
                                    $("#groupRow_" + rowId).remove();
                                    //var res = row.replace("claimTasklist", "openTasklistApplication");
                                    $('#mytaskBody').append('<tr id="my_' + rowId + '"> ' + row + '</tr>');
                                    $("#my_" + rowId + " a").attr("onclick", link);
                                    $('#claim_button_'+rowId).remove();
                                    $('#actionRow_'+rowId).append('<a class="btn btn-primary" style="color:white" style="font-size:15px"><i class="fa fa-chevron-up"></i>Unclaim</a>');
                                    $('#actionRow_'+rowId).append('<a class="btn btn-success" style="color:white" style="font-size:15px"><i class="fa fa-chevron-right mr-1"></i>Open</a>');
                                    $.ajax({
                                        async: true,
                                        type: 'GET',
                                        url: '${pageContext.request.contextPath}/common/claimTasklistApplication?applicationNo=' + appNo + '&taskId=' + taskId,
                                        success: function (res) {
                                            $("#mainContent").html(res);
                                        },
                                        error: function (data, textStatus, errorThrown) {
                                        }
                                    });
                                    window.location.reload();
                                }
                                function unClaimTasklist(rowId, appNo, taskId) {
                                    var link = "openTasklistApplication('" + appNo + "','" + taskId + "')";
                                    var row = $('#my_' + rowId).html();
                                    $("#my_" + rowId).remove();
                                    $('#groupTaskBody').append('<tr id="groupRow_' + rowId + '"> ' + row + '</tr>');
                                    $("#groupRow_" + rowId + " a").attr("onclick", link);
                                    $("#unclaim_button_"+rowId).remove();
                                    $("#open_button_"+rowId).remove();
                                    $('#actionCol_'+rowId).append('<a class="btn btn-primary"style="color:white"style="font-size:15px")"><i class="fa fa-chevron-Down"></i>Claim</a>');
                                    $.ajax({
                                        async: true,
                                        type: 'GET',
                                        url: '${pageContext.request.contextPath}/common/unClaimTasklistApplication?applicationNo=' + appNo + '&taskId=' + taskId,
                                        success: function (res) {
                                        }
                                    });
                                    window.location.reload();
                                }

                            </script>

                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function()
    {
        $('#group_task').DataTable(
                {
            responsive: true
        });
        $('#my_task').DataTable(
                {
            responsive: true
        });
    });
    function openTasklistApplication(applicationNo, taskId)
    {
        $.ajax({
            async: true,
            type: 'GET',
            url: '${pageContext.request.contextPath}/building/openTasklistApplication?applicationNo=' + applicationNo + '&taskId=' + taskId,
            success: function (res)
            {
                $("#mainContent").html(res);
            },
            error: function (data, textStatus, errorThrown)
            {
            }
        });
    }

</script>
</body>
</html>
