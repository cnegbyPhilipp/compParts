<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<html>
<head>
    <title>CompParts</title>
</head>
<body>

<h2>CompParts</h2>

<form action="<c:url value="/search"/>" method="POST" accept-charset="UTF-8">
    <label for="searchField">Поиск по названию: </label>
    <input type="text" name="searchString" id="searchField">
    <input type="submit" value="Поиск">
</form>

<p>
    <c:url value="/" var="url">
        <c:param name="page" value="${page}"/>
        <c:param name="filterTypeName" value="DEFAULT"/>
    </c:url>
    <a href="${url}">Все детали</a>

    <c:url value="/" var="url">
        <c:param name="page" value="${page}"/>
        <c:param name="filterTypeName" value="NECESSARY"/>
    </c:url>
    <a href="${url}">Необходимые детали</a>

    <c:url value="/" var="url">
        <c:param name="page" value="${page}"/>
        <c:param name="filterTypeName" value="OPTIONAL"/>
    </c:url>
    <a href="${url}">Опциональные детали</a>
</p>

<p>
<table>
    <thead>
    <tr>
        <th>Название</th>
        <th>Необходимость для сборки</th>
        <th>Количество</th>
    </tr>
    </thead>
    <c:forEach var="part" items="${partsList}">
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
</p>

<p>
    <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
        <c:url value="/" var="url">
            <c:param name="page" value="${i.index}"/>
            <c:param name="filterTypeName"/>
        </c:url>
        <a href="${url}">${i.index}</a>
    </c:forEach>
</p>

<h3>Можно собрать: ${compsCount} компьютеров(-ов)</h3>
<c:url value="/add" var="add"/>
<h2><a href="${add}">Добавить новую деталь</a></h2>
</body>
</html>