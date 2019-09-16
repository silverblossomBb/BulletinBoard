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
			  
			   $scope.getNotice = function() {
				   $http({
					   url: "/notice/select",
					   method: "POST"
				   }).then(function(res) {
					   $scope.notice = res.data.getData;
				   })
			   }
			   $scope.getNotice();
			   
			   $scope.detail = function(noticeNo) {
				   if ($scope.status != "login") {
					   alert("Login please");
				   } else {
					   location.href= "/detail/" + noticeNo;
				   }
			   }
			   
			   $scope.write = function() {
				   if ($scope.status != "login") {
					   alert("Login please");
				   } else {
					   location.href= "/write";
				   };
			   };
			   
			   $scope.select = "";
			   $scope.board = {};
			   $scope.target = ["title", "name"];
			   $scope.search = function() {
				   switch ($scope.select) {
				   case "title":
					   $scope.board.title = $scope.txt;
					   break;
				   case "name":
					   $scope.board.name = $scope.txt;
					   break;
				   default:
					   alert("Select Type!");
				   }
				   
				   if ($scope.select == "title" || $scope.select == "name") {
					   $http({
						   url: "/notice/select",
						   method: "POST",
						   params: $scope.board
					   }).then(function(res) {
						   $scope.notice = res.data.getData;
					   })
				   }
				  
			   }
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

  <!-- About Card -->
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
  
  <!-- Posts -->
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
  <div class="w3-card-4 w3-margin w3-white">
    <div class="w3-container">
      <div class="button" data-ng-click="write()" style="float: right;"><h4><b>Write</b></h4></div>
    </div>
  </div>
  <hr>
  
  <div class="w3-card-4 w3-margin w3-white">
    <div class="w3-container">
    	<select data-ng-model="select">
    		<option data-ng-repeat="t in target" value="{{t}}">{{t}}</option>
    	</select>
    	<input data-ng-model="txt">
      	<div class="button" data-ng-click="search()" style="float: right;"><h4><b>Search</b></h4></div>
    </div>
  </div>
  <hr>
  
  <div class="w3-card-4 w3-margin w3-white">
    <div class="w3-container">
      <h3><b>Notice Board</b></h3>
    </div>

    <div class="w3-container">
      <table class="table table-striped">
			<thead>
				<tr>
					<th>No</th>
					<th>Title</th>
					<th>Writer</th>
				</tr>
			</thead>
			<tbody>
				<tr data-ng-repeat="row in notice" data-ng-click="detail(row.no)">
					<td>{{$index + 1}}</td>
					<td>{{row.title}}</td>
					<td>{{row.name}}</td>
				</tr>
			</tbody>
		</table>
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
