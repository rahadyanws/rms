<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="rms" uri="/WEB-INF/tags/link.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">

<title>RMS</title>
<meta name="description" content="Index">
<meta name="author" content="Mitrais">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<rms:link type="stylesheet" href="css/styles.css?v=1.0" />

<!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
  <![endif]-->
</head>

<body>
	<div class="demo-layout-transparent mdl-layout mdl-js-layout">
		<header class="mdl-layout__header mdl-layout__header--transparent">
			<div class="mdl-layout__header-row mdl-color--primary">
				<!-- Title -->
				<span class="mdl-layout-title"><a href="/rms-servlet-web"><img
						src="/rms-servlet-web/img/logo.png" style="width: 20%" alt="RMS"></a></span>
				<!-- Add spacer, to align navigation to the right -->
				<div class="mdl-layout-spacer"></div>
				<!-- Navigation -->
				<nav class="mdl-navigation">
					<a class="mdl-navigation__link" href="/rms-servlet-web/users/list">User</a>

					<c:choose>
						<c:when test="${user != null}">
							<a class="mdl-navigation__link" href="/rms-servlet-web/dashboard">Dashboard</a>
							<a class="mdl-navigation__link" href="/rms-servlet-web/logout">Logout</a>
						</c:when>
						<c:otherwise>
							<a class="mdl-navigation__link"
								href="/rms-servlet-web/users/form">Register</a>
							<a class="mdl-navigation__link" href="/rms-servlet-web/login">Login</a>
						</c:otherwise>
					</c:choose>
				</nav>
			</div>
		</header>
		<div class="mdl-layout mdl-js-layout box-center mdl-card">
			<main class="mdl-layout__content">
			<div class="mdl-typography--text-center mdl-color-text--blue">
				<c:if test="${message!=null}">
					<p>${message}</p>
				</c:if>
			</div>
			<div class="mdl-card__title mdl-color--primary mdl-color-text--white mdl-shadow--6dp">
				<h2 class="mdl-card__title-text">Education List</h2>
			</div>
			<table
				class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
				<thead>
					<tr>
						<th class="mdl-data-table__cell--non-numeric">Period</th>
						<th>Qualification</th>
						<th>School/Collage</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ educations }" var="education">
						<tr>
							<td class="mdl-data-table__cell--non-numeric">${ education.period }</td>
							<td>${ education.qualification }</td>
							<td>${ education.school }</td>
							<td>
								<a href="/rms-servlet-web/education/delete?educationId=${ education.educationId }"><button type="button"
									class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Delete</button></a>
								<a href="/rms-servlet-web/education/edit?educationId=${ education.educationId }"><button type="button"
									class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Edit</button></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div
				class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
				<a href="../dashboard"><button type="button"
						class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Back</button></a>
				<a href="/rms-servlet-web/education/form"><button type="button"
						class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Add
						Education</button></a>
			</div>
		</div>
		</main>
	</div>

	<script src="js/scripts.js"></script>
</body>
</html>