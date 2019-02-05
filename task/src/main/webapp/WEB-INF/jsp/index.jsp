<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head></head>

<body>
<h1>Login</h1>

<form id="login" name='f' action="/login" method='POST'>

    <table>
        <tr>
            <td>Логин:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="Войти" /></td>
        </tr>
    </table>
    <sec:csrfInput/>

</form>
</body>
</html>