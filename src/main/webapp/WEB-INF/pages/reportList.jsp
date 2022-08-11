<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bt.gov.ditt.does.Dto.BuildingDto" %>
<%@ page import="java.util.List" %>
<%@ page import="bt.gov.ditt.does.Dto.UserRolePrivilegeDES" %>
<meta name="decorator" content="/layout/emptyLayout.jsp"/>
<%
    HttpSession sess = request.getSession();
    UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
    String user_Role = userdet.getCurrentRole().getRoleName();
%>

<div class="panel-heading">
    <br/>
    <b>List of Building Applications</b>

    <div class="pull-right">
            <a href="#" onClick="$('#reportTableId').tableExport(
               {
                    type:'excel',
                    escape:'false',
                    fileName:'report'
               });
               ">
                <button style="margin-top:10px;" class="btn btn-outline-success" type="button">
                    <i class="fa fa-file-excel-o"></i>&nbsp;<b>Get Excel</b></button>
            </a>
        <br>
    </div>
</div>

<div class="panel-body">
    <br>

    <div class="">
        <div class="col-lg-12">
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover" id="reportTableId">
                    <thead>
                    <tr>
                        <th style="background-color: lightsalmon; color: #000000">Sl#</th>
                        <th style="background-color: lightsalmon; color: #000000">Application Number</th>
                        <th style="background-color: lightsalmon; color: #000000">Received Date</th>
                        <th style="background-color: lightsalmon; color: #000000">Claimed Date</th>
                        <th style="background-color: lightsalmon; color: #000000">Owner's Name</th>
                        <th style="background-color: lightsalmon; color: #000000">Owner's Location</th>
                        <th style="background-color: lightsalmon; color: #000000">Tharm Number</th>
                        <th style="background-color: lightsalmon; color: #000000">Plot Number</th>
                        <th style="background-color: lightsalmon; color: #000000">Building Storied</th>
                        <th style="background-color: lightsalmon; color: #000000">Building Use</th>
                        <th style="background-color: lightsalmon; color: #000000">Number of Floors</th>
                        <th style="background-color: lightsalmon; color: #000000">Designed By</th>
                        <th style="background-color: lightsalmon; color: #000000">Scrutinized By</th>
                        <th style="background-color: lightsalmon; color: #000000">Application Status</th>
                        <th style="background-color: lightsalmon; color: #000000">Approved Date</th>
                        <%if (userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("1")){%>
                        <th style="background-color: lightsalmon; color: #000000">Forwarded to DES Date</th>
                        <%}%>
                        <th style="background-color: lightsalmon; color: #000000">Hold Date/Reason</th>
                        <th style="background-color: lightsalmon; color: #000000">UnHold Date/Reason</th>
                        <th style="background-color: lightsalmon; color: #000000">Rejected Date/Reason</th>
                        <th style="background-color: lightsalmon; color: #000000">Remarks</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% List<BuildingDto> reportList = (List<BuildingDto>) request.getAttribute("reportList"); %>
                    <% for (int i = 0; i < reportList.size(); i++) { %>
                    <tr>
                        <td><%=i + 1%>
                        </td>
                        <td><%=reportList.get(i).getApplication_Id()%>
                        </td>
                        <td> <%if(reportList.get(i).getStringCreated_on()!=null){%>
                            <%=reportList.get(i).getStringCreated_on()%>
                            <%}%>
                        </td>
                        <td>
                            <%if(reportList.get(i).getStringClaimed_On()!=null){%>
                            <%=reportList.get(i).getStringClaimed_On()%>
                            <%}%>
                        </td>
                        <td><%=reportList.get(i).getOwner_Name()%>
                        </td>
                        <td><%=reportList.get(i).getLocation_Id()%>
                        </td>
                        <td><%=reportList.get(i).getThram_Nos()%>
                        </td>
                        <td><%=reportList.get(i).getPlot_Nos()%>
                        </td>
                        <td><%=reportList.get(i).getProjectTitle()%>
                        </td>
                        <td><%=reportList.get(i).getBuilding_Use_Name()%>
                        </td>
                        <td><%=reportList.get(i).getNumber_of_Floor()%>
                        </td>
                        <% if (request.getAttribute("usr_Role").equals("DOES Architect")) {%>
                        <td><%=reportList.get(i).getArchitect_Name()%>
                        </td>
                        <% } else if (request.getAttribute("usr_Role").equals("DOES Structural Engineer")) {%>
                        <td><%=reportList.get(i).getElectrical_Name()%>
                        </td>
                        <%} else if (request.getAttribute("usr_Role").equals("DOES Electrical Engineer")) {%>
                        <td><%=reportList.get(i).getStructural_Name()%>
                        </td>
                        <%} else if (request.getAttribute("usr_Role").equals("DZO_EE")) {%>
                        <td><%=reportList.get(i).getElectrical_Name()%>
                        </td>
                        <%} else if (request.getAttribute("usr_Role").equals("DZO_SE")) {%>
                        <td><%=reportList.get(i).getStructural_Name()%>
                        </td>
                        <%} else if (request.getAttribute("usr_Role").equals("DZO_ARCHITECT")) {%>
                        <td><%=reportList.get(i).getArchitect_Name()%>
                        </td>
                        <% } %>
                        <td><%=reportList.get(i).getModified_By()%>
                        </td>
                        <td><%=reportList.get(i).getTask_Stage_Name()%>
                        </td>
                        <%if (reportList.get(i).getStringApproved_On()!=null){%>
                        <td><%=reportList.get(i).getStringApproved_On()%>
                            <%}%>
                        </td>
                        <%if (userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("1")){%>
                        <td>

                            <% if(reportList.get(i).getStringForwarded_On()!=null){%>
                            <%=reportList.get(i).getStringForwarded_On()%>
                            <%}%>
                            <br><br>
                            <%if (reportList.get(i).getForwardReason()!=null){%>
                            <%=reportList.get(i).getForwardReason()%>
                            <%}%>
                        </td>
                        <%}%>
                        <td>
                            <%if (reportList.get(i).getStringHold_On()!=null){%>
                            <%=reportList.get(i).getStringHold_On()%>
                            <%}%>
                            <br><br>
                            <%if (reportList.get(i).getHoldReason()!=null){%>
                            <%=reportList.get(i).getHoldReason()%>
                            <%}%>
                        </td>
                        <td>
                            <%if (reportList.get(i).getStringUnHold_On()!=null){%>
                            <%=reportList.get(i).getStringUnHold_On()%>
                            <%}%>
                            <br><br>
                            <% if(reportList.get(i).getUnHoldReason()!=null){%>
                            <%=reportList.get(i).getUnHoldReason()%><br>
                            <%}%>
                        </td>
                        <td>
                            <%if (reportList.get(i).getRejcted_On()!=null){%>
                            <%=reportList.get(i).getStringRejcted_On()%>
                            <%}%>
                            <br><br>
                            <%if (reportList.get(i).getRejectReason()!=null){%>
                            <%=reportList.get(i).getRejectReason()%>
                            <%}%>
                        </td>
                        <td>
                            <%if (reportList.get(i).getRemarks()!=null){%>
                            <%=reportList.get(i).getRemarks()%>
                            <%}%>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#reportTableId').DataTable({
            responsive: true
        });
    });
    function doExportItem(selector, params) {
        var options = {
            tableName: 'reportTableId',
            worksheetName: 'report',
            fileName: 'report'
        };
        $.extend(true, options, params);
        $(selector).tableExport(options);
    }


</script>
