const temps = document.querySelector('.playcontainer')
const idvalue = temps.getAttribute('value')

if(temps) {
	fetch('/record?id=' + idvalue)
	.then(res => res.json())
	.then(myJson => {
		createText(myJson)
	})
	temps.removeAttribute('value')
	
	function createText(myJson) {
		const leftinfo = document.querySelector('.left-information')
		const rightinfo = document.querySelector('.right-information')
		
		const ul1 = document.createElement('ul')
		const li1 = document.createElement('li')
		const li2 = document.createElement('li')
		const li3 = document.createElement('li')
		const li4 = document.createElement('li')
		const li5 = document.createElement('li')
		
		const ul2 = document.createElement('ul')
		const li12 = document.createElement('li')
		const li22 = document.createElement('li')
		const li32 = document.createElement('li')
		const li42 = document.createElement('li')
		const li52 = document.createElement('li')
		
		li1.innerText = '슈팅 : ' + myJson.ltotalShots
		li2.innerText = '점유율 : ' + myJson.lpossessionPct + '%'
		li3.innerText = '유효슈팅 : ' + myJson.lshotsOnTarget
		li4.innerText = '파울 : ' + myJson.lfoulsCommitted
		li5.innerText = '코너킥 : ' + myJson.lwonCorners
		
		li12.innerText = '슈팅 : ' + myJson.rtotalShots
		li22.innerText = '점유율 : ' + myJson.rpossessionPct + '%'
		li32.innerText = '유효슈팅 : ' + myJson.rshotsOnTarget
		li42.innerText = '파울 : ' + myJson.rfoulsCommitted
		li52.innerText = '코너킥 : ' + myJson.rwonCorners
		
		ul1.append(li1)
		ul1.append(li3)
		ul1.append(li2)
		ul1.append(li4)
		ul1.append(li5)
		
		leftinfo.append(ul1)
		
		ul2.append(li12)
		ul2.append(li32)
		ul2.append(li22)
		ul2.append(li42)
		ul2.append(li52)
		
		rightinfo.append(ul2)
	}
}

const container = document.querySelector('.container')
const betcontainer = document.querySelector('.betcontainer')
if(container) {
	fetch('/recentMatch?id=' + idvalue)
	.then(res => res.json())
	.then(myJson => {
		createText(myJson)
	})
	
	function createText(myJson) {
		const leftinfo = document.querySelector('.left-information')
		const rightinfo = document.querySelector('.right-information')
		const lteam = document.querySelector('.leftContainer')
		const rteam = document.querySelector('.rightContainer')
		const lscore = document.querySelector('.lscore')
		const rscore = document.querySelector('.rscore')
		const date = document.querySelector('.date')
		const venue = document.querySelector('.stadium')
		const dates = new Date(myJson.date)
		
		const lspan = document.createElement('span')
		const rspan = document.createElement('span')
		var limg = document.createElement('img')
		var rimg = document.createElement('img')
		
		var lString = 'img/teamlogo/' + myJson.lid + '.png'
		var rString = 'img/teamlogo/' + myJson.rid + '.png'

		limg.src = lString
		rimg.src = rString
		
		if(myJson.lscore > myJson.rscore) {
      		lteam.className += 'colorblue'
			rteam.className += 'colorred'
		} else if(myJson.lscore < myJson.rscore) {
			lteam.className += 'colorred'
			rteam.className += 'colorblue'
		} else if(myJson.lscore === myJson.rscore) {
      		lteam.className += 'colorgray'
			rteam.className += 'colorgray'
		}
		
		lteam.append(lspan)
		rteam.append(rspan)
		lspan.innerText = myJson.lteam
		rspan.innerText = myJson.rteam
		lteam.append(limg)
		rteam.append(rimg)
		lscore.innerText = myJson.lscore
		rscore.innerText = myJson.rscore
		date.innerText = dates.toLocaleString()
		venue.innerText = myJson.venue
	}
}

if(betcontainer) {
	let myJson2 = null
	fetch('/recentMatch?id=' + idvalue)
	.then(res => res.json())
	.then(myJson => {
		myJson2 = myJson
	})
	
	
	fetch('/betallocation?id=' + idvalue)
	.then(res => res.json())
	.then(myJson => {
		createAllocation(myJson)
		console.log(myJson)
	})
	
	function createAllocation(myJson) {
		const betgraph = document.querySelector('.betgraph')
		const allocation = document.querySelector('.allocation')
		const w_percent = document.getElementsByClassName('share_1')[0]
		const d_percent = document.getElementsByClassName('share_2')[0]
		const l_percent = document.getElementsByClassName('share_3')[0]
		const horizontalbar_w = document.createElement('div')
		const horizontalbar_d = document.createElement('div')
		const horizontalbar_l = document.createElement('div')
		
		w_percent.style.width = myJson.w_percent.toFixed(1) + '%'
		d_percent.style.width = myJson.d_percent.toFixed(1) + '%'
		l_percent.style.width = myJson.l_percent.toFixed(1) + '%'
		
		if(myJson2.lscore > myJson2.rscore) {
			w_percent.className += 'change'
		} else if(myJson2.lscore < myJson2.rscore) {
			l_percent.className += 'change'
		} else if(myJson2.lscore === myJson2.rscore) {
			d_percent.className += 'change'
		}
		horizontalbar_w.innerText  = '승 : x'+ myJson.w_allocation.toFixed(2)
		horizontalbar_d.innerText  = '무 : x' + myJson.d_allocation.toFixed(2)
		horizontalbar_l.innerText  = '패 : x' + myJson.l_allocation.toFixed(2)
		
		horizontalbar_w.className = 'horizontalbar_w'
		horizontalbar_d.className = 'horizontalbar_d'		
		horizontalbar_l.className = 'horizontalbar_l'
		
		allocation.append(horizontalbar_w)
		allocation.append(horizontalbar_d)
		allocation.append(horizontalbar_l)
	}
}