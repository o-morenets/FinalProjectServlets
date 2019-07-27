<%@ taglib prefix="w" tagdir="/WEB-INF/tags" %>
<jsp:directive.page isErrorPage="true"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.apache.log4j.Logger" %>

<%! private static final Logger logger = Logger.getLogger("JSP Error Handler Page"); %>
<% logger.error(pageContext.getException()); %>

<%@ include file="/WEB-INF/view/parts/header.jsp" %>
    <!DOCTYPE html>
    <div class="alert alert-danger" role="alert">
        <h3><fmt:message key="title.error.throwable"/></h3>
    </div>
    <div class="card">
        <div class="card-body">
            <%
                pageContext.getException().printStackTrace(new java.io.PrintWriter(out));
            %>
        </div>
    </div>
<%@ include file="/WEB-INF/view/parts/footer.jsp" %>

