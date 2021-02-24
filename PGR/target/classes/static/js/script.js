window.onload = function() {
	
}

$(document).ready(function() {
    $('#fullpage').fullpage({
        //options here
        licenseKey: 'OPEN-SOURCE-GPLV3-LICENSE',
        autoScrolling: true,
        scrollHorizontally: true,
        // autoplay
        afterRender: function () {
            setInterval(function () {
                $.fn.fullpage.moveSlideRight();
            }, 5000);
        }
    });
    const hBot = $('.h_bot');
    // content의 높이값계산
    let contHight = $('.content').outerHeight();
    contHight = parseInt(contHight);
    // 스크롤의 높이값계산
    const scrollValue = $(document).scrollTop();
    if(scrollValue == contHight) {
        $(hBot).scroll(function () {
            $(hBot).css("top", "0");
        })
    }
});