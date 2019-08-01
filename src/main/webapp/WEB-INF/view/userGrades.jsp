<%@ page import="ua.training.admission.view.Paths" %>
<%@ page import="ua.training.admission.view.Attributes" %>

<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row justify-content-center">
    <div class="card">
        <div class="card-header">
            <h5>${user.lastName} ${user.firstName}</h5>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col">
                    <fmt:message key="user.speciality.name"/>:
                </div>

                <c:choose>
                    <c:when test="${!empty user.speciality}">
                        <div class="col">
                            <span class="badge badge-info mb-3">${user.speciality.name}</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="user.speciality.notSelected"/>
                        <c:if test="${!isAdmin}">
                            <a href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_}${user.id}${Paths.SPECIALITY}"
                               class="badge badge-warning ml-3"><fmt:message key="button.select"/>
                            </a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>

            <c:if test="${!empty user.speciality}">
                <div class="row">
                    <form action="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_UPDATE_GRADES}"
                          method="post">
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
                                    <c:forEach items="${requestScope[Attributes.USER_SUBJECT_GRADE_LIST]}"
                                               var="userSubjectGrade">
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
                                    <div class="form-row text-center">
                                        <div class="col-12">
                                            <button type="submit" class="btn btn-primary mt-3">
                                                <fmt:message key="button.save"/>
                                            </button>
                                        </div>
                                    </div>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="msg.noSubjects"/>
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="${Parameters.USER_ID}" value="${user.id}"/>
                    </form>
                </div>
            </c:if>
        </div>
        <c:if test="${!isAdmin}">
            <div class="card-footer text-muted">
                <fmt:message key="user.averageGrade"/>
            </div>
        </c:if>
    </div>
</div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>