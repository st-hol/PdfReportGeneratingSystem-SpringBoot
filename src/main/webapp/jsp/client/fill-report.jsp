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

    <jsp:include page="${pageContext.request.contextPath}/resources/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/bootstrap_min.jsp"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/navbar.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/personal-cabinet.css"/>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/input-g.css" rel="stylesheet">


</head>
<body>

<div class="container bg-faded">
    <div class="row justify-content-center">
        <div class="col-8">
            <div class="row">
                <div class="container offset-5">


                    <form method="POST" class="form-signin"
                          action="${pageContext.request.contextPath}/client/fill-report">

                        <input type="hidden" name="templateId" value="${reportTemplate.id}">

                        <%--        todo--%>
                        <c:forEach var="field" items="${reportTemplate.fields}">


                        <div class="form-group">
                            <div class="soflow-color">
                                <div class="clearfix">
                                    <h3 class="panel-title form-title-my">
                                        <c:out value="Fill report fields, please:"/>
                                    </h3>

                                    <input maxlength="45" minlength="1" name="<c:out value="${field.fieldName}"/>"
                                           type="text" placeholder="${field.fieldName}"
                                           required>
                                </div>
                            </div>
                            <br>
                            </c:forEach>

                            <div class="form-group">
                                <div class="">
                                    <button type="submit" class="btn btn-lg btn-primary btn-block">
                                        <spring:message code="label.submit"/>
                                    </button>

                                    <BR>
                                    <BR>

                                    <a class="" href="${pageContext.request.contextPath}/personal-cabinet">
                                        <div class="btn btn-lg btn-primary btn-block">
                                            <spring:message code="back.to.cabinet"/>
                                        </div>
                                    </a>

                                </div>
                            </div>
                    </form>


                </div>
            </div>
        </div>
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








