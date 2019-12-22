<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<c:set var="noOfPages" value="${page.getTotalPages()}"/>
<c:set var="currentPage" value="${page.getNumber()}"/>

<html>
<head>
    <title>
        <spring:message code="see.all.reports"/>
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
    <%--    <link href="${pageContext.request.contextPath}/resources/css/input-g.css" rel="stylesheet">--%>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/report-list.css"/>
    <link href="${pageContext.request.contextPath}/resources/css/preload.css" rel="stylesheet">

</head>

<c:if test="${noOfPages-1} >= 0">
<div class="row justify-content-center">
    <div class="panel-heading clearfix">
        <h3 class="panel-title">
            <div><spring:message code="select.page.number"/></div>
        </h3>
    </div>
</div>
<nav>
    <div class="row justify-content-center">
        <ul class="pagination">
            <c:if test="${page.isFirst() != true}">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}${url}?page=${currentPage-1}&size=${page.getSize()}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only"><spring:message code="label.prev"/></span>
                    </a>
                </li>
            </c:if>


            <c:forEach begin="0" end="${noOfPages-1}" var="i">
                <c:choose>
                    <c:when test="${page.getNumber() eq i}">
                        <li class="page-item active" style="background-color: rebeccapurple">
                            <a class="in-table-link page-link"
                               href="#"> ${i + 1} </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="in-table-link page-link"
                               href="${pageContext.request.contextPath}${url}?page=${i}&size=${page.getSize()}">${i+1}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>


            <c:if test="${page.isLast() != true}">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}${url}?page=${currentPage+1}&size=${page.getSize()}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only"><spring:message code="label.next"/></span>
                    </a>
                </li>
            </c:if>

        </ul>
    </div>
</nav>


    <div class="row justify-content-center">
        <div class="panel-heading clearfix">
            <h3 class="panel-title">
                <div><spring:message code="select.n.pages.disp"/></div>
            </h3>
        </div>
</div>
<%--sizing--%>
<nav>
    <div class="row justify-content-center">
        <ul class="pagination pagination-sm">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}${url}?page=${currentPage}&size=3"
                   tabindex="-1"><spring:message code="three"/></a>
            </li>
            <li class="page-item">
                <a class="page-link" href=${pageContext.request.contextPath}${url}?page=${currentPage}&size=5>
                    <spring:message code="five"/>
                </a>
            </li>
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}${url}?page=${currentPage}&size=10">
                    <spring:message code="ten"/>
                </a>
            </li>
        </ul>
    </div>
</nav>


    <div id="loader" class="center"></div>

    <div class="row justify-content-center">
        <div class="send-email-response">
            <div id="sendEmailError"></div>
            <div id="sendEmailSuccess"></div>
        </div>
</div>


    <form id="sendEmailForm" name="sendEmailForm">
        <table class="responstable" border="1" cellpadding="3" cellspacing="3">
            <tr>
                <th><spring:message code="placeholder.report.id"/></th>
                <th><spring:message code="placeholder.report.template.name"/></th>
                <th><spring:message code="completion.time"/></th>
                <th><spring:message code="click.download"/></th>
                <th><spring:message code="click.get.by.email"/></th>
            </tr>

            <c:forEach var="report" items="${page.content}">
                <tr>
                    <td><c:out value="${report.id}"/></td>
                    <td><c:out value="${report.reportType.templateName}"/></td>
                    <td><c:out value="${report.completionTime}"/></td>
                    <td>
                        <a class="page-link get-link"
                           href="${pageContext.request.contextPath}/client/download-file/${report.id}">
                            <div class="btn btn-lg btn-primary btn-block">
                                <spring:message code="download"/>
                            </div>
                        </a>
                    </td>
                    <td>

                        <input type="hidden" id="sendEmailLink"
                               value="${pageContext.request.contextPath}/client/send-by-email/${report.id}">
                        <button type="submit" class="btn btn-lg btn-primary btn-block">
                            <spring:message code="send"/>
                        </button>

                            <%--                <a class="page-link get-link"--%>
                            <%--                   href="${pageContext.request.contextPath}/client/send-by-email/${report.id}">--%>
                            <%--                    <spring:message code="send"/>--%>
                            <%--                </a>--%>
                    </td>
                </tr>
            </c:forEach>
        </table>
</form>
<br>

</c:if>
<c:if test="${noOfPages - 1<0}">

    <div class="row justify-content-center">
        <div class="panel-heading clearfix">
            <h3 class="panel-title">
                <div><spring:message code="u.have.no.reports.yet"/></div>
            </h3>
        </div>
    </div>

</c:if>

<div class="row justify-content-center">
    <a class="" href="${pageContext.request.contextPath}/personal-cabinet">
        <div class="btn btn-lg btn-primary btn-block">
            <spring:message code="back.to.cabinet"/>
        </div>
    </a>
</div>

<script src="${pageContext.request.contextPath}/resources/js/send-email.js"></script>

</body>
</html>


<%--    &lt;%&ndash;nav-pagination&ndash;%&gt;--%>
<%--    <nav aria-label="Page navigation example">--%>
<%--        <ul class="pagination">--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="#" aria-label="Previous">--%>
<%--                    <span aria-hidden="true">&laquo;</span>--%>
<%--                    <span class="sr-only">Previous</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="#" aria-label="Next">--%>
<%--                    <span aria-hidden="true">&raquo;</span>--%>
<%--                    <span class="sr-only">Next</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </nav>--%>








