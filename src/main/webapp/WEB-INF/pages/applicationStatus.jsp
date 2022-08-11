<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<div id="mainContent" class="container">
    <div class="my-3 my-md-5">
        <form name="taskListForm" id="taskListForm" enctype="multipart/form-data">
            <div class="container mdi-format-horizontal-align-center">
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table card-table table-vcenter text-nowrap" id="my-task">
                                <thead>
                                <tr>
                                    <th>Sl#</th>
                                    <th>Action Taken By</th>
                                    <th>Application Status</th>
                                    <th>Action Taken</th>
                                    <th>Action Date</th>
                                </tr>
                                </thead>
                                <tbody id="mytaskBody">
                                <% int i =1;%>
                                <c:forEach var="list" items="${applicationStatus}">
                                    <tr >
                                        <td><%=i%></td>
                                        <td>${list.createdBy}</td>
                                        <td>${list.applicationStatus}</td>
                                        <td>${list.task_Stage_Name}</td>
                                        <td>${list.stringCreatedOn}</td>
                                    </tr>
                                    <% i++;%>
                                </c:forEach>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>
