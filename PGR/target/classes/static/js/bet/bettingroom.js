const temps = document.querySelector('.bettingroom_container')
let dates

if(temps) {
	fetch('/recentMatch?id=' + temps.getAttribute('value'))
	.then(res => res.json())
	.then(myJson => {
		createText(myJson)
	})
	
	function createText(myJson) {
		const lteam = document.querySelector('.leftContainer')
		const rteam = document.querySelector('.rightContainer')
		const empty = document.querySelector('.empty')
		const left = document.querySelector('.lTeam')
		const right = document.querySelector('.rTeam')
		const spanDate = document.createElement('span')
		const spanVenue = document.createElement('span')
		const spanVs= document.createElement('span')
		dates = new Date(myJson.date)
		
		var date_string = myJson.date
		const apidate = date_string.substring(0, 4) + date_string.substring(5, 7) + date_string.substring(8, 10)
		
		fetch('http://site.api.espn.com/apis/site/v2/sports/soccer/eng.1/scoreboard?dates=' + apidate)
		.then(res => res.json())
		.then(myJson => {
			for(var i=0;i<myJson.events.length;i++) {
				if(myJson.events[i].id == temps.getAttribute('value')) {
					var lform = myJson.events[i].competitions[0].competitors[0].form
					var rform = myJson.events[i].competitions[0].competitors[1].form
					
					const recordiv1 = document.createElement('div')
					
					const recordiv2 = recordiv1.cloneNode()
					const recordiv3 = recordiv1.cloneNode()
					const recordiv4 = recordiv1.cloneNode()
					const recordiv5 = recordiv1.cloneNode()
					
					const recordiv6 = recordiv1.cloneNode()
					const recordiv7 = recordiv1.cloneNode()
					const recordiv8 = recordiv1.cloneNode()
					const recordiv9 = recordiv1.cloneNode()
					const recordiv10 = recordiv1.cloneNode()
					
					recordiv1.innerText = lform.charAt(0)
					recordiv2.innerText = lform.charAt(1)
					recordiv3.innerText = lform.charAt(2)
					recordiv4.innerText = lform.charAt(3)
					recordiv5.innerText = lform.charAt(4)
					
					recordiv6.innerText = rform.charAt(0)
					recordiv7.innerText = rform.charAt(1)
					recordiv8.innerText = rform.charAt(2)
					recordiv9.innerText = rform.charAt(3)
					recordiv10.innerText = rform.charAt(4)
					
					changeClass(recordiv1)
					changeClass(recordiv2)
					changeClass(recordiv3)
					changeClass(recordiv4)
					changeClass(recordiv5)
					changeClass(recordiv6)
					changeClass(recordiv7)
					changeClass(recordiv8)
					changeClass(recordiv9)
					changeClass(recordiv10)
					
					const lrecord = document.querySelector('#lrecord')
					lrecord.append(recordiv1)
					lrecord.append(recordiv2)
					lrecord.append(recordiv3)
					lrecord.append(recordiv4)
					lrecord.append(recordiv5)
					
					const rrecord = document.querySelector('#rrecord')
					rrecord.append(recordiv6)
					rrecord.append(recordiv7)
					rrecord.append(recordiv8)
					rrecord.append(recordiv9)
					rrecord.append(recordiv10)
					
					function changeClass(item) {
						item.className = item.innerText
					}
				}
			}
		})
		
		var limg = document.createElement('img')
		var rimg = document.createElement('img')
		
		var lString = 'img/teamlogo/' + myJson.lid + '.png'
		var rString = 'img/teamlogo/' + myJson.rid + '.png'
			
		limg.src = lString
		rimg.src = rString
		
		const lteamNode = document.createTextNode(myJson.lteam)
		const rteamNode = document.createTextNode(myJson.rteam)
		
		spanDate.className = 'empty_date'
		spanVenue.className = 'empty_venue'
		spanVs.className = 'empty_vs'
		
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

const empty = document.querySelector('.empty')
if(empty) {
	
	const time = document.createElement('span')
	time.className = 'empty_time'
	empty.prepend(time)
	setInterval("dpTime()", 1000)
	function dpTime() {
		var now = new Date()
		if(dates.getTime() > now.getTime()) { // 경기 시작전일때만
			now.setHours(now.getHours() + 9)
			now.setTime(dates.getTime() - now.getTime())
	
			months = now.getMonth()
			days = now.getDate()-1
			hours = now.getHours() + (days * 24) >= 10 ? now.getHours() + (days * 24) : '0' + (now.getHours() + (days * 24)) 
			minutes = now.getMinutes() >= 10 ? now.getMinutes() : '0' + now.getMinutes()
			seconds = now.getSeconds() >= 10 ? now.getSeconds() : '0' + now.getSeconds()
			
			time.innerText = '남은 시간  '+ hours + ' : ' + minutes + ' : ' + seconds
		}
		else {
			time.innerText = '경기가 진행중입니다.'
			const manage = document.querySelector('.betmanage')
			manage.innerHTML = ''
		}
	}
}

const betFrm = document.querySelector('#betFrm')
if(betFrm) {
	const buttons = document.querySelector('#buttons')
	if(buttons) {
		buttons.addEventListener('click', function() {
			ajax()
		})
	}
	
	function ajax() {
		const userp = document.querySelector('#userp')
		
		var param = {
			userPk: userp.getAttribute('value'),
			id: temps.getAttribute('value'),
			property: betFrm.property.value,
			team: value_check()
		}
		
		fetch('/bet', {
			method: 'post',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			console.log(res)
			return res.json()
		}).then(function(myJson) {
			console.log(myJson)
		})
		
		location.reload();
	}
	
	    function value_check() {
        var check_count = document.getElementsByName("chk_info").length;
 
        for (var i=0; i<check_count; i++) {
            if (document.getElementsByName("chk_info")[i].checked == true) {
                return document.getElementsByName("chk_info")[i].value;
            }
        }
    }
}

	const betchoice_boxElem = document.querySelector('.betchoice_box')
	if(betchoice_boxElem) {
			fetch('/betallocation?id=' + temps.getAttribute('value'))
			.then(res => res.json())
			.then(myJson => {
				betchoice(myJson)
			})	
			
			function betchoice(myJson) {

				const win = document.querySelector('.choice_win')
				const draw = document.querySelector('.choice_draw')
				const lose = document.querySelector('.choice_lose')

				const wdefault = "( x" + (myJson.w_allocation).toFixed(2) + ")"
				const ddefault = "( x" + (myJson.d_allocation).toFixed(2) + ")"
				const ldefault = "( x" + (myJson.l_allocation).toFixed(2) + ")"
				
				
				const win_text = document.createTextNode(wdefault)
				const draw_text = document.createTextNode(ddefault)
				const lose_text = document.createTextNode(ldefault)

				win.append(win_text)
				draw.append(draw_text)
				lose.append(lose_text)
				}
				
			}
	
	const betdetailFrm = document.querySelector('.betdetail')
	if(betdetailFrm) {
			const userp = document.querySelector('#userp')
			let myJson2 = null
		
			fetch('/betUser?id=' + temps.getAttribute('value') + '&userPk=' + userp.getAttribute("value"))
			.then(res => res.json())
			.then(myJson => {
				myJson2 = myJson
			})
			
			fetch('/betallocation?id=' + temps.getAttribute('value'))
			.then(res => res.json())
			.then(myJson => {
				betdetail(myJson)
			})
			
			function betdetail(myJson){
				const WDL = document.createElement('span')
				const span1 = document.createElement('span')
				const Property = document.createElement('span')
				const span2 = document.createElement('span')
				const Success = document.createElement('span')
				const span3 = document.createElement('span')
				
				WDL.className = 'WDL'
				span1.className = 'span1'
				Property.className = 'Property'
				span2.className = 'span2'
				Success.className = 'Success'
				span3.className = 'span3'
				
				switch(myJson2.team) {
					case 0:
						WDL.innerText = '승'
						Success.innerText = (myJson2.property * myJson.w_allocation - myJson2.property).toFixed(0) + 'p'
						break
					case 1:
						WDL.innerText = '무'
						Success.innerText = (myJson2.property * myJson.d_allocation - myJson2.property).toFixed(0) + 'p'
						break
					case 2:
						WDL.innerText = '패'
						Success.innerText = (myJson2.property * myJson.l_allocation - myJson2.property).toFixed(0) + 'p'
						break 
				}
				span1.innerText = '에 '
				Property.innerText = myJson2.property + 'p'
				span2.innerText = '를 거셨습니다. 예상 수익은 '
				span3.innerText = '입니다.'
				betdetailFrm.append(WDL)
				betdetailFrm.append(span1)
				betdetailFrm.append(Property)
				betdetailFrm.append(span2)
				betdetailFrm.append(Success)
				betdetailFrm.append(span3)
			}
	}