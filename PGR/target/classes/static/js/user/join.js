const formElem = document.querySelector('#form')
const temp = document.querySelector('#temp')

// 이메일 인증이 완료되기 전까지는 밑에 칸들을 입력할수없도록 막음
const userPw = formElem.userPw
const userPwRe = formElem.userPwRe
const nickname = formElem.nickname

closeElement()
function closeElement() {
	userPw.disabled = true // userPw칸 비활성화
	userPwRe.disabled = true // userPwRe칸 비활성화
	nickname.disabled = true // nickname칸 비활성화
}


// 회원가입 버튼을 눌렀을때 이벤트 발생
const chkjoinButton = document.querySelector('#joinButton')

if (chkjoinButton) {
	const userEmailElem = formElem.userEmail
	const anno = formElem.anno
	const textEmailElem = formElem.textEmail
	const userPwElem = formElem.userPw
	const userPwReElem = formElem.userPwRe
	const nicknameElem = formElem.nickname

	function ajax() {
		const userEmail = userEmailElem.value + anno.value + textEmailElem.value
		const param = {
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
		}).then(function(res) {
			return res.json()
		}).then(function(myJson) {
			proc(myJson)
		})

		function proc(myJson) {
			if (userPw.disabled) { // userPw값이 disabled상태이면 실행 (이메일 인증을 완료하지 않은 상태)
				alert('이메일 인증을 완료해 주세요')
				return
			}
			switch (myJson.result) {
				case 0:
					alert('아이디(이메일)을 확인해 주세요')
					return
				case 1:
					alert('이미 존재하는 아이디(이메일)입니다')
					location.reload() // 새로고침 해줌
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
					if (temp.classList){
						alert('회원가입을 축하합니다!')
						history.go(-1) // 원래 있던 페이지로 돌아가게 해줌
						return
					} else {
						alert('이메일 인증을 완료해 주세요')
						return
					}
			}
		}
	}

	chkjoinButton.addEventListener('click', ajax)
}



// 이메일 인증 버튼을 눌렀을때 이벤트 발생
const emailsendButton = document.querySelector('#emailsend')
const email = formElem.userEmail
const textEmail = formElem.textEmail
const anno = formElem.anno

if (emailsendButton) {
	function ajax() {

		const param = email.value + anno.value + textEmail.value
		
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

const emailchkbtn = document.querySelector('#emailchkbtn')

if (emailchkbtn) {
	const emailchk = document.querySelector('#emailchk')
	function ajax() {
		const param = emailchk.value

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
		switch (myJson) {
			case 0:
				alert('인증번호를 확인해 주세요')
				return
			case 1:
				// 인증번호가 일치했을 때 모달창을 없애면서 밑에 칸들을 입력할수 있게 변경
				alert('인증번호가 일치합니다.')
				changeEmail() // 인증번호가 일치하면 changeEmail() 함수 호출
				return
		}
	}

	// 호출 당하면 disabled들을 false로 변경 (입력 가능하게 해줌) 
	function changeEmail() {
		temp.innerHTML = `<input type="button" value="이메일 인증 완료" class="confirm_btn" disabled>`

		userPw.disabled = false
		userPwRe.disabled = false
		nickname.disabled = false
	}

}

function changeDomain() {
	const domainSelect = document.getElementById('domainSelect')
	const textEmail = document.getElementById('textEmail')

	// select 박스에서 직접입력 선택시 실행
	if (domainSelect.value == 'direct') {
		textEmail.disabled = false // textEmail박스의 disabled을 false로 변경 (입력 가능하게 해줌)
		textEmail.value = null // textEmail의 값을 비워줌
		textEmail.focus()
	// 직접입력이 아닌 다른 값들을 선택시
	} else {
		textEmail.disabled = true // textEmail박스를 입력하지 못하게 변경
		textEmail.value = domainSelect.value // textEmail의 value를 select박스의 값으로 변경
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

// 이메일 정규표현식
function chkSign() {
	// 이메일 앞쪽 ( @포함 )
	const signupFrm = document.getElementById('form')
	const emailVal = signupFrm.email.value
	const emailChk = /^[\w.\-_]+$/i

	// 이메일 뒷쪽 (도메인)
	const textEmailVal = signupFrm.textEmail.value
	const textEmailChk = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{3}$/i

	if (!emailChk.test(emailVal)) { // 이메일 앞쪽이 정규표현식에 부합하면 실행
		alert('이메일을 확인해 주세요')	
		return false
	} else if (!textEmailChk.test(textEmailVal)) { // 이메일 뒷쪽이 정규표현식에 부합하면 실행
		alert('이메일을 확인해 주세요')
		return false
	}

	return true
}
