const schedule_table = document.querySelector('.schedule_table')
const game_content = document.querySelector('tbody')
const now = new Date()
let queryString = ''
	
if(schedule_table) {
	
	if(schedule_table.getAttribute('value') != '') {
		queryString = schedule_table.getAttribute('value') >= 10 ? schedule_table.getAttribute('value') : '0' + schedule_table.getAttribute('value')
	} else if(now.getMonth()+1 > 10) {
		queryString = now.getMonth()+1
	} else {
		queryString = '0' + (now.getMonth()+1)
	}
	
	fetch('/scheduleMatch?month=' + queryString)
	.then(res => res.json())
	.then(myJson => {
		createSchedule(myJson)
	})
	
	function createSchedule(myJson) {
		//empty game내용이 길어져서 보류
		/*const tempDate = new Date(myJson[0].date)
		const nowDate = new Date(tempDate.getFullYear(), (tempDate.getMonth()+1), 0) // 말일 수 구하기
		const arrayMonth = new Array(nowDate.getDate())
		
		for(var i=0;i<arrayMonth.length;i++) {
			createEmpty(i)
		}
		
		function createEmpty(i) {
			const empty_game = document.createElement('tr')
			const th = document.createElement('th')
			const td = document.createElement('td')
			
			const span = document.createElement('span')
			const span2 = document.createElement('span')
			
			const innerempty = document.createElement('div')
			const innerempty2 = document.createElement('div')
			
			var dayoftheweek = document.createTextNode('(일)')
			
			empty_game.className = 'empty_game'
			innerempty.className = 'inner empty'
			innerempty2.className = 'inner empty'
				
			td.setAttribute('colspan', 3)
			span.innerText = tempDate.getMonth()+1 + '/' + (i+1)
			span2.innerText = '경기가 없습니다.'
				
			innerempty.append(span)
			innerempty.append(dayoftheweek)
			th.append(innerempty)
			
			empty_game.append(th)
			
			innerempty2.append(span2)
			td.append(innerempty2)
			
			empty_game.append(td)
			
			game_content.append(empty_game)
		}*/

		var th_count = 0
		var reset_count = -1
		
		for(var i=0;i<myJson.length;i++) {
			const isGame = document.createElement('tr')
			
			const th = document.createElement('th')
			const td_time = document.createElement('td')
			const td_venue = document.createElement('td')
			const td_team = document.createElement('td')
			
			const div_inner1 = document.createElement('div')
			div_inner1.className = 'inner'
			const div_inner2 = div_inner1.cloneNode()
			const div_inner3 = div_inner1.cloneNode()
			const div_inner4 = div_inner1.cloneNode()
			const div_inner5 = document.createElement('div')
			const div_inner6 = document.createElement('div')
			
			const span1 = document.createElement('span')
			const span2 = document.createElement('span')
			const span3 = document.createElement('span')
			const span4 = document.createElement('span')
			const span5 = document.createElement('span')
			
			const limg = document.createElement('img')
			const rimg = document.createElement('img')
			
			var lString = 'img/teamlogo/' + myJson[i].lid + '.png'
			var rString = 'img/teamlogo/' + myJson[i].rid + '.png'
			
			isGame.className = 'expected_game'
			div_inner5.className = 'team_left'
			div_inner6.className = 'team_right'
			td_team.className = 'teams'
			th.className = 'selectTh'
			
			limg.src = lString
			rimg.src = rString
			
			const date = new Date(myJson[i].date)
			
			var dayoftheweek
			
			switch(date.getDay()) {
				case 0:
					dayoftheweek = document.createTextNode('(일)')
					break;
				case 1:
					dayoftheweek = document.createTextNode('(월)')
					break;
				case 2:
					dayoftheweek = document.createTextNode('(화)')
					break;
				case 3:
					dayoftheweek = document.createTextNode('(수)')
					break;
				case 4:
					dayoftheweek = document.createTextNode('(목)')
					break;
				case 5:
					dayoftheweek = document.createTextNode('(금)')
					break;
				case 6:
					dayoftheweek = document.createTextNode('(토)')
					break;
			}
			
			const hours = date.getHours() >= 10 ? date.getHours() : '0' + date.getHours()
			const mins = date.getMinutes() >= 10 ? date.getMinutes() : '0' + date.getMinutes()
			
			span1.innerText = (date.getMonth()+1) + '/' + date.getDate()
			span2.innerText = hours + ':' + mins
			span3.innerText = myJson[i].venue
			span4.innerText = myJson[i].lteam
			span5.innerText = myJson[i].rteam
			
			if(i > 0) {
				const preDate = new Date(myJson[i-1].date)

				if(date.getDate() != preDate.getDate()) {
					div_inner1.append(span1)
					div_inner1.append(dayoftheweek)
					th.append(div_inner1)
					isGame.append(th)
					reset_count++
					const selTh = document.getElementsByClassName('selectTh')[reset_count]
					if(selTh && th_count != 0) {
						selTh.setAttribute('rowspan', th_count)
						th_count = 1
					}
				}
				else {
					th_count++
				}
			} else {
				div_inner1.append(span1)
				div_inner1.append(dayoftheweek)
				th.append(div_inner1)
				isGame.append(th)
				th_count++
			}
			
			div_inner2.append(span2)
			td_time.append(div_inner2)
			isGame.append(td_time)
			
			div_inner3.append(span3)
			td_venue.append(div_inner3)
			isGame.append(td_venue)
			
			div_inner5.append(span4)
			div_inner5.append(limg)
			div_inner6.append(span5)
			div_inner6.append(rimg)
			
			div_inner4.append(div_inner5)
			div_inner4.append(div_inner6)
			td_team.append(div_inner4)
			isGame.append(td_team)
			
			game_content.append(isGame)
		}

		const selTh = document.getElementsByClassName('selectTh')[reset_count+1]
		selTh.setAttribute('rowspan', th_count+1)
	}
}

const findDateString = schedule_table.getAttribute('value') != '' ? schedule_table.getAttribute('value') : now.getMonth()+1

const findDate = document.querySelector('#date_' + findDateString)
if(findDate) {
	findDate.className = 'focus_date'
}
