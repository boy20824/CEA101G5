<%@page contentType="text/html;charset=utf-8"  language="java" import="java.sql.*" errorPage=""%> 
<%@page import="org.json.JSONObject"%>
<%
//取得前端送來的資料 (前端來的資料都是字串)
String storeid = request.getParameter("storeid");
Integer queuetableid = new Integer(request.getParameter("queuetableid"));
Integer queuetableocc = new Integer(request.getParameter("queuetableocc"))-1;
Integer queuetableusable = new Integer(request.getParameter("queuetableusable"))+1;

//載入JDBC驅動程式類別 
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","CEA101G5", "123456"); 


PreparedStatement pstmt = conn.prepareStatement("UPDATE queue_table set queue_table_occ=? , queue_table_usable=? where queue_table_id=? AND store_id=?");
//更新座位可用數
pstmt.setInt(1, queuetableocc);
pstmt.setInt(2, queuetableusable);
pstmt.setInt(3, queuetableid);
pstmt.setString(4, storeid);
//執行PreparedStatement
pstmt.executeUpdate();


PreparedStatement stmt = conn.prepareStatement("select * from queue_table where store_id = ? and queue_table_id = ?");
//代入資料
stmt.setString(1, storeid);
stmt.setInt(2, queuetableid);
//執行PreparedStatement
ResultSet rs = stmt.executeQuery();
//取回一筆資料


//將資料轉成JSONObject		 
JSONObject noCall = new JSONObject();
if(rs.next()){
	noCall.put("queuetableid", rs.getInt("queue_table_id"));
	noCall.put("queuetableusable", rs.getInt("queue_table_usable"));
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


