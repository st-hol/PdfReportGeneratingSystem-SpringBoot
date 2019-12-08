<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">--%>
    <title>Spring Boot File Upload / Download Rest API Example</title>
    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/upload-file.css"/>


    <%--    <link rel="stylesheet" href="/css/main.css" />--%>
    <%--    <script src="${pageContext.request.contextPath}/js/main.js" ></script>--%>
    <%--    <jsp:include page="${pageContext.request.contextPath}/resources/js/main.js"/>--%>
    <%--<jsp:include page="${pageContext.request.contextPath}/resources/css/upload-file.css"/> --%>
</head>
<body>
<noscript>
    <h2>Sorry! Your browser doesn't support Javascript</h2>
</noscript>
<div class="upload-container">
    <div class="upload-header">
        <h2>Spring Boot File Upload / Download Rest API Example</h2>
    </div>
    <div class="upload-content">
        <div class="single-upload">
            <h3>Upload Single File</h3>
            <form id="singleUploadForm" name="singleUploadForm">
                <input id="singleFileUploadInput" type="file" name="file" class="file-input" required/>
                <button type="submit" class="primary submit-btn">Submit</button>
            </form>
            <div class="upload-response">
                <div id="singleFileUploadError"></div>
                <div id="singleFileUploadSuccess"></div>
            </div>
        </div>
        <div class="multiple-upload">
            <h3>Upload Multiple Files</h3>
            <form id="multipleUploadForm" name="multipleUploadForm">
                <input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required/>
                <button type="submit" class="primary submit-btn">Submit</button>
            </form>
            <div class="upload-response">
                <div id="multipleFileUploadError"></div>
                <div id="multipleFileUploadSuccess"></div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>