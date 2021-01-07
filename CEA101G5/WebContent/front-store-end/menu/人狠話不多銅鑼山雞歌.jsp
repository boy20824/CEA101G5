<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="#" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<div class="show"></div>
<script>
$.ajax({
	url:"<%=request.getContextPath()%>/menu/MenuServlet.do?menuChar=主餐&action=menuAjax",
				type : "GET",
				dataType : "json",
				success : function(data) {
					show(data)
				}
			})
	//神魔的子民 偉成到此一遊
	function show(data) {
		// 	$(".show").html(data);
		for (myDataRow in data) {
			let myData = data[myDataRow];
			$.each(myData, function(key, value) {
				$(".show").append("<a>" + key +"  :  " +"</a>");
				$(".show").append("<a>" + value + "</a>");
				$(".show").append("<br>");
			});
			$(".show").append("<br>");
		}

	}
</script>
</body>
</html>