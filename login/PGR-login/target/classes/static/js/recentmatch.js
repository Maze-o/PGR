const mainleftElem = document.querySelector('.main_left')
if(mainleftElem) {
	fetch('/recent')
	.then(res => res.json())
	.then(myJson => {
		recentPrint(myJson)
	})
	
	function recentPrint(myJson) {
		myJson.forEach(function(item) {
			var div = document.createElement('div')
			var ing = item.completed == 1 ? '경기 완료' : '경기 예정'
			var date = new Date(item.date).toLocaleString()

			div.innerHTML = `<div>${item.lteam}(${item.lscore}) VS ${item.rteam}(${item.rscore})</div>경기시작시간: ${date}` + ing
			
			mainleftElem.append(div)
		})
	}
}