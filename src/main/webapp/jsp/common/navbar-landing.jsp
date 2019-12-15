<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<c:set var="userName" value="${pageContext.request.userPrincipal.name}"/>


<nav class="navbar navbar-expand-lg fixed-top bg-dark ">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/welcome">
        <spring:message code="go.home"/>
    </a>

    <a class="navbar-brand en-icon" href="?lang=en">
        <img class="lang-icon" src="${pageContext.request.contextPath}/resources/image/eng.png"
             alt="<spring:message code="lang.en"/>">
    </a>

    <a class="navbar-brand ua-icon" href="?lang=ua">
        <img class="lang-icon" src="${pageContext.request.contextPath}/resources/image/ukr.png"
             alt="<spring:message code="lang.ua"/>">
    </a>


    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse " id="navbarSupportedContent">
        <ul class="navbar-nav mr-4">


            <%--            <li class="nav-item">--%>
            <%--                <a class="nav-link " href="${pageContext.request.contextPath}/">--%>
            <%--                    <spring:message code="try.demo"/>--%>
            <%--                </a>--%>
            <%--            </li>--%>
            <li class="nav-item">
                <a class="nav-link " href="${pageContext.request.contextPath}/personal-cabinet">
                    <spring:message code="pers.cab"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/login">
                    <spring:message code="sign.in"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="${pageContext.request.contextPath}/registration">
                    <spring:message code="sign.up"/>
                </a>
            </li>


        </ul>

    </div>
</nav>

