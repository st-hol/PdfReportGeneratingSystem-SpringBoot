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

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/personal-cabinet.css"/>
</head>
<body>

<!-- Row start -->
<div class="row">
    <div class="container col-lg-3 offset-5">

        <div class="panel-heading clearfix">
            <h3 class="panel-title">
                <spring:message code="choose.report"/>
            </h3>
        </div>

        <form method="POST" class="" action="${pageContext.request.contextPath}/reporting/choose-report">

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
                    <button type="submit" class="btn btn-primary btn-sm">
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
<!-- Row end -->
</body>
</html>