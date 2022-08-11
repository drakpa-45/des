<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bt.gov.ditt.does.Dto.UserRolePrivilegeDES" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession sess = request.getSession();
    UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords" content="Motive Mag Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design">
<body>
<% if (userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("1")){%>
<div class="row">
                <span style="font-size: 20px;color:#064CA7">&nbsp;&nbsp;&nbsp;<b>Dzongkhag</b>
                    <% if(userdet.getCurrentRole().getRoleName().equalsIgnoreCase("DZO_OPERATOR")){%>
                    <small class="text-secondary">
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        <label style="color: #003300">Application Details</label>
                    </small>
                    <%}%>
                </span>


</div>
<%}%>
    <div class="card ">
        <form name="ConstructionForm" id="buildingConsFormId" method="post" action="<c:url value="/building/saveApplication" />" class="form-horizontal" enctype="multipart/form-data" novalidate="novalidate">
            <div><input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="0d02d378a1ca3ea40df0955edb1faf32"></div>
            <input type="hidden" name="buildingCategory" value="P" id="buildingCategoryId">
            <div class="content-wrapper">
                <div class="container">
                    <div class="row" id="csdSection">
                            <div class="form-group">
                                <label class="control-label col-lg-2"><input type="hidden" id="isRejectCSDPrv" value="N"></label>
                            </div>
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="panel-body">
                                        <div class="form-group col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left">Building Storied<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <select name="projectTitleId" class="form-control bg-light border-secondary" id="storied">
                                                    <option value="" style="display: none">Select</option>
                                                    <c:forEach var="list" items="${projectTitle}">
                                                        <option value="${list.dropdownId}">${list.dropdownName}</option>
                                                    </c:forEach>
                                                </select>
                                                <div class="text-danger validation-error" style="display:none"  id="storied_validation">This field is mandatory</div>
                                            </div>
                                            <label class="control-label col-lg-2 float-left">Building Use<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <select class="form-control bg-light border-secondary" name="building_Use_Id" id="building_Use_Id">
                                                    <option value="" style="display: none">Select Building Use</option>
                                                    <c:forEach var="list" items="${buildingUse}">
                                                        <option value="${list.dropdownId}">${list.dropdownName}</option>
                                                    </c:forEach>
                                                </select>
                                                <div class="text-danger validation-error" style="display:none"  id="building_Use_Id_validation">This field is mandatory</div>
                                            </div>
                                        </div>

                                        <div class="form-group  col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left">Number of Floors<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="number" name="number_of_Floor" id="number_of_Floor" class="form-control bg-light border-secondary" pattern="/^-?\d+\.?\d*$/" onKeyPress="if(this.value.length==4) return false;" />
                                                <div class="text-danger validation-error" style="display:none"  id="number_of_Floor_validation">This field is mandatory</div>
                                            </div>
                                        </div>

                                        <div class="form-group col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left">Building Category<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="radio" id="private" name="buildingCategory" onclick="bulidingCategory('P');">&nbsp;&nbsp;Private&nbsp;&nbsp;
                                                <input type="radio" id="institute" name="buildingCategory" onclick="bulidingCategory('I');" >&nbsp;&nbsp;Government&nbsp;&nbsp;
                                                <input type="radio" id="othersId" name="buildingCategory" onclick="bulidingCategory('O');" >&nbsp;&nbsp;Others&nbsp;&nbsp;
                                                <div class="text-danger validation-error" style="display:none"  id="private_validation">This field is mandatory</div>


                                            </div>
                                        </div>
                                        <div class="form-group  col-lg-12 float-left categoryTypeClass" id="instituteDiv" style="display: none;">
                                            <label class="control-label col-lg-2 float-left">Agency<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="text" name="institutionName" maxlength="50" value="" id="institutionNameId" class="form-control bg-light border-secondary">
                                            </div>
                                        </div>
                                        <div class="form-group  col-lg-12 float-left categoryTypeClass" id="otherIdDiv" style="display: none;">
                                            <label class="control-label col-lg-2 float-left">Details<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left"><textarea name="detailsArea" id="detailsArea" class="form-control bg-light border-secondary"></textarea></div>
                                        </div>

                                        <div class="form-group  col-lg-12 float-left categoryTypeClass" id="privateDiv" style="display: none;" >
                                            <label class="control-label col-lg-2 float-left">Ownership Details<font color="red">*</font>:</label>
                                            <div class="col-lg-10 float-left">
                                                <div class="table-responsive">
                                                    <table id="ownerTable" class="table table-striped table-bordered table-hover">
                                                        <% int j = 1;%>
                                                        <tr>
                                                            <th style="text-align: center;">CID <font color="red">*</font></th>
                                                            <th style="text-align: center;" readonly id="fullNameId1">Full Name <font color="red">*</font></th>
                                                            <th style="text-align: center;">Contact No</th>
                                                        </tr>
                                                        <tr id="my_<%=j%>">
                                                            <td><input type="number" name="ownerList[0].owner_CID_No" onchange="checkCID(this.value, this.id)" id="ownerCIDId_0" class="form-control bg-light border-secondary" onkeypress="return this.value.length < 11;" oninput="if(this.value.length>=11) { this.value = this.value.slice(0,11); }" pattern="[0-9]" required="required"/>
                                                                <div class="text-danger validation-error" style="display:none"  id="ownerCIDId_0_validation">This field is mandatory</div>
                                                            </td>
                                                            <td><input type="text" name="ownerList[0].Owner_Name" maxlength="150" value="" readonly id="ownerNameId_0" class="form-control bg-light border-secondary">
                                                                <div class="text-danger validation-error" style="display:none"  id="ownerNameId_0_validation">This field is mandatory</div>
                                                            </td>
                                                            <td><input type="number" name="ownerList[0].Owner_Mobile_No" id="ownerContId_0" class="form-control bg-light border-secondary" onkeypress="return this.value.length < 8;" oninput="if(this.value.length>=8) { this.value = this.value.slice(0,8); }" />
                                                                <div class="text-danger validation-error" style="display:none"  id="ownerContId_0_validation">This field is mandatory</div>
                                                            </td>
                                                        </tr>
                                                        <% j++;%>
                                                    </table>
                                                    <div class="col-md-4">
                                                        <input type="button" value="Add Owner" class="btn btn-primary" onclick="addRow('ownerTable')">
                                                        <input type="button" class="btn btn-danger" value="Delete Owner" onclick="deleteRow( <% j++;%>)">
                                                        <span id="ownerDeleteErrorMsg" style="display:none"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <script>
                                            rowCount = 1;
                                            function addRow(tableId) {
                                                var tr = ' <tr id="owner_row_'+rowCount+'">'+
                                                        '<td><input type="number" name="ownerList['+rowCount+'].Owner_CID_No"  value="" onchange="checkCID(this.value, this.id)" id="ownerCIDId_'+rowCount+'" class="form-control"></td>'+
                                                        '<td><input type="text" name="ownerList['+rowCount+'].Owner_Name" maxlength="150" value=""  id="ownerNameId_'+rowCount+'" class="form-control"></td>'+
                                                        '<td><input type="number" name="ownerList['+rowCount+'].Owner_Mobile_No" maxlength="11" value="" id="ownerContId_'+rowCount+'" class="form-control"></td>'+
                                                        '</tr>';
                                                $("#"+tableId).append(tr);
                                                rowCount++;
                                            }
                                            function deleteRow(id) {
                                                $("#owner_row_" + rowCount).remove();
                                                rowCount--;
                                            }
                                        </script>

                                        <div class="form-group  col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left">Thram No.(s)<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="text" name="thram_Nos" id="thram_Nos" maxlength="30" value="" class="form-control bg-light border-secondary">
                                                <span style="font-size: 8pt; font-style: italic;"><i>For more than one Thram, please specify Thram Nos in a comma-separated format (e.g) T1, T2</i></span>
                                                <div class="text-danger validation-error" style="display:none"  id="thram_Nos_validation">This field is mandatory</div>
                                            </div>
                                            <label class="control-label col-lg-2 float-left">Plot No.(s)<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="text" name="plot_Nos" maxlength="30" value="" id="plotNumberId" class="form-control bg-light border-secondary">&nbsp;<span style="font-size: 8pt; font-style: italic;">For more than one Plot, please specify Plot Nos in a comma-separated format (e.g) P1, P2</span>
                                                <div class="text-danger validation-error" style="display:none"  id="plotNumberId_validation">This field is mandatory</div>
                                            </div>
                                        </div>
                                        <div class="form-group  col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left">Dzongkhag<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                        <input type="text" name="dzongkhagName" id="dzongkhagName" readonly class="form-control bg-light border-secondary"
                                                              value="${dzongkhagName}"/>
                                                    <input type="hidden" name="dzonkhag_Id"  class="form-control" value="<%=userdet.getJurisdictions()[0].getLocationID()%>" id="dzonkhag_Id"/>
                                                <div class="text-danger validation-error" style="display:none"  id="dzonkhag_Id_validation">This field is mandatory</div>
                                            </div>
                                            <label class="control-label col-lg-2 float-left">Gewog<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <select name="gewog_Id" onchange="populateDependentDropDown(this.value, 'VILLAGE_LIST', 'village_Id')" id="gewog_Id" class="form-control bg-light border-secondary">
                                                    <option value="" style="display: none">-Select one-</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group  col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left">Village<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <select name="village_Id" id="village_Id" class="form-control bg-light border-secondary">
                                                    <option value="" style="display: none">-Select one-</option>
                                                </select>
                                            </div>
                                            <label class="control-label col-lg-2 float-left">Contact Address<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <textarea name="contact_Address" id="contactDetailsId" class="form-control bg-light border-secondary"></textarea>
                                                <div class="text-danger validation-error" style="display:none"  id="contactDetailsId_validation">This field is mandatory</div>

                                            </div>
                                        </div>
                                        <div class="form-group  col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left" for="contactEmailIdId">Contact Email ID<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="text" maxlength="49" name="contact_Email_Id" value="" id="contactEmailIdId" class="form-control bg-light border-secondary"/>
                                                <div class="text-danger validation-error" style="display:none"  id="contactEmailIdId_validation">This field is mandatory</div>
                                                <span id="emailErrorMsg" class="text-danger"></span>
                                            </div>
                                            <label class="control-label col-lg-2 float-left">Contact Telephone No<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="number" name="contact_Mobile_No" value="" min="0" maxlength="8" id="contactPhoneNoId" class="form-control bg-light border-secondary">
                                                <div class="text-danger validation-error" style="display:none"  id="contactPhoneNoId_validation">This field is mandatory</div>
                                            </div>
                                        </div>
                                        <div class="form-group  col-lg-12  float-left">
                                            <label class="control-label col-lg-2 float-left">Architect Registration No.<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="text" name="architect_Registration_No" min="0" value="" onchange="checkCDBNo(this.value)" id="engineerRegNoId" class="form-control bg-light border-secondary">
                                                <label class="labelNormalError"><span id="regErrorMsg" style="display:none"></span></label>
                                                <div class="text-danger validation-error" style="display:none"  id="engineerRegNoId_validation">This field is mandatory</div>
                                            </div>
                                            <label class="control-label col-lg-2 float-left">Architect Contact No. :</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="number" min="0" name="architect_Contact_No" value="" id="architect_id" class="form-control bg-light border-secondary">
                                            </div>
                                        </div>
                                        <div class="form-group  col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left">Architect Name.<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="text" name="architect_Name" value="" id="engineerNameId" readonly class="form-control bg-light border-secondary">
                                                <div class="text-danger validation-error" style="display:none"  id="architecNametValidator">This field is mandatory</div>
                                            </div>
                                        </div>
                                        <div class="form-group  col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left tooltipCSSSelector" title="" data-original-title="Minimum of 2 Years">Structural Engineer Cid No<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="number" name="structural_ID" min="0" max="11" onchange="checkEID(this.value, this.id)" id="engStruNameId" class="form-control bg-light border-secondary">
                                                <label class="labelNormalError"><span id="anameerror" style="display:none"></span></label>
                                                <div class="text-danger validation-error" style="display:none"  id="engStruNameId_validation">This field is mandatory</div>
                                            </div>
                                            <label class="control-label col-lg-2 float-left tooltipCSSSelector" title="" data-original-title="Minimum of 2 Years">Electrical Engineer Cid No<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="number" name="electrical_ID" max="11" min="0" onchange="rcscCidno(this.value, this.id)" id="engEleNameId" class="form-control bg-light border-secondary">
                                                <div class="text-danger validation-error" style="display:none"  id="engEleNameId_validation">This field is mandatory</div>
                                            </div>

                                        </div>

                                        <div class="form-group  col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left">Structural Engineer Name<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="text" name="structural_Name" value="" id="nameStr_id" readonly class="form-control bg-light border-secondary">
                                                <div class="text-danger validation-error" style="display:none"  id="structualNameValidator">This field is mandatory</div>
                                            </div>
                                             <label class="control-label col-lg-2 float-left">Electrical Engineer Name<font color="red">*</font>:</label>
                                                <div  class="col-lg-4 float-left">
                                                    <input type="text" name="electrical_Name" value="" readonly id="nameEle_id" class="form-control bg-light border-secondary">
                                                    <div class="text-danger validation-error" style="display:none"  id="electricalNameValidator">This field is mandatory</div>
                                                </div>
                                        </div>
                                        <div class="form-group  col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left">Structural Engineer's Year of Graduation
                                                <font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="number" name="structural_Graduation_Year" min="0" value="" id="structural_Graduation_Year" class="form-control bg-light border-secondary">
                                                <div class="text-danger validation-error" style="display:none"  id="structural_Graduation_Year_validation">This field is mandatory</div>
                                            </div>
                                            <label class="control-label col-lg-2 float-left">Electrical Engineer's Year of Graduation<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="number" name="electrical_Graduation_Year" min="0" value="" id="electrical_Graduation_Year" class="form-control bg-light border-secondary">
                                                <div class="text-danger validation-error" style="display:none"  id="electrical_Graduation_Yea_validation">This field is mandatory</div>
                                            </div>
                                        </div>
                                        <div class="form-group  col-lg-12 float-left">
                                            <label class="control-label col-lg-2 float-left">Is Architect available?<font color="red">*</font>:</label>
                                            <div class="col-lg-4 float-left">
                                                <input type="radio" id="yes" name="available" value="yes" onclick="checkArchitect('Y')" checked>
                                                <label for="yes">Yes</label><br>
                                                <input type="radio" id="no" name="available" value="no" onclick="checkArchitect('N')">
                                                <label for="no">No</label>
                                            </div>
                                        </div>
                                        <script>
                                            function checkArchitect(dataValue)
                                            {
                                                if(dataValue=="Y")
                                                {
                                                    $("#forwardButton").hide();
                                                    $(".forward-des-field").hide();
                                                    $("#attachmentCol").html('');
                                                    $("#submitdButton").show();
                                                }
                                                else
                                                {
                                                    $("#forwardButton").show();
                                                    $(".forward-des-field").show();
                                                    $("#attachmentCol").html('<input type="file" name="appUploadFile">');
                                                    $("#submitdButton").hide();
                                                    $("#architech_Id").prop("checked", true);
                                                }
                                            }
                                        </script>

                                        <div class="form-group  col-lg-12 float-left forward-des-field" style="display:none">
                                            <label class="control-label col-lg-2 float-left">Drawing To Be Forwarded : <span class="text-danger">*</span>:</label>
                                            <div class="col-lg-6 float-left">
                                                    <div class="form-group  col-lg-12 float-left forward-des-field" style="display:none">
                                                        <div class="col-lg-6 float-left">
                                                            <input type="checkbox" id="allD" onclick="allDrawing()"/> All Drawings<br/>
                                                            <input type="checkbox" id="architech_Id" class="forwardVerifier" name="is_Architect_Forwarded" value="Y" /> Architectural Drawing<br/>
                                                            <input type="checkbox" id="struct_id" class="forwardVerifier" name="is_Structural_Forwarded" value="Y" /> Structural Drawing<br/>
                                                            <input type="checkbox" id="ele_id" class="forwardVerifier" name="is_Electrical_Forwarded" value="Y" /> Electrical Drawing<br/>
                                                        </div>
                                                    </div>
                                            </div>
                                            <script>


                                                function  allDrawing()
                                                {
                                                    if(document.getElementById('allD').checked) {
                                                        $(".forwardVerifier").prop("checked", true);
                                                    }
                                                    else
                                                    {
                                                        $(".forwardVerifier").prop("checked", false);
                                                    }
                                                }

                                            </script>
                                            <div class="form-group  col-lg-12 float-left forward-des-field" style="display:none" >
                                                <label class="control-label col-lg-2 float-left">Attach Forwarding letter <span class="text-danger">*</span>:</label>
                                                <div class="col-lg-6 float-left">
                                                    <table border="0" id="fileTable">
                                                        <thead>
                                                        <tr>
                                                            <td id="attachmentCol"></td>
                                                        </tr>
                                                        </thead>
                                                    </table>
                                                    <table>
                                                        <tbody>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <label class="labelNormalError"><span id="fileDeleteErrorMsg" style="display:none"></span></label>
                                                </div>
                                            </div>
                                        </div>

                                            <div class="form-group  col-lg-12 float-left">
                                                <label class="control-label col-lg-2 float-left">Comments:</label>
                                                <div class="col-lg-4 float-left">
                                                    <textarea name="appl_comments" id="applicantCommentsId" class="form-control bg-light border-secondary"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group col-lg-12">
                                                <div class="col-lg-12 float-left text-center">
                                                    <input type="hidden" name="is_Private_Building" id="is_Private_Building">
                                                    <button type="button" class="btn btn-primary" id="submitdButton" onclick="formSubmit()">Submit</button>


                                                    <button type="button" class="btn btn-info" style="display:none" id="forwardButton" onclick="forwardToDes()">Forward to Des</button>

                                                    <%--<button type="button" class="btn btn-danger" onclick="verfiyApplcication()">Hold</button>--%>
                                                </div>
                                                <div id="message"></div>
                                            </div>
                                    </div>
                                </div>
                             </div>
                        <div>
                        <font color="red"></font>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
