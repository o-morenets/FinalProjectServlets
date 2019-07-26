<%@ page import="ua.training.admission.view.Paths" %>
<%@ include file="/WEB-INF/view/parts/header.jsp" %>

<h5>${user.lastName} ${user.firstName}</h5>
<fmt:message key="user.speciality.name"/>:

<c:choose>
    <c:when test="${!empty user.speciality}">
        <span class="badge badge-info">${user.speciality.name}</span>
        <br/>

        <form action="${pageContext.request.contextPath}${Paths.USERS_}${Paths.UPDATE_GRADES}" method="post">
            <c:choose>
                <c:when test="${!empty userSubjectGradeDtoList}">
                    <table>
                        <thead>
                        <tr>
                            <th><fmt:message key="userGrades.table.subject"/></th>
                            <th><fmt:message key="userGrades.table.grade"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userSubjectGradeDtoList}" var="userSubjectGradeDto">
                            <tr>
                                <td>
                                    <label for="subject_${userSubjectGradeDto.subjectId}">
                                            ${userSubjectGradeDto.subjectName}
                                    </label>
                                </td>
                                <td>
                                    <input type="number"
                                           id="subject_${userSubjectGradeDto.subjectId}"
                                           name="subject_${userSubjectGradeDto.subjectId}"
                                           value="<c:if test="${userSubjectGradeDto.grade}">
                                                ${userSubjectGradeDto.grade}</c:if>"
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
            <a href="${pageContext.request.contextPath}${Paths.USERS_}${user.id}${Paths.SELECT_SPEC}"
               class="badge badge-warning">
                <fmt:message key="button.select"/>
            </a>
        </c:if>
    </c:otherwise>
</c:choose>

<%@ include file="/WEB-INF/view/parts/footer.jsp" %>