/*var ws = new WebSocket("ws://" + location.host + "/chating")
wsEvt();

function wsEvt() {
	ws.onopen = function(data){
	}
	
	ws.onmessage = function(data) {
		var msg = data.data;
		if(msg != null && msg.trim() != ''){
			$(".me").append("<p class='chat_message'>" + msg + "</p>");
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
		var msg = $("#chatting").val();
		ws.send(msg);
		$('#chatting').val("");
	}
}*/