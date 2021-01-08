window.addEventListener("load", function() {

    $(".side-menu-p").click(function() {
        // 側拉選單動畫事件
        $(".side-menu").stop().animate({ width: "toggle" }, 500);
        $(".side-menu-black").fadeToggle(1);

        //輯神到此一遊
        // if(document.querySelector('.title').classList.contains('title-show')){
        //     $('.title').removeClass('title-show')
        // }else{
        //     $('.title').addClass('title-show')
        // }

        //神魔的子民偉成到此一遊
        if ($(".forfiexed").css("background-color") == "rgba(0, 0, 0, 0)") {
            document.querySelector(".forfiexed").style.backgroundColor = "#fa7e23";
            document.querySelector(".forfiexed").style.opacity = "85%";
        } else {
            document.querySelector(".forfiexed").style.backgroundColor =
                "#fa7e23t";

        }
    });
    // 點擊黑色畫面也會觸發的側拉選單收縮
    $(".side-menu-black").click(function() {
        $(".side-menu").animate({ width: "toggle" }, 500);
        $(".side-menu-black").fadeToggle(200);
        if ($(".forfiexed").css("background-color") == "rgba(0, 0, 0, 0)") {
            document.querySelector(".forfiexed").style.backgroundColor = "#fa7e23";
            document.querySelector(".forfiexed").style.opacity = "85%";
        } else {
            document.querySelector(".forfiexed").style.backgroundColor =
                "#fa7e23";

        }
    });

});