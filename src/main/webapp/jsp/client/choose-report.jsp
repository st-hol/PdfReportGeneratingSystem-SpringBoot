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
    <link href="${contextPath}/resources/css/input-g.css" rel="stylesheet">

</head>
<body>

<!-- Row start -->
<div class="row">
    <div class="container offset-5">

        <div class="row justify-content-center">

            <div class="panel-heading clearfix">
                <h3 class="panel-title">
                    <spring:message code="choose.report"/>
                </h3>
            </div>

            <form method="POST" class="form-signin" action="${pageContext.request.contextPath}/reporting/choose-report">

                <div class="form-group">
                    <select class="soflow-color" name="idTemplate" required>
                        <option value=""><fmt:message key="placeholder.choose.template"/></option>
                        <c:forEach var="template" items="${reportTemplates}">
                            <option value="${template.id}">
                                <c:out value="${template.templateName}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <br>

                <div class="form-group">
                    <div class="">
                        <button type="submit" class="btn btn-lg btn-primary btn-block">
                            <spring:message code="label.go.to.filling"/>
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
<!-- Row end -->
</body>
</html>