<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ua.training.admission.view.Parameters" %>
<%@ page import="ua.training.admission.i18n.SupportedLocale" %>
<%@ page import="ua.training.admission.view.Attributes" %>

<ul class="navbar-nav mr-3">
    <c:forEach items="${SupportedLocale.values()}" var="locale">
        <li class="nav-item">
            <c:choose>
                <c:when test="${locale.locale == sessionScope[Attributes.USER_LOCALE]}">
                    <a class="nav-link disabled" href="?${Parameters.USER_LOCALE}=${locale.key}">${locale}</a>
                </c:when>
                <c:otherwise>
                    <a class="nav-link active" href="?${Parameters.USER_LOCALE}=${locale.key}">${locale}</a>
                </c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
</ul>
