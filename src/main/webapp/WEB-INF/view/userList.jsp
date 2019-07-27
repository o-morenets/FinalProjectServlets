<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading"><fmt:message key="userList.panel.title"/></div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="user.lastName"/></th>
                        <th><fmt:message key="user.firstName"/></th>
                        <th><fmt:message key="user.email"/></th>
                        <th><fmt:message key="user.username"/></th>
                        <th><fmt:message key="user.speciality.name"/></th>
                        <th><fmt:message key="userList.link.grades"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope[Attributes.USERS]}" var="user">
                        <tr>
                            <td>${user.lastName}</td>
                            <td>${user.firstName}</td>
                            <td>${user.email}</td>
                            <td>${user.username}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${!empty user.speciality}">${user.speciality.name}</c:when>
                                    <c:otherwise>-<fmt:message key="user.speciality.notSelected"/>-</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:if test="${!empty user.speciality}">
                                    <a href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_}${user.id}${Paths.GRADES}"
                                       class="badge badge-warning"><fmt:message key="userList.link.grades"/>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>
