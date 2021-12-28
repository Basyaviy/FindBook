<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Books</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>Test - just out all books in DB</h2>
			<div id="container">
				<div id="content">
			
					<!-- add  our html table here -->
					<table>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Title</th>
							<th>Action</th>
							
							<!-- loop over and print our customers -->
							<c:forEach var="tempBook" items="${books}">
								<!--  construct an "update" link with book id -->
								<c:url var="updateLink" value="/book/showFormForUpdate">
									<c:param name="bookId" value="${tempBook.id}"/>
								</c:url>
								
								<!--  construct a "download" link with book id -->
								<c:url var="downloadLink" value="/book/download">
									<c:param name="bookId" value="${tempBook.id}"/>
								</c:url>
							
							
								<tr>
									<td>${tempBook.firstName}</td>
									<td>${tempBook.lastName}</td>
									<td>${tempBook.bookTitle}</td>
									
									<td>
										<!--  display the update link -->
										<a href="${updateLink}">Update</a>
										|
										<a href="${downloadLink}">Download</a>
									</td>
								</tr>
							</c:forEach>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>