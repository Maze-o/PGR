
var userFrmElem = document.querySelector('#userFrm')
var changeProfileBtn = document.querySelector('#changeProfileBtn')
var userEmailElem = document.querySelector('#userEmail')
var nicknameElem = userFrmElem.nickname
var nowPw = document.querySelector('#nowPw')
var newPw = document.querySelector('#newPw')
var newPwRe = document.querySelector('#newPwChk')

if (changeProfileBtn) {
	function ajax() {
		var param = {
			userEmail: userEmailElem.value,
			nickname: nicknameElem.value,
			userPw: nowPw.value,
			userNewPw: newPw.value,
			userPwRe: newPwRe.value
		}
		console.log(nicknameElem.value)
		fetch('/mypage', {
			method: 'post',
			headers: {
				'Content-type': 'application/json',
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(myJson) {
			proc(myJson)
		})
	}

	function proc(myJson) {
		switch (myJson.result) {
			case 0:
				alert('닉네임을 입력해 주십시오')
				nicknameElem.focus()
				return
			case 1:
				alert('비밀번호가 일치하지 않습니다')
				nowPw.focus()
				return
			case 2:
				alert('새로운 비밀번호를 입력해 주십시오')
				newPw.focus()
				return
			case 3:
				alert('새로운 비밀번호와 비밀번호 확인 칸이 일치하지 않습니다')
				return
			case 4:
				alert('회원정보 수정을 성공하셨습니다')
				location.href = '/mypage'
				return
		}
	}

}
changeProfileBtn.addEventListener('click', ajax)

const bet_table = document.querySelector('.bet_table')
const tbody = document.querySelector('tbody')
if(bet_table) {
	const userPk_value = bet_table.getAttribute("value")
	bet_table.removeAttribute("value")
	
	fetch('/betUserPk?userPk=' + userPk_value)
	.then(res => res.json())
	.then(myJson => {
		console.log(myJson)
		createBet(myJson)
	})
	
	function createBet(myJson) {
		myJson.forEach(function(item) {
			const tr = document.createElement('tr')
			const td_date = document.createElement('td')
			const td_result = document.createElement('td')
			const td_play = document.createElement('td')
			const limg = document.createElement('img')
			const rimg = document.createElement('img')
			const lscore = document.createElement('span')
			const rscore = document.createElement('span')
			const td_where = document.createElement('td')
			const td_point = document.createElement('td')
			
			const lString = 'img/teamlogo/' + item.lid + '.png'
			const rString = 'img/teamlogo/' + item.rid + '.png'
			
			const dates = new Date(item.date)
			
			td_result.className = item.bResult > 0 ? 'plus' : 'minus'
			td_play.className = 'play_result'
			lscore.className = 'after'
			
			const year_string = dates.getFullYear() - 2000
			const month_string = dates.getMonth()+1 >= 10 ? dates.getMonth()+1 : '0' + (dates.getMonth()+1)
			const date_string = dates.getDate() >= 10 ? dates.getDate() : '0' + dates.getDate()
			const hour_string = dates.getHours() >= 10 ? dates.getHours() : '0' + dates.getHours()
			const min_string = dates.getMinutes() >= 10 ? dates.getMinutes() : '0' + dates.getMinutes()
			let day_string
			
			switch(dates.getDay()) {
				case 0:
					day_string = '일'
					break;
				case 1:
					day_string = '월'
					break;
				case 2:
					day_string = '화'
					break;
				case 3:
					day_string = '수'
					break;
				case 4:
					day_string = '목'
					break;
				case 5:
					day_string = '금'
					break;
				case 6:
					day_string = '토'
					break;
			}
			
			td_date.innerText = year_string + '.' + month_string + '.' + date_string + ' ' + day_string + ' ' + hour_string + ':' + min_string
			if(item.bResult == 0) {
				td_result.innerText = '진행중'
			} else {
				td_result.innerText = item.bResult > 0 ? '+' + item.bResult : item.bResult
			}
			
			lscore.innerText = item.lscore
			rscore.innerText = item.rscore
			
			limg.src = lString
			rimg.src = rString
			
			switch(item.team) {
				case 0:
					td_where.innerText = '승'
					break
				case 1:
					td_where.innerText = '무'
					break
				case 2:
					td_where.innerText = '패'
					break
			}
			
			td_point.innerText = item.property
			
			
			tr.append(td_date)
			tr.append(td_result)
			td_play.append(limg)
			td_play.append(lscore)
			td_play.append(rscore)
			td_play.append(rimg)
			tr.append(td_play)
			tr.append(td_where)
			tr.append(td_point)
			
			tbody.append(tr)
		})
	}
}
