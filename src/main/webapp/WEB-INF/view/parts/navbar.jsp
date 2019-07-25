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
            <c:if test="${!empty principal}">
                <c:choose>
                    <c:when test="${isAdmin}">
                        <li class="nav-item">
                            <a class="nav-link" href=".${Paths.USERS}"><fmt:message key="menu.admin.grades"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href=".${Paths.USERS_}${principal.id}/grades"><fmt:message key="menu.user.profile"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </ul>

        <c:choose>
            <c:when test="${!empty principal}">
                <div class="navbar-text mr-3">${principal.username}</div>
                <form action=".${Paths.LOGOUT}" method="post">
                    <button class="btn btn-secondary" type="submit">
                        <fmt:message key="menu.logout"/>
                    </button>
                </form>
            </c:when>
            <c:otherwise>
                <ul class="navbar-nav mr-3">
                    <li class="nav-item">
                        <a class="nav-link" href=".${Paths.SIGNUP}"><fmt:message key="menu.signup"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary" href=".${Paths.LOGIN}" role="button"><fmt:message
                                key="menu.login"/></a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>

        <%@ include file="lang.jsp" %>
    </div>
</nav>
