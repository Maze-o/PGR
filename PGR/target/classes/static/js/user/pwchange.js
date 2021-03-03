
var formElem = document.querySelector('#form')
var pwchange = document.querySelector('#pwchange')
var nowPw = document.querySelector('#nowPw')
var newPw = document.querySelector('#newPw')
var newPwRe = document.querySelector('#newPwChk')

if (pwchange) {
	function ajax() {
		var param = {
			userEmail: formElem.userEmail.value,
			userPw: nowPw.value,
			userNewPw: newPw.value,
			userPwRe: newPwRe.value
		}
		fetch('/pwchange', {
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
	}

	function proc(myJson) {
		switch (myJson.result) {
			case 0:
				alert('비밀번호가 일치하지 않습니다')
				nowPw.focus()
				return
			case 1:
				alert('새로운 비밀번호를 입력해 주십시오')
				newPw.focus()
				return
			case 2:
				alert('새로운 비밀번호와 비밀번호 확인 칸이 일치하지 않습니다')
				return
			case 3:
				alert('비밀번호 변경을 성공하셨습니다')
				location.href = '/mypage'
				return
		}
	}

}



pwchange.addEventListener('click', ajax)
