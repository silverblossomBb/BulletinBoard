const app = angular.module('hastarApp',[]);

app.controller('MainCtrl',function($scope,$http){
	$scope.boardData={};
	$scope.storageFile={};
	$scope.isYours=false;
	$scope.link=document.location.href.split("/");
	$scope.viewN = document.location.href.split("/")[$scope.link.length-1];
	$scope.isLogin=false;
	
	$scope.selectOnePost = function(){
		$http({
			url:"/view/"+$scope.viewN,
			method:"POST"
		}).then(function(data){
			console.log("data : ",data.data);
			$scope.boardData=data.data;
			console.log("this.data = ",$scope.boardData);
			$scope.getUserInfo();
		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	$scope.getStorageData= function(){
		$http({
			url:"/getFileList/"+$scope.viewN,
			method:"POST"
		}).then(function(data){
			console.log("datas : ",data);
			$scope.storageFile=data.data;

		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	$scope.goToFile = function(idex){
		location.href="/download/"+idex;
	}
	
	$scope.goToUpdate=function(){
		location.href="/update/"+$scope.viewN;
	}
	
	$scope.goToDelete=function(){
		location.href="/delete/"+$scope.viewN;
	}
	
	$scope.getUserInfo = function(){
		$http({
			url:"/getUserInfo",
			method:"POST"
		}).then(function(data){
			console.log("DATA2 ::",$scope.boardData.name,data.data.result);
			$scope.isYours = $scope.boardData.name==data.data.results ? true : false
			$scope.isLogin=data.data.result;
			console.log("DATA :: ",$scope.isYours,$scope.isLogin);
		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	$scope.selectOnePost();
	$scope.getStorageData();
});