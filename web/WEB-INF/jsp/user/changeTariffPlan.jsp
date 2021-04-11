<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<u:page title="Смена тарифного плана: <u>${user.account.tariffPlan.name}</u>">
    <c:url var="saveChangedTariffPlanUrl" value="/user/saveChangedTariffPlan.html"/>
    <form action="${saveChangedTariffPlanUrl}" method="post">
        <br>
        <h2><i>Выберите новый тарифный план из предложенных:
            <select name="tariffPlanId">
                <c:forEach var="tariffPlan" items="${tariffPlans}">
                    <c:choose>
                        <c:when test="${tariffPlan.id == user.account.tariffPlan.id}">
                            <c:set var="selected" value="selected"/>
                        </c:when>
                        <c:otherwise>
                            <c:remove var="selected"/>
                        </c:otherwise>
                    </c:choose>
                    <option value="${tariffPlan.id}" ${selected}>${tariffPlan.name}</option>
                </c:forEach>
            </select></i></h2>
        <br>
        <br>
        <button type="submit"><b>Подтвердить смену тарифного плана</b></button>
        <input type="hidden" name="accountId" value="${user.account.id}"><br>
        <input type="hidden" name="tariffPlanId" value="${tariffPlan.id}"><br>
        <style>
            button {
                border: 3px solid #152c8b;
                padding: 10px;
                border-radius: 10px;
                font-size: 1em;
                color: #152c8b;
            }

            select {
                border: 3px solid #152c8b;
                padding: 4px;
                border-radius: 7px;
                font-size: 0.7em;
                color: #152c8b;
            }
        </style>
    </form>
</u:page>