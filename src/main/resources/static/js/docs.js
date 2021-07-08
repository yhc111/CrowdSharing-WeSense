/*
 * 控制docs.html的js文件
 */
window.onload = function(){
	var basicInformation = document.getElementById("BasicInformation");
	var restrictions = document.getElementById("Restrictions");
	var description = document.getElementById("Description");

    var strcookie = document.cookie;//获取cookie字符串
    var arrcookie = strcookie.split(";");//分割
    var m = new Map();//空map
    //遍历匹配
    for(var i = 0; i < arrcookie.length; i++){
        var arr = arrcookie[i].split("=");

        m.set(arr[0],arr[1]);
        /*if(arr[0] == "taskID"){
            console.log(arr[1]);
        }else if(arr[0] == "taskstatus"){
            console.log(arr[1]);
        }else{}*/
    }

    var str = "{";
    for(var x of m){
        str = str + '"' + x[0].trim() + '"' + ":" + '"' + x[1] + '"' +",";
    }
    str = str.substring(0,str.length-1);
    str = str + "}";

    $.ajax({
        type:"post",
        contentType:"application/json",
        data:str,
        url:"http://localhost:8080/task/getSingleTaskByID",
        async:false,
        dataType:"json",
        success:function(result){
            set_formdata(result,basicInformation,restrictions,description);
        },
        error:function(result){
            console.log("获取数据失败");
        }
    });

	/*task = {
		"taskid": 1,
		"taskname": "快递",
		"publisherid": "1",
		"publishername": "Shawn",
		"tasktype": 1,
		"taskdescribe": "帮忙去东元超市拿一下京东快递",
		"taskstatus": 0,
		"publishtime": "2020-02-10T00:00:00.000+0000",
		"deadline": "2020-02-11T00:00:00.000+0000",
		"latitude": 34.036892,
		"longitude": 108.761717,
		"totalworker": 5,
		"nowworker": 2,
		"needworker": 3,
		"coin": 100
	}*/

}

function set_formdata(task,basicInformation,restrictions,description){
	
	var IDcell = document.createElement("li");
	IDcell.innerHTML = "Task ID:" + "&nbsp&nbsp" + task.taskid;
	basicInformation.append(IDcell);
	
	var nameCell = document.createElement("li");
	nameCell.innerHTML = "Task Name:" + "&nbsp&nbsp" + task.taskname;
	basicInformation.append(nameCell);
	
	var typeCell = document.createElement("li");
	switch(task.tasktype)
	{
		case 1:
		    typeCell.innerHTML = "Task Type:" + "&nbsp&nbsp" + "Public Safety";
	        basicInformation.append(typeCell);
	        break;
	    case 2:
	        typeCell.innerHTML = "Task Type:" + "&nbsp&nbsp" + "Daliy Life";
	        basicInformation.append(typeCell);
	        break;
	    case 3:
	        typeCell.innerHTML = "Task Type:" + "&nbsp&nbsp" + "City Traffic";
	        basicInformation.append(typeCell);
	        break;
	    case 4:
	        typeCell.innerHTML = "Task Type:" + "&nbsp&nbsp" + "Business Activity";
	        basicInformation.append(typeCell);
	        break;
	    case 5:
	        typeCell.innerHTML = "Task Type:" + "&nbsp&nbsp" + "Entertainment";
	        basicInformation.append(typeCell);
	        break;
	}
	
	var statusCell = document.createElement("li");
	switch(task.taskstatus)
	{
		case 0:
		    statusCell.innerHTML = "Task Status:" + "&nbsp&nbsp" + "Unfinished"
		    basicInformation.append(statusCell);
		    break;
		case 1:
		    statusCell.innerHTML = "Task Status:" + "&nbsp&nbsp" + "Finished"
		    basicInformation.append(statusCell);
		    break;
	}
	
	var publisherIDCell = document.createElement("li");
	publisherIDCell.innerHTML = "Publisher ID:" + "&nbsp&nbsp" + task.publisherid;
	basicInformation.append(publisherIDCell);
	
	var publisherCell = document.createElement("li");
	publisherCell.innerHTML = "Publisher Name:" + "&nbsp&nbsp" + task.publishername;
	basicInformation.append(publisherCell);
	
	var timeCell = document.createElement("li");
	timeCell.innerHTML = "Publish Time:" + "&nbsp&nbsp" + task.publishtime.substr(0,10);
	restrictions.append(timeCell);
	
	var deadlineCell = document.createElement("li");
	deadlineCell.innerHTML = "Deadline:" + "&nbsp&nbsp" + task.deadline.substr(0,10);
	restrictions.append(deadlineCell);
	
	var locationCell = document.createElement("li");
	locationCell.innerHTML = "Location:" + "&nbsp&nbsp" + "Longitude" + "&nbsp&nbsp" + task.longitude + "&nbsp&nbsp" + "Latitude" + "&nbsp&nbsp" + task.latitude;
	restrictions.append(locationCell);
	
	var workerCell = document.createElement("li");
	workerCell.innerHTML = "Worker:" + "&nbsp&nbsp" + "Total" + "&nbsp&nbsp" + task.totalworker + "&nbsp&nbsp" + "Now" + "&nbsp&nbsp" + task.nowworker + "&nbsp&nbsp" + "Need" + "&nbsp&nbsp" + task.needworker;
	restrictions.append(workerCell);
	
	var coinsCell = document.createElement("li");
	coinsCell.innerHTML = "Coins:" + "&nbsp&nbsp" + task.coin;
	restrictions.append(coinsCell);
	
	description.innerHTML = task.taskdescribe;
}
