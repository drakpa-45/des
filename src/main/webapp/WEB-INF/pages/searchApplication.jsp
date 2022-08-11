<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card card-body">
    <form name="ConstructionFormAudit" id="buildingConsAuditFormId" method="post" action="/DOES/buildingConsApplAudit.do" class="formAlignment" enctype="multipart/form-data"><div><input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="9419af6723155078a6c701a98855867a"></div>
        <div class="row">
            <div class="col-md-12 col-lg-12">
                <h4 class="page-head-line" style="
                    margin-bottom: 21px;
                ">Search Application<small><i class="ace-icon fa fa-angle-double-right"></i>Application Details</small></h4>
            </div>
        </div>
        <div class="panel-body form-horizontal">
            <div class="form-group col-lg-12 float-left">
                <label class="control-label col-md-2 float-left">Application Number:</label>
                <div class="col-md-3 float-left"><input type="text" name="applNo" value="" id="applNumberId" class="form-control bg-light border-secondary">
                    <label class="labelNormalError"><span id="applErrorMsg" style="display:none"></span></label>
                </div>
                <div class="col-md-2  float-left">
                    <button onclick="trackApplication();" class="btn btn-primary" value="" id="applicationAuditButtonId"> Search</button>
                </div>
            </div>

            <div  id="statusDiv">
             </div>
        </div>
    </form>
</div>



<script>
    function trackApplication()
    {
        var applicationNo = $("#applNumberId").val()
        var url='${pageContext.request.contextPath}/building/trackApplication?applicationNo='+applicationNo;
        $.ajax({
            type: "GET",
            url: url,
            success: function(res)
            {
                $("#statusDiv").html(res);
            }
        });

    }
</script>