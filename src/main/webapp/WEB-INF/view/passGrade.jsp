<%@ page import="ua.training.admission.view.Constants" %>

<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row">
    <div class="mx-auto">
        <div class="card">
            <div class="card-body">
                <form method="post" novalidate
                      action="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_SEND_MESSAGES}">
                    <div class="form-group-row">
                        <label for="passGrade">
                            <fmt:message key="form.control.passGrade"/>
                        </label>
                        <input class="form-control <c:if test="${!empty passGradeError}">is-invalid</c:if>"
                               id="passGrade" type="number" min="${Constants.GRADE_MIN}" max="${Constants.GRADE_MAX}"
                               step="any" name="passGrade">
                        <c:if test="${not empty passGradeError}">
                            <div class="invalid-feedback">
                                <fmt:message key="${passGradeError}"/>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group-row">
                        <button type="submit" class="btn btn-primary mt-3">
                            <fmt:message key="button.sendMessages"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>
