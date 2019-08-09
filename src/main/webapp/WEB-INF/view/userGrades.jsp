<%@ page import="ua.training.admission.view.Paths" %>
<%@ page import="ua.training.admission.view.Attributes" %>

<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row justify-content-center">
    <div>
        <c:if test="${isAdmin}">
            <div class="row">
                <div class="col">
                    <div class="alert alert-dark" role="alert">
                        <h5>${user.lastName} ${user.firstName}</h5>
                        <h6>${user.email}</h6>
                    </div>
                </div>
            </div>
        </c:if>
        <div>
            <div class="row">
                <div class="col">
                    <div class="card">
                        <div class="card-header">
                            <fmt:message key="card.grades"/>
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
                                                    <c:forEach
                                                            items="${requestScope[Attributes.USER_SUBJECT_GRADE_LIST]}"
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
                        <c:if test="${!isAdmin && !empty user.speciality}">
                            <div class="card-footer text-muted">
                                <fmt:message key="user.averageGrade"/>: ${user.message.averageGrade}
                            </div>
                        </c:if>
                    </div>
                    <c:if test="${!isAdmin && !empty user.message}">
                        <c:choose>
                            <c:when test="${user.message.entered}">
                                <div class="alert alert-success" role="alert">
                                    <fmt:message key="user.message.entered"/>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-danger" role="alert">
                                    <fmt:message key="user.message.notEntered"/>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>