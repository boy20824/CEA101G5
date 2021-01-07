window.addEventListener("load", function() {
	let check = document.querySelectorAll(".onoffswitch input:first-child");
	
	for (let i = 0; i < check.length; i++) {
		check[i].addEventListener("click", function() {
			let status = document.querySelectorAll(".status");
			let status1 = document.querySelectorAll(".status1");
			if (status[i].innerText === "開放中") {
				status1[i].value = '1';
				status[i].innerText = "關閉中";
				status[i].style.color="gray";
			} else {
				status[i].innerText = "開放中";
				status1[i].value = '0';
				status[i].style.color="orange";
			}
		});
	}
});
