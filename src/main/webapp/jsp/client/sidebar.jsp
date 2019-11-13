<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>


<div class="col-md-3">
    <div class="profile-sidebar position-fixed">

        <!-- SIDEBAR MENU -->
        <div class="profile-usermenu sidebar-sticky">
            <h5 class="text-primary">
                <spring:message code="label.client"/>
            </h5>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/client/make-report" class="nav-link">
                        <i class="fa fa-home"></i>
                        <spring:message code="submit.report"/>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/client/show-reports">
                        <i class="fa fa-user"></i>
                        <spring:message code="see.all.reports"/>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/client/obtain-digital-signature">
                        <i class="fa fa-check"></i>
                        <spring:message code="obtain.d.s"/>
                    </a>
                </li>


            </ul>
        </div>
        <!-- END MENU -->
    </div>
</div>
















<!-- SIDEBAR USERPIC -->
<%--<div class="profile-userpic">--%>
<%--<img src="../../image/person.png" class="img-responsive portrait" alt="portrait">--%>
<%--</div>--%>
<%--<!-- END SIDEBAR USERPIC -->--%>
<%--<!-- SIDEBAR USER TITLE -->--%>
<%--<div class="profile-usertitle">--%>
<%--<div class="profile-usertitle-name">--%>
<%--{{param.name}}--%>
<%--</div>--%>
<%--<div class="profile-usertitle-job">--%>
<%--{{param.role}}--%>
<%--</div>--%>
<%--</div>--%>
<!-- END SIDEBAR USER TITLE -->