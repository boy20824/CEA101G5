<%@page contentType="text/html;charset=utf-8"  language="java" import="java.sql.*" errorPage=""%> 
<%@page import="org.json.JSONObject"%>
<%
//取得前端送來的資料 (前端來的資料都是字串)
String storeid = request.getParameter("storeid");
Integer queueperiodid = new Integer(request.getParameter("queueperiodid"));
Integer queuenum = new Integer(request.getParameter("queuenum"));


//載入JDBC驅動程式類別 
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","CEA101G5", "123456"); 

PreparedStatement pstmt = conn.prepareStatement("update queue_period set queue_no_current=? where queue_period_id = ? and store_id = ?");
pstmt.setInt(1, queuenum);
pstmt.setInt(2, queueperiodid);
pstmt.setString(3, storeid);
pstmt.executeUpdate();


//建立PreparedStatement物件 
PreparedStatement stmt = conn.prepareStatement("select queue_no_current from queue_period where queue_period_id = ? and store_id = ?");
//代入資料
stmt.setInt(1, queueperiodid);
stmt.setString(2, storeid);
//執行PreparedStatement
ResultSet rs = stmt.executeQuery();

//取回一筆資料
//將資料轉成JSONObject		 
JSONObject nocurrent = new JSONObject();
if(rs.next()){
nocurrent.put("queuenocurrent", rs.getInt("queue_no_current"));
};
  
//輸出JSONObject
out.print(nocurrent);
//關閉ResultSet物件 	
rs.close();
//關閉Statement物件    
stmt.close();
//關閉Connection物件 	 
 conn.close();
%>   


