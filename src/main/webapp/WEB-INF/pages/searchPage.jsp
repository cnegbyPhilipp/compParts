<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>Search</title>
</head>
<body>
<a href="<c:url value="/?page=${page}"/>">Home</a>
<form action="<c:url value="/search"/>" method="POST">
    <label for="searchField">Поиск по названию: </label>
    <input type="text" name="searchString" id="searchField">
    <input type="submit" value="Поиск">
</form>

<table>
    <thead>
    <tr>
        <th>Название</th>
        <th>Необходимость для сборки</th>
        <th>Количество</th>
    </tr>
    </thead>
    <c:forEach var="part" items="${resultParts}">
        <tr align="center">
            <td>${part.title}</td>
            <c:if test="${part.necessary}">
                <td>Да</td>
            </c:if>
            <c:if test="${!part.necessary}">
                <td>Нет</td>
            </c:if>
            <td>${part.count}</td>
            <td>
                <a href="/edit/${part.id}">Редактировать</a>
                <a href="/delete/${part.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
