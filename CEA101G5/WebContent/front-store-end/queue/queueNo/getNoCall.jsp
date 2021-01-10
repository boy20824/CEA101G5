<%@page contentType="text/html;charset=utf-8"  language="java" import="java.sql.*" errorPage=""%> 
<%@page import="org.json.JSONObject"%>
<%
//取得前端送來的資料 (前端來的資料都是字串)
String storeid = request.getParameter("storeid");
Integer queuetableid = new Integer(request.getParameter("queuetableid"));
Integer queuelineno = new Integer(request.getParameter("queuelineno"));


//載入JDBC驅動程式類別 
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","CEA101G5", "123456"); 

//建立PreparedStatement物件 
PreparedStatement stmt = conn.prepareStatement("select queue_no_call from queue_line where queue_line_no = ? and store_id = ? and queue_table_id = ?");
//代入資料
stmt.setInt(1, queuelineno);
stmt.setString(2, storeid);
stmt.setInt(3, queuetableid);
//執行PreparedStatement
ResultSet rs = stmt.executeQuery();

//取回一筆資料
//將資料轉成JSONObject		 
JSONObject noCall = new JSONObject();
if(rs.next()){
	noCall.put("queuenocall", rs.getInt("queue_no_call"));
};
  
//輸出JSONObject
out.print(noCall);
//關閉ResultSet物件 	
rs.close();
//關閉Statement物件    
stmt.close();
//關閉Connection物件 	 
 conn.close();
%>   


