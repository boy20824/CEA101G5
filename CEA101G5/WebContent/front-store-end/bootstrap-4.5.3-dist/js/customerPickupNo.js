

$(":radio").click(function() {// 指派按鈕值給資料庫party欄位
	switch ($(this).attr("value")) {
	case "option1":
		$("[name='party']").attr("value", "2");
		$("[name='queuetableid']").attr("value", "1");
		$("[name='queuelineno']").attr("value", "1");
		break;
	case "option2":
		$("[name='party']").attr("value", "4");
		$("[name='queuetableid']").attr("value", "2");
		$("[name='queuelineno']").attr("value", "2");
		break;
	case "option3":
		$("[name='party']").attr("value", "8");
		$("[name='queuetableid']").attr("value", "3");
		$("[name='queuelineno']").attr("value", "3");
		break;
	default:
		break;
	}
});

//$("#memphone").blur(
//		// 驗證帳號
//		function() {
//			$(this).blur(
//					function() {
//						if ($(this).val().length === 10
//								&& $(this).val() !== null
//								&& $(this).val() !== "") {
//							$(this).toggleClass("is-invalid",
//									$(this).val() === "0987654321");
//							if ($(this).val() !== "0987654321") {
//								$(this).addClass("is-valid");
//								$("#submit").prop("disabled", false);
//							}
//						} else {
//							$(this).attr("class", "form-control");
//						}
//					});
//		});
//
//$("#reset").click(function(){//設定密碼需再驗證
//	$("#submit").prop("disabled", "disabled");
//	$("#psw").attr("value", "");
//	$("#pswCheck").attr("value", "");
//	$("#psw").prop("readonly", false);
//	
//});
//
//$("#psw").click(
//		function() {// 取消預設密碼顯示密碼檢查
//			$(this).blur(
//					function() {
//						
//							$("#showPswCheck").removeAttr("style");
//						
////						if ($(this).val() !== null
////								&& $("#memberName").val() !== null
////								&& $("#memphone").val() !== null
////								&& $("pswCheck").val() ===$(this)) {
////							$("#submit").prop("disabled", false);
////						}
//					});
//		});
//
//$("#pswCheck").click(function() {
//	$(this).blur(function() {
//		if ($(this).val() !== $("#psw").val()) {
//			$('.alert').alert()
//		}else{
//			$("#submit").prop("disabled", false);
//		}
//	});
//});

//$(document).ready(function() {
//	$("#submit").prop("disabled", true);
//});

//$("input").keypress(function(e) {
//	code = e.keyCode ? e.keyCode : e.which; // in case of browser compatibility
//	if (code == 13) {
//		e.preventDefault();
//		// do something
//		/* also can use return false; instead. */
//	}
//});
