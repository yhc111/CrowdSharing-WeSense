$(function(){
	var UsernameInfo = document.getElementById("UsernameInfo");
	//var RoleInfo = document.getElementById("RoleInfo");
	var arr=document.cookie.split(";");//获取cookie字符串并分割
	//遍历匹配
	for(var i=0; i<arr.length; i++){
		var a = arr[i].split("=");
		if(a[0].trim() == "userName"){
			UsernameInfo.innerHTML = a[1];
		}else{
			//RoleInfo.innerHTML = a[1];
		}
	}
});