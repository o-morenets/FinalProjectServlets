<fmt:setBundle basename="${sessionScope[Attributes.BUNDLE_FILE]}"/>
<%@ page import="ua.training.admission.view.Paths" %>
<%@include file="security.jsp" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href=".${Paths.HOME}"><img alt="" src="${pageContext.request.contextPath}/img/book.png"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href=".${Paths.HOME}"><fmt:message key="admission.homePage"/></a>
            </li>
            <c:if test="${user}">
                <%--            <#if isAdmin>--%>
                <li class="nav-item">
                    <a class="nav-link" href=".${Paths.USERS}">Grades</a>
                </li>
                <%--            <#else>--%>
                <li class="nav-item">
                    <a class="nav-link" href=".${Paths.USER_PROFILE}"><fmt:message key="menu.user.profile"/></a>
                </li>
                <%--        </#if>--%>
            </c:if>
        </ul>

        <c:choose>
            <c:when test="${user}">
                <div class="navbar-text mr-3">${user}</div>
            </c:when>
            <c:otherwise>
                <ul class="navbar-nav mr-3">
                    <li class="nav-item">
                        <a class="nav-link" href=".${Paths.SIGNUP}"><fmt:message key="menu.signup"/></a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>

        <%--    Logout    --%>
        <c:choose>
            <c:when test="${user}">
                <form action=".${Paths.LOGOUT}" method="post">
                    <button class="btn btn-secondary" type="submit">
                        <fmt:message key="menu.logout"/>
                    </button>
                </form>
            </c:when>
            <c:otherwise>
                <a class="btn btn-primary" href=".${Paths.LOGIN}" role="button"><fmt:message key="menu.login"/></a>
            </c:otherwise>
        </c:choose>

        <%@ include file="lang.jsp" %>
    </div>
</nav>
