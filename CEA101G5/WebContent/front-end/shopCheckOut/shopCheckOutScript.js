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

// Updating Purchase Qty & Quantity Proofing & Delete Item.
let qtyToPurchaseArr = document.querySelectorAll(".qtyToPurchase");
let qtyDecreaseBtnArr = document.querySelectorAll(".qtyDecreaseBtn");
let qtyIncreaseBtnArr = document.querySelectorAll(".qtyIncreaseBtn");
let deleteBtnArr = document.querySelectorAll(".deleteBtn");
let productRowCounterArr = document.querySelectorAll(".productRowCounter");

for (let i = 0; i < qtyToPurchaseArr.length; i++) {
	qtyDecreaseBtnArr[i].addEventListener("click", function(e) {
		if (qtyToPurchaseArr[i].value > 1) {
			qtyToPurchaseArr[i].value = parseInt(qtyToPurchaseArr[i].value) - 1;
			updateOrderDetails();
			let productId = e.target.getAttribute("data-productid");
			let productQty = document.getElementById(productId + "qtyToPurchase").value;
			updateOrderDetailsByAJAX(productId, productQty);
		} else {
			alert("購買數量不可小於 1");
		}
	});
	
	qtyIncreaseBtnArr[i].addEventListener("click", function(e) {
		if (qtyToPurchaseArr[i].value < 99) {
			qtyToPurchaseArr[i].value = parseInt(qtyToPurchaseArr[i].value) + 1;
			updateOrderDetails();
			let productId = e.target.getAttribute("data-productid");
			let productQty = document.getElementById(productId + "qtyToPurchase").value;
			updateOrderDetailsByAJAX(productId, productQty);
		} else {
			alert("購買數量不可大於 99");
		}
	});
	
	qtyToPurchaseArr[i].addEventListener("change", function(e) {
		if (qtyToPurchaseArr[i].value < 1) {
			alert("購買數量不可小於 1");
			qtyToPurchaseArr[i].value = 1;
		}
		if (qtyToPurchaseArr[i].value > 99) {
			alert("購買數量不可大於 99");
			qtyToPurchaseArr[i].value = 1;
		}
		updateOrderDetails();
		let productId = e.target.getAttribute("data-productid");
		let productQty = document.getElementById(productId + "qtyToPurchase").value;
		updateOrderDetailsByAJAX(productId, productQty);
	});
	
	deleteBtnArr[i].addEventListener("click", function(e) {
		let productId = e.target.getAttribute("data-productid"); 
		let ajaxRequest = new XMLHttpRequest();
	
		let url = window.location.origin + "/CEA101G5/shop/ordermaster.do";
		ajaxRequest.open("POST", url, true);
		ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajaxRequest.onload = function () {
			if (ajaxRequest.status >= 200 && ajaxRequest.status < 400) {
				let rowToRemoveFromTable = document.querySelector(".productRowCounter" + i);
				rowToRemoveFromTable.remove();
				updateOrderDetails();
			} else {
				console.log("An error has occured...");
			}
		}
		ajaxRequest.send("action=deleteFromCart&productId=" + productId);
		
	});
}

function updateOrderDetails() {
	let productPriceArr = document.querySelectorAll(".productPrice");
	let qtyToPurchaseArr = document.querySelectorAll(".qtyToPurchase");
	let totalByProductArr = document.querySelectorAll(".totalByProduct");
	
	let orderTotalPrice = document.getElementById("orderTotalPrice");
	let totalForOrder = 0;
	
	for (let i = 0; i < totalByProductArr.length; i++) {
		let productPrice = parseInt((productPriceArr[i].innerHTML).substring(1));
		let productQty = qtyToPurchaseArr[i].value;
		totalByProductArr[i].innerHTML = "$" + productPrice * productQty;
		totalForOrder += productPrice * productQty;
	}
	orderTotalPrice.innerHTML = "$" + totalForOrder;
}

function updateOrderDetailsByAJAX(productId, productQty) {
	let ajaxRequest = new XMLHttpRequest();
	
	let url = window.location.origin + "/CEA101G5/shop/ordermaster.do";
	ajaxRequest.open("POST", url, true);
	ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	ajaxRequest.onload = function () {
		if (ajaxRequest.status >= 200 && ajaxRequest.status < 400) {
			return;
		} else {
			console.log("An error has occured...");
		}
	}
	ajaxRequest.send("action=updateCart&productId=" + productId + "&productQty=" + productQty);
}

// Credit Card
var card = new Card({
    form: document.getElementById("checkOutForm"), // *required*
    container: '.card-wrapper', // *required*

    formSelectors: {
        numberInput: 'input[name="number"]', // optional — default input[name="number"]
        expiryInput: 'input[name="expiry"]', // optional — default input[name="expiry"]
        cvcInput: 'input[name="cvc"]', // optional — default input[name="cvc"]
        nameInput: 'input[name="name"]' // optional - defaults input[name="name"]
    },

    width: 300, // optional — default 350px
    formatting: true, // optional - default true

    // Strings for translation - optional
    messages: {
        validDate: 'valid\ndate', // optional - default 'valid\nthru'
        monthYear: 'mm/yyyy', // optional - default 'month/year'
    },

    // Default placeholders for rendered fields - optional
    placeholders: {
        number: '•••• •••• •••• ••••',
        name: 'Full Name',
        expiry: '••/••',
        cvc: '•••'
    },

    masks: {
        cardNumber: '•' // optional - mask card number
    },

    // if true, will log helpful messages for setting up Card
    debug: false // optional - default false
});












