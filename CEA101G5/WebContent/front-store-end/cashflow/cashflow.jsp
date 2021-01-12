<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="org.hibernate.*"%>
<%@ page import="java.util.*"%>
<%@ page import="hibernate.util.HibernateUtil"%>
<%@ page import="java.math.*" %>
<%@ page import="com.restaurant.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="echarts.min.js"></script>
</head>
<body>
<%@include file="../selectpage/selectstorepage.jsp" %>

<%
@SuppressWarnings("unchecked")
Session session1 = HibernateUtil.getSessionFactory().openSession();
Transaction tx = null;
int money[] = new int[12];
int sumMonthMoney = 0 ; 
String storeId = ((RestaurantVO)session.getAttribute("storeLogin")).getStoreId();
String month[]=new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
for(int i =0;i<month.length;i++){
	Query<Object> query1 = session1.createNativeQuery("select FOODORDER_ID from FOODORDER where Store_ID='"+storeId+"'"+"and to_char(FOODORDER_TIME,'mm')='"+month[i]+"'");
	List<Object> list1 = query1.getResultList();
	for (Object FOODORDER_ID : list1) {
		Query<Object> query2 = session1.createNativeQuery("select sum(MENU_PRICE) as 總金額 from FOODORDER_DETAIL where FOODORDER_ID='"+FOODORDER_ID+"'");
		List<Object> list2 = query2.getResultList();
		sumMonthMoney=0;
		for (Object aArray : list2) {
			BigDecimal bd = (BigDecimal) aArray;
			sumMonthMoney = sumMonthMoney + bd.intValue();
 		}
		money[i] +=sumMonthMoney;
	}
}




%>
<div id="main" style="width: 1800px; height: 800px;margin-left:2em;margin-top:5em;">
      <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById("main"));

        // 指定图表的配置项和数据
        var option = {
          xAxis: {
            type: "category",
            data: ["一月營收", "二月營收", "三月營收", "四月營收", "五月營收", "六月營收", "七月營收", "八月營收", "九月營收", "十月營收", "十一月營收", "十二月營收"],
          },
          yAxis: {
            type: "value",
          },
          series: [
            {
              data: [<%=money[0]%>, <%=money[1]%>, <%=money[2]%>, <%=money[3]%>,<%=money[4]%>, <%=money[5]%>, <%=money[6]%>,<%=money[7]%>,<%=money[8]%>,<%=money[9]%>,<%=money[10]%>,<%=money[11]%>],
              type: "bar",
              showBackground: true,
              backgroundStyle: {
                color: "rgba(220, 220, 220, 0.8)",
              },
            },
          ],
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      </script>
    </div>
</body>
</html>