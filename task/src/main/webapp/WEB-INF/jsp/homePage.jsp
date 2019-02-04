<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring5 MVC Hibernate Demo</title>
    <style type="text/css">
        .error {
            color: red;
        }
        table {
            width: 50%;
            border-collapse: collapse;
            border-spacing: 0px;
        }
        table td {
            border: 1px solid #565454;
            padding: 20px;
        }
    </style>
</head>
<body>
<h1>Input Form</h1> for <security:authentication property="principal.username" />

<sec:authorize access="hasRole('ROLE_EXECUTOR')">
    <form:form action="/service" method="post" modelAttribute="service">
        <table>
            <tr>
                <td>Name</td>
                <td>
                    <form:input path="name" /> <br />
                    <form:errors path="name" cssClass="error" />
                </td>
            </tr>
            <tr>
                <td colspan="2"><button type="submit">Submit</button></td>
            </tr>
        </table>
    </form:form>
</sec:authorize>


<h2>Services List</h2>
<table>
    <tr>
        <td><strong>Name</strong></td>
    </tr>
    <c:forEach items="${services}" var="item">
        <tr>
            <td>${item.name}</td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value='/logout' />">Click here to logout</a>
</body>
</html>