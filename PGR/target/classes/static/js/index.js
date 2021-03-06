/*const mainleftElem = document.querySelector('.main_left')
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
}*/

// 좌측하단 팀 순위 출력하는 곳
const rankBoxCont = document.querySelector('.rank_box_cont')
if(rankBoxCont) {
	fetch('/teamdata')
	.then(res => res.json())
	.then(myJson => {
		rankBoxPrint(myJson)
	})
	
	function rankBoxPrint(myJson) {
		
		for(var i=0;i<5;i++) {
			const div = document.createElement('div')
			const div2 = document.createElement('div')
			const spanRank = document.createElement('span')
			const spanLogo = document.createElement('span')
			const spanScore = document.createElement('span')
			
			div.className = 'rank_box'
			div2.className = 'overlay'
			spanRank.innerText = myJson[i].rank
			spanLogo.innerHTML = `<img id="teamlogo" src="img/teamlogo/` + myJson[i].id + `.png">`
			spanScore.innerText = myJson[i].points
			
			div.append(div2)
			div.append(spanRank)
			div.append(spanLogo)
			div.append(spanScore)
			
			rankBoxCont.append(div)	
		}
	}
}

//메인 뉴스 출력해주는 곳
const swiperWrapper = document.querySelector('#newsPrint')
if(swiperWrapper) {
	fetch('http://site.api.espn.com/apis/site/v2/sports/soccer/eng.1/news')
	.then(res => res.json())
	.then(myJson => {
		newsPrint(myJson)
	})
	
	function newsPrint(myJson) {
		for(var i=0;i<myJson.articles.length;i++) {
			if(myJson.articles[i].images.length != 0) { 
			//console.log(myJson.articles[i].headline)
			//console.log(myJson.articles[i].images[0].url)
			//console.log(myJson.articles[i].links.web.href)
			const swiperSlide = document.createElement('div')
			const swText = document.createElement('div')
			const aHref = document.createElement('a')
			const headLine = document.createElement('span')
			const imgHref = document.createElement('img')
			
			swiperSlide.className = 'swiper-slide'
			swText.className = 'sw_text'
			imgHref.className = 'header_img'
			
			imgHref.src = myJson.articles[i].images[0].url
			headLine.innerText = myJson.articles[i].headline
			aHref.href = myJson.articles[i].links.web.href
			aHref.setAttribute('target', '_blank')
			
			swiperSlide.append(aHref)
			aHref.append(imgHref)
			aHref.append(swText)
			swText.append(headLine)
			
			swiperWrapper.append(swiperSlide)
			
			// news slide
			const sw_news = new Swiper('.sw_news', {
			    loop: true,
			    autoplay: {
			        delay: 3000,
			        disableOnInteraction: false,
			    },
			    navigation: {
			        prevEl: '.news_slide .sw_prev',
			        nextEl: '.news_slide .sw_next',
			    },
			    pagination: {
			        el: '.news_slide .sw_pg',
			        type: 'bullets',
			        // clickable: true,
			    },
			});
			
			}
		}
	}
}

// 하단 중간 종료된 베팅리스트
const bflist = document.querySelector('.bf_list')
if(bflist) {
	fetch('/endbetroomlist')
	.then(res => res.json())
	.then(myJson => {
		createTable(myJson)
	})
	
	function createTable(myJson) {
		for(var i=0;i<3;i++) {
			const vs_area = document.createElement('div')
			const limg = document.createElement('img')
			const rimg = document.createElement('img')
			
			const score_sec = document.createElement('div')
			const lscore = document.createElement('span')
			const rscore = document.createElement('span')
			
			const ahref = document.createElement('a')
			const bdate = document.createElement('div')
			var dates = new Date(myJson[i].date)
			
			const lString = 'img/teamlogo/' + myJson[i].lid + '.png'
			const rString = 'img/teamlogo/' + myJson[i].rid + '.png'
			
			vs_area.className = 'vs_area'
			limg.className = 'vs_img'
			rimg.className = 'vs_img'
			score_sec.className = 'score_sec'
			lscore.className = 'vs_score'
			rscore.className = 'vs_score'
			bdate.className = 'vs_date'
			
			limg.src = lString
			rimg.src = rString
			
			ahref.href = '/endbettingroom?id=' + myJson[i].id
			ahref.innerHTML = '<input type="button" value="상세보기" class="vs_btn">'
			lscore.innerText = myJson[i].lscore
			rscore.innerText = myJson[i].rscore
			bdate.innerText = dates.toLocaleString()
			
			vs_area.append(limg)
			vs_area.append(rimg)
			
			score_sec.append(lscore)
			score_sec.append(rscore)
			
			vs_area.append(score_sec)
			vs_area.append(bdate)
			vs_area.append(ahref)
			
			bflist.append(vs_area)
		}
	}
}

