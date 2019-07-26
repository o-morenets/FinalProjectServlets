<%@ page import="ua.training.admission.view.Paths" %>

<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row justify-content-center">
    <div class="col-6">
        <c:if test="${param.logout == true}">
            <div class="row">
                <div class="col">
                    <div class="alert alert-primary" role="alert">
                        <fmt:message key="form.alert.logout"/>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.error == true}">
            <div class="row">
                <div class="col">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="form.alert.error"/>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.authorized == true}">
            <div class="row">
                <div class="col">
                    <div class="alert alert-warning" role="alert">
                        <fmt:message key="form.alert.authorized"/>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="row">
            <div class="col">
                <form class="needs-validation" novalidate action="${pageContext.request.contextPath}${Paths.LOGIN}" method="post">
                    <div class="form-group-row">
                        <label class="control-label"
                               for="inputUsername"><fmt:message key="user.username"/></label>
                        <div class="input-group mb-2 mr-sm-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">@</div>
                            </div>
                            <input type="text"
                                   class="form-control"
                                   id="inputUsername"
                                   placeholder="<fmt:message key="user.username"/>"
                                   required
                                   autofocus
                                   name="username">
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
                                   class="form-control"
                                   id="inputPassword"
                                   placeholder="<fmt:message key="user.password"/>"
                                   required
                                   name="password">
                        </div>
                    </div>
                    <div class="form-group-row">
                        <div class="col-sm-offset-2 col-12">
                            <div class="row justify-content-between">
                                <div class="col-md-7 mt-2 checkbox">
                                    <label>
                                        <input type="checkbox" name='remember-me'>
                                        <fmt:message key="form.control.rememberMe"/>
                                    </label>
                                </div>
                                <div class="col-md-5 mt-2">
                                    <p class="text-right"><a href="${Paths.SIGNUP}">
                                        <fmt:message key="form.control.signup"/></a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group-row">
                        <div class="col-sm-offset-2 col-sm-6 mt-2">
                            <button id="btnSubmit" type="submit" class="btn btn-success">
                                <fmt:message key="form.control.login"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>