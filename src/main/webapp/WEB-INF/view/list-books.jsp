<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Books</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/clock-style.css" />
</head>
<body>
	
  <div class="wrapper">
        <div class="intro">
            <div class="intro__content">
               <h1>Out all books from DB</h1>

               <div class="control-block">
                   <!-- search form -->
                   <div class="search-form">
                       <form action="processSearchForm" method="POST">
                           <input type="text"  name="searchBox"/>
                           <input type="submit" value="Search"/>
                        </form>
                    </div>
                    <!-- reFill all records -->
                    <input type="button" value="reFillDB"
                    onclick="window.location.href='fillDB'; return false;"
                    class="refill-button"
                    />
                </div>
            </div>
            <!-- clock -->
            <div class="clock-wrapper">
                <div class="clock">
                    <div class="hour"><div class="hr" id="hr"></div></div>
                    <div class="min"><div class="mn" id="mn"></div></div>
                    <div class="sec"><div class="sc" id="sc"></div></div>
                </div>
            </div>
        </div>
        <div class="table-block">
					
			<!-- add  our html table here -->
			<table><tr>
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
				</tr></table>
        </div>

    </div>
	
	<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</body>
</html>