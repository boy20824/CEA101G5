// NavBar
let homeNavBar = document.getElementById("homeNavBar");

window.addEventListener('wheel', function (e) {
    if (e.deltaY < 0) {
        homeNavBar.classList.remove("navbarDisplayController");
    }
    else if (e.deltaY > 0) {
        homeNavBar.classList.add("navbarDisplayController");
    }
});

// User Signing In
let signInMainVisibility = false;

$("#navbarUserIcon").click(function () {
    if (signInMainVisibility) {
        $("#signInMain").fadeOut(200);
        signInMainVisibility = false;
    } else {
        $("#signInMain").fadeIn(200);
        signInMainVisibility = true;
    }
});

$("#loginBtn").click(function () {
    if (signInMainVisibility) {
        $("#signInMain").fadeOut(200);
        signInMainVisibility = false;
    } else {
        $("#signInMain").fadeIn(200);
        signInMainVisibility = true;
    }
});

$("#signInCloseButton").click(function () {
    $("#signInMain").fadeOut(200);
    signInMainVisibility = false;
});

$(window).keydown(function (e) {
    if (signInMainVisibility) {
        if (e.keyCode === 27) {
            $("#signInMain").fadeOut(200);
            signInMainVisibility = false;
        }
    }
});

// Shopping Cart
let shoppingCartVisibility = false;

function hideShoppingCart() {
    $("#shoppingCartContainer").hide();
}

$("#navbarCartIcon").click(function () {
    if (shoppingCartVisibility) {
        $("#shoppingCartContainer").animate({
            right: "-400px",
            opacity: "60%"
        }, 300, hideShoppingCart);
        shoppingCartVisibility = false;
    } else {
        $("#shoppingCartContainer").show();
        $("#shoppingCartContainer").animate({
            right: "10px",
            opacity: "100%"
        }, 300);
        shoppingCartVisibility = true;
    }
});

$("#cartCloseButton").click(function () {
    $("#shoppingCartContainer").animate({
        right: "-400px",
        opacity: "60%"
    }, 300, hideShoppingCart);
    shoppingCartVisibility = false;
});

$(window).keydown(function (e) {
    if (shoppingCartVisibility) {
        if (e.keyCode === 27) {
            $("#shoppingCartContainer").animate({
                right: "-400px",
                opacity: "60%"
            }, 300, hideShoppingCart);
            shoppingCartVisibility = false;
        }
    }
});

// Product Image Carousel (Slick)
$('.productPhotoMain').slick({
	slidesToShow: 1,
	slidesToScroll: 1,
	arrows: false,
	fade: true,
	asNavFor: '.productPhotoPreviews'
});
$('.productPhotoPreviews').slick({
	slidesToShow: 3,
	slidesToScroll: 1,
	asNavFor: '.productPhotoMain',
	centerMode: true,
	arrows: false,
	focusOnSelect: true
});

// Updating Purchase Qty & Quantity Proofing.
let qtyToPurchase = document.getElementById("qtyToPurchase");

$("#qtyDecreaseBtn").click(function() {
	if (qtyToPurchase.value > 1) {
		qtyToPurchase.value = parseInt(qtyToPurchase.value) - 1;				
	} else {
		alert("購買數量不可小於 1");
	}
});

$("#qtyIncreaseBtn").click(function() {
	if (qtyToPurchase.value < 99) {
		qtyToPurchase.value = parseInt(qtyToPurchase.value) + 1;
	} else {
		alert("購買數量不可大於 99");
	}
});

$("#qtyToPurchase").change(function() {
	if (qtyToPurchase.value < 1) {
		alert("購買數量不可小於 1");
		qtyToPurchase.value = 1;
	}
	if (qtyToPurchase.value > 99) {
	alert("購買數量不可大於 99");
	qtyToPurchase.value = 1;
	}
});

// Toggling Between QA & Review
let qaButton = document.getElementById("qaButton");
let reviewButton = document.getElementById("reviewButton");
let productQAContainer = document.getElementById("productQAContainer");
let productReviewContainer = document.getElementById("productReviewContainer");