<script>
    var i =1;
    function forwardToDes() {
        if(i==1){
            var url='${pageContext.request.contextPath}/building/rejectApplication?type=OPERATOR_FORWARD_TO_DES';
            var options ={target:'#message',
                url:url,
                type:'POST',
                enctype: 'multipart/form-data',
                data: $('#buildingConsFormId').serialize()};
            $("#buildingConsFormId").ajaxSubmit(options);
        }
        i=1;
    }


    function verfiyApplcication(){
        var url = '${pageContext.request.contextPath}/building/rejectApplication?type=HOLD';
        var options = {
            target: '#loadContainer',
            url: url,
            type: 'POST',
            enctype: 'multipart/form-data',
            data: $("#buildingConsFormId").serialize()
        };
        $("#buildingConsFormId").ajaxSubmit(options);
    }


    function checkCDBNo(cidNo) {
        var url='${pageContext.request.contextPath}/building/getArchitectController?cidNo='+cidNo;
        $.ajax({
            type: "GET",
            url: url,
            success: function(res)
            {
                $("#engineerNameId").val(res.fullName);
            }
        });
    }

    function checkEID(cidNo,rowId)

    {
                    var url='${pageContext.request.contextPath}/building/getEmployeeDetails?cidNo='+cidNo;
                    $.ajax({
                        type: "GET",
                        url: url,
                        success: function(res)
                        {
                            $("#nameStr_id").val(res.name);
                        }
                    });

    }

    function rcscCidno(cidNo,rowId)

    {
        var url='${pageContext.request.contextPath}/building/getEmployeeDetails?cidNo='+cidNo;
        $.ajax({
            type: "GET",
            url: url,
            success: function(res) {
                $("#nameEle_id").val(res.name);
            }
        });
    }

    function checkCID(cidNo,rowId)

    {
        var url='${pageContext.request.contextPath}/building/getCitizenDetails?cidNo='+cidNo;
        $.ajax({
            type: "GET",
            url: url,
            success: function(res)
            {
                var dataArray = rowId.split("_");
                if(dataArray[1]==0)
                    $("#ownerNameId_"+dataArray[1]).val(res.ownerName);
                else if(dataArray[1]==1)
                    $("#ownerNameId_"+dataArray[1]).val(res.ownerName);
                else if(dataArray[1]==2)
                    $("#ownerNameId_"+dataArray[1]).val(res.ownerName);
                else if(dataArray[1]==3)
                    $("#ownerNameId_"+dataArray[1]).val(res.ownerName);
            }
        });
    }
    $(document).ready(function() {
        var selectedValue = $("#dzonkhag_Id").val();
        var dropdownType = "GEWOG_LIST";
        var targetId = "gewog_Id";

        $("#"+targetId).empty();
        $("#"+targetId).append("<option value='0'>Select</option>");
        var url='${pageContext.request.contextPath}/common/populateDropdown?dropdownType='+dropdownType+'&parameter='+selectedValue;
        $.ajax({
            type: "GET",
            url: url,
            success: function(res)
            {
                for(var i=0;i<res.length;i++)
                {
                    $("#"+targetId).append("<option value='"+res[i].dropdownId +"'>" + res[i].dropdownName + "</option>");
                }
            }
        })

    });

        function populateDependentDropDown(selectedValue,dropdownType,targetId){
        $("#"+targetId).empty();
        $("#"+targetId).append("<option value='0'>Select</option>");
        var url='${pageContext.request.contextPath}/common/populateDropdown?dropdownType='+dropdownType+'&parameter='+selectedValue;
        $.ajax({
            type: "GET",
            url: url,
            success: function(res)
            {
                for(var i=0;i<res.length;i++)
                {
                    $("#"+targetId).append("<option value='"+res[i].dropdownId +"'>" + res[i].dropdownName + "</option>");
                }
            }
        })
        };

    function bulidingCategory(categoryType)
    {
        $("#is_Private_Building").val(categoryType);
        $(".categoryTypeClass").hide();
        if(categoryType=='I')
        {
            $("#instituteDiv").show();
        }
        else if(categoryType=='O')
        {
            $("#otherIdDiv").show();
        }
        else
        {
            $("#privateDiv").show();
        }
    }


    function formSubmit() {
        if(checkValidation()==0)
        {
            var url='${pageContext.request.contextPath}/building/saveApplication?type=SAVE';
            var options ={target:'#message',
                url:url,
                type:'POST',
                enctype: 'multipart/form-data',
                processData: false,  // tell jQuery not to process the data
                contentType: false,   // tell jQuery not to set contentType
                data: $('#buildingConsFormId').serialize()};
                $("#buildingConsFormId").ajaxSubmit(options);
        }
    }

    function checkValidation()
    {
        var storied = $("#storied").val();
        var building_Use_Id = $("#building_Use_Id").val();
        var number_of_Floor = $("#number_of_Floor").val();
        var private = $("#private").val();
        var thram_Nos = $("#thram_Nos").val();
        var plotNumberId = $("#plotNumberId").val();
        var dzonkhag_Id = $("#dzonkhag_Id").val();
        var contactDetailsId = $("#contactDetailsId").val();
        var contactEmailIdId = $('#contactEmailIdId').val();
        var contactPhoneNoId = $("#contactPhoneNoId").val();
        var engineerRegNoId = $("#engineerRegNoId").val();
        var engStruNameId = $("#engStruNameId").val();
        var engEleNameId = $("#engEleNameId").val();
        var engineerNameId = $("#engineerNameId").val();
        var nameStr_id = $("#nameStr_id").val();
        var nameEle_id = $("#nameEle_id").val();
        var structural_Graduation_Year = $("#structural_Graduation_Year").val();
        var electrical_Graduation_Year = $("#electrical_Graduation_Year").val();
        var ownerCIDId_0 = $("#ownerCIDId_0").val();
        var ownerNameId_0 = $("#ownerNameId_0").val();
        var ownerContId_0 = $("#ownerContId_0").val();


        $(".validation-error").hide();
        var check = 0;
        if(storied=="")
        {
            check=1;
            $("#storied_validation").show();
        }
        if(building_Use_Id=="")
        {
            check=1;
            $("#building_Use_Id_validation").show();
        }
        if(number_of_Floor=="")
        {
            check=1;
            $("#number_of_Floor_validation").show();
        }
        if(private=="")
        {
            check=1;
            $("#private_validation").show();
        }
        if(ownerCIDId_0=="")
        {
            check=1;
            $("#ownerCIDId_0_validation").show();
        }
        if(ownerNameId_0=="")
        {
            check=1;
            $("#ownerNameId_0_validation").show();
        }
        if(ownerContId_0=="")
        {
            check=1;
            $("#ownerContId_0_validation").show();
        }
        if(thram_Nos=="")
        {
            check=1;
            $("#thram_Nos_validation").show();
        }
        if(plotNumberId=="")
        {
            check=1;
            $("#plotNumberId_validation").show();
        }
        if(dzonkhag_Id=="")
        {
            $("#dzonkhag_Id_validation").show();
            check=1;
        }
        if(contactDetailsId=="")
        {
            check=1;
            $("#contactDetailsId_validation").show();
        }
        if(contactEmailIdId=="")
        {
            check=1;
            $("#contactEmailIdId_validation").show();
        }

        if(contactPhoneNoId=="")
        {
            check=1;
            $("#contactPhoneNoId_validation").show();
        }
        if(engineerRegNoId=="")
        {
            check=1;
            $("#engineerRegNoId_validation").show();
        }
        if(engineerNameId==""){
            check=1;
            $("#architecNametValidator").show();
        }

        if(engStruNameId=="")
        {
            check=1;
            $("#engStruNameId_validation").show();

        }
        if(nameStr_id==""){
            check=1;
            $("#structualNameValidator").show();

        }
        if(engEleNameId=="")
        {
            check=1;
            $("#engEleNameId_validation").show();
        }
        if(nameEle_id==""){
            check=1;
            $("#electricalNameValidator ").show();
        }
        if(structural_Graduation_Year=="")
        {
            check=1;
            $("#structural_Graduation_Year_validation").show();

        }
        if(electrical_Graduation_Year == "")
        {
            check=1;
            $("#electrical_Graduation_Yea_validation").show();

        }
        return check;
    }
    $('input.valid-number').bind('keypress', function(e) {
        return ( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57)) ? false : true ;
    });

    $('#contactEmailIdId').on('blur', function () {
        var email = $(this).val().trim();
        if (email != '' && !isEmail(email)) {
            $('#emailErrorMsg').text('Email format is invalid.');
            $('#contactEmailIdId').val('');
            $('#contactEmailIdId').addClass('error');
        }
    });

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }
        $('#contactEmailIdId').on('keyup blur', function () {
            var val = $(this).val();
            if (val != '') {
                $('#contactEmailIdId').removeClass('error');
                $('#emailErrorMsg').text('');
            }
        });

</script>

</body>
</html>