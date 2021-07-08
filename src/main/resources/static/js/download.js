/*
 *控制download.html页面的js文件
 */

$(function(){
	$("div a[id^='csv']").click(function(){
	    var index = $(this).attr("id");
	    $("input[type='hidden']").attr("value",index);
	    alert("You do not have this permission!")
	    //redirect();
	});
});

function redirect(){
    document.redirectForm.submit();
}