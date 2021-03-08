
const formElem = document.querySelector('#form')

const writeEditBtn = document.querySelector('#writeEditBtn')

if (writeEditBtn) {
	function ajax() {
		const titleElem = formElem.title
		const ctntElem = formElem.ctnt
		const userPkElem = formElem.userPk

		const param = {
			title: titleElem.value,
			ctnt: ctntElem.value,
			userPk: userPkElem.value
		}
		console.log(param)

		fetch('/writeEdit', {
			method: 'post',
			headers: {
				'Content-type': 'application/json',
			},
			body: JSON.stringify(param)
		}).then(res => {
			return res.json()
		}).then(myJson => {
			proc(myJson)
		})
	}

	function proc(myJson) {
		switch (myJson.result) {
			case 0:
				alert('제목을 입력해 주십시오')
				return
			case 1:
				alert('내용을 입력해 주십시오')
				return
			case 2:
				location.href = "/home"
				return
		}
	}
}

writeEditBtn.addEventListener('click', ajax)