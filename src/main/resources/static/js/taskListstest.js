/*
 * 控制taskLists.html的js文件
 */

var tbody1 = document.getElementById("UnfinishedTaskTable");
var tbody2 = document.getElementById("FinishedTaskTable");

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

	initalPagination();

	$(document).on("click","ul.pagination li a.backMore",function(){
	    var lastPage = parseInt($("ul.pagination li a.num:last").text());
	    ajax_post(lastPage+1);
        $(".pagination").empty();
        $(".pagination").append("<li><a class=\"up1\" href=\"#\"><<</a></li>");
        $(".pagination").append("<li><a class=\"frontMore\" href=\"#\">...</a></li>");
        for(var i=lastPage+1; i<lastPage+6; i++){
            if(i == lastPage+1){
                $(".pagination").append("<li><a class=\"num active\" href=\"#\">" + i + "</a></li>");
            }else{
                $(".pagination").append("<li><a class=\"num\" href=\"#\">" + i + "</a></li>");
            }
        }
        $(".pagination").append("<li><a class=\"backMore\" href=\"#\">...</a></li>");
        $(".pagination").append("<li><a class=\"down1\" href=\"#\">>></a></li>");
	});

	$(document).on("click","ul.pagination li a.frontMore",function(){
	    var firstPage = parseInt($("ul.pagination li a.num:first").text());
        if(firstPage != 1){
        	ajax_post(firstPage-5);
            $(".pagination").empty();
            $(".pagination").append("<li><a class=\"up1\" href=\"#\"><<</a></li>");
            $(".pagination").append("<li><a class=\"frontMore\" href=\"#\">...</a></li>");
            for(var i=firstPage-5; i<firstPage; i++){
                if(i == firstPage-5){
                    $(".pagination").append("<li><a class=\"num active\" href=\"#\">" + i + "</a></li>");
                }else{
                    $(".pagination").append("<li><a class=\"num\" href=\"#\">" + i + "</a></li>");
                }
            }
            $(".pagination").append("<li><a class=\"backMore\" href=\"#\">...</a></li>");
            $(".pagination").append("<li><a class=\"down1\" href=\"#\">>></a></li>");
        }
	});

    $.ajax({
        type:"post",
        contentType:"application/json",
        data:JSON.stringify({"pageNum":"1","pageSize":"5","flag":"true"}),
        url:"http://localhost:8080/task/findPage",
        async:false,
        dataType:"json",
        success:function(result){
            var data1 = result.content;
            set_formdata(data1,tbody1);
        },
        error:function(result){
            console.log("获取数据失败");
        }
    });

    $.ajax({
        type:"post",
        contentType:"application/json",
        data:JSON.stringify({"pageNum":"1","pageSize":"5","flag":"false"}),
        url:"http://localhost:8080/task/findPage",
        async:false,
        dataType:"json",
        success:function(result){
            var data2 = result.content;
            set_formdata(data2,tbody2);
        },
        error:function(result){
            console.log("获取数据失败");
        }
    });

//要改
	$(document).on("click","ul.pagination li a.up1",function(){

		$("ul.pagination li a.num").each(function(index, element){
			if($(this).hasClass("active")){
				if($(this).parent().index()>2){
	            $("#UnfinishedTaskTable").empty();
	            $("#FinishedTaskTable").empty();
					return false;
				}else{}
			}
		});

		$("ul.pagination li a.num").each(function(index, element){
			if($(this).hasClass("active")){
				if($(this).parent().index()>2){
					$("ul.pagination li a.num").removeClass("active");
					var parent = $(this).parent().prev();
					parent.children().addClass("active");
					var a = parent.text();

					$.ajax({
		                type:"post",
		                contentType:"application/json",
		                data:JSON.stringify({"pageNum":a,"pageSize":"5","flag":"true"}),
		                url:"http://localhost:8080/task/findPage",
		                async:false,
		                dataType:"json",
		                success:function(result){
		                    var data1 = result.content;
		                    set_formdata(data1,tbody1);
		                },
		                error:function(result){
		                    console.log("获取数据失败");
		                }
		            });

		            $.ajax({
		                type:"post",
		                contentType:"application/json",
		                data:JSON.stringify({"pageNum":a,"pageSize":"5","flag":"false"}),
		                url:"http://localhost:8080/task/findPage",
		                async:false,
		                dataType:"json",
		                success:function(result){
		                    var data2 = result.content;
		                    set_formdata(data2,tbody2);
		                },
		                error:function(result){
		                    console.log("获取数据失败");
		                }
		            });
			        console.log(a)//当前激活item的内容

				}else{}
			}
		});
	})

