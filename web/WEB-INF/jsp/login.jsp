<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<u:page title="Авторизация">
    <c:if test="${not empty param.message}">
        <p style="color: #dc5274;">${param.message}</p>

    </c:if>
    <c:url var="loginUrl" value="/login.html"/>
    <form action="${loginUrl}" method="post">
        <b>Имя пользователя:</b><br>
        <input type="text" name="login"><br>
        <br>
        <b>Пароль:</b><br>
        <input type="password" name="password"><br>
        <br>
        <button type="submit"><b>Войти</b></button>
        <style>
            button {
                border: 3px solid #152c8b;
                padding: 10px;
                border-radius: 10px;
                font-size: 1em;
                color: #152c8b;
            }

            input {
                border: 3px solid #152c8b;
                padding: 4px;
                border-radius: 7px;
                font-size: 0.7em;
                color: #152c8b;
            }
        </style>
    </form>
</u:page>

