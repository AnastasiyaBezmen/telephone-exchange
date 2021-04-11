<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Телефонная станция<c:if test="${not empty title}"> :: ${title}</c:if></title>
    <style type="text/css">
        body {
            background: #b4ebff
        }
    </style>
</head>
<body>
<div class="header-panel">
    <h1 align="center">Телефонная станция <i>"Будь на связи"</i></h1>
<style type="text/css">
    .header-panel {
        width: 100%;
        height: 100px;
        background-color: #256bff;
        border: 2px solid #152c8b;
        padding: 3px;
        font-size: 1.2em;
        color: #090970;
        font-family: Dialog;
        float: left;
        box-sizing: border-box;
        border-radius: 20px;
    }
</style>
    <c:if test="${not empty title}"><h1>${title}</h1></c:if>
    <jsp:doBody/>
</div>
</body>
</html>


