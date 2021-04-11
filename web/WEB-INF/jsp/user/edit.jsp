<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="Редактирование пользователя <u><i>${user.personalInformation.lastName}</i></u>"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового пользователя"/>
    </c:otherwise>
</c:choose>
<u:page title="${title}">
    <c:url var="userSaveUrl" value="/user/save.html"/>
    <form action="${userSaveUrl}" method="post">
        <c:if test="${not empty user}">
            <input type="hidden" name="id" value="${user.id}">
            <input type="hidden" name="personalInfoId" value="${user.personalInformation.id}">
            <input type="hidden" name="addressId" value="${user.personalInformation.address.id}">
            <input type="hidden" name="accountId" value="${user.account.id}">
        </c:if>
        Фамилия:<input type="text" name="lastName" value="${user.personalInformation.lastName}"><br>
        Имя:<input type="text" name="firstName" value="${user.personalInformation.firstName}"><br>
        Отчество:<input type="text" name="patronymicName" value="${user.personalInformation.patronymicName}"><br>
        <hr>
        Город:<input type="text" name="city" value="${user.personalInformation.address.city}"><br>
        Улица:<input type="text" name="street" value="${user.personalInformation.address.street}"><br>
        Дом:<input type="number" name="houseNumber" value="${user.personalInformation.address.houseNumber}"><br>
        Корпус:<input type="number" name="block" value="${user.personalInformation.address.block}"><br>
        Квартира:<input type="number" name="flat" value="${user.personalInformation.address.flat}"><br>
        <hr>
        Логин:<input type="text" name="login" value="${user.login}"><br>
        Пароль:<input type="password" name="password" value="${user.password}"><br>
        Роль:<select name="role">
        <c:forEach var="role" items="${roles}">
            <c:choose>
                <c:when test="${role == user.role}">
                    <c:set var="selected" value="selected"/>
                </c:when>
                <c:otherwise>
                    <c:remove var="selected"/>
                </c:otherwise>
            </c:choose>
            <option value="${role}" ${selected}>${role.name}</option>
        </c:forEach>
    </select>
        <br>
        Телефонный номер:<input type="text" name="phoneNumber" value="${user.personalInformation.phoneNumber}"><br>
        Тариф:<select name="tariffPlanId">
        <c:forEach var="tariffPlan" items="${tariffPlans}">
            <c:choose>
                <c:when test="${tariffPlan.name.equals(user.account.tariffPlan.name)}">
                    <c:set var="selected" value="selected"/>
                </c:when>
                <c:otherwise>
                    <c:remove var="selected"/>
                </c:otherwise>
            </c:choose>
            <option value="${tariffPlan.id}" ${selected}>${tariffPlan.name}</option>
        </c:forEach>
    </select>
        <br>
        <br>
        <button type="submit"><b>Сохранить</b></button>
        <input type="reset"/>
        <c:url var="userListUrl" value="/user/list.html"/>
        <br>
        <br>
        <hr>
        <button formaction="${userListUrl}" type="submit" formmethod="get"
                name="userList"><b>Вернуться к списку пользователей</b></button>
        <style>
            input, select, button {
                border: 2px solid #152c8b;
                padding: 4px;
                border-radius: 7px;
                font-size: 1em;
                color: #152c8b;
            }
        </style>
    </form>
</u:page>
