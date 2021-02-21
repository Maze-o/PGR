
var chkjoinButton = document.querySelector('#joinButton')

if (chkjoinButton) {
	var formElem = document.querySelector('#form')
	var userEmailElem = formElem.userEmail
	var textEmailElem = formElem.textEmail
	var userPwElem = formElem.userPw
	var userPwReElem = formElem.userPwRe
	var nicknameElem = formElem.nickname
	
	function ajax() {
		console.log(userEmailElem.value)
		console.log(textEmailElem.value)
		var userEmail = userEmailElem.value + textEmailElem.value
		console.log(userEmail)
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
			console.log(myJson.result)
			switch (myJson.result) {
				case 0:
					alert('아이디(이메일)을 확인해 주세요')
					return
				case 1:
					alert('이미 존재하는 아이디(이메일)입니다')
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
					location.href = '/login'
					return
			}
		}
	}

	chkjoinButton.addEventListener('click', ajax)
}


/*
var chkjoinEvent = document.querySelector('#joinEvent')

if (chkjoinEvent) {
	var frmElem = document.querySelector('#frm')
	var userIdElem = frmElem.userId
	var userPwElem = frmElem.userPw
	var userPwReElem = frmElem.userPwRe
	var nmElem = frmElem.nm
	function cleChk() { // 이상있으면 true, 없으면 false
		if (chkItem(userIdElem, 'Id')
			|| chkItem(userPwElem, 'Pw')
			|| chkItem(nmElem, '이름')) {
			return true
		} else if (userPwElem.value !== userPwReElem.value) {
			alert('비밀번호를 확인해 주세요')
			frm.userPwElem.focus()
			return true
		}
		return false
	}
	
	function proc(myJson){
		if (myJson.result === 0) { //회원가입 실패
			alert('회원가입에 실패하셨습니다.')
			return
		} 
		
		location.href='/user/login'
	}	
	chkjoinEvent.addEventListener('click', function() {
		if (eleChk()) { return }
		ajax()
	})
}
*/