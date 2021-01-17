<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.foodorderdetail.model.*"%>
<%@ page import="com.foodorder.model.*"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	FoodOrderService foodOrderSvc = new FoodOrderService();
	List<FoodOrderVO> list = foodOrderSvc.getAllByStoreId(((RestaurantVO)session.getAttribute("storeLogin")).getStoreId());
	pageContext.setAttribute("list", list);
%>
<!-- 呼叫訂單明細 -->
<jsp:useBean id="foodOrderDetailSvc" scope="page" class="com.foodorderdetail.model.FoodOrderDetailService" />
<!-- 呼叫餐點 -->
<jsp:useBean id="menuSvc" scope="page" class="com.menu.model.MenuService" />
<!-- 呼叫會員 -->
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemService" />


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="orderlistcss.css" />
    <script src="orderlistjs.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/animate.css@4.0.0/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <style>
    body {
  font-family: "Open Sans", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Helvetica, Arial, sans-serif; }
    </style>
</head>

<body>

<input type="hidden" class="storeId" value="<%=((RestaurantVO)session.getAttribute("storeLogin")).getStoreId()%>">



<%@include file="../selectpage/selectstorepage.jsp" %>
<style>
.sidebar label{
	line-height:80px;
}
</style>
    <div class="storeDetail">
        <h1>訂單清單</h1>
        <div class="nav" style="display:flex;justify-content: space-between; color:#2775b6">
        <style>
        	.nav:hover{
        		cursor:pointer;
        	}
        </style>
        <div class="all">
        	全部
        	</div >
        <div class="do">
        	製作中
        	</div >
        	<div class="OK">
        	已完成
        	</div >
        	<div class="OK1">
        	已取餐
        	</div>
        	<div class="notOK">
        	遲到未取餐
        	</div>
        </div>
       
        <c:forEach var="foodOrderVO" items="${list}">
        <div class="list animate__animated animate__flipInX">
            <p class="orderMenu">訂單明細
            <span  class="statusName" style="margin-left:1.5em;color:orange">
           	<c:choose>
    			<c:when test="${foodOrderVO.foodOrderStatus == 1}">已完成</c:when>
    			<c:when test="${foodOrderVO.foodOrderStatus == 2}">已取餐</c:when>
    			<c:when test="${foodOrderVO.foodOrderStatus == 3}">遲到未取餐</c:when>
    			<c:otherwise>製作中</c:otherwise>
			</c:choose>
            </span>
            </p>
            <div class="menu">
             <c:set var="total" value="${0}" />
				<c:forEach var="foodOrderDetailVO" items="${foodOrderDetailSvc.getAll(foodOrderVO.getFoodOrderId())}">
					<c:set var="total" value="${total + foodOrderDetailVO.menuNum * foodOrderDetailVO.menuPrice}" />
				</c:forEach>
                <p><span>電話:<span>${foodOrderVO.memPhone}</span></span><span>會員名稱:<span>${memSvc.getOneMem(foodOrderVO.memPhone).memName }</span></span><span>訂單總價:$<span>${total}</span></span>時間:<span style="width:10em;"><fmt:formatDate value="${foodOrderVO.foodOrderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
                <table>
                    <tr>
                        <th>餐點名稱:</th>
                        <th>餐點數量:</th>
                        <th>餐點價格:</th>
                    </tr>
                    <c:forEach var="foodOrderDetailVO" items="${foodOrderDetailSvc.getAll(foodOrderVO.getFoodOrderId())}">
                    <tr>
                        <td>${menuSvc.getOneMenu(foodOrderDetailVO.getMenuId()).menuName}</td>
                        <td>${foodOrderDetailVO.menuNum}</td>
                        <td>${foodOrderDetailVO.menuPrice}</td>
                    </tr>
                    </c:forEach>
                </table>
                <form method="post" name="form2" action="<%=request.getContextPath()%>/foodorder/FoodOrderServlet.do">
                    <label for="status1">取餐狀態:</label>
					<!--要判斷訂單狀態 -->
                    <input type="radio" id="status1" class="status" name="status" value="1" ${(foodOrderVO.foodOrderStatus>='1')?'disabled':'' }><label for="status1">已完成</label>
                    <input type="radio" id="status2" class="status" name="status" value="2" ${(foodOrderVO.foodOrderStatus>='2')?'disabled':'' }><label for="status2">已取餐</label>
                    <input type="radio" id="status3" class="status" name="status" value="3" ${(foodOrderVO.foodOrderStatus>='3')?'disabled':'' }><label for="status3">未取餐</label>
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="foodOrderId" value="${foodOrderVO.foodOrderId}">
                    <input type="hidden" name="memPhone" value="${memSvc.getOneMem(foodOrderVO.memPhone).memPhone }">
                    <input type="hidden" name="memEmail" value="${memSvc.getOneMem(foodOrderVO.memPhone).memEmail }">
                </form>
            </div>
        </div>
        </c:forEach>
    </div>
<!--     前端餐點狀態分類 -->
     		<script>
            	let statusName = document.querySelectorAll('.statusName')
            	let list = document.querySelectorAll('.list')
            	$('.all').click(function(){
            		
            		for(let i=0;i<statusName.length;i++){
            			list[i].style.display="block"
            			
            		}
            	})
            	$('.do').click(function(){
            		
            		for(let i=0;i<statusName.length;i++){
            			list[i].style.display="block"
            			if(statusName[i].innerText!=="製作中"){
            				list[i].style.display="none";
            			}
            		}
            	})
            	$('.OK').click(function(){
            		
            		for(let i=0;i<statusName.length;i++){
            			list[i].style.display="block"
            			if(statusName[i].innerText!=="已完成"){
            				list[i].style.display="none";
            			}
            		}
            	})
            	$('.OK1').click(function(){
            	
            		for(let i=0;i<statusName.length;i++){
            			list[i].style.display="block"
            			if(statusName[i].innerText!=="已取餐"){
            				list[i].style.display="none";
            			}
            		}
            	})
            	$('.notOK').click(function(){
            		
            		for(let i=0;i<statusName.length;i++){
            			list[i].style.display="block"
            			if(statusName[i].innerText!=="遲到未取餐"){
            				list[i].style.display="none";
            			}
            		}
            	})
            	
            </script>
<script>
var storeId = document.querySelector('.storeId');
var MyPoint = "/TogetherWS/"+storeId.value;
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

var webSocket;

window.addEventListener('load',function(){
	
	webSocket = new WebSocket(endPointURL);
		
	webSocket.onmessage = function(event) {
		
		Swal.fire({
			  title: '您有新的訂單產生',
			  showClass: {
			    popup: 'animate__animated animate__fadeInDown'
			  },
			  hideClass: {
			    popup: 'animate__animated animate__fadeOutUp'
			  }
			}).then(function(result) {
	               if (result.value) {
	            	   location.reload(true);
	               }
	               else {
	            	   location.reload(true);
	               }
			
})
	}
})
	
	window.addEventListener('unload',function(){
		webSocket.close();
	})
		
</script>

<script>
	let status = document.querySelectorAll('.status');
	for(let i=0 ;i<status.length;i++){
		status[i].addEventListener('change',function(){
			let ans =confirm("確定要改變餐點狀態??");
			if(ans){
				let form = document.querySelectorAll("form[name=form2]")
				form[Math.floor(i/3)].submit();
			}
	})
}
</script>

</body>
</html>