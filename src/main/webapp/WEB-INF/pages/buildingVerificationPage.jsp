<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="bt.gov.ditt.does.Dto.BuildingDto" %>
<%@ page import="bt.gov.ditt.does.Entity.OwnerEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="bt.gov.ditt.does.Dto.TasklistDto" %>
<%@ page import="bt.gov.ditt.does.Dto.UserRolePrivilegeDES" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession sess = request.getSession();
    UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
    String user_Role = userdet.getCurrentRole().getRoleName();
%>
<style>

    a {
        color: #fff !important;
        text-decoration: none;
        background-color: transparent;
        -webkit-text-decoration-skip: objects;
    }

</style>
<% if (userdet.getJurisdictions()[0].getJurisdictionType().equalsIgnoreCase("20")){%>
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
                    <%} else if(user_Role.equalsIgnoreCase("DOES Operater")){%>
                     <small class="text-secondary">
                         <i class="ace-icon fa fa-angle-double-right"></i>
                         <label style="color: #003300">Operator Section</label>
                     </small>
                    <%}%>
                </span>

</div>
<%}%>
<%if (userdet.getJurisdictions()[0].getJurisdictionType().equalsIgnoreCase("1")){%>
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
                    <%}else if(user_Role.equalsIgnoreCase("DZO_SE") || request.getAttribute("taskId").equals("4") ){%>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        <label style="color: #003300">Structural Section</label>
                    </small>
                    <%} else if(user_Role.equalsIgnoreCase("DZO_OPERATOR")){%>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        <label style="color: #003300">Customer Service Operator</label>
                    </small>
                    <%}%>
                </span>

