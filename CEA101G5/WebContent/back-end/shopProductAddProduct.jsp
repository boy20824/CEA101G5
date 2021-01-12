<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
  ProductVO pVO = (ProductVO) request.getAttribute("pVO");

%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增商品</title>

<style>
  table#table-1 {
	background-color: #f3853d;
/*     border: 2px solid black; */
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
 	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align:center;
  }
  table, th, td {
/*      border: 1px solid #404040;  */
     border-spacing: 0; 
     background-color: #F6F6F6;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  tr td {
  	padding: 5px;
    text-align: center;
  }
  .button{
	  background-color:#FF615F;
	  border-radius: 5px;
	  border:1px;
	  color:white;
	  font-family: #606060;
	  text-decoration:none;
  }
  body{
  	magin:0;
  	padding:0;
  	text-align:center;
/*   	background-image: url("images/r1.jpg"); */
  	background-size: cover;
  	background-attachment: fixed;
  	background-position: center;
  	background-repeat: no-repeat;
  }
  .info{
  	text-align: center;
  	margin: 50px auto;
  }
  .tableborder{
  background-color: white;
  margin:20px;
  }
  #openp{
  background-color: #228b22;
  }
  .addC{
  display: none;
  }
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">

</script>
</head>
<body bgcolor='white'>

<a href="<%=request.getContextPath() %>/back-end/shopProductListAll.jsp" class="button">回首頁</a>
<br>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
${message}
		</c:forEach>
</c:if>
<!-- ACITION最好都加&GT=request.getContextPath() %%LT> -->
<table class="info">
	<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/shop/productcategory.do" name="form1">
		<tr>
			<td class="addC">
				<input type="text" name="categoryName">
				<input type="hidden" name="addCategory" value="yes">
				<input type="submit" value="新增分類" class="button">
			</td>
		</tr>
	</FORM>
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/shop/product.do" name="form1" enctype="multipart/form-data">
	<tr>
		<td>商品分類
		<jsp:useBean id="pcSvc" scope="page" class="com.productcategory.model.ProductCategoryService"/>
			<select name="categoryId" class="qq">
				<option>----請先選擇或新增分類----</option>
				<c:forEach var="pcVO" items="${pcSvc.allCategories}">
					<option value="${pcVO.categoryId }">${pcVO.categoryName}</option>
				</c:forEach>
				<option value ="ac">--------新增一個分類--------</option>
			</select>
			
		</td>
	</tr>
	<tr>
		<td>
<!-- 			<div display="block"> -->
<!-- 				<div id="photoArea"> -->
<!-- 					<div id="display" style="width: 100px;"></div> -->
<!-- 				</div> -->
<!-- 			</div> -->
	<input type="file" id="productPhoto" name="productPhoto" accept="image/*" size="45" multiple="multiple" /></td>
	</tr>
	<tr>
		<td>商品名稱 <input type="text" name="productName" value="${pVO.getProductName()}" /></td>
	</tr>
	<tr>
		<td>商品原價 <input type="text" name="productMSRP" value="${pVO.getProductMSRP()}" /></td>
	</tr>
	<tr>
		<td>商品售價 <input type="text" name="productPrice" value="${pVO.getProductPrice()}" /></td>
	</tr>
	
	<tr>
		<td>商品狀態
			<select name="productStatus">
<%-- 				<c:forEach var="arVO" items="${arSvc.getSearch(ss)}"> --%>
					<option value="0">停售中</option>
					<option value="1">上架中</option>
<%-- 				</c:forEach> --%>
			</select>
		</td>
	</tr>
	<tr>
		<td>商品描述<br><input type="text" name="productDescription" size="100" style="height: 100px;" value="<%= (pVO==null)? "" : pVO.getProductDescription()%>" /></td>
	</tr>
	<input type="hidden" name="productQtySold" value="0">
	<input type="hidden" name="action" value="add">

	
</table>
<input type="submit" value="新增商品" class="button">
</FORM>

	
	<script>
// 		window.onload = photoPreview;

// 		function photoPreview() {
// 			let display = document.getElementById("display");
// 			let photo = document.getElementById("productPhoto");

// 			photo.addEventListener("change", function() {
// 				for (let i = 0; i < this.files.length; i++) {
// 					let file = this.files[i];
// 					if (file.type.indexOf('image') > -1) {
// 						let reader = new FileReader();
// 						reader.addEventListener('load', function(e) {
// 							//新增div(照片預覽)
// 							let oneSet = document.createElement('div');
// 							oneSet.setAttribute("id", "oneSet");
// 							oneSet.setAttribute("name", "oneSet");
// 							display.append(oneSet);
// 							//新增照片
// 							let img = document.createElement('img');
// 							img.src = e.target.result;
// 							img.setAttribute("name", "img");
// 							img.setAttribute("width", "100px");
// 							img.setAttribute("style", "float:left");
// 							oneSet.append(img);
// 						});
// 						reader.readAsDataURL(file);
// 					} else {
// 						alert("plz upload an img")
// 					}
// 					//只show當前上傳圖片
// 					let sets = document.getElementsByName("oneSet");
// 					while (sets.length > 0) {
// 						sets[0].remove();
// 					}
// 				}
// 			});
// 		}
	</script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!-- <link rel="stylesheet" href="/resources/demos/style.css" /> -->
    <script src="https://kit.fontawesome.com/ab82f2141f.js" crossorigin="anonymous"></script>
    <script>
    	$(".qq").change(function(){
    		var qq=$(".qq option:selected");
    		if (qq.val() === "ac"){
    			var fq = document.getElementsByClassName("addC");
        		for (var i=0; i<fq.length; i++){
        			fq[i].style.display = 'block';
        		}
    		}
    		if (qq.val() !== "ac"){
    			var fq = document.getElementsByClassName("addC");
        		for (var i=0; i<fq.length; i++){
        			fq[i].style.display = 'none';
        		}
    		}
    	});
		
    </script>
</body>
</html>