<%@ page import="bt.gov.ditt.does.Dto.UserRolePrivilegeDES " %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%HttpSession sess = request.getSession();
    UserRolePrivilegeDES  userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
    String user_Role = userdet.getCurrentRole().getRoleName();
    %>

<% if (userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("1")){%>
<div class="row">
    <span style="font-size: 20px;color:#064CA7">&nbsp;&nbsp;&nbsp;<b>Dzongkhag</b>
                    <%if(user_Role.equalsIgnoreCase("DZO_ARCHITECT")){%>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        <label style="color: #003300">Architectural Section</label>
                    </small>
                    <%}else if(user_Role.equalsIgnoreCase("DZO_EE")){%>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        <label style="color: #003300">Electrical Section</label>
                    </small>
                    <%}else if(user_Role.equalsIgnoreCase("DZO_SE")){%>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        <label style="color: #003300">Structural Section</label>
                    </small>
                    <%}%>
                </span>


</div>
<%} else {%>
<div class="row">
                <span style="font-size: 20px;color:#064CA7">&nbsp;&nbsp;&nbsp;<b>DES</b>
                    <%if(user_Role.equalsIgnoreCase("DOES Architect")){%>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        <label style="color: #003300">Architectural Section</label>
                    </small>
                    <%}else if(user_Role.equalsIgnoreCase("DOES Electrical Engineer")){%>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        <label style="color: #003300">Electrical Section</label>
                    </small>
                    <%}else if(user_Role.equalsIgnoreCase("DOES Structural Engineer")){%>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        <label style="color: #003300">Structural Section</label>
                    </small>
                    <%}%>
                </span>

</div>
<%}%>

<div class="card card-body">



    <form name="ConstructionFormAudit" id="buildingConsAuditFormId" method="post"
          action="/DOES/buildingConsApplAudit.do" class="formAlignment" enctype="multipart/form-data">
        <div>
            <input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="9419af6723155078a6c701a98855867a">
        </div>

        <% if (userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("1")){%>
        <div class="row">

            <h4 class="page-head-line" style="margin-bottom: 21px; margin-left: 500px; color: #003300">Generate Report
                <%if(user_Role.equalsIgnoreCase("DZO_ARCHITECT")){%>
                <small class="text-secondary">
                    <i class="ace-icon fa fa-angle-double-right"></i>
                    <label style="color: #003300">Architectural Report</label>
                </small>
                <%}else if(user_Role.equalsIgnoreCase("DZO_EE")){%>
                <small class="text-secondary">
                    <i class="ace-icon fa fa-angle-double-right"></i>
                    <label style="color: #003300">Electrical Report</label>
                </small>
                <%}else if(user_Role.equalsIgnoreCase("DZO_SE")){%>
                <small class="text-secondary">
                    <i class="ace-icon fa fa-angle-double-right"></i>
                    <label style="color: #003300">Structural Report</label>
                </small>
                <%}%>
            </h4>


        </div>
        <%} else {%>
        <div class="row">
            <h4 class="page-head-line" style="margin-bottom: 21px; margin-left: 500px; color: #003300">Generate Report

                <%if(user_Role.equalsIgnoreCase("DOES Architect")){%>
                <small class="text-secondary">
                    <i class="ace-icon fa fa-angle-double-right"></i>
                    <label style="color: #003300">Architectural Report</label>
                </small>
                <%}else if(user_Role.equalsIgnoreCase("DOES Electrical Engineer")){%>
                <small class="text-secondary">
                    <i class="ace-icon fa fa-angle-double-right"></i>
                    <label style="color: #003300">Electrical Report</label>
                </small>
                <%}else if(user_Role.equalsIgnoreCase("DOES Structural Engineer")){%>
                <small class="text-secondary">
                    <i class="ace-icon fa fa-angle-double-right"></i>
                    <label style="color: #003300">Structural Report</label>
                </small>
                <%}%>
            </h4>

        </div>
        <%}%>

        <div class="panel-body form-horizontal">
            <div class="form-group col-lg-12 float-left">
                <br/>
                <b><label class="control-label col-sm-2 float-left">From Date :</label></b>

                <label class="control-label col-sm-3 float-left">
                    <input type="date" name="fromDate" onclick="" id="fromDate" class="form-control datepicker">
                </label>
                <b><label class="control-label col-sm-2 float-left" style="margin-left: 180px">To Date :</label></b>

                <label class=" control-label col-sm-3 float-left">
                    <input type="date" name="toDate" onclick="" id="toDate" class="form-control datepicker">
                </label>
            </div>

            <div class="form-group" id="noSelectionErrorDiv" style="display: none;">
                <div class="text-center">
                    <span id="noSelectionErrorMsg" class="text-danger">Please enter at least one search criteria.</span>
                </div>
            </div>
            <br/><br/>
            <div class="form-group col-lg-12  float-left">
                <div class="col-md-12 text-center">
                    <input type="button" onclick="getReport()" class="btn btn-primary" value="Generate Report" id="applicationAuditButtonId">
                </div>
            </div>
            <div class="panel panel-default" id="fetchReportListDiv" style="display: none;"></div>
        </div>
    </form>
</div>
<script>

    function getReport() {
        var fromDate = $('#fromDate').val();
        var toDate = $('#toDate').val();
        if (fromDate != "" && toDate != "") {
            var url = '${pageContext.request.contextPath}/common/generateReport?fromDate=' + fromDate + '&toDate=' + toDate;
            $.ajax({
                async: true,
                type: 'GET',
                url: url,
                success: function (res) {
                    $('#noSelectionErrorDiv').hide();
                    $('#fetchReportListDiv').html(res).show();
                }
            });
        } else {
            $('#noSelectionErrorDiv').show();
            $('#fetchReportListDiv').hide();
        }
    }
</script>

