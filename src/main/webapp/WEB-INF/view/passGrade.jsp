<%@ include file="/WEB-INF/view/parts/header.jsp" %>
<div class="row">
    <div class="mx-auto">
        <form method="post"
              action="${pageContext.request.contextPath}${requestScope[Attributes.SERVLET_PATH_API]}${Paths.USERS_SEND_MESSAGES}">
            <div class="form-group-row">
                <label for="passGrade">
                    <fmt:message key="form.control.passGrade"/>
                </label>
                <input id="passGrade" type="number" name="passGrade">
            </div>
            <div class="form-group-row">
                <button type="submit" class="btn btn-primary mt-3">
                    <fmt:message key="button.sendMessages"/>
                </button>
            </div>
        </form>
    </div>
</div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>
