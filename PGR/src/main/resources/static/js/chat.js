var ws = new WebSocket("ws://" + location.host + "/chating")

const chatBox = document.querySelector('.others')
const chatlog = document.querySelector('.chatlogs')
const currentStatus = document.querySelector('#current_status')
const tempNick = document.querySelector('#tempNick') != null ? document.querySelector('#tempNick').getAttribute('value') : null
let chatCount = 0;

setTimeout(function() {
	if(chatlog) {
		chatlog.scrollTop = chatlog.scrollHeight
	}
}, 100)

fetch('/chatRecord')
.then(res => res.json())
.then(myJson => {
	myJson.forEach(function(item) {
		const pclass = document.createElement('p')
		const nickname = document.createElement('span')
		const textnode = document.createTextNode(item.msg)
		
		pclass.className = 'chat_message'
		nickname.innerText = item.nickname + ': '
		pclass.append(nickname)
		pclass.append(textnode)
		chatBox.append(pclass)
		
		//chatBox.innerHTML += "<p class='chat_message'> <span>" + "asd: " + "</span>" + msg.value + "</p>"
		chatCount++;
	})
})

wsEvt();
function wsEvt() {
	ws.onopen = function(data){
	}
	
	ws.onmessage = function(data) {
		const msg = JSON.parse(data.data)
		if(msg.type == 'chat') {
	    	if(chatlog) {
	    		chatlog.scrollTop = chatlog.scrollHeight
	    	}
				const pclass = document.createElement('p')
				const nickname = document.createElement('span')
				const textnode = document.createTextNode(msg.value)
				
				pclass.className = 'chat_message'
				nickname.innerText = msg.nickname + ': '
				pclass.append(nickname)
				pclass.append(textnode)
				chatBox.append(pclass)
				
				//chatBox.innerHTML += "<p class='chat_message'> <span>" + "asd: " + "</span>" + msg.value + "</p>"
				chatCount++;
				if(chatCount > 50) {
					chatBox.removeChild(chatBox.childNodes[0]); // 채팅이 50개가 넘어가면 가장 오래된 메시지 삭제
					chatCount--;
				}
		}
		if(msg.type == 'status') {
				currentStatus.innerText = '홈페이지 동시접속자수: ' + msg.value
		}
	}

	document.addEventListener("keypress", function(e){
		if(e.keyCode == 13){ //enter press
			send();
		}
	});
}

var sendBtnElem = document.querySelector('#sendBtn')
if(sendBtnElem) {
	
	sendBtnElem.addEventListener('click', function() {
		send()
	})
	
	function send() {
		const msg = document.querySelector('#chatting')
		if(msg.value != null && msg.value.trim() != '' && msg.value !='\n'){ // 글자가 없는게 아니라면
		const jsonmsg = {
			msg: msg.value,
			nickname: tempNick
		}
			ws.send(JSON.stringify(jsonmsg));
		}
		msg.value = ''
	}
}