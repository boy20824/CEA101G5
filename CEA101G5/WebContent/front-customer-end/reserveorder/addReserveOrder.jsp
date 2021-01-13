<%@page import="java.sql.Date"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reserveorder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
  ReserveOrderVO reserveOrderVO = (ReserveOrderVO) request.getAttribute("reserveOrderVO");
//   String ss = "S000003";
//   request.setAttribute("ss",ss);
  String ss = request.getParameter("storeId");
  System.out.println(ss);
%>
<jsp:useBean id="arSvc" scope="page" class="com.acceptreserve.model.AcceptReserveService"/>
<jsp:useBean id="rsSvc" scope="page" class="com.reservesituation.model.ReserveSituationService"/>
<jsp:useBean id="rSvc" scope="page" class="com.restaurant.model.RestaurantService"/>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<title>訂位</title>
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
/*   table { */
/* 	width: 450px; */
/* 	background-color: white; */
/* 	margin-top: 1px; */
/* 	margin-bottom: 1px; */
/*   } */
/*   table, th, td { */
/*     border: 0px solid #CCCCFF; */
/*   } */
/*   th, td { */
/*     padding: 1px; */
/*   } */
  .ui-datepicker {
    width: 17em;
    padding: .2em .2em 0;
    display: none;
    margin: 2px auto;
}
	.pickPeopleAndNote{
	margin:2px auto;
	background-color: #F6F6F6;
/* 	width: 600px; */
	}
	.bigbutton{
	position: relative;
	width:100px;
	height:35px;
	background-color:#FF615F;
	  border-radius: 5px;
	  border:1px;
	  color:white;
	  font-family: #606060;
	  text-decoration:none;
	  font-size: 20;
	}
	body{
  	text-align:center;
  	background-size: cover;
  	background-attachment: fixed;
  	background-position: center;
  	background-repeat: no-repeat;
  }
  #backblock {
    background-color: #F6F6F6;
    width: 620px;
    margin-left: 50%;
    position: relative;
    right: 350px;
}
tr .radio-item {
    position: relative;
    width: 66px;
    height: 22px;
    line-height: 22px;
    float: left;
    margin-right: 6px;
}

tr .radio-item input {
    width: 60px;
    height: 22px;
    opacity: 0;
    appearance: none;
    position: absolute;
    left: 0;
    top: 0;
}

tr .radio-item label {
    position: absolute;
    left: 0;
    top: 0;
    width: 60px;
    height: 18px;
    line-height: 18px;
    padding: 2px 3px;
    background-color: #FF615F;
    border-radius: 3px;
    z-index: -1;
    display: flex;
    align-items: center;
}

tr .radio-item label i {
    display: inline-block;
    margin-left: 3px;
}

tr .radio-item label i.disc {
    width: 4px;
    height: 4px;
    background: #333;
    border-radius: 100%;
}

tr .radio-item label i.active {
    width: 6px;
    height: 3px;
    border-left: 1px solid #FFFFFF;
    border-bottom: 1px solid #FFFFFF;
    transform: rotate(-45deg);
    display: none;
}

tr .radio-item span {
    margin-left: 32px;
}

tr .radio-item input:checked + label {
    background: #3698DB;
    color: #FFFFFF;
}

tr .radio-item input:checked + label i.disc {
    display: none;
}

tr .radio-item input:checked + label i.active {
    display: block;
}

tr .radio-item input:checked + label + span {
    color: #FF615F;
}	
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">

</script>
</head>
<body bgcolor='white'>

<br>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div id="backblock">
<div id="f_date1"></div>

<!-- ACITION最好都加&GT=request.getContextPath() %%LT> -->
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/reserveorder/reserveorder.do" name="form1">

<table class="pickPeopleAndNote">
	<tr><!-- 餐廳號 -->
		<td><input type="hidden" name="storeId" size="45" 
			 value="<%=ss %>" /></td> <!-- value 若空值=吳永志 , 否則=empVO.getEname() -->
	</tr>
	<tr><!-- 會員電話 -->
		<td><input type="hidden" name="memphone" size="45"
			 value="${memLogin.memPhone}" /></td>
	</tr>
		<td><input name="reservetime" id="zzz" type="hidden" class="bookingDate"></td>
	<tr>
		<td>大人人數:
			<td><select name="reserveadult" size="1" id="adult">
				<option></option>
				<option value=1>1</option>
				<option value=2>2</option>
				<option value=3>3</option>
				<option value=4>4</option>
				<option value=5>5</option>
				<option value=6>6</option>
				<option value=7>7</option>
				<option value=8>8</option>
				<option value=9>9</option>
				<option value=10>10</option>
			</select></td>
		</td>
	</tr>
	<tr><td>選擇時段 :</td></tr>
	<tr id="periodselect">
	</tr>
<!-- 	<tr> -->
<!-- 		<select id="show"></select> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>時段:</td> -->
<!-- 		<td><select name="periodid" size="1"> -->
<%-- 			<c:forEach var="rsVO" items="${rsSvc.getSearch(dd,ss)}"> --%>
<%-- 				<c:if test="${2 < rsVO.acceptGroups * 2}"> --%>
<%-- 					<option value="${rsVO.periodId}"> --%>
<%-- 					<fmt:formatDate value="${arSvc.getOneAcceptReserve(rsVO.storeId,rsVO.periodId).startTime}" pattern="HH:mm"/> --%>
<!-- 					</option> -->
<%-- 				</c:if> --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
	<tr>
		<td><input type="hidden" name="reservestatus" size="45"
			 value="<%= (reserveOrderVO==null)? "0" : reserveOrderVO.getReserveStatus()%>" /></td>
	</tr>
	
	<tr>
		<td>特殊需求:</td>
		<td><input type="TEXT" name="reservenote" size="45" placeholder="如 : 過敏食材, 嬰兒座椅...."
			 value="<%= (reserveOrderVO==null)? "" : reserveOrderVO.getReserveNote()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>訂位訂單產生時間:</td> -->
