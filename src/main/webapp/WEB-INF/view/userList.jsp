<%@ page import="ua.training.admission.view.Paths" %>
<%@ include file="/WEB-INF/view/parts/header.jsp" %>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading"><fmt:message key="userList.panel.title"/></div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="user.username"/></th>
                        <th><fmt:message key="user.email"/></th>
                        <th><fmt:message key="user.lastName"/></th>
                        <th><fmt:message key="user.firstName"/></th>
                        <th><fmt:message key="user.speciality.name"/></th>
                        <th><fmt:message key="userList.link.grades"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userList}" var="user">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.lastName}</td>
                            <td>${user.firstName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${!empty user.speciality}">${user.speciality.name}</c:when>
                                    <c:otherwise>---</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="/users/${user.id}/grades" class="badge badge-warning">grades</a>
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
