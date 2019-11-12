<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>



<html>
<head>
    <title>
        <fmt:message key="make.complaint"/>
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
    <div class="container col-lg-3 offset-4">

        <div class="panel-heading clearfix">
            <i class="icon-calendar"></i>
            <h3 class="panel-title">
                <spring:message code="make.complaint"/>
            </h3>
        </div>

        <form method="POST" class="" action="${pageContext.request.contextPath}/client/make-complaint">
            <div class="form-group">
                <div class="">
                        <textarea maxlength="45" minlength="5" name="content"
                                  placeholder="<spring:message code="placeholder.write.charge"/>"
                                  class="col-12" required>
                        </textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
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



