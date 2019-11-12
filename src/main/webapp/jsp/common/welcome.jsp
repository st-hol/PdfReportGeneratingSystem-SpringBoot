<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<c:set var="userName" value="${pageContext.request.userPrincipal.name}"/>


<html>

<head>
    <title>
        <fmt:message key="tax.report"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/navbar.css">
    <jsp:include page="${pageContext.request.contextPath}/resources/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/bootstrap_min.jsp"/>

</head>
<body>
<c:if test="${userName != null}">
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <h2>
        Welcome ${pageContext.request.userPrincipal.name} | You are already logged in
        You can go to <a href="${contextPath}/personal-cabinet">personal cabinet</a>
        or <a onclick="document.forms['logoutForm'].submit()">Logout</a>
    </h2>
</c:if>
<c:if test="${userName == null}">
    <h2>Hello. guest</h2>
</c:if>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/navbar-landing.jsp"/>

<header class="header">
    <div class="overlay"></div>
    <div class="container offset-1">
        <div class="description text-left">
            <h3>
                <span>
                        <spring:message code="tax.report"/>
                </span>
                <p>
                    <spring:message code="landing.welcome"/>
                </p>
            </h3>
        </div>

    </div>
</header>


</body>

</html>