qaButton.addEventListener("click", function() {
	qaButton.classList.remove("qaReviewBtnBlur");
	qaButton.classList.add("qaReviewBtnFocus");
	reviewButton.classList.remove("qaReviewBtnFocus");
	reviewButton.classList.add("qaReviewBtnBlur");
	
	productQAContainer.classList.remove("qaAndReviewDisplayController");
	productReviewContainer.classList.add("qaAndReviewDisplayController");
})

reviewButton.addEventListener("click", function() {
	reviewButton.classList.remove("qaReviewBtnBlur");
	reviewButton.classList.add("qaReviewBtnFocus");
	qaButton.classList.remove("qaReviewBtnFocus");
	qaButton.classList.add("qaReviewBtnBlur");
	
	productReviewContainer.classList.remove("qaAndReviewDisplayController")
	productQAContainer.classList.add("qaAndReviewDisplayController");
})

// Product QA TextArea
let productQuesResetBtn = document.getElementById("productQuesResetBtn");
let qaSubmissionTextArea = document.getElementById("qaSubmissionTextArea");

productQuesResetBtn.addEventListener("click", function() {
	qaSubmissionTextArea.value = "";
});

// Product To Cart
$("#addToCart").click(function() {
	//Add To Cart Animation
	$(".addToCartPopContainer").fadeIn(100);
	setTimeout(function() {
		$(".addToCartPopContainer").fadeOut(100);	
	}, 2500);
	
	// Sending Data To Server
	let ajaxRequest = new XMLHttpRequest();
	let productId = document.getElementById("productIdToJS").value;
	let productQty = document.getElementById("qtyToPurchase").value;
	let cartCheckOutButton = document.getElementById("cartCheckOutButton");
	
	let url = window.location.origin + "/CEA101G5/shop/ordermaster.do";
	ajaxRequest.open("POST", url, true);
	ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	ajaxRequest.onload = function () {
		if (ajaxRequest.status >= 200 && ajaxRequest.status < 400) {
			ajaxData = JSON.parse(ajaxRequest.responseText);
			renderProductToCart(ajaxData);
			addToCartAnimation();
		} else {
			console.log("An error has occured...");
		}
	}
	ajaxRequest.send("action=addToCart&productId=" + productId + "&productQty=" + productQty);
	refreshCheckOutBtn();	
});

function renderProductToCart(data) {
	let shoppingCartTable = document.getElementById("shoppingCartTable");
	shoppingCartTable.innerHTML = "";
	
	for (let i = 0; i < data.length; i++) {
		let productVO = "<tr><td><img src=" + window.location.origin + "/CEA101G5/shop/productphotoreader.do?productId=" + data[i].productId + " alt='' width='50' height='50'></td><td><p>" + data[i].productName + "</p></td><td><p>$" + data[i].productPrice + "</p></td></tr>"
		if (data.length == 1) {
			$("#EmptyCartController").hide();
		}
		shoppingCartTable.insertAdjacentHTML("beforeend", productVO);	
	}
}

function addToCartAnimation() {
	$("#shoppingCartContainer").show();
	$("#shoppingCartContainer").animate({
		right: "10px",
		opacity: "100%"
	}, 300);
	shoppingCartVisibility = true;
	
	setTimeout(function() {
       $("#shoppingCartContainer").animate({
            right: "-400px",
            opacity: "60%"
        }, 300, hideShoppingCart);
        shoppingCartVisibility = false;
	}, 3000);
}

function refreshCheckOutBtn() {
	cartCheckOutButton.innerHTML = "";
	
	let newCheckOutBtnATag = document.createElement("a");
	let checkOutUrl = window.location.origin + "/CEA101G5/front-end/shopCheckOut.jsp";
	newCheckOutBtnATag.setAttribute("href", checkOutUrl);
	
	let newCheckOutBtn = document.createElement("button");
	newCheckOutBtn.setAttribute("type", "button");
	newCheckOutBtn.setAttribute("class", "btn btn-danger cartTwoButtonSetup");
	newCheckOutBtn.innerText = "前往結帳";
	
	cartCheckOutButton.appendChild(newCheckOutBtnATag);
	newCheckOutBtnATag.appendChild(newCheckOutBtn);
}







