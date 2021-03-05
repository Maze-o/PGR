
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
		})
	}
}

writeEditBtn.addEventListener('click', ajax)