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

const modalDiv = document.querySelector('.email_modal')

function setModal() {
	const modalClose = document.querySelector('.email_close')
	const modalShow = document.querySelector('#emailsend')

	// 닫기 버튼을 클릭시 모달창 닫기
	modalClose.addEventListener('click', function(event) {
		if (event.target === modalClose) {
			closeModal()
		}
	})
	// 모달 창 보기 버튼 클릭
	modalShow.addEventListener('click', function() {
		if(chkSign()) {
			openModal()
		}
	} )
}
setModal()

function chkSign() {
	// 이메일 앞쪽 ( @포함 )
	const signupFrm = document.getElementById('signupFrm')
	const emailVal = signupFrm.email.value
	const emailChk = /^[\w.\-_]+@$/i

	// 이메일 뒷쪽 (도메인)
	const textEmailVal = signupFrm.textEmail.value
	const textEmailChk = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{3}$/i

	if (!emailChk.test(emailVal)) {
		alert('잘못된 이메일 형식 입니다')
		return false
	} else if(!textEmailChk.test(textEmailVal)) {
		alert('잘못된 도메인 입니다')
		return false
	}

	return true
}

function openModal() {
	modalDiv.classList.add('open')
}

function closeModal() {
	modalDiv.classList.remove('open')
}
