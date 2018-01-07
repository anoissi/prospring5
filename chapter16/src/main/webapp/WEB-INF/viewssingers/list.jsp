<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="label_singer_list" var="labelSingerList"/>
<spring:message code="label_singer_first_name" var="labelSingerFirstName"/>
<spring:message code="label_singer_last_name" var="labelSingerLastName"/>
<spring:message code="label_singer_birth_date" var="labelSingerBirthDate"/>


<html>
<head>
<title>Singers list</title>
</head>
<body>
	<div>
        <h1>${labelSingerList}</h1>
        <c:if test="${not empty singers}">
            <table>
                <thead>
                    <tr>
                        <th>${labelSingerFirstName}</th>
                        <th>${labelSingerLastName}</th>
                        <th>${labelSingerBirthDate}</th>
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