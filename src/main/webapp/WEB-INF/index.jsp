<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Expenses</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="container w-100 h-100">
	<h1>All Expenses</h1>
	<table class="table table-striped w-100">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Expense</th>
			<th scope="col">Vendor</th>
			<th scope="col">Description</th>
			<th scope="col">Amount</th>
			<th scope="col">Action</th>
		</tr>
		<c:forEach var="oneExpense" items="${expenses}">
			<tr>
				<th scope="row"><c:out value="${oneExpense.id}"></c:out></th>
				<td><a href="/expenses/${oneExpense.id}"><c:out value="${oneExpense.name}"></c:out></a></td>
				<td><c:out value="${oneExpense.vendor}"></c:out></td>
				<td><c:out value="${oneExpense.description}"></c:out></td>
				<td><fmt:formatNumber type="currency" value="${oneExpense.amount}"/></td>
				<td>
				<a href="/expenses/${oneExpense.id}/edit">edit</a>
				<form action="/expenses/${oneExpense.id}" method="post">
    <input type="hidden" name="_method" value="delete">
    <button class="btn btn-danger">delete</button>
</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="container w-25 border border-secondary p-3">
		<h1>New Expense</h1>
		<form:form action="/expenses/create" method="post" modelAttribute="expense">
			<p>
				<form:label path="name">Expense Name</form:label>
				<form:errors path="name" />
				<form:input path="name" />
			</p>
			<p>
				<form:label path="vendor">Vendor</form:label>
				<form:errors path="vendor" />
				<form:textarea path="vendor" />
			</p>
			<p>
				<form:label path="description">Description</form:label>
				<form:errors path="description" />
				<form:input path="description" />
			</p>
			<p>
				<form:label path="amount">Amount</form:label>
				<form:errors path="amount" />
				<form:input path="amount" />
			</p>
			<input type="submit" value="Submit" />
		</form:form>
	</div>
</body>
</html>
