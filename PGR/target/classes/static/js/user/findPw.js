console.log('test')
var findPwButton = document.querySelector('#find')

if (findPwButton) {
	var formElem = document.querySelector('#findFrm)
	var userEmailElem = document.querySelector('#ID')
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
			console.log(formElem.userEmail.value)
			console.log(formElem.nickname.value)
			console.log(param)
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










