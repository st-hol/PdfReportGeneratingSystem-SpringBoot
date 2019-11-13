<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>



<div class="col-md-3">
    <div class="profile-sidebar position-fixed">
        <h5 class="text-primary">
            <spring:message code="label.inspector"/>
        </h5>
        <!-- SIDEBAR MENU -->
        <div class="profile-usermenu sidebar-sticky">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/inspector/show-reports" class="nav-link active">
                        <i class="fa fa-home"></i>
                        <spring:message code="check.reports"/>
                    </a>
                </li>
            </ul>
        </div>
        <!-- END MENU -->
    </div>
</div>