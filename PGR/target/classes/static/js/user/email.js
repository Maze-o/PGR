
var emailsendButton = document.querySelector('#emailsend')
var formElem = document.querySelector('#form')
var email = formElem.userEmail
var textEmail = formElem.textEmail

if (emailsendButton) {
	function ajax() {
		var param = email.value + textEmail.value
		console.log(param)
		if (email.value == '') {
			alert('이메일을 입력해 주세요')
		}

		console.log(param)
		fetch('/email', {
			method: 'post',
			headers: {
				'Content-type': 'application/json',
			},
			body: JSON.stringify(param)
		})
	}
}
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
				alert('인증번호가 일치합니다.')

		}

	}

}

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
emailchkbtn.addEventListener('click', ajax)
