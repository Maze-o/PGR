window.onload = function() {
	// sticky header 스크롤 관련
    // window.addEventListener("scroll", function() {
    //     const header = document.querySelector(".header");
    //     header.classList.toggle("sticky", window.scrollY > 0);
    // });

    // 새로고침시 스크롤 상단위치
    history.scrollRestoration = "manual"

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