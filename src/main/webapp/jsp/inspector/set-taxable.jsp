<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<html>
<head>
    <title>
        <fmt:message key="set.taxable"/>
    </title>

    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <jsp:include page="${pageContext.request.contextPath}/resources/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/bootstrap_min.jsp"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css"/>
</head>
<body>

<!-- Row start -->
<div class="row">
    <div class="container align-items-center align-self-center col-lg-3">

        <div class="form-title">
            <h3 class="panel-title">
                <fmt:message key="reg.prop"/>
            </h3>
        </div>

        <form method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/inspector/set-taxable">

            <div class="form-group">
                <select class="soflow-color" name="idUser" required>
                    <option value=""><fmt:message key="placeholder.choose.person"/></option>
                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}">
                            <c:out value="id:[${user.id}], full name:[${user.firstName} ${user.lastName}]"/>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <select class="soflow-color" name="idItem" required>
                    <option value=""><fmt:message key="placeholder.choose.item"/></option>
                    <c:forEach var="item" items="${items}">
                        <option value="${item.id}">
                            <c:out value="${item.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <br>

            <div class="form-group">
                <div class="">
                    <button type="submit" class="btn btn-primary btn-sm">
                        <fmt:message key="label.submit"/>
                    </button>
                </div>
            </div>

        </form>
        <a class="" href="${pageContext.request.contextPath}/personal-cabinet">
            <fmt:message key="back.to.cabinet"/>
        </a>
    </div>
</div>

<!-- Row end -->
</body>
</html>
