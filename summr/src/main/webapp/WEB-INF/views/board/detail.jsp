<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SilverStar</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="/resources/css/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-route.min.js"></script>
<script>
	angular.module("ngApp", [])
		   .controller("ngCtrl", function($scope, $http) {
			  $scope.dpOn = true;
			  
			  $scope.loginCheck = function() {
				  $http({
					url: "/loginCheck",
					method: "POST"
				  }).then(function(res) {
					  if (res.data.login != null) {
						  $scope.dpOn = false;
						  $scope.dpOff = true;
						  $scope.status = "login";
					  } else {
						  $scope.status = "logout";
					  }
				  })
			  };
			  $scope.loginCheck();
			  
			  $scope.getVisitor = function() {
				   $http({
					  method: "POST",
					  url: "/getVisitors"
				   }).then(function(res) {
					   $scope.visitor = res.data.getData;
					   console.log(res.data.getData);
				   });
			   };
			   $scope.getVisitor();
			  
			   $scope.board = {};
			   $scope.setNotice = function() {
				   $http({
					   url: "/notice/insert",
					   method: "POST",
					   params: $scope.board 
				   }).then(function() {
					   location.href= "/";
				   });
			   };
			   
			   $scope.board = {};
			   $scope.board.no = ${sessionScope.noticeNo};
			   $scope.getNotice = function() {
				   $http({
					   url: "/notice/select",
					   method: "POST",
					   params: $scope.board
				   }).then(function(res) {
					   $scope.board = res.data.getData[0];
					   if (${sessionScope.id} == $scope.board.id) {
						   $scope.mine = true;
					   };
				   });
			   };
			   
			   $scope.getNotice();
			   
			   $scope.setNotice = function(path) {
				   if ($scope.board.title.trim() == "") {
					   alert("Insert title!");
				   } else {
					   $http({
						   url: "/notice" + path,
						   method: "POST",
						   params: $scope.board 
					   }).then(function() {
						   location.href="/";
					   });
				   };
			   };
			   console.log($scope.board.title);
		   });
</script>
</head>

<body class="w3-light-grey" data-ng-app="ngApp" data-ng-controller="ngCtrl">
<!-- w3-content defines a container for fixed size centered content, 
and is wrapped around the whole page content, except for the footer in this example -->
<div class="w3-content" style="max-width:1400px">

<!-- Header -->
<header class="w3-container w3-center w3-padding-32"> 
  <h1><b>Welcome :D</b></h1>
</header>

<!-- Grid -->
<div class="w3-row">

<!-- Introduction menu -->
<div class="w3-col l4">

  <!-- About Me -->
  <div class="w3-card w3-margin w3-margin-top aboutMe">
	  <div data-ng-show="dpOff">
	  	<img src=${sessionScope.imageUrl} style="width:100%">
	    <div class="w3-container w3-white">
	    	<h4><b>ID: ${sessionScope.id}</b></h4>
	      	<h4><b>Name: ${sessionScope.name}</b></h4>
	      	<h4><b><a href="/logout">Logout</a></b></h4>
	    </div>
	    
	  </div>
	  <a href="/login" data-ng-show="dpOn">Login</a>
  </div><hr>
  
  <!-- Visitors -->
  <div class="w3-card w3-margin">
    <div class="w3-container w3-padding" style="text-align: center;">
      <h4>Visitors</h4>
    </div>
    <table class="table table-striped">
			<thead>
				<tr>
					<th>No</th>
					<th>Name</th>
					<th>Login Time</th>
				</tr>
			</thead>
			<tbody>
				<tr data-ng-repeat="row in visitor | orderBy : -no">
					<td>{{row.no}}</td>
					<td>{{row.name}}</td>
					<td>{{row.timeLog}}</td>
				</tr>
			</tbody>
		</table>
  </div>
  <hr> 
  
<!-- END Introduction Menu -->
</div>

<!-- Blog entries -->
<div class="w3-col l8 s12">
  <!-- Blog entry -->
  <div class="w3-card-4 w3-margin w3-white">
    <div class="w3-container" style="padding: 10px;">
    	<div>
			<h4>No: {{board.no}}</h4>
			<h4>Writer: {{board.name}}</h4>
		</div>
    	<lable>Tile</lable><br>
		<input data-ng-model="board.title" data-ng-disabled="!mine"><br>
		<lable>Content</lable><br>
		<input data-ng-model="board.content" data-ng-disabled="!mine"><br><br>	
		<button type="button" data-ng-show="mine" data-ng-click="setNotice('/update')">Update</button>
		<button type="button" data-ng-show="mine" data-ng-click="setNotice('/delete')">Delete</button>
		<a href="/"><button type="button">To List</button></a>
    </div>
  </div>
<!-- END BLOG ENTRIES -->
</div>

<!-- END GRID -->
</div><br>

<!-- END w3-content -->
</div>
</body>
</html>