<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 17/05/19
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>


<html>
<head>
    <title>
        <spring:message code="check.reports"/>
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
<div class="form">
    <div class="row">
        <div class="container align-self-center align-items-center col-lg-3">

            <div class="panel-heading clearfix">
                <i class="icon-calendar"></i>
                <h3 class="panel-title offset-2">
                    <spring:message code="check.reports"/>
                </h3>
            </div>

            <form method="POST" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/inspector/check-report">

                    <div class="form-group">

  <div class="form-group">
                <input type="hidden" name="id" value="${report.id}"/>
            </div>

                        <span>
                            <spring:message code="placeholder.accept"/>
                        </span>
                        <div  class="form-group">
                            <div class="md-radio">
                                <input value="1" id="ac1" type="radio" name="accepted" checked>
                                <label  for="ac1">+</label>
                            </div>
                            <div class="md-radio">
                                <input value="0" id="ac2" type="radio" name="accepted">
                                <label for="ac2">-</label>
                            </div>
                        </div>

                        <span>
                            <spring:message code="should.change"/>
                        </span>
                        <div  class="form-group">
                            <div class="md-radio">
                                <input value="1" id="s1" type="radio" name="shouldBeChanged" checked>
                                <label for="s1">+</label>
                            </div>
                            <div class="md-radio">
                                <input value="0" id="s2" type="radio" name="shouldBeChanged">
                                <label for="s2">-</label>
                            </div>
                        </div>

                        <div  class="form-group">
                            <div class="">
                                <textarea maxlength="100" minlength="1" name="inspectorComment" class="col-12"
                                          placeholder=" <spring:message code="insp.comment"/>" required>
                                </textarea>
                            </div>
                        </div>


                        <div  class="form-group">
                            <div class="">
                                <button type="submit" class="btn btn-primary btn-sm">
                                    <spring:message code="label.submit"/>
                                </button>
                            </div>
                        </div>

                    </div>
            </form>
            <a class="" href="${pageContext.request.contextPath}/personal-cabinet">
                <spring:message code="back.to.cabinet"/>
            </a>
        </div>
    </div>
</div>

<!-- Row end -->
</body>
</html>

