<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="rms" uri="/WEB-INF/tags/link.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<a class="mdl-navigation__link" href="/rms-servlet-web/users/form">Register</a>
							<a class="mdl-navigation__link" href="/rms-servlet-web/login">Login</a>
						</c:otherwise>
					</c:choose>
				</nav>
			</div>
		</header>
		<div class="mdl-layout mdl-js-layout box-center mdl-card">


			<main class="mdl-layout__content">
			<div class="mdl-card mdl-shadow--6dp">
				<div
					class="mdl-card__title mdl-color--primary mdl-color-text--white">
					<h2 class="mdl-card__title-text">Edit User: ${user.name}</h2>
				</div>
				<div class="mdl-card__supporting-text">
					<form action="edit" method="post" id="form_edit">
						<div class="mdl-textfield mdl-js-textfield">
							<input class="mdl-textfield__input" type="text" id="name"
								name="name" value="${user.name}" /> <label
								class="mdl-textfield__label" for="userpass">Name</label>
						</div>
						<div class="mdl-textfield mdl-js-textfield">
							<label for="gender">Gender: </label> <select id="gender"
								name="gender">
								<c:choose>
									<c:when test="${user.gender=='Male'}">
										<option selected="selected" value="Male">Male</option>
										<option value="Female">Female</option>
									</c:when>
									<c:when test="${user.gender=='Female'}">
										<option value="Male">Male</option>
										<option selected="selected" value="Female">Female</option>
									</c:when>
									<c:otherwise>
										<option value="Male">Male</option>
										<option value="Female">Female</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
						<div class="mdl-textfield mdl-js-textfield">
							<input class="mdl-textfield__input" type="email" id="email"
								name="email" value="${user.email}" /> <label
								class="mdl-textfield__label" for="email">Email</label>
						</div>
						<div class="mdl-textfield mdl-js-textfield">
							<label for="type">Type: </label> <select id="type"
								name="type">
								<c:choose>
									<c:when test="${user.type=='Reguler'}">
										<option selected="selected" value="Reguler">Reguler</option>
										<option value="Premium">Premium</option>
									</c:when>
									<c:when test="${user.type=='Premium'}">
										<option value="Reguler">Reguler</option>
										<option selected="selected" value="Premium">Premium</option>
									</c:when>
									<c:otherwise>
										<option value="Reguler">Reguler</option>
										<option value="Premium">Premium</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
						<div class="mdl-textfield mdl-js-textfield">
							<label for="status">Status: </label> <select id="status"
								name="status">
								<c:choose>
									<c:when test="${user.status=='Active'}">
										<option selected="selected" value="Active">Active</option>
										<option value="Deactive">Deactive</option>
									</c:when>
									<c:when test="${user.status=='Deactive'}">
										<option value="Active">Active</option>
										<option selected="selected" value="Deactive">Deactive</option>
									</c:when>
									<c:otherwise>
										<option value="Active">Active</option>
										<option value="Deactive">Deactive</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
						<div class="mdl-textfield mdl-js-textfield">
							<input class="mdl-textfield__input" type="text" id="address"
								name="address" value="${user.address}" /> <label
								class="mdl-textfield__label" for="address">Address</label>
						</div>
					</form>
				</div>
				<div class="mdl-card__actions mdl-card--border">
					<button type="submit" form="form_edit"
						class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Save</button>
					<a href="../dashboard"><button type="button"
							class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Cancel</button></a>
				</div>
			</div>
			</main>
		</div>
	</div>
	<script src="js/scripts.js"></script>
</body>
</html>