</div>
<%}%>
<div class="my-3 my-md-5">

    <div class="row">

        <div class="col-sm-12" id="loadContainer">
            <div class="card">

                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs m-0 bg-white">
                        <li id="personalDetials">
                            <a href="#" onclick="viewDetails('p')" style="padding: 9px;" class=" bg-blue-darker show text-white"
                               id="personalh" data-toggle="tab" data-placement="top"><i class="fa fa-user"></i>
                                Application Details<span class="text-info" style="font-size: 20px"
                                                         id="personalcheck"></span></a>
                        </li>
                        &nbsp;
                        <% if (request.getAttribute("taskId").equals("15")) {%>
                        <li id="locationDetails">
                            <a href="#locationcontent" style="padding: 9px;" id="locationh" data-toggle="tab"
                               data-placement="top" class="bg-azure-dark"><i class="fa fa-globe"></i>&nbsp; Architectural
                                Section<span class="text-info" style="font-size: 20px" id="locationcheck"></span></a>
                        </li>
                        &nbsp;

                        <%} else if (request.getAttribute("taskId").equals("17") && request.getAttribute("userRole").equals("DOES Architect")){%>
                        <li id="locationDetails">
                            <a href="#locationcontent" style="padding: 9px;" id="locationh" data-toggle="tab"
                               data-placement="top" class="bg-azure-dark"><i class="fa fa-globe"></i>&nbsp; Architectural
                                Section<span class="text-info" style="font-size: 20px" id="locationcheck"></span></a>
                        </li>
                        &nbsp;
                        <%} else if (request.getAttribute("taskId").equals("20") && request.getAttribute("userRole").equals("DOES Architect")){%>
                        <li id="locationDetails">
                            <a href="#locationcontent" style="padding: 9px;" id="locationh" data-toggle="tab"
                               data-placement="top" class="bg-azure-dark"><i class="fa fa-globe"></i>&nbsp; Notesheet Preparation
                                Section<span class="text-info" style="font-size: 20px" id="locationcheck"></span></a>
                        </li>
                        &nbsp;
                        <%} else if (request.getAttribute("taskId").equals("21") || request.getAttribute("taskId").equals("5")){%>
                        <li id="locationDetails">
                            <a href="#locationcontent" style="padding: 9px;" id="locationh" data-toggle="tab"
                               data-placement="top" class="bg-azure-dark"><i class="fa fa-globe"></i>&nbsp; Dzongkhag Operator
                                Section for NoteSheet Closure<span class="text-info" style="font-size: 20px" id="locationcheck"></span></a>
                        </li>
                        &nbsp;
                        <%} else if (request.getAttribute("taskId").equals("18") && request.getAttribute("userRole").equals("DOES Structural Engineer")){%>
                        <li id="locationDetails">
                            <a href="#locationcontent" style="padding: 9px;" id="locationh" data-toggle="tab"
                               data-placement="top" class="bg-azure-dark"><i class="fa fa-globe"></i>&nbsp; Structural
                                Section<span class="text-info" style="font-size: 20px" id="locationcheck"></span></a>
                        </li>
                        &nbsp;
                        <%} else if (request.getAttribute("taskId").equals("19") && request.getAttribute("userRole").equals("DOES Electrical Engineer")){%>
                        <li id="locationDetails">
                            <a href="#locationcontent" style="padding: 9px;" id="locationh" data-toggle="tab"
                               data-placement="top" class="bg-azure-dark"><i class="fa fa-globe"></i>&nbsp; Electrical
                                Section<span class="text-info" style="font-size: 20px" id="locationcheck"></span></a>
                        </li>

                        &nbsp;
                        <% }else if(request.getAttribute("taskId").equals("16") || request.getAttribute("taskId").equals("28") || request.getAttribute("taskId").equals("26") || request.getAttribute("taskId").equals("27")){ %>
                        <li id="locationDetails">
                            <a href="#locationcontent" style="padding: 9px;" id="locationh" data-toggle="tab"
                               data-placement="top" class="bg-azure-dark"><i class="fa fa-globe"></i>&nbsp; DES Operator
                                Section<span class="text-info" style="font-size: 20px" id="locationcheck"></span></a>
                        </li>
                        &nbsp;
                        <%} else if (request.getAttribute("taskId").equals("4")) { %>
                        <li id="structuralDetails">
                            <a href="#structural_tab" style="padding: 9px;" id="structuralh" data-toggle="tab"
                               data-placement="top" class="bg-azure-dark"><i class="fa fa-globe"></i>&nbsp; Structural Section<span class="text-info" style="font-size: 20px"
                                                                                                                                    id="structuralcheck">
                            </span></a>
                        </li>
                        &nbsp;
                        <%} else if (request.getAttribute("taskId").equals("3")) {%>
                        <li id="electricalDetails">
                            <a href="#electrical_tab" style="padding: 9px;" id="electricalh" data-toggle="tab" data-placement="top"
                               class="bg-azure-dark">
                                <i class="fa fa-globe"></i>&nbsp; Electrical Section<span class="text-info" style="font-size: 20px" id="electricalcheck"></span>
                            </a>
                        </li>
                        &nbsp;
                        <%} else if (request.getAttribute("taskId").equals("22") ||request.getAttribute("taskId").equals("23") ) {%>
                        <li id="notesheetDetails">
                            <a href="#sewerage_tab" style="padding: 9px;" id="sewerageh" class="bg-azure-dark"
                               data-toggle="tab" data-placement="top">
                                <i class="fa fa-globe"></i>&nbsp; Notesheet Preparation
                                <span class="text-info" style="font-size: 20px" id="seweragecheck"></span></a>
                        </li>

                        &nbsp;
                        <%} else if (request.getAttribute("taskId").equals("25") && request.getAttribute("userRole").equals("DOES Structural Engineer")){%>
                        <li id="locationDetails">
                            <a href="#locationcontent" style="padding: 9px;" id="locationh" data-toggle="tab"
                               data-placement="top" class="bg-azure-dark"><i class="fa fa-globe"></i>&nbsp; Notesheet Preparation
                                Section<span class="text-info" style="font-size: 20px" id="locationcheck"></span></a>
                        </li>

                        &nbsp;
                        <%} else if (request.getAttribute("taskId").equals("24") && request.getAttribute("userRole").equals("DOES Electrical Engineer")){%>
                        <li id="locationDetails">
                            <a href="#locationcontent" style="padding: 9px;" id="locationh" data-toggle="tab"
                               data-placement="top" class="bg-azure-dark"><i class="fa fa-globe"></i>&nbsp; Notesheet Preparation
                                Section<span class="text-info" style="font-size: 20px" id="locationcheck"></span></a>
                        </li>

                        <%}%>

                    </ul>
                </div>
                <div class="card-body tab-content">
                    <div class="tab-pane active" id="personalcontent">
                        <div class="row">
                            <div class="">
                                <div class="">
                                    <div class="">
                                        <br/>

                                        <div class="col-md-12">
                                            <div class="panel panel-info">
                                                <div class="panel-body">
                                                    <% if (request.getAttribute("taskId").equals("17") || request.getAttribute("taskId").equals("16") ||
                                                            request.getAttribute("taskId").equals("18") ||  request.getAttribute("taskId").equals("19") ||
                                                            request.getAttribute("taskId").equals("20") || request.getAttribute("taskId").equals("21") ||
                                                            request.getAttribute("taskId").equals("26") || request.getAttribute("taskId").equals("27") ||
                                                            request.getAttribute("taskId").equals("28")) {%>
                                                    <div class="form-group col-lg-12 float-left alert alert-danger  ">
                                                        <label class="control-label col-lg-2 float-left">Reason for
                                                            Forwarding:</label>

                                                        <div class="col-lg-4 float-left">
                                                            ${FORWARD_REASON.appl_comments}
                                                        </div>
                                                    </div>
                                                    <% }%>

                                                    <div class="form-group col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Building
                                                            Storied:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.projectTitle}
                                                        </div>

                                                        <label class="control-label col-lg-2 float-left">Building
                                                            Use:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.buildingUseString}
                                                        </div>

                                                    </div>
                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Number of
                                                            Floors:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.number_of_Floor}
                                                        </div>
                                                    </div>
                                                    <% BuildingDto buildingCategory = (BuildingDto) request.getAttribute("applicationDetails"); %>
                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Building
                                                            Category:</label>
                                                        <div class="col-lg-4 float-left bg-light text-secondary ">
                                                            <%if (buildingCategory.getIs_Private_Building().equalsIgnoreCase("P")) {%>
                                                            <label>Private</label>
                                                            <%} else if (buildingCategory.getIs_Private_Building().equalsIgnoreCase("I")){%>
                                                            <label>Government</label>
                                                            <%} else {%>
                                                            <label>Others</label>
                                                            <%}%>
                                                        </div>
                                                    </div>
                                                    <div class="form-group  col-lg-12 float-left categoryTypeClass"
                                                         id="otherIdDiv" style="display: none;">
                                                        <label class="control-label col-lg-2 float-left">Details:</label>

                                                        <div class="col-lg-4 float-left">


                                                        </div>
                                                    </div>
                                                    <div class="form-group  col-lg-12 float-left categoryTypeClass"
                                                         id="privateDiv">
                                                        <label class="control-label col-lg-2 float-left">Ownership
                                                            Details:</label>

                                                        <div class="col-lg-10 float-left">
                                                            <div class="table-responsive">
                                                                <table id="ownerTable"
                                                                       class="table table-striped table-bordered table-hover text-secondary">
                                                                    <tr>
                                                                        <th style="text-align: center;">CID</th>
                                                                        <th style="text-align: center;">Full Name</th>
                                                                        <th style="text-align: center;">Contact No</th>
                                                                    </tr>
                                                                    <%
                                                                        List<OwnerEntity> ownerList = (List<OwnerEntity>) request.getAttribute("ownerList");

                                                                        for (int i = 0; i < ownerList.size(); i++) {
                                                                    %>
                                                                    <tr>
                                                                        <td><%=ownerList.get(i).getOwner_CID_No()%>
                                                                        </td>
                                                                        <td><%=ownerList.get(i).getOwner_Name()%>
                                                                        </td>
                                                                        <td><%=ownerList.get(i).getOwner_Mobile_No()%>
                                                                            <%--<input type="text" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">--%>
                                                                        </td>
                                                                    </tr>
                                                                    <%}%>
                                                                </table>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Thram
                                                            No.(s):</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.thram_Nos}
                                                        </div>
                                                        <label class="control-label col-lg-2 float-left">Plot
                                                            No.(s):</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.plot_Nos}
                                                        </div>
                                                    </div>
                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Dzongkhag:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.dzongkhag_Name}
                                                        </div>
                                                        <label class="control-label col-lg-2 float-left">Gewog:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.gewog_Name}
                                                        </div>
                                                    </div>

                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Village:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.village_Name}
                                                        </div>
                                                        <label class="control-label col-lg-2 float-left">Contact
                                                            Address:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.contact_Address}
                                                        </div>
                                                    </div>
                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Contact Email
                                                            ID:</label>
                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.contact_Email_Id}
                                                        </div>
                                                        <label class="control-label col-lg-2 float-left">Contact
                                                            Telephone No:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">

                                                            ${applicationDetails.contact_Mobile_No}
                                                        </div>
                                                    </div>

                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Architect
                                                            Registration No:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.architect_Registration_No}
                                                        </div>

                                                        <label class="control-label col-lg-2 float-left">Architect
                                                            Contact No:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.architect_Contact_No}
                                                        </div>

                                                    </div>

                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Architect
                                                            Name:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.architect_Name}
                                                        </div>
                                                    </div>

                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left tooltipCSSSelector"
                                                               title="" data-original-title="Minimum of 2 Years">Structural
                                                            Cid No:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.structural_ID}
                                                        </div>
                                                        <label class="control-label col-lg-2 float-left tooltipCSSSelector"
                                                               title="" data-original-title="Minimum of 2 Years">Electrical
                                                            Cid No:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.electrical_ID}
                                                        </div>

                                                    </div>
                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Structural
                                                            Engineer Name:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.structural_Name}
                                                        </div>

                                                        <label class="control-label col-lg-2 float-left">Electrical
                                                            Engineer Name:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.electrical_Name}
                                                        </div>
                                                    </div>

                                                    <div class="form-group  col-lg-12 float-left">
                                                        <label class="control-label col-lg-2 float-left">Structural
                                                            Engineer's Year of Graduation:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.structural_Graduation_Year}
                                                        </div>
                                                        <label class="control-label col-lg-2 float-left">Electrical
                                                            Engineer's Year of Graduation:</label>

                                                        <div class="col-lg-4 float-left bg-light text-secondary">
                                                            ${applicationDetails.electrical_Graduation_Year}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-12">
                                            <div class="pull-right">
                                                <button type="button" id="btnId" onclick="next('personal','location')"
                                                        class="btn btn-primary">
                                                    <i class="fa fa-arrow-right"></i>
                                                    Next
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane" id="locationcontent">
                        <% if (request.getAttribute("taskId").equals("15")) {%>
                        <button type="button" style="padding: 9px; background-color: #00aa88; color: #ffffff" id="Approvalid"
                                onclick="CheckApproval('Approval')">Architectural Verification
                        </button>
                        <button type="button" style="padding: 9px; background-color: #00aa88; color: #ffffff"  id="forwarId"
                                onclick="CheckApproval('Forward')">Forward to DES(if Applicable)
                        </button>
                        <div class="form-group col-lg-12 verifier border" id="ApprovalDiv">
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                <br/>

                                <div class="verifier-tab">
                                    <label class="col-lg-12">Select the types of verification required to process the
                                        application <span class="text-danger">*</span>:</label><br/>
                                    </br>
                                    <% BuildingDto forwardDetails = (BuildingDto) request.getAttribute("applicationDetails"); %>
                                    <% BuildingDto isElectricalForward = (BuildingDto) request.getAttribute("is_electrical_forwarded"); %>
                                    <% TasklistDto statusCount = (TasklistDto) request.getAttribute("statusCount"); %>
                                    <% TasklistDto checkStatus = (TasklistDto) request.getAttribute("checkHold"); %>
                                    <div class="col-sm-4">

                                        <label name="is_Electrical_Forwarded" id="isElecVerificationReq"/> Electrical
                                        Drawing</label>
                                        <%if(forwardDetails.getIs_Electrical_Forwarded().equalsIgnoreCase("N")){%>
                                        <% if (isElectricalForward.getRowCount().intValue() == 0){ %>
                                        <button type="button"
                                                onclick="verfiyApplcication('ELECTRICAL_FORWARD','FORWARD')"
                                                id="electricalId" class="btn btn-primary"
                                                style="position: absolute; right: 10px;"><i class="fa fa-forward" aria-hidden="true" ;></i>
                                            Forward
                                        </button>
                                        <% } else { %>
                                        <label style="color: red; position: absolute; right: 10px;"><b><i>Forwarded</i></b></label>
                                        <% } %>
                                        <label id="electricalForward"
                                               style="display: none; color: red; position: absolute; right: 10px;"><b><i>Forwarded successfully</i></b></label>
                                        <%} else {%>
                                        <label style="color: red; position: absolute; right: 10px;"><b><i>Forwarded</i></b></label>
                                        <%}%>
                                    </div>
                                    <br/>
                                    <%BuildingDto isStructuralForward = (BuildingDto) request.getAttribute("is_structural_forwarded"); %>
                                    <div class="col-sm-4">
                                        <label name="is_Structural_Forwarded" id="isStrucVerificationReq"/> Structural
                                        Drawing</label>
                                        <%if (forwardDetails.getIs_Structural_Forwarded().equalsIgnoreCase("N")){%>
                                        <% if (isStructuralForward.getRowCount().intValue() == 0) { %>
                                        <button type="button"
                                                onclick="verfiyApplcication('STRUCTURAL_FORWARD','FORWARD')"
                                                id="structuralId" class="btn btn-primary" style="position: absolute; right: 10px;"><i class="fa fa-forward" aria-hidden="true" ;></i>
                                            Forward
                                        </button>
                                        <% } else { %>
                                        <label style="color: red; position: absolute; right: 10px;"><b><i>Forwarded</i></b></label>
                                        <% } %>
                                        <label id="structuralForward"
                                               style="display: none; color: red; position: absolute; right: 10px;"><b><i>Forwarded successfully</i></b></label>
                                        <%} else {%>
                                        <label style="color: red; position: absolute; right: 10px;"><b><i>Forwarded</i></b></label>
                                        <%}%>
                                    </div>
                                    <div class="form-group col-lg-12">
                                        <br><br>
                                        <label class="col-sm-2">Comments(if any):</label>

                                        <div class="col-sm-4">
                                            <textarea class="form-control bg-light border-secondary" name="appl_comments" id="comments"> </textarea>
                                        </div>
                                        <br><br>
                                        <div class="col-sm-4">
                                            <%if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Claimed") || checkStatus.getTask_Stage_Name().equalsIgnoreCase("Unhold")){%>
                                            <% if (isElectricalForward.getRowCount().intValue() == 0 || isStructuralForward.getRowCount().intValue() == 0){ %>
                                            <button onclick="actionType('VERIFIER','architectVerifier')"
                                                    disabled="true" class="btn btn-primary"><i class="fa fa-check"></i>Approve</button>
                                            <%} else {%>
                                            <% if (statusCount.getRowCount().intValue() == 0) { %>
                                            <a href="#" onclick="actionType('VERIFIER','architectVerifier')"
                                               class="btn btn-primary"><i class="fa fa-check"></i>Approve</a>
                                            <%} else{%>
                                            <button onclick="actionType('VERIFIER','architectVerifier')"
                                                    disabled="true" class="btn btn-primary"><i class="fa fa-check"></i>Approve</button>
                                            <%}%>
                                            <%}%>
                                            <a href="#" onclick="actionType('Hold','architectVerifier')"
                                               class="btn btn-warning"><i class="fa fa-pause"></i> Hold
                                            </a>
                                            <a href="#" onclick="actionType('Reject','architectVerifier')"
                                               class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                            </a>
                                            <%} else if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Hold")) {%>

                                            <a href="#" onclick="verfiyApplcication('UnHold','UNHOLD')"
                                               id="unhold_btn" class="btn btn-info"><i class="fa fa-folder-open"></i> UnHold
                                            </a>
                                            <%--<button class="btn btn-danger" disabled="true"
                                                    onclick="verfiyApplcication('FORWARD_DRAWING', 'FORWARD DRAWING')">Submit</button>
                                            <button class="btn btn-warning" disabled="true" onclick="actionType('Hold','architectVerifier')"><i class="fa fa-pause"></i> Hold
                                            </button>
                                            <button disabled="true" onclick="actionType('Reject','architectVerifier')"
                                                    class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                            </button>--%>
                                            <%}%>
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                                <div id="reject-tab" style="display: none;">
                                    <label class="col-lg-12">Reason :</label>

                                    <div class="col-lg-4">
                                        <select name="reason_id" id="reason_id" class="form-control bg-light border-secondary">
                                            <option value="0">Select</option>
                                            <c:forEach var="list" items="${reasonList}">
                                                <option value="${list.dropdownId}">${list.dropdownName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <br>
                                    <label class="col-lg-12">Reason Description:</label>

                                    <div class="col-lg-4">
                                        <textarea name="appl_comments" class="form-control bg-light border-secondary"></textarea>
                                    </div>
                                    <br/>

                                    <div id="confirmButtonDiv1" class="col-lg-12 bg-light border-secondary" style="display: none;">
                                        <a class="btn btn-warning" onclick="confirmAction('hold')">Hold</a>
                                        <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>

                                    </div>
                                    <div id="confirmButtonDiv3" class="col-lg-12 bg-light border-secondary" style="display: none;">
                                        <a class="btn btn-info" onclick="confirmAction('reject')">Reject</a>
                                        <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                    </div>
                                    <br/>
                                </div>
                                <input type="hidden" name="taskId" value="${taskId}">
                                <input type="hidden" name="isApproved" value="N" id="isApproved">
                                <input type="hidden" name="application_Id" value="${applicationNo}">
                                <input type="hidden" name="workflow_instance_Id" value="${applicationDetails.workflow_instance_Id}">
                                <input type="hidden" name="taskInstanceId" value="${applicationDetails.taskInstanceId}">
                                <input type="hidden" name="is_Architect_Forwarded" value="${applicationDetails.is_Architect_Forwarded}">
                                <input type="hidden" name="is_Structural_Forwarded" value="${applicationDetails.is_Structural_Forwarded}">
                                <input type="hidden" name="is_Electrical_Forwarded" value="${applicationDetails.is_Electrical_Forwarded}">
                                <%
                                    List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                    for (int i = 0; i < ownerList1.size(); i++) {
                                %>
                                <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                <%}%>


                            </form>
                        </div>
                        <div class="form-group col-lg-12 float-left" id="forwadId" style="display:none">
                            <form action="#" id="fowardForm" enctype="multipart/form-data">
                                <div id="forwardTo">
                                    <br/>
                                    <label class="control-label col-lg-2 float-left">Drawing To Be Forwarded:</label>
                                    <div class="col-lg-6 float-left">
                                        <% BuildingDto isElectForward = (BuildingDto) request.getAttribute("is_electrical_forwarded"); %>
                                        <%BuildingDto isStructForward = (BuildingDto) request.getAttribute("is_structural_forwarded"); %>
                                        <input type="checkbox" name="is_Architect_Forwarded" value="Y"/> Architectural Drawing<br/>
                                        <%if (forwardDetails.getIs_Structural_Forwarded().equalsIgnoreCase("N")){%>
                                        <%if (isStructForward.getRowCount().intValue()==0){%>
                                        <input type="checkbox" name="is_Structural_Forwarded" value="Y"/> Structural Drawing<br/>
                                        <%} else{%>
                                        <label style="color: red"><b><i>Structural Drawing Forwarded</i></b></label><br/>
                                        <%}%>
                                        <%}%>
                                        <%if (forwardDetails.getIs_Electrical_Forwarded().equalsIgnoreCase("N")) {%>
                                        <%if (isElectForward.getRowCount().intValue()==0){%>
                                        <input type="checkbox" name="is_Electrical_Forwarded" value="Y"/>  Electrical Drawing<br/>
                                        <%} else{%>
                                        <label style="color: red"><b><i>Electrical Drawing Forwarded</i></b></label>
                                        <%}%>
                                        <%}%>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group  col-lg-12 float-left forward-des-field">
                                    <br>
                                    <label class="control-label col-lg-2 float-left">Attach Forwarding letter:</label>
                                    <label class="control-label col-lg-2 float-left"><input type="file" name="appUploadFile"></label>
                                    <br> <br>
                                    <label class="col-lg-12">Comment (If any:)</label>
                                    <div class="col-lg-4">
                                        <textarea name="appl_comments" class="form-control bg-light border-secondary"></textarea>
                                    </div>
                                    <br/>
                                    <div id="confirmButtonDiv2" class="col-lg-12" style="display: none;">
                                        <a class="btn btn-info"
                                           onclick="verfiyApplcication('DZONGKHAG_TO_DES', 'DZONGKHAG TO DES')">Forward to DES</a>
                                    </div>
                                </div>
                                <input type="hidden" name="taskId" value="${taskId}">
                                <input type="hidden" name="isApproved" value="N" id="isApproved">
                                <input type="hidden" name="application_Id" value="${applicationNo}">
                                <input type="hidden" name="workflow_instance_Id"value="${applicationDetails.workflow_instance_Id}">
                                <input type="hidden" name="taskInstanceId" value="${applicationDetails.taskInstanceId}">
                                <%
                                    List<OwnerEntity> ownerList2 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                    for (int i = 0; i < ownerList2.size(); i++) {
                                %>
                                <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                <%}%>
                            </form>
                        </div>
                        <% } else if (request.getAttribute("taskId").equals("17") && request.getAttribute("userRole").equals("DOES Architect")) {%>
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                <div class="verifier-tab">
                                    <div class="form-group  col-lg-12 float-left" id="showHideDocument17">
                                        <label class="control-label col-lg-2 float-left"> Attached Documents :</label>
                                        <div class="container">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>Sl.No</th>
                                                        <th>File Type</th>
                                                        <th>File Name</th>
                                                        <th colspan="2">Action</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="doc" items="${documents}" varStatus="counter">
                                                        <tr>
                                                            <td>${counter.index + 1}</td>
                                                            <td>${doc.document_Type}</td>
                                                            <td>${doc.document_Name}</td>
                                                            <td><button class="btn btn-outline-warning" type="button" onclick="viewAttachment('${doc.document_id}','download')"
                                                                        target="_blank"><i class="fa fa-download"></i> Download
                                                            </button></td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <% BuildingDto isDesElectricalForward = (BuildingDto) request.getAttribute("is_electrical_forwarded"); %>
                                    <% BuildingDto isDesStructuralForward = (BuildingDto) request.getAttribute("is_structural_forwarded"); %>
                                    <% BuildingDto forwardDetails = (BuildingDto) request.getAttribute("applicationDetails"); %>
                                    <% TasklistDto statusCount = (TasklistDto) request.getAttribute("statusCount"); %>
                                    <% TasklistDto checkStatus = (TasklistDto) request.getAttribute("checkHold"); %>
                                    <% if(forwardDetails.getIs_Electrical_Forwarded().equalsIgnoreCase("Y") && forwardDetails.getIs_Architect_Forwarded().equalsIgnoreCase("Y") && forwardDetails.getIs_Structural_Forwarded().equalsIgnoreCase("Y")){%>
                                    <div class="col-sm-4">
                                        <label> Electrical Drawing</label>
                                        <% if (isDesElectricalForward.getRowCount().intValue() == 0) { %>

                                        <button type="button" onclick="verfiyApplcication('ELECTRICAL_FORWARD','FORWARD')"
                                                id="electricalId" class="btn btn-primary"
                                                style="position: absolute; right: 10px;"><i class="fa fa-forward"
                                                                                            aria-hidden="true" ;></i>
                                            Forward
                                        </button>
                                        <% } else { %>
                                        <label style="color: red; position: absolute; right: 10px;"><b><i>Forwarded</i></b></label>
                                        <% } %>
                                        <label id="electricalForward"
                                               style="display: none; color: red; position: absolute; right: 10px;"><b><i>Forwarded successfully</i></b></label>
                                    </div>
                                    <br/>
                                    <div class="col-sm-4">
                                        <label> Structural Drawing</label>
                                        <% if (isDesStructuralForward.getRowCount().intValue() == 0 ) { %>
                                        <button type="button" onclick="verfiyApplcication('STRUCTURAL_FORWARD','FORWARD')"
                                                id="structuralId" class="btn btn-primary"
                                                style="position: absolute; right: 10px;"><i class="fa fa-forward"
                                                                                            aria-hidden="true" ;></i>
                                            Forward
                                        </button>
                                        <% } else { %>
                                        <label style="color: red; position: absolute; right: 10px;"><b><i>Forwarded</i></b></label>
                                        <% } %>

                                        <label id="structuralForward"
                                               style="display: none; color: red; position: absolute; right: 10px;"><b><i>Forwarded successfully</i></b></label>
                                    </div>
                                    <br/>
                                    <%} else if(forwardDetails.getIs_Architect_Forwarded().equalsIgnoreCase("Y") && forwardDetails.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")){%>
                                    <div class="col-sm-4">
                                        <label> Electrical Drawing</label>
                                        <% if (isDesElectricalForward.getRowCount().intValue() == 0) { %>
                                        <button type="button" onclick="verfiyApplcication('ELECTRICAL_FORWARD','FORWARD')"
                                                id="electricalId" class="btn btn-primary"
                                                style="position: absolute; right: 10px;"><i class="fa fa-forward"
                                                                                            aria-hidden="true" ;></i>
                                            Forward
                                        </button>
                                        <% } else { %>
                                        <label style="color: red; position: absolute; right: 1px;"><b><i>Forwarded</i></b></label>
                                        <% } %>
                                        <label id="electricalForward"
                                               style="display: none; color: red; position: absolute; right: 10px;"><b><i>Forwarded successfully</i></b></label>
                                    </div>
                                    <%} else if(forwardDetails.getIs_Architect_Forwarded().equalsIgnoreCase("Y") && forwardDetails.getIs_Structural_Forwarded().equalsIgnoreCase("Y")) {%>
                                    <div class="col-sm-4">
                                        <label> Structural Drawing</label>
                                        <% if (isDesStructuralForward.getRowCount().intValue() == 0 ) { %>
                                        <button type="button" onclick="verfiyApplcication('STRUCTURAL_FORWARD','FORWARD')"
                                                id="structuralId" class="btn btn-primary"
                                                style="position: absolute; right: 10px;"><i class="fa fa-forward"
                                                                                            aria-hidden="true" ;></i>
                                            Forward
                                        </button>
                                        <% } else { %>
                                        <label style="color: red; position: absolute; right: 10px;"><b><i>Forwarded</i></b></label>
                                        <% } %>
                                        <label id="structuralForward"
                                               style="display: none; color: red; position: absolute; right: 10px;"><b><i>Forwarded successfully</i></b></label>
                                    </div>
                                    <%} else if (forwardDetails.getIs_Architect_Forwarded()!=null){%>
                                    <div class="col-sm-4">
                                        <label> Architectural Drawing</label><br/>
                                    </div>
                                    <%} else{%>
                                    <% if (isDesElectricalForward.getRowCount().intValue()!= 0) { %>
                                    <div class="col-sm-4">
                                        <label> Electrical
                                            Drawing</label>
                                        <label style="color: red; position: absolute; right: 10px;"><b><i>Forwarded</i></b></label>
                                        <%}%>
                                    </div>
                                    <br>
                                    <% if (isDesStructuralForward.getRowCount().intValue()!= 0) { %>
                                    <div class="col-sm-4">
                                        <label> Structural
                                            Drawing</label>
                                        <label style="color: red; position: absolute; right: 10px;"><b><i>Forwarded</i></b></label>
                                        <%}%>
                                    </div>
                                    <%}%>
                                    <br>
                                    <div class="form-group col-lg-12">
                                        <label>Comments(if any):</label>
                                        <div>
                                            <textarea class="form-control bg-light border-secondary" name="appl_comments" id="comments"></textarea>
                                            <br>
                                        </div>
                                        <div>
                                            <%if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Claimed") || checkStatus.getTask_Stage_Name().equalsIgnoreCase("Unhold"))
                                            {%>
                                                <%if (forwardDetails.getIs_Architect_Forwarded().equalsIgnoreCase("Y") && forwardDetails.getIs_Electrical_Forwarded().equalsIgnoreCase("N") &&
                                                    forwardDetails.getIs_Structural_Forwarded().equalsIgnoreCase("N"))
                                                {%>
                                                    <a href="#" onclick="actionType('VERIFIER','architectVerifier')" class="btn btn-primary"><i class="fa fa-check"></i>Approve</a>
                                                <%} else if(forwardDetails.getIs_Architect_Forwarded().equalsIgnoreCase("Y") && forwardDetails.getIs_Electrical_Forwarded().equalsIgnoreCase("Y") &&
                                                    forwardDetails.getIs_Structural_Forwarded().equalsIgnoreCase("Y"))
                                                {%>
                                                    <%if(isDesStructuralForward.getRowCount().intValue() == 0 && isDesElectricalForward.getRowCount().intValue() == 0){%>
                                                    <button onclick="actionType('VERIFIER','architectVerifier')" disabled="true" class="btn btn-primary"><i class="fa fa-check"></i>Approve</button>
                                                    <%}else{%>
                                                        <% if(statusCount.getRowCount().intValue() == 0){%>
                                                            <a href="#" onclick="actionType('VERIFIER','architectVerifier')" class="btn btn-primary"><i class="fa fa-check"></i>Approve</a>
                                                        <%}else{%>
                                                            <button onclick="actionType('VERIFIER','architectVerifier')" disabled="true" class="btn btn-primary"><i class="fa fa-check"></i>Approve</button>
                                                        <%}%>
                                                        <%--<button onclick="actionType('VERIFIER','architectVerifier')" disabled="true" class="btn btn-primary"><i class="fa fa-check"></i>Approve</button>--%>
                                                    <%}%>
                                                <%}else{%>
                                                    <%if(forwardDetails.getIs_Structural_Forwarded().equalsIgnoreCase("Y")){%>
                                                         <%if(isDesStructuralForward.getRowCount().intValue() == 0){%>
                                                            <button onclick="actionType('VERIFIER','architectVerifier')" disabled="true" class="btn btn-primary"><i class="fa fa-check"></i>Approve</button>
                                                         <%}else{%>
                                                            <% if(statusCount.getRowCount().intValue() == 0){%>
                                                                <a href="#" onclick="actionType('VERIFIER','architectVerifier')" class="btn btn-primary"><i class="fa fa-check"></i>Approve</a>
                                                            <%}else{%>
                                                                <button onclick="actionType('VERIFIER','architectVerifier')" disabled="true" class="btn btn-primary"><i class="fa fa-check"></i>Approve</button>
                                                            <%}%>
                                                         <%}%>
                                                    <%}%>
                                                    <%if(forwardDetails.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")){%>
                                                        <% if(isDesElectricalForward.getRowCount().intValue() == 0){%>
                                                            <button onclick="actionType('VERIFIER','architectVerifier')" disabled="true" class="btn btn-primary"><i class="fa fa-check"></i>Approve</button>
                                                        <%} else {%>
                                                            <% if (statusCount.getRowCount().intValue() == 0){%>
                                                                <a href="#" onclick="actionType('VERIFIER','architectVerifier')" class="btn btn-primary"><i class="fa fa-check"></i>Approve</a>
                                                            <%} else{%>
                                                                <button onclick="actionType('VERIFIER','architectVerifier')" disabled="true" class="btn btn-primary"><i class="fa fa-check"></i>Approve</button>
                                                            <%}%>
                                                        <%}%>
                                                    <%}%>
                                                <%}%>
                                            <a href="#" onclick="actionType('Hold','architectVerifier')" class="btn btn-warning"><i class="fa fa-pause"></i> Hold</a>
                                            <a href="#" onclick="actionType('Reject','architectVerifier')" class="btn btn-danger"><i class="fa fa-ban"></i> Reject</a>
                                            <%} else if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Hold")){%>
                                            <a href="#" onclick="verfiyApplcication('UnHold','UNHOLD')" id="unhold_btn" class="btn btn-info"><i class="fa fa-folder-open"></i> UnHold</a>
                                            <%--<button class="btn btn-danger" disabled="true"
                                                    onclick="verfiyApplcication('FORWARD_DRAWING', 'FORWARD DRAWING')">Submit</button>
                                            <button class="btn btn-warning" disabled="true" onclick="actionType('Hold','architectVerifier')"><i class="fa fa-pause"></i> Hold
                                            </button>
                                            <button disabled="true" onclick="actionType('Reject','architectVerifier')"
                                                    class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                            </button>--%>
                                            <%}%>
                                        </div>
                                    </div>
                                </div>
                                <div id="reject-tab" style="display: none;">
                                    <label class="col-lg-12">Reason :</label>

                                    <div class="col-lg-4">
                                        <select name="reason_id" id="reason_id" class="form-control bg-light border-secondary">
                                            <option value="0">Select</option>
                                            <c:forEach var="list" items="${reasonList}">
                                                <option value="${list.dropdownId}">${list.dropdownName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <br>
                                    <label class="col-lg-12">Reason Description:</label>
                                    <div class="col-lg-4">
                                        <textarea name="appl_comments" class="form-control bg-light border-secondary"></textarea>
                                    </div>
                                    <br/>
                                    <div id="confirmButtonDiv1" class="col-lg-12" style="display: none;">
                                        <a class="btn btn-warning" onclick="confirmAction('hold')">Hold</a>
                                        <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                    </div>
                                    <div id="confirmButtonDiv3" class="col-lg-12" style="display: none;">
                                        <a class="btn btn-info" onclick="confirmAction('reject')">Reject</a>
                                        <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                    </div>
                                    <br/>
                                </div>
                                    <br/>
                                    <input type="hidden" name="taskId" value="${taskId}">
                                    <input type="hidden" name="isApproved" value="N" id="isApproved">
                                    <input type="hidden" name="application_Id" value="${applicationNo}">
                                    <%
                                        List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                        for (int i = 0; i < ownerList1.size(); i++) {
                                    %>
                                    <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                    <%}%>
                                    <input type="hidden" name="workflow_instance_Id"
                                           value="${applicationDetails.workflow_instance_Id}">
                                    <input type="hidden" name="taskInstanceId" value="${applicationDetails.taskInstanceId}">
                                    <input type="hidden" name="is_Architect_Forwarded" value="${applicationDetails.is_Architect_Forwarded}">
                                    <input type="hidden" name="is_Structural_Forwarded" value="${applicationDetails.is_Structural_Forwarded}">
                                    <input type="hidden" name="is_Electrical_Forwarded" value="${applicationDetails.is_Electrical_Forwarded}">
                            </form>
                        <%} else if(request.getAttribute("taskId").equals("16") && request.getAttribute("userRole").equals("DOES Operater")){ %>
                        <div class="form-group col-lg-12 float-left" id="forwardWithiDES" style="display:none">
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                <div class="form-group col-lg-12 verifier">
                                    <div class="verifier-tab">
                                        <div class="form-group  col-lg-12 float-left" id="showHideDocument16">
                                            <label class="control-label col-lg-2 float-left"> Attached Documents :</label>
                                            <div class="container">
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th>Sl.No</th>
                                                            <th>File Type</th>
                                                            <th>File Name</th>
                                                            <th colspan="2">Action</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach var="doc" items="${documents}" varStatus="counter">
                                                            <tr>
                                                                <td>${counter.index + 1}</td>
                                                                <td>${doc.document_Type}</td>
                                                                <td>${doc.document_Name}</td>
                                                                <td><button class="btn btn-outline-warning" type="button" onclick="viewAttachment('${doc.document_id}','download')"
                                                                            target="_blank"><i class="fa fa-download"></i> Download
                                                                </button></td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div>
                                            <label class="control-label col-lg-2 float-left">Drawing Forwarded:</label>
                                        </div>
                                        <div class="col-lg-6 float-left">
                                            <% BuildingDto forwardDetails = (BuildingDto) request.getAttribute("applicationDetails"); %>
                                            <% TasklistDto checkStatus = (TasklistDto) request.getAttribute("checkHold"); %>
                                            <% if (forwardDetails.getIs_Architect_Forwarded().equalsIgnoreCase("Y")) { %>
                                            <input type="checkbox" name="is_Architect_Forwarded" value="Y" checked /> Architectural Drawing<br/>
                                            <% } %>
                                            <% if (forwardDetails.getIs_Structural_Forwarded().equalsIgnoreCase("Y")) { %>
                                            <input type="checkbox" name="is_Structural_Forwarded" value="Y" checked /> Structural Drawing<br/>
                                            <% } %>
                                            <% if (forwardDetails.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")) { %>
                                            <input type="checkbox" name="is_Electrical_Forwarded" value="Y" checked  /> Electrical Drawing<br/>
                                            <% } %>
                                            <br><br>
                                        </div>
                                        <div class="form-group col-lg-6">
                                            <label >Any Forwarding letter?</label>
                                            <input type="radio" value="Y" id="yes" name="Yes_No" onclick="checkforwaringLetter('Y')"> <label for="yes"> Yes</label>
                                            <input type="radio" value="N" id="no" name="Yes_No" onclick="checkforwaringLetter('N')"> <label for="no">No</label>
                                        </div>
                                        <div class="form-group col-lg-12" id="forward_letter1">
                                        </div>
                                        <script>
                                            function checkforwaringLetter(dataType){
                                                if (dataType=="Y"){
                                                    $('#forward_letter1').show();

                                                    $("#forward_letter1").html('<input type="file" name="appUploadFile">');
                                                }
                                                else{
                                                    $('#forward_letter1').html('');
                                                    $('#forward_letter1').hide();
                                                }
                                            }
                                        </script>
                                        <div class="form-group col-lg-12">
                                            <label>Comment (If any:)</label>
                                        </div>
                                        <div class="form-group col-lg-12">
                                            <textarea name="appl_comments" id="DEScomments" class="form-control bg-light border-secondary"></textarea>
                                        </div>
                                        <div class="col-sm-4">
                                            <%if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Claimed") || checkStatus.getTask_Stage_Name().equalsIgnoreCase("Unhold")){%>
                                            <a href="#" class="btn btn-info"
                                               onclick="verfiyApplcication('FORWARD_DRAWING', 'FORWARD DRAWING')">Submit</a>
                                            <a href="#" onclick="actionType('Hold','architectVerifier')"
                                               class="btn btn-warning"><i class="fa fa-pause"></i> Hold
                                            </a>
                                            <%} else if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Hold")){%>
                                            <a href="#" onclick="verfiyApplcication('UnHold','UNHOLD')"
                                               id="unhold_btn" class="btn btn-info"><i class="fa fa-folder-open"></i> UnHold
                                            </a>
                                            <%--<button class="btn btn-danger" disabled="true"
                                               onclick="verfiyApplcication('FORWARD_DRAWING', 'FORWARD DRAWING')">Submit</button>
                                            <button disabled="true" onclick="actionType('Hold','architectVerifier')"
                                               class="btn btn-warning"><i class="fa fa-pause"></i> Hold
                                            </button>
                                            <button disabled="true" onclick="actionType('Reject','architectVerifier')"
                                               class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                            </button>--%>
                                            <%}%>
                                        </div>
                                    </div>
                                    <div id="reject-tab" style="display: none;">
                                        <label class="col-lg-12">Reason :</label>

                                        <div class="col-lg-4">
                                            <select name="reason_id" id="reason_id" class="form-control bg-light border-secondary">
                                                <option value="0">Select</option>
                                                <c:forEach var="list" items="${reasonList}">
                                                    <option value="${list.dropdownId}">${list.dropdownName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <br>
                                        <label class="col-lg-12">Reason Description:</label>
                                        <div class="col-lg-4">
                                            <textarea name="appl_comments" class="form-control bg-light border-secondary"></textarea>
                                        </div>
                                        <br/>
                                        <div id="confirmButtonDiv1" class="col-lg-12" style="display: none;">
                                            <a class="btn btn-warning" onclick="confirmAction('hold')">Hold</a>
                                            <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                        </div>
                                        <div id="confirmButtonDiv3" class="col-lg-12" style="display: none;">
                                            <a class="btn btn-info" onclick="confirmAction('reject')">Reject</a>
                                            <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                        </div>
                                        <br/>
                                    </div>
                                    <div class="col-sm-4 " id="buttonsId">
                                        <input type="hidden" name="taskId" value="${taskId}">
                                        <input type="hidden" name="isApproved" value="N" id="isApproved">
                                        <input type="hidden" name="application_Id" value="${applicationNo}">
                                        <input type="hidden" name="workflow_instance_Id"
                                               value="${applicationDetails.workflow_instance_Id}">
                                        <%
                                            List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                            for (int i = 0; i < ownerList1.size(); i++) {
                                        %>
                                        <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                        <%}%>
                                        <input type="hidden" name="taskInstanceId"
                                               value="${applicationDetails.taskInstanceId}">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <%} else if(request.getAttribute("taskId").equals("26")){ %>
                        <div class="form-group col-lg-12 float-left" id="forwardWithiDES" style="display:none">
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                <div class="form-group col-lg-12 verifier">
                                    <div class="verifier-tab">
                                        <div class="form-group  col-lg-12 float-left" id="showHideDocument16">
                                            <label class="control-label col-lg-2 float-left"> Attached Documents :</label>
                                            <div class="container">
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th>Sl.No</th>
                                                            <th>File Type</th>
                                                            <th>File Name</th>
                                                            <th colspan="2">Action</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach var="doc" items="${documents}" varStatus="counter">
                                                            <tr>
                                                                <td>${counter.index + 1}</td>
                                                                <td>${doc.document_Type}</td>
                                                                <td>${doc.document_Name}</td>
                                                                <td><button class="btn btn-outline-warning" type="button" onclick="viewAttachment('${doc.document_id}','download')"
                                                                            target="_blank"><i class="fa fa-download"></i> Download
                                                                </button></td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div>
                                            <label class="control-label col-lg-2 float-left">Drawing To Be Forwarded:</label>
                                        </div>
                                        <div class="col-lg-6 float-left">
                                            <% BuildingDto forwardDetails = (BuildingDto) request.getAttribute("applicationDetails"); %>
                                            <% TasklistDto checkStatus = (TasklistDto) request.getAttribute("checkHold"); %>
                                            <% if (forwardDetails.getIs_Structural_Forwarded().equalsIgnoreCase("Y")) { %>
                                            <input type="checkbox" name="is_Structural_Forwarded" value="Y" checked /> Structural Drawing<br/>
                                            <% } %>
                                            <br><br>
                                        </div>
                                        <%if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Claimed")){%>
                                        <div class="form-group col-lg-6">
                                            <label >Any Forwarding letter?</label>
                                            <input type="radio" value="Y" id="yes" name="Yes_No" onclick="checkforwaringLetter('Y')"> <label for="yes"> Yes</label>
                                            <input type="radio" value="N" id="no" name="Yes_No" onclick="checkforwaringLetter('N')"> <label for="no">No</label>
                                        </div>
                                        <div class="form-group col-lg-12" id="forward_letter1">
                                        </div>
                                        <script>
                                            function checkforwaringLetter(dataType){
                                                if (dataType=="Y"){
                                                    $('#forward_letter1').show();
                                                    $("#forward_letter1").html('<input type="file" name="appUploadFile">');
                                                }
                                                else{
                                                    $('#forward_letter1').html('');
                                                    $('#forward_letter1').hide();
                                                }
                                            }
                                        </script>
                                        <%}%>
                                        <div class="form-group col-lg-8">
                                            <label>Comment (If any:)</label>
                                        </div>
                                        <div class="form-group col-lg-12">
                                            <textarea name="appl_comments" id="DEScomments" class="form-control bg-light border-secondary"></textarea>
                                        </div>
                                        <div class="col-sm-4">
                                            <%if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Claimed") || checkStatus.getTask_Stage_Name().equalsIgnoreCase("Unhold")){%>
                                            <a href="#" class="btn btn-info"
                                               onclick="verfiyApplcication('FORWARD_DRAWING_TO_DES', 'FORWARD DRAWING')">Submit</a>
                                            <a href="#" onclick="actionType('Hold','architectVerifier')"
                                               class="btn btn-warning"><i class="fa fa-pause"></i> Hold
                                            </a>
                                            <a href="#" onclick="actionType('Reject','architectVerifier')"
                                               class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                            </a>
                                            <%} else if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Hold")){%>
                                            <a href="#" onclick="verfiyApplcication('UnHold','UNHOLD')"
                                               id="unhold_btn" class="btn btn-info"><i class="fa fa-folder-open"></i> UnHold
                                            </a>
                                            <%--<button class="btn btn-danger" disabled="true"
                                                    onclick="verfiyApplcication('FORWARD_DRAWING', 'FORWARD DRAWING')">Submit</button>
                                            <button disabled="true" onclick="actionType('Hold','architectVerifier')"
                                                    class="btn btn-warning"><i class="fa fa-pause"></i> Hold
                                            </button>
                                            <button disabled="true" onclick="actionType('Reject','architectVerifier')"
                                                    class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                            </button>--%>
                                            <%}%>
                                        </div>
                                    </div>
                                    <div id="reject-tab" style="display: none;">
                                        <label class="col-lg-12">Reason :</label>

                                        <div class="col-lg-4">
                                            <select name="reason_id" id="reason_id" class="form-control bg-light border-secondary">
                                                <option value="0">Select</option>
                                                <c:forEach var="list" items="${reasonList}">
                                                    <option value="${list.dropdownId}">${list.dropdownName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <br>
                                        <label class="col-lg-12">Reason Description:</label>
                                        <div class="col-lg-4">
                                            <textarea name="appl_comments" class="form-control bg-light border-secondary"></textarea>
                                        </div>
                                        <br/>
                                        <div id="confirmButtonDiv1" class="col-lg-12" style="display: none;">
                                            <a class="btn btn-warning" onclick="confirmAction('hold')">Hold</a>
                                            <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                        </div>
                                        <div id="confirmButtonDiv3" class="col-lg-12" style="display: none;">
                                            <a class="btn btn-info" onclick="confirmAction('reject')">Reject</a>
                                            <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                        </div>
                                        <br/>
                                    </div>
                                    <div class="col-sm-4 " id="buttonsId">
                                        <input type="hidden" name="taskId" value="${taskId}">
                                        <input type="hidden" name="isApproved" value="N" id="isApproved">
                                        <input type="hidden" name="application_Id" value="${applicationNo}">
                                        <input type="hidden" name="workflow_instance_Id"
                                               value="${applicationDetails.workflow_instance_Id}">
                                        <%
                                            List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                            for (int i = 0; i < ownerList1.size(); i++) {
                                        %>
                                        <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                        <%}%>
                                        <input type="hidden" name="taskInstanceId"
                                               value="${applicationDetails.taskInstanceId}">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <%} else if(request.getAttribute("taskId").equals("27")){ %>
                        <div class="form-group col-lg-12 float-left" id="forwardWithiDES" style="display:none">
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                <div class="form-group col-lg-12 verifier">
                                    <div class="verifier-tab">
                                        <div class="form-group  col-lg-12 float-left" id="showHideDocument16">
                                            <label class="control-label col-lg-2 float-left"> Attached Documents :</label>
                                            <div class="container">
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th>Sl.No</th>
                                                            <th>File Type</th>
                                                            <th>File Name</th>
                                                            <th colspan="2">Action</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach var="doc" items="${documents}" varStatus="counter">
                                                            <tr>
                                                                <td>${counter.index + 1}</td>
                                                                <td>${doc.document_Type}</td>
                                                                <td>${doc.document_Name}</td>
                                                                <td><button class="btn btn-outline-warning" type="button" onclick="viewAttachment('${doc.document_id}','download')"
                                                                            target="_blank"><i class="fa fa-download"></i> Download
                                                                </button></td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div>
                                            <label class="control-label col-lg-2 float-left">Drawing To Be Forwarded:</label>
                                        </div>
                                        <div class="col-lg-6 float-left">
                                            <% BuildingDto forwardDetails = (BuildingDto) request.getAttribute("applicationDetails"); %>
                                            <% TasklistDto checkStatus = (TasklistDto) request.getAttribute("checkHold"); %>

                                            <% if (forwardDetails.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")) { %>
                                            <input type="checkbox" name="is_Electrical_Forwarded" value="Y" checked  /> Electrical Drawing<br/>
                                            <% } %>
                                            <br><br>
                                        </div>
                                        <%if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Claimed")){%>
                                        <div class="form-group col-lg-6">
                                            <label >Any Forwarding letter?</label>
                                            <input type="radio" value="Y" id="yes" name="Yes_No" onclick="checkforwaringLetter('Y')"> <label for="yes"> Yes</label>
                                            <input type="radio" value="N" id="no" name="Yes_No" onclick="checkforwaringLetter('N')"> <label for="no">No</label>
                                        </div>
                                        <div class="form-group col-lg-12" id="forward_letter1">
                                        </div>
                                        <script>
                                            function checkforwaringLetter(dataType){
                                                if (dataType=="Y"){
                                                    $('#forward_letter1').show();

                                                    $("#forward_letter1").html('<input type="file" name="appUploadFile">');
                                                }
                                                else{
                                                    $('#forward_letter1').html('');
                                                    $('#forward_letter1').hide();
                                                }
                                            }
                                        </script>
                                        <%}%>
                                        <div class="form-group col-lg-8">
                                            <label>Comment (If any:)</label>
                                        </div>
                                        <div class="form-group col-lg-12">
                                            <textarea name="appl_comments" id="DEScomments" class="form-control bg-light border-secondary"></textarea>
                                        </div>
                                        <div class="col-sm-4">
                                            <%if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Claimed") || checkStatus.getTask_Stage_Name().equalsIgnoreCase("Unhold")){%>
                                            <a href="#" class="btn btn-info"
                                               onclick="verfiyApplcication('FORWARD_DRAWING_TO_DES', 'FORWARD DRAWING')">Submit</a>
                                            <a href="#" onclick="actionType('Hold','architectVerifier')"
                                               class="btn btn-warning"><i class="fa fa-pause"></i> Hold
                                            </a>
                                            <a href="#" onclick="actionType('Reject','architectVerifier')"
                                               class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                            </a>
                                            <%} else if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Hold")){%>
                                            <a href="#" onclick="verfiyApplcication('UnHold','UNHOLD')"
                                               id="unhold_btn" class="btn btn-info"><i class="fa fa-folder-open"></i> UnHold
                                            </a>
                                            <%--<button class="btn btn-danger" disabled="true"
                                                        onclick="verfiyApplcication('FORWARD_DRAWING', 'FORWARD DRAWING')">Submit</button>
                                            <button class="btn btn-warning" disabled="true" onclick="actionType('Hold','architectVerifier')"><i class="fa fa-pause"></i> Hold
                                            </button>
                                            <button disabled="true" onclick="actionType('Reject','architectVerifier')"
                                                    class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                            </button>--%>
                                            <%}%>
                                        </div>
                                    </div>
                                    <div id="reject-tab" style="display: none;">
                                        <label class="col-lg-12">Reason :</label>

                                        <div class="col-lg-4">
                                            <select name="reason_id" id="reason_id" class="form-control bg-light border-secondary">
                                                <option value="0">Select</option>
                                                <c:forEach var="list" items="${reasonList}">
                                                    <option value="${list.dropdownId}">${list.dropdownName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <br>
                                        <label class="col-lg-12">Reason Description:</label>
                                        <div class="col-lg-4">
                                            <textarea name="appl_comments" class="form-control bg-light border-secondary"></textarea>
                                        </div>
                                        <br/>
                                        <div id="confirmButtonDiv1" class="col-lg-12" style="display: none;">
                                            <a class="btn btn-warning" onclick="confirmAction('hold')">Hold</a>
                                            <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                        </div>
                                        <div id="confirmButtonDiv3" class="col-lg-12" style="display: none;">
                                            <a class="btn btn-info" onclick="confirmAction('reject')">Reject</a>
                                            <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                        </div>
                                        <br/>
                                    </div>
                                    <div class="col-sm-4 " id="buttonsId">
                                        <input type="hidden" name="taskId" value="${taskId}">
                                        <input type="hidden" name="isApproved" value="N" id="isApproved">
                                        <input type="hidden" name="application_Id" value="${applicationNo}">
                                        <input type="hidden" name="workflow_instance_Id"
                                               value="${applicationDetails.workflow_instance_Id}">
                                        <%
                                            List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                            for (int i = 0; i < ownerList1.size(); i++) {
                                        %>
                                        <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                        <%}%>
                                        <input type="hidden" name="taskInstanceId"
                                               value="${applicationDetails.taskInstanceId}">
                                    </div>
                                </div>
                            </form>
                        </div>

                        <%} else if(request.getAttribute("taskId").equals("28")){ %>
                        <div class="form-group col-lg-12 float-left" id="forwardWithiDES" style="display:none">
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                <div class="form-group col-lg-12 verifier">
                                    <div class="verifier-tab">
                                        <div class="form-group  col-lg-12 float-left" id="showHideDocument16">
                                            <label class="control-label col-lg-2 float-left"> Attached Documents :</label>
                                            <div class="container">
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th>Sl.No</th>
                                                            <th>File Type</th>
                                                            <th>File Name</th>
                                                            <th colspan="2">Action</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach var="doc" items="${documents}" varStatus="counter">
                                                            <tr>
                                                                <td>${counter.index + 1}</td>
                                                                <td>${doc.document_Type}</td>
                                                                <td>${doc.document_Name}</td>
                                                                <td><button class="btn btn-outline-warning" type="button" onclick="viewAttachment('${doc.document_id}','download')"
                                                                            target="_blank"><i class="fa fa-download"></i> Download
                                                                </button></td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div>
                                            <label class="control-label col-lg-2 float-left">Drawing To Be Forwarded:</label>
                                        </div>
                                        <div class="col-lg-6 float-left">
                                            <% BuildingDto forwardDetails = (BuildingDto) request.getAttribute("applicationDetails"); %>
                                            <% TasklistDto checkStatus = (TasklistDto) request.getAttribute("checkHold"); %>
                                            <% if (forwardDetails.getIs_Structural_Forwarded().equalsIgnoreCase("Y")) { %>
                                            <input type="checkbox" name="is_Structural_Forwarded" value="Y" checked /> Structural Drawing<br/>
                                            <% } %>
                                            <% if (forwardDetails.getIs_Architect_Forwarded().equalsIgnoreCase("Y")) { %>
                                            <input type="checkbox" name="is_Architect_Forwarded" value="Y" checked /> Architectural Drawing<br/>
                                            <% } %>
                                            <% if (forwardDetails.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")) { %>
                                            <input type="checkbox" name="is_Electrical_Forwarded" value="Y" checked /> Electrical Drawing<br/>
                                            <% } %>
                                            <br>
                                        </div>
                                        <%if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Claimed")){%>
                                        <div class="form-group col-lg-6">
                                            <label >Any Forwarding letter?</label>
                                            <input type="radio" value="Y" id="yes" name="Yes_No" onclick="checkforwaringLetter('Y')"> <label for="yes"> Yes</label>
                                            <input type="radio" value="N" id="no" name="Yes_No" onclick="checkforwaringLetter('N')"> <label for="no">No</label>
                                        </div>
                                        <div class="form-group col-lg-12" id="forward_letter1">
                                        </div>
                                        <script>
                                            function checkforwaringLetter(dataType){
                                                if (dataType=="Y"){
                                                    $('#forward_letter1').show();
                                                    $("#forward_letter1").html('<input type="file" name="appUploadFile">');
                                                }
                                                else{
                                                    $('#forward_letter1').html('');
                                                    $('#forward_letter1').hide();
                                                }
                                            }
                                        </script>
                                        <%}%>
                                        <div class="form-group col-lg-8">
                                            <label>Comment (If any:)</label>
                                        </div>
                                        <div class="form-group col-lg-12">
                                            <textarea name="appl_comments" id="DEScomments" class="form-control bg-light border-secondary"></textarea>
                                        </div>
                                        <div class="col-sm-4">
                                            <%if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Claimed") || checkStatus.getTask_Stage_Name().equalsIgnoreCase("Unhold")){%>
                                            <a href="#" class="btn btn-info"
                                               onclick="verfiyApplcication('FORWARD_DRAWING_TO_DES', 'FORWARD DRAWING')">Submit</a>
                                            <a href="#" onclick="actionType('Hold','architectVerifier')"
                                               class="btn btn-warning"><i class="fa fa-pause"></i> Hold
                                            </a>
                                            <%} else if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Hold")){%>
                                            <a href="#" onclick="verfiyApplcication('UnHold','UNHOLD')"
                                               id="unhold_btn" class="btn btn-info"><i class="fa fa-folder-open"></i> UnHold
                                            </a>
                                            <%--<button class="btn btn-danger" disabled="true"
                                                    onclick="verfiyApplcication('FORWARD_DRAWING_TO_DES', 'FORWARD DRAWING')">Submit</button>
                                            <button disabled="true" onclick="actionType('Hold','architectVerifier')"
                                                    class="btn btn-warning"><i class="fa fa-pause"></i> Hold
                                            </button>
                                            <button disabled="true" onclick="actionType('Reject','architectVerifier')"
                                                    class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                            </button>--%>
                                            <%}%>
                                        </div>
                                    </div>
                                    <div id="reject-tab" style="display: none;">
                                        <label class="col-lg-12">Reason :</label>

                                        <div class="col-lg-4">
                                            <select name="reason_id" id="reason_id" class="form-control bg-light border-secondary">
                                                <option value="0">Select</option>
                                                <c:forEach var="list" items="${reasonList}">
                                                    <option value="${list.dropdownId}">${list.dropdownName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <br>
                                        <label class="col-lg-12">Reason Description:</label>
                                        <div class="col-lg-4">
                                            <textarea name="appl_comments" class="form-control bg-light border-secondary"></textarea>
                                        </div>
                                        <br/>
                                        <div id="confirmButtonDiv1" class="col-lg-12" style="display: none;">
                                            <a class="btn btn-warning" onclick="confirmAction('hold')">Hold</a>
                                            <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                        </div>
                                        <div id="confirmButtonDiv3" class="col-lg-12" style="display: none;">
                                            <a class="btn btn-info" onclick="confirmAction('reject')">Reject</a>
                                            <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                        </div>
                                        <br/>
                                    </div>
                                    <div class="col-sm-4 " id="buttonsId">
                                        <input type="hidden" name="taskId" value="${taskId}">
                                        <input type="hidden" name="isApproved" value="N" id="isApproved">
                                        <input type="hidden" name="application_Id" value="${applicationNo}">
                                        <input type="hidden" name="workflow_instance_Id"
                                               value="${applicationDetails.workflow_instance_Id}">
                                        <%
                                            List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                            for (int i = 0; i < ownerList1.size(); i++) {
                                        %>
                                        <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                        <%}%>
                                        <input type="hidden" name="taskInstanceId"
                                               value="${applicationDetails.taskInstanceId}">
                                    </div>
                                </div>
                            </form>
                        </div>

                        <% } else if (request.getAttribute("taskId").equals("3") || request.getAttribute("taskId").equals("4")){%>
                        <div class="tab-pane" id="structuralcontent">
                            <div class="form-group col-lg-12">
                                <% TasklistDto checkStage = (TasklistDto) request.getAttribute("checkHold"); %>
                                <% if(request.getAttribute("taskId").equals("3")){%>
                                <button type="button" style="padding: 9px; background-color: #00aa88; color: #ffffff" id="Approvalid"
                                        onclick="CheckForward('Approval')">Electrical Verification
                                </button>
                                <%} else {%>
                                <button type="button" style="padding: 9px; background-color: #00aa88; color: #ffffff" id="Approvalid"
                                        onclick="CheckForward('Approval')">Structural Verification
                                </button>
                                <%}%>
                                <button type="button" style="padding: 9px;background-color: #00aa88; color: #ffffff" id="forwarId"
                                        onclick="CheckForward('Forward')">Forward to DES(if Applicable)
                                </button>
                            </div>
                            <div class="form-group col-lg-12 verifier" id="dzongkhagVerify">
                                <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                    <div class="verifier-tab">
                                        <div class="form-group col-lg-12">
                                            <label class="col-sm-2">Comment (If any:)</label>
                                            <div class="col-sm-4">
                                                <textarea class="form-control bg-light border-secondary" name="appl_comments" id="dzongkhagVerifyComments"></textarea>
                                            </div>
                                            <br/>
                                            <div class="col-sm-4">
                                                <%if (checkStage.getTask_Stage_Name().equalsIgnoreCase("Claimed") || checkStage.getTask_Stage_Name().equalsIgnoreCase("Unhold")){%>
                                                <a href="#" onclick="actionType('VERIFIER','architectVerifier')"
                                                   class="btn btn-primary">Approve</a>
                                                <a href="#" onclick="actionType('Hold','architectVerifier');"
                                                   class="btn btn-warning"><i class="fa fa-pause"></i> Hold
                                                </a>
                                                <a href="#" onclick="actionType('Reject','architectVerifier');"
                                                   class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                                </a>
                                                <%} else if (checkStage.getTask_Stage_Name().equalsIgnoreCase("Hold")){%>
                                                <a href="#" onclick="verfiyApplcication('UnHold','UNHOLD');"
                                                   id="unhold_btn" class="btn btn-info"><i class="fa fa-folder-open"></i> UnHold
                                                </a>
                                                <%--<button class="btn btn-danger" disabled="true"
                                                        onclick="verfiyApplcication('FORWARD_DRAWING', 'FORWARD DRAWING')">Submit</button>
                                                <button class="btn btn-warning" disabled="true" onclick="actionType('Hold','architectVerifier');"><i class="fa fa-pause"></i> Hold
                                                </button>
                                                <button disabled="true" onclick="actionType('Reject','architectVerifier');"
                                                        class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                                </button>--%>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="reject-tab" style="display: none;">
                                        <label class="col-lg-12">Reason :</label>
                                        <div class="col-lg-4">
                                            <select name="reason_id" id="reason_id" class="form-control bg-light border-secondary">
                                                <option value="0">Select</option>
                                                <c:forEach var="list" items="${reasonList}">
                                                    <option value="${list.dropdownId}">${list.dropdownName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <br>
                                        <label class="col-lg-12">Reason Description:</label>
                                        <div class="col-lg-4">
                                            <textarea name="appl_comments" class="form-control bg-light border-secondary"></textarea>
                                        </div>
                                        <br/>
                                        <div id="confirmButtonDiv1" class="col-lg-12" style="display: none;">
                                            <a class="btn btn-warning" onclick="confirmAction('hold')">Hold</a>
                                            <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                        </div>
                                        <div id="confirmButtonDiv3" class="col-lg-12" style="display: none;">
                                            <a class="btn btn-info" onclick="confirmAction('reject')">Reject</a>
                                            <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                        </div>
                                        <br/>
                                    </div>
                                    <div class="form-group col-lg-12">
                                        <div class="col-sm-4 " id="buttonsId">
                                            <input type="hidden" name="taskId" value="${taskId}">
                                            <input type="hidden" name="isApproved" value="N" id="isApproved">
                                            <input type="hidden" name="application_Id" value="${applicationNo}">
                                            <input type="hidden" name="workflow_instance_Id" value="${applicationDetails.workflow_instance_Id}">
                                            <input type="hidden" name="taskInstanceId" value="${applicationDetails.taskInstanceId}">
                                            <%
                                                List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                                for (int i = 0; i < ownerList1.size(); i++) {
                                            %>
                                            <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                            <%}%>
                                            <input type="hidden" name="is_Architect_Forwarded" value="${applicationDetails.is_Architect_Forwarded}">
                                            <input type="hidden" name="is_Structural_Forwarded" value="${applicationDetails.is_Structural_Forwarded}">
                                            <input type="hidden" name="is_Electrical_Forwarded" value="${applicationDetails.is_Electrical_Forwarded}">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="form-group col-lg-12 float-left" id="forwadId" style="display:none">
                                <form action="#" id="DzongkhagDESforwardForm" enctype="multipart/form-data">
                                    <div class="form-group  col-lg-12 float-left forward-des-field">
                                        <br>
                                        <div class="col-lg-6 float-left">
                                            <%if(user_Role.equalsIgnoreCase("DZO_EE")){%>
                                            Drawing to be Forwarded: &nbsp;&nbsp;&nbsp;<input type="checkbox" name="is_Electrical_Forwarded" value="Y" checked /> Electrical Drawing<br/>
                                            <%} else {%>
                                            Drawing to be Forwarded: &nbsp;&nbsp;&nbsp;<input type="checkbox" name="is_Structural_Forwarded" value="Y" checked  />Structural Drawing <br/>
                                            <%}%>
                                        </div>
                                        <br>
                                        <br>
                                        <label class="control-label col-lg-2 float-left">Any Forwarding letter?</label>
                                        <input type="radio" value="Y" id="yes" name="Yes_No" onclick="checkforwaringLetter('Y')"> <label for="yes"> Yes</label>
                                        <input type="radio" value="N" id="no" name="Yes_No" onclick="checkforwaringLetter('N')"> <label for="no">No</label>
                                        <br>
                                        <div class="control-label col-lg-2 float-left" id="forward_letter" >
                                            <br>
                                        </div>
                                        <script>
                                            function checkforwaringLetter(dataType){
                                                if (dataType=="Y"){
                                                    $('#forward_letter').show();
                                                    $("#forward_letter").html('<input type="file" name="appUploadFile">');
                                                }
                                                else{
                                                    $('#forward_letter').html('');
                                                    $('#forward_letter').hide();
                                                }
                                            }
                                        </script>
                                        <br> <br>
                                        <label class="col-lg-12">Comment (If any:)</label>
                                        <div class="col-sm-4">
                                            <textarea class="form-control bg-light border-secondary" name="appl_comments" id="comments"></textarea>
                                        </div>
                                        <br/>
                                        <div class="col-lg-12" id="actionButtonDiv">
                                            <button type="button" onclick="verfiyApplcication('DZO_TO_DES','FORWARD DRAWING')" class="btn btn-success"><i class="fa fa-forward"></i> Forward to DES
                                            </button>
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-12">
                                        <div class="col-sm-4 " id="buttonsId">
                                            <input type="hidden" name="taskId" value="${taskId}">
                                            <input type="hidden" name="isApproved" value="N" id="isApproved">
                                            <input type="hidden" name="application_Id" value="${applicationNo}">
                                            <input type="hidden" name="workflow_instance_Id"
                                                   value="${applicationDetails.workflow_instance_Id}">
                                            <input type="hidden" name="taskInstanceId"
                                                   value="${applicationDetails.taskInstanceId}">
                                            <%
                                                List<OwnerEntity> ownerList2 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                                for (int i = 0; i < ownerList2.size(); i++) {
                                            %>
                                            <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                            <%}%>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <% } else if (request.getAttribute("taskId").equals("20")){%>
                        <div class="tab-pane" id="structuralcontent">
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                <%--<div class="form-group col-lg-12 verifier">--%>
                                    <label class="control-label col-lg-2 float-left">Attach Forwarding letter:</label>
                                    <label class="control-label col-lg-2 float-left"><input type="file" name="appUploadFile"></label>
                                    <br>
                                    <div class="col-sm-4">
                                        <label>Comments(if any):</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <textarea class="form-control bg-light border-secondary" name="appl_comments" id="comments"></textarea>
                                    </div>
                                    <br/>
                                    <div class="col-sm-4" id="actionButtonDiv">
                                        <a href="#" onclick="verfiyApplcication('SUBMIT','SUBMITAPPLICATION')"
                                           class="btn btn-primary">Submit Notesheet</a>
                                    </div>
                                <%--</div>--%>
                                <div class="form-group col-lg-12">
                                    <div class="col-sm-4 " id="buttonsId">
                                        <input type="hidden" name="taskId" value="${taskId}">
                                        <input type="hidden" name="isApproved" value="N" id="isApproved">
                                        <input type="hidden" name="application_Id" value="${applicationNo}">
                                        <input type="hidden" name="workflow_instance_Id"
                                               value="${applicationDetails.workflow_instance_Id}">
                                        <input type="hidden" name="taskInstanceId"
                                               value="${applicationDetails.taskInstanceId}">
                                        <%
                                            List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                            for (int i = 0; i < ownerList1.size(); i++) {
                                        %>
                                        <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                        <%}%>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <% } else if (request.getAttribute("taskId").equals("21")){%>
                        <div class="tab-pane" id="structuralcontent">
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                <div class="form-group col-lg-12 verifier">
                                    <div class="form-group  col-lg-12 float-left" id="showHideDocument">
                                        <label class="control-label col-lg-2 float-left"> Attached Documents :</label>
                                        <div class="container">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>Sl.No</th>
                                                        <th>File Type</th>
                                                        <th>File Name</th>
                                                        <th colspan="2">Action</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="doc" items="${documents}" varStatus="counter">
                                                        <tr>
                                                            <td>${counter.index + 1}</td>
                                                            <td>${doc.document_Type}</td>
                                                            <td>${doc.document_Name}</td>
                                                            <td><button class="btn btn-outline-warning" type="button" onclick="viewAttachment('${doc.document_id}','download')"
                                                                        target="_blank"><i class="fa fa-download"></i> Download
                                                            </button></td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <br/><br/>
                                    <div class="col-sm-4" id="actionButtonDiv">
                                        <a href="#" onclick="verfiyApplcication('CLOSE','CLOSEAPPLICATION')"
                                           class="btn btn-primary">Close Application</a>
                                    </div>
                                </div>
                                <div class="form-group col-lg-12">
                                    <div class="col-sm-4 " id="buttonsId">
                                        <input type="hidden" name="taskId" value="${taskId}">
                                        <input type="hidden" name="isApproved" value="N" id="isApproved">
                                        <input type="hidden" name="application_Id" value="${applicationNo}">
                                        <input type="hidden" name="workflow_instance_Id"
                                               value="${applicationDetails.workflow_instance_Id}">
                                        <input type="hidden" name="taskInstanceId"
                                               value="${applicationDetails.taskInstanceId}">
                                        <%
                                            List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                            for (int i = 0; i < ownerList1.size(); i++) {
                                        %>
                                        <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                        <%}%>
                                        <input type="hidden" name="is_Architect_Forwarded" value="${applicationDetails.is_Architect_Forwarded}">
                                        <input type="hidden" name="is_Structural_Forwarded" value="${applicationDetails.is_Structural_Forwarded}">
                                        <input type="hidden" name="is_Electrical_Forwarded" value="${applicationDetails.is_Electrical_Forwarded}">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <%}else if (request.getAttribute("taskId").equals("22") || request.getAttribute("taskId").equals("23") || request.getAttribute("taskId").equals("5")){%>
                        <div class="tab-pane" id="structuralcontent">
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                <div class="col-sm-4">
                                    <label>Comments(if any):</label>
                                </div>
                                <div class="col-sm-4">
                                    <textarea class="form-control bg-light border-secondary" name="appl_comments" id="comments"></textarea>
                                </div>
                                <div class="col-sm-4" id="actionButtonDiv">
                                    <br/>
                                    <a href="#" onclick="verfiyApplcication('CLOSE','CLOSEAPPLICATION')"
                                       class="btn btn-primary">Close Application</a>
                                </div>
                                <input type="hidden" name="taskId" value="${taskId}">
                                <input type="hidden" name="isApproved" value="N" id="isApproved">
                                <input type="hidden" name="application_Id" value="${applicationNo}">
                                <input type="hidden" name="workflow_instance_Id"value="${applicationDetails.workflow_instance_Id}">
                                <input type="hidden" name="taskInstanceId" value="${applicationDetails.taskInstanceId}">
                                <%
                                    List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                    for (int i = 0; i < ownerList1.size(); i++) {
                                %>
                                <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                <%}%>
                            </form>
                        </div>
                        <%} else if (request.getAttribute("taskId").equals("18") || request.getAttribute("taskId").equals("19")){%>
                        <div class="tab-pane" id="structuralcontent">
                            <% TasklistDto checkStatus = (TasklistDto) request.getAttribute("checkHold"); %>
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">
                                <div class="verifier-tab">
                                    <div class="form-group  col-lg-12 float-left" id="showHideDocument5">
                                        <label class="control-label col-lg-2 float-left"> Attached Documents :</label>
                                        <div class="container">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>Sl.No</th>
                                                        <th>File Type</th>
                                                        <th>File Name</th>
                                                        <th colspan="2">Action</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="doc" items="${documents}" varStatus="counter">
                                                        <tr>
                                                            <td>${counter.index + 1}</td>
                                                            <td>${doc.document_Type}</td>
                                                            <td>${doc.document_Name}</td>
                                                            <td><button class="btn btn-outline-warning" type="button" onclick="viewAttachment('${doc.document_id}','download')"
                                                                        target="_blank"><i class="fa fa-download"></i> Download
                                                            </button></td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="col-sm-4">
                                        <label>Comments(if any):</label>
                                    </div>
                                    <div class="col-sm-12">
                                        <textarea class="form-control bg-light border-secondary" name="appl_comments" id="comments"></textarea>
                                    </div>
                                    <br/>
                                    <div class="col-sm-4">

                                        <%if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Claimed") || checkStatus.getTask_Stage_Name().equalsIgnoreCase("Unhold")){%>
                                        <a href="#" onclick="actionType('VERIFIER','architectVerifier')"
                                           class="btn btn-primary">Approve</a>
                                        <a href="#" onclick="actionType('Hold','architectVerifier');"
                                           class="btn btn-warning"><i class="fa fa-pause"></i> Hold
                                        </a>
                                        <a href="#" onclick="actionType('Reject','architectVerifier');"
                                           class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                        </a>
                                        <%} else if (checkStatus.getTask_Stage_Name().equalsIgnoreCase("Hold")){%>
                                        <a href="#" onclick="verfiyApplcication('UnHold','UNHOLD');"
                                           id="unhold_btn" class="btn btn-info"><i class="fa fa-folder-open"></i> UnHold
                                        </a>
                                        <%--<button class="btn btn-danger" disabled="true"
                                                onclick="verfiyApplcication('FORWARD_DRAWING', 'FORWARD DRAWING')">Submit</button>
                                        <button class="btn btn-warning" disabled="true" onclick="actionType('Hold','architectVerifier');"><i class="fa fa-pause"></i> Hold
                                        </button>
                                        <button disabled="true" onclick="actionType('Reject','architectVerifier');"
                                                class="btn btn-danger"><i class="fa fa-ban"></i> Reject
                                        </button>--%>
                                        <%}%>
                                    </div>
                                </div>
                                <div id="reject-tab" style="display: none;">
                                    <label class="col-lg-12">Reason :</label>

                                    <div class="col-lg-4">
                                        <select name="reason_id" id="reason_id" class="form-control bg-light border-secondary">
                                            <option value="0">Select</option>
                                            <c:forEach var="list" items="${reasonList}">
                                                <option value="${list.dropdownId}">${list.dropdownName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <br>
                                    <label class="col-lg-12">Reason Description:</label>
                                    <div class="col-lg-4">
                                        <textarea name="appl_comments" class="form-control bg-light border-secondary"></textarea>
                                    </div>
                                    <br/>
                                    <div id="confirmButtonDiv1" class="col-lg-12" style="display: none;">
                                        <a class="btn btn-warning" onclick="confirmAction('hold')">Hold</a>
                                        <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                    </div>
                                    <div id="confirmButtonDiv3" class="col-lg-12" style="display: none;">
                                        <a class="btn btn-info" onclick="confirmAction('reject')">Reject</a>
                                        <a class="btn btn-danger" onclick="confirmAction('cancel')">Cancel</a>
                                    </div>
                                    <br/>
                                </div>
                                <div class="form-group col-lg-12">
                                    <div class="col-sm-4 " id="buttonsId">
                                        <input type="hidden" name="taskId" value="${taskId}">
                                        <input type="hidden" name="isApproved" value="N" id="isApproved">
                                        <input type="hidden" name="application_Id" value="${applicationNo}">
                                        <input type="hidden" name="workflow_instance_Id"
                                               value="${applicationDetails.workflow_instance_Id}">
                                        <input type="hidden" name="taskInstanceId"
                                               value="${applicationDetails.taskInstanceId}">
                                        <%
                                            List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                            for (int i = 0; i < ownerList1.size(); i++) {
                                        %>
                                        <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                        <%}%>
                                        <input type="hidden" name="is_Architect_Forwarded" value="${applicationDetails.is_Architect_Forwarded}">
                                        <input type="hidden" name="is_Structural_Forwarded" value="${applicationDetails.is_Structural_Forwarded}">
                                        <input type="hidden" name="is_Electrical_Forwarded" value="${applicationDetails.is_Electrical_Forwarded}">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <%} else if (request.getAttribute("taskId").equals("25") || request.getAttribute("taskId").equals("24")){%>
                        <div class="tab-pane" id="structuralcontent">
                            <form action="#" id="architectVerifierForm" enctype="multipart/form-data">

                                    <label class="control-label col-lg-2 float-left">Attach Forwarding letter:</label>
                                    <label class="control-label col-lg-2 float-left"><input type="file" name="appUploadFile"></label><br>
                                <div class="form-group col-lg-12 verifier">
                                    <div class="col-sm-4">
                                        <label>Comments(if any):</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <textarea class="form-control bg-light border-secondary" name="appl_comments" id="comments"></textarea>
                                    </div>
                                    <br/>
                                    <div class="col-sm-4" id="actionButtonDiv">
                                        <a href="#" onclick="verfiyApplcication('SUBMIT','SUBMITAPPLICATION')"
                                           class="btn btn-primary">Submit Notesheet</a>
                                    </div>
                                </div>
                                <div class="form-group col-lg-12">
                                    <div class="col-sm-4 " id="buttonsId">
                                        <input type="hidden" name="taskId" value="${taskId}">
                                        <input type="hidden" name="isApproved" value="N" id="isApproved">
                                        <input type="hidden" name="application_Id" value="${applicationNo}">
                                        <input type="hidden" name="workflow_instance_Id"
                                               value="${applicationDetails.workflow_instance_Id}">
                                        <input type="hidden" name="taskInstanceId"
                                               value="${applicationDetails.taskInstanceId}">
                                        <input type="hidden" name="is_Architect_Forwarded" value="${applicationDetails.is_Architect_Forwarded}">
                                        <input type="hidden" name="is_Structural_Forwarded" value="${applicationDetails.is_Structural_Forwarded}">
                                        <input type="hidden" name="is_Electrical_Forwarded" value="${applicationDetails.is_Electrical_Forwarded}">
                                        <%
                                            List<OwnerEntity> ownerList1 = (List<OwnerEntity>) request.getAttribute("ownerList");
                                            for (int i = 0; i < ownerList1.size(); i++) {
                                        %>
                                        <input type="hidden" id="mobileNo<%=i%>" name="ownerDetailsDtos[<%=i%>].ownerContact" value="<%=ownerList.get(i).getOwner_Mobile_No()%>">
                                        <%}%>
                                    </div>
                                </div>
                            </form>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<script>

    var actionButton = "";
    var actionUserType = "";
    function actionType(action, userType) {
        countButtonType = 0;
        actionButton = action;
        actionUserType = userType;
        if (actionButton == "VERIFIER") {
            $("#isApproved").val("Y");
            verfiyApplcication('Y', 'VERIFIER');
        }
        else if (actionButton == "Hold") {
            $("#isApproved").val("Y");
            $('#reject-tab').show();
            $(".verifier-tab").hide();
            $("#confirmButtonDiv1").show();
            $("#confirmButtonDiv3").hide()
            $("#actionButtonDiv").hide();
            $("#confirmButtonDiv").show();
            $
        }
        else if (actionButton == "Reject") {
            $("#isApproved").val("Y");
            $('#reject-tab').show();
            $(".verifier-tab").hide();
            $("#confirmButtonDiv3").show();
            $("#confirmButtonDiv1").hide();
            $("#actionButtonDiv").hide();
            $("#confirmButtonDiv").show();
        }
        else {
            $('.verifier-tab').hide();
            showRejectApplicationReason(action);
            if (action == 'FORWARD') {

                $("#forwardTo").show();
                $('#reject-tab').show();
                $(".verifier-tab").hide();
                $("#actionButtonDiv2").show()
            }
            else {
                $("#forwardTo").hide();
            }
        }
    }
    function confirmAction(actionType) {
        if (actionType == "hold") {
            $("#isApproved").val("Y");
            verfiyApplcication('Y', 'hold');
        }
        else if (actionType == 'cancel') {
            $('#reject-tab').hide();
            $(".verifier-tab").show();
        }
        else if (actionType == 'reject') {

            $("#isApproved").val("Y");
            verfiyApplcication('Y', 'reject');
        }
        else {
            if (actionType == 'architectHold') {
                verfiyApplcication('Y', 'architectHold');
            }
            else if (actionType == 'FORWARD') {
                verfiyApplcication('Y', 'FORWARD');
            }
            else {
                verfiyApplcication('Y', 'Reject');
            }
        }
    }

</script>
<script>
    var countButtonType = 0;
    function showRejectApplicationReason(buttonType) {
        if (countButtonType == 0) {
            $("#rejectReasonDiv").show();
            $('#reject-tab').show();
        }
        else {
            var rs = $("#rejectReasonId").val();
            var ds = $("#rejectRemarks").val();
            if (rs != 0 || ds != "") {
                var url = '${pageContext.request.contextPath}/building/rejectApplication?type=' + buttonType;
                var options = {
                    target: '#loadContainer',
                    url: url,
                    type: 'POST',
                    enctype: 'multipart/form-data',
                    data: $('#architectVerifierForm').serialize()
                };
                $("#architectVerifierForm").ajaxSubmit(options);
            }
            else {
                alert("Reject reason is empty");
            }
        }
        countButtonType = 1;
    }
    function verfiyApplcication(verifierType, actionType) {
        $("#isApproved").val(actionType);
        if (actionType == 'VERIFIER') {

            if (confirm("Are you sure you want to Approve this drawing?") == true)
            {
                var url = '${pageContext.request.contextPath}/building/architectVerification?type=save_approval_details&verifierType=' + verifierType;
                var options = {
                    target: '#loadContainer',
                    url: url,
                    type: 'POST',
                    enctype: 'multipart/form-data',
                    data: $('#architectVerifierForm').serialize()
                };
                $("#architectVerifierForm").ajaxSubmit(options);
            }
        } else  if (actionType == 'SUBMITAPPLICATION') {

            if (confirm("Are you sure you want to Submit this Application?") == true)
            {
                var url = '${pageContext.request.contextPath}/building/architectVerification?type=save_approval_details&verifierType=' + verifierType;
                var options = {
                    target: '#loadContainer',
                    url: url,
                    type: 'POST',
                    enctype: 'multipart/form-data',
                    data: $('#architectVerifierForm').serialize()
                };
                $("#architectVerifierForm").ajaxSubmit(options);
            }
        }
        else  if (actionType == 'CLOSEAPPLICATION') {

            if (confirm("Are you sure you want to Close this Application?") == true){
                var url = '${pageContext.request.contextPath}/building/architectVerification?type=save_approval_details&verifierType=' + verifierType;
                var options = {
                    target: '#loadContainer',
                    url: url,
                    type: 'POST',
                    enctype: 'multipart/form-data',
                    data: $('#architectVerifierForm').serialize()
                };
                $("#architectVerifierForm").ajaxSubmit(options);
            }
        }
        else if (actionType == 'FORWARD') {
            if (verifierType == "ELECTRICAL_FORWARD") {
                if (confirm("Are you sure you want to forward this drawing to Electrical Section?") == true) {

                    var url = '${pageContext.request.contextPath}/building/architectVerification?type=save_approval_details&verifierType=' + verifierType;
                    var options = {
                        target: '#loadContainer',
                        url: url,
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: $('#architectVerifierForm').serialize()
                    };
                    $("#architectVerifierForm").ajaxSubmit(options);

                    $('#electricalId').hide();
                    $('#electricalForward').show();
                } else {
                    $('#electricalId').show();
                    $('#electricalForward').hide();
                    return false;
                }
            } else {
                if (confirm("Are you sure you want to forward this drawing to Structural Section?") == true) {
                    var url = '${pageContext.request.contextPath}/building/architectVerification?type=save_approval_details&verifierType=' + verifierType;
                    var options = {
                        target: '#loadContainer',
                        url: url,
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: $('#architectVerifierForm').serialize()
                    };
                    $("#architectVerifierForm").ajaxSubmit(options);
                    $('#structuralId').hide();
                    $('#structuralForward').show();
                } else {
                    $('#structuralId').show();
                    $('#structuralForward').hide();
                    return false;
                }
            }
        }
        else if (actionType == 'DZONGKHAG TO DES') {
            var url = '${pageContext.request.contextPath}/building/rejectApplication?type='+verifierType;
            var options = {
                target: '#loadContainer',
                url: url,
                type: 'POST',
                enctype: 'multipart/form-data',
                data: $('#fowardForm').serialize()
            };
            $("#fowardForm").ajaxSubmit(options);
        }
        else if (actionType == 'FORWARD DRAWING') {

            if(verifierType =='DZO_TO_DES'){
                if (confirm("Are you sure you want to Forward to DES?") == true){
                    var url = '${pageContext.request.contextPath}/building/rejectApplication?type='+verifierType;
                    var options = {
                        target: '#loadContainer',
                        url: url,
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: $('#DzongkhagDESforwardForm').serialize()
                    };
                    $("#DzongkhagDESforwardForm").ajaxSubmit(options);
                }
            }
            else if(verifierType =='FORWARD_DRAWING'){
                if (confirm("Are you sure you want to Submit this drawing?") == true){
                    var url = '${pageContext.request.contextPath}/building/rejectApplication?type='+verifierType;
                    var options = {
                        target: '#loadContainer',
                        url: url,
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: $('#architectVerifierForm').serialize()
                    };
                    $("#architectVerifierForm").ajaxSubmit(options);
                }
            }
            else if(verifierType =='FORWARD_DRAWING_TO_DES'){


                if (confirm("Are you sure you want to Submit this drawing?") == true){
                    var url = '${pageContext.request.contextPath}/building/rejectApplication?type=FORWARD_DRAWING_TO_DES';
                    var options = {
                        target: '#loadContainer',
                        url: url,
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: $('#architectVerifierForm').serialize()
                    };
                    $("#architectVerifierForm").ajaxSubmit(options);
                }
            }
            else{
                var url = '${pageContext.request.contextPath}/building/rejectApplication?type=HOLD';
                var options = {
                    target: '#loadContainer',
                    url: url,
                    type: 'POST',
                    enctype: 'multipart/form-data',
                    data: $('#fowardForm').serialize()
                };
                $("#fowardForm").ajaxSubmit(options);
            }
        }
        else if (actionType == 'architectHold') {
            var url = '${pageContext.request.contextPath}/building/rejectApplication?type=HOLD';
            var options = {
                target: '#loadContainer',
                url: url,
                type: 'POST',
                enctype: 'multipart/form-data',
                data: $('#fowardForm').serialize()
            };
            $("#fowardForm").ajaxSubmit(options);
        }
        else if (actionType == "hold") {
            if (confirm("Are you sure you want to Hold this drawing?") == true) {
            var url = '${pageContext.request.contextPath}/building/rejectApplication?type=HOLD';
            var options = {
                target: '#loadContainer',
                url: url,
                type: 'POST',
                enctype: 'multipart/form-data',
                data: $('#architectVerifierForm').serialize()
            };
            $('#architectVerifierForm').ajaxSubmit(options);

            }
        }
        else if (actionType == 'reject') {
            if (confirm("Are you sure you want to Reject this drawing?") == true) {
                var url = '${pageContext.request.contextPath}/building/rejectApplication?type=REJECT';
                var options = {
                    target: '#loadContainer',
                    url: url,
                    type: 'POST',
                    enctype: 'multipart/form-data',
                    data: $('#architectVerifierForm').serialize()
                };
                $('#architectVerifierForm').ajaxSubmit(options);
            }
        }
        else if (actionType == 'UNHOLD') {
            var url = '${pageContext.request.contextPath}/building/rejectApplication?type=UNHOLD';
            var options = {
                target: '#loadContainer',
                url: url,
                type: 'POST',
                enctype: 'multipart/form-data',
                data: $('#architectVerifierForm').serialize()
            };
            $('#architectVerifierForm').ajaxSubmit(options);
        }

        else if (actionType == 'Reject') {
            if (confirm("Are you sure you want to Reject this drawing?") == true) {
                var url = '${pageContext.request.contextPath}/building/rejectApplication?type=REJECT';
                var options = {
                    target: '#loadContainer',
                    url: url,
                    type: 'POST',
                    enctype: 'multipart/form-data',
                    data: $('#architectVerifierForm').serialize()
                };
                $('#architectVerifierForm').ajaxSubmit(options);
            }
        }
    }

</script>
<script type="text/javascript">
    function viewDetails(view){
        if(view=='p'){
            $('#personalcontent').show();
            $('#locationcontent').hide();
            $('#notesheetDetails').hide();
            $('#btnId').show();
        }
        else if(view=='d'){
            $('#locationcontent').show();

        }
        else if(view=='aA'){
            $('#locationcontent').show();
            $('#personalcontent').hide();
        }
        else if(view=='s'){
            $('#personalcontent').hide();

        }
        else if(view=='e'){

        }
        else if(view=='n'){
            $('#notesheetDetails').show();
            $('#personalcontent').hide();

        }
        else if(view=='py'){

        }
        else{

        }
    }

    function next(currentTab, nextTab) {
        if(nextTab=='location'){
            $('#locationcontent').show();
            $('#personalcontent').hide();
            $('#desOperator').show();
            $('#forwardWithiDES').show();
            $('#ApprovalDiv').show();
        }
        else if(nextTab=='structural'){

            $('#structId').hide();
        }
        $('#' + nextTab + 'content').prop('class', 'tab-pane active');
        $('#' + currentTab + 'Detials').removeClass("active");
        $('#' + currentTab + 'content').removeClass("active");
        $('#' + currentTab + 'h').css("color", "white");
        $('#' + currentTab + 'check').html('<i class="fa fa-check text-white"></i>');
        $("#" + currentTab + "h").css("background-color", "#120f65");
        $("#" + nextTab + "h").css("background-color", "rgb(18, 18, 19)!important");
        $("#" + nextTab + "h").css("color", "white");
    }
    function pretab(val) {
        if (val == "personal") {
            $('#personal_tab').prop('class', 'tab-pane active');
            $('#locationhead').removeClass("active");
            $('#locationcontent').removeClass("active");
            $("#personalh").css("background-color", "rgb(18, 18, 19)!important");
            $("#locationh").css("background-color", "#120f65");
        }
    }

    function CheckApproval(val) {
        if (val == "Approval") {

            $('#ApprovalDiv').show();
            $('#forwadId').hide();
            $("#forwardTo").hide();
            $(".verifier-tab").show();
            $('#reject-tab').hide();
            $("#confirmButtonDiv1").hide();
            $("#confirmButtonDiv3").hide();
            $("#actionButtonDiv").show();

        }
        else {
            $('#forwadId').show();
            $("#forwardTo").show();
            $(".verifier-tab").hide();
            $("#confirmButtonDiv2").show();
            $('#ApprovalDiv').hide();
            $("#confirmButtonDiv1").hide();
            $("#confirmButtonDiv3").hide();
            $("#actionButtonDiv").show();
        }
    }

    function  CheckForward(val){
        if (val=="Approval"){
            $('#dzongkhagVerify').show();
            $('#forwadId').hide();
            $('#actionButtonDiv').hide();
        }
        else{
            $('#dzongkhagVerify').hide();
            $('#forwadId').show();
            $('#actionButtonDiv').show();
        }
    }
    function viewAttachment(uuid) {
        //var url= '${pageContext.request.contextPath}/FileDownloadServlet?uuid='+uuid+'&type='+type;
        var url = '${pageContext.request.contextPath}/building/loadpagetoemptylayout/donwloadFiles?uuid=' + uuid;
        window.open(url, '_blank');
    }
    $(document).on("keypress", "input[type=number]", function(e){
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
    });
</script>
</body>
</html>
