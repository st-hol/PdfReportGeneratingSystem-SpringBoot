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
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/preload.css" rel="stylesheet">

</head>
<body>

<div id="loader" class="center"></div>
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
        <form id="sendEmailForm" name="sendEmailForm">
            <input type="hidden" id="sendEmailLink"
                   value="${pageContext.request.contextPath}/client/send-by-email/<c:out value="${reportId}"/>">
            <button type="submit" class="btn btn-lg btn-primary btn-block">
                <spring:message code="get.by.email"/>
            </button>
        </form>

        <div class="send-email-response">
            <div id="sendEmailError"></div>
            <div id="sendEmailSuccess"></div>
        </div>

    </div>
</div>
<!-- Row end -->
<script src="${pageContext.request.contextPath}/resources/js/send-email.js"></script>

</body>
</html>

<%--'use strict';--%>

<%--var sendEmailForm = document.querySelector('#sendEmailForm');--%>
<%--var sendEmailLink = document.querySelector('#sendEmailLink');--%>
<%--var sendEmailError = document.querySelector('#sendEmailError');--%>
<%--var sendEmailSuccess = document.querySelector('#sendEmailSuccess');--%>

<%--function sendEmail() {--%>

<%--var url = sendEmailLink.value;--%>
<%--var xhr = new XMLHttpRequest();--%>
<%--xhr.open("GET", url);--%>


<%--xhr.onload = function () {--%>

<%--console.log(xhr.responseText);--%>
<%--var response = JSON.parse(xhr.responseText);--%>
<%--if (xhr.status == 200 && response.success === true) {--%>
<%--sendEmailError.style.display = "none";--%>
<%--sendEmailSuccess.innerHTML = "<p>Email was sent.</p><p>TO:" + response.email + "</p>" +--%>
<%--"<p> File name: " + response.fileName + "</p>";--%>
<%--sendEmailSuccess.style.display = "block";--%>

<%--document.querySelector(--%>
<%--"#loader").style.display = "none";--%>
<%--document.querySelector(--%>
<%--"body").style.visibility = "visible";--%>
<%--} else {--%>
<%--sendEmailSuccess.style.display = "none";--%>
<%--sendEmailError.innerHTML = (response && response.message) || "Some Error Occurred";--%>
<%--}--%>
<%--}--%>

<%--xhr.send();--%>
<%--}--%>

<%--sendEmailForm.addEventListener('submit', function (event) {--%>

<%--document.querySelector(--%>
<%--"body").style.visibility = "hidden";--%>
<%--document.querySelector(--%>
<%--"#loader").style.visibility = "visible";--%>

<%--sendEmail();--%>
<%--event.preventDefault();--%>

<%--}, true);--%>
