const app = angular.module('hastarApp',[]);

app.controller('MainCtrl',function($scope,$http){
	$scope.boardData={};
	$scope.name="Board New";
	$scope.isLogin=false;
	
	$scope.getUserInfo = function(){
		$http({
			url:"/getUserInfo",
			method:"POST"
		}).then(function(data){
			console.log("data : ",data.data.result);
			$scope.isLogin=data.data.result;
		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	$scope.getUserInfo();
});