<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurant.model.*"%>

<%
 	MenuService menuSvc = new MenuService(); //創建 實體
    List<MenuVO> list = menuSvc.getAllByStoreId(((RestaurantVO)session.getAttribute("storeLogin")).getStoreId());//呼叫DAO並執行getAll()取得VO為每一列資訊的所有欄位並裝入List集合<泛型只能裝取該VO型別>;
    Iterator it = list.iterator();
    while(it.hasNext()){
    	MenuVO m = (MenuVO)it.next();
    	if(m.getMenuSellStatus().equals(0)){
    		it.remove();
    	}
    }
    pageContext.setAttribute("list",list); //存在JSP的最小Scope命名為list
%>
<html>
<head>
<%@include file="../selectpage/selectstorepage.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-store-end/menu/menulistcss.css" />
  </head>
  <body>
  <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
  <%@ include file="page1.file" %>  <!-- include先省略 -->
  <c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
  <c:if test="${menuVO.menuSellStatus == 1}">
    <div class="card">
      <img src="<%=request.getContextPath()%>/menu/MenuServlet.do?menuId=${menuVO.menuId}&action=getOnePicture" alt="" />
      <div class="detail">
        <h1>${menuVO.menuName}</h1>
        <h2>${menuVO.menuChar}</h2>
        <input type="hidden" name="menuName" value="${menuVO.menuName}">
        <div class="detail-word">
          	${menuVO.menuDetail}
        <input type="hidden" name="menuDetail" value="${menuVO.menuDetail}">
        </div>
        <div class="price">
            <span>${menuVO.menuPrice}</span>
            <input type="hidden" name="menuPrice" value="${menuVO.menuPrice}">
          <img src="<%=request.getContextPath()%>/front-store-end/menu/dollar-sign-solid.svg" alt="" />
          
        </div>
        <div class="bth">
          <form method="post"  ACTION="<%=request.getContextPath()%>/menu/MenuServlet.do">
          <input type="submit" value="編輯餐點" />
          <input type="hidden" name="menuId"  value="${menuVO.menuId}">
		  <input type="hidden" name="action"	value="getOne_For_Update">
          </form>
          
          <FORM name="form2" method="post" class="table2" action="<%=request.getContextPath()%>/menu/MenuServlet.do"  enctype="multipart/form-data">
           <input type="hidden" name="menuDetail" value="${menuVO.menuDetail}">
           <input type="hidden" name="menuPrice" value="${menuVO.menuPrice}">
           <input type="hidden" name="menuName" value="${menuVO.menuName}">
           <input type="hidden" name="menuChar" value="${menuVO.menuChar}">
           <input type="hidden" name="menuId" value="${menuVO.menuId}">
           <input type="hidden" name="storeId" value="${menuVO.storeId}">
           <input type="hidden" name="menuStatus" value="${menuVO.menuStatus}">
           <input type="hidden" name="menuPic" value="">
           <input type="hidden" name="action" value="update">
           <input type="hidden" name="menuSellStatus" value="0">
           <input type="button" value="下架餐點" class="menuSellStatus" />
           </FORM>
           
        </div>
      </div>
    </div>
    </c:if>
    	</c:forEach>
<%@ include file="page2.file" %>
<script>
window.addEventListener('load',function(){

	let menuSellStatus = document.querySelectorAll(".menuSellStatus")
	for(let i=0;i<menuSellStatus.length;i++){
		menuSellStatus[i].addEventListener("click",function(e){
			let ans =confirm("確定要下架餐點??");
			if(ans){
				let form = document.querySelectorAll("form[name=form2]")
				form[i].submit();
				
			}
		})
	}
	
})


</script>
  </body>
</html>