<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<u:page title="<b>Личный кабинет пользователя: <i><u>${programUser.personalInformation.lastName}
    ${programUser.personalInformation.firstName} ${programUser.personalInformation.patronymicName}</u></i></b>">

    <form method="get">
    <input type="hidden" name="id" value="${programUser.id}">
    <hr>
    <h1>Тарифный план: <u>"${programUser.account.tariffPlan.name}"</u></h1>
    <c:url var="changeTariffPlanUrl" value="/user/changeTariffPlan.html"/>
    <button formaction="${changeTariffPlanUrl}" type="submit"
            name="changeTariffPlan"><b>Сменить тарифный план</b></button>
    <br>

    <br>
    <hr>
    <h1>Баланс: <u>${programUser.account.balance/100} рублей</u></h1>
    <h2>${programUser.personalInformation.phoneNumber}</h2>
    <c:url var="refillAccountUrl" value="/user/refillAccount.html"/>
    <button formaction="${refillAccountUrl}" type="submit" name="refillAccount"><b>Пополнить баланс</b></button>
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
    </style>
</u:page>
