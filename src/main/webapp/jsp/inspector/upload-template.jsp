<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">--%>
    <title><spring:message code="admin.upload.template"/></title>
    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/upload-file.css"/>

    <jsp:include page="${pageContext.request.contextPath}/resources/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/bootstrap_min.jsp"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/navbar.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/personal-cabinet.css"/>
    <%--    <link rel="stylesheet" href="/css/main.css" />--%>
    <%--    <script src="${pageContext.request.contextPath}/js/main.js" ></script>--%>
    <%--    <jsp:include page="${pageContext.request.contextPath}/resources/js/main.js"/>--%>
    <%--<jsp:include page="${pageContext.request.contextPath}/resources/css/upload-file.css"/> --%>
</head>
<body>
<noscript>
    <h2><spring:message code="no.js"/></h2>
</noscript>

<!--navbar-->
<jsp:include page="${pageContext.request.contextPath}/jsp/common/navbar-cabinet.jsp"/>


<div class="upload-container">
    <div class="upload-header">
        <h3><spring:message code="admin.upload.template"/></h3>
    </div>
    <div class="upload-content">
        <div class="single-upload">
            <br>
            <h6><spring:message code="put.pdf.here"/></h6>
            <form id="singleUploadForm" name="singleUploadForm">
                <input id="singleFileUploadInput" type="file" name="file" class="file-input" required/>
                <br><br>
                <button type="submit" class="primary submit-btn"><spring:message code="label.submit"/></button>


                <div class="upload-response">
                    <div id="singleFileUploadError"></div>
                    <div id="singleFileUploadSuccess"></div>
                </div>

            </form>
        </div>

    </div>
</div>

<br>
<div class="container">
    <div class="row">
        <h4><spring:message code="admin.you.admin"/></h4>
    </div>
</div>
<div class="container">
    <div class="row">
        <h4><spring:message code="admin.you.can"/></h4>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>