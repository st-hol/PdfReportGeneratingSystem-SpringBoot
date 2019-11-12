<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 17/05/19
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>


<%--<c:set var="userPrincipal" value="${pageContext.request.userPrincipal}"/>--%>


<html>
<head>
    <title>
        <fmt:message key="submit.report"/>
    </title>

    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <jsp:include page="${pageContext.request.contextPath}/resources/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/bootstrap_min.jsp"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/personal-cabinet.css"/>
</head>
<body>

<!-- Row start -->
<div class="row">
    <div class="container col-lg-3 offset-5">

        <div class="panel-heading clearfix">
            <h3 class="panel-title">
                <spring:message code="submit.report"/>
            </h3>
        </div>
        <%--action="${pageContext.request.contextPath}/client/submit-report"--%>
        <form id="report-submit-ajax-form" class="" method="POST">

           <%-- <input type="hidden" value="${userPrincipal}" name="person">--%>

            <div id="ajax-succeed"></div>

            <div class="form-group">
               <div class="">
                    <input maxlength="45" minlength="1" name="companyName" id="companyName" type="text"
                           placeholder="<spring:message code="placeholder.company.name"/>" required>
                </div>
            </div>


            <div class="form-group">
                <div class="">
                    <input maxlength="45" minlength="1" name="taxpayerCode" id="taxpayerCode" type="number"
                           placeholder="<fmt:message key="placeholder.taxpayer.code"/>" required>
                </div>
            </div>

            <div class="form-group">
                <div class="">
                    <button type="submit" class="btn btn-primary btn-sm" id="ajax-submit-btn">
                        <spring:message code="label.submit"/>
                    </button>
                </div>
            </div>
        </form>

        <a class="" href="${pageContext.request.contextPath}/personal-cabinet">
            <spring:message code="back.to.cabinet"/>
        </a>
    </div>
</div>


<script src="${pageContext.request.contextPath}/resources/js/ajax-submit-report.js"></script>
<!-- Row end -->
</body>
</html>