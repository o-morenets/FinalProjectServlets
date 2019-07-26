<%@ page import="ua.training.admission.view.Paths" %>

<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row justify-content-center">
    <div class="col-6">
        <div class="row">
            <div class="col">
                <form class="needs-validation" novalidate
                      action="${pageContext.request.contextPath}${Paths.SIGNUP}" method="post">
                    <div class="form-group-row">
                        <label class="control-label"
                               for="inputUsername"><fmt:message key="user.username"/></label>
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">@</div>
                            </div>
                            <input type="text"
                                   class="form-control <c:if test="${!empty usernameError}">is-invalid</c:if>"
                                   id="inputUsername"
                                   placeholder="<fmt:message key="user.username"/>"
                                   value="<c:if test="${!empty user}">${user.username}</c:if>"
                                   required
                                   autofocus
                                   name="username">
                            <c:if test="${not empty usernameError}">
                                <div class="invalid-feedback">
                                    <fmt:message key="${usernameError}"/>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group-row">
                        <label class="control-label"
                               for="inputPassword"><fmt:message key="user.password"/></label>
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">#</div>
                            </div>
                            <input type="password"
                                   class="form-control <c:if test="${!empty passwordError}">is-invalid</c:if>"
                                   id="inputPassword"
                                   placeholder="<fmt:message key="user.password"/>"
                                   required
                                   name="password">
                            <c:if test="${!empty passwordError}">
                                <div class="invalid-feedback">
                                    <fmt:message key="${passwordError}"/>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group-row">
                        <label class="control-label"
                               for="inputPasswordRetype"><fmt:message key="form.control.passwordRetype"/></label>
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">#</div>
                            </div>
                            <input type="password"
                                   class="form-control <c:if test="${!empty password2Error}">is-invalid</c:if>"
                                   id="inputPasswordRetype"
                                   placeholder="<fmt:message key="form.control.passwordRetype"/>"
                                   required
                                   name="password2">
                            <c:if test="${!empty password2Error}">
                                <div class="invalid-feedback">
                                    <fmt:message key="${password2Error}"/>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group-row">
                        <label class="control-label"
                               for="inputEmail"><fmt:message key="user.email"/></label>
                        <input type="email"
                               class="form-control <c:if test="${!empty emailError}">is-invalid</c:if>"
                               id="inputEmail"
                               placeholder="<fmt:message key="user.email"/>"
                               value="<c:if test="${!empty user}">${user.email}</c:if>"
                               required
                               name="email">
                        <c:if test="${!empty emailError}">
                            <div class="invalid-feedback">
                                <fmt:message key="${emailError}"/>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group-row">
                        <label class="control-label"
                               for="inputFirstName"><fmt:message key="user.firstName"/></label>
                        <input type="text"
                               class="form-control <c:if test="${!empty firstNameError}">is-invalid</c:if>"
                               id="inputFirstName"
                               placeholder="<fmt:message key="user.firstName"/>"
                               value="<c:if test="${!empty user}">${user.firstName}</c:if>"
                               required
                               name="firstName">
                        <c:if test="${!empty firstNameError}">
                            <div class="invalid-feedback">
                                <fmt:message key="form.invalid.firstName"/>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group-row">
                        <label class="control-label"
                               for="inputLastName"><fmt:message key="user.lastName"/></label>
                        <input type="text"
                               class="form-control <c:if test="${!empty lastNameError}">is-invalid</c:if>"
                               id="inputLastName"
                               placeholder="<fmt:message key="user.lastName"/>"
                               value="<c:if test="${!empty user}">${user.lastName}</c:if>"
                               required
                               name="lastName">
                        <c:if test="${!empty lastNameError}">
                            <div class="invalid-feedback">
                                <fmt:message key="form.invalid.lastName"/>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group-row">
                        <div class="col-sm-offset-2 col-sm-6 mt-2">
                            <button id="btnSubmit" type="submit" class="btn btn-success">
                                <fmt:message key="form.control.signup"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>