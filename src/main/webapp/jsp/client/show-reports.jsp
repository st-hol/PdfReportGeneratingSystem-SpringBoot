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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/report-list.css"/>
</head>
<body>

<div class="table-cont">
    <table class="responstable" border="1" cellpadding="3" cellspacing="3">
        <tr>
            <th><spring:message code="placeholder.company.name"/></th>
            <th><spring:message code="placeholder.taxpayer.code"/></th>
            <th><spring:message code="completion.time"/></th>
            <th><spring:message code="total.amount"/></th>
            <th><spring:message code="is.accepted"/></th>
            <th><spring:message code="should.change"/></th>
            <th><spring:message code="insp.comment"/></th>
            <th></th>
        </tr>

        <c:forEach var="report" items="${page.content}">
            <tr>
                <td><c:out value="${report.companyName}"/></td>
                <td><c:out value="${report.taxpayerCode}"/></td>
                <td><c:out value="${report.completionTime}"/></td>
                <td><c:out value="${report.totalAmountOfProperty}"/></td>
                <td><c:out value="${report.accepted}"/></td>
                <td><c:out value="${report.shouldBeChanged}"/></td>
                <td><c:out value="${report.inspectorComment}"/></td>
                <c:if test="${report.shouldBeChanged == true}">
                    <td><a href="${pageContext.request.contextPath}/client/edit-report/${report.id}">edit</a></td>
                </c:if>

            </tr>
        </c:forEach>
    </table>

    <nav style="background-color: #9ededd">
        <p>select nomer of page</p>
        <ul class="pagination">
            <c:if test="${page.isFirst() != true}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}${url}?page=${currentPage-1}&size=${page.getSize()}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="0" end="${noOfPages-1}" var="i">
                <c:choose>
                    <c:when test="${page.getNumber() eq i}">
                        <li class="page-item active" style="background-color: rebeccapurple">
                            <a class="in-table-link page-link"
                               href="#"> ${i + 1} (current)  </a>
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
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </c:if>

        </ul>
    </nav>

    <%--sizing--%>
    <nav style="background-color: #5bc0de">
        <p>select N of elements per page</p>
        <ul class="pagination pagination-sm">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}${url}?page=${currentPage}&size=3"
                   tabindex="-1">3</a>
            </li>
            <li class="page-item">
                <a class="page-link" href=${pageContext.request.contextPath}${url}?page=${currentPage}&size=5>5</a>
            </li>
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}${url}?page=${currentPage}&size=10">10</a>
            </li>
        </ul>
    </nav>


    <br>
    <div class="home">
        <a class="" href="${pageContext.request.contextPath}/personal-cabinet">
            <spring:message code="back.to.cabinet"/>
        </a>
    </div>
</div>


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