<!-- 		<td><input type="TEXT" name="reservecreatetime" size="45" -->
<%-- 			 value="<%= (reserveOrderVO==null)? "2020-12-13 10:10:10" : reserveOrderVO.getReserveCreateTime()%>" /></td> --%>
<!-- 	</tr> -->
	

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
	<tr>
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<!-- 				上面都用JAVA的三元運算式寫, 但部門是select選項要用EL寫法(因為一開始是空值)  上面其他輸入一開始空值無所謂  但這邊選項一開始就需要取到資料庫的部門名稱否則500 -->
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
	</tr>

</table>
<br>
<input type="hidden" name="reservechild" value=0>
<input type="hidden" name="action" value="insertro">
<input type="submit" value="確認" class="bigbutton"></FORM>
</div>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!-- <link rel="stylesheet" href="/resources/demos/style.css" /> -->
    <script src="https://kit.fontawesome.com/ab82f2141f.js" crossorigin="anonymous"></script>
<script>
	$(function() {
	    $("#f_date1").datepicker({
	        showOn: "button",
// 	        buttonImage: "images/",
// 	        buttonImageOnly: true,
	        numberOfMonths: 2,
	        dateFormat: "yy-mm-dd",
	        showButtonPanel: true,
	        minDate: +1,
	        maxDate: "+0M +${rSvc.getOneRestaurant(ss).storeFinalReservDate}D",
	        onSelect: function(date) {
	            $(".bookingDate").val(date);
	     		hi();	//直接在這邊執行hi()就能觸發了*****感謝輯神
	        }
	    });
	});
	let adtf = false;
	let ddtf = false;
	$("#adult").change(function(){	//就算你不用匿名函式還是要這樣寫  語法問題
		//還是這邊CHANGE給一個判斷  下面再判斷要不要執行hi()
		adtf = true;
		hi()
	})
		
//  	$("#zzz").change(function(){	//我這現在這邊change沒反應, 而且我改時段select就一直跳出來(我是不是在迴圈開始時把DOM元素刪除就好?)
//  		ddtf = true;
//  		hi();
// 	})
// 	if(adtf === true && ddtf === true){
// 		console.log("doublechange");
// 		let controll = false;
// 		hi();
// 	}
	let controll = false;
	function hi(){
 		let adnum = document.getElementById("adult").value;
 		let dd = document.getElementById("zzz").value;
// 		console.log(dd);
		let ss = "S000003";
 		if (adnum != null && dd != null){ //ById找不到回傳null sByClassName找不到回傳空陣列
 			$.ajax({
 				url: "<%=request.getContextPath()%>/reservesituation/reservesituation.do?action=searchacg",
 				type: "POST",
 				data: {
 					//這是用來給你getParameter ("date")   值是dd
 					date : dd,
 					picknum : adnum,
 					storeid : ss,
 					},
 					success: function(msg){//傳回字串
//  						console.log(msg)
 						var myParent = document.getElementById("periodselect");
						//把msg字串轉成物件
						var objJsonArray = JSON.parse(msg);
// 						console.log(objJsonArray['2']);
// 						console.log(Object.keys(objJsonArray));//這是個Object
						
						//Create and append select list
// 				        var selectList = document.createElement("select");
// 				        selectList.id = "mySelect";
// 				        selectList.name = "periodid";
// 				        myParent.appendChild(selectList);
				        
				        
// 						for (let i = 0; i < Object.keys(objJsonArray).length; i++){
// 				            var option = document.createElement("option");
// 				            option.value = Object.keys(objJsonArray)[i];//值是時段編號
// 				            option.text = objJsonArray[Object.keys(objJsonArray)[i]];//顯示是開始時間
// 				            selectList.appendChild(option);
// 						}
						
						//嘗試做成單選按鈕
						//把舊的刪掉
						if (controll === true){
// 							var ddiv = document.getElementsByClassName("radio-item");
// 							var dNode = myParent.removeChild(ddiv);
							while(myParent.firstChild){
								myParent.removeChild(myParent.firstChild);
							}

						}
						var td = document.createElement("td");
						td.className = "choose"
						myParent.appendChild(td);
// 						td.innerHTML = "選擇時段: ";
						for (let i = 0; i < Object.keys(objJsonArray).length; i++){
							var div = document.createElement("div");
							div.className = "radio-item";
// 							div.style.display = "flex";
// 							div.style.flex-direction = "row";
// 							div.style.justify-content = "flex";
							td.appendChild(div);
				            var radio = document.createElement("input");
				            var label = document.createElement("label");
// 				            label.style["background-color"] = "red";
				            var ib = document.createElement("i");
				            ib.className = "disc";
				            var ib2 = document.createElement("i");
				            ib2.className = "active";
				            var span = document.createElement("span");
							radio.type = "radio";
// 				            radio.id = "myRadio";
				            radio.name = "periodid";//名稱為periodid
				            radio.value = Object.keys(objJsonArray)[i];//值是時段編號
				            div.appendChild(radio);
				            div.appendChild(label);
				            label.appendChild(ib);
				            label.appendChild(ib2);
				            div.appendChild(span);
				            span.innerHTML = objJsonArray[Object.keys(objJsonArray)[i]];
// 				            console.log(typeof(div));
				            controll = true;
 						}

 					}
 			})
 		}
	};
// 	function setUserID(myValue) {
// 		$('.bookingDate').val(myValue).trigger('change');
// 	}
</script>
    
</html>