
function getMaxPageNum() {
	fetch(`/gallery/getMaxPageNum?rowCnt=5`)
		.then(res => res.json())
		.then(myJson => {
			pageProc(myJson)
		})
}

const pagingContentElem = document.querySelector('#pagingContent')
function pageProc(myJson) {
	console.log(myJson)

	for (const i = 1; i <= myJson; i++) {
		const span = document.createElement('span')
		span.innerText = i
		span.classList.add('pointer')
		
		if (i === 1){
			span.classList.add('selected')
		}
		span.addEventListener('click', function() {
			getBoardList(i)

			const spanList = pagingContentElem.children
			console.log(spanList)
			for (const z = 0; z < spanList.length; z++) {
				spanList[z].classList.remove('selected')
			}

			span.classList.add('selected')
		})
		pagingContentElem.append(span)
	}

}

getMaxPageNum()