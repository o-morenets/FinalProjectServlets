<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <fmt:message key="card.userList.title"/>
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message key="user.lastName"/></th>
                        <th><fmt:message key="user.firstName"/></th>
                        <th><fmt:message key="user.email"/></th>
                        <th><fmt:message key="user.username"/></th>
                        <th><fmt:message key="user.speciality.name"/></th>
                        <th><fmt:message key="userList.link.grades"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope[Attributes.USERS]}" var="user">
                        <tr>
                            <td>${user.lastName}</td>
                            <td>${user.firstName}</td>
                            <td>${user.email}</td>
                            <td>${user.username}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${!empty user.speciality}">${user.speciality.name}</c:when>
                                    <c:otherwise>-<fmt:message key="user.speciality.notSelected"/>-</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:if test="${!empty user.speciality}">
                                    <a href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_}${user.id}${Paths.GRADES}"
                                       class="badge badge-warning"><fmt:message key="userList.link.grades"/>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="row justify-content-center mt-3">
            <div class="col-6">
                <nav aria-label="Navigation for users">
                    <ul class="pagination">
                        <c:if test="${currentPage != 1}">
                            <li class="page-item">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS}?recordsPerPage=${recordsPerPage}&currentPage=${currentPage - 1}">
                                    <fmt:message key="pagination.previous"/>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="page-item active">
                                        <a class="page-link">${i} <span class="sr-only">(current)</span></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS}?recordsPerPage=${recordsPerPage}&currentPage=${i}">
                                                ${i}
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${currentPage lt noOfPages}">
                            <li class="page-item">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS}?recordsPerPage=${recordsPerPage}&currentPage=${currentPage + 1}">
                                    <fmt:message key="pagination.next"/>
                                </a>
                            </li>
                        </c:if>
                        <li>
                            <form action="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS}?recordsPerPage=${recordsPerPage}&currentPage=${currentPage}">
                                <input type="hidden" name="currentPage" value="1">
                                <div class="form-row align-items-center">
                                    <div class="col-auto">
                                        <label class="ml-2" for="records"><fmt:message
                                                key="pagination.recordsPerPage"/></label>
                                        <select class="my-1 mr-2" id="records" name="recordsPerPage">
                                            <option value="5">5</option>
                                            <option value="10" selected>10</option>
                                            <option value="15">15</option>
                                        </select>
                                    </div>
                                    <div class="col-auto">
                                        <button type="submit" class="btn btn-primary"><fmt:message
                                                key="button.refresh"/></button>
                                    </div>
                                </div>
                            </form>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>
