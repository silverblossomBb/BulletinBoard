let idx=-1;

const app = angular.module('hastarApp',[]);
app.controller('MainCtrl',function($scope,$http){
	$scope.userInfo={};
	$scope.message="";
	$scope.storage={};
	$scope.dataInfo={};
	
	$scope.formAction = function(branch){
		$http({
			url:"/t0902/crud/"+branch,
			method:"POST",
			params:$scope.dataInfo
		}).then(function(data){
			//console.log("Data : ",data);
			alert(data.data.comment);
			$scope.getData();
			$scope.dataInfo.comment="";
		}).catch(function(err){
			console.log("ERR! : ",err);
			$scope.dataInfo.comment="";
		});
	}
	
	$scope.getData = function(){
		$http({
			url:"/t0902/getPostData",
			method:"POST"
		}).then(function(data){
			//console.log("data : ",data);
			$scope.storage=data.data;
		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	/**/
	//Login http
	$scope.modalMethod = function(){
		let url ="login";
		if($scope.loginCheckbox){
			url="addUser";
		}
		console.log("회원가입");
		$http({
			url:"/t0902/"+url,
			method:"POST",
			params:$scope.userInfo
		}).then(function(data){
			let status =data.data.status;
			let message="";
			if(status == "400"){
				message=data.data.errorList[0];
			}else{
				message=data.data.returnInfo.message;
			}
			$scope.message=message;
			
			if(data.data.returnInfo.secStatus == "222"){
				alert(message);
				location.href="/t0902/";
			}
			/*
			 * 초기화 부분 함수로 빼기
			 */
			$scope.userInfo.username="";
			$scope.userInfo.password="";
		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	//checkBox
	$scope.updateCheckNumb = function(index){
		if(idx==index){
			$scope.storage[index].isChecked=false;
			idx=-1;
			$scope.dataInfo.comment="";
			$scope.dataInfo.pno=-1;
		}else{
			$scope.storage.forEach(function(item){
				item.isChecked = false;
			});
			$scope.dataInfo.comment=$scope.storage[index].comment;
			$scope.dataInfo.pno=$scope.storage[index].pno;
			$scope.storage[index].isChecked=true;
			idx=index;
		}
	}
	/**/
	$scope.getData();
});