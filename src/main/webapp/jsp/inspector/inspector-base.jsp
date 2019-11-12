<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>


<html>
<head>
   <title> <spring:message code="label.inspector"/></title>

    <meta name="viewport" content="width=device-width"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <jsp:include page="${pageContext.request.contextPath}/resources/css/bootstrap_min.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/jquery.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/resources/js/bootstrap_min.jsp"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/navbar.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/personal-cabinet.css"/>
</head>
<body>

<!--navbar-->
<jsp:include page="${pageContext.request.contextPath}/jsp/common/navbar-cabinet.jsp"/>

<!--our content goes here-->
<div class="container content">
    <div class="row profile">

                <jsp:include page="${pageContext.request.contextPath}/jsp/inspector/sidebar.jsp"/>
                <jsp:include page="${pageContext.request.contextPath}/jsp/common/default-content.jsp"/>


    </div>
</div>





<div class="container">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-9 ft">
            <footer class="footer">
                <div class="row">
                    <div class="col-md-4">
                        <span class="copyright">Copyright &copy; TaxReportSystem 2019</span>
                    </div>
                </div>
            </footer>
        </div>
    </div>
</div>
</body>
</html>



