const temps = document.querySelector('.bettingroom_container')

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
		const dates = new Date(myJson.date)	
		
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
					recordiv1.className = 'record'
					
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

const betFrm = document.querySelector('#betFrm')
if(betFrm) {
	const buttons = document.querySelector('#buttons')
	buttons.addEventListener('click', function() {
		ajax()
	})
	
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