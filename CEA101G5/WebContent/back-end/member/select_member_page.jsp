<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Enak Member: Home</title>

<style>
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
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Enak Member: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Enak Member: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMem.jsp'>List</a> all Mems.  <br><br></li>
</ul>

<ul>
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/mem.do" >
        <b>輸入會員手機號碼 (如0921842851):</b>
        <input type="text" name="memPhone">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
</ul>

  <jsp:useBean id="memSvc" scope="page" class="com.member.model.MemService" />
<ul>  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/mem.do" >
       <b>選擇會員手機號碼:</b>
       <select size="1" name="memPhone">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.memPhone}">${memVO.memPhone}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
 </ul>
 
 <ul>
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/mem.do" >
       <b>選擇會員姓名:</b>
       <select size="1" name="memPhone">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.memPhone}">${memVO.memName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>會員管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-customer-end/member/addMem.jsp'>Add</a> a new Mem.</li>
</ul>

</body>
</html>