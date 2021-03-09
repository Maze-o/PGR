
// 삭제 ajax처리
const pkvalue = document.querySelector('#pkvalue')
const btnDelElem = document.querySelector("#btnDel")

if (btnDelElem) {
	btnDelElem.addEventListener('click', function() {
		if (confirm('삭제하시겠습니까 ?')) {
			ajax()
		}
	})
	console.log(pkvalue.value)

	function ajax() {
		const pk = pkvalue.value
		fetch(`/del/${pk}`, {
			method: 'delete',
		}).then(function() {
			location.href = '/gallery'
		})
	}
}
