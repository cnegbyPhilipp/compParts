<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:if test="${empty part.title}">
        <title>Добавить новую деталь</title>
    </c:if>
    <c:if test="${!empty part.title}">
        <title>Редактировать</title>
    </c:if>

</head>

<body>
<c:if test="${empty part.title}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty part.title}">
    <c:url value="/edit" var="var"/>
</c:if>

<form action="${var}" method="POST">
    <c:if test="${!empty part.title}">
        <input type="hidden" name="id" value="${part.id}">
    </c:if>

    <label for="title">Название</label>
    <input type="text" name="title" id="title" value="${part.title}">

    <label for="necessary">Необходимость для сборки (true/false)</label>
    <input type="text" name="necessary" id="necessary" value="${part.necessary}">

    <label for="count">Количество</label>
    <input type="text" name="count" id="count" value="${part.count}">

    <c:if test="${!empty part.title}">
        <input type="submit" value="Редактировать">
    </c:if>
    <c:if test="${empty part.title}">
        <input type="submit" value="Добавить">
    </c:if>
</form>
</body>
</html>