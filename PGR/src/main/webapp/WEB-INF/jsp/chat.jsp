<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Chating</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	outline-style: none;
}

ul, ol {
	list-style: none;
}

a {
	color: #333;
	text-decoration: none;
}

img {
	border: 0;
	vertical-align: middle;
}

html {
	user-select: none;
}

body {
	font-family: tahoma, serif;
	height: 100%;
}

.wrap {
	position: relative;
}

.pagew {
	position: relative;
	width: 500px;
	margin: 0 auto;
}

.titlebox {
	position: relative;
	display: block;
	width: 500px;
	height: 100px;
	padding: 25px;
	margin: 20px 0;
	border: 1px solid #ccc;
	text-align: center;
	text-transform: uppercase;
	background: #F18C7E;
	color: white;
}

.chatbox {
	position: relative;
	display: block;
	width: 500px;
	min-width: 390px;
	height: 600px;
	background: #fff;
	margin: 20px 0;
	padding: 25px;
	box-shadow: 0 3px #ccc;
	border: 1px solid #ccc;
}

.chatlogs::-webkit-scrollbar {
	width: 10px;
}

.chatlogs::-webkit-scrollbar-thumb {
	border-radius: 10px;
	background: #ccc;
}

.chatlogs {
	position: relative;
	display: block;
	padding: 10px;
	width: 100%;
	height: 450px;
	background: #fbfbfb;
	overflow-x: hidden;
	overflow-y: scroll;
}

.chat_me {
	position: relative;
	display: flex;
	flex-flow: row wrap;
	align-items: flex-start;
	margin-bottom: 10px;
	display: flex;
}

.chat_others {
	position: relative;
	display: flex;
	flex-flow: row wrap;
	align-items: flex-start;
	margin-bottom: 10px;
	display: flex;
}

.chatmessage {

	color: #fff;
	font-size: 20px;
	border-radius: 10px;
}

.me {
	background: #5b84b1ff;
	border-radius: 35%;
	white-space: pre;
	text-align: left;
	padding: 9px 6px;
	left: 61px;
}

.profileme {
	width: 44px;
	height: 44px;
	background: #ccc;
	border-radius: 50%;
	text-align: center;
}

.others {
	background: #fc766aff;
    border-radius: 35%;
    white-space: pre;
    text-align: right;
    padding: 9px 6px;
    position: relative;
    right: 40px;
    margin-inline-start: auto;
}

.profileothers {
	width: 44px;
    height: 44px;
    background: #ccc;
    border-radius: 50%;
    text-align: center;
    position: relative;
    right:-380px
}


.chatform {
	position: relative;
	display: flex;
	align-items: flex-start;
	margin-top: 20px;
}

.chatform textarea {
	background: #fbfbfb;
	width: 75%;
	height: 50px;
	border: 2px solid #ccc;
	resize: none;
	padding: 12px;
	font-size: 18px;
	font-family: tahoma, serif;
	color: #333;
}


.chatform textarea::-webkit-scrollbar {
	width: 10px;
}

.chatform textarea::-webkit-scrollbar-thumb {
	border-radius: 10px;
	background: #ccc;
}

.chatform #yourMsg {
	display:none;
}

.chatform button {
    position: relative;
    top: -53px;
    right: -282px;
    background: #fc766aff;
    padding: 6px 15px;
    margin: 0 61px;
    border-radius: 3px;
    font-size: 30px;
    color: #fff;
    border: none;
    box-shadow: 0 3px 0 #333;
    cursor: pointer;
    transition: background 0.2s;
    text-transform: capitalize;
}

.chatform button:active {
	background: #fb4737;
}


</style>
</head>

<script type="text/javascript">
	var ws;

	function wsOpen() {
		//웹소켓 전송시 현재 방의 번호를 넘겨서 보낸다.
		ws = new WebSocket("ws://" + location.host + "/chating/"
				+ $("#roomNumber").val());
		wsEvt();
	}

	function wsEvt() {
		ws.onopen = function(data) {
			//소켓이 열리면 동작
		}

		ws.onmessage = function(data) {
			//메시지를 받으면 동작
			var msg = data.data;
			if (msg != null && msg.trim() != '') {
				var d = JSON.parse(msg);
				if (d.type == "getId") {
					var si = d.sessionId != null ? d.sessionId : "";
					if (si != '') {
						$("#sessionId").val(si);
					}
				} else if (d.type == "message") {
					if (d.sessionId == $("#sessionId").val()) {
						$(".chatmessage").append(
								"<div class='chat_me'>"
										+ "<span class='profileme'>"
										+ d.userName + "</span>"
										+ "<span class='me'> " + d.msg
										+ "</span>" + "</div>");
					} else {
						$(".chatmessage").append(
								"<div class='chat_others'>"
										+ "<span class='profileothers'>"
										+ d.userName + "</span>"
										+ "<span class='others'> " + d.msg
										+ "</span>" + "</div>");
					}
				} else {
					console.warn("unknown type!")
				}
			}
		}

		document.addEventListener("keypress", function(e) {
			if (e.keyCode == 13) { //enter press
				send();
			}
		});
	}

	function chatName() {
		var userName = $("#userName").val();
		if (userName == null || userName.trim() == "") {
			alert("사용자 이름을 입력해주세요.");
			$("#userName").focus();
		} else {
			wsOpen();
			$("#yourName").hide();
			$("#yourMsg").show();
		}
	}

	function send() {
		var option = {
			type : "message",
			roomNumber : $("#roomNumber").val(),
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : $("#chatting").val()
		}
		ws.send(JSON.stringify(option))
		$('#chatting').val("");
	}
</script>
<body>
	<div class="wrap">
		<div id="pagew" class="pagew">
			<div class="titlebox">
				<h1>${roomName}</h1>
			</div>
			<input type="hidden" id="sessionId" value=""> <input
				type="hidden" id="roomNumber" value="${roomNumber}">
			<div id="chatbox" class="chatbox">
				<div class="chatlogs">
					<div class="chat"></div>
					<div class="chatmessage"></div>
				</div>
			
			
				<div class="chatform">
					<div id="yourName" >	
							<span><textarea id="userName" placeholder="Please enter your id."></textarea>
							<button onclick="chatName()" id="startBtn">Click</button></span>			
					</div>
					
					<div id="yourMsg" >
							<textarea id="chatting" placeholder="Please enter your message."></textarea>
							<button onclick="send()" id="sendBtn">send</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>