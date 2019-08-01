<%@ page import="ua.training.admission.view.Paths" %>
<%@ page import="ua.training.admission.view.Attributes" %>

<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">${user.lastName}: <@s.message "card.speciality.title"/></div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_UPDATE_SPEC}"
                      method="post">

                    <div class="accordion" id="accordionSpecialities">
                        <div class="card">
                            <div class="card-header" id="headingNone">
                                <h2 class="mb-0">
                                    <button class="btn btn-link" type="button" data-toggle="collapse"
                                            data-target="#collapseNone"
                                            aria-expanded="false" aria-controls="collapseNone">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="specRadios"
                                                   value="-1"
                                                   id="spec_none"
                                                   checked>
                                            <label class="form-check-label" for="spec_none">
                                                ---
                                            </label>
                                        </div>
                                    </button>
                                </h2>
                            </div>
                            <div id="collapseNone" class="collapse show" aria-labelledby="headingNone"
                                 data-parent="#accordionSpecialities">
                            </div>
                        </div>

                        <c:forEach items="${requestScope[Attributes.SPECIALITIES]}" var="speciality">
                            <div class="card">
                                <div class="card-header" id="heading_${speciality.id}">
                                    <h2 class="mb-0">
                                        <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                                data-target="#collapse_${speciality.id}" aria-expanded="false"
                                                aria-controls="collapse_${speciality.id}">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="specRadios"
                                                       value="${speciality.id}"
                                                       id="spec_${speciality.id}">
                                                <label class="form-check-label" for="spec_${speciality.id}">
                                                        ${speciality.name}
                                                </label>
                                            </div>
                                        </button>
                                    </h2>
                                </div>
                                <div id="collapse_${speciality.id}" class="collapse"
                                     aria-labelledby="heading_${speciality.id}"
                                     data-parent="#accordionSpecialities">
                                    <div class="card-body">
                                        <c:forEach items="${speciality.subjects}" var="subject">
                                            <table>
                                                <tr>
                                                    <td>
                                                            ${subject.name}
                                                    </td>
                                                </tr>
                                            </table>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <input type="hidden" name="${Parameters.USER_ID}" value="${user.id}"/>
                    <div class="col-12 mt-3 text-center">
                        <button class="btn btn-primary" type="submit">
                            <fmt:message key="button.save"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>