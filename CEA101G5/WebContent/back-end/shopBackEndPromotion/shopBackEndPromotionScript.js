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