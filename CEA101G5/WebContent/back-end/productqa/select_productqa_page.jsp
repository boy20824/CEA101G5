<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

	<link rel="stylesheet" type="text/css" href="css/bootstrap-grid.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-reboot.min.css">
<style>
a {
    color:#FF615E;
}
 li {list-style-type:none;
 text-align: center;
 }
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
</head>
<body bgcolor='white' >
<%-- 錯誤表列 --%>
<div align="center">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<br><br><br>
<li><a href='<%=request.getContextPath() %>/back-end/productqa/listAllProductQA.jsp'>查看全部問答 </a>  <br><br></li>
<li><a href='<%=request.getContextPath() %>/back-end/productqa/listAllProductQANull.jsp'>未回覆 </a>  <br><br></li>
</div>
<br><br>
<ul>
  
  <li>
    <FORM  METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/productqa/productqa.do" >
        <b>輸入問與答編號 (如1):</b>
        <input type="text" name="pqaId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" class="btn btn-danger">
    </FORM>
  </li>

  <jsp:useBean id="productQASvc" scope="page" class="com.productqa.model.ProductQAService" />
     <br>
       <br>
  <li>
     <FORM  METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/productqa/productqa.do" >
       <b>選擇問與答編號:</b>
       <select size="1" name="pqaId">
         <c:forEach var="productQAVO" items="${productQASvc.allQA}" > 
          <option value="${productQAVO.pqaId}">${productQAVO.pqaId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="btn btn-danger">
    </FORM>
  </li>
  
  <br>
    <br>
<!--       <li> -->
<%--      <FORM  METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/productqa/productqa.do" > --%>
<!--        <b>選擇員工編號:</b> -->
<!--        <select size="1" name="pqaId"> -->
<%--          <c:forEach var="productQAVO" items="${productQASvc.AllQA}" >  --%>
<%--           <option value="${productQAVO.pqaId}">${productQAVO.productId} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出" class="btn btn-danger"> -->
<!--     </FORM> -->
<!--   </li> -->
</ul>
<ul>
  <li><a href='<%=request.getContextPath() %>/back-end/productqa/addProductQA.jsp'>Add</a> a new ProductQA.</li>
</ul>

<!-- <h3>員工管理</h3> -->

<!-- <ul> -->
<!--   <li><a href='addEmp2.jsp'>Add</a> a new Emp.</li> -->
<!-- </ul> -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
</body>
</html>