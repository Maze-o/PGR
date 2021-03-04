var formElem = document.querySelector('#form')

// 이메일 인증이 완료되기 전까지는 밑에 칸들을 입력할수없도록 막음
var userPw = formElem.userPw
var userPwRe = formElem.userPwRe
var nickname = formElem.nickname

closeElement()
function closeElement() {

	userPw.disabled = true
	userPwRe.disabled = true
	nickname.disabled = true
}


// 회원가입 버튼을 눌렀을때 이벤트 발생
var chkjoinButton = document.querySelector('#joinButton')

if (chkjoinButton) {
	var userEmailElem = formElem.userEmail
	var anno = formElem.anno
	var textEmailElem = formElem.textEmail
	var userPwElem = formElem.userPw
	var userPwReElem = formElem.userPwRe
	var nicknameElem = formElem.nickname

	function ajax() {
		var userEmail = userEmailElem.value + anno.value + textEmailElem.value
		var param = {
			userEmail: userEmail,
			userPw: userPwElem.value,
			userPwRe: userPwReElem.value,
			nickname: nicknameElem.value
		}

		fetch('/join', {
			method: 'post',
			headers: {
				'Content-type': 'application/json',
			},
			body: JSON.stringify(param)

		})

			.then(function(res) {
				return res.json()
			}).then(function(myJson) {
				proc(myJson)
			})

		function proc(myJson) {
			console.log('비활성화 : ' + userPw.disabled)
			if (userPw.disabled){
				alert('이메일 인증을 완료해 주세요')
				return
			}
			switch (myJson.result) {
				case 0:
					alert('아이디(이메일)을 확인해 주세요')
					return
				case 1:
					alert('이미 존재하는 아이디(이메일)입니다')
					location.reload()
					return
				case 2:
					alert('비밀번호를 입력해 주세요')
					return
				case 3:
					alert('비밀번호 확인 칸을 입력해 주세요')
					return
				case 4:
					alert('닉네임을 입력해 주세요')
					return
				case 5:
					alert('비밀번호와 비밀번호 확인 칸이 같은지 확인해 주세요')
					return
				case 6:
					alert('회원가입을 축하합니다!')
					location.href = '/login'
					return
			}
		}
	}

	chkjoinButton.addEventListener('click', ajax)
}



// 이메일 인증 버튼을 눌렀을때 이벤트 발생
var emailsendButton = document.querySelector('#emailsend')
var email = formElem.userEmail
var textEmail = formElem.textEmail
var anno = formElem.anno

if (emailsendButton) {
	function ajax() {
		
		var param = email.value + anno.value + textEmail.value
		
		fetch('/email', {
			method: 'post',
			headers: {
				'Content-type': 'application/json',
			},
			body: JSON.stringify(param)
		})
	}
}

setModal()
// 이메일 인증 모달 창에서 confirm버튼을 눌렀을때 이벤트 발생
emailsendButton.addEventListener('click', ajax)

var emailchkbtn = document.querySelector('#emailchkbtn')

if (emailchkbtn) {
	var emailchk = document.querySelector('#emailchk')
	function ajax() {
		var param = emailchk.value

		console.log(emailchk.value)
		fetch('/verifyCode', {
			method: 'post',
			headers: {
				'Content-type': 'application/json',
			},
			body: param

		}).then(function(res) {
			return res.json()
		}).then(function(myJson) {
			console.log(myJson)
			proc(myJson)
		})

	}


	function proc(myJson) {
		console.log(myJson)
		switch (myJson) {
			case 0:
				alert('인증번호를 확인해 주세요')
				return
			case 1:
				// 인증번호가 일치했을 때 모달창을 없애면서 밑에 칸들을 입력할수 있게 변경
				alert('인증번호가 일치합니다.')
				changeEmail()
				return
		}

	}


	function changeEmail() {
		var temp = document.querySelector('#temp')
		temp.innerHTML = `<input type="button" value="이메일 인증 완료" class="confirm_btn" disabled>`

		userPw.disabled = false
		userPwRe.disabled = false
		nickname.disabled = false
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
emailchkbtn.addEventListener('click', ajax)


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
		if (chkSign()) {
			openModal()
		}
	})
}

function openModal() {
	modalDiv.classList.add('open')
}

function closeModal() {
	modalDiv.classList.remove('open')
}

function chkSign() {
	// 이메일 앞쪽 ( @포함 )
	const signupFrm = document.getElementById('form')
	const emailVal = signupFrm.email.value
	const emailChk = /^[\w.\-_]+$/i

	// 이메일 뒷쪽 (도메인)
	const textEmailVal = signupFrm.textEmail.value
	const textEmailChk = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{3}$/i

	if (!emailChk.test(emailVal)) {
		alert('이메일을 확인해 주세요')	
		return false
	} else if (!textEmailChk.test(textEmailVal)) {
		alert('이메일을 확인해 주세요')
		return false
	}

	return true
}
