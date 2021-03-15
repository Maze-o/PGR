
var findPwButton = document.querySelector('#findPw')

if (findPwButton) {
	var formElem = document.querySelector('#form')
	var userEmailElem = document.querySelector('#userEmail')
	var nicknameElem = document.querySelector('#nickname')


	function ajax() {
		var param = {
			userEmail: formElem.userEmail.value,
			nickname: formElem.nickname.value
		}
		fetch('/findpw', {
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
			case 1:
				alert('이메일을 확인해 주세요')
				userEmailElem.focus()
				return
			case 2:
				alert('닉네임을 확인해 주세요')
				nicknameElem.focus()
				return
			case 3:
				alert('입력된 이메일로 임시 비밀번호를 발송하였습니다.')
				location.href = '/login'
				return
		}
	}



	findPwButton.addEventListener('click', ajax)
}
