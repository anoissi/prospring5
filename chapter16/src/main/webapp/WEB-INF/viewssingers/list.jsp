<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Singers list</title>
</head>
<body>
	<div>
        <h1>Singer Listing</h1>
        <c:if test="${not empty singers}">
            <table>
                <thead>
                    <tr>
                        <th>First  Name</th>
                        <th>Last  Name</th>
                        <th>Birth  Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${singers}"  var="singer">
                        <tr>
                            <td>${singer.firstName}</td>
                            <td>${singer.lastName}</td>
                            <td><fmt:formatDate value="${singer.birthDate}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>