$(document).ready(function() {
    const modalDiv = document.querySelector('.email_modal')
    const modalCon = document.querySelector('.email_con')
    const modalClose = document.querySelector('.email_close')
    const modalShow = document.querySelector('#emailsend')

    const modalOpen = false;
    const modalMotion = true;
    // 1000 = 1초
    const modalMotionTime = true;

    // 초기에 보일지 말지 결정한다.
    if (modalOpen == true) {
        if (modalMotion == true) {
            modalDiv.fadeIn(modalMotionTime);
        } else {
            modalDiv.show();
        }
    }
    // 닫기 버튼을 클릭시 모달창 닫기
    modalClose.addEventListener('click', function(event) {
        if (event.target === modalClose) {
            modalDiv.classList.remove('open')
        } else {
        	false
        }
    })
    // 모달 창 보기 버튼 클릭
    modalShow.addEventListener('click', function() {
        modalDiv.classList.add('open')
    })
})

function chkSign() {
	// 이메일 앞쪽 ( @포함 )
    const signupFrm = document.getElementById('signupFrm')
    const emailVal = signupFrm.email.value
    const emailChk = /^[\w.\-_]+@$/i

    // 이메일 뒷쪽 (도메인)
    const textEmailVal = signupFrm.textEmail.value
    const textEmailChk = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{3}$/i

    if (!emailChk.test(emailVal) || !textEmailChk.test(textEmailVal)) {
        alert('잘못된 이메일 형식 입니다')
        email.focus()
    }

}

function changeDomain() {
    const domainSelect = document.getElementById('domainSelect')
    const textEmail = document.getElementById('textEmail')

    if (domainSelect.value == 'direct') {
        textEmail.disabled = false
        textEmail.value = null
        textEmail.focus()
    } else {
        textEmail.disabled = true
        textEmail.value = domainSelect.value
    }
}