var ws = new WebSocket("ws://" + location.host + "/chating")

const chatBox = document.querySelector('.others')
const chatlog = document.querySelector('.chatlogs')
const currentStatus = document.querySelector('#current_status')
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
		chatBox.innerHTML += "<p class='chat_message'>" + item + "</p>"
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
				chatBox.innerHTML += "<p class='chat_message'>" + msg.value + "</p>"
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
			ws.send(msg.value);
		}
		msg.value = ''
	}
}