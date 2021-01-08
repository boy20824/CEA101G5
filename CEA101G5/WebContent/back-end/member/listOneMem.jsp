<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ page import="com.member.model.*"%>

            <%
	MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java(Concroller), 存入req的memVO物件
%>

                <html>

                <head>
                    <title>會員資料 - listOneEmp.jsp</title>

                    <style>
                        table#table-1 {
                            background-color: #CCCCFF;
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
                            width: 600px;
                            background-color: white;
                            margin-top: 5px;
                            margin-bottom: 5px;
                        }
                        
                        table,
                        th,
                        td {
                            border: 1px solid #CCCCFF;
                        }
                        
                        th,
                        td {
                            padding: 5px;
                            text-align: center;
                        }
                    </style>

                </head>

                <body bgcolor='white'>

                    <table id="table-1">
                        <tr>
                            <td>
                                <h3>會員資料 - ListOneMem.jsp</h3>
                                <h4>
                                    <a href="select_member_page.jsp"><img src="images/mycode.jpg" width="100" height="32" border="0">回首頁</a>
                                </h4>
                            </td>
                        </tr>
                    </table>

                    <table>
                        <tr>
                            <th>會員手機</th>
                            <th>會員密碼</th>
                            <th>會員姓名</th>
                            <th>會員地址</th>
                            <th>會員性別</th>
                            <th>會員電子信箱</th>
                            <th>會員身份證字號</th>
                            <th>會員生日</th>
                            <th>會員暱稱</th>
                            <th>統一編號</th>
                            <th>會員狀態</th>
                            <th>會員權限</th>
                            <th>儲值餘額</th>
                            <th>信用卡卡號</th>
                            <th>信用卡擁有者</th>
                            <th>信用卡到期日</th>
                            <th>信用卡CCV</th>
                            <th>大頭照</th>

                        </tr>
                        <tr>
                            <td>${memVO.memPhone}</td>
                            <td>${memVO.memPwd}</td>
                            <td>${memVO.memName}</td>
                            <td>${memVO.memAddress}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${memVO.memSex ==0}">男</c:when>
                                    <c:otherwise>女</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${memVO.memEmail}</td>
                            <td>${memVO.memIdentity}</td>
                            <td>${memVO.memBirth}</td>
                            <td>${memVO.memNick}</td>
                            <td>${memVO.memLice}</td>
                            <td>${memVO.memCondition}</td>
                            <td>${memVO.memAuth}</td>
                            <td>${memVO.memTotalRechar}</td>
                            <td>${memVO.memCardNumber}</td>
                            <td>${memVO.memCardHolder}</td>
                            <td>${memVO.memCardExpirationDate}</td>
                            <td>${memVO.memCardCCV}</td>
                            <td><img width=50 height=50 src="<%=request.getContextPath()%>/back-end/member/memPhotoReader.do?memPhone=${memVO.memPhone}"></td>
                        </tr>
                    </table>

                </body>

                </html>