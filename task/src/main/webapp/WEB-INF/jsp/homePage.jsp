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

<h2>Список услуг</h2>
<table>
    <tr>
        <td><strong>Наименование</strong></td>
    </tr>
    <c:forEach items="${services}" var="item">
        <tr>
            <security:authorize access="hasRole('ROLE_CUSTOMER')">
                <td><a href="/service/${item.id}/order/add"> ${item.name}</a></td>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_EXECUTOR')">
                <td>${item.name}                <a href="/service/remove/${item.id}">Удалить</a></td>
            </security:authorize>
        </tr>
    </c:forEach>
</table>

<security:authorize access="hasRole('ROLE_CUSTOMER')">
    <h2>Список заказов</h2>
    <table>
        <tr>
            <td><strong>Идентификатор</strong></td>
            <td><strong>Услуга</strong></td>
            <td><strong>Инициатор</strong></td>
        </tr>
        <c:forEach items="${orders}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.service.name}</td>
                <td>${id.user.name}</td>
                <td><a href="/orders/${item.id}/apply">Принять</a> / <a href="/orders/${item.id}/decline"></a></td>
            </tr>
        </c:forEach>
    </table>
</security:authorize>
<a href="<c:url value='/logout' />">Выйти</a>
</body>
</html>