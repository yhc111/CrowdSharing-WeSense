/*
 *控制upload.html页面的js文件
 */

$(function () {
    $("#testSubmit").click(function () {
        uploadData();
    });
});

function uploadData() {
    var formData = new FormData();
    var dataName = $('input[id="dataName"]').val();
    var describe = $('input[id="describe"]').val();
    formData.append("dataset", $("#dataset")[0].files[0]);
    formData.append("dataName", dataName);
    formData.append("describe", describe);
    $.ajax({
        url:"http://localhost:8080/file/fileUpload",
        type:'post',
        data:formData,
        contentType:false,
        processData:false,
        success:function (result) {
            var obj = JSON.parse(result);
            if (obj.status == 'true'){
                alert("Successful upload!")
            }
        },
        error:function () {
            console.log("false");
        }
    });
}