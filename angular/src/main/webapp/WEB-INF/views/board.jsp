<%@page import="eun.byeol.jo.bean.DataBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Test</title>
	
	<link rel="stylesheet" href="/res/css/board.css">	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	<script type="text/javascript">
		angular.module("ngApp", [])
			   .controller("ngCtrl", function($scope, $http) {
				   // getData >> select
				   $scope.getData = function() {
					   $http({
						   method: "POST",
						   url: "/notice/select"
					   }).then(function(res) {
						   $scope.list = res.data.result;
					   })
				   };
				   $scope.getData();
				   
				   // setData >> insert, update, delete
				   $scope.setData = function(path) {
					   var pk = 0;
					   if (path != '/notice/insert') {pk = $scope.no;}
					   
					   $http({
						   method: "POST",
						   url: path,
						   params: {
							   no: pk,
							   id: $scope.id,
							   txt: $scope.txt
						   }
					   }).then(function(res){
						   $scope.txt = "";
						   $scope.dp = true;
						   cbi = -1; 
						   check = false;
						   $scope.getData();
					   });
				   }
				   
				   // checkBox
				   $scope.dp = true;
				   var cbi = -1; // chekbox index
				   var check = false; // cheked
				   $scope.checkbox = function(row, index) {
					   if (cbi > -1) {
						   if (cbi != index) {
							   $scope.list[cbi].ckbox = false;
							   cbi = index;
						   } else {
							   cbi = -1;
							   check = false;
						   }
					   } else {
						   cbi = index;
						   check = true;
					   }
					   
					   if (check) {
						   $scope.no = row.no;
						   $scope.id = row.id;
						   $scope.txt = row.txt;
						   $scope.dp = false;
					   } else {
						   $scope.no = "";
						   $scope.txt = "";
						   $scope.dp = true;
					   }
				   }
				   
				   $scope.status = "Login";
				   $scope.login = function() {
					   $http({
						   method: "POST",
						   url: "/login",
						   params: {
							   id: $scope.id,
							   pw: $scope.pw
						   }
					   }).then(function(res){
						   $scope.id = "";
						   $scope.pw = "";
						   
						   if (res.data.result == "true") {
							   $scope.add = false;
							   $scope.status = "Logout";
						   } else {
							   alert("Login Fail!");
						   }
					   });
					  
				   }
				   
			   });
	</script>
</head>

<body data-ng-app="ngApp" data-ng-controller="ngCtrl">
	
	<div class="container">
		<h1 class="text-center">구디아카데미</h1>
		<form id="edit">
		  <div class="form-group row">
		    <div class="col-xs-2">
		    	<label for="text">한줄평  :</label>
		    </div>
		    <div class="col-xs-7">
		    	<input type="text" class="form-control" data-ng-model="txt" placeholder="입력하세요." autocomplete="off">
		    	<input type="hidden" data-ng-model="no">
		    </div>
		    <div class="col-xs-1">
		    	<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".modal" data-ng-click="setData('/notice/insert')">추가</button>
		    </div>
		    <div class="col-xs-1">
		    	<button type="button" class="btn btn-success" data-ng-disabled="dp" data-ng-click="setData('/notice/update')">수정</button>
		    </div>
		    <div class="col-xs-1">
		    	<button type="button" class="btn btn-danger" data-ng-disabled="dp" data-ng-click="setData('/notice/delete')">삭제</button>
		    </div>
		  </div>
		</form>
	</div>
	
	<div class="container">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th>선택</th>
		      <th>번호</th>
		      <th>작성자</th>
		      <th>한줄평</th>
		    </tr>
		  </thead>
		  <tbody>
			<tr data-ng-repeat="row in list | orderBy : txt">
		      <td><input type="checkbox" data-ng-model="row.ckbox" data-ng-click="checkbox(row, $index)"></td>
		      <td>{{$index + 1}}</td>
		      <td>{{row.id}}</td>
		      <td>{{row.txt}}</td>
		    </tr>
		  </tbody>
		</table>
	</div>
	
	<!-- Modal -->
	<div class="container">
	  <!-- Modal -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header" style="padding:35px 50px;">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
	        </div>
	        <div class="modal-body" style="padding:40px 50px;">
	          <form action="/login" role="form" method="post">
	            <div class="form-group">
	              <label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
	              <input type="text" class="form-control" data-ng-model="id" placeholder="Enter email" required="required" autocomplete="off">
	            </div>
	            <div class="form-group">
	              <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
	              <input type="text" class="form-control" data-ng-model="pw" placeholder="Enter password" required="required" autocomplete="off">
	            </div>
	              <button type="button" class="btn btn-success btn-block" data-ng-click="login()" data-dismiss="modal"><span class="glyphicon glyphicon-off"></span> Login</button>
	          </form>
	        </div>
	      </div>
	    </div>
	  </div> 
	</div>
	
</body>
</html>