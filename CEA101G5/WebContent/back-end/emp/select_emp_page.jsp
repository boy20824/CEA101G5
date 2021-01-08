<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>
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

<!-- <table id="table-1"> -->
<!--    <tr><td><h3>IBM Emp: Home</h3><h4>( MVC )</h4></td></tr> -->
<!-- </table> -->

<!-- <p>This is the Home page for IBM Emp: Home</p> -->

<h3 align="center">員工資料查詢</h3>
	
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
</div>
<br><br>
<ul>
<!--   <li><a href='listAllEmp.jsp'>List</a> all Emps.  <br><br></li> -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #e3f2fd;">
    <a class="nav-link active" href="<%=request.getContextPath()%>/back-end/back-emplistall.jsp">查詢員工</a>
    <a class="nav-link" href="<%=request.getContextPath()%>/back-end/back-empupdate.jsp">新增員工</a>
    
     <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
<!--     <a class="navbar-brand" href="#">Hidden brand</a> -->
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
      </li>
      <li class="nav-item">
      </li>
      <li class="nav-item">
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/emp/emp.do">
      <b>輸入員工編號 (如EMP0001):</b>
        <input type="text" name="emp_id" class="form-control mr-sm-2">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" class="btn btn-danger">
    </form>
  </div>
</nav>
  

  <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
     <br>
       <br>
  <li>
     <FORM  METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/emp/emp.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="emp_id">
         <c:forEach var="empVO" items="${empSvc.getAll()}" > 
          <option value="${empVO.emp_id}">${empVO.emp_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="btn btn-danger">
    </FORM>
  </li>
  <br>
    <br>
  <li>
     <FORM  METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/emp/emp.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="emp_id">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.emp_id}">${empVO.emp_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出"  class="btn btn-danger">

       
     </FORM>
  </li>
</ul>

</body>
</html>