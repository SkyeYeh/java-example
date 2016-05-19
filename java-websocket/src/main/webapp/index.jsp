<%--
  Created by IntelliJ IDEA.
  User: TV6015
  Date: 2016/3/18
  Time: 下午 02:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Websocket</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-2.2.3.min.js"
            integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="form-inline">
    <div class="form-group">
        <label for="messageinput">Input</label>
        <input type="text" id="messageinput" class="form-control"/>
    </div>
</div>
<div class="form-group">
    <button type="button" id="open" class="btn btn-default" onclick="openSocket();">Open</button>
    <button type="button" id="send" class="btn btn-primary" onclick="send();">Send</button>
    <button type="button" id="close" class="btn btn-default" onclick="closeSocket();">Close</button>
</div>
<!-- Server responses get written here -->
<div id="messages"></div>

<!-- Script to utilise the WebSocket -->
<script src="js/websocket.js"></script>
</body>
</html>
