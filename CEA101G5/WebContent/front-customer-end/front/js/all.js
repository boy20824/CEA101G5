        window.addEventListener("load", function() {

            // 餐廳推薦頁面輪播
            $(".menu").slick({
                dots: true,
                slidesToShow: 3,
                slidesToScroll: 1,
                autoplay: true,
                autoplaySpeed: 2000,
                responsive: [{
                        breakpoint: 1024,
                        settings: {
                            slidesToShow: 3,
                            slidesToScroll: 3,
                            infinite: true,
                            dots: true,
                        },
                    },
                    {
                        breakpoint: 600,
                        settings: {
                            slidesToShow: 2,
                            slidesToScroll: 2,
                        },
                    },
                    {
                        breakpoint: 480,
                        settings: {
                            slidesToShow: 1,
                            slidesToScroll: 1,
                        },
                    },
                ],
            });



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
                        "transparent";
                    document.querySelector(".forfiexed").style.opacity = "1";
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
                        "transparent";
                    document.querySelector(".forfiexed").style.opacity = "1";
                }
            });

            //  title滾動變色事件
            window.addEventListener(
                "scroll",
                function(e) {
                    if (window.innerHeight + window.scrollY > window.innerHeight) {
                        document.querySelector(".forfiexed2").style.backgroundColor = "#fa7e23";
                        document.querySelector(".forfiexed2").style.opacity = "85%";
                        $(".forfiexed2").slideDown();
                        $(".forfiexed").fadeOut(1);
                    } else {
                        document.querySelector(".forfiexed2").style.backgroundColor =
                            "transparent";
                        document.querySelector(".forfiexed2").style.opacity = "1";
                        $(".forfiexed2").slideUp(10);
                        $(".forfiexed").fadeIn(1);
                    }
                },
                false
            );


        });
        
 