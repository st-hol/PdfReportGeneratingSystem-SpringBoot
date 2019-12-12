<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>


<html>
<head>
    <title>
        <fmt:message key="edit.report"/>
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
</head>
<body>

<!-- Row start -->
<div class="row">
    <div class="container col-lg-3 offset-5">

        <div class="panel-heading clearfix">
            <h3 class="panel-title">
                <spring:message code="report.done.success"/>
            </h3>
        </div>


        <a class="" href="${pageContext.request.contextPath}/personal-cabinet">
            <button type="submit" class="btn btn-lg btn-primary btn-block">
                <spring:message code="back.to.cabinet"/>
            </button>
        </a>
        <br>
        <a class="" href="${pageContext.request.contextPath}/client/download-file/<c:out value="${reportId}"/>">
            <button type="submit" class="btn btn-lg btn-primary btn-block">
                <spring:message code="download"/>
            </button>
        </a>
        <br>
        <a class="" href="${pageContext.request.contextPath}/personal-cabinet">
            <button type="submit" class="btn btn-lg btn-primary btn-block">
                <spring:message code="get.by.email"/>
            </button>
        </a>
    </div>
</div>
<!-- Row end -->
</body>
</html>