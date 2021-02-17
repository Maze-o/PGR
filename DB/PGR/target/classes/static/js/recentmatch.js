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
			var date = item.date
			div.innerHTML = `
			${item.lteam}(${item.lscore}) VS ${item.rteam}(${item.rscore}) date: ${item.date}` + ing
			
			mainleftElem.append(div)
		})
	}
	
}