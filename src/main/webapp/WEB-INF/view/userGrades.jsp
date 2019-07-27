<%@ page import="ua.training.admission.view.Paths" %>
<%@ page import="ua.training.admission.view.Attributes" %>

<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<h5>${user.lastName} ${user.firstName}</h5>
<fmt:message key="user.speciality.name"/>:
<c:choose>
    <c:when test="${!empty user.speciality}">
        <span class="badge badge-info">${user.speciality.name}</span>
        <br/>
        <form action="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_UPDATE_GRADES}" method="post">
            <c:choose>
                <c:when test="${!empty requestScope[Attributes.USER_SUBJECT_GRADE_LIST]}">
                    <table>
                        <thead>
                        <tr>
                            <th><fmt:message key="userGrades.table.subject"/></th>
                            <th><fmt:message key="userGrades.table.grade"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope[Attributes.USER_SUBJECT_GRADE_LIST]}" var="userSubjectGrade">
                            <tr>
                                <td>
                                    <label for="subject_${userSubjectGrade.subject.id}">
                                            ${userSubjectGrade.subject.name}
                                    </label>
                                </td>
                                <td>
                                    <input type="number"
                                           id="subject_${userSubjectGrade.subject.id}"
                                           name="subject_${userSubjectGrade.subject.id}"
                                           value="<c:if test="${!empty userSubjectGrade.grade}">${userSubjectGrade.grade}</c:if>"
                                           <c:if test="${!isAdmin}">disabled</c:if>
                                    >
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${isAdmin}">
                        <button type="submit"><fmt:message key="button.save"/></button>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <fmt:message key="msg.noSubjects"/>
                </c:otherwise>
            </c:choose>
            <input type="hidden" name="userId" value="${user.id}"/>
        </form>
    </c:when>
    <c:otherwise>
        <fmt:message key="user.speciality.notSelected"/>
        <c:if test="${!isAdmin}">
            <a href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_}${user.id}${Paths.SPECIALITY}"
               class="badge badge-warning">
                <fmt:message key="button.select"/>
            </a>
        </c:if>
    </c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>