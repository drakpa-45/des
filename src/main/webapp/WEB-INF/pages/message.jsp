<div class="col-lg-12 float-left mt-3">
    <%if(request.getAttribute("result").equals("Failed")) {%>
     <div class="alert alert-danger">Something went wrong, your application could not submit. Try again!</div>
    <%} else if (request.getAttribute("result").equals("Verified")) {%>
    <div class="alert alert-success">Application has been verified.</div>
    <%} else if (request.getAttribute("result").equals("SUBMITTED")) {%>
    <div class="alert alert-success">Application has been submitted and application no. is <b>${applicationNo}</b></div>
    <% } else if (request.getAttribute("result").equals("Approved")) {%>
    <div class="alert alert-success">Application has been approved and closed</div>
    <%} else if (request.getAttribute("result").equals("HOLD")) {%>
    <div class="alert alert-success">Application is on hold</div>
    <%} else if (request.getAttribute("result").equals("UNHOLD")) {%>
    <div class="alert alert-success">Application is UnHold</div>
    <% } else if (request.getAttribute("result").equals("REJECT")) {%>
    <div class="alert alert-success">Application is rejected</div>
    <%} else if (request.getAttribute("result").equals("FORWARD")) {%>
    <div class="alert alert-success">Application has been successfully forwarded</div>
    <%} else if (request.getAttribute("result").equals("SUBMIT")) {%>
    <div class="alert alert-success">Your Application has been verified from DES and it will reach to Dzongkhag Operator only after the verification from the Dzongkhag engineers/</div>
    <%} else if (request.getAttribute("result").equals("CLOSE")) {%>
    <div class="alert alert-success">Application is closed</div>
    <%} else if (request.getAttribute("result").equals("OPERATOR_FORWARD_TO_DES")) {%>
    <div class="alert alert-success">Application is forwarded to DES and Application No. is <b>${application_id}</b></div>
    <%}%>
</div>