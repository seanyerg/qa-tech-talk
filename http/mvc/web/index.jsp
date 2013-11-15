<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: seanyerg
  Date: 8/13/12
  Time: 10:17 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form method="POST" action="select.do">
    <label>Pick your coworker:</label>
    <select name="coworker">
	    <c:forEach items="${coworkers}" var="coworker">
		    <option value="${coworker.id}">${coworker.name}</option>
	    </c:forEach>
    </select>
    <input type="submit">
</form>
</body>
</html>