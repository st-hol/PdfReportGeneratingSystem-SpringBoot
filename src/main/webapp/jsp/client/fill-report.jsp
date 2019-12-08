<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<%--<c:set var="fields" value="${reportTemplate.fields}"/>--%>
<%--todo--%>
<html>
<head>
    <title>
        <spring:message code="fill.report"/>
    </title>
    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/report-list.css"/>
</head>
<body>

<div class="table-cont">


    <form method="POST" class="" action="${pageContext.request.contextPath}/client/fill-report">

        <input type="hidden" name="templateId" value="${reportTemplate.id}">

        <%--        todo--%>
        <c:forEach var="field" items="${reportTemplate.fields}">

            <c:out value=" Input << ${field.fieldName} >>"/> "
            <div class="form-group">
                <div class="">
                    <input maxlength="45" minlength="1" name="<c:out value="${field.fieldName}"/>" type="text" required>
                </div>
            </div>

        </c:forEach>

        <div class="form-group">
            <div class="">
                <button type="submit" class="btn btn-primary btn-sm">
                    <spring:message code="label.submit"/>
                </button>
            </div>
        </div>
    </form>

    <br>
    <div class="home">
        <a class="" href="${pageContext.request.contextPath}/personal-cabinet">
            <spring:message code="back.to.cabinet"/>
        </a>
    </div>
</div>


</body>
</html>


<%--    &lt;%&ndash;nav-pagination&ndash;%&gt;--%>
<%--    <nav aria-label="Page navigation example">--%>
<%--        <ul class="pagination">--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="#" aria-label="Previous">--%>
<%--                    <span aria-hidden="true">&laquo;</span>--%>
<%--                    <span class="sr-only">Previous</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="#" aria-label="Next">--%>
<%--                    <span aria-hidden="true">&raquo;</span>--%>
<%--                    <span class="sr-only">Next</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </nav>--%>








