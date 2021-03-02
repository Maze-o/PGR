const temps = document.querySelector('.bettingroom_container')

if(temps) {
	fetch('/recentMatch?id=' + temps.getAttribute('value'))
	.then(res => res.json())
	.then(myJson => {
		createText(myJson)
	})
	temps.removeAttribute('value')
	
	function createText(myJson) {
		const lteam = document.querySelector('.leftContainer')
		const rteam = document.querySelector('.rightContainer')
		const empty = document.querySelector('.empty')
		const spanDate = document.createElement('span')
		const spanVenue = document.createElement('span')
		const spanVs= document.createElement('span')
		const dates = new Date(myJson.date)
		
		const lteamNode = document.createTextNode(myJson.lteam)
		const rteamNode = document.createTextNode(myJson.rteam)
		
		lteam.prepend(lteamNode)
		rteam.prepend(rteamNode)
		spanDate.innerText = dates.toLocaleString()
		spanVenue.innerText = '장소:' + myJson.venue
		spanVs.innerText = 'VS'
		
		empty.append(spanDate)
		empty.append(spanVenue)
		empty.append(spanVs)
	}
}