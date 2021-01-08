<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ page import="java.util.*"%>
            <%@ page import="com.storechardetail.model.*"%>

                <%
StoreCharDetailService storeCharDetailSvc = new StoreCharDetailService();
List<StoreCharDetailVO> list = storeCharDetailSvc.getAll();
pageContext.setAttribute("list", list);
%>


                    <!DOCTYPE html>
                    <html>

                    <head>
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/storeChar/css/css.css">
                    </head>
                    <header id="header" class=""> </header>
                    <!-- /header -->

                    <body>
                        <div id="mySidebar" class="sidebar">
                            <div>
                                <img id="logo" src="./image/Logo2 (2).png" class="img-circle" alt="User Image">
                            </div>
                            <br> <br> <br> <br> <br>
                            <span id="empFront">首頁<br>
								<a href="#"> FAQ</a> 
								<a href="#"> 評論審核</a> 
								<a href="#"> 最新消息管理</a>
							</span>
                            <span id="empShop">商城管理<br> 
								<a href="#"> 商品管理</a> 
								<a href="#">訂單處理 </a> 
								<a href="#">回覆買家留言 </a> 
								<a href="#">廣告設置管理 </a> 
                                <a href="#">促銷活動設置 </a>
                            </span> <span id="empStore">餐廳管理<br> 
								<a href="<%=request.getContextPath()%>/back-end/storeChar/listAllStoreChar.jsp"> 餐廳分類管理 </a> 
                                <a href="#"> 餐廳資訊管理 </a> 
                                <a href="#"> 餐廳申請審核 </a> 	
							</span> <span id="empMem">會員管理<br> 
								<a href="<%=request.getContextPath()%>/back-end/member/listAllMem.jsp"> 會員資料管理 </a> 
								<a href="#"> 商家註冊審核 </a> 
								<a href="#"> </a>
							</span> <span id="empBack">後台管理<br> 
								<a href="#"> 員工管理 </a> 
								<a href="#"> </a> <a href="#"> </a>
							</span>
                        </div>
                        <div>
                            <script>
                            </script>
                            <a href="#" id="login"> 員工登入/登出 </a>
                        </div>
                        <div id=backSidebar></div>
                        <div class="main">
                            <div class="addStoreChar">
                                <p></p>
                                <c:if test="${not empty errorMsgs}">
                                    <font style="color: red">請修正以下錯誤:</font>
                                    <ul>
                                        <c:forEach var="message" items="${errorMsgs}">
                                            <li style="color: red">${message}</li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <form METHOD="get" ACTION="<%=request.getContextPath()%>/back-end/storeChar/storeChar.do">
                                    <label for="fname">餐廳分類名稱</label>
                                    <input type="text" id="fname" name="storeCharName" placeholder="請輸入分類名稱">
                                    <input type="hidden" name="action" value="insert">
                                    <input type="submit" value="送出">

                                </form>
                            </div>
                        </div>

                    </body>

                    </html>