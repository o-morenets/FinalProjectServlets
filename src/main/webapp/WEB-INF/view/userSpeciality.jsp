<%@ include file="/WEB-INF/view/parts/header.jsp" %>

${user.lastName} - <fmt:message key="selectSpec.selectSpeciality"/>

<form action="/users/updateSpec" method="post">
    <div class="form-check">
        <input class="form-check-input" type="radio" name="specRadios" value="-1" id="spec_none" checked>
        <label class="form-check-label" for="spec_none">
            ---
        </label>
    </div>
    <c:forEach items="${requestScope[Attributes.SPECIALITIES]}" var="speciality">
        <div class="form-check">
            <input class="form-check-input" type="radio" name="specRadios" value="${speciality.id}"
                   id="spec_${speciality.id}">
            <label class="form-check-label" for="spec_${speciality.id}">
                    ${speciality.name}
            </label>
        </div>
    </c:forEach>
    <input type="hidden" name="userId" value="${user.id}"/>
    <button type="submit"><@s.message "button.save"/></button>
</form>

<%@ include file="/WEB-INF/view/parts/footer.jsp" %>