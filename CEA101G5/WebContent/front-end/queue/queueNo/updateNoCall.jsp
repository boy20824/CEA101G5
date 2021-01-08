<%@page contentType="text/html;charset=utf-8"  language="java" import="java.sql.*" errorPage=""%> 
<%@page import="org.json.JSONObject"%>
<%
//取得前端送來的資料 (前端來的資料都是字串)
String storeid = request.getParameter("storeid");
Integer queuetableid = new Integer(request.getParameter("queuetableid"));// 同queuelineno
Integer queuenocall = new Integer(request.getParameter("queuenum"))+1;
Integer queuetableocc = new Integer(request.getParameter("queuetableocc"))+1;
Integer queuetableusable = new Integer(request.getParameter("queuetableusable"))-1;

//載入JDBC驅動程式類別 
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","CEA101G5", "123456"); 

//建立PreparedStatement物件 
PreparedStatement pstmt = conn.prepareStatement("UPDATE queue_line set queue_no_call=? where queue_line_no=? AND store_id=? AND queue_table_id=?");
//更新排隊號碼
pstmt.setInt(1, queuenocall);
pstmt.setInt(2, queuetableid);
pstmt.setString(3, storeid);
pstmt.setInt(4, queuetableid);
//執行PreparedStatement
pstmt.executeUpdate();

PreparedStatement pstmtt = conn.prepareStatement("UPDATE queue_table set queue_table_occ=? , queue_table_usable=? where queue_table_id=? AND store_id=?");
//更新座位可用數
pstmtt.setInt(1, queuetableocc);
pstmtt.setInt(2, queuetableusable);
pstmtt.setInt(3, queuetableid);
pstmtt.setString(4, storeid);
//執行PreparedStatement
pstmtt.executeUpdate();


PreparedStatement stmt = conn.prepareStatement("select * from queue_line where queue_line_no = ? and store_id = ? and queue_table_id = ?");
//代入資料
stmt.setInt(1, queuetableid);
stmt.setString(2, storeid);
stmt.setInt(3, queuetableid);
//執行PreparedStatement
ResultSet rs = stmt.executeQuery();
//取回一筆資料

PreparedStatement stmtt = conn.prepareStatement("select * from queue_table where store_id = ? and queue_table_id = ?");
//代入資料
stmtt.setString(1, storeid);
stmtt.setInt(2, queuetableid);
//執行PreparedStatement
ResultSet rss = stmtt.executeQuery();
//取回一筆資料


//將資料轉成JSONObject		 
JSONObject noCall = new JSONObject();
if(rs.next()){
	noCall.put("queuenocall", rs.getInt("queue_no_call"));
	noCall.put("queuetableid", rs.getInt("queue_table_id"));
	if(rss.next())
	noCall.put("queuetableusable", rss.getInt("queue_table_usable"));
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


