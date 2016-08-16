var gcm = require('node-gcm');
var conf = require('../conf');
var sender = new gcm.Sender(conf.gcmKey);

// 주변 라이더에게 주문 정보를 푸시 한다.
function sendOrderMessage(keys, message, data) {
    var gcmMessage = new gcm.Message({restrictedPackageName:'com.yellowfuture.thankurider'});
    // gcmMessage.setNotification('title', 'Thanks');
    // gcmMessage.setNotification('body', message);
    gcmMessage.addNotification(message);
    gcmMessage.addData(data);

    // 메시지 객체, 푸시를 보낼 토큰키 목록, 에러시 재시도할 횟수, 콜백
    sender.send(gcmMessage, {registrationTokens:keys}, 10, function(err, response) {
        if(err) console.error(err);
        else console.log(response);
    });
}

// 라이더가 주문을 수락할 때 주문자에게 푸시한다.
function sendSelectMessage(tokens, message, data) {
    var gcmMessage = new gcm.Message({restrictedPackageName:'com.yellowfuture.thanku'});
    gcmMessage.addNotification(message);
    gcmMessage.addData(data);

    // 메시지 객체, 푸시를 보낼 토큰키 목록, 에러시 재시도할 횟수, 콜백
    sender.send(gcmMessage, {registrationTokens:tokens}, 10, function(err, response) {
        if(err) console.error(err);
        else console.log(response);
    });
}

module.exports = {sendOrderMessage : sendOrderMessage,
sendSelectMessage:sendSelectMessage};
