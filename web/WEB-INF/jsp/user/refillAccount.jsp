<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<u:page title="Пополнение счета: <u>${user.personalInformation.phoneNumber}</u>">
    <c:url var="executeRefillAccountUrl" value="/user/executeRefillAccount.html"/>
    <form action="${executeRefillAccountUrl}" method="post">
        <hr>
        <h3><i>Введите сумму для пополнения счета:</i></h3>
        <input type="number" step="0.01" min="0" placeholder="0,00" name="sumRefill"><b> рублей</b><br>
        <input type="hidden" name="userId" value="${user.id}"><br>
        <input type="hidden" name="accountId" value="${user.account.id}"><br>
        <input type="hidden" name="balance" value="${user.account.balance}"><br>
        <button type="submit"><b>пополнить</b></button>
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