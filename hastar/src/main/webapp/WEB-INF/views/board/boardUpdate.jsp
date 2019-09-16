<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Simple Sidebar</title>
<!-- Bootstrap core CSS -->
<!-- Custom styles for this template -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<link href="<c:url value='/resources/vendor/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/simple-sidebar.css' />" rel="stylesheet">
<script src="<c:url value="/resources/js/boardUpdate.js"/>"></script>
</head>
<body>
	<div class="d-flex" id="wrapper">
		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<a href="/test"><div class="sidebar-heading">Ha-Star😁</div></a>
			<div class="list-group list-group-flush">
				<a href="/board" class="list-group-item list-group-item-action bg-light"> Bulletin board💬</a> <a href="/myInfo"
					class="list-group-item list-group-item-action bg-light">My Info🕶</a> <a href="/temp"
					class="list-group-item list-group-item-action bg-light">TEMP</a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>

				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
						<li class="nav-item active"><a class="nav-link" href="#">Home <span class="sr-only">(current)</span>
						</a></li>
						<li ng-switch on="isLogin" class="nav-item">
							<a ng-switch-when="true" class="nav-link" href="/logout">LogOut</a>
							<a ng-switch-default class="nav-link" href="/login">Login</a>
						</li>
						<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Dropdown </a>
							<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Something else here</a>
							</div></li>
					</ul>
				</div>
			</nav>
			<div class="container-fluid" id="app">
				<form method="post" action="/update" enctype="multipart/form-data">
					<h2>Title</h2>
					<div class="input-group mb-3">
						<input v-model="updateData.title" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" name="title" id="title">
					</div>
					<input type="hidden" name="no" v-model="viewN">
					<h2>Content</h2>
					<div class="input-group">
						<textarea v-model="updateData.content" class="form-control" aria-label="With textarea" name="content" id="content"></textarea>
					</div>
					<h2>File Upload</h2>
					<div class="input-group">
						<div class="custom-file">
							<input type="file" class="custom-file-input" name="file" multiple="multiple" id="inputGroupFile04"> <label
								class="custom-file-label" for="inputGroupFile04">Choose file</label>
						</div>
					</div>
					
					<input type="submit" class="btn btn-success" value="전송">
				</form>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->
	<!-- Bootstrap core JavaScript -->
	<script src="<c:url value='/resources/vendor/jquery/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script>
		new Vue({
			el:"#app",
			data:{
				updateData:{},
				fileData:{},
				viewN:-1
			},
			methods:{
				getData:function(){
					let link=document.location.href.split("/");
					this.viewN=document.location.href.split("/")[link.length-1];
					axios.post('/view/'+this.viewN)
						 .then((res)=>{
							 console.log("RES",res);
							 this.updateData=res.data;
					}).catch((err)=>{
						console.log("ERR",err);
					})
				},
				getFile:function(){
					let link=document.location.href.split("/");
					this.viewN=document.location.href.split("/")[link.length-1];
					axios.post('/getFileList/'+this.viewN)
						 .then((res)=>{
							 console.log("RES",res);
					}).catch((err)=>{
						console.log("ERR",err);
					})
				}
			},
			mounted:function(){
				console.log(this.updateData);
				this.getData();
				this.getFile();
			}
		})
	</script>
	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>

</html>
