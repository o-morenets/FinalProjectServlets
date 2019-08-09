<%@ page import="ua.training.admission.view.Paths" %>
<%@include file="security.jsp" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand"
       href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.HOME}">
        <img alt="" src="${pageContext.request.contextPath}/img/book.png">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.HOME}">
                    <fmt:message key="admission.homePage"/>
                </a>
            </li>
            <c:if test="${!empty principal}">
                <c:choose>
                    <c:when test="${isAdmin}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS}">
                                <fmt:message key="menu.admin.grades"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_PASS_GRADE}">
                                <fmt:message key="menu.admin.rating"/>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_}${principal.id}${Paths.PROFILE}">
                                <fmt:message key="menu.user.profile"/>
                                <c:if test="${!empty principal.message && !empty principal.message.entered}">
                                    <span class="badge badge-pill badge-danger">1</span>
                                </c:if>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </ul>

        <c:choose>
            <c:when test="${!empty principal}">
                <div class="navbar-text mr-3">
                    <b>${principal.lastName} ${principal.firstName}</b> [${principal.username}]
                </div>
                <c:choose>
                    <c:when test="${isAdmin}">
                        <span class="badge badge-danger"><fmt:message key="menu.role.admin"/></span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge badge-info"><fmt:message key="menu.role.user"/></span>
                    </c:otherwise>
                </c:choose>
                <form method="post"
                      action="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.LOGOUT}">
                    <button class="btn btn-secondary ml-3" type="submit">
                        <fmt:message key="menu.logout"/>
                    </button>
                </form>
            </c:when>
            <c:otherwise>
                <ul class="navbar-nav mr-3">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.SIGNUP}">
                            <fmt:message key="menu.signup"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary" role="button"
                           href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.LOGIN}">
                            <fmt:message key="menu.login"/>
                        </a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>

        <%@ include file="lang.jsp" %>
    </div>
</nav>