// 진행 예정인 베팅룸 리스트
const recentBet = document.querySelector('#recentBet')
if(recentBet) {
	fetch('/betroomlist')
	.then(res => res.json())
	.then(myJson => {
		createTable(myJson)
	})
	
	function createTable(myJson) {
		for(var i=0;i<3;i++) {
			const swiper_slide = document.createElement('div')
			const state_cont = document.createElement('div')
			const pl_profile = document.createElement('div')
			const state = document.createElement('span')
			
			const limg = document.createElement('img')
			const rimg = document.createElement('img')
			const lString = 'img/teamlogo/' + myJson[i].lid + '.png'
			const rString = 'img/teamlogo/' + myJson[i].rid + '.png'
			
			const nowdate = new Date()
			const matchdate = new Date(myJson[i].date)
			
			const dateHour = matchdate.getHours() >= 10 ? matchdate.getHours() : '0' + matchdate.getHours()
			const dateMinutes = matchdate.getMinutes() >= 10 ? matchdate.getMinutes() : '0' + matchdate.getMinutes()
			
			
			limg.src = lString
			rimg.src = rString
			
			swiper_slide.className = 'swiper-slide'
			state_cont.className = 'state_cont'
			pl_profile.className = 'pl_profile'
			limg.className = 'pl_img'
			rimg.className = 'pl_img'
			state.className = nowdate > matchdate ? 'state play' : 'state expected'
			
			pl_profile.setAttribute("data-text", dateHour + ':' + dateMinutes)
			
			state_cont.append(state)
			
			pl_profile.append(limg)
			pl_profile.append(rimg)
			
			swiper_slide.append(state_cont)
			swiper_slide.append(pl_profile)
			
			recentBet.append(swiper_slide)
		}
		
		// play_list slide
		const sw_pl = new Swiper('.sw_pl', {
		    loop: true,
		    autoplay: {
		        delay: 4000,
		        disableOnInteraction: false,
		    },
		    navigation: {
		        prevEl: '.pl_slide .sw_prev',
		        nextEl: '.pl_slide .sw_next',
		    },
		    pagination: {
		        el: '.pl_slide .sw_pg',
		        type: 'bullets',
		        // clickable: true,
		    },
		});
	}
}

const usercont = document.querySelector('.user_rank_cont')
if(usercont) {
	fetch('/topuser')
	.then(res => res.json())
	.then(myJson => {
		console.log(myJson)
		createTopuser(myJson)
	})
	
	function createTopuser(myJson) {
		
		for(var i=0;i<myJson.length;i++) {
			const userbox = document.createElement('div')
			const medalicon= document.createElement('div')
			const overlay = document.createElement('div')
			const userinfo = document.createElement('div')
			
			const imedal = document.createElement('i')
			
			const name = document.createElement('span')
			const point = document.createElement('span')
			
			userbox.className = 'user_box'
			medalicon.className = 'medal_icon_cont'
			
			userinfo.className = 'user_info'
			imedal.className = 'fas fa-medal fa-3x medal_icon'
			
			switch(i) {
				case 0:
					overlay.className = 'overlay gold_medal'
					break;
				case 1:
					overlay.className = 'overlay silver_medal'
					break;
				case 2:
					overlay.className = 'overlay bronze_medal'
					break;
			}
			
			name.innerText = myJson[i].nickname
			point.innerText = myJson[i].myProperty
			
			medalicon.append(imedal)
			userbox.append(medalicon)
			
			userbox.append(overlay)
			
			userinfo.append(name)
			userinfo.append(point)
			
			userbox.append(userinfo)
			usercont.append(userbox)
		}
	}
}
