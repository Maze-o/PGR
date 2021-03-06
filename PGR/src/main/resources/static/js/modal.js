$(document).ready(function() {
    const modalDiv = $('.chat_container')
    const modalCon = $('.chat_con')
    const modalClose = $('#close_chat')
    const modalShow = $('#show_chat')

    const modalOpen = true;
    const modalMotion = true;
    // 1000 = 1초
    const modalMotionTime = true;
    
    const onoff = 'onoff'

    modalDiv.css({
        'position': 'fixed',
        'left': 0,
        'top': 0,
        'width': '100%',
        'height': '100%',
        'z-index': 99999,
        'display': 'none'
    })

    /*// 초기에 보일지 말지 결정한다.
    if (modalOpen == true) {
        if (modalMotion == true) {
            modalDiv.fadeIn(modalMotionTime);
        } else {
            modalDiv.show();
        }
    }*/
    if(sessionStorage.getItem(onoff) == 1) {
        if (modalMotion == true) {
            modalDiv.fadeIn(modalMotionTime);
        } else {
            modalDiv.show();
        }
    }
    
    // 모달창 클릭시 닫기
    modalDiv.click(function() {
    	if(sessionStorage.getItem(onoff) == null || sessionStorage.getItem(onoff) == 1) {
    		sessionStorage.setItem(onoff, 0)
    	}
        if (modalMotion == true) {
            modalDiv.fadeOut(modalMotionTime / 2);
        } else {
            modalDiv.hide();
        }
    })
    // 내용 클릭시 창이 닫히지 않도록 한다.
    modalCon.click(function(event) {
        event.preventDefault();
        event.stopPropagation();
    })
    // 닫기 버튼을 클릭시 모달창 닫기
    modalClose.click(function() {
    	if(sessionStorage.getItem(onoff) == null || sessionStorage.getItem(onoff) == 1) {
    		sessionStorage.setItem(onoff, 0)
    	}
        if (modalMotion == true) {
            modalDiv.fadeOut(modalMotionTime / 2);
        } else {
            modalDiv.hide();
        }
    })
    // 모달 창 보기 버튼 클릭
    modalShow.click(function() {
    	if(sessionStorage.getItem(onoff) == null || sessionStorage.getItem(onoff) == 0) {
    		sessionStorage.setItem(onoff, 1)
    	}
        if (modalMotion == true) {
            modalDiv.fadeIn(modalMotionTime);
        } else {
            modalDiv.show();
        }
    })
})