<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<u:page title="Список пользователей">
    <c:url var="userDeleteUrl" value="/user/delete.html"/>
    <form method="get">
        <table border="1" cellspacing="0" cellpadding="8">
            <tr bgcolor="#256bff">
                <td></td>
                <td><b>Фамилия</b></td>
                <td><b>Имя</b></td>
                <td><b>Отчество</b></td>
                <td><b>Тариф</b></td>
                <td><b>Баланс</b></td>
                <td><b>Контактный номер</b></td>
                <td><b>Логин</b></td>
                <td><b>Роль пользователя</b></td>
            </tr>
            <c:forEach var="user" items="${users}">
                <c:url var="userEditUrl" value="/user/edit.html">
                    <c:param name="id" value="${user.id}"/>
                    <c:param name="personalInfoId" value="${user.personalInformation.id}"/>
                    <c:param name="addressId" value="${user.personalInformation.address.id}"/>
                    <c:param name="accountId" value="${user.account.id}"/>
                </c:url>
                <tr bgcolor="white">
                    <td><input type="checkbox" name="id" value="${user.id}"></td>
                    <td><a href="${userEditUrl}">${user.personalInformation.lastName}</a></td>
                    <td>${user.personalInformation.firstName}</td>
                    <td>${user.personalInformation.patronymicName}</td>
                    <td>${user.account.tariffPlan.name}</td>
                    <td>${user.account.balance/100}</td>
                    <td>${user.personalInformation.phoneNumber}</td>
                    <td>${user.login}</td>
                    <td>${user.role.name}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:url var="userDeleteUrl" value="/user/delete.html"/>
        <button formaction="${userDeleteUrl}" type="submit" formmethod="post" name="userDelete"><b>Удалить пользователя</b></button>
        <br>
        <br>
        <c:url var="userEditUrl" value="/user/edit.html"/>
        <button formaction="${userEditUrl}" type="submit" name="userEdit"><b>Добавить нового пользователя</b></button>
        <br>
        <br>
        <c:url var="userBlockUrl" value="/user/block.html"/>
        <button formaction="${userBlockUrl}" type="submit" name="userBlock"><b>Просмотреть неоплаченные счета</b></button>
        <hr>
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

