var teamElem = document.querySelector('#team_table')

if(teamElem) {

	fetch('/teamdata')
	.then(res => res.json())
	.then(myJson => {
		createTable(myJson)
	})
	
	function createTable(myJson) {
		var table = document.createElement('table') // table 생성
		table.className = 'sortable'
		
		var thead = document.createElement('thead') // thead 생성
		var tbody = document.createElement('tbody') // tbody 생성
		
		var headtr = document.createElement('tr') // tr th 생성
		headtr.innerHTML = `
		<th>Name</th>
		<th>Wins</th>
		<th>Losses</th>
		<th>Ties</th>
		<th>GamesPlayed</th>
		<th>PointsFor</th>
		<th>PointsAgainst</th>
		<th>Points</th>
		<th>Streak</th>
		<th>RankChange</th>
		<th>Rank</th>
		<th>PointDifferential</th>
		<th>HomePointsFor</th>
		<th>HomePointsAgainst</th>
		<th>HomeLosses</th>
		<th>HomeWins</th>
		<th>HomeTies</th>
		<th>HomeGamesPlayed</th>
		<th>AwayPointsFor</th>
		<th>AwayPointsAgainst</th>
		<th>AwayLosses</th>
		<th>AwayWins</th>
		<th>AwayTies</th>
		<th>AwayGamesPlayed</th>`

		thead.append(headtr)
		table.append(thead)
		table.append(tbody)
		
		myJson.forEach(function(item) {
			var tr = document.createElement('tr')
			var name = document.createElement('td')
			var wins = document.createElement('td')
			var losses = document.createElement('td')
			var ties = document.createElement('td')
			var gamesplayed = document.createElement('td')
			var pointsfor = document.createElement('td')
			var pointsagainst = document.createElement('td')
			var points = document.createElement('td')
			var streak = document.createElement('td')
			var rankchange = document.createElement('td')
			var rank = document.createElement('td')
			var pointdifferential = document.createElement('td')
			var homepointsfor = document.createElement('td')
			var homepointsagainst = document.createElement('td')
			var homelosses = document.createElement('td')
			var homewins = document.createElement('td')
			var hometies = document.createElement('td')
			var homegamesplayed = document.createElement('td')
			var awaypointsfor = document.createElement('td')
			var awaypointsagainst = document.createElement('td')
			var awaylosses = document.createElement('td')
			var awaywins = document.createElement('td')
			var awayties = document.createElement('td')
			var awaygamesplayed = document.createElement('td')
			
			tr.append(name)
			tr.append(wins)
			tr.append(losses)
			tr.append(ties)
			tr.append(gamesplayed)
			tr.append(pointsfor)
			tr.append(pointsagainst)
			tr.append(points)
			tr.append(streak)
			tr.append(rankchange)
			tr.append(rank)
			tr.append(pointdifferential)
			tr.append(homepointsfor)
			tr.append(homepointsagainst)
			tr.append(homelosses)
			tr.append(homewins)
			tr.append(hometies)
			tr.append(homegamesplayed)
			tr.append(awaypointsfor)
			tr.append(awaypointsagainst)
			tr.append(awaylosses)
			tr.append(awaywins)
			tr.append(awayties)
			tr.append(awaygamesplayed)
			
			name.innerText = item.name
			wins.innerText = item.wins
			losses.innerText = item.losses
			ties.innerText = item.ties
			gamesplayed.innerText = item.gamesPlayed
			pointsfor.innerText = item.pointsFor
			pointsagainst.innerText = item.pointsAgainst
			points.innerText = item.points
			streak.innerText = item.streak
			rankchange.innerText = item.rankChange
			rank.innerText = item.rank
			pointdifferential.innerText = item.pointDifferential
			homepointsfor.innerText = item.homePointsFor
			homepointsagainst.innerText = item.homePointsAgainst
			homelosses.innerText = item.homeLosses
			homewins.innerText = item.homeWins
			hometies.innerText = item.homeTies
			homegamesplayed.innerText = item.homeGamesPlayed
			awaypointsfor.innerText = item.awayPointsFor
			awaypointsagainst.innerText = item.awayPointsAgainst
			awaylosses.innerText = item.awayLosses
			awaywins.innerText = item.awayWins
			awayties.innerText = item.awayTies
			awaygamesplayed.innerText = item.awayGamesPlayed
			
			tbody.append(tr)
		})
		
		sorttable.makeSortable(table);
		
		teamElem.innerHTML = ''
		teamElem.append(table)
	}
}