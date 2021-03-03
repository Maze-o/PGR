window.onload = function() {
    // sticky header 스크롤 관련
    // window.addEventListener("scroll", function() {
    //     const header = document.querySelector(".header");
    //     header.classList.toggle("sticky", window.scrollY > 0);
    // });

    // 새로고침시 스크롤 상단위치
    history.scrollRestoration = "manual"

    // news slide
    const sw_news = new Swiper('.sw_news', {
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

    // play_list slide
    const sw_pl = new Swiper('.sw_pl', {
        loop: true,
        autoplay: {
            delay: 4000,
            disableOnInteraction: false,
        },
        navigation: {
            prevEl: '.pl_slide .sw_prev',
            nextEl: '.pl_slide .sw_next',
        },
        pagination: {
            el: '.pl_slide .sw_pg',
            type: 'bullets',
            // clickable: true,
        },
    });
}

$(document).ready(function() {
    $('.gotop').click(function() {
        $('html').animate({
            scrollTop: 0
        }, 600);
    })
})

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