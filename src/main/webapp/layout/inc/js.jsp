<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script type="text/javascript" src="<c:url value='/resources/jquery/jquery.min.js'/>"></script>--%>
<%--<script src="<c:url value="/resources/assets/js/vendors/jquery-3.2.1.min.js"/>"></script>--%>

<script type="text/javascript" src="<c:url value="/resources/jquery/jQuery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/popper.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/css/bootstrap.min.css"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/chosen.jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/jquery.dataTables.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/dist/dataTables.bootstrap4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/plugins/charts-c3/plugin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/assets/plugins/input-mask/plugin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTable/tableExport.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery.validate.js"/>"></script>

<%--Datatable--%>
<script type="text/javascript" src="<c:url value='/resources/dataTable/jquery.dataTables.editable.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/dataTable/dataTables.buttons.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/dataTable/pdfmake.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/dataTable/jszip.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/dataTable/jspdf.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/dataTable/vfs_fonts.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/dataTable/jquery.table2excel.js'/>"></script>

<script type="text/javascript" src="<c:url value="/resources/assets/js/dashboard.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/assets/plugins/charts-c3/plugin.css"/>"/>
<script type="text/javascript" src="<c:url value="/resources/assets/plugins/input-mask/plugin.js"/>"></script>


<script type="text/javascript" src="<c:url value='/resources/js/jquery.validate.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.bundle.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/datepicker.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/dialog.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/alertify.new.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/nprogress.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.blockUI.js' />"></script>

<script type="text/javascript" src="<c:url value="/resources/dataTableJar/js-xlsx/xlsx.core.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF/jspdf.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/dataTableJar/jsPDF-AutoTable/jspdf.plugin.autotable.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/customJS/nprogress.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/customJS/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/customJS/script.js"/>"></script>




<script>
    $('.dropdown-menu a.dropdown-toggle').on('click', function (e) {
        if (!$(this).next().hasClass('show')) {
            $(this).parents('.dropdown-menu').first().find('.show').removeClass("show");
        }
        var $subMenu = $(this).next(".dropdown-menu");
        $subMenu.toggleClass('show');
        $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function (e) {
            $('.dropdown-submenu .show').removeClass("show");
        });
        return false;
    });
</script>