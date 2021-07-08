/*
 * 控制task_data.html的js文件
 */
$(function(){

    var unfiTask = document.getElementById("UnfiTask");
    var fiTask = document.getElementById("FiTask");
    var acceptTask = document.getElementById("AcceptTask")
	var arr=document.cookie.split(";");//获取cookie字符串并分割
	var userid;
	//遍历匹配
	for(var i=0; i<arr.length; i++){
		var a = arr[i].split("=");
		if(a[0].trim() == "userID"){
		    userid = a[1];
		}else{
		}
	}

	$.ajax({
	    type:"post",
	    contentType:"application/json",
	    data:JSON.stringify({"taskstatus":"0","userID":userid}),
	    url:"http://localhost:8080/task/getTaskByUserID",
	    async:false,
	    dataType:"json",
	    success:function(result){
	        set_formdata(result,unfiTask);
	    },
	    error:function(result){
	        console.log("获取数据失败");
	    }
	});

	$.ajax({
	    type:"post",
	    contentType:"application/json",
	    data:JSON.stringify({"taskstatus":"1","userID":userid}),
	    url:"http://localhost:8080/task/getTaskByUserID",
	    async:false,
	    dataType:"json",
	    success:function(result){
	        set_formdata(result,fiTask);
	    },
	    error:function(result){
	        console.log("获取数据失败");
	    }
	});

	$.ajax({
	    type:"post",
	    contentType:"application/json",
	    data:JSON.stringify({"userID":userid}),
	    url:"http://localhost:8080/task/getAcceptTaskByUserID",
	    async:false,
	    dataType:"json",
	    success:function(result){
	        set_formdata(result,acceptTask);
	    },
	    error:function(result){
	        console.log("获取数据失败");
	    }
	});

})

function set_formdata(data,tbody){

	for(var i=0; i<data.length; i++){
		var row = getDataRow(data[i]);
		tbody.appendChild(row);
	}
}

function getDataRow(h){
	var row = document.createElement("tr");//创建行
	row.setAttribute("align","center");

	var IDcell = document.createElement("td");//创建第一列
	IDcell.setAttribute("scope","row");
	IDcell.innerHTML = h.taskid;//填充数据
	row.appendChild(IDcell);//加入行中

	var TaskNamecell = document.createElement("td");//创建第二列
	TaskNamecell.innerHTML = h.taskname;//填充数据
	row.appendChild(TaskNamecell);//加入行中

    var Detailscell = document.createElement("td");//创建第三列
    var link = document.createElement("a");
    link.href = "docs";
    link.onclick = function(){
    document.cookie = "taskID=" + h.taskid;
    document.cookie = "taskstatus=" + h.taskstatus;
    }
    link.innerHTML = h.describe_task.substr(0,10) + "......";//填充数据
    Detailscell.appendChild(link);
    row.appendChild(Detailscell);//加入行中

	var Datacell = document.createElement("td");//创建第四列
	var link_download = document.createElement("a");
	link_download.href = "#";
	link_download.onclick = function(){
	}
	link_download.innerHTML = "Download";
	Datacell.appendChild(link_download);
	row.appendChild(Datacell);

    var Datacell = document.createElement("td");//创建第五列
    var link_download = document.createElement("a");
    link_download.href = "#";
    link_download.onclick = function(){
    }
    link_download.innerHTML = "Download";
    Datacell.appendChild(link_download);
    row.appendChild(Datacell);

	return row;//返回tr数据

}