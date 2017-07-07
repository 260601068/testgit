<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<script type="text/javascript" src="../js/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#text").on("click",function(){
		$.ajax({
			 url: "subject_addResource",
			 type: "POST",
			 data: data,
			 success: success,
			 dataType: dataType
		})
	})
	$("#pictureSub").on("click",function(){
		var formData=new FormData($('#pictureForm')[0]);
		formData.append("a","wjl");
	    $.ajax({
	        url: "subject_addPicture",
	        async: false,
	        type: 'POST',
	        cache: false,
	        data: formData,
	        processData: false,
	        contentType: false,
	        dataType:"json",
	        success : function(data) {
				alert("uploadPictureSuccess")
	        },
	    	error : function(data){
	    		alert("error")
	    	}
	    });
	})
	$("#video").on("click",function(){

	    $.ajax({
	        url: ctx + "/xxx/upload",
	        type: 'POST',
	        cache: false,
	        data: new FormData($('#infoLogoForm')[0]),
	        processData: false,
	        contentType: false,
	        dataType:"json",
	        beforeSend: function(){
	            uploading = true;
	        },
	        success : function(data) {
	            if (data.code == 1) {
	                $("#logo").attr("src", data.msg);
	            } else {
	                showError(data.msg);
	            }
	            uploading = false;
	        }
	    });
	})
})

/* function mychange(){
 	var files=$("#myinput")[0].files
	for(var i=0;i<files.length;i++){
		alert(files[i].name)
	} 
} */
</script>
<body>
<ul class="nav nav-tabs">
<li class="active"><a href="#textPane" data-toggle="tab">添加文本</a></li>
<li><a href="#picturePane" data-toggle="tab">添加图片</a></li>
<li><a href="#videoPane" data-toggle="tab">添加视频</a></li>
</ul>
<div class="tab-content">
<div class="tab-pane active" id="textPane">
<form>
<textarea rows="15" cols="120"></textarea><br/>
<button id="text" type="submit">提交</button>
</form>

</div>
<div class="tab-pane" id="picturePane">
<form id="pictureForm" enctype="multipart/form-data">
<input id="picture" name="picture" type="file">
<button id="pictureSub" type="button">提交</button>
</form>

</div>
<div class="tab-pane" id="videoPane">
<form>
<input type="file">
<button id="video" type="submit">提交</button>
</form>
</div>
</div>


<!-- <form id="myform" action="">
<input id="myinput" type="file" onchange="mychange()" multiple><br/>
<input id="mysub" type="submit">
</form> -->
</body>
</html>