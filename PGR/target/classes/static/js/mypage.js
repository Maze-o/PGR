
var userFrmElem = document.querySelector('#userFrm')
var changeProfileBtn = document.querySelector('#changeProfileBtn')
var userEmailElem = document.querySelector('#userEmail')
var nicknameElem = userFrmElem.nickname
var nowPw = document.querySelector('#nowPw')
var newPw = document.querySelector('#newPw')
var newPwRe = document.querySelector('#newPwChk')

if (changeProfileBtn) {
	function ajax() {
		var param = {
			userEmail: userEmailElem.value,
			nickname: nicknameElem.value,
			userPw: nowPw.value,
			userNewPw: newPw.value,
			userPwRe: newPwRe.value
		}
		console.log(nicknameElem.value)
		fetch('/mypage', {
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
				alert('닉네임을 입력해 주십시오')
				nicknameElem.focus()
				return
			case 1:
				alert('비밀번호가 일치하지 않습니다')
				nowPw.focus()
				return
			case 2:
				alert('새로운 비밀번호를 입력해 주십시오')
				newPw.focus()
				return
			case 3:
				alert('새로운 비밀번호와 비밀번호 확인 칸이 일치하지 않습니다')
				return
			case 4:
				alert('회원정보 수정을 성공하셨습니다')
				location.href = '/mypage'
				return
		}
	}

}



changeProfileBtn.addEventListener('click', ajax)
