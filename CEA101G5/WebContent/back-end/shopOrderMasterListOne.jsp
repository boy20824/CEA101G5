<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	String orderId = request.getParameter("orderId");
	if (orderId != null){	//第一頁從input來的參數不會=null, 所以存到session裡
		session.setAttribute("orderId", orderId);
	}else{					//第二頁從input來的參數不見了, 所以要取剛剛存到session裡的參數
		orderId = (String) session.getAttribute("orderId");
	}
	
	Integer oid = 0;
	try{oid = new Integer(orderId);}
	catch(Exception e){}
	OrderMasterService omSvc = new OrderMasterService();
	OrderMasterVO omVO = omSvc.getOrderById(oid);
	List<String> errorMsgs = new LinkedList<String>();
	request.setAttribute("errorMsgs", errorMsgs);
	if (omVO == null) {
		errorMsgs.add("查無資料");
	}
	if (!errorMsgs.isEmpty()) {
		RequestDispatcher failureView = request
				.getRequestDispatcher("/back-end/shopOrderMasterListAll.jsp");
		failureView.forward(request, response);
		return;
	}
%>

<html>
<head>
<title>所有商品訂單</title>

<style>

  table#table-1 {
	background-color: #f3853d;
    border: 2px solid black;
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
 	width: 900px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align:center;
  }
  table, th, td {
     border: 1px solid #404040; 
     border-spacing: 0; 
     background-color: #F6F6F6;
  }
  th, td {
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
</style>

</head>
<body bgcolor='white'>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="tableborder">

<table class="info">
	<tr>
		<th>訂單編號</th>
		<th>訂單日期</th>
		<th>會員編號</th>
		<th>收貨人姓名</th>
		<th>行動電話</th>
		<th>室內電話</th>
		<th>收貨人電子郵件</th>
		<th>統一編號</th>
		<th>配送方式</th><!-- 0:宅配-實體商品 1:電子郵件寄送-虛擬商品 -->
		<th>配送地址</th>
		<th>訂單備註</th>
		<th>訂單金額</th>
		<th>結帳日期</th>
		<th>配送時間</th>
		<th>訂單狀態</th><!-- 0:等待付款中 1:訂單已確認 2:訂單處理中 3:訂單已出貨 4:取消 -->
		
		<th>送出修改</th>
		<th>查看明細</th>
	</tr>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop/ordermaster.do" style="margin-bottom: 0px;">
			<tr>
				<td><%=omVO.getOrderId()%></td>
				<td><fmt:formatDate value="<%=omVO.getOrderDate()%>" pattern="yyyy-MM-dd"/></td>
				<td><%=omVO.getMemPhone()%></td>
				<td><%=omVO.getRecipientName()%></td>
				<td><%=omVO.getRecipientMobNumber()%></td>
				<td><%=omVO.getRecipientTelNumber()%></td>
				<td><%=omVO.getRecipientEmail()%></td>
				<td><%=omVO.getBusinessNumber()%></td>
				<td>
					<c:if test="<%=omVO.getDeliveryMethod()==0%>">
						宅配
					</c:if>
					<c:if test="<%=omVO.getDeliveryMethod()==1%>">
						電子郵件寄送
					</c:if>
				</td>
				<td><%=omVO.getDeliveryAddress()%></td>
				<td><%=omVO.getOrderMemo()%></td>
				<td><%=omVO.getInvoicePrice()%></td>
				<td><fmt:formatDate value="<%=omVO.getInvoicePaidDate()%>" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="<%=omVO.getDeliveryTime()%>" pattern="yyyy-MM-dd"/></td>
				
				
				<td>
					<select name="orderStatus">
						<c:if test="<%=omVO.getOrderStatus()==0%>">
							<option value=0 selected>等待付款中</option>
							<option value=1>訂單已確認</option>
							<option value=2>訂單處理中</option>
							<option value=3>訂單已出貨</option>
							<option value=4>取消</option>
						</c:if>
						<c:if test="<%=omVO.getOrderStatus()==1%>">
							<option value=0>等待付款中</option>
							<option value=1  selected>訂單已確認</option>
							<option value=2>訂單處理中</option>
							<option value=3>訂單已出貨</option>
							<option value=4>取消</option>
						</c:if>
						<c:if test="<%=omVO.getOrderStatus()==2%>">
							<option value=0>等待付款中</option>
							<option value=1>訂單已確認</option>
							<option value=2 selected>訂單處理中</option>
							<option value=3>訂單已出貨</option>
							<option value=4>取消</option>
						</c:if>
						<c:if test="<%=omVO.getOrderStatus()==3%>">
							<option value=0>等待付款中</option>
							<option value=1>訂單已確認</option>
							<option value=2>訂單處理中</option>
							<option value=3 selected>訂單已出貨</option>
							<option value=4>取消</option>
						</c:if>
						<c:if test="<%=omVO.getOrderStatus()==4%>">
							<option value=0>等待付款中</option>
							<option value=1>訂單已確認</option>
							<option value=2>訂單處理中</option>
							<option value=3>訂單已出貨</option>
							<option value=4 selected>取消</option>
						</c:if>
					</select>
				</td>
				<td>
						<input type="submit" value="送出修改	" class="button">
					    <input type="hidden" name="action" value="updateom">
					    <input type="hidden" name=orderId value="<%=omVO.getOrderId()%>">
					    <input type="hidden" name=searchyn value="yes">
					    
				</td>
				</FORM>
				<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/shopOrderDetailListOne.jsp" >
					<td>
							<input type="submit" value="查看明細" class="button">
						    <input type="hidden" name="action" value="getDetail">
						    <input type="hidden" name=orderId value="<%=omVO.getOrderId()%>">
					</td>
				</FORM>
			</tr>
			
		
</table>

</div>
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/shopOrderMasterListOne.jsp" >
	<b>依訂單編號搜尋:</b>
	<input type="text" name="orderId">
	<input type="submit" value="送出" class="button">
</FORM>
</body>
</html>