<%@ page import="ua.training.admission.view.Attributes" %>

<c:set var="principal" value="${sessionScope[Attributes.PRINCIPAL]}"/>
<c:set var="isAdmin" value="${principal.isAdmin()}"/>