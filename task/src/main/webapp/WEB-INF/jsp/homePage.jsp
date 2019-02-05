<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Task for interview</title>
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
<strong>Пользователь: <security:authentication property="principal.username"/> </strong>

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
    <c:forEach items="${mainPageDto.services}" var="item">
        <tr>
            <security:authorize access="hasRole('ROLE_CUSTOMER')">
                <td><a href="/service/${item.id}/order/add"> ${item.name}</a></td>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_EXECUTOR')">
                <td>${item.name}                <a href="/service/${item.id}/remove">Удалить</a></td>
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
                <td><strong>Статус</strong></td>
        </tr>
        <c:forEach items="${mainPageDto.orders}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.service.name}</td>
                <td><strong>${item.status}</strong></td>
            </tr>
        </c:forEach>
    </table>
</security:authorize>

<security:authorize access="hasRole('ROLE_EXECUTOR')">
<h2>Список заказов</h2>
<table>
    <tr>
        <td><strong>Идентификатор</strong></td>
        <td><strong>Услуга</strong></td>
        <td><strong>Инициатор</strong></td>
    </tr>
    <c:forEach items="${mainPageDto.orders}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.service.name}</td>
                <td><strong>${item.status}</strong></td>
                <td>${item.user.login}</td>
                <td>
                    <form action="<c:url value="/orders/process"/>" method="post">
                        <label>
                            <input hidden type="text" name="orderId" value="${item.id}">
                        </label>
                        <button type="submit" name="action" value="apply">Принять</button>
                        <button type="submit" name="action" value="decline">Отказать</button>
                        <sec:csrfInput/>
                    </form>
                </td>
        </tr>
    </c:forEach>
</table>
</security:authorize>
<a href="<c:url value='/logout' />">Выйти</a>
</body>
</html>