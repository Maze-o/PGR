window.onload = function() {
	// sticky header 스크롤 관련
    // window.addEventListener("scroll", function() {
    //     const header = document.querySelector(".header");
    //     header.classList.toggle("sticky", window.scrollY > 0);
    // });

    // 새로고침시 스크롤 상단위치
    history.scrollRestoration = "manual"

    // news slide
    new Swiper('.sw_news', {
        loop: true,
        autoplay: {
            delay: 3000,
            disableOnInteraction: false,
        },
        navigation: {
            prevEl: '.news_slide .sw_prev',
            nextEl: '.news_slide .sw_next',
        },
        pagination: {
            el: '.news_slide .sw_pg',
            type: 'bullets',
            // clickable: true,
        },
    });

     // swiper 일시멈춤/재생 기능
    const pause = document.getElementByClassName('.sw_pause')
    pause.click(function() {
        var state = $(this).hasClass('bt_play');
        if (state == false) {
            sw_24.autoplay.stop();
            $(this).addClass('bt_play');
        } else {
            sw_24.autoplay.start();
            $(this).removeClass('bt_play');
        }
    });

}

// $(document).ready(function() {
//     $('#fullpage').fullpage({
//         //options here
//         licenseKey: 'OPEN-SOURCE-GPLV3-LICENSE',
//         autoScrolling: true,
//         scrollHorizontally: true,
//         // autoplay
//         afterRender: function () {
//             setInterval(function () {
//                 $.fn.fullpage.moveSlideRight();
//             }, 5000);
//         }
//     });
// });