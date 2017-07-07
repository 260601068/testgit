<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap-treeview.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<script src="js/jquery-2.2.1.min.js"></script>
<script src="js/bootstrap-treeview.js"></script>   
<script src="js/bootstrap.min.js"></script>   
<script type="text/javascript">
  function getTree(){
 	var jsonStr=$("label").text();
	var data=$.parseJSON(jsonStr); 
	return data
}
$(function(){
	$("#tree").treeview({
		data:getTree(),
	    levels : 100,
		})
$('#tree').on('nodeSelected', function(event, data) {
$(".btn-lg").trigger("click")
	$("#parentComId").val(data.id)
});   
$("#addComment").click(function(){
	$(".btn-lg").trigger("click")
})
})
function subComment(){
	var parentComId=$("#parentComId").val();
	var subId=$("#subId").val();
	var comBy=$("#comBy").val();
	var comByName=$("#comByName").val();
	var content=$("#comContent").val();
	$.ajax({
		type:"post",
		url:"comment_subComment.action",
		data:{"parent_com_id":parentComId,"sub_id":subId,"com_content":content,"com_by":comBy,"com_by_name":comByName},
		success:function(result){
			location.reload()
		},
		error:function(msg){
			alert("error")
		}
	})
	
}

function deleteComment(){
	var comId=$("#parentComId").val();
	$.ajax({
		type:"post",
		url:"comment_deleteComment.action",
		data:{"com_id":comId},
		success:function(result){
			location.reload()
		},
		error:function(msg){
			alert("error")
		}
	})
}
</script>
<body>
<s:debug></s:debug><hr/>
<h4 style="text-align:right">欢迎${session.user.user_name }!</h4>
<h1 style="text-align:center">${subject.title }</h1>
<label style="display: none">${comment_list_treeview}</label>
<div id="tree"></div>
<button class="btn btn-primary btn-lg" data-toggle="modal" style="display:none"
   data-target="#myModal">
   开始演示模态框
</button>
 
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               对当前论评进行回复或删除
            </h4>
         </div>
         <div class="modal-body">
         <input id="parentComId" type="hidden">
         <input id="subId" type="hidden" value="${subject.sub_id }">
         <input id="comBy" type="hidden" value="${session.user.user_id }">
         <input id="comByName" type="hidden" value="${session.user.user_name }">
         	评论内容：<textarea id="comContent" rows="10" cols="90"></textarea>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default"
               data-dismiss="modal" onclick="deleteComment()">删除
            </button>
            <button type="button" class="btn btn-default"
               data-dismiss="modal" onclick="subComment()">回复
            </button>
         </div>
      </div>
</div>
</div>
<button id="addComment" style="background:lightblue">回复新评论</button>
</body>
</html>