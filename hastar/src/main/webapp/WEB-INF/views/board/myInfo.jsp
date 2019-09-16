<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Simple Sidebar</title>
<!-- Bootstrap core CSS -->
<!-- Custom styles for this template -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<link
	href="<c:url value='/resources/vendor/bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/simple-sidebar.css' />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/board.js"/>"></script>
</head>
<body ng-app="hastarApp" ng-controller="MainCtrl">
	<div class="d-flex" id="wrapper">
		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<a href="/test"><div class="sidebar-heading">
				Ha-Star😁</div></a>
			<div class="list-group list-group-flush">
				<a href="/board" class="list-group-item list-group-item-action bg-light">
				Bulletin board💬</a>
				<a href="/myInfo" class="list-group-item list-group-item-action bg-light">My Info🕶</a>
				<a href="/temp" class="list-group-item list-group-item-action bg-light">TEMP</a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<button class="btn btn-primary" id="menu-toggle">Toggle
					Menu</button>

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
						<li class="nav-item active"><a class="nav-link" href="#">Home
								<span class="sr-only">(current)</span>
						</a></li>
						<li ng-switch on="isLogin" class="nav-item">
							<a ng-switch-when="true" class="nav-link" href="/logout">LogOut</a>
							<a ng-switch-default class="nav-link" href="/login">Login</a>
						</li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> Dropdown </a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="#">Action</a> <a
									class="dropdown-item" href="#">Another action</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Something else here</a>
							</div></li>
					</ul>
				</div>
			</nav>
			<div class="container-fluid text-center">
				<h1>ID: ${sessionScope.id}</h1>
				<h1>Name: ${sessionScope.name}</h1>
				<image src=${sessionScope.image}>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->
	<!-- Bootstrap core JavaScript -->
	<script src="<c:url value='/resources/vendor/jquery/jquery.min.js' />"></script>
	<script
		src="<c:url value='/resources/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>
	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>

</html>
