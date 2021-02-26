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
	console.log('asd')
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
	}
}