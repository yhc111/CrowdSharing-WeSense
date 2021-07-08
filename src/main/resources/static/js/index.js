/*
 * 控制index.html的js文件
 */

window.onload = function(){
	var UsernameInfo = document.getElementById("UsernameInfo");
	//var RoleInfo = document.getElementById("RoleInfo");
	var arr=document.cookie.split(";");//获取cookie字符串并分割
	//遍历匹配
	for(var i=0; i<arr.length; i++){
		var a = arr[i].split("=");
		if(a[0].trim() === "userName"){
			UsernameInfo.innerHTML = a[1];
		}else{
			//RoleInfo.innerHTML = a[1];
		}
	}

	var userNum = document.getElementById("userNum");
	var newTaskNum = document.getElementById("newTaskNum");
	var finishedTaskNum = document.getElementById("finishedTaskNum");
	var totalTaskNum = document.getElementById("totalTaskNum");
    //userNum.innerHTML = "0";
	getUserNum();
	getTaskNum();
}

function getUserNum(){
	window.setInterval(function(){
		$.ajax({
		type:"get",
		url:"http://localhost:8080/user/getUserNum",
		async:true,
		//data: {username:$("#username").val(), content:$("#content").val()},
		dataType:"json",
		success:function(result){
			//请求成功时处理
			userNum.innerHTML = result.userNum;
		},
		error:function(){
			//请求出错处理
			userNum.innerHTML = "0";
		}
		});
	},1000)
}

function getTaskNum(){
	window.setInterval(function(){
		$.ajax({
		type:"get",
		url:"http://localhost:8080/task/getTaskNum",
		async:true,
		dataType:"json",
		success:function(result){
			//请求成功时处理
			newTaskNum.innerHTML = result.unfinishedTaskNum;
			finishedTaskNum.innerHTML = result.finishedTaskNum;
			totalTaskNum.innerHTML = result.totalTaskNum;
		},
		error:function(){
			//请求出错处理
			newTaskNum.innerHTML = "0";
            finishedTaskNum.innerHTML = "0";
            totalTaskNum.innerHTML = "0";
		}
		});
	},1000)
}