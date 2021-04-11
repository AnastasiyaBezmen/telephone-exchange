<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<u:page title="Список пользователей">
    <c:url var="userSaveBlockUrl" value="/user/saveBlock.html"/>
    <form action="${userSaveBlockUrl}" method="post">
        <table border="1" cellspacing="0" cellpadding="8">
            <tr bgcolor="#256bff">
                <td></td>
                <td><b>Фамилия</b></td>
                <td><b>Имя</b></td>
                <td><b>Отчество</b></td>
                <td><b>Тариф</b></td>
                <td><b>Баланс</b></td>
                <td><b>Контактный номер</b></td>
                <td><b>Статус "заблокирован"</b></td>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr bgcolor="white">
                    <td><input type="checkbox" name="id" value="${user.id}"></td>
                    <td>${user.personalInformation.lastName}</td>
                    <td>${user.personalInformation.firstName}</td>
                    <td>${user.personalInformation.patronymicName}</td>
                    <td>${user.account.tariffPlan.name}</td>
                    <td>${user.account.balance/100}</td>
                    <td>${user.personalInformation.phoneNumber}</td>
                    <td>${user.blocked}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <button type="submit"><b>Заблокировать пользователя</b></button>
        <br>
        <br>
        <br>
        <br>
        <c:url var="userListUrl" value="/user/list.html"/>
        <button formaction="${userListUrl}" type="submit" formmethod="get"
                name="userList"><b>Вернуться к списку пользователей</b></button>
        <c:url var="loginUrl" value="/login.html"/>
        <button formaction="${loginUrl}" type="submit" formmethod="get" name="login"><b>Выйти</b></button>
        <style>
            button {
                border: 3px solid #152c8b;
                padding: 10px;
                border-radius: 10px;
                font-size: 1em;
                color: #152c8b;
            }

            table {
                border: 3px solid #152c8b;
                padding: 2px;
                border-radius: 7px;
                font-size: 1.2em;
                color: #090970;
            }
        </style>
    </form>
</u:page>
