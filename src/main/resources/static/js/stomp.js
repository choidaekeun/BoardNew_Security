var sock = new SockJS("/ws/chat");
var stomp = webstomp.over(sock);

stomp.connect({}, function(frame) {
}

/* WebSocket만 이용할 경우 */

var websocket = new WebSocket("/ws/chat");
var stomp = webstomp.over(websocket);

stomp.connect({}, function(frame) {
}