<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
body {
	font-family: 'Nanum Gothic';
	background-color: #e0e4e8;
}

h2 {
	font-weight: bold;
	color: #007bff;
	margin-bottom: 1.5rem;
	font-weight: 600;
}

button.btn-primary {
	margin: 0.5rem;
	padding: 0.6rem 1.4rem;
	font-size: 1rem;
	background: #4a90e2;
	color: #fff;
	border: none;
	border-radius: 50px;
	transition: transform 0.2s, box-shadow 0.2s;
}

button.btn-primary:hover {
	transform: translateY(-3px);
	box-shadow: 0 4px 10px rgba(0, 123, 255, 0.3);
}

button.btn-outline-primary {
	margin: 0.5rem;
	padding: 0.6rem 1.4rem;
	font-size: 1rem;
	border-radius: 50px;
}

button.btn-success {
	margin: 0.5rem;
	padding: 0.6rem 1.4rem;
	font-size: 1rem;
	color: #fff;
	border: none;
	border-radius: 50px;
	transition: transform 0.2s, box-shadow 0.2s;
}

button.btn-success:hover {
	transform: translateY(-3px);
	box-shadow: 0 4px 10px rgba(0, 123, 255, 0.3);
}

.input-group-text {
	background-color: #e9ecef;
	font-weight: bold;
}

input[type="text"], input[type="number"], select, input[type="date"] {
	border: 1px solid #ced4da;
	border-radius: 4px;
}

#list {
	margin-top: 2rem;
	background: #f9f9f9;
	padding: 1rem;
	border-radius: 8px;
	color: #333;
	font-size: 0.9rem;
	box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
}

.modal-title {
	text-align: center;
	font-weight: bold;
	width: 100%;
}

[readonly] {
	background-color: lightgray;
}
</style>
