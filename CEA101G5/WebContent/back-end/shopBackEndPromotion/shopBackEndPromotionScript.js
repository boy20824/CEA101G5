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

// Promotion
// Generate Form By Product Id
let toPromoProductIdArr = document.querySelectorAll(".toPromoProductId");
let promoPrice = document.getElementById("promoPrice");
let formProductPrice = document.getElementById("formProductPrice");

for (let i = 0; i < toPromoProductIdArr.length; i++) {
	toPromoProductIdArr[i].addEventListener("click", function(e) {
		ajaxFormProcessorByProductId(e.target.getAttribute("data-productId"));
	});
}

// Genereate Form By Product Name
let toPromoProductNameArr = document.querySelectorAll(".toPromoProductName");

for (let i = 0; i < toPromoProductNameArr.length; i++) {
	toPromoProductNameArr[i].addEventListener("click", function(e) {
		ajaxFormProcessorByProductId(e.target.getAttribute("data-productId"));
	});
}

// Submitting Promo
let addToPromoBtn = document.getElementById("addToPromoBtn");

addToPromoBtn.addEventListener("click", function() {
	let productId = document.getElementById("formProductId").value; 
	let productPromoPrice = document.getElementById("promoPrice").value;
	let productVOInListArr = document.querySelectorAll(".productVOInList");
	let promoProductRepeat = false;

	for(let i = 0; i < productVOInListArr.length; i++) {
		if (productId == productVOInListArr[i].innerText) {
			promoProductRepeat = true;
		}
	}
	
	if (promoProductRepeat) {
		alert("商品目前促銷中，請勿重複加入商品！");
	} else if (productPromoPrice < 1) {
		alert("商品促銷售價不得小於１元");
	} else if (productPromoPrice >= ((formProductPrice.value).substring(1))) {
		alert("商品促銷售價不得等於或大於商品售價");
	} else {
		ajaxAddProductToPromo(productId, productPromoPrice);
	}
	
	document.getElementById("promoPrice").value = null;
});

// Functions
function ajaxFormProcessorByProductId(productId) {
	let formProductId = document.getElementById("formProductId");
	let formProductName = document.getElementById("formProductName");
	let formProductStatus = document.getElementById("formProductStatus");
	let formCategoryId = document.getElementById("formCategoryId");
	let formProductMSRP = document.getElementById("formProductMSRP");
	
	let ajaxRequest = new XMLHttpRequest();
	
	let url = window.location.origin + "/CEA101G5/shop/promotion.do";
	ajaxRequest.open("POST", url, true);
	ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	ajaxRequest.onload = function () {
		if (ajaxRequest.status >= 200 && ajaxRequest.status < 400) {
			ajaxData = JSON.parse(ajaxRequest.responseText);
			
			promoPrice.disabled = false;
			formProductId.value = ajaxData["productId"];
			formProductName.value = ajaxData["productName"];
			if (ajaxData["productStatus"] === 0) {
				formProductStatus.value = "停用";
				promoPrice.disabled = true;
				alert("停用商品不得加入促銷活動！")
			}
			if (ajaxData["productStatus"] === 1) {
				formProductStatus.value = "上架中";
			}
			
			formCategoryId.value = ajaxData["categoryId"];
			formProductMSRP.value = "$" + ajaxData["productMSRP"];
			formProductPrice.value = "$" + ajaxData["productPrice"];
			
		} else {
			console.log("An error has occured...");
		}
	}
	ajaxRequest.send("action=queryByProductId&productId=" + productId);
}

function ajaxAddProductToPromo(productId, productPromoPrice) {
	let promoListTable = document.getElementById("promoListTable");

	let ajaxRequest = new XMLHttpRequest();
	let url = window.location.origin + "/CEA101G5/shop/promotion.do";
	
	ajaxRequest.open("POST", url, true);
	ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	ajaxRequest.onload = function () {
		if (ajaxRequest.status >= 200 && ajaxRequest.status < 400) {
			ajaxData = JSON.parse(ajaxRequest.responseText);
			
			let productVO = "<tr><td class='col-1'><img class='rounded' src='" + window.location.origin + "/CEA101G5/shop/productphotoreader.do?productId=" + ajaxData["productId"] + "'></td><td class='col-1'>" + ajaxData["productId"] + "</td><td class='col-3'>" + ajaxData["productDescription"] + "</td><td class='col-1 tdAlignMiddle'>" + "$" + ajaxData["productMSRP"] + "</td><td class='col-1 tdAlignMiddle'>" + "$" + ajaxData["productPrice"] + "</td><td class='col-1 tdAlignMiddle'>" + "$" + ajaxData["productPromoPrice"] + "</td></tr>";
			promoListTable.insertAdjacentHTML("beforeend", productVO);
			
		} else {
			console.log("An error has occured...");
		}
	}
	
	ajaxRequest.send("action=addToPromo&productId=" + productId + "&productPromoPrice=" + productPromoPrice);
}




































