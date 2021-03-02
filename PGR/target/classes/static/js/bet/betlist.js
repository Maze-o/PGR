const containerElem = document.querySelector('.bet_container')
if(containerElem) {
	fetch('/betroomlist')
	.then(res => res.json())
	.then(myJson => {
		createTable(myJson)
	})
	
	function createTable(myJson) {
		
		for(var i=0;i<myJson.length;i++) {
			var ahref = document.createElement('a')
			var div = document.createElement('div')
			var lteam = document.createElement('div')
			var rteam = document.createElement('div')
			var span = document.createElement('span')
			var limg = document.createElement('img')
			var rimg = document.createElement('img')
			var lString = 'img/teamlogo/' + myJson[i].lid + '.png'
			var rString = 'img/teamlogo/' + myJson[i].rid + '.png'
			
			limg.src = lString
			rimg.src = rString
			
			ahref.href = `/bettingroom?id=${myJson[i].id}`
			div.className = 'box'
			lteam.className = 'lTeam'
			rteam.className = 'rTeam'
			limg.className = 'teamlogo'
			rimg.className = 'teamlogo'
			
			span.innerText = 'vs'
			lteam.innerText = myJson[i].lteam
			rteam.innerText = myJson[i].rteam
			
			lteam.append(limg)
			rteam.append(rimg)
			
			div.append(ahref)
			ahref.append(lteam)
			ahref.append(span)
			ahref.append(rteam)
			containerElem.append(div)
		}
	}
}

