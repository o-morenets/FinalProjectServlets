<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row">
    <div class="mx-auto">
        <h2>
            <fmt:message key="homePage.message"/>
        </h2>
        <c:if test="${!empty principal}">
            <h3>
                <c:choose>
                    <c:when test="${isAdmin}">
                        <fmt:message key="homePage.adminPage"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="homePage.userPage"/>
                    </c:otherwise>
                </c:choose>
            </h3>
        </c:if>
    </div>
</div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>
