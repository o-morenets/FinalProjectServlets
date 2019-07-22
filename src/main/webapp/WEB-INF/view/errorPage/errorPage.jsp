<jsp:directive.page isErrorPage="true" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.apache.log4j.Logger" %>

<%! private static final Logger logger = Logger.getLogger("JSP Error Handler Page"); %>
<% logger.error(pageContext.getException());
%>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<!DOCTYPE html>
<div align="center">
    <div align="center" style="max-width: 1024px">
        <h3><fmt:message key="error"/></h3>
    </div>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>