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
		const left = document.querySelector('.lTeam')
		const right = document.querySelector('.rTeam')
		const spanDate = document.createElement('span')
		const spanVenue = document.createElement('span')
		const spanVs= document.createElement('span')
		const dates = new Date(myJson.date)
		
		var limg = document.createElement('img')
		var rimg = document.createElement('img')
		
		var lString = 'img/teamlogo/' + myJson.lid + '.png'
		var rString = 'img/teamlogo/' + myJson.rid + '.png'
			
		limg.src = lString
		rimg.src = rString
		
		const lteamNode = document.createTextNode(myJson.lteam)
		const rteamNode = document.createTextNode(myJson.rteam)
		
		lteam.prepend(lteamNode)
		rteam.prepend(rteamNode)
		
		left.append(limg)
		right.append(rimg)	
		
		spanDate.innerText = dates.toLocaleString()
		spanVenue.innerText = '장소:' + myJson.venue
		spanVs.innerText = 'VS'
		
		empty.append(spanDate)
		empty.append(spanVenue)
		empty.append(spanVs)
	}
}