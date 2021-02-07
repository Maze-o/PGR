<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <title>PJA</title>
   
    <link rel="shortcut icon" href="resources/static/asset/favicon.ico">
    <!-- jquery -->
    <script src="resources/static/js/jquery.min.js"></script>
    <!-- css -->
    <link rel="stylesheet" type="text/css" href="resources/static/css/main.css">
    <!-- js -->
    <script type="text/javascript" src="resources/static/js/script"></script>
<style>
    * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    outline-style: none;
}

ul,
ol {
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
    font-size: 10px;
    user-select: none;
}

body {
    font-family: 'IBM Plex Serif', serif;
}

/*delete scroll*/
::-webkit-scrollbar {
    width: 0px;
}

/*wrap*/
.wrap {
    position: relative;
    display: block;
    height: 100%;
}

/*visual*/
.visual {
    position: relative;
    display: block;
    height: 100vh;
    background: crimson;
}

/*content*/
.content {
    position: relative;
    display: flex;
    flex-direction: column;
    height: 100vh;
}

/*header*/
.header {
    position: relative;
    display: block;
    height: 145px;
    overflow: hidden;
}

.h_logo {
    position: relative;
    display: block;
    float: left;
    height: 100%;
    width: 20%;
    font-size: 50px;
    color: #fff;
    background-color: #000;
    line-height: 149px;
    text-align: center;
    text-transform: uppercase;
    font-weight: 700;
    border-top-right-radius: 100px;
    border-bottom-right-radius: 100px;
    z-index: 999999;
}

.h_top {
    position: relative;
    display: block;
    height: 50%;
    overflow: hidden;
}

.h_top_img {
	position: relative;
	display: block;
	height: 100%;
	background: url('../asset/h_top_img') no-repeat center;
}

.h_bot {
    position: absolute;
    display: flex;
    left: 0;
    top: 50%;
    justify-content: space-around;
    height: 50%;
    width: 100%;
    background-color: #000;
}


.h_list {
    position: relative;
    display: flex;
    justify-content: space-around;
    width: 100%;
    height: 100%;
    line-height: 72.5px;
    text-align: center;
    font-size: 20px;
    margin-left: 20%;
    z-index: 9999;
}

.h_list li {
    position: relative;
    display: inline-block;
    text-decoration: none;
    cursor: pointer;
}

.h_list li a::after {
    position: absolute;
    display: block;
    content: "";
    bottom: 20px;
    height: 2px;
    left: 50%;
    background: #fff;
    transition: width 0.3s ease 0s, left 0.3s ease 0s;
    width: 0;
}

.h_list li:hover a::after {
    width: 100%;
    left: 0;
}

.h_list li a {
    color: #fff;
	text-transform: capitalize;
}

.h_login {
    position: relative;
    display: flex;
    justify-content: flex-end;
    height: 100%;
    width: 30%;
    float: right;
}

input[type=button] {
	position: relative;
	display: inline-block;
    background-color: #fff;
    color: #000;
    border: none;
    padding: 10px 10px;
    text-decoration: none;
    text-transform: capitalize;
    text-align: center;
    cursor: pointer;
    margin: 15px 0;
    margin-right: 10px;
    border-radius: 10px;
}

/*main*/
.main {
    position: relative;
    height: 100%;
    display: flex;
    flex: 1;
}

.main_left {
    position: relative;
    display: block;
    flex: 1;
}

.main_mid {
    position: relative;
    display: block;
    flex: 1;
}

.main_right {
    position: relative;
    display: block;
    flex: 1;
}

/*aside*/
.aside {
 	position: relative;
    display: inline-block;
    text-decoration: none;
    cursor: pointer;
}
/*footer*/
.footer {
    position: relative;
    display: block;
    height: 50px;
    background-color: #000;
}
    </style>


</head>

<body>
    <div class="wrap">
        <!-- visual -->
        <div class="visual">
            <!-- visual_slide -->
            <div class="container">
            </div>
        </div>
        <!-- content -->
        <div class="content">
            <!-- header -->
            <div class="header">
                    <a href="#" class="h_logo" title="logo">
                        logo
                    </a>
                <div class="h_top">
                    <!-- 이미지넣을곳 -->
                    <img src="asset/h_top_img.jpg">
                </div>
                <div class="h_bot">
                    <ul class="h_list">
                        <li><a href="#">menu1</a></li>
                        <li><a href="#">menu2</a></li>
                        <li><a href="#">menu3</a></li>
                    </ul>
                    <div class="h_login">
                        <input type="button" name="" value="sign in">
                        <input type="button" name="" value="sign up">
                    </div>
                </div>
            </div>
            <!-- main -->
            <div class="main">
                <div class="main_left">
                </div>
                <div class="main_mid">
                </div>
                <div class="main_right">
                </div>
            </div>
            <div class="aside">
                <a href="/room"><input type="button" value="chatroom"></a>
            </div>
            <!-- footer -->
            <div class="footer">
            </div>
        </div>
    </div>
</body>

</html>