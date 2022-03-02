<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Books</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/clock-style.css" />
</head>
<body>
	
  <form:form method="POST" action="saveLibs" modelAttribute="Libs">
<table>
<tr>
<th>Path</th>
</tr>
<c:forEach items="${Libs.libraries}" var="library" varStatus="tagStatus">
    <tr>
        <td><form:input path="libraries[${tagStatus.index}].path" value="${library.path}" class="input-fld"/></td>
    </tr>
</c:forEach>
</table>
<input type="submit" value="Save" />
</form:form>
  
</body>
</html>