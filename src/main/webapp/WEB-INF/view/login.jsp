<%@ page import="ua.training.admission.view.Paths" %>

<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row justify-content-center">
    <div class="col-sm-8 col-md-6">
        <div class="card">
            <div class="card-header">
                <h5><fmt:message key="card.login.header"/></h5>
            </div>
            <div class="card-body">
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
                        <form class="needs-validation" method="post" novalidate
                              action="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.LOGIN}">
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
                                           name="${Parameters.USERNAME}">
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
                                           name="${Parameters.PASSWORD}">
                                </div>
                            </div>
                            <div class="form-group-row">
                                <div class="col">
                                    <div class="row justify-content-between">
                                        <div class="col-8 mt-2 checkbox">
                                            <label>
                                                <input type="checkbox" name='${Parameters.REMEMBER_ME}'>
                                                <fmt:message key="form.control.rememberMe"/>
                                            </label>
                                        </div>
                                        <div class="col-4 mt-2">
                                            <p class="text-right">
                                                <a href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.SIGNUP}">
                                                    <fmt:message key="form.control.signup"/></a></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group-row">
                                <div class="col-12 mt-3 text-center">
                                    <button id="btnSubmit" type="submit" class="btn btn-primary">
                                        <fmt:message key="form.control.login"/>
                                    </button>
                                </div>
                            </div>
                            <input type="hidden" name="${Parameters.REDIRECT_ID}"
                                   value="${requestScope[Parameters.REDIRECT_ID]}">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>