//要改
	$(document).on("click","ul.pagination li a.down1",function(){

		$("ul.pagination li a.num").each(function(index, element){
			if($(this).hasClass("active")){
				if($(this).parent().index()<6){
	            $("#UnfinishedTaskTable").empty();
	            $("#FinishedTaskTable").empty();
					return false;
				}else{}
			}
		});

		$("ul.pagination li a.num").each(function(index, element){
			if($(this).hasClass("active")){
				if($(this).parent().index()<6){
					$("ul.pagination li a.num").removeClass("active");
					var parent = $(this).parent().next();
					parent.children().addClass("active");
					var a = parent.text();

		            $.ajax({
		                type:"post",
		                contentType:"application/json",
		                data:JSON.stringify({"pageNum":a,"pageSize":"5","flag":"true"}),
		                url:"http://localhost:8080/task/findPage",
		                async:false,
		                dataType:"json",
		                success:function(result){
		                    var data1 = result.content;
		                    set_formdata(data1,tbody1);
		                },
		                error:function(result){
		                    console.log("获取数据失败");
		                }
		            });

		            $.ajax({
		                type:"post",
		                contentType:"application/json",
		                data:JSON.stringify({"pageNum":a,"pageSize":"5","flag":"false"}),
		                url:"http://localhost:8080/task/findPage",
		                async:false,
		                dataType:"json",
		                success:function(result){
		                    var data2 = result.content;
		                    set_formdata(data2,tbody2);
		                },
		                error:function(result){
		                    console.log("获取数据失败");
		                }
		            });
			        console.log(a)//当前激活item的内容

					return false;
				}else{}
			}
		});
	})

	$(document).on("click","ul.pagination li a.num",function(){
		$("#UnfinishedTaskTable").empty();
		$("#FinishedTaskTable").empty();
		//这里存在一个问题：为什么用remove方法去掉所有元素就不行？
		$("ul.pagination li a.num").removeClass("active");
		$(this).addClass("active");
		$("ul.pagination li a.num").each(function(index, element) {
		    if($(this).hasClass("active")){
		    	var a = $(this).text();

	            $.ajax({
	                type:"post",
	                contentType:"application/json",
	                data:JSON.stringify({"pageNum":a,"pageSize":"5","flag":"true"}),
	                url:"http://localhost:8080/task/findPage",
	                async:false,
	                dataType:"json",
	                success:function(result){
	                    var data1 = result.content;
	                    set_formdata(data1,tbody1);
	                },
	                error:function(result){
	                    console.log("获取数据失败");
	                }
	            });

	            $.ajax({
	                type:"post",
	                contentType:"application/json",
	                data:JSON.stringify({"pageNum":a,"pageSize":"5","flag":"false"}),
	                url:"http://localhost:8080/task/findPage",
	                async:false,
	                dataType:"json",
	                success:function(result){
	                    var data2 = result.content;
	                    set_formdata(data2,tbody2);
	                },
	                error:function(result){
	                    console.log("获取数据失败");
	                }
	            });
		        console.log(a)//当前激活item的内容
		    }
		});
	});


})

function initalPagination(){
	//var html = $(".pagination").html();
	$(".pagination").append("<li><a class=\"up1\" href=\"#\"><<</a></li>");
	$(".pagination").append("<li><a class=\"frontMore\" href=\"#\">...</a></li>");
	for(var i=1; i<6; i++){
		if(i == 1){
			$(".pagination").append("<li><a class=\"num active\" href=\"#\">" + i + "</a></li>");
		}else{
			$(".pagination").append("<li><a class=\"num\" href=\"#\">" + i + "</a></li>");
		}
	}
	$(".pagination").append("<li><a class=\"backMore\" href=\"#\">...</a></li>");
	$(".pagination").append("<li><a class=\"down1\" href=\"#\">>></a></li>");
}

function ajax_post(a){
    $("#UnfinishedTaskTable").empty();
    $("#FinishedTaskTable").empty();
    $.ajax({
        type:"post",
        contentType:"application/json",
        data:JSON.stringify({"pageNum":a,"pageSize":"5","flag":"true"}),
        url:"http://localhost:8080/task/findPage",
        async:false,
        dataType:"json",
        success:function(result){
            var data1 = result.content;
            set_formdata(data1,tbody1);
        },
        error:function(result){
            console.log("获取数据失败");
        }
    });

    $.ajax({
        type:"post",
        contentType:"application/json",
        data:JSON.stringify({"pageNum":a,"pageSize":"5","flag":"false"}),
        url:"http://localhost:8080/task/findPage",
        async:false,
        dataType:"json",
        success:function(result){
            var data2 = result.content;
            set_formdata(data2,tbody2);
        },
        error:function(result){
            console.log("获取数据失败");
        }
    });
}

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

	var Publishercell = document.createElement("td");//创建第三列
	Publishercell.innerHTML = h.publishername;//填充数据
	row.appendChild(Publishercell);//加入行中

	var Workerscell = document.createElement("td");//创建第四列
	Workerscell.innerHTML = h.totalworker;//填充数据
	row.appendChild(Workerscell);//加入行中

	var Coinscell = document.createElement("td");//创建第五列
	Coinscell.innerHTML = h.coin;//填充数据
	row.appendChild(Coinscell);//加入行中

	var Detailscell = document.createElement("td");//创建第六列
	var link = document.createElement("a");
	link.href = "docs";
	link.onclick = function(){
	document.cookie = "taskID=" + h.taskid;
	document.cookie = "taskstatus=" + h.taskstatus;
	}
	link.innerHTML = h.taskdescribe.substr(0,10) + "......";//填充数据
	Detailscell.appendChild(link);
	row.appendChild(Detailscell);//加入行中

	return row;//返回tr数据

}