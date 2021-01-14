<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>沒有網路連線</title>

<style type="text/css">
.ratings {
	position: relative;
	vertical-align: middle;
	display: inline-block;
	color: #ddd; /*背景星星顏色*/
	overflow: hidden;
	font-size: 16px; /*調整字體大小可放大縮小星星*/
	text-shadow: 0px 1px 0 #999;
}

.full_star {
	/*調整寬度可變更星等*/
	position: absolute;
	left: 0;
	top: 0;
	white-space: nowrap;
	overflow: hidden;
	color: #F5E960; /*前景星星顏色*/
}
</style>
</head>

<body style="background-color:#F7F7F7">
<div style="display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;">
	<img style="width: 80%" src="<%=request.getContextPath()%>/front-customer-end/front/img/error.png"/>
</div>
</body>

</